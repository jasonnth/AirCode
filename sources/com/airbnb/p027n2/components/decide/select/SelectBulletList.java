package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.decide.select.SelectBulletList */
public class SelectBulletList extends BaseDividerComponent {
    @BindView
    AirTextView textView;

    public SelectBulletList(Context context) {
        super(context);
    }

    public SelectBulletList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectBulletList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_select_bullet_list;
    }

    public void setBulletPoints(List<String> bulletPoints) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (CharSequence bullet : bulletPoints) {
            builder.append(TextUtil.makeColored(getContext(), R.color.n2_rausch_dark, "• "));
            builder.append(bullet);
            builder.append("\n");
            builder.append("\n");
        }
        this.textView.setText(builder);
    }

    public static void mock(SelectBulletList bulletList) {
        List<String> bulletPoints = new ArrayList<>();
        bulletPoints.add("Professionally inspected");
        bulletPoints.add("Easy self check-in");
        bulletPoints.add("Standard set of amenitites");
        bulletPoints.add("Priority Airbnb support");
        bulletList.setBulletPoints(bulletPoints);
    }
}
