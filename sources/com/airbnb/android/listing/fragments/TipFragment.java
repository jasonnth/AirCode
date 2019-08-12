package com.airbnb.android.listing.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;

public class TipFragment extends AirFragment {
    private static final String ARG_NAVIGATION_TAG = "arg_navigation_tag";
    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_TITLE = "arg_title";
    @BindView
    DocumentMarquee marquee;
    @BindView
    TextRow text;
    @BindView
    AirToolbar toolbar;

    public static class Builder {
        private final Context context;
        private final NavigationTag navigationTag;
        private CharSequence text;
        private CharSequence title;

        Builder(Context context2, NavigationTag navigationTag2) {
            this.context = (Context) Check.notNull(context2);
            this.navigationTag = (NavigationTag) Check.notNull(navigationTag2);
        }

        public Builder addTitle(CharSequence title2) {
            this.title = title2;
            return this;
        }

        public Builder addTitleRes(int titleRes) {
            return addTitle(this.context.getString(titleRes));
        }

        public Builder addText(CharSequence text2) {
            this.text = text2;
            return this;
        }

        public Builder addTextRes(int textRes) {
            return addText(this.context.getString(textRes));
        }

        public TipFragment build() {
            return TipFragment.create((CharSequence) Check.notNull(this.title), (CharSequence) Check.notNull(this.text), this.navigationTag);
        }
    }

    public static Builder builder(Context context, NavigationTag navigationTag) {
        return new Builder(context, navigationTag);
    }

    public static TipFragment create(CharSequence title, CharSequence text2, NavigationTag navigationTag) {
        return (TipFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new TipFragment()).putCharSequence(ARG_TITLE, title)).putCharSequence(ARG_TEXT, text2)).putSerializable(ARG_NAVIGATION_TAG, navigationTag)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_title_and_text, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        Bundle args = getArguments();
        this.marquee.setTitle(args.getCharSequence(ARG_TITLE));
        this.text.setText(args.getCharSequence(ARG_TEXT));
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return (NavigationTag) getArguments().getSerializable(ARG_NAVIGATION_TAG);
    }
}
