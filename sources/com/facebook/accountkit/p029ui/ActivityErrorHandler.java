package com.facebook.accountkit.p029ui;

/* renamed from: com.facebook.accountkit.ui.ActivityErrorHandler */
final class ActivityErrorHandler {
    private ActivityErrorHandler() {
    }

    static void onErrorRestart(AccountKitActivity activity, LoginFlowState returnState) {
        ContentController contentController = activity.getContentController();
        if (contentController != null && (contentController instanceof ErrorContentController)) {
            activity.onContentControllerDismissed(contentController);
        }
        activity.popBackStack(returnState, null);
    }
}
