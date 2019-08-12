package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.p029ui.AdvancedUIManager.AdvancedUIManagerListener;
import com.facebook.accountkit.p029ui.SkinManager.Skin;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;
import com.facebook.accountkit.p029ui.UIManager.UIManagerListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.accountkit.ui.StateStackManager */
final class StateStackManager implements OnBackStackChangedListener, AdvancedUIManagerListener, UIManagerListener {
    private final WeakReference<AccountKitActivity> activityRef;
    private final AccountKitConfiguration configuration;
    private ContentController contentController;
    private final Map<LoginFlowState, ContentController> contentControllerMap = new HashMap();
    private final List<OnPopListener> onPopListeners = new ArrayList();
    private final List<OnPushListener> onPushListeners = new ArrayList();
    private final UIManager uiManager;

    /* renamed from: com.facebook.accountkit.ui.StateStackManager$FragmentType */
    private enum FragmentType {
        BODY,
        FOOTER,
        HEADER
    }

    /* renamed from: com.facebook.accountkit.ui.StateStackManager$OnPopListener */
    interface OnPopListener {
        void onContentPopped();
    }

    /* renamed from: com.facebook.accountkit.ui.StateStackManager$OnPushListener */
    interface OnPushListener {
        void onContentControllerReady(ContentController contentController);

        void onContentPushed();
    }

    StateStackManager(AccountKitActivity activity, AccountKitConfiguration configuration2) {
        UIManager uIManager;
        this.activityRef = new WeakReference<>(activity);
        activity.getFragmentManager().addOnBackStackChangedListener(this);
        this.configuration = configuration2;
        if (configuration2 == null) {
            uIManager = null;
        } else {
            uIManager = configuration2.getUIManager();
        }
        this.uiManager = uIManager;
        if (this.uiManager instanceof AdvancedUIManagerWrapper) {
            ((AdvancedUIManagerWrapper) this.uiManager).getAdvancedUIManager().setAdvancedUIManagerListener(this);
        } else if (this.uiManager != null) {
            this.uiManager.setUIManagerListener(this);
        }
    }

