package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.nativecode.JpegTranscoder;
import com.facebook.imagepipeline.producers.JobScheduler.JobRunnable;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Executor;

public class ResizeAndRotateProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final int DEFAULT_JPEG_QUALITY = 85;
    private static final String FRACTION_KEY = "Fraction";
    @VisibleForTesting
    static final int MAX_JPEG_SCALE_NUMERATOR = 8;
    @VisibleForTesting
    static final int MIN_TRANSFORM_INTERVAL_MS = 100;
    private static final String ORIGINAL_SIZE_KEY = "Original size";
    private static final String PRODUCER_NAME = "ResizeAndRotateProducer";
    private static final String REQUESTED_SIZE_KEY = "Requested size";
    private static final float ROUNDUP_FRACTION = 0.6666667f;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    /* access modifiers changed from: private */
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    private class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        /* access modifiers changed from: private */
        public boolean mIsCancelled = false;
        /* access modifiers changed from: private */
        public final JobScheduler mJobScheduler;
        /* access modifiers changed from: private */
        public final ProducerContext mProducerContext;

        public TransformingConsumer(final Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new JobRunnable(ResizeAndRotateProducer.this) {
                public void run(EncodedImage encodedImage, boolean isLast) {
                    TransformingConsumer.this.doTransform(encodedImage, isLast);
                }
            }, 100);
            this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks(ResizeAndRotateProducer.this) {
                public void onIsIntermediateResultExpectedChanged() {
                    if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                        TransformingConsumer.this.mJobScheduler.scheduleJob();
                    }
                }

                public void onCancellationRequested() {
                    TransformingConsumer.this.mJobScheduler.clearJob();
                    TransformingConsumer.this.mIsCancelled = true;
                    consumer.onCancellation();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage newResult, boolean isLast) {
            if (!this.mIsCancelled) {
                if (newResult != null) {
                    TriState shouldTransform = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), newResult);
                    if (!isLast && shouldTransform == TriState.UNSET) {
                        return;
                    }
                    if (shouldTransform != TriState.YES) {
                        getConsumer().onNewResult(newResult, isLast);
                    } else if (!this.mJobScheduler.updateJob(newResult, isLast)) {
                    } else {
                        if (isLast || this.mProducerContext.isIntermediateResultExpected()) {
                            this.mJobScheduler.scheduleJob();
                        }
                    }
                } else if (isLast) {
                    getConsumer().onNewResult(null, true);
                }
            }
        }

        /* access modifiers changed from: private */
        public void doTransform(EncodedImage encodedImage, boolean isLast) {
            this.mProducerContext.getListener().onProducerStart(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME);
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            PooledByteBufferOutputStream outputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.newOutputStream();
            Map<String, String> extraMap = null;
            InputStream is = null;
            try {
                int numerator = ResizeAndRotateProducer.getScaleNumerator(imageRequest, encodedImage);
                extraMap = getExtraMap(encodedImage, imageRequest, numerator);
                is = encodedImage.getInputStream();
                JpegTranscoder.transcodeJpeg(is, outputStream, ResizeAndRotateProducer.getRotationAngle(imageRequest, encodedImage), numerator, 85);
                CloseableReference<PooledByteBuffer> ref = CloseableReference.m1871of(outputStream.toByteBuffer());
                try {
                    EncodedImage ret = new EncodedImage(ref);
                    try {
                        ret.setImageFormat(ImageFormat.JPEG);
                        ret.parseMetaData();
                        this.mProducerContext.getListener().onProducerFinishWithSuccess(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, extraMap);
                        getConsumer().onNewResult(ret, isLast);
                        EncodedImage.closeSafely(ret);
                    } catch (Throwable th) {
                        th = th;
                        EncodedImage encodedImage2 = ret;
                        CloseableReference.closeSafely(ref);
                        throw th;
                    }
                    try {
                        CloseableReference.closeSafely(ref);
                        Closeables.closeQuietly(is);
                        outputStream.close();
                        EncodedImage encodedImage3 = ret;
                    } catch (Exception e) {
                        e = e;
                        EncodedImage encodedImage4 = ret;
                        try {
                            this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, e, extraMap);
                            getConsumer().onFailure(e);
                            Closeables.closeQuietly(is);
                            outputStream.close();
                        } catch (Throwable th2) {
                            th = th2;
                            Closeables.closeQuietly(is);
                            outputStream.close();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        EncodedImage encodedImage5 = ret;
                        Closeables.closeQuietly(is);
                        outputStream.close();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    CloseableReference.closeSafely(ref);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }

        private Map<String, String> getExtraMap(EncodedImage encodedImage, ImageRequest imageRequest, int numerator) {
            String requestedSize;
            if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            String originalSize = encodedImage.getWidth() + "x" + encodedImage.getHeight();
            if (imageRequest.getResizeOptions() != null) {
                requestedSize = imageRequest.getResizeOptions().width + "x" + imageRequest.getResizeOptions().height;
            } else {
                requestedSize = "Unspecified";
            }
            return ImmutableMap.m1783of(ResizeAndRotateProducer.ORIGINAL_SIZE_KEY, originalSize, ResizeAndRotateProducer.REQUESTED_SIZE_KEY, requestedSize, ResizeAndRotateProducer.FRACTION_KEY, numerator > 0 ? numerator + "/8" : "", "queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
        }
    }

    public ResizeAndRotateProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> inputProducer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, context), context);
    }

    /* access modifiers changed from: private */
    public static TriState shouldTransform(ImageRequest request, EncodedImage encodedImage) {
        if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        if (encodedImage.getImageFormat() != ImageFormat.JPEG) {
            return TriState.NO;
        }
        return TriState.valueOf(getRotationAngle(request, encodedImage) != 0 || shouldResize(getScaleNumerator(request, encodedImage)));
    }

    @VisibleForTesting
    static float determineResizeRatio(ResizeOptions resizeOptions, int width, int height) {
        if (resizeOptions == null) {
            return 1.0f;
        }
        float ratio = Math.max(((float) resizeOptions.width) / ((float) width), ((float) resizeOptions.height) / ((float) height));
        if (((float) width) * ratio > 2048.0f) {
            ratio = 2048.0f / ((float) width);
        }
        if (((float) height) * ratio > 2048.0f) {
            return 2048.0f / ((float) height);
        }
        return ratio;
    }

    @VisibleForTesting
    static int roundNumerator(float maxRatio) {
        return (int) (0.6666667f + (8.0f * maxRatio));
    }

    /* access modifiers changed from: private */
    public static int getScaleNumerator(ImageRequest imageRequest, EncodedImage encodedImage) {
        int widthAfterRotation;
        int heightAfterRotation;
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null) {
            return 8;
        }
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        boolean swapDimensions = rotationAngle == 90 || rotationAngle == 270;
        if (swapDimensions) {
            widthAfterRotation = encodedImage.getHeight();
        } else {
            widthAfterRotation = encodedImage.getWidth();
        }
        if (swapDimensions) {
            heightAfterRotation = encodedImage.getWidth();
        } else {
            heightAfterRotation = encodedImage.getHeight();
        }
        int numerator = roundNumerator(determineResizeRatio(resizeOptions, widthAfterRotation, heightAfterRotation));
        if (numerator > 8) {
            return 8;
        }
        if (numerator < 1) {
            return 1;
        }
        return numerator;
    }

    /* access modifiers changed from: private */
    public static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        boolean z = false;
        if (!imageRequest.getAutoRotateEnabled()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return rotationAngle;
    }

    private static boolean shouldResize(int numerator) {
        return numerator < 8;
    }
}
