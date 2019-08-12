package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.FixedLoadProvider;
import com.bumptech.glide.provider.LoadProvider;

public class GenericTranscodeRequest<ModelType, DataType, ResourceType> extends GenericRequestBuilder<ModelType, DataType, ResourceType, ResourceType> {
    private final Class<DataType> dataClass;
    private final ModelLoader<ModelType, DataType> modelLoader;
    private final OptionsApplier optionsApplier;
    private final Class<ResourceType> resourceClass;

    private static <A, T, Z, R> LoadProvider<A, T, Z, R> build(Glide glide, ModelLoader<A, T> modelLoader2, Class<T> dataClass2, Class<Z> resourceClass2, ResourceTranscoder<Z, R> transcoder) {
        return new FixedLoadProvider(modelLoader2, transcoder, glide.buildDataProvider(dataClass2, resourceClass2));
    }

    GenericTranscodeRequest(Context context, Glide glide, Class<ModelType> modelClass, ModelLoader<ModelType, DataType> modelLoader2, Class<DataType> dataClass2, Class<ResourceType> resourceClass2, RequestTracker requestTracker, Lifecycle lifecycle, OptionsApplier optionsApplier2) {
        super(context, modelClass, build(glide, modelLoader2, dataClass2, resourceClass2, UnitTranscoder.get()), resourceClass2, glide, requestTracker, lifecycle);
        this.modelLoader = modelLoader2;
        this.dataClass = dataClass2;
        this.resourceClass = resourceClass2;
        this.optionsApplier = optionsApplier2;
    }
}
