package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import java.util.Map;

public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    /* access modifiers changed from: private */
    public final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Map map;
        Map map2;
        Map map3 = null;
        ProducerListener listener = producerContext.getListener();
        String requestId = producerContext.getId();
        listener.onProducerStart(requestId, getProducerName());
        CacheKey cacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
        CloseableReference<CloseableImage> cachedReference = this.mMemoryCache.get(cacheKey);
        if (cachedReference != null) {
            boolean isFinal = ((CloseableImage) cachedReference.get()).getQualityInfo().isOfFullQuality();
            if (isFinal) {
                String producerName = getProducerName();
                if (listener.requiresExtraMap(requestId)) {
                    map2 = ImmutableMap.m1780of(VALUE_FOUND, "true");
                } else {
                    map2 = null;
                }
                listener.onProducerFinishWithSuccess(requestId, producerName, map2);
                consumer.onProgressUpdate(1.0f);
            }
            consumer.onNewResult(cachedReference, isFinal);
            cachedReference.close();
            if (isFinal) {
                return;
            }
        }
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
            String producerName2 = getProducerName();
            if (listener.requiresExtraMap(requestId)) {
                map = ImmutableMap.m1780of(VALUE_FOUND, InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
            } else {
                map = null;
            }
            listener.onProducerFinishWithSuccess(requestId, producerName2, map);
            consumer.onNewResult(null, true);
            return;
        }
        Consumer<CloseableReference<CloseableImage>> wrappedConsumer = wrapConsumer(consumer, cacheKey);
        String producerName3 = getProducerName();
        if (listener.requiresExtraMap(requestId)) {
            map3 = ImmutableMap.m1780of(VALUE_FOUND, InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        }
        listener.onProducerFinishWithSuccess(requestId, producerName3, map3);
        this.mInputProducer.produceResults(wrappedConsumer, producerContext);
    }

    /* access modifiers changed from: protected */
    public Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey) {
        return new DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>(consumer) {
            public void onNewResultImpl(CloseableReference<CloseableImage> newResult, boolean isLast) {
                if (newResult == null) {
                    if (isLast) {
                        getConsumer().onNewResult(null, true);
                    }
                } else if (((CloseableImage) newResult.get()).isStateful()) {
                    getConsumer().onNewResult(newResult, isLast);
                } else {
                    if (!isLast) {
                        CloseableReference<CloseableImage> currentCachedResult = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey);
                        if (currentCachedResult != null) {
                            try {
                                QualityInfo newInfo = ((CloseableImage) newResult.get()).getQualityInfo();
                                QualityInfo cachedInfo = ((CloseableImage) currentCachedResult.get()).getQualityInfo();
                                if (cachedInfo.isOfFullQuality() || cachedInfo.getQuality() >= newInfo.getQuality()) {
                                    getConsumer().onNewResult(currentCachedResult, false);
                                    return;
                                }
                                CloseableReference.closeSafely(currentCachedResult);
                            } finally {
                                CloseableReference.closeSafely(currentCachedResult);
                            }
                        }
                    }
                    CloseableReference<CloseableImage> newCachedResult = BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, newResult);
                    if (isLast) {
                        try {
                            getConsumer().onProgressUpdate(1.0f);
                        } catch (Throwable th) {
                            CloseableReference.closeSafely(newCachedResult);
                            throw th;
                        }
                    }
                    Consumer consumer = getConsumer();
                    if (newCachedResult != null) {
                        newResult = newCachedResult;
                    }
                    consumer.onNewResult(newResult, isLast);
                    CloseableReference.closeSafely(newCachedResult);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return PRODUCER_NAME;
    }
}
