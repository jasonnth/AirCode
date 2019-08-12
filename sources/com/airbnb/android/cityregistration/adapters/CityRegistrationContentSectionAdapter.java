package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.ListingRegistrationContentObject;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.BulletTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.CityRegistrationCheckmarkRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NumberedSimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.p027n2.utils.AirTextBuilder;
import java.util.List;

public abstract class CityRegistrationContentSectionAdapter extends AirEpoxyAdapter {
    /* access modifiers changed from: protected */
    public void addListingRegistrationContentObjects(List<ListingRegistrationContentObject> contentList, Context context) {
        for (ListingRegistrationContentObject content : contentList) {
            switch (content.getStyle()) {
                case Paragraph:
                    for (String text : content.getTexts()) {
                        addModel(new SimpleTextRowEpoxyModel_().text(text).smallPadding());
                    }
                    break;
                case Bullet:
                    for (String text2 : content.getTexts()) {
                        addModel(new BulletTextRowEpoxyModel_().text(text2));
                    }
                    break;
                case Header:
                    for (String text3 : content.getTexts()) {
                        addModel(new SimpleTextRowEpoxyModel_().text(text3).plusAndTopPadding());
                    }
                    break;
                case NumberList:
                    List<String> texts = content.getTexts();
                    for (int i = 0; i < texts.size(); i++) {
                        addModel(new NumberedSimpleTextRowEpoxyModel_().content((CharSequence) texts.get(i)).stepNumber(i + 1).tightPadding());
                    }
                    break;
                case Checkmark:
                    CityRegistrationCheckmarkRowEpoxyModel_ checkmark = new CityRegistrationCheckmarkRowEpoxyModel_().title(content.getTitle()).iconDrawableRes(C5630R.C5631drawable.n2_icon_circle_checkmark_babu);
                    checkmark.subtitle(buildContentText(content, Boolean.valueOf(true), context)).withTinyPaddingLayout();
                    addModel(checkmark);
                    break;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unhandled style " + content.getStyle()));
                    break;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addLinkToText(AirTextBuilder builder, ListingRegistrationHelpLink link, Context context) {
        if (link != null) {
            builder.append(" ").appendLink(link.getLinkText(), CityRegistrationContentSectionAdapter$$Lambda$1.lambdaFactory$(link, context));
        }
    }

    static /* synthetic */ void lambda$addLinkToText$0(ListingRegistrationHelpLink link, Context context, View v, CharSequence linkText) {
        if (link.getArticleId() > 0) {
            CityRegistrationUtils.openHelpCenterArticle(context, link.getArticleId());
        } else {
            CityRegistrationUtils.openCityRegistrationUrl(context, link.getUrl());
        }
    }

    /* access modifiers changed from: protected */
    public CharSequence buildContentText(ListingRegistrationContentObject content, Boolean includeLink, Context context) {
        AirTextBuilder textBuilder = new AirTextBuilder(context);
        for (String text : content.getTexts()) {
            textBuilder.append(text);
        }
        if (includeLink.booleanValue()) {
            addLinkToText(textBuilder, content.getLink(), context);
        }
        return textBuilder.build();
    }
}
