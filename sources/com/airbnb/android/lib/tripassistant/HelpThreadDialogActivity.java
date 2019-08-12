package com.airbnb.android.lib.tripassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import p030in.uncod.android.bypass.Bypass;

public class HelpThreadDialogActivity extends AirActivity {
    private static final String EXTRA_DIALOG_CONTENT = "extra_dialog_content";
    Bypass bypass;
    @BindView
    TextView text;
    @BindView
    AirToolbar toolbar;

    public static Intent intent(Context context, String dialogContent) {
        return new Intent(context, HelpThreadDialogActivity.class).putExtra(EXTRA_DIALOG_CONTENT, dialogContent);
    }

    private String formatMarkup(String content) {
        String markedContent = content.replace("\n", "\n\n");
        if (markedContent.indexOf("---") == -1 || markedContent.indexOf("](tel:") == -1) {
            return markedContent;
        }
        return markedContent.replace("|---|---|", "").replace("|", " ");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_help_thread_dialog);
        ButterKnife.bind((Activity) this);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setToolbar(this.toolbar);
        this.text.setText(this.bypass.markdownToSpannable(formatMarkup(getIntent().getStringExtra(EXTRA_DIALOG_CONTENT))));
        this.text.setMovementMethod(LinkMovementMethod.getInstance());
        overridePendingTransition(C0880R.anim.enter_bottom, C0880R.anim.stay);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C0880R.anim.stay, C0880R.anim.exit_bottom);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
