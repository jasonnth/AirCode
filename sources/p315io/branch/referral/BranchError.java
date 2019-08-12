package p315io.branch.referral;

import com.airbnb.android.utils.AirbnbConstants;

/* renamed from: io.branch.referral.BranchError */
public class BranchError {
    int errorCode_ = -113;
    String errorMessage_ = "";

    public String getMessage() {
        return this.errorMessage_;
    }

    public int getErrorCode() {
        return this.errorCode_;
    }

    public String toString() {
        return getMessage();
    }

    public BranchError(String failMsg, int statusCode) {
        this.errorMessage_ = failMsg + initErrorCodeAndGetLocalisedMessage(statusCode);
    }

    private String initErrorCodeAndGetLocalisedMessage(int statusCode) {
        if (statusCode == -113) {
            this.errorCode_ = -113;
            return " Branch API Error: poor network connectivity. Please try again later.";
        } else if (statusCode == -114) {
            this.errorCode_ = -114;
            return " Branch API Error: Please enter your branch_key in your project's manifest file first.";
        } else if (statusCode == -104) {
            this.errorCode_ = -104;
            return " Did you forget to call init? Make sure you init the session before making Branch calls.";
        } else if (statusCode == -101) {
            this.errorCode_ = -101;
            return " Unable to initialize Branch. Check network connectivity or that your branch key is valid.";
        } else if (statusCode == -102) {
            this.errorCode_ = -102;
            return " Please add 'android.permission.INTERNET' in your applications manifest file.";
        } else if (statusCode == -105) {
            this.errorCode_ = -105;
            return " Unable to create a URL with that alias. If you want to reuse the alias, make sure to submit the same properties for all arguments and that the user is the same owner.";
        } else if (statusCode == -106) {
            this.errorCode_ = -106;
            return " That Branch referral code is already in use.";
        } else if (statusCode == -107) {
            this.errorCode_ = -107;
            return " Unable to redeem rewards. Please make sure you have credits available to redeem.";
        } else if (statusCode == -108) {
            this.errorCode_ = -108;
            return "BranchApp class can be used only with API level 14 or above. Please make sure your minimum API level supported is 14. If you wish to use API level below 14 consider calling getInstance(Context) instead.";
        } else if (statusCode == -109) {
            this.errorCode_ = -109;
            return "Branch instance is not created. Make  sure your Application class is an instance of BranchLikedApp.";
        } else if (statusCode == -110) {
            this.errorCode_ = AirbnbConstants.SUPERHERO_COUPON_ID;
            return " Unable create share options. Couldn't find applications on device to share the link.";
        } else if (statusCode == -111) {
            this.errorCode_ = AirbnbConstants.SUPERHERO_TEST_ID;
            return " Request to Branch server timed out. Please check your internet connectivity";
        } else if (statusCode >= 500 || statusCode == -112) {
            this.errorCode_ = -112;
            return " Unable to reach the Branch servers, please try again shortly.";
        } else if (statusCode == 409 || statusCode == -115) {
            this.errorCode_ = -115;
            return " A resource with this identifier already exists.";
        } else if (statusCode >= 400 || statusCode == -116) {
            this.errorCode_ = -116;
            return " The request was invalid.";
        } else {
            this.errorCode_ = -113;
            return " Check network connectivity and that you properly initialized.";
        }
    }
}
