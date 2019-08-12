package com.airbnb.android.core.views;

import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

public class AirEditTextPageView_ViewBinding implements Unbinder {
    private AirEditTextPageView target;
    private View view2131755449;

    public AirEditTextPageView_ViewBinding(AirEditTextPageView target2) {
        this(target2, target2);
    }

    public AirEditTextPageView_ViewBinding(final AirEditTextPageView target2, View source) {
        this.target = target2;
        target2.titleView = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.marquee, "field 'titleView'", DocumentMarquee.class);
        target2.textView = (AirEditTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.edit_text, "field 'textView'", AirEditTextView.class);
        target2.characterCountView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.char_count, "field 'characterCountView'", AirTextView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.space_click_target, "method 'requestFocusAndKeyboard'");
        this.view2131755449 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.requestFocusAndKeyboard();
            }
        });
        Resources res = source.getContext().getResources();
        target2.withCountBottomSpacerPx = res.getDimensionPixelSize(C0716R.dimen.text_edit_page_with_count_bottom_spacer);
        target2.withoutCountBottomSpacerPx = res.getDimensionPixelSize(C0716R.dimen.text_edit_page_without_count_bottom_spacer);
        target2.bottomBarPaddingPx = res.getDimensionPixelSize(C0716R.dimen.n2_bottom_button_bar_content_height);
    }

    public void unbind() {
        AirEditTextPageView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.textView = null;
        target2.characterCountView = null;
        this.view2131755449.setOnClickListener(null);
        this.view2131755449 = null;
    }
}
