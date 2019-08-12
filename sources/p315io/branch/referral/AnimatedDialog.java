package p315io.branch.referral;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/* renamed from: io.branch.referral.AnimatedDialog */
public class AnimatedDialog extends Dialog {
    private boolean isClosing_ = false;
    private final boolean isFullWidthStyle_;

    public AnimatedDialog(Context context, boolean isFullWidthStyle) {
        super(context);
        this.isFullWidthStyle_ = isFullWidthStyle;
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.isFullWidthStyle_) {
            LayoutParams params = getWindow().getAttributes();
            params.width = -1;
            getWindow().setAttributes(params);
        }
    }

    public void show() {
        slideOpen();
    }

    public void cancel() {
        slideClose();
    }

    public void setContentView(int layoutResID) {
        setDialogWindowAttributes();
        super.setContentView(layoutResID);
    }

    private void init(Context context) {
        setDialogWindowAttributes();
        setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == 4) {
                    AnimatedDialog.this.slideClose();
                }
                return true;
            }
        });
    }

    public void setDialogWindowAttributes() {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().addFlags(2);
        getWindow().addFlags(1024);
        LayoutParams lp = new LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = -1;
        lp.height = -1;
        lp.gravity = 80;
        lp.dimAmount = 0.8f;
        getWindow().setAttributes(lp);
        getWindow().setWindowAnimations(17432578);
        setCanceledOnTouchOutside(true);
    }

    private void slideOpen() {
        TranslateAnimation slideUp = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        slideUp.setDuration(500);
        slideUp.setInterpolator(new AccelerateInterpolator());
        ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(slideUp);
        super.show();
    }

    /* access modifiers changed from: private */
    public void slideClose() {
        if (!this.isClosing_) {
            this.isClosing_ = true;
            TranslateAnimation slideDown = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            slideDown.setDuration(500);
            slideDown.setInterpolator(new DecelerateInterpolator());
            ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(slideDown);
            slideDown.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    AnimatedDialog.this.dismiss();
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }
}
