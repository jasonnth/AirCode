package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;

public class CancellationGuestCancelFragment extends AirFragment {
    private Listener listener;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        Strap getLoggingParams();

        void onConfirmGuestCancel();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_guest_cancel, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    @OnClick
    public void onClickButton() {
        this.listener.onConfirmGuestCancel();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostCancellationGuestNeedsToCancel;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mix(this.listener.getLoggingParams());
    }
}
