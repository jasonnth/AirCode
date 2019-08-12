package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MessageInputTwoRows_ViewBinding */
public class MessageInputTwoRows_ViewBinding extends MessageInputRow_ViewBinding {
    private MessageInputTwoRows target;
    private View view2131493365;
    private View view2131493366;
    private View view2131493367;
    private View view2131493368;

    public MessageInputTwoRows_ViewBinding(final MessageInputTwoRows target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, R.id.text_icon, "field 'textIcon' and method 'showTextInput'");
        target2.textIcon = (AirImageView) Utils.castView(view, R.id.text_icon, "field 'textIcon'", AirImageView.class);
        this.view2131493365 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showTextInput();
            }
        });
        View view2 = Utils.findRequiredView(source, R.id.saved_messages_icon, "field 'savedMessageIcon' and method 'showSavedMessages'");
        target2.savedMessageIcon = (AirImageView) Utils.castView(view2, R.id.saved_messages_icon, "field 'savedMessageIcon'", AirImageView.class);
        this.view2131493368 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showSavedMessages();
            }
        });
        View view3 = Utils.findRequiredView(source, R.id.camera_icon, "field 'cameraIcon' and method 'showCamera'");
        target2.cameraIcon = (AirImageView) Utils.castView(view3, R.id.camera_icon, "field 'cameraIcon'", AirImageView.class);
        this.view2131493366 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showCamera();
            }
        });
        View view4 = Utils.findRequiredView(source, R.id.photos_icon, "field 'photosIcon' and method 'showPhotos'");
        target2.photosIcon = (AirImageView) Utils.castView(view4, R.id.photos_icon, "field 'photosIcon'", AirImageView.class);
        this.view2131493367 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showPhotos();
            }
        });
    }

    public void unbind() {
        MessageInputTwoRows target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textIcon = null;
        target2.savedMessageIcon = null;
        target2.cameraIcon = null;
        target2.photosIcon = null;
        this.view2131493365.setOnClickListener(null);
        this.view2131493365 = null;
        this.view2131493368.setOnClickListener(null);
        this.view2131493368 = null;
        this.view2131493366.setOnClickListener(null);
        this.view2131493366 = null;
        this.view2131493367.setOnClickListener(null);
        this.view2131493367 = null;
        super.unbind();
    }
}
