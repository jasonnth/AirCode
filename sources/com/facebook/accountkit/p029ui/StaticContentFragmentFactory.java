package com.facebook.accountkit.p029ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.accountkit.C3354R;

/* renamed from: com.facebook.accountkit.ui.StaticContentFragmentFactory */
final class StaticContentFragmentFactory {
    private static final String LAYOUT_RESOURCE_ID_KEY = "layoutResourceId";
    private static final String LOGIN_FLOW_STATE_KEY = "loginFlowState";

    /* renamed from: com.facebook.accountkit.ui.StaticContentFragmentFactory$StaticContentFragment */
    public static final class StaticContentFragment extends ContentFragment {
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

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(getViewState().getInt(StaticContentFragmentFactory.LAYOUT_RESOURCE_ID_KEY, C3354R.layout.com_accountkit_fragment_static_content), container, false);
        }

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return LoginFlowState.valueOf(getViewState().getString(StaticContentFragmentFactory.LOGIN_FLOW_STATE_KEY, LoginFlowState.NONE.name()));
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            int color;
            super.onViewReadyWithState(view, viewState);
            View iconView = view.findViewById(C3354R.C3356id.com_accountkit_icon_view);
            if (iconView != null) {
                if (ViewUtility.useLegacy(getUIManager())) {
                    color = ViewUtility.getColor((Context) getActivity(), C3354R.attr.com_accountkit_icon_color, -1);
                } else {
                    color = ViewUtility.getPrimaryColor(getActivity(), getUIManager());
                }
                if (iconView instanceof ImageView) {
                    ViewUtility.applyThemeColor((Context) getActivity(), (ImageView) iconView, color);
                } else {
                    ViewUtility.applyThemeColor((Context) getActivity(), iconView.getBackground(), color);
                }
            }
        }
    }

    StaticContentFragmentFactory() {
    }

    static StaticContentFragment create(UIManager uiManager, LoginFlowState loginFlowState, int layoutResourceId) {
        StaticContentFragment fragment = create(uiManager, loginFlowState);
        fragment.getViewState().putInt(LAYOUT_RESOURCE_ID_KEY, layoutResourceId);
        return fragment;
    }

    static StaticContentFragment create(UIManager uiManager, LoginFlowState loginFlowState) {
        StaticContentFragment fragment = new StaticContentFragment();
        Bundle viewState = fragment.getViewState();
        viewState.putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
        viewState.putString(LOGIN_FLOW_STATE_KEY, loginFlowState.name());
        return fragment;
    }
}
