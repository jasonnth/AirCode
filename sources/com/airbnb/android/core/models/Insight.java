package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.generated.GenInsight;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Insight extends GenInsight {
    public static final Creator<Insight> CREATOR = new Creator<Insight>() {
        public Insight[] newArray(int size) {
            return new Insight[size];
        }

        public Insight createFromParcel(Parcel source) {
            Insight object = new Insight();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum ButtonAction {
        SendRequestAction,
        UndoRequestAction,
        OpenScreenAction,
        AcknowledgeAction,
        NothingAction,
        RedirectAndDismissAction
    }

    public enum ConversionType {
        SetWeeklyDiscount(1, InsightButtonBehavior.Type1, C0716R.string.insight_go_to_settings),
        SetPricingTipForDateRange(2),
        UnblockNightsForDateRange(3, InsightButtonBehavior.Type1, C0716R.string.insight_show_calendar),
        SetSmartPricingMinPrice(4, InsightButtonBehavior.Type1, C0716R.string.insight_go_to_settings),
        SetSmartPromotion(5, InsightButtonBehavior.Type1, C0716R.string.insight_show_calendar),
        SetBasePrice(6, InsightButtonBehavior.Type1, C0716R.string.insight_go_to_settings),
        TurnOnSmartPricing(7),
        TurnOnInstantBooking(8),
        AddDetailedDescription(9, InsightButtonBehavior.Type2),
        AddBedDetails(10),
        AddCoverPhoto(11),
        AddPhoto(12),
        Completion(13),
        CompletionWithNextListing(14),
        SetPricingTipForMonth(15, InsightButtonBehavior.Type1, C0716R.string.action_card_preview_price_tips),
        SetCleaningFee(16, InsightButtonBehavior.Type2),
        Acknowledge(17, InsightButtonBehavior.Type3),
        OpenListingDescription(18),
        OpenListingPhotos(19, InsightButtonBehavior.Type4),
        OpenListingAmenities(20, InsightButtonBehavior.Type4);
        
        public final InsightButtonBehavior buttonBehavior;
        public final int secondaryButtonStringRes;
        public final int serverKey;

        private ConversionType(int serverKey2, InsightButtonBehavior buttonBehavior2, int secondaryButtonStringRes2) {
            this.serverKey = serverKey2;
            this.buttonBehavior = buttonBehavior2;
            this.secondaryButtonStringRes = secondaryButtonStringRes2;
        }

        private ConversionType(int serverKey2) {
            this(r7, r8, serverKey2, null, 0);
        }

        private ConversionType(int serverKey2, InsightButtonBehavior buttonBehavior2) {
            this(r7, r8, serverKey2, buttonBehavior2, 0);
        }

        public static ConversionType fromServerKey(int key) {
            return (ConversionType) FluentIterable.m1283of(values()).firstMatch(Insight$ConversionType$$Lambda$1.lambdaFactory$(key)).orNull();
        }
    }

    public enum GraphicType {
        Empty(1),
        BoostBar(2),
        SegmentedBoostBar(3),
        DemandCurve(4);
        
        public final int serverKey;

        private GraphicType(int serverKey2) {
            this.serverKey = serverKey2;
        }

        public static GraphicType fromServerKey(int key) {
            return (GraphicType) FluentIterable.m1283of(values()).firstMatch(Insight$GraphicType$$Lambda$1.lambdaFactory$(key)).orNull();
        }
    }

    public enum InsightButtonBehavior {
        Type1(ButtonAction.SendRequestAction, ButtonAction.OpenScreenAction, ButtonAction.UndoRequestAction),
        Type2(ButtonAction.OpenScreenAction, ButtonAction.NothingAction, ButtonAction.UndoRequestAction),
        Type3(ButtonAction.AcknowledgeAction, ButtonAction.NothingAction, ButtonAction.NothingAction),
        Type4(ButtonAction.RedirectAndDismissAction, ButtonAction.NothingAction, ButtonAction.NothingAction);
        
        public ButtonAction primaryBefore;
        public ButtonAction secondaryAfter;
        public ButtonAction secondaryBefore;

        private InsightButtonBehavior(ButtonAction primaryBefore2, ButtonAction secondaryBefore2, ButtonAction secondaryAfter2) {
            this.primaryBefore = primaryBefore2;
            this.secondaryBefore = secondaryBefore2;
            this.secondaryAfter = secondaryAfter2;
        }
    }

    @JsonProperty("position")
    public void setBackendPosition(int position) {
        this.mPosition = -1;
        this.mBackendPosition = position;
    }

    @JsonProperty("story_conversion_type")
    public void setStoryConversionType(int serverKey) {
        this.mStoryConversionType = ConversionType.fromServerKey(serverKey);
    }

    public void setStoryConversionType(ConversionType storyConversionType) {
        this.mStoryConversionType = storyConversionType;
    }

    @JsonProperty("story_graphic_type")
    public void setStoryGraphicType(int serverKey) {
        this.mStoryGraphicType = GraphicType.fromServerKey(serverKey);
    }

    public void setStoryGraphicType(GraphicType storyGraphicType) {
        this.mStoryGraphicType = storyGraphicType;
    }

    public static List<ConversionType> getValidInsightConversionTypes() {
        List<ConversionType> list = new ArrayList<>(Arrays.asList(new ConversionType[]{ConversionType.SetWeeklyDiscount, ConversionType.UnblockNightsForDateRange, ConversionType.SetSmartPricingMinPrice}));
        if (FeatureToggles.shouldEnableInsightsCardToOpenListingPhotosAndAmenities()) {
            list.add(ConversionType.OpenListingPhotos);
            list.add(ConversionType.OpenListingAmenities);
        }
        if (PricingFeatureToggles.showNewInsightCards()) {
            list.add(ConversionType.SetPricingTipForMonth);
            list.add(ConversionType.SetSmartPromotion);
            list.add(ConversionType.SetBasePrice);
        }
        return ImmutableList.copyOf((Collection<? extends E>) list);
    }

    public double getSmartPromotionPriceFactor() {
        if (getStoryConversionType() == ConversionType.SetSmartPromotion) {
            return ((double) (100 - getConversionPayload().getIntegerValue())) / 100.0d;
        }
        return 0.0d;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insight that = (Insight) o;
        if (this.mPosition != that.mPosition || this.mGlobalPosition != that.mGlobalPosition || this.mBackendPosition != that.mBackendPosition || this.mStoryType != that.mStoryType || this.mListingId != that.mListingId || this.mStoryGraphicType != that.mStoryGraphicType) {
            return false;
        }
        if (this.mStoryId != null) {
            if (!this.mStoryId.equals(that.mStoryId)) {
                return false;
            }
        } else if (that.mStoryId != null) {
            return false;
        }
        if (this.mOriginalRequestId != null) {
            z = this.mOriginalRequestId.equals(that.mOriginalRequestId);
        } else if (that.mOriginalRequestId != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.mStoryGraphicType != null) {
            result = this.mStoryGraphicType.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.mStoryId != null) {
            i = this.mStoryId.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.mOriginalRequestId != null) {
            i2 = this.mOriginalRequestId.hashCode();
        }
        return ((((((((((i4 + i2) * 31) + this.mPosition) * 31) + this.mGlobalPosition) * 31) + this.mBackendPosition) * 31) + this.mStoryType) * 31) + ((int) (this.mListingId ^ (this.mListingId >>> 32)));
    }
}
