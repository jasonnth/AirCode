package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.MicroRow */
public final class MicroRow extends BaseDividerComponent {
    @BindView
    AirTextView textView;

    public MicroRow(Context context) {
        super(context);
    }

    public MicroRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MicroRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_micro_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setText(CharSequence string) {
        ViewLibUtils.setText((TextView) this.textView, string, true);
    }

    public void setText(int stringRes) {
        setText((CharSequence) getResources().getString(stringRes));
    }

    public static void mock(MicroRow row) {
        row.setText((CharSequence) "Micro row");
        row.setOnClickListener(MicroRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWrap(MicroRow row) {
        row.setText((CharSequence) "Micro row with text long enough to wrap to multiple lines");
        row.setOnClickListener(MicroRow$$Lambda$2.lambdaFactory$());
    }

    public static void mockRichText(MicroRow row) {
        row.setText(new AirTextBuilder(row.getContext()).append("Micro row supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", MicroRow$$Lambda$3.lambdaFactory$()).build());
    }
}
