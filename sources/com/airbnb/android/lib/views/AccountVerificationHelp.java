package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

public class AccountVerificationHelp extends LinearLayout {
    @BindView
    TextView mContactTextView;

    public AccountVerificationHelp(Context context) {
        super(context);
        init();
    }

    public AccountVerificationHelp(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountVerificationHelp(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOrientation(1);
        Context context = getContext();
        LayoutInflater.from(context).inflate(C0880R.layout.account_verification_help, this);
        ButterKnife.bind(this, this);
        String email = context.getString(C0880R.string.account_verification_contact_email);
        ClickableLinkUtils.setupClickableTextView(this.mContactTextView, context.getString(C0880R.string.account_verification_contact, new Object[]{email}), C0880R.color.canonical_press_darken, AccountVerificationHelp$$Lambda$1.lambdaFactory$(email, context));
    }

    static /* synthetic */ void lambda$init$0(String email, Context context, int linkIndex) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/html");
        intent.putExtra("android.intent.extra.EMAIL", email);
        context.startActivity(Intent.createChooser(intent, context.getString(C0880R.string.send_mail)));
    }
}
