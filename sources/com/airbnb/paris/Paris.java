package com.airbnb.paris;

import android.view.View;
import com.airbnb.p027n2.components.BaseComponent;
import com.airbnb.p027n2.components.BaseComponentStyleApplier;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.components.BaseDividerComponentStyleApplier;
import com.airbnb.p027n2.components.BasicRow;
import com.airbnb.p027n2.components.BasicRowStyleApplier;
import com.airbnb.p027n2.components.CityRegistrationCheckmarkRow;
import com.airbnb.p027n2.components.CityRegistrationCheckmarkRowStyleApplier;
import com.airbnb.p027n2.components.CityRegistrationIconActionRow;
import com.airbnb.p027n2.components.CityRegistrationIconActionRowStyleApplier;
import com.airbnb.p027n2.components.EditorialMarquee;
import com.airbnb.p027n2.components.EditorialMarqueeStyleApplier;
import com.airbnb.p027n2.components.EmptyStateCard;
import com.airbnb.p027n2.components.EmptyStateCardStyleApplier;
import com.airbnb.p027n2.components.FixedActionFooterWithText;
import com.airbnb.p027n2.components.FixedActionFooterWithTextStyleApplier;
import com.airbnb.p027n2.components.FlightTimeRow;
import com.airbnb.p027n2.components.FlightTimeRowStyleApplier;
import com.airbnb.p027n2.components.HaloImageTextRow;
import com.airbnb.p027n2.components.HaloImageTextRowStyleApplier;
import com.airbnb.p027n2.components.IconRow;
import com.airbnb.p027n2.components.IconRowStyleApplier;
import com.airbnb.p027n2.components.ImageRow;
import com.airbnb.p027n2.components.ImageRowStyleApplier;
import com.airbnb.p027n2.components.InfiniteDotIndicator;
import com.airbnb.p027n2.components.InfiniteDotIndicatorStyleApplier;
import com.airbnb.p027n2.components.InfoRow;
import com.airbnb.p027n2.components.InfoRowStyleApplier;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.InlineInputRowStyleApplier;
import com.airbnb.p027n2.components.InputSuggestionActionRow;
import com.airbnb.p027n2.components.InputSuggestionActionRowStyleApplier;
import com.airbnb.p027n2.components.InquiryCard;
import com.airbnb.p027n2.components.InquiryCardStyleApplier;
import com.airbnb.p027n2.components.Interstitial;
import com.airbnb.p027n2.components.InterstitialStyleApplier;
import com.airbnb.p027n2.components.LeftAlignedImageRow;
import com.airbnb.p027n2.components.LeftAlignedImageRowStyleApplier;
import com.airbnb.p027n2.components.LeftIconRow;
import com.airbnb.p027n2.components.LeftIconRowStyleApplier;
import com.airbnb.p027n2.components.ListingInfoRow;
import com.airbnb.p027n2.components.ListingInfoRowStyleApplier;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.LoadingViewStyleApplier;
import com.airbnb.p027n2.components.MicroRow;
import com.airbnb.p027n2.components.MicroRowStyleApplier;
import com.airbnb.p027n2.components.MicroSectionHeader;
import com.airbnb.p027n2.components.MicroSectionHeaderStyleApplier;
import com.airbnb.p027n2.components.MultiLineSplitRow;
import com.airbnb.p027n2.components.MultiLineSplitRowStyleApplier;
import com.airbnb.p027n2.components.NuxCoverCard;
import com.airbnb.p027n2.components.NuxCoverCardStyleApplier;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.RefreshLoaderStyleApplier;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.components.SectionHeaderStyleApplier;
import com.airbnb.p027n2.components.SummaryInterstitial;
import com.airbnb.p027n2.components.SummaryInterstitialStyleApplier;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.SwitchRowStyleApplier;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.components.TextRowStyleApplier;
import com.airbnb.p027n2.components.ThreadPreviewRowWithLabel;
import com.airbnb.p027n2.components.ThreadPreviewRowWithLabelStyleApplier;
import com.airbnb.p027n2.components.ValueRow;
import com.airbnb.p027n2.components.ValueRowStyleApplier;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooterStyleApplier;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooterStyleApplier;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooterStyleApplier;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooterStyleApplier;
import com.airbnb.p027n2.primitives.AirAutoCompleteTextView;
import com.airbnb.p027n2.primitives.AirAutoCompleteTextViewStyleApplier;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButtonStyleApplier;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirEditTextViewStyleApplier;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirSwitchStyleApplier;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirTextViewStyleApplier;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.primitives.ExpandableTextViewStyleApplier;

public final class Paris {
    public static ViewStyleApplier style(View view) {
        return new ViewStyleApplier(view);
    }

    public static AirTextViewStyleApplier style(AirTextView view) {
        return new AirTextViewStyleApplier(view);
    }

    public static AirEditTextViewStyleApplier style(AirEditTextView view) {
        return new AirEditTextViewStyleApplier(view);
    }

    public static AirSwitchStyleApplier style(AirSwitch view) {
        return new AirSwitchStyleApplier(view);
    }

    public static ExpandableTextViewStyleApplier style(ExpandableTextView view) {
        return new ExpandableTextViewStyleApplier(view);
    }

    public static AirButtonStyleApplier style(AirButton view) {
        return new AirButtonStyleApplier(view);
    }

