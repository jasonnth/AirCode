package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MessageImageFullScreenFragment extends AirFragment {
    public static final String IMG_URL = "img_url";
    @BindView
    AirImageView imageView;

    public static MessageImageFullScreenFragment newInstance(Bundle args) {
        MessageImageFullScreenFragment frag = new MessageImageFullScreenFragment();
        frag.setArguments(args);
        return frag;
    }

    public static Intent newIntent(Context context, String url) {
        Bundle args = new Bundle();
        args.putString(IMG_URL, url);
        return AutoAirActivity.intentForFragment(context, MessageImageFullScreenFragment.class, args);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_message_image_full_screen, container, false);
        ButterKnife.bind(this, view);
        this.imageView.setImageUrl(getArguments().getString(IMG_URL));
        return view;
    }
}
