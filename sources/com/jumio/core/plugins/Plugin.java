package com.jumio.core.plugins;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.core.overlay.Overlay;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;

public interface Plugin {
    ExtractionClient<ExtractionUpdate, StaticModel> getExtractionClient(Context context);

    Overlay getOverlay(Context context, Bundle bundle);

    <T> void populateData(Context context, T t);
}