    public void onBack() {
        AccountKitActivity activity = (AccountKitActivity) this.activityRef.get();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public void onBackStackChanged() {
        AccountKitActivity activity = (AccountKitActivity) this.activityRef.get();
        if (activity != null) {
            updateContentController(activity);
        }
    }

    public void onCancel() {
        AccountKitActivity activity = (AccountKitActivity) this.activityRef.get();
        if (activity != null) {
            activity.sendCancelResult();
        }
    }

    /* access modifiers changed from: 0000 */
    public ContentController getContentController() {
        return this.contentController;
    }

    /* access modifiers changed from: 0000 */
    public void popBackStack(LoginFlowState toState, OnPopListener onPopListener) {
        AccountKitActivity activity = (AccountKitActivity) this.activityRef.get();
        if (activity != null) {
            if (onPopListener != null) {
                this.onPopListeners.add(onPopListener);
            }
            ContentController toContentController = ensureContentController(activity, toState, LoginFlowState.NONE, false);
            activity.getFragmentManager().popBackStack();
            ensureNextButton(activity, toContentController);
        }
    }

    /* access modifiers changed from: 0000 */
    public void multiPopBackStack(OnPopListener onPopListener) {
        AccountKitActivity activity = (AccountKitActivity) this.activityRef.get();
        if (activity != null) {
            if (onPopListener != null) {
                this.onPopListeners.add(onPopListener);
            }
            activity.getFragmentManager().popBackStack();
            ensureNextButton(activity, null);
        }
    }

    private void ensureNextButton(AccountKitActivity activity, ContentController contentController2) {
        if (ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
            FragmentManager fm = activity.getFragmentManager();
            if (contentController2 == null) {
                FragmentTransaction removeBtnTransaction = fm.beginTransaction();
                if (remove(fm, removeBtnTransaction, C3354R.C3356id.com_accountkit_content_bottom_fragment) == null) {
                    remove(fm, removeBtnTransaction, C3354R.C3356id.com_accountkit_content_bottom_keyboard_fragment);
                }
                removeBtnTransaction.commit();
                return;
            }
            ContentFragment contentBottomFragment = contentController2.getBottomFragment();
            FragmentTransaction btnTransaction = fm.beginTransaction();
            if (contentBottomFragment.isKeyboardFragment()) {
                remove(fm, btnTransaction, C3354R.C3356id.com_accountkit_content_bottom_fragment);
                replace(fm, btnTransaction, C3354R.C3356id.com_accountkit_content_bottom_keyboard_fragment, contentBottomFragment);
            } else {
                remove(fm, btnTransaction, C3354R.C3356id.com_accountkit_content_bottom_keyboard_fragment);
                replace(fm, btnTransaction, C3354R.C3356id.com_accountkit_content_bottom_fragment, contentBottomFragment);
            }
            btnTransaction.commit();
        }
    }

    /* access modifiers changed from: 0000 */
    public void pushError(AccountKitActivity activity, LoginFlowManager loginFlowManager, LoginFlowState returnState, AccountKitError error, OnPushListener onPushListener) {
        this.uiManager.onError(error);
        pushState(activity, loginFlowManager, returnState, onPushListener);
    }

    /* access modifiers changed from: 0000 */
    public OnPushListener getErrorOnPushListener(final String errorMessage) {
        return new OnPushListener() {
            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ErrorContentController) {
                    ((ErrorContentController) contentController).setErrorMessage(errorMessage);
                }
            }

            public void onContentPushed() {
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void pushState(AccountKitActivity activity, LoginFlowManager loginFlowManager, OnPushListener onPushListener) {
        pushState(activity, loginFlowManager, LoginFlowState.NONE, onPushListener);
    }

    private void pushState(AccountKitActivity activity, LoginFlowManager loginFlowManager, LoginFlowState returnState, OnPushListener onPushListener) {
        Fragment headerFragment;
        int contentPaddingBottomResourceId;
        int contentPaddingTopResourceId;
        int contentPaddingTop;
        int contentPaddingBottom;
        LoginFlowState loginFlowState = loginFlowManager.getFlowState();
        ContentController fromContentController = getContentController();
        ContentController toContentController = ensureContentController(activity, loginFlowState, returnState, false);
        if (toContentController != null && fromContentController != toContentController) {
            Logger.logUIManager(this.uiManager);
            if ((loginFlowState != LoginFlowState.RESEND || !(toContentController instanceof ResendContentController)) && ((loginFlowState != LoginFlowState.CODE_INPUT || !(toContentController instanceof ConfirmationCodeContentController)) && !(toContentController instanceof ErrorContentController))) {
                headerFragment = this.uiManager.getHeaderFragment(loginFlowState);
                Logger.logUICustomFragment(this.configuration.getLoginType(), FragmentType.HEADER.name(), headerFragment != null);
            } else {
                headerFragment = toContentController.getHeaderFragment();
            }
            Fragment contentCenterFragment = this.uiManager.getBodyFragment(loginFlowState);
            Logger.logUICustomFragment(this.configuration.getLoginType(), FragmentType.BODY.name(), contentCenterFragment != null);
            Fragment footerFragment = this.uiManager.getFooterFragment(loginFlowState);
            Logger.logUICustomFragment(this.configuration.getLoginType(), FragmentType.FOOTER.name(), footerFragment != null);
            if (headerFragment == null) {
                headerFragment = BaseUIManager.getDefaultHeaderFragment(this.uiManager, loginFlowState, loginFlowManager.getLoginType());
            }
            if (contentCenterFragment == null) {
                contentCenterFragment = BaseUIManager.getDefaultBodyFragment(this.uiManager, loginFlowState);
            }
            if (footerFragment == null) {
                footerFragment = BaseUIManager.getDefaultFooterFragment(this.uiManager);
            }
            TextPosition textPosition = this.uiManager.getTextPosition(loginFlowState);
            if (toContentController instanceof ButtonContentController) {
                ButtonType buttonType = this.uiManager.getButtonType(loginFlowState);
                if (buttonType != null) {
                    ((ButtonContentController) toContentController).setButtonType(buttonType);
                }
            }
            Fragment contentTopFragment = toContentController.getTopFragment();
            ContentFragment contentTextFragment = toContentController.getTextFragment();
            ContentFragment contentBottomFragment = toContentController.getBottomFragment();
            if (onPushListener != null) {
                this.onPushListeners.add(onPushListener);
                onPushListener.onContentControllerReady(toContentController);
            }
            if (textPosition == null) {
                textPosition = TextPosition.BELOW_BODY;
            }
            if (contentTextFragment != null) {
                switch (textPosition) {
                    case ABOVE_BODY:
                        contentPaddingBottomResourceId = 0;
                        contentPaddingTopResourceId = C3354R.dimen.com_accountkit_vertical_spacer_small_height;
                        break;
                    case BELOW_BODY:
                        contentPaddingBottomResourceId = C3354R.dimen.com_accountkit_vertical_spacer_small_height;
                        contentPaddingTopResourceId = 0;
                        break;
                    default:
                        contentPaddingBottomResourceId = 0;
                        contentPaddingTopResourceId = 0;
                        break;
                }
                if (contentPaddingTopResourceId == 0) {
                    contentPaddingTop = 0;
                } else {
                    contentPaddingTop = activity.getResources().getDimensionPixelSize(contentPaddingTopResourceId);
                }
                if (contentPaddingBottomResourceId == 0) {
                    contentPaddingBottom = 0;
                } else {
                    contentPaddingBottom = activity.getResources().getDimensionPixelSize(contentPaddingBottomResourceId);
                }
                if (contentTextFragment instanceof TextContentFragment) {
                    TextContentFragment textContentFragment = (TextContentFragment) contentTextFragment;
                    textContentFragment.setContentPaddingTop(contentPaddingTop);
                    textContentFragment.setContentPaddingBottom(contentPaddingBottom);
                }
            }
            FragmentManager fm = activity.getFragmentManager();
            if (fromContentController != null) {
                activity.onContentControllerDismissed(fromContentController);
                if (fromContentController.isTransient()) {
                    fm.popBackStack();
                }
            }
            if (ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
                ensureNextButton(activity, toContentController);
            }
            FragmentTransaction transaction = fm.beginTransaction();
            replace(fm, transaction, C3354R.C3356id.com_accountkit_header_fragment, headerFragment);
            replace(fm, transaction, C3354R.C3356id.com_accountkit_content_top_fragment, contentTopFragment);
            replace(fm, transaction, C3354R.C3356id.com_accountkit_content_top_text_fragment, textPosition == TextPosition.ABOVE_BODY ? contentTextFragment : null);
            replace(fm, transaction, C3354R.C3356id.com_accountkit_content_center_fragment, contentCenterFragment);
            int i = C3354R.C3356id.com_accountkit_content_bottom_text_fragment;
            if (textPosition != TextPosition.BELOW_BODY) {
                contentTextFragment = null;
            }
            replace(fm, transaction, i, contentTextFragment);
            if (!ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
                replace(fm, transaction, C3354R.C3356id.com_accountkit_content_bottom_fragment, contentBottomFragment);
                replace(fm, transaction, C3354R.C3356id.com_accountkit_footer_fragment, footerFragment);
            }
            transaction.addToBackStack(null);
            ViewUtility.hideKeyboard(activity);
            transaction.commit();
            toContentController.onResume(activity);
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateContentController(AccountKitActivity activity) {
        ContentFragment topFragment = getContentFragment(activity, C3354R.C3356id.com_accountkit_content_top_fragment);
        if (topFragment != null) {
            ContentController contentController2 = ensureContentController(activity, topFragment.getLoginFlowState(), LoginFlowState.NONE, true);
            if (contentController2 != null) {
                this.contentController = contentController2;
                List<OnPopListener> onPopListeners2 = new ArrayList<>(this.onPopListeners);
                this.onPopListeners.clear();
                for (OnPopListener onPopListener : onPopListeners2) {
                    onPopListener.onContentPopped();
                }
                List<OnPushListener> onPushListeners2 = new ArrayList<>(this.onPushListeners);
                this.onPushListeners.clear();
                for (OnPushListener onPushListener : onPushListeners2) {
                    onPushListener.onContentPushed();
                }
            }
        }
    }

    private static void replace(FragmentManager fragmentManager, FragmentTransaction transaction, int containerViewId, Fragment fragment) {
        if (fragmentManager.findFragmentById(containerViewId) != fragment) {
            transaction.replace(containerViewId, fragment);
        }
    }

    private static Fragment remove(FragmentManager fragmentManager, FragmentTransaction transaction, int containerViewId) {
        Fragment fragment = fragmentManager.findFragmentById(containerViewId);
        if (fragment != null) {
            transaction.remove(fragment);
        }
        return fragment;
    }

    private ContentFragment getContentFragment(AccountKitActivity activity, int id) {
        Fragment fragment = activity.getFragmentManager().findFragmentById(id);
        if (!(fragment instanceof ContentFragment)) {
            return null;
        }
        return (ContentFragment) fragment;
    }

    private ContentController ensureContentController(AccountKitActivity activity, LoginFlowState loginFlowState, LoginFlowState returnState, boolean updateFragments) {
        ContentController contentController2;
        ContentController contentController3 = (ContentController) this.contentControllerMap.get(loginFlowState);
        if (contentController3 != null) {
            return contentController3;
        }
        switch (loginFlowState) {
            case PHONE_NUMBER_INPUT:
                contentController2 = new PhoneLoginContentController(this.configuration);
                break;
            case SENDING_CODE:
                contentController2 = new SendingCodeContentController(this.configuration);
                break;
            case SENT_CODE:
                switch (this.configuration.getLoginType()) {
                    case PHONE:
                        contentController2 = new PhoneSentCodeContentController(this.configuration);
                        break;
                    case EMAIL:
                        contentController2 = new EmailSentCodeContentController(this.configuration);
                        break;
                    default:
                        throw new RuntimeException("Unexpected login type: " + this.configuration.getLoginType().toString());
                }
            case ACCOUNT_VERIFIED:
                contentController2 = new AccountVerifiedContentController(this.configuration);
                break;
            case CONFIRM_ACCOUNT_VERIFIED:
                contentController2 = new ConfirmAccountVerifiedContentController(this.configuration);
                break;
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                contentController2 = new VerifyingCodeContentController(this.configuration);
                break;
            case CODE_INPUT:
                contentController2 = new ConfirmationCodeContentController(this.configuration);
                break;
            case VERIFYING_CODE:
                contentController2 = new VerifyingCodeContentController(this.configuration);
                break;
            case VERIFIED:
                contentController2 = new VerifiedCodeContentController(this.configuration);
                break;
            case ERROR:
                contentController2 = new ErrorContentController(returnState, this.configuration);
                break;
            case EMAIL_INPUT:
                contentController2 = new EmailLoginContentController(this.configuration);
                break;
            case EMAIL_VERIFY:
                contentController2 = new EmailVerifyContentController(this.configuration);
                break;
            case RESEND:
                contentController2 = new ResendContentController(this.configuration);
                break;
            default:
                return null;
        }
        if (updateFragments) {
            Fragment headerFragment = activity.getFragmentManager().findFragmentById(C3354R.C3356id.com_accountkit_header_fragment);
            if (headerFragment instanceof TitleFragment) {
                contentController2.setHeaderFragment((TitleFragment) headerFragment);
            }
            contentController2.setTopFragment(getContentFragment(activity, C3354R.C3356id.com_accountkit_content_top_fragment));
            contentController2.setCenterFragment(getContentFragment(activity, C3354R.C3356id.com_accountkit_content_center_fragment));
            contentController2.setBottomFragment(getContentFragment(activity, C3354R.C3356id.com_accountkit_content_bottom_fragment));
            Fragment footerFragment = activity.getFragmentManager().findFragmentById(C3354R.C3356id.com_accountkit_footer_fragment);
            if (footerFragment instanceof TitleFragment) {
                contentController2.setFooterFragment((TitleFragment) footerFragment);
            }
            contentController2.onResume(activity);
        }
        this.contentControllerMap.put(loginFlowState, contentController2);
        return contentController2;
    }
}
