package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.TeamComponentTemplateCopyMe */
public class TeamComponentTemplateCopyMe extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public TeamComponentTemplateCopyMe(Context context) {
        super(context);
        init(context, null);
    }

    public TeamComponentTemplateCopyMe(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TeamComponentTemplateCopyMe(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.n2_team_component_template_copy_me, this);
        ButterKnife.bind((View) this);
        setupAttributes(context, attrs);
    }

    private void setupAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_ScratchTemplateCopyMe);
        setTitle((CharSequence) a.getString(R.styleable.n2_ScratchTemplateCopyMe_n2_titleText));
        setOptionalSubtitleText((CharSequence) a.getString(R.styleable.n2_ScratchTemplateCopyMe_n2_subtitleText));
        showDivider(a.getBoolean(R.styleable.n2_ScratchTemplateCopyMe_n2_showDivider, true));
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setOptionalSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitle, !TextUtils.isEmpty(text));
        this.subtitle.setText(text);
    }

    public void setOptionalSubtitleText(int stringId) {
        setOptionalSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(TeamComponentTemplateCopyMe view) {
        view.setTitle((CharSequence) "Title");
    }
}
