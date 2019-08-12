package com.airbnb.android.contentframework.imagepicker;

import android.net.Uri;
import com.airbnb.android.contentframework.imagepicker.MediaGridItemView.OnMediaItemClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class MediaGridItemEpoxyModel extends AirEpoxyModel<MediaGridItemView> {
    OnMediaItemClickListener onMediaItemClickListener;
    List<Uri> selectedItems;
    Uri uri;

    public void bind(MediaGridItemView view) {
        boolean z = true;
        super.bind(view);
        view.setMediaUri(this.uri);
        view.setListener(this.onMediaItemClickListener);
        int selectedPosition = this.selectedItems.indexOf(this.uri);
        if (selectedPosition >= 0) {
            view.setCheckedNum(selectedPosition + 1);
            view.setEnabled(true);
            return;
        }
        view.setCheckedNum(Integer.MIN_VALUE);
        if (this.selectedItems.size() >= 4) {
            z = false;
        }
        view.setEnabled(z);
    }

    public void unbind(MediaGridItemView view) {
        super.unbind(view);
        view.setListener(null);
    }
}