    public static AirAutoCompleteTextViewStyleApplier style(AirAutoCompleteTextView view) {
        return new AirAutoCompleteTextViewStyleApplier(view);
    }

    public static SwitchRowStyleApplier style(SwitchRow view) {
        return new SwitchRowStyleApplier(view);
    }

    public static BaseDividerComponentStyleApplier style(BaseDividerComponent view) {
        return new BaseDividerComponentStyleApplier(view);
    }

    public static ImageRowStyleApplier style(ImageRow view) {
        return new ImageRowStyleApplier(view);
    }

    public static BasicRowStyleApplier style(BasicRow view) {
        return new BasicRowStyleApplier(view);
    }

    public static FixedFlowActionFooterStyleApplier style(FixedFlowActionFooter view) {
        return new FixedFlowActionFooterStyleApplier(view);
    }

    public static FixedDualActionFooterStyleApplier style(FixedDualActionFooter view) {
        return new FixedDualActionFooterStyleApplier(view);
    }

    public static FixedActionFooterStyleApplier style(FixedActionFooter view) {
        return new FixedActionFooterStyleApplier(view);
    }

    public static FixedFlowActionAdvanceFooterStyleApplier style(FixedFlowActionAdvanceFooter view) {
        return new FixedFlowActionAdvanceFooterStyleApplier(view);
    }

    public static IconRowStyleApplier style(IconRow view) {
        return new IconRowStyleApplier(view);
    }

    public static TextRowStyleApplier style(TextRow view) {
        return new TextRowStyleApplier(view);
    }

    public static MicroSectionHeaderStyleApplier style(MicroSectionHeader view) {
        return new MicroSectionHeaderStyleApplier(view);
    }

    public static InputSuggestionActionRowStyleApplier style(InputSuggestionActionRow view) {
        return new InputSuggestionActionRowStyleApplier(view);
    }

    public static RefreshLoaderStyleApplier style(RefreshLoader view) {
        return new RefreshLoaderStyleApplier(view);
    }

    public static BaseComponentStyleApplier style(BaseComponent view) {
        return new BaseComponentStyleApplier(view);
    }

    public static InlineInputRowStyleApplier style(InlineInputRow view) {
        return new InlineInputRowStyleApplier(view);
    }

    public static InfoRowStyleApplier style(InfoRow view) {
        return new InfoRowStyleApplier(view);
    }

    public static InterstitialStyleApplier style(Interstitial view) {
        return new InterstitialStyleApplier(view);
    }

    public static LoadingViewStyleApplier style(LoadingView view) {
        return new LoadingViewStyleApplier(view);
    }

    public static MicroRowStyleApplier style(MicroRow view) {
        return new MicroRowStyleApplier(view);
    }

    public static EditorialMarqueeStyleApplier style(EditorialMarquee view) {
        return new EditorialMarqueeStyleApplier(view);
    }

    public static SectionHeaderStyleApplier style(SectionHeader view) {
        return new SectionHeaderStyleApplier(view);
    }

    public static ValueRowStyleApplier style(ValueRow view) {
        return new ValueRowStyleApplier(view);
    }

    public static SummaryInterstitialStyleApplier style(SummaryInterstitial view) {
        return new SummaryInterstitialStyleApplier(view);
    }

    public static CityRegistrationCheckmarkRowStyleApplier style(CityRegistrationCheckmarkRow view) {
        return new CityRegistrationCheckmarkRowStyleApplier(view);
    }

    public static InfiniteDotIndicatorStyleApplier style(InfiniteDotIndicator view) {
        return new InfiniteDotIndicatorStyleApplier(view);
    }

    public static ListingInfoRowStyleApplier style(ListingInfoRow view) {
        return new ListingInfoRowStyleApplier(view);
    }

    public static LeftIconRowStyleApplier style(LeftIconRow view) {
        return new LeftIconRowStyleApplier(view);
    }

    public static FixedActionFooterWithTextStyleApplier style(FixedActionFooterWithText view) {
        return new FixedActionFooterWithTextStyleApplier(view);
    }

    public static InquiryCardStyleApplier style(InquiryCard view) {
        return new InquiryCardStyleApplier(view);
    }

    public static CityRegistrationIconActionRowStyleApplier style(CityRegistrationIconActionRow view) {
        return new CityRegistrationIconActionRowStyleApplier(view);
    }

    public static LeftAlignedImageRowStyleApplier style(LeftAlignedImageRow view) {
        return new LeftAlignedImageRowStyleApplier(view);
    }

    public static EmptyStateCardStyleApplier style(EmptyStateCard view) {
        return new EmptyStateCardStyleApplier(view);
    }

    public static NuxCoverCardStyleApplier style(NuxCoverCard view) {
        return new NuxCoverCardStyleApplier(view);
    }

    public static MultiLineSplitRowStyleApplier style(MultiLineSplitRow view) {
        return new MultiLineSplitRowStyleApplier(view);
    }

    public static HaloImageTextRowStyleApplier style(HaloImageTextRow view) {
        return new HaloImageTextRowStyleApplier(view);
    }

    public static FlightTimeRowStyleApplier style(FlightTimeRow view) {
        return new FlightTimeRowStyleApplier(view);
    }

    public static ThreadPreviewRowWithLabelStyleApplier style(ThreadPreviewRowWithLabel view) {
        return new ThreadPreviewRowWithLabelStyleApplier(view);
    }
}
