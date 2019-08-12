package p315io.branch.referral.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import p315io.branch.referral.SharingHelper.SHARE_WITH;

/* renamed from: io.branch.referral.util.ShareSheetStyle */
public class ShareSheetStyle {
    private String copyURlText_;
    private Drawable copyUrlIcon_;
    private String defaultURL_;
    private int dividerHeight_;
    private List<String> excludeFromShareSheet;
    private List<String> includeInShareSheet;
    private final String messageBody_;
    private final String messageTitle_;
    private Drawable moreOptionIcon_;
    private String moreOptionText_;
    private final ArrayList<SHARE_WITH> preferredOptions_;
    private boolean setFullWidthStyle_;
    private View sharingTitleView_;
    private String sharingTitle_;
    private int styleResourceID_;
    private String urlCopiedMessage_;

    public List<String> getExcludedFromShareSheet() {
        return this.excludeFromShareSheet;
    }

    public List<String> getIncludedInShareSheet() {
        return this.includeInShareSheet;
    }

    public ArrayList<SHARE_WITH> getPreferredOptions() {
        return this.preferredOptions_;
    }

    public Drawable getCopyUrlIcon() {
        return this.copyUrlIcon_;
    }

    public Drawable getMoreOptionIcon() {
        return this.moreOptionIcon_;
    }

    public String getMessageBody() {
        return this.messageBody_;
    }

    public String getMessageTitle() {
        return this.messageTitle_;
    }

    public String getCopyURlText() {
        return this.copyURlText_;
    }

    public String getDefaultURL() {
        return this.defaultURL_;
    }

    public String getMoreOptionText() {
        return this.moreOptionText_;
    }

    public String getUrlCopiedMessage() {
        return this.urlCopiedMessage_;
    }

    public int getDividerHeight() {
        return this.dividerHeight_;
    }

    public String getSharingTitle() {
        return this.sharingTitle_;
    }

    public View getSharingTitleView() {
        return this.sharingTitleView_;
    }

    public boolean getIsFullWidthStyle() {
        return this.setFullWidthStyle_;
    }

    public int getStyleResourceID() {
        return this.styleResourceID_;
    }
}
