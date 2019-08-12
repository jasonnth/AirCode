package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.utils.StoryUtils;
import com.airbnb.android.core.models.StoryElement;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.AirTextView;

public abstract class ArticleTextEpoxyModel extends AirEpoxyModel<AirTextView> {
    private final CharSequence text;

    public ArticleTextEpoxyModel(StoryElement element) {
        this.text = element.getText();
    }

    public void bind(AirTextView textView) {
        super.bind(textView);
        textView.setText(StoryUtils.addExtraNewLineToBodyTextForRendering(this.text));
    }
}
