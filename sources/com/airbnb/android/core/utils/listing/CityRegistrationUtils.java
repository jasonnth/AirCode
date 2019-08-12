package com.airbnb.android.core.utils.listing;

import android.content.Context;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import java.util.List;

public class CityRegistrationUtils {
    public static String getStringFromAirAddress(AirAddress address) {
        if (address == null) {
            return "";
        }
        Parcel p = Parcel.obtain();
        p.writeValue(address);
        return Base64.encodeToString(p.marshall(), 0);
    }

    public static AirAddress getAirAddressFromString(String str) {
        Parcel p = Parcel.obtain();
        byte[] data = Base64.decode(str, 0);
        p.unmarshall(data, 0, data.length);
        p.setDataPosition(0);
        return (AirAddress) p.readValue(AirAddress.class.getClassLoader());
    }

    public static void openHelpCenterArticle(Context context, int articleId) {
        context.startActivity(HelpCenterIntents.intentForHelpCenterArticle(context, articleId).toIntent());
    }

    public static void openCityRegistrationUrl(Context context, String url) {
        context.startActivity(WebViewIntentBuilder.newBuilder(context, url).toIntent());
    }

    public static void addHelpLinkToMarquee(DocumentMarqueeEpoxyModel_ marquee, String helpText, int articleId) {
        if (!TextUtils.isEmpty(helpText) && articleId > 0) {
            marquee.linkText(helpText).linkClickListener(CityRegistrationUtils$$Lambda$1.lambdaFactory$(articleId));
        }
    }

    public static void addLinkToMarquee(DocumentMarqueeEpoxyModel_ marquee, String helpText, String helpUrl) {
        if (!TextUtils.isEmpty(helpText) && !TextUtils.isEmpty(helpUrl)) {
            marquee.linkText(helpText).linkClickListener(CityRegistrationUtils$$Lambda$2.lambdaFactory$(helpUrl));
        }
    }

    public static void addHelpLinkToMarquee(DocumentMarqueeEpoxyModel_ marquee, ListingRegistrationHelpLink helpLink) {
        if (helpLink == null) {
            return;
        }
        if (helpLink.getArticleId() > 0) {
            addHelpLinkToMarquee(marquee, helpLink.getLinkText(), helpLink.getArticleId());
        } else {
            addLinkToMarquee(marquee, helpLink.getLinkText(), helpLink.getUrl());
        }
    }

    public static void addHelpLinkToMarquee(DocumentMarqueeEpoxyModel_ marquee, ListingRegistrationContent content) {
        int articleId = content.getHelpCenterId();
        String helpText = content.getHelpLinkText();
        if (!TextUtils.isEmpty(helpText)) {
            addHelpLinkToMarquee(marquee, helpText, articleId);
        } else if (articleId > 0) {
            marquee.linkRes(C0716R.string.learn_more).linkClickListener(CityRegistrationUtils$$Lambda$3.lambdaFactory$(articleId));
        }
    }

    public static String textFromLines(List<String> lines, boolean useDoubleLineBreaker) {
        if (lines == null || lines.isEmpty()) {
            return null;
        }
        return TextUtils.join(useDoubleLineBreaker ? "\n\n" : "\n", lines);
    }

    public static boolean equals(String one, String two) {
        return (one == null && two == null) || (one != null && one.equals(two));
    }
}
