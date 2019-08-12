package com.airbnb.android.lib.controller;

import android.content.Context;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader.PreloadModelProvider;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.Collections;
import java.util.List;

public class PropertyImagesPagerPreloader extends PagerPreloader<String> {
    private static final int MAX_IMAGE_PRELOAD_COUNT = 5;

    public interface PropertyImagesAdapter {
        int getCount();

        String getPictureUrlForAdapterPosition(int i);
    }

    public /* bridge */ /* synthetic */ void onPageScrollStateChanged(int i) {
        super.onPageScrollStateChanged(i);
    }

    public /* bridge */ /* synthetic */ void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
    }

    public /* bridge */ /* synthetic */ void onPageSelected(int i) {
        super.onPageSelected(i);
    }

    public PropertyImagesPagerPreloader(final Context context, final PropertyImagesAdapter adapter, int[] preloadSize) {
        super(1, adapter.getCount(), new PreloadModelProvider<String>() {
            public List<String> getPreloadItems(int position) {
                return Collections.singletonList(PropertyImagesAdapter.this.getPictureUrlForAdapterPosition(position));
            }

            public GenericRequestBuilder getPreloadRequestBuilder(String pictureUrl) {
                return Glide.with(context).load(pictureUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE);
            }
        }, PropertyImagesPagerPreloader$$Lambda$1.lambdaFactory$(preloadSize), 5);
    }

    static /* synthetic */ int[] lambda$new$0(int[] preloadSize, String item, int adapterPosition, int perItemPosition) {
        return preloadSize;
    }
}
