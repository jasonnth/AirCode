package com.facebook.accountkit.p029ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.Utility;

/* renamed from: com.facebook.accountkit.ui.TitleFragmentFactory */
final class TitleFragmentFactory {

    /* renamed from: com.facebook.accountkit.ui.TitleFragmentFactory$TitleFragment */
    public static class TitleFragment extends LoginFragment {
        private static final String TITLE_KEY = "title";
        private static final String TITLE_RESOURCE_ARGS_KEY = "titleResourceArgs";
        private static final String TITLE_RESOURCE_ID_KEY = "titleResourceId";
        protected TextView titleView;

        public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
        }

        public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }

        public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
        }

        private String getTitle() {
            return getViewState().getString("title");
        }

        public void setTitle(String title) {
            getViewState().putString("title", title);
            updateTitleView();
        }

        private String[] getTitleResourceArgs() {
            return getViewState().getStringArray(TITLE_RESOURCE_ARGS_KEY);
        }

        private int getTitleResourceId() {
            return getViewState().getInt(TITLE_RESOURCE_ID_KEY);
        }

        public void setTitleResourceId(int titleResourceId, String... args) {
            Bundle viewState = getViewState();
            viewState.putInt(TITLE_RESOURCE_ID_KEY, titleResourceId);
            viewState.putStringArray(TITLE_RESOURCE_ARGS_KEY, args);
            updateTitleView();
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_title, container, false);
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            this.titleView = (TextView) view.findViewById(C3354R.C3356id.com_accountkit_title);
            updateTitleView();
        }

        private void updateTitleView() {
            if (this.titleView != null) {
                String title = getTitle();
                int titleResourceId = 0;
                if (Utility.isNullOrEmpty(title)) {
                    titleResourceId = getTitleResourceId();
                    String[] titleResourceArgs = getTitleResourceArgs();
                    if (!(titleResourceId <= 0 || titleResourceArgs == null || titleResourceArgs.length == 0 || getActivity() == null)) {
                        title = getString(titleResourceId, (Object[]) titleResourceArgs);
                        titleResourceId = 0;
                    }
                }
                if (!Utility.isNullOrEmpty(title)) {
                    this.titleView.setText(title);
                    this.titleView.setVisibility(0);
                } else if (titleResourceId > 0) {
                    this.titleView.setText(titleResourceId);
                    this.titleView.setVisibility(0);
                } else {
                    this.titleView.setVisibility(8);
                }
            }
        }
    }

    TitleFragmentFactory() {
    }

    public static TitleFragment create(UIManager uiManager) {
        TitleFragment titleFragment = new TitleFragment();
        titleFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
        return titleFragment;
    }

    public static TitleFragment create(UIManager uiManager, int titleResourceId, String... args) {
        TitleFragment titleFragment = create(uiManager);
        titleFragment.setTitleResourceId(titleResourceId, args);
        return titleFragment;
    }
}
