package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;
import com.airbnb.p027n2.AddToPlanButtonMockAdapter;
import com.airbnb.p027n2.AirToolbarMockAdapter;
import com.airbnb.p027n2.ArticleDocumentMarqueeMockAdapter;
import com.airbnb.p027n2.ArticleSummaryRowMockAdapter;
import com.airbnb.p027n2.AutoResizableButtonBarMockAdapter;
import com.airbnb.p027n2.BarRowMockAdapter;
import com.airbnb.p027n2.BasicRowMockAdapter;
import com.airbnb.p027n2.BedDetailsCardMockAdapter;
import com.airbnb.p027n2.BigNumberRowMockAdapter;
import com.airbnb.p027n2.BookingNavigationViewMockAdapter;
import com.airbnb.p027n2.BottomBarBannerMockAdapter;
import com.airbnb.p027n2.BottomButtonBarMockAdapter;
import com.airbnb.p027n2.BulletTextRowMockAdapter;
import com.airbnb.p027n2.ButtonBarMockAdapter;
import com.airbnb.p027n2.CalendarBubblePopUpMockAdapter;
import com.airbnb.p027n2.CategorizedFilterButtonsMockAdapter;
import com.airbnb.p027n2.CheckInGuideAddStepButtonMockAdapter;
import com.airbnb.p027n2.CheckInGuideStepCardMockAdapter;
import com.airbnb.p027n2.CityRegistrationCheckmarkRowMockAdapter;
import com.airbnb.p027n2.CityRegistrationIconActionRowMockAdapter;
import com.airbnb.p027n2.CityRegistrationToggleRowMockAdapter;
import com.airbnb.p027n2.CollaboratorsRowMockAdapter;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.DLSMockAdapter;
import com.airbnb.p027n2.DestinationCardMockAdapter;
import com.airbnb.p027n2.DisplayCardMockAdapter;
import com.airbnb.p027n2.DocumentCarouselMarqueeMockAdapter;
import com.airbnb.p027n2.DocumentMarqueeMockAdapter;
import com.airbnb.p027n2.EditorialMarqueeMockAdapter;
import com.airbnb.p027n2.EditorialSectionHeaderMockAdapter;
import com.airbnb.p027n2.EmptyStateCardMockAdapter;
import com.airbnb.p027n2.EntryMarqueeMockAdapter;
import com.airbnb.p027n2.EventScheduleInterstitialMockAdapter;
import com.airbnb.p027n2.ExpandableCollectionRowMockAdapter;
import com.airbnb.p027n2.ExploreEmptyStateMockAdapter;
import com.airbnb.p027n2.FilterSuggestionPillMockAdapter;
import com.airbnb.p027n2.FindInlineFiltersToggleRowMockAdapter;
import com.airbnb.p027n2.FixedActionFooterMockAdapter;
import com.airbnb.p027n2.FixedActionFooterWithTextMockAdapter;
import com.airbnb.p027n2.FixedDualActionFooterMockAdapter;
import com.airbnb.p027n2.FixedFlowActionAdvanceFooterMockAdapter;
import com.airbnb.p027n2.FixedFlowActionFooterMockAdapter;
import com.airbnb.p027n2.FlightTimeRowMockAdapter;
import com.airbnb.p027n2.FullImageRowMockAdapter;
import com.airbnb.p027n2.FullScreenImageMarqueeMockAdapter;
import com.airbnb.p027n2.GiftCardPromoMockAdapter;
import com.airbnb.p027n2.GuestRatingsMarqueeMockAdapter;
import com.airbnb.p027n2.GuestStarRatingBreakdownMockAdapter;
import com.airbnb.p027n2.GuideImageMarqueeMockAdapter;
import com.airbnb.p027n2.HaloImageTextRowMockAdapter;
import com.airbnb.p027n2.HeroMarqueeMockAdapter;
import com.airbnb.p027n2.HomeAmenitiesMockAdapter;
import com.airbnb.p027n2.HomeCardChinaMockAdapter;
import com.airbnb.p027n2.HomeCardMockAdapter;
import com.airbnb.p027n2.HomeHighlightsMockAdapter;
import com.airbnb.p027n2.HomeMarqueeMockAdapter;
import com.airbnb.p027n2.HomeReviewRowMockAdapter;
import com.airbnb.p027n2.HomeStarRatingBreakdownMockAdapter;
import com.airbnb.p027n2.IconRowMockAdapter;
import com.airbnb.p027n2.ImagePreviewRowMockAdapter;
import com.airbnb.p027n2.ImageRowMockAdapter;
import com.airbnb.p027n2.ImageWithButtonRowMockAdapter;
import com.airbnb.p027n2.ImpactDisplayCardMockAdapter;
import com.airbnb.p027n2.ImpactMarqueeMockAdapter;
import com.airbnb.p027n2.InfiniteDotIndicatorMockAdapter;
import com.airbnb.p027n2.InfoActionRowMockAdapter;
import com.airbnb.p027n2.InfoPanelRowMockAdapter;
import com.airbnb.p027n2.InfoRowMockAdapter;
import com.airbnb.p027n2.InlineCautionMockAdapter;
import com.airbnb.p027n2.InlineContextMockAdapter;
import com.airbnb.p027n2.InlineFormattedIntegerInputRowMockAdapter;
import com.airbnb.p027n2.InlineInputRowMockAdapter;
import com.airbnb.p027n2.InlineInputWithContactPickerRowMockAdapter;
import com.airbnb.p027n2.InlineMultilineInputRowMockAdapter;
import com.airbnb.p027n2.InlineTipRowMockAdapter;
import com.airbnb.p027n2.InputFieldMockAdapter;
import com.airbnb.p027n2.InputMarqueeMockAdapter;
import com.airbnb.p027n2.InputSuggestionActionRowChinaMockAdapter;
import com.airbnb.p027n2.InputSuggestionActionRowMockAdapter;
import com.airbnb.p027n2.InputSuggestionSubRowMockAdapter;
import com.airbnb.p027n2.InquiryCardMockAdapter;
import com.airbnb.p027n2.IntegerFormatInputViewMockAdapter;
import com.airbnb.p027n2.InterstitialMockAdapter;
import com.airbnb.p027n2.InviteRowMockAdapter;
import com.airbnb.p027n2.KickerMarqueeMockAdapter;
import com.airbnb.p027n2.LabelDocumentMarqueeMockAdapter;
import com.airbnb.p027n2.LabelRowMockAdapter;
import com.airbnb.p027n2.LabeledSectionRowMockAdapter;
import com.airbnb.p027n2.LargeIconRowMockAdapter;
import com.airbnb.p027n2.LeftAlignedImageRowMockAdapter;
import com.airbnb.p027n2.LeftIconRowMockAdapter;
import com.airbnb.p027n2.LinkActionRowMockAdapter;
import com.airbnb.p027n2.LinkableLegalTextRowMockAdapter;
import com.airbnb.p027n2.ListingDescriptionMockAdapter;
import com.airbnb.p027n2.ListingInfoCardRowMockAdapter;
import com.airbnb.p027n2.ListingInfoRowMockAdapter;
import com.airbnb.p027n2.LoadingTextMockAdapter;
import com.airbnb.p027n2.LocationContextCardMockAdapter;
import com.airbnb.p027n2.MapInterstitialMockAdapter;
import com.airbnb.p027n2.MapSearchButtonMockAdapter;
import com.airbnb.p027n2.MarketingCardMockAdapter;
import com.airbnb.p027n2.MessageInputOneRowMockAdapter;
import com.airbnb.p027n2.MessageInputTwoRowsMockAdapter;
import com.airbnb.p027n2.MessageTranslationRowMockAdapter;
import com.airbnb.p027n2.MicroDisplayCardMockAdapter;
import com.airbnb.p027n2.MicroRowMockAdapter;
import com.airbnb.p027n2.MicroSectionHeaderMockAdapter;
import com.airbnb.p027n2.MosaicCardMockAdapter;
import com.airbnb.p027n2.MultiLineSplitRowMockAdapter;
import com.airbnb.p027n2.NavigationPillMockAdapter;
import com.airbnb.p027n2.NestedListingChildRowMockAdapter;
import com.airbnb.p027n2.NestedListingEditRowMockAdapter;
import com.airbnb.p027n2.NestedListingRowMockAdapter;
import com.airbnb.p027n2.NestedListingToggleRowMockAdapter;
import com.airbnb.p027n2.NoProfilePhotoDetailsSummaryMockAdapter;
import com.airbnb.p027n2.NumberedSimpleTextRowMockAdapter;
import com.airbnb.p027n2.NuxCoverCardMockAdapter;
import com.airbnb.p027n2.ParticipantRowMockAdapter;
import com.airbnb.p027n2.PaymentInputLayoutMockAdapter;
import com.airbnb.p027n2.PaymentOptionRowMockAdapter;
import com.airbnb.p027n2.PlaceCardMockAdapter;
import com.airbnb.p027n2.PosterCardMockAdapter;
import com.airbnb.p027n2.PosterRowMockAdapter;
import com.airbnb.p027n2.PriceFilterButtonsMockAdapter;
import com.airbnb.p027n2.PriceSummaryMockAdapter;
import com.airbnb.p027n2.PricingBreakdownRowMockAdapter;
import com.airbnb.p027n2.PrimaryButtonMockAdapter;
import com.airbnb.p027n2.PrimaryTextBottomBarMockAdapter;
import com.airbnb.p027n2.ProfileCompletionBarRowMockAdapter;
import com.airbnb.p027n2.ProfileLinkRowMockAdapter;
import com.airbnb.p027n2.PromotionMarqueeMockAdapter;
import com.airbnb.p027n2.RangeDisplayMockAdapter;
import com.airbnb.p027n2.RearrangableLabeledPhotoCellMockAdapter;
import com.airbnb.p027n2.RecentSearchCardMockAdapter;
import com.airbnb.p027n2.RecommendationCardMockAdapter;
import com.airbnb.p027n2.RecommendationCardSquareMockAdapter;
import com.airbnb.p027n2.RecommendationRowMockAdapter;
import com.airbnb.p027n2.RefreshLoaderMockAdapter;
import com.airbnb.p027n2.ReportableDetailsSummaryMockAdapter;
import com.airbnb.p027n2.RequirementChecklistRowMockAdapter;
import com.airbnb.p027n2.ReviewBulletRowMockAdapter;
import com.airbnb.p027n2.ReviewMarqueeMockAdapter;
import com.airbnb.p027n2.ScratchMicroRowWithRightTextMockAdapter;
import com.airbnb.p027n2.SearchParamsRowMockAdapter;
import com.airbnb.p027n2.SearchSuggestionItemMockAdapter;
import com.airbnb.p027n2.SectionHeaderMockAdapter;
import com.airbnb.p027n2.SelectAmenityItemMockAdapter;
import com.airbnb.p027n2.SelectBulletListMockAdapter;
import com.airbnb.p027n2.SelectHomeRoomItemMockAdapter;
import com.airbnb.p027n2.SelectHomeSummaryRowMockAdapter;
import com.airbnb.p027n2.SelectHostRowMockAdapter;
import com.airbnb.p027n2.SelectIconBulletRowMockAdapter;
import com.airbnb.p027n2.SelectMapInterstitialMockAdapter;
import com.airbnb.p027n2.SelectMarqueeMockAdapter;
import com.airbnb.p027n2.SheetFormattedIntegerInputTextMockAdapter;
import com.airbnb.p027n2.SheetInputTextMockAdapter;
import com.airbnb.p027n2.SheetMarqueeMockAdapter;
import com.airbnb.p027n2.SheetProgressBarMockAdapter;
import com.airbnb.p027n2.SheetStepperRowMockAdapter;
import com.airbnb.p027n2.SimilarPlaylistCardMockAdapter;
import com.airbnb.p027n2.SimpleTextRowMockAdapter;
import com.airbnb.p027n2.SimpleTitleContentRowMockAdapter;
import com.airbnb.p027n2.SmallMarqueeMockAdapter;
import com.airbnb.p027n2.SmallSheetSwitchRowMockAdapter;
import com.airbnb.p027n2.SmallSheetSwitchRowSwitchMockAdapter;
import com.airbnb.p027n2.SmallTextRowMockAdapter;
import com.airbnb.p027n2.StandardButtonRowMockAdapter;
import com.airbnb.p027n2.StandardRowMockAdapter;
import com.airbnb.p027n2.StandardRowWithLabelMockAdapter;
import com.airbnb.p027n2.StarRatingSummaryMockAdapter;
import com.airbnb.p027n2.StatusBannerMockAdapter;
import com.airbnb.p027n2.StepperRowMockAdapter;
import com.airbnb.p027n2.SummaryInterstitialMockAdapter;
import com.airbnb.p027n2.SwitchRowMockAdapter;
import com.airbnb.p027n2.TagsCollectionRowMockAdapter;
import com.airbnb.p027n2.TeamComponentTemplateCopyMeMockAdapter;
import com.airbnb.p027n2.TeamOwner;
import com.airbnb.p027n2.TextRowMockAdapter;
import com.airbnb.p027n2.ThreadBottomActionButtonMockAdapter;
import com.airbnb.p027n2.ThreadPreviewRowMockAdapter;
import com.airbnb.p027n2.ThreadPreviewRowWithLabelMockAdapter;
import com.airbnb.p027n2.ToggleActionRowMockAdapter;
import com.airbnb.p027n2.TweenRowMockAdapter;
import com.airbnb.p027n2.UserDetailsActionRowMockAdapter;
import com.airbnb.p027n2.ValueRowMockAdapter;
import com.airbnb.p027n2.components.decide.select.SelectAmenityItem;
import com.airbnb.p027n2.components.decide.select.SelectBulletList;
import com.airbnb.p027n2.components.decide.select.SelectHomeRoomItem;
import com.airbnb.p027n2.components.decide.select.SelectHomeSummaryRow;
import com.airbnb.p027n2.components.decide.select.SelectHostRow;
import com.airbnb.p027n2.components.decide.select.SelectIconBulletRow;
import com.airbnb.p027n2.components.decide.select.SelectMapInterstitial;
import com.airbnb.p027n2.components.decide.select.SelectMarquee;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCell;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.crypto.tls.CipherSuite;

/* renamed from: com.airbnb.n2.components.DLSComponentsBase */
public class DLSComponentsBase {
    protected static final DLSComponent[] ALL;
    public static final DLSComponent<AddToPlanButton> AddToPlanButton = new DLSComponent(AddToPlanButton.class, DLSComponentType.Team, "AddToPlanButton", " Button for Add to Plans flow with title and subtitle.\n Has selected and disabled states.\n", TeamOwner.TRIPS, R.layout.n2_view_holder_add_to_plan_button) {
        public DLSMockAdapter<AddToPlanButton> mockAdapter() {
            return new AddToPlanButtonMockAdapter();
        }

        public AddToPlanButton createView(Context context, AttributeSet attrs) {
            return new AddToPlanButton(context, attrs);
        }
    };
    public static final DLSComponent<AirToolbar> AirToolbar = new DLSComponent(AirToolbar.class, DLSComponentType.Core, "AirToolbar", " AirToolbar is designed to be used as the primary toolbar across the Airbnb app. It can take care of many of the confusing aspects or\n inconsistencies across existing Toolbars.\n <p>\n Setting a navigation icon: From XML: Use `airbnb:n2_navigationIcon` with: back, x, menu, or none. From Java: Use {@link #setNavigationIcon(int)}\n with {@link #NAVIGATION_ICON_BACK}, {@link #NAVIGATION_ICON_X}, {@link #NAVIGATION_ICON_MENU}. or {@link #NAVIGATION_ICON_NONE}.\n <p>\n Setting the title or subtitle: From XML: Use `airbnb:n2_titleText` or `airbnb:n2_subtitleText`. From Java: Use {@link #setTitle(int)}, {@link\n #setTitle(CharSequence)}, {@link #setSubtitle(int)}, or {@link #setSubtitle(CharSequence)}.\n <p>\n Setting the foreground or background color: From XML: Use `android:background` or `airbnb:n2_foregroundColor` From Java: Use {@link\n #setBackgroundColor(int)} or {@link #setForegroundColor(int)}\n <p>\n Foreground colors will tint the navigation icon, title, subtitle, menu icons, and overflow icon.\n <p>\n Setting a menu: From XML: Use `airbnb:n2_menu`.\n <p>\n You ALSO need to enable it from a fragment or activity and CANNOT manually inflate the menu into the toolbar yourself or it won't match your theme.\n Fragment: Call {@link android.support.v4.app.Fragment#setHasOptionsMenu(boolean)} and call {@link #onCreateOptionsMenu(Menu, MenuInflater)} from\n the fragment's {@link android.support.v4.app.Fragment#onCreateOptionsMenu(Menu, MenuInflater)}. Activity: Call {@link #onCreateOptionsMenu(Menu,\n MenuInflater)} with {@link Activity#getMenuInflater()} from {@link Activity#onCreateOptionsMenu(Menu)}.\n <p>\n Scrolling with {@link ToolbarCoordinator} behavior. From XML: Use `airbnb:n2_scrollWith`. AirToolbar will walk up the view hierarchy, try to find\n it, and attach a {@link ToolbarCoordinator}.\n", TeamOwner.DLS, R.layout.n2_view_holder_air_toolbar) {
        public DLSMockAdapter<AirToolbar> mockAdapter() {
            return new AirToolbarMockAdapter();
        }

        public AirToolbar createView(Context context, AttributeSet attrs) {
            return new AirToolbar(context, attrs);
        }
    };
    public static final DLSComponent<ArticleDocumentMarquee> ArticleDocumentMarquee = new DLSComponent(ArticleDocumentMarquee.class, DLSComponentType.Team, "ArticleDocumentMarquee", " This is the same as a DocumentMarquee but has a kicker at the top. The kicker is a smaller height text at the top to specify some sort of title,\n e.g. a listing name\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_article_document_marquee) {
        public DLSMockAdapter<ArticleDocumentMarquee> mockAdapter() {
            return new ArticleDocumentMarqueeMockAdapter();
        }

        public ArticleDocumentMarquee createView(Context context, AttributeSet attrs) {
            return new ArticleDocumentMarquee(context, attrs);
        }
    };
    public static final DLSComponent<ArticleSummaryRow> ArticleSummaryRow = new DLSComponent(ArticleSummaryRow.class, DLSComponentType.Team, "ArticleSummaryRow", " A DLS component for displaying stories.\n", TeamOwner.CHINA, R.layout.n2_view_holder_article_summary_row) {
        public DLSMockAdapter<ArticleSummaryRow> mockAdapter() {
            return new ArticleSummaryRowMockAdapter();
        }

        public ArticleSummaryRow createView(Context context, AttributeSet attrs) {
            return new ArticleSummaryRow(context, attrs);
        }
    };
    public static final DLSComponent<AutoResizableButtonBar> AutoResizableButtonBar = new DLSComponent(AutoResizableButtonBar.class, DLSComponentType.Team, "AutoResizableButtonBar", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_auto_resizable_button_bar) {
        public DLSMockAdapter<AutoResizableButtonBar> mockAdapter() {
            return new AutoResizableButtonBarMockAdapter();
        }

        public AutoResizableButtonBar createView(Context context, AttributeSet attrs) {
            return new AutoResizableButtonBar(context, attrs);
        }
    };
    public static final DLSComponent<BarRow> BarRow = new DLSComponent(BarRow.class, DLSComponentType.Core, "BarRow", " Displays a bar indicating progress towards a goal\n", TeamOwner.DLS, R.layout.n2_view_holder_bar_row) {
        public DLSMockAdapter<BarRow> mockAdapter() {
            return new BarRowMockAdapter();
        }

        public BarRow createView(Context context, AttributeSet attrs) {
            return new BarRow(context, attrs);
        }
    };
    public static final DLSComponent<BasicRow> BasicRow = new DLSComponent(BasicRow.class, DLSComponentType.Core, "BasicRow", null, TeamOwner.DLS, R.layout.n2_view_holder_basic_row) {
        public DLSMockAdapter<BasicRow> mockAdapter() {
            return new BasicRowMockAdapter();
        }

        public BasicRow createView(Context context, AttributeSet attrs) {
            return new BasicRow(context, attrs);
        }
    };
    public static final DLSComponent<BedDetailsCard> BedDetailsCard = new DLSComponent(BedDetailsCard.class, DLSComponentType.Team, "BedDetailsCard", " Card component used to display an image with text below\n", TeamOwner.HOMES, R.layout.n2_view_holder_bed_details_card) {
        public DLSMockAdapter<BedDetailsCard> mockAdapter() {
            return new BedDetailsCardMockAdapter();
        }

        public BedDetailsCard createView(Context context, AttributeSet attrs) {
            return new BedDetailsCard(context, attrs);
        }
    };
    public static final DLSComponent<BigNumberRow> BigNumberRow = new DLSComponent(BigNumberRow.class, DLSComponentType.Deprecated, "BigNumberRow", " Displays three lines of text in descending text size.\n", TeamOwner.HOMES, R.layout.n2_view_holder_big_number_row) {
        public DLSMockAdapter<BigNumberRow> mockAdapter() {
            return new BigNumberRowMockAdapter();
        }

        public BigNumberRow createView(Context context, AttributeSet attrs) {
            return new BigNumberRow(context, attrs);
        }
    };
    public static final DLSComponent<BookingNavigationView> BookingNavigationView = new DLSComponent(BookingNavigationView.class, DLSComponentType.Team, "BookingNavigationView", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_booking_navigation_view) {
        public DLSMockAdapter<BookingNavigationView> mockAdapter() {
            return new BookingNavigationViewMockAdapter();
        }

        public BookingNavigationView createView(Context context, AttributeSet attrs) {
            return new BookingNavigationView(context, attrs);
        }
    };
    public static final DLSComponent<BottomBarBanner> BottomBarBanner = new DLSComponent(BottomBarBanner.class, DLSComponentType.Team, "BottomBarBanner", null, TeamOwner.HOMES, R.layout.n2_view_holder_bottom_bar_banner) {
        public DLSMockAdapter<BottomBarBanner> mockAdapter() {
            return new BottomBarBannerMockAdapter();
        }

        public BottomBarBanner createView(Context context, AttributeSet attrs) {
            return new BottomBarBanner(context, attrs);
        }
    };
    public static final DLSComponent<BottomButtonBar> BottomButtonBar = new DLSComponent(BottomButtonBar.class, DLSComponentType.Team, "BottomButtonBar", null, TeamOwner.HOMES, R.layout.n2_view_holder_bottom_button_bar) {
        public DLSMockAdapter<BottomButtonBar> mockAdapter() {
            return new BottomButtonBarMockAdapter();
        }

        public BottomButtonBar createView(Context context, AttributeSet attrs) {
            return new BottomButtonBar(context, attrs);
        }
    };
    public static final DLSComponent<BulletTextRow> BulletTextRow = new DLSComponent(BulletTextRow.class, DLSComponentType.Team, "BulletTextRow", " The text row with a bullet symbol on the left.\n", TeamOwner.UNKNOWN, R.layout.n2_view_holder_bullet_text_row) {
        public DLSMockAdapter<BulletTextRow> mockAdapter() {
            return new BulletTextRowMockAdapter();
        }

        public BulletTextRow createView(Context context, AttributeSet attrs) {
            return new BulletTextRow(context, attrs);
        }
    };
    public static final DLSComponent<ButtonBar> ButtonBar = new DLSComponent(ButtonBar.class, DLSComponentType.Core, "ButtonBar", " Component with 2, 3, or 4 buttons that fit across the width of the device.\n", TeamOwner.DLS, R.layout.n2_view_holder_button_bar) {
        public DLSMockAdapter<ButtonBar> mockAdapter() {
            return new ButtonBarMockAdapter();
        }

        public ButtonBar createView(Context context, AttributeSet attrs) {
            return new ButtonBar(context, attrs);
        }
    };
    public static final DLSComponent<CalendarBubblePopUp> CalendarBubblePopUp = new DLSComponent(CalendarBubblePopUp.class, DLSComponentType.Team, "CalendarBubblePopUp", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_calendar_bubble_pop_up) {
        public DLSMockAdapter<CalendarBubblePopUp> mockAdapter() {
            return new CalendarBubblePopUpMockAdapter();
        }

        public CalendarBubblePopUp createView(Context context, AttributeSet attrs) {
            return new CalendarBubblePopUp(context, attrs);
        }
    };
    public static final DLSComponent<CategorizedFilterButtons> CategorizedFilterButtons = new DLSComponent(CategorizedFilterButtons.class, DLSComponentType.Team, "CategorizedFilterButtons", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_categorized_filter_buttons) {
        public DLSMockAdapter<CategorizedFilterButtons> mockAdapter() {
            return new CategorizedFilterButtonsMockAdapter();
        }

        public CategorizedFilterButtons createView(Context context, AttributeSet attrs) {
            return new CategorizedFilterButtons(context, attrs);
        }
    };
    public static final DLSComponent<CheckInGuideAddStepButton> CheckInGuideAddStepButton = new DLSComponent(CheckInGuideAddStepButton.class, DLSComponentType.Team, "CheckInGuideAddStepButton", " Lightweight wrapper around a button for the step card carousel in the Check-in guide creation screen\n", TeamOwner.HOMES, R.layout.n2_view_holder_check_in_guide_add_step_button) {
        public DLSMockAdapter<CheckInGuideAddStepButton> mockAdapter() {
            return new CheckInGuideAddStepButtonMockAdapter();
        }

        public CheckInGuideAddStepButton createView(Context context, AttributeSet attrs) {
            return new CheckInGuideAddStepButton(context, attrs);
        }
    };
    public static final DLSComponent<CheckInGuideStepCard> CheckInGuideStepCard = new DLSComponent(CheckInGuideStepCard.class, DLSComponentType.Team, "CheckInGuideStepCard", " Card component used to display a step of the check-in guide to the host who is creating it\n", TeamOwner.HOMES, R.layout.n2_view_holder_check_in_guide_step_card) {
        public DLSMockAdapter<CheckInGuideStepCard> mockAdapter() {
            return new CheckInGuideStepCardMockAdapter();
        }

        public CheckInGuideStepCard createView(Context context, AttributeSet attrs) {
            return new CheckInGuideStepCard(context, attrs);
        }
    };
    public static final DLSComponent<CityRegistrationCheckmarkRow> CityRegistrationCheckmarkRow = new DLSComponent(CityRegistrationCheckmarkRow.class, DLSComponentType.Team, "CityRegistrationCheckmarkRow", null, TeamOwner.TRUST, R.layout.n2_view_holder_city_registration_checkmark_row) {
        public DLSMockAdapter<CityRegistrationCheckmarkRow> mockAdapter() {
            return new CityRegistrationCheckmarkRowMockAdapter();
        }

        public CityRegistrationCheckmarkRow createView(Context context, AttributeSet attrs) {
            return new CityRegistrationCheckmarkRow(context, attrs);
        }
    };
    public static final DLSComponent<CityRegistrationIconActionRow> CityRegistrationIconActionRow = new DLSComponent(CityRegistrationIconActionRow.class, DLSComponentType.Team, "CityRegistrationIconActionRow", " A Simple Info Action Row with a Left Aligned Icon\n", TeamOwner.TRUST, R.layout.n2_view_holder_city_registration_icon_action_row) {
        public DLSMockAdapter<CityRegistrationIconActionRow> mockAdapter() {
            return new CityRegistrationIconActionRowMockAdapter();
        }

        public CityRegistrationIconActionRow createView(Context context, AttributeSet attrs) {
            return new CityRegistrationIconActionRow(context, attrs);
        }
    };
    public static final DLSComponent<CityRegistrationToggleRow> CityRegistrationToggleRow = new DLSComponent(CityRegistrationToggleRow.class, DLSComponentType.Team, "CityRegistrationToggleRow", " A row with a title, subtitle and check box.\n", TeamOwner.TRUST, R.layout.n2_view_holder_city_registration_toggle_row) {
        public DLSMockAdapter<CityRegistrationToggleRow> mockAdapter() {
            return new CityRegistrationToggleRowMockAdapter();
        }

        public CityRegistrationToggleRow createView(Context context, AttributeSet attrs) {
            return new CityRegistrationToggleRow(context, attrs);
        }
    };
    public static final DLSComponent<CollaboratorsRow> CollaboratorsRow = new DLSComponent(CollaboratorsRow.class, DLSComponentType.Team, "CollaboratorsRow", "Shows a horizontal stack of user photos, with an overflow indicator if necessary. ", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_collaborators_row) {
        public DLSMockAdapter<CollaboratorsRow> mockAdapter() {
            return new CollaboratorsRowMockAdapter();
        }

        public CollaboratorsRow createView(Context context, AttributeSet attrs) {
            return new CollaboratorsRow(context, attrs);
        }
    };
    public static final DLSComponent<DestinationCard> DestinationCard = new DLSComponent(DestinationCard.class, DLSComponentType.Core, "DestinationCard", null, TeamOwner.TRIPS, R.layout.n2_view_holder_destination_card) {
        public DLSMockAdapter<DestinationCard> mockAdapter() {
            return new DestinationCardMockAdapter();
        }

        public DestinationCard createView(Context context, AttributeSet attrs) {
            return new DestinationCard(context, attrs);
        }
    };
    public static final DLSComponent<DisplayCard> DisplayCard = new DLSComponent(DisplayCard.class, DLSComponentType.Core, "DisplayCard", " Card component used to display an image with text below\n", TeamOwner.DLS, R.layout.n2_view_holder_display_card) {
        public DLSMockAdapter<DisplayCard> mockAdapter() {
            return new DisplayCardMockAdapter();
        }

        public DisplayCard createView(Context context, AttributeSet attrs) {
            return new DisplayCard(context, attrs);
        }
    };
    public static final DLSComponent<DocumentCarouselMarquee> DocumentCarouselMarquee = new DLSComponent(DocumentCarouselMarquee.class, DLSComponentType.Team, "DocumentCarouselMarquee", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_document_carousel_marquee) {
        public DLSMockAdapter<DocumentCarouselMarquee> mockAdapter() {
            return new DocumentCarouselMarqueeMockAdapter();
        }

        public DocumentCarouselMarquee createView(Context context, AttributeSet attrs) {
            return new DocumentCarouselMarquee(context, attrs);
        }
    };
    public static final DLSComponent<DocumentMarquee> DocumentMarquee = new DLSComponent(DocumentMarquee.class, DLSComponentType.Core, "DocumentMarquee", " See DLS spec for Document Marquee and Link Marquee\n", TeamOwner.DLS, R.layout.n2_view_holder_document_marquee) {
        public DLSMockAdapter<DocumentMarquee> mockAdapter() {
            return new DocumentMarqueeMockAdapter();
        }

        public DocumentMarquee createView(Context context, AttributeSet attrs) {
            return new DocumentMarquee(context, attrs);
        }
    };
    public static final DLSComponent<EditorialMarquee> EditorialMarquee = new DLSComponent(EditorialMarquee.class, DLSComponentType.Core, "EditorialMarquee", null, TeamOwner.DLS, R.layout.n2_view_holder_editorial_marquee) {
        public DLSMockAdapter<EditorialMarquee> mockAdapter() {
            return new EditorialMarqueeMockAdapter();
        }

        public EditorialMarquee createView(Context context, AttributeSet attrs) {
            return new EditorialMarquee(context, attrs);
        }
    };
    public static final DLSComponent<EditorialSectionHeader> EditorialSectionHeader = new DLSComponent(EditorialSectionHeader.class, DLSComponentType.Team, "EditorialSectionHeader", " Section header for items that are editorial in nature (aka Recommendation items etc)\n", TeamOwner.TRIPS, R.layout.n2_view_holder_editorial_section_header) {
        public DLSMockAdapter<EditorialSectionHeader> mockAdapter() {
            return new EditorialSectionHeaderMockAdapter();
        }

        public EditorialSectionHeader createView(Context context, AttributeSet attrs) {
            return new EditorialSectionHeader(context, attrs);
        }
    };
    public static final DLSComponent<EmptyStateCard> EmptyStateCard = new DLSComponent(EmptyStateCard.class, DLSComponentType.Team, "EmptyStateCard", null, TeamOwner.HOMES, R.layout.n2_view_holder_empty_state_card) {
        public DLSMockAdapter<EmptyStateCard> mockAdapter() {
            return new EmptyStateCardMockAdapter();
        }

        public EmptyStateCard createView(Context context, AttributeSet attrs) {
            return new EmptyStateCard(context, attrs);
        }
    };
    public static final DLSComponent<EntryMarquee> EntryMarquee = new DLSComponent(EntryMarquee.class, DLSComponentType.Deprecated, "EntryMarquee", " Text is larger than Document Marquee with extra padding below Title and Caption.\n Contains title, caption and optional separator.\n <p>\n TODO(nathanael-silverman) Replace with DocumentMarquee\n", TeamOwner.DLS, R.layout.n2_view_holder_entry_marquee) {
        public DLSMockAdapter<EntryMarquee> mockAdapter() {
            return new EntryMarqueeMockAdapter();
        }

        public EntryMarquee createView(Context context, AttributeSet attrs) {
            return new EntryMarquee(context, attrs);
        }
    };
    public static final DLSComponent<EventScheduleInterstitial> EventScheduleInterstitial = new DLSComponent(EventScheduleInterstitial.class, DLSComponentType.Team, "EventScheduleInterstitial", " Implements the Interstitial component to show inspection booking scheduling information\n", TeamOwner.HOMES, R.layout.n2_view_holder_event_schedule_interstitial) {
        public DLSMockAdapter<EventScheduleInterstitial> mockAdapter() {
            return new EventScheduleInterstitialMockAdapter();
        }

        public EventScheduleInterstitial createView(Context context, AttributeSet attrs) {
            return new EventScheduleInterstitial(context, attrs);
        }
    };
    public static final DLSComponent<ExpandableCollectionRow> ExpandableCollectionRow = new DLSComponent(ExpandableCollectionRow.class, DLSComponentType.Team, "ExpandableCollectionRow", "  This row uses a list of {@link RowItem}s to populate inflated row views that contain a left and right text.\n  <p/>\n  It defaults to bolding the text of the\n  first row.\n  <p/>\n  Initially, this component will not show any rows past the first 2 rows, but clicking the expand text will expand the component to show\n  all the rows. There is also text available at the bottom of the component to show a caption or disclaimer.\n  <p/>\n  There is no way to un-expand the rows\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_expandable_collection_row) {
        public DLSMockAdapter<ExpandableCollectionRow> mockAdapter() {
            return new ExpandableCollectionRowMockAdapter();
        }

        public ExpandableCollectionRow createView(Context context, AttributeSet attrs) {
            return new ExpandableCollectionRow(context, attrs);
        }
    };
    public static final DLSComponent<ExploreEmptyState> ExploreEmptyState = new DLSComponent(ExploreEmptyState.class, DLSComponentType.Team, "ExploreEmptyState", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_explore_empty_state) {
        public DLSMockAdapter<ExploreEmptyState> mockAdapter() {
            return new ExploreEmptyStateMockAdapter();
        }

        public ExploreEmptyState createView(Context context, AttributeSet attrs) {
            return new ExploreEmptyState(context, attrs);
        }
    };
    public static final DLSComponent<FilterSuggestionPill> FilterSuggestionPill = new DLSComponent(FilterSuggestionPill.class, DLSComponentType.Team, "FilterSuggestionPill", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_filter_suggestion_pill) {
        public DLSMockAdapter<FilterSuggestionPill> mockAdapter() {
            return new FilterSuggestionPillMockAdapter();
        }

        public FilterSuggestionPill createView(Context context, AttributeSet attrs) {
            return new FilterSuggestionPill(context, attrs);
        }
    };
    public static final DLSComponent<FindInlineFiltersToggleRow> FindInlineFiltersToggleRow = new DLSComponent(FindInlineFiltersToggleRow.class, DLSComponentType.Team, "FindInlineFiltersToggleRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_find_inline_filters_toggle_row) {
        public DLSMockAdapter<FindInlineFiltersToggleRow> mockAdapter() {
            return new FindInlineFiltersToggleRowMockAdapter();
        }

        public FindInlineFiltersToggleRow createView(Context context, AttributeSet attrs) {
            return new FindInlineFiltersToggleRow(context, attrs);
        }
    };
    public static final DLSComponent<FixedActionFooter> FixedActionFooter = new DLSComponent(FixedActionFooter.class, DLSComponentType.Core, "FixedActionFooter", " Use when there is one primary \"submit\" type of action.\n <p>\n Use the Rausch style for the most important flows (e.g. the booking flow where there are\n financial, legal or other commitments). Use the Babu style for normal flows with less important\n or reversible commitments.\n\n @see FixedDualActionFooter\n @see FixedFlowActionFooter\n @see FixedFlowActionAdvanceFooter\n", TeamOwner.DLS, R.layout.n2_view_holder_fixed_action_footer) {
        public DLSMockAdapter<FixedActionFooter> mockAdapter() {
            return new FixedActionFooterMockAdapter();
        }

        public FixedActionFooter createView(Context context, AttributeSet attrs) {
            return new FixedActionFooter(context, attrs);
        }
    };
    public static final DLSComponent<FixedActionFooterWithText> FixedActionFooterWithText = new DLSComponent(FixedActionFooterWithText.class, DLSComponentType.Team, "FixedActionFooterWithText", null, TeamOwner.HOMES, R.layout.n2_view_holder_fixed_action_footer_with_text) {
        public DLSMockAdapter<FixedActionFooterWithText> mockAdapter() {
            return new FixedActionFooterWithTextMockAdapter();
        }

        public FixedActionFooterWithText createView(Context context, AttributeSet attrs) {
            return new FixedActionFooterWithText(context, attrs);
        }
    };
    public static final DLSComponent<FixedDualActionFooter> FixedDualActionFooter = new DLSComponent(FixedDualActionFooter.class, DLSComponentType.Core, "FixedDualActionFooter", " Use when there are two related or unrelated actions (e.g. agree, disagree).\n <p>\n Use the Rausch style for the most important flows (e.g. the booking flow where there are\n financial, legal or other commitments). Use the Babu style for normal flows with less important\n or reversible commitments.\n\n @see FixedActionFooter\n @see FixedFlowActionFooter\n @see FixedFlowActionAdvanceFooter\n", TeamOwner.DLS, R.layout.n2_view_holder_fixed_dual_action_footer) {
        public DLSMockAdapter<FixedDualActionFooter> mockAdapter() {
            return new FixedDualActionFooterMockAdapter();
        }

        public FixedDualActionFooter createView(Context context, AttributeSet attrs) {
            return new FixedDualActionFooter(context, attrs);
        }
    };
    public static final DLSComponent<FixedFlowActionAdvanceFooter> FixedFlowActionAdvanceFooter = new DLSComponent(FixedFlowActionAdvanceFooter.class, DLSComponentType.Core, "FixedFlowActionAdvanceFooter", " Use to advance along a flow between the start and end pages.\n <p>\n Use the Rausch style for the most important flows (e.g. the booking flow where there are\n financial, legal or other commitments). Use the Babu style for normal flows with less important\n or reversible commitments.\n\n @see FixedActionFooter\n @see FixedDualActionFooter\n @see FixedFlowActionFooter\n", TeamOwner.DLS, R.layout.n2_view_holder_fixed_flow_action_advance_footer) {
        public DLSMockAdapter<FixedFlowActionAdvanceFooter> mockAdapter() {
            return new FixedFlowActionAdvanceFooterMockAdapter();
        }

        public FixedFlowActionAdvanceFooter createView(Context context, AttributeSet attrs) {
            return new FixedFlowActionAdvanceFooter(context, attrs);
        }
    };
    public static final DLSComponent<FixedFlowActionFooter> FixedFlowActionFooter = new DLSComponent(FixedFlowActionFooter.class, DLSComponentType.Core, "FixedFlowActionFooter", " Use to start or submit a flow (e.g. request to book, confirm booking).\n <p>\n Use the Rausch style for the most important flows (e.g. the booking flow where there are\n financial, legal or other commitments). Use the Babu style for normal flows with less important\n or reversible commitments.\n <p>\n Note: This class is designed to be extended by {@link FixedFlowActionAdvanceFooter} since it's\n extremely similar save for the button styles, and the collapsed state.\n\n @see FixedActionFooter\n @see FixedDualActionFooter\n @see FixedFlowActionAdvanceFooter\n", TeamOwner.DLS, R.layout.n2_view_holder_fixed_flow_action_footer) {
        public DLSMockAdapter<FixedFlowActionFooter> mockAdapter() {
            return new FixedFlowActionFooterMockAdapter();
        }

        public FixedFlowActionFooter createView(Context context, AttributeSet attrs) {
            return new FixedFlowActionFooter(context, attrs);
        }
    };
    public static final DLSComponent<FlightTimeRow> FlightTimeRow = new DLSComponent(FlightTimeRow.class, DLSComponentType.Team, "FlightTimeRow", null, TeamOwner.TRIPS, R.layout.n2_view_holder_flight_time_row) {
        public DLSMockAdapter<FlightTimeRow> mockAdapter() {
            return new FlightTimeRowMockAdapter();
        }

        public FlightTimeRow createView(Context context, AttributeSet attrs) {
            return new FlightTimeRow(context, attrs);
        }
    };
    public static final DLSComponent<FullImageRow> FullImageRow = new DLSComponent(FullImageRow.class, DLSComponentType.Team, "FullImageRow", null, TeamOwner.TRUST, R.layout.n2_view_holder_full_image_row) {
        public DLSMockAdapter<FullImageRow> mockAdapter() {
            return new FullImageRowMockAdapter();
        }

        public FullImageRow createView(Context context, AttributeSet attrs) {
            return new FullImageRow(context, attrs);
        }
    };
    public static final DLSComponent<FullScreenImageMarquee> FullScreenImageMarquee = new DLSComponent(FullScreenImageMarquee.class, DLSComponentType.Team, "FullScreenImageMarquee", " This component is intended to be full screen and portrait mode. It contains a text box,\n anchored to the bottom of the screen, that grows to fit its content. The rest of the\n screen is taken up by an image on a background. The image will have left and right margins,\n and will be cropped and aligned to the top of the screen. There is an optional\n AirTextView over the image, which will have left and top margins.\n", TeamOwner.UNKNOWN, R.layout.n2_view_holder_full_screen_image_marquee) {
        public DLSMockAdapter<FullScreenImageMarquee> mockAdapter() {
            return new FullScreenImageMarqueeMockAdapter();
        }

        public FullScreenImageMarquee createView(Context context, AttributeSet attrs) {
            return new FullScreenImageMarquee(context, attrs);
        }
    };
    public static final DLSComponent<GiftCardPromo> GiftCardPromo = new DLSComponent(GiftCardPromo.class, DLSComponentType.Team, "GiftCardPromo", null, TeamOwner.PAYMENTS, R.layout.n2_view_holder_gift_card_promo) {
        public DLSMockAdapter<GiftCardPromo> mockAdapter() {
            return new GiftCardPromoMockAdapter();
        }

        public GiftCardPromo createView(Context context, AttributeSet attrs) {
            return new GiftCardPromo(context, attrs);
        }
    };
    public static final DLSComponent<GuestRatingsMarquee> GuestRatingsMarquee = new DLSComponent(GuestRatingsMarquee.class, DLSComponentType.Team, "GuestRatingsMarquee", " Used as a header for Guest Star Ratings page. It has a title with an overall star rating\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_guest_ratings_marquee) {
        public DLSMockAdapter<GuestRatingsMarquee> mockAdapter() {
            return new GuestRatingsMarqueeMockAdapter();
        }

        public GuestRatingsMarquee createView(Context context, AttributeSet attrs) {
            return new GuestRatingsMarquee(context, attrs);
        }
    };
    public static final DLSComponent<GuestStarRatingBreakdown> GuestStarRatingBreakdown = new DLSComponent(GuestStarRatingBreakdown.class, DLSComponentType.Team, "GuestStarRatingBreakdown", " View to show a list of all star rating categories for guest ratings\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_guest_star_rating_breakdown) {
        public DLSMockAdapter<GuestStarRatingBreakdown> mockAdapter() {
            return new GuestStarRatingBreakdownMockAdapter();
        }

        public GuestStarRatingBreakdown createView(Context context, AttributeSet attrs) {
            return new GuestStarRatingBreakdown(context, attrs);
        }
    };
    public static final DLSComponent<GuideImageMarquee> GuideImageMarquee = new DLSComponent(GuideImageMarquee.class, DLSComponentType.Team, "GuideImageMarquee", null, TeamOwner.HOMES, R.layout.n2_view_holder_guide_image_marquee) {
        public DLSMockAdapter<GuideImageMarquee> mockAdapter() {
            return new GuideImageMarqueeMockAdapter();
        }

        public GuideImageMarquee createView(Context context, AttributeSet attrs) {
            return new GuideImageMarquee(context, attrs);
        }
    };
    public static final DLSComponent<HaloImageTextRow> HaloImageTextRow = new DLSComponent(HaloImageTextRow.class, DLSComponentType.Team, "HaloImageTextRow", null, TeamOwner.TRIPS, R.layout.n2_view_holder_halo_image_text_row) {
        public DLSMockAdapter<HaloImageTextRow> mockAdapter() {
            return new HaloImageTextRowMockAdapter();
        }

        public HaloImageTextRow createView(Context context, AttributeSet attrs) {
            return new HaloImageTextRow(context, attrs);
        }
    };
    public static final DLSComponent<HeroMarquee> HeroMarquee = new DLSComponent(HeroMarquee.class, DLSComponentType.Deprecated, "HeroMarquee", " HeroMarquee component which is meant to be used, as a marquee, anchored to the top of the screen. In particular the HeroMarquee is intened to be\n used for relatively \"special\" contextual moments\n", TeamOwner.DLS, R.layout.n2_view_holder_hero_marquee) {
        public DLSMockAdapter<HeroMarquee> mockAdapter() {
            return new HeroMarqueeMockAdapter();
        }

        public HeroMarquee createView(Context context, AttributeSet attrs) {
            return new HeroMarquee(context, attrs);
        }
    };
    public static final DLSComponent<HomeAmenities> HomeAmenities = new DLSComponent(HomeAmenities.class, DLSComponentType.Core, "HomeAmenities", " DLS spec for a row that displays home amenities.\n Based on the screen size takes in the max number of items to display\n and aggregates the rest into a number like +8.\n", TeamOwner.DLS, R.layout.n2_view_holder_home_amenities) {
        public DLSMockAdapter<HomeAmenities> mockAdapter() {
            return new HomeAmenitiesMockAdapter();
        }

        public HomeAmenities createView(Context context, AttributeSet attrs) {
            return new HomeAmenities(context, attrs);
        }
    };
    public static final DLSComponent<HomeCard> HomeCard = new DLSComponent(HomeCard.class, DLSComponentType.Core, "HomeCard", " DLS spec card that displays a listing in a list or carousel.\n", TeamOwner.DLS, R.layout.n2_view_holder_home_card) {
        public DLSMockAdapter<HomeCard> mockAdapter() {
            return new HomeCardMockAdapter();
        }

        public HomeCard createView(Context context, AttributeSet attrs) {
            return new HomeCard(context, attrs);
        }
    };
    public static final DLSComponent<HomeCardChina> HomeCardChina = new DLSComponent(HomeCardChina.class, DLSComponentType.Team, "HomeCardChina", " This component is forked from {@link HomeCard} for P2 redesign for China\n", TeamOwner.CHINA, R.layout.n2_view_holder_home_card_china) {
        public DLSMockAdapter<HomeCardChina> mockAdapter() {
            return new HomeCardChinaMockAdapter();
        }

        public HomeCardChina createView(Context context, AttributeSet attrs) {
            return new HomeCardChina(context, attrs);
        }
    };
    public static final DLSComponent<HomeHighlights> HomeHighlights = new DLSComponent(HomeHighlights.class, DLSComponentType.Core, "HomeHighlights", " Actual DLS name: Specialty/Home/Highlights\n <p>\n DLS spec view for displaying listing highlights like rooms, beds etc\n", TeamOwner.DLS, R.layout.n2_view_holder_home_highlights) {
        public DLSMockAdapter<HomeHighlights> mockAdapter() {
            return new HomeHighlightsMockAdapter();
        }

        public HomeHighlights createView(Context context, AttributeSet attrs) {
            return new HomeHighlights(context, attrs);
        }
    };
    public static final DLSComponent<HomeMarquee> HomeMarquee = new DLSComponent(HomeMarquee.class, DLSComponentType.Core, "HomeMarquee", " DLS spec marquee that is used as a heading for Listing related pages.\n", TeamOwner.DLS, R.layout.n2_view_holder_home_marquee) {
        public DLSMockAdapter<HomeMarquee> mockAdapter() {
            return new HomeMarqueeMockAdapter();
        }

        public HomeMarquee createView(Context context, AttributeSet attrs) {
            return new HomeMarquee(context, attrs);
        }
    };
    public static final DLSComponent<HomeReviewRow> HomeReviewRow = new DLSComponent(HomeReviewRow.class, DLSComponentType.Core, "HomeReviewRow", " DLS spec view for displaying listing/user reviews\n", TeamOwner.DLS, R.layout.n2_view_holder_home_review_row) {
        public DLSMockAdapter<HomeReviewRow> mockAdapter() {
            return new HomeReviewRowMockAdapter();
        }

        public HomeReviewRow createView(Context context, AttributeSet attrs) {
            return new HomeReviewRow(context, attrs);
        }
    };
    public static final DLSComponent<HomeStarRatingBreakdown> HomeStarRatingBreakdown = new DLSComponent(HomeStarRatingBreakdown.class, DLSComponentType.Core, "HomeStarRatingBreakdown", " DLS spec view to show a list of all star rating categories\n", TeamOwner.DLS, R.layout.n2_view_holder_home_star_rating_breakdown) {
        public DLSMockAdapter<HomeStarRatingBreakdown> mockAdapter() {
            return new HomeStarRatingBreakdownMockAdapter();
        }

        public HomeStarRatingBreakdown createView(Context context, AttributeSet attrs) {
            return new HomeStarRatingBreakdown(context, attrs);
        }
    };
    public static final DLSComponent<IconRow> IconRow = new DLSComponent(IconRow.class, DLSComponentType.Core, "IconRow", null, TeamOwner.DLS, R.layout.n2_view_holder_icon_row) {
        public DLSMockAdapter<IconRow> mockAdapter() {
            return new IconRowMockAdapter();
        }

        public IconRow createView(Context context, AttributeSet attrs) {
            return new IconRow(context, attrs);
        }
    };
    public static final DLSComponent<ImagePreviewRow> ImagePreviewRow = new DLSComponent(ImagePreviewRow.class, DLSComponentType.Team, "ImagePreviewRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_image_preview_row) {
        public DLSMockAdapter<ImagePreviewRow> mockAdapter() {
            return new ImagePreviewRowMockAdapter();
        }

        public ImagePreviewRow createView(Context context, AttributeSet attrs) {
            return new ImagePreviewRow(context, attrs);
        }
    };
    public static final DLSComponent<ImageRow> ImageRow = new DLSComponent(ImageRow.class, DLSComponentType.Core, "ImageRow", null, TeamOwner.DLS, R.layout.n2_view_holder_image_row) {
        public DLSMockAdapter<ImageRow> mockAdapter() {
            return new ImageRowMockAdapter();
        }

        public ImageRow createView(Context context, AttributeSet attrs) {
            return new ImageRow(context, attrs);
        }
    };
    public static final DLSComponent<ImageWithButtonRow> ImageWithButtonRow = new DLSComponent(ImageWithButtonRow.class, DLSComponentType.Team, "ImageWithButtonRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_image_with_button_row) {
        public DLSMockAdapter<ImageWithButtonRow> mockAdapter() {
            return new ImageWithButtonRowMockAdapter();
        }

        public ImageWithButtonRow createView(Context context, AttributeSet attrs) {
            return new ImageWithButtonRow(context, attrs);
        }
    };
    public static final DLSComponent<ImpactDisplayCard> ImpactDisplayCard = new DLSComponent(ImpactDisplayCard.class, DLSComponentType.Deprecated, "ImpactDisplayCard", " Card component used to display an image with text atop the image\n", TeamOwner.DLS, R.layout.n2_view_holder_impact_display_card) {
        public DLSMockAdapter<ImpactDisplayCard> mockAdapter() {
            return new ImpactDisplayCardMockAdapter();
        }

        public ImpactDisplayCard createView(Context context, AttributeSet attrs) {
            return new ImpactDisplayCard(context, attrs);
        }
    };
    public static final DLSComponent<ImpactMarquee> ImpactMarquee = new DLSComponent(ImpactMarquee.class, DLSComponentType.Deprecated, "ImpactMarquee", null, TeamOwner.DLS, R.layout.n2_view_holder_impact_marquee) {
        public DLSMockAdapter<ImpactMarquee> mockAdapter() {
            return new ImpactMarqueeMockAdapter();
        }

        public ImpactMarquee createView(Context context, AttributeSet attrs) {
            return new ImpactMarquee(context, attrs);
        }
    };
    public static final DLSComponent<InfiniteDotIndicator> InfiniteDotIndicator = new DLSComponent(InfiniteDotIndicator.class, DLSComponentType.Team, "InfiniteDotIndicator", " Infinite Dot Indicator intended to be used with RecyclerViews (preferably Carousel) or ViewPager with many/possibly infinite items or pages.\n The dots shrink when going out of view and grow when coming into view.\n It can be used interchangeably with a ViewPager or RecyclerView dynamically with no overhead.\n Colors (active/inactive) and large dot count can be customized.\n <p>\n Currently does not support padding.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_infinite_dot_indicator) {
        public DLSMockAdapter<InfiniteDotIndicator> mockAdapter() {
            return new InfiniteDotIndicatorMockAdapter();
        }

        public InfiniteDotIndicator createView(Context context, AttributeSet attrs) {
            return new InfiniteDotIndicator(context, attrs);
        }
    };
    public static final DLSComponent<InfoActionRow> InfoActionRow = new DLSComponent(InfoActionRow.class, DLSComponentType.Core, "InfoActionRow", " Clicking on this row will bring up other pages in the flow\n\n @see InfoRow\n", TeamOwner.DLS, R.layout.n2_view_holder_info_action_row) {
        public DLSMockAdapter<InfoActionRow> mockAdapter() {
            return new InfoActionRowMockAdapter();
        }

        public InfoActionRow createView(Context context, AttributeSet attrs) {
            return new InfoActionRow(context, attrs);
        }
    };
    public static final DLSComponent<InfoPanelRow> InfoPanelRow = new DLSComponent(InfoPanelRow.class, DLSComponentType.Team, "InfoPanelRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_info_panel_row) {
        public DLSMockAdapter<InfoPanelRow> mockAdapter() {
            return new InfoPanelRowMockAdapter();
        }

        public InfoPanelRow createView(Context context, AttributeSet attrs) {
            return new InfoPanelRow(context, attrs);
        }
    };
    public static final DLSComponent<InfoRow> InfoRow = new DLSComponent(InfoRow.class, DLSComponentType.Core, "InfoRow", " @see InfoActionRow\n", TeamOwner.DLS, R.layout.n2_view_holder_info_row) {
        public DLSMockAdapter<InfoRow> mockAdapter() {
            return new InfoRowMockAdapter();
        }

        public InfoRow createView(Context context, AttributeSet attrs) {
            return new InfoRow(context, attrs);
        }
    };
    public static final DLSComponent<InlineCaution> InlineCaution = new DLSComponent(InlineCaution.class, DLSComponentType.Team, "InlineCaution", null, TeamOwner.CHINA, R.layout.n2_view_holder_inline_caution) {
        public DLSMockAdapter<InlineCaution> mockAdapter() {
            return new InlineCautionMockAdapter();
        }

        public InlineCaution createView(Context context, AttributeSet attrs) {
            return new InlineCaution(context, attrs);
        }
    };
    public static final DLSComponent<InlineContext> InlineContext = new DLSComponent(InlineContext.class, DLSComponentType.Core, "InlineContext", null, TeamOwner.DLS, R.layout.n2_view_holder_inline_context) {
        public DLSMockAdapter<InlineContext> mockAdapter() {
            return new InlineContextMockAdapter();
        }

        public InlineContext createView(Context context, AttributeSet attrs) {
            return new InlineContext(context, attrs);
        }
    };
    public static final DLSComponent<InlineFormattedIntegerInputRow> InlineFormattedIntegerInputRow = new DLSComponent(InlineFormattedIntegerInputRow.class, DLSComponentType.Team, "InlineFormattedIntegerInputRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_inline_formatted_integer_input_row) {
        public DLSMockAdapter<InlineFormattedIntegerInputRow> mockAdapter() {
            return new InlineFormattedIntegerInputRowMockAdapter();
        }

        public InlineFormattedIntegerInputRow createView(Context context, AttributeSet attrs) {
            return new InlineFormattedIntegerInputRow(context, attrs);
        }
    };
    public static final DLSComponent<InlineInputRow> InlineInputRow = new DLSComponent(InlineInputRow.class, DLSComponentType.Core, "InlineInputRow", " @see InlineMultilineInputRow\n", TeamOwner.DLS, R.layout.n2_view_holder_inline_input_row) {
        public DLSMockAdapter<InlineInputRow> mockAdapter() {
            return new InlineInputRowMockAdapter();
        }

        public InlineInputRow createView(Context context, AttributeSet attrs) {
            return new InlineInputRow(context, attrs);
        }
    };
    public static final DLSComponent<InlineInputWithContactPickerRow> InlineInputWithContactPickerRow = new DLSComponent(InlineInputWithContactPickerRow.class, DLSComponentType.Team, "InlineInputWithContactPickerRow", null, TeamOwner.DLS, R.layout.n2_view_holder_inline_input_with_contact_picker_row) {
        public DLSMockAdapter<InlineInputWithContactPickerRow> mockAdapter() {
            return new InlineInputWithContactPickerRowMockAdapter();
        }

        public InlineInputWithContactPickerRow createView(Context context, AttributeSet attrs) {
            return new InlineInputWithContactPickerRow(context, attrs);
        }
    };
    public static final DLSComponent<InlineMultilineInputRow> InlineMultilineInputRow = new DLSComponent(InlineMultilineInputRow.class, DLSComponentType.Core, "InlineMultilineInputRow", " @see InlineInputRow\n", TeamOwner.DLS, R.layout.n2_view_holder_inline_multiline_input_row) {
        public DLSMockAdapter<InlineMultilineInputRow> mockAdapter() {
            return new InlineMultilineInputRowMockAdapter();
        }

        public InlineMultilineInputRow createView(Context context, AttributeSet attrs) {
            return new InlineMultilineInputRow(context, attrs);
        }
    };
    public static final DLSComponent<InlineTipRow> InlineTipRow = new DLSComponent(InlineTipRow.class, DLSComponentType.Team, "InlineTipRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_inline_tip_row) {
        public DLSMockAdapter<InlineTipRow> mockAdapter() {
            return new InlineTipRowMockAdapter();
        }

        public InlineTipRow createView(Context context, AttributeSet attrs) {
            return new InlineTipRow(context, attrs);
        }
    };
    public static final DLSComponent<InputField> InputField = new DLSComponent(InputField.class, DLSComponentType.Core, "InputField", " TODO(nathanael-silverman)  Add \"success\" functionality\n\n @see InlineInputRow\n @see InlineMultilineInputRow\n", TeamOwner.DLS, R.layout.n2_view_holder_input_field) {
        public DLSMockAdapter<InputField> mockAdapter() {
            return new InputFieldMockAdapter();
        }

        public InputField createView(Context context, AttributeSet attrs) {
            return new InputField(context, attrs);
        }
    };
    public static final DLSComponent<InputMarquee> InputMarquee = new DLSComponent(InputMarquee.class, DLSComponentType.Core, "InputMarquee", null, TeamOwner.DLS, R.layout.n2_view_holder_input_marquee) {
        public DLSMockAdapter<InputMarquee> mockAdapter() {
            return new InputMarqueeMockAdapter();
        }

        public InputMarquee createView(Context context, AttributeSet attrs) {
            return new InputMarquee(context, attrs);
        }
    };
    public static final DLSComponent<InputSuggestionActionRow> InputSuggestionActionRow = new DLSComponent(InputSuggestionActionRow.class, DLSComponentType.Core, "InputSuggestionActionRow", " InputSuggestionActionRow component which is used as a row that can provide a title, a subtitle, and a label. The subtitle and label are used to provide more\n context to the information that the title provides. For example, it can be used for search suggestions to describe the suggestion.\n", TeamOwner.DLS, R.layout.n2_view_holder_input_suggestion_action_row) {
        public DLSMockAdapter<InputSuggestionActionRow> mockAdapter() {
            return new InputSuggestionActionRowMockAdapter();
        }

        public InputSuggestionActionRow createView(Context context, AttributeSet attrs) {
            return new InputSuggestionActionRow(context, attrs);
        }
    };
    public static final DLSComponent<InputSuggestionActionRowChina> InputSuggestionActionRowChina = new DLSComponent(InputSuggestionActionRowChina.class, DLSComponentType.Team, "InputSuggestionActionRowChina", null, TeamOwner.CHINA, R.layout.n2_view_holder_input_suggestion_action_row_china) {
        public DLSMockAdapter<InputSuggestionActionRowChina> mockAdapter() {
            return new InputSuggestionActionRowChinaMockAdapter();
        }

        public InputSuggestionActionRowChina createView(Context context, AttributeSet attrs) {
            return new InputSuggestionActionRowChina(context, attrs);
        }
    };
    public static final DLSComponent<InputSuggestionSubRow> InputSuggestionSubRow = new DLSComponent(InputSuggestionSubRow.class, DLSComponentType.Team, "InputSuggestionSubRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_input_suggestion_sub_row) {
        public DLSMockAdapter<InputSuggestionSubRow> mockAdapter() {
            return new InputSuggestionSubRowMockAdapter();
        }

        public InputSuggestionSubRow createView(Context context, AttributeSet attrs) {
            return new InputSuggestionSubRow(context, attrs);
        }
    };
    public static final DLSComponent<InquiryCard> InquiryCard = new DLSComponent(InquiryCard.class, DLSComponentType.Team, "InquiryCard", null, TeamOwner.HOMES, R.layout.n2_view_holder_inquiry_card) {
        public DLSMockAdapter<InquiryCard> mockAdapter() {
            return new InquiryCardMockAdapter();
        }

        public InquiryCard createView(Context context, AttributeSet attrs) {
            return new InquiryCard(context, attrs);
        }
    };
    public static final DLSComponent<IntegerFormatInputView> IntegerFormatInputView = new DLSComponent(IntegerFormatInputView.class, DLSComponentType.Team, "IntegerFormatInputView", " A text edit field that is used for formatted integer input such as percentages or currencies. This provides the ability to format integer content\n while maintaining position and disallowing selection of special symbols (restricts selection to between the start and end of the integer input).\n", TeamOwner.HOMES, R.layout.n2_view_holder_integer_format_input_view) {
        public DLSMockAdapter<IntegerFormatInputView> mockAdapter() {
            return new IntegerFormatInputViewMockAdapter();
        }

        public IntegerFormatInputView createView(Context context, AttributeSet attrs) {
            return new IntegerFormatInputView(context, attrs);
        }
    };
    public static final DLSComponent<Interstitial> Interstitial = new DLSComponent(Interstitial.class, DLSComponentType.Core, "Interstitial", " Implements the Interstitial DLS component\n\n Inline item displayed as a callout in a list such as \"golden ticket\" suggested filters in Find.\n", TeamOwner.DLS, R.layout.n2_view_holder_interstitial) {
        public DLSMockAdapter<Interstitial> mockAdapter() {
            return new InterstitialMockAdapter();
        }

        public Interstitial createView(Context context, AttributeSet attrs) {
            return new Interstitial(context, attrs);
        }
    };
    public static final DLSComponent<InviteRow> InviteRow = new DLSComponent(InviteRow.class, DLSComponentType.Team, "InviteRow", null, TeamOwner.GROWTH, R.layout.n2_view_holder_invite_row) {
        public DLSMockAdapter<InviteRow> mockAdapter() {
            return new InviteRowMockAdapter();
        }

        public InviteRow createView(Context context, AttributeSet attrs) {
            return new InviteRow(context, attrs);
        }
    };
    public static final DLSComponent<KickerMarquee> KickerMarquee = new DLSComponent(KickerMarquee.class, DLSComponentType.Team, "KickerMarquee", " This is the same as a SheetMarquee but has a breadcrumb/kicker at the top left.\n e.g. 1 of 4 steps\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_kicker_marquee) {
        public DLSMockAdapter<KickerMarquee> mockAdapter() {
            return new KickerMarqueeMockAdapter();
        }

        public KickerMarquee createView(Context context, AttributeSet attrs) {
            return new KickerMarquee(context, attrs);
        }
    };
    public static final DLSComponent<LabelDocumentMarquee> LabelDocumentMarquee = new DLSComponent(LabelDocumentMarquee.class, DLSComponentType.Team, "LabelDocumentMarquee", null, TeamOwner.HOMES, R.layout.n2_view_holder_label_document_marquee) {
        public DLSMockAdapter<LabelDocumentMarquee> mockAdapter() {
            return new LabelDocumentMarqueeMockAdapter();
        }

        public LabelDocumentMarquee createView(Context context, AttributeSet attrs) {
            return new LabelDocumentMarquee(context, attrs);
        }
    };
    public static final DLSComponent<LabelRow> LabelRow = new DLSComponent(LabelRow.class, DLSComponentType.Team, "LabelRow", " LabelRow component which is used as a row that can provide a title, a subtitle, and a label (at the end of the row).\n", TeamOwner.PAYMENTS, R.layout.n2_view_holder_label_row) {
        public DLSMockAdapter<LabelRow> mockAdapter() {
            return new LabelRowMockAdapter();
        }

        public LabelRow createView(Context context, AttributeSet attrs) {
            return new LabelRow(context, attrs);
        }
    };
    public static final DLSComponent<LabeledSectionRow> LabeledSectionRow = new DLSComponent(LabeledSectionRow.class, DLSComponentType.Team, "LabeledSectionRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_labeled_section_row) {
        public DLSMockAdapter<LabeledSectionRow> mockAdapter() {
            return new LabeledSectionRowMockAdapter();
        }

        public LabeledSectionRow createView(Context context, AttributeSet attrs) {
            return new LabeledSectionRow(context, attrs);
        }
    };
    public static final DLSComponent<LargeIconRow> LargeIconRow = new DLSComponent(LargeIconRow.class, DLSComponentType.Team, "LargeIconRow", " A simple row with a large, left-aligned icon\n", TeamOwner.HOMES, R.layout.n2_view_holder_large_icon_row) {
        public DLSMockAdapter<LargeIconRow> mockAdapter() {
            return new LargeIconRowMockAdapter();
        }

        public LargeIconRow createView(Context context, AttributeSet attrs) {
            return new LargeIconRow(context, attrs);
        }
    };
    public static final DLSComponent<LeftAlignedImageRow> LeftAlignedImageRow = new DLSComponent(LeftAlignedImageRow.class, DLSComponentType.Team, "LeftAlignedImageRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_left_aligned_image_row) {
        public DLSMockAdapter<LeftAlignedImageRow> mockAdapter() {
            return new LeftAlignedImageRowMockAdapter();
        }

        public LeftAlignedImageRow createView(Context context, AttributeSet attrs) {
            return new LeftAlignedImageRow(context, attrs);
        }
    };
    public static final DLSComponent<LeftIconRow> LeftIconRow = new DLSComponent(LeftIconRow.class, DLSComponentType.Team, "LeftIconRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_left_icon_row) {
        public DLSMockAdapter<LeftIconRow> mockAdapter() {
            return new LeftIconRowMockAdapter();
        }

        public LeftIconRow createView(Context context, AttributeSet attrs) {
            return new LeftIconRow(context, attrs);
        }
    };
    public static final DLSComponent<LinkActionRow> LinkActionRow = new DLSComponent(LinkActionRow.class, DLSComponentType.Core, "LinkActionRow", " One of many Row components used for displaying a small amount of text in a Row. This particular Row is meant to serve as a button/link elsewhere in\n the app. End users are expected to call setOnClickListener(OnClickListener) on a particular {@link LinkActionRow} to receive click events\n", TeamOwner.DLS, R.layout.n2_view_holder_link_action_row) {
        public DLSMockAdapter<LinkActionRow> mockAdapter() {
            return new LinkActionRowMockAdapter();
        }

        public LinkActionRow createView(Context context, AttributeSet attrs) {
            return new LinkActionRow(context, attrs);
        }
    };
    public static final DLSComponent<LinkableLegalTextRow> LinkableLegalTextRow = new DLSComponent(LinkableLegalTextRow.class, DLSComponentType.Team, "LinkableLegalTextRow", " Row that is used in Quick Pay to display Terms and Conditions to the user.  It is similar to the\n {@link StandardRow}, except this Row has linkable/clickable text\n", TeamOwner.PAYMENTS, R.layout.n2_view_holder_linkable_legal_text_row) {
        public DLSMockAdapter<LinkableLegalTextRow> mockAdapter() {
            return new LinkableLegalTextRowMockAdapter();
        }

        public LinkableLegalTextRow createView(Context context, AttributeSet attrs) {
            return new LinkableLegalTextRow(context, attrs);
        }
    };
    public static final DLSComponent<ListingDescription> ListingDescription = new DLSComponent(ListingDescription.class, DLSComponentType.Team, "ListingDescription", " View that presents some information relevant ot a listing\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_listing_description) {
        public DLSMockAdapter<ListingDescription> mockAdapter() {
            return new ListingDescriptionMockAdapter();
        }

        public ListingDescription createView(Context context, AttributeSet attrs) {
            return new ListingDescription(context, attrs);
        }
    };
    public static final DLSComponent<ListingInfoCardRow> ListingInfoCardRow = new DLSComponent(ListingInfoCardRow.class, DLSComponentType.Team, "ListingInfoCardRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_listing_info_card_row) {
        public DLSMockAdapter<ListingInfoCardRow> mockAdapter() {
            return new ListingInfoCardRowMockAdapter();
        }

        public ListingInfoCardRow createView(Context context, AttributeSet attrs) {
            return new ListingInfoCardRow(context, attrs);
        }
    };
    public static final DLSComponent<ListingInfoRow> ListingInfoRow = new DLSComponent(ListingInfoRow.class, DLSComponentType.Team, "ListingInfoRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_listing_info_row) {
        public DLSMockAdapter<ListingInfoRow> mockAdapter() {
            return new ListingInfoRowMockAdapter();
        }

        public ListingInfoRow createView(Context context, AttributeSet attrs) {
            return new ListingInfoRow(context, attrs);
        }
    };
    public static final DLSComponent<LoadingText> LoadingText = new DLSComponent(LoadingText.class, DLSComponentType.Team, "LoadingText", null, TeamOwner.CHINA, R.layout.n2_view_holder_loading_text) {
        public DLSMockAdapter<LoadingText> mockAdapter() {
            return new LoadingTextMockAdapter();
        }

        public LoadingText createView(Context context, AttributeSet attrs) {
            return new LoadingText(context, attrs);
        }
    };
    public static final DLSComponent<LocationContextCard> LocationContextCard = new DLSComponent(LocationContextCard.class, DLSComponentType.Team, "LocationContextCard", " A card used to introduce location information used in p2.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_location_context_card) {
        public DLSMockAdapter<LocationContextCard> mockAdapter() {
            return new LocationContextCardMockAdapter();
        }

        public LocationContextCard createView(Context context, AttributeSet attrs) {
            return new LocationContextCard(context, attrs);
        }
    };
    public static final DLSComponent<MapInterstitial> MapInterstitial = new DLSComponent(MapInterstitial.class, DLSComponentType.Core, "MapInterstitial", " DLS spec map view. This serves for both Precise and Imprecise map interstitial.\n", TeamOwner.DLS, R.layout.n2_view_holder_map_interstitial) {
        public DLSMockAdapter<MapInterstitial> mockAdapter() {
            return new MapInterstitialMockAdapter();
        }

        public MapInterstitial createView(Context context, AttributeSet attrs) {
            return new MapInterstitial(context, attrs);
        }
    };
    public static final DLSComponent<MapSearchButton> MapSearchButton = new DLSComponent(MapSearchButton.class, DLSComponentType.Core, "MapSearchButton", " Refresh button for map that is a babu pill on the top of the screen.\n", TeamOwner.DLS, R.layout.n2_view_holder_map_search_button) {
        public DLSMockAdapter<MapSearchButton> mockAdapter() {
            return new MapSearchButtonMockAdapter();
        }

        public MapSearchButton createView(Context context, AttributeSet attrs) {
            return new MapSearchButton(context, attrs);
        }
    };
    public static final DLSComponent<MarketingCard> MarketingCard = new DLSComponent(MarketingCard.class, DLSComponentType.Team, "MarketingCard", " Marketing Card component used to display an image with a title, a subtitle caption and a call to action below\n", TeamOwner.GROWTH, R.layout.n2_view_holder_marketing_card) {
        public DLSMockAdapter<MarketingCard> mockAdapter() {
            return new MarketingCardMockAdapter();
        }

        public MarketingCard createView(Context context, AttributeSet attrs) {
            return new MarketingCard(context, attrs);
        }
    };
    public static final DLSComponent<MessageInputOneRow> MessageInputOneRow = new DLSComponent(MessageInputOneRow.class, DLSComponentType.Team, "MessageInputOneRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_message_input_one_row) {
        public DLSMockAdapter<MessageInputOneRow> mockAdapter() {
            return new MessageInputOneRowMockAdapter();
        }

        public MessageInputOneRow createView(Context context, AttributeSet attrs) {
            return new MessageInputOneRow(context, attrs);
        }
    };
    public static final DLSComponent<MessageInputTwoRows> MessageInputTwoRows = new DLSComponent(MessageInputTwoRows.class, DLSComponentType.Team, "MessageInputTwoRows", null, TeamOwner.HOMES, R.layout.n2_view_holder_message_input_two_rows) {
        public DLSMockAdapter<MessageInputTwoRows> mockAdapter() {
            return new MessageInputTwoRowsMockAdapter();
        }

        public MessageInputTwoRows createView(Context context, AttributeSet attrs) {
            return new MessageInputTwoRows(context, attrs);
        }
    };
    public static final DLSComponent<MessageTranslationRow> MessageTranslationRow = new DLSComponent(MessageTranslationRow.class, DLSComponentType.Team, "MessageTranslationRow", null, TeamOwner.UNKNOWN, R.layout.n2_view_holder_message_translation_row) {
        public DLSMockAdapter<MessageTranslationRow> mockAdapter() {
            return new MessageTranslationRowMockAdapter();
        }

        public MessageTranslationRow createView(Context context, AttributeSet attrs) {
            return new MessageTranslationRow(context, attrs);
        }
    };
    public static final DLSComponent<MicroDisplayCard> MicroDisplayCard = new DLSComponent(MicroDisplayCard.class, DLSComponentType.Deprecated, "MicroDisplayCard", " Card component to display a smaller card with text atop it. End users are expected to give the card a height, it's width will scale according\n to it's aspect ratio.\n", TeamOwner.DLS, R.layout.n2_view_holder_micro_display_card) {
        public DLSMockAdapter<MicroDisplayCard> mockAdapter() {
            return new MicroDisplayCardMockAdapter();
        }

        public MicroDisplayCard createView(Context context, AttributeSet attrs) {
            return new MicroDisplayCard(context, attrs);
        }
    };
    public static final DLSComponent<MicroRow> MicroRow = new DLSComponent(MicroRow.class, DLSComponentType.Core, "MicroRow", " One of many Row components used for displaying a small amount of text in a Row\n", TeamOwner.DLS, R.layout.n2_view_holder_micro_row) {
        public DLSMockAdapter<MicroRow> mockAdapter() {
            return new MicroRowMockAdapter();
        }

        public MicroRow createView(Context context, AttributeSet attrs) {
            return new MicroRow(context, attrs);
        }
    };
    public static final DLSComponent<MicroSectionHeader> MicroSectionHeader = new DLSComponent(MicroSectionHeader.class, DLSComponentType.Core, "MicroSectionHeader", " Same as {@link SectionHeader} but with a smaller title\n\n @see SectionHeader\n", TeamOwner.DLS, R.layout.n2_view_holder_micro_section_header) {
        public DLSMockAdapter<MicroSectionHeader> mockAdapter() {
            return new MicroSectionHeaderMockAdapter();
        }

        public MicroSectionHeader createView(Context context, AttributeSet attrs) {
            return new MicroSectionHeader(context, attrs);
        }
    };
    public static final DLSComponent<MosaicCard> MosaicCard = new DLSComponent(MosaicCard.class, DLSComponentType.Core, "MosaicCard", " DLS card for Guidebook place collections, called Albums.\n", TeamOwner.DLS, R.layout.n2_view_holder_mosaic_card) {
        public DLSMockAdapter<MosaicCard> mockAdapter() {
            return new MosaicCardMockAdapter();
        }

        public MosaicCard createView(Context context, AttributeSet attrs) {
            return new MosaicCard(context, attrs);
        }
    };
    public static final DLSComponent<MultiLineSplitRow> MultiLineSplitRow = new DLSComponent(MultiLineSplitRow.class, DLSComponentType.Team, "MultiLineSplitRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_multi_line_split_row) {
        public DLSMockAdapter<MultiLineSplitRow> mockAdapter() {
            return new MultiLineSplitRowMockAdapter();
        }

        public MultiLineSplitRow createView(Context context, AttributeSet attrs) {
            return new MultiLineSplitRow(context, attrs);
        }
    };
    public static final DLSComponent<NavigationPill> NavigationPill = new DLSComponent(NavigationPill.class, DLSComponentType.Team, "NavigationPill", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_navigation_pill) {
        public DLSMockAdapter<NavigationPill> mockAdapter() {
            return new NavigationPillMockAdapter();
        }

        public NavigationPill createView(Context context, AttributeSet attrs) {
            return new NavigationPill(context, attrs);
        }
    };
    public static final DLSComponent<NestedListingChildRow> NestedListingChildRow = new DLSComponent(NestedListingChildRow.class, DLSComponentType.Team, "NestedListingChildRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_nested_listing_child_row) {
        public DLSMockAdapter<NestedListingChildRow> mockAdapter() {
            return new NestedListingChildRowMockAdapter();
        }

        public NestedListingChildRow createView(Context context, AttributeSet attrs) {
            return new NestedListingChildRow(context, attrs);
        }
    };
    public static final DLSComponent<NestedListingEditRow> NestedListingEditRow = new DLSComponent(NestedListingEditRow.class, DLSComponentType.Team, "NestedListingEditRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_nested_listing_edit_row) {
        public DLSMockAdapter<NestedListingEditRow> mockAdapter() {
            return new NestedListingEditRowMockAdapter();
        }

        public NestedListingEditRow createView(Context context, AttributeSet attrs) {
            return new NestedListingEditRow(context, attrs);
        }
    };
    public static final DLSComponent<NestedListingRow> NestedListingRow = new DLSComponent(NestedListingRow.class, DLSComponentType.Team, "NestedListingRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_nested_listing_row) {
        public DLSMockAdapter<NestedListingRow> mockAdapter() {
            return new NestedListingRowMockAdapter();
        }

        public NestedListingRow createView(Context context, AttributeSet attrs) {
            return new NestedListingRow(context, attrs);
        }
    };
    public static final DLSComponent<NestedListingToggleRow> NestedListingToggleRow = new DLSComponent(NestedListingToggleRow.class, DLSComponentType.Team, "NestedListingToggleRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_nested_listing_toggle_row) {
        public DLSMockAdapter<NestedListingToggleRow> mockAdapter() {
            return new NestedListingToggleRowMockAdapter();
        }

        public NestedListingToggleRow createView(Context context, AttributeSet attrs) {
            return new NestedListingToggleRow(context, attrs);
        }
    };
    public static final DLSComponent<NoProfilePhotoDetailsSummary> NoProfilePhotoDetailsSummary = new DLSComponent(NoProfilePhotoDetailsSummary.class, DLSComponentType.Team, "NoProfilePhotoDetailsSummary", " View that has a few fields for a summary of listing information including a couple text fields indicating the type of listing, the host and an\n image of the host.\n", TeamOwner.HOMES, R.layout.n2_view_holder_no_profile_photo_details_summary) {
        public DLSMockAdapter<NoProfilePhotoDetailsSummary> mockAdapter() {
            return new NoProfilePhotoDetailsSummaryMockAdapter();
        }

        public NoProfilePhotoDetailsSummary createView(Context context, AttributeSet attrs) {
            return new NoProfilePhotoDetailsSummary(context, attrs);
        }
    };
    public static final DLSComponent<NumberedSimpleTextRow> NumberedSimpleTextRow = new DLSComponent(NumberedSimpleTextRow.class, DLSComponentType.Team, "NumberedSimpleTextRow", " A Simple Text Row with a number\n", TeamOwner.TRUST, R.layout.n2_view_holder_numbered_simple_text_row) {
        public DLSMockAdapter<NumberedSimpleTextRow> mockAdapter() {
            return new NumberedSimpleTextRowMockAdapter();
        }

        public NumberedSimpleTextRow createView(Context context, AttributeSet attrs) {
            return new NumberedSimpleTextRow(context, attrs);
        }
    };
    public static final DLSComponent<NuxCoverCard> NuxCoverCard = new DLSComponent(NuxCoverCard.class, DLSComponentType.Team, "NuxCoverCard", null, TeamOwner.HOMES, R.layout.n2_view_holder_nux_cover_card) {
        public DLSMockAdapter<NuxCoverCard> mockAdapter() {
            return new NuxCoverCardMockAdapter();
        }

        public NuxCoverCard createView(Context context, AttributeSet attrs) {
            return new NuxCoverCard(context, attrs);
        }
    };
    public static final DLSComponent<ParticipantRow> ParticipantRow = new DLSComponent(ParticipantRow.class, DLSComponentType.Team, "ParticipantRow", " A row showing a user's picture with their name to the right of the picture. An optional \"X\" icon on the right allows them to be removed from the\n context of the row usage.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_participant_row) {
        public DLSMockAdapter<ParticipantRow> mockAdapter() {
            return new ParticipantRowMockAdapter();
        }

        public ParticipantRow createView(Context context, AttributeSet attrs) {
            return new ParticipantRow(context, attrs);
        }
    };
    public static final DLSComponent<PaymentInputLayout> PaymentInputLayout = new DLSComponent(PaymentInputLayout.class, DLSComponentType.Team, "PaymentInputLayout", null, TeamOwner.PAYMENTS, R.layout.n2_view_holder_payment_input_layout) {
        public DLSMockAdapter<PaymentInputLayout> mockAdapter() {
            return new PaymentInputLayoutMockAdapter();
        }

        public PaymentInputLayout createView(Context context, AttributeSet attrs) {
            return new PaymentInputLayout(context, attrs);
        }
    };
    public static final DLSComponent<PaymentOptionRow> PaymentOptionRow = new DLSComponent(PaymentOptionRow.class, DLSComponentType.Team, "PaymentOptionRow", null, TeamOwner.PAYMENTS, R.layout.n2_view_holder_payment_option_row) {
        public DLSMockAdapter<PaymentOptionRow> mockAdapter() {
            return new PaymentOptionRowMockAdapter();
        }

        public PaymentOptionRow createView(Context context, AttributeSet attrs) {
            return new PaymentOptionRow(context, attrs);
        }
    };
    public static final DLSComponent<PlaceCard> PlaceCard = new DLSComponent(PlaceCard.class, DLSComponentType.Core, "PlaceCard", " DLS Component to display guidebook places.\n", TeamOwner.DLS, R.layout.n2_view_holder_place_card) {
        public DLSMockAdapter<PlaceCard> mockAdapter() {
            return new PlaceCardMockAdapter();
        }

        public PlaceCard createView(Context context, AttributeSet attrs) {
            return new PlaceCard(context, attrs);
        }
    };
    public static final DLSComponent<PosterCard> PosterCard = new DLSComponent(PosterCard.class, DLSComponentType.Team, "PosterCard", " DLS component to display Magical Trip multi-day experiences as cards.\n", TeamOwner.TRIPS, R.layout.n2_view_holder_poster_card) {
        public DLSMockAdapter<PosterCard> mockAdapter() {
            return new PosterCardMockAdapter();
        }

        public PosterCard createView(Context context, AttributeSet attrs) {
            return new PosterCard(context, attrs);
        }
    };
    public static final DLSComponent<PosterRow> PosterRow = new DLSComponent(PosterRow.class, DLSComponentType.Team, "PosterRow", null, TeamOwner.PAYMENTS, R.layout.n2_view_holder_poster_row) {
        public DLSMockAdapter<PosterRow> mockAdapter() {
            return new PosterRowMockAdapter();
        }

        public PosterRow createView(Context context, AttributeSet attrs) {
            return new PosterRow(context, attrs);
        }
    };
    public static final DLSComponent<PriceFilterButtons> PriceFilterButtons = new DLSComponent(PriceFilterButtons.class, DLSComponentType.Team, "PriceFilterButtons", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_price_filter_buttons) {
        public DLSMockAdapter<PriceFilterButtons> mockAdapter() {
            return new PriceFilterButtonsMockAdapter();
        }

        public PriceFilterButtons createView(Context context, AttributeSet attrs) {
            return new PriceFilterButtons(context, attrs);
        }
    };
    public static final DLSComponent<PriceSummary> PriceSummary = new DLSComponent(PriceSummary.class, DLSComponentType.Core, "PriceSummary", null, TeamOwner.DLS, R.layout.n2_view_holder_price_summary) {
        public DLSMockAdapter<PriceSummary> mockAdapter() {
            return new PriceSummaryMockAdapter();
        }

        public PriceSummary createView(Context context, AttributeSet attrs) {
            return new PriceSummary(context, attrs);
        }
    };
    public static final DLSComponent<PricingBreakdownRow> PricingBreakdownRow = new DLSComponent(PricingBreakdownRow.class, DLSComponentType.Team, "PricingBreakdownRow", null, TeamOwner.PAYMENTS, R.layout.n2_view_holder_pricing_breakdown_row) {
        public DLSMockAdapter<PricingBreakdownRow> mockAdapter() {
            return new PricingBreakdownRowMockAdapter();
        }

        public PricingBreakdownRow createView(Context context, AttributeSet attrs) {
            return new PricingBreakdownRow(context, attrs);
        }
    };
    public static final DLSComponent<PrimaryButton> PrimaryButton = new DLSComponent(PrimaryButton.class, DLSComponentType.Deprecated, "PrimaryButton", null, TeamOwner.DLS, R.layout.n2_view_holder_primary_button) {
        public DLSMockAdapter<PrimaryButton> mockAdapter() {
            return new PrimaryButtonMockAdapter();
        }

        public PrimaryButton createView(Context context, AttributeSet attrs) {
            return new PrimaryButton(context, attrs);
        }
    };
    public static final DLSComponent<PrimaryTextBottomBar> PrimaryTextBottomBar = new DLSComponent(PrimaryTextBottomBar.class, DLSComponentType.Team, "PrimaryTextBottomBar", " A bottom bar with a button and an optional text.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_primary_text_bottom_bar) {
        public DLSMockAdapter<PrimaryTextBottomBar> mockAdapter() {
            return new PrimaryTextBottomBarMockAdapter();
        }

        public PrimaryTextBottomBar createView(Context context, AttributeSet attrs) {
            return new PrimaryTextBottomBar(context, attrs);
        }
    };
    public static final DLSComponent<ProfileCompletionBarRow> ProfileCompletionBarRow = new DLSComponent(ProfileCompletionBarRow.class, DLSComponentType.Team, "ProfileCompletionBarRow", " Similar to {@link BarRow} but the progress bar uses discrete steps rather than percentage\n\n @see BarRow\n", TeamOwner.GROWTH, R.layout.n2_view_holder_profile_completion_bar_row) {
        public DLSMockAdapter<ProfileCompletionBarRow> mockAdapter() {
            return new ProfileCompletionBarRowMockAdapter();
        }

        public ProfileCompletionBarRow createView(Context context, AttributeSet attrs) {
            return new ProfileCompletionBarRow(context, attrs);
        }
    };
    public static final DLSComponent<ProfileLinkRow> ProfileLinkRow = new DLSComponent(ProfileLinkRow.class, DLSComponentType.Team, "ProfileLinkRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_profile_link_row) {
        public DLSMockAdapter<ProfileLinkRow> mockAdapter() {
            return new ProfileLinkRowMockAdapter();
        }

        public ProfileLinkRow createView(Context context, AttributeSet attrs) {
            return new ProfileLinkRow(context, attrs);
        }
    };
    public static final DLSComponent<PromotionMarquee> PromotionMarquee = new DLSComponent(PromotionMarquee.class, DLSComponentType.Team, "PromotionMarquee", null, TeamOwner.HOMES, R.layout.n2_view_holder_promotion_marquee) {
        public DLSMockAdapter<PromotionMarquee> mockAdapter() {
            return new PromotionMarqueeMockAdapter();
        }

        public PromotionMarquee createView(Context context, AttributeSet attrs) {
            return new PromotionMarquee(context, attrs);
        }
    };
    public static final DLSComponent<RangeDisplay> RangeDisplay = new DLSComponent(RangeDisplay.class, DLSComponentType.Core, "RangeDisplay", " Component consisting of two two-line text fields separated by a slash.\n", TeamOwner.DLS, R.layout.n2_view_holder_range_display) {
        public DLSMockAdapter<RangeDisplay> mockAdapter() {
            return new RangeDisplayMockAdapter();
        }

        public RangeDisplay createView(Context context, AttributeSet attrs) {
            return new RangeDisplay(context, attrs);
        }
    };
    public static final DLSComponent<RearrangableLabeledPhotoCell> RearrangableLabeledPhotoCell = new DLSComponent(RearrangableLabeledPhotoCell.class, DLSComponentType.Team, "RearrangableLabeledPhotoCell", null, TeamOwner.HOMES, R.layout.n2_view_holder_rearrangable_labeled_photo_cell) {
        public DLSMockAdapter<RearrangableLabeledPhotoCell> mockAdapter() {
            return new RearrangableLabeledPhotoCellMockAdapter();
        }

        public RearrangableLabeledPhotoCell createView(Context context, AttributeSet attrs) {
            return new RearrangableLabeledPhotoCell(context, attrs);
        }
    };
    public static final DLSComponent<RecentSearchCard> RecentSearchCard = new DLSComponent(RecentSearchCard.class, DLSComponentType.Team, "RecentSearchCard", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_recent_search_card) {
        public DLSMockAdapter<RecentSearchCard> mockAdapter() {
            return new RecentSearchCardMockAdapter();
        }

        public RecentSearchCard createView(Context context, AttributeSet attrs) {
            return new RecentSearchCard(context, attrs);
        }
    };
    public static final DLSComponent<RecommendationCard> RecommendationCard = new DLSComponent(RecommendationCard.class, DLSComponentType.Team, "RecommendationCard", " DLS view for a card that is displayed on For You tab as a recommendation\n", TeamOwner.TRIPS, R.layout.n2_view_holder_recommendation_card) {
        public DLSMockAdapter<RecommendationCard> mockAdapter() {
            return new RecommendationCardMockAdapter();
        }

        public RecommendationCard createView(Context context, AttributeSet attrs) {
            return new RecommendationCard(context, attrs);
        }
    };
    public static final DLSComponent<RecommendationCardSquare> RecommendationCardSquare = new DLSComponent(RecommendationCardSquare.class, DLSComponentType.Team, "RecommendationCardSquare", null, TeamOwner.TRIPS, R.layout.n2_view_holder_recommendation_card_square) {
        public DLSMockAdapter<RecommendationCardSquare> mockAdapter() {
            return new RecommendationCardSquareMockAdapter();
        }

        public RecommendationCardSquare createView(Context context, AttributeSet attrs) {
            return new RecommendationCardSquare(context, attrs);
        }
    };
    public static final DLSComponent<RecommendationRow> RecommendationRow = new DLSComponent(RecommendationRow.class, DLSComponentType.Team, "RecommendationRow", " A container for {@link RecommendationCard}s. Only supports 2up and 3up rows.\n", TeamOwner.TRIPS, R.layout.n2_view_holder_recommendation_row) {
        public DLSMockAdapter<RecommendationRow> mockAdapter() {
            return new RecommendationRowMockAdapter();
        }

        public RecommendationRow createView(Context context, AttributeSet attrs) {
            return new RecommendationRow(context, attrs);
        }
    };
    public static final DLSComponent<RefreshLoader> RefreshLoader = new DLSComponent(RefreshLoader.class, DLSComponentType.Core, "RefreshLoader", null, TeamOwner.DLS, R.layout.n2_view_holder_refresh_loader) {
        public DLSMockAdapter<RefreshLoader> mockAdapter() {
            return new RefreshLoaderMockAdapter();
        }

        public RefreshLoader createView(Context context, AttributeSet attrs) {
            return new RefreshLoader(context, attrs);
        }
    };
    public static final DLSComponent<ReportableDetailsSummary> ReportableDetailsSummary = new DLSComponent(ReportableDetailsSummary.class, DLSComponentType.Team, "ReportableDetailsSummary", " View that has a few fields for a summary of listing information including a couple text fields indicating the type of listing, the host and an\n image of the host.\n", TeamOwner.HOMES, R.layout.n2_view_holder_reportable_details_summary) {
        public DLSMockAdapter<ReportableDetailsSummary> mockAdapter() {
            return new ReportableDetailsSummaryMockAdapter();
        }

        public ReportableDetailsSummary createView(Context context, AttributeSet attrs) {
            return new ReportableDetailsSummary(context, attrs);
        }
    };
    public static final DLSComponent<RequirementChecklistRow> RequirementChecklistRow = new DLSComponent(RequirementChecklistRow.class, DLSComponentType.Team, "RequirementChecklistRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_requirement_checklist_row) {
        public DLSMockAdapter<RequirementChecklistRow> mockAdapter() {
            return new RequirementChecklistRowMockAdapter();
        }

        public RequirementChecklistRow createView(Context context, AttributeSet attrs) {
            return new RequirementChecklistRow(context, attrs);
        }
    };
    public static final DLSComponent<ReviewBulletRow> ReviewBulletRow = new DLSComponent(ReviewBulletRow.class, DLSComponentType.Team, "ReviewBulletRow", null, TeamOwner.UNKNOWN, R.layout.n2_view_holder_review_bullet_row) {
        public DLSMockAdapter<ReviewBulletRow> mockAdapter() {
            return new ReviewBulletRowMockAdapter();
        }

        public ReviewBulletRow createView(Context context, AttributeSet attrs) {
            return new ReviewBulletRow(context, attrs);
        }
    };
    public static final DLSComponent<ReviewMarquee> ReviewMarquee = new DLSComponent(ReviewMarquee.class, DLSComponentType.Team, "ReviewMarquee", " Used as a header for reviews page. It shows the total number of reviews and the overall star rating\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_review_marquee) {
        public DLSMockAdapter<ReviewMarquee> mockAdapter() {
            return new ReviewMarqueeMockAdapter();
        }

        public ReviewMarquee createView(Context context, AttributeSet attrs) {
            return new ReviewMarquee(context, attrs);
        }
    };
    public static final DLSComponent<ScratchMicroRowWithRightText> ScratchMicroRowWithRightText = new DLSComponent(ScratchMicroRowWithRightText.class, DLSComponentType.Team, "ScratchMicroRowWithRightText", " One of many Row components used for displaying a small amount of text in a Row\n Micro row but allows text on the right side too\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_scratch_micro_row_with_right_text) {
        public DLSMockAdapter<ScratchMicroRowWithRightText> mockAdapter() {
            return new ScratchMicroRowWithRightTextMockAdapter();
        }

        public ScratchMicroRowWithRightText createView(Context context, AttributeSet attrs) {
            return new ScratchMicroRowWithRightText(context, attrs);
        }
    };
    public static final DLSComponent<SearchParamsRow> SearchParamsRow = new DLSComponent(SearchParamsRow.class, DLSComponentType.Team, "SearchParamsRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_search_params_row) {
        public DLSMockAdapter<SearchParamsRow> mockAdapter() {
            return new SearchParamsRowMockAdapter();
        }

        public SearchParamsRow createView(Context context, AttributeSet attrs) {
            return new SearchParamsRow(context, attrs);
        }
    };
    public static final DLSComponent<SearchSuggestionItem> SearchSuggestionItem = new DLSComponent(SearchSuggestionItem.class, DLSComponentType.Team, "SearchSuggestionItem", null, TeamOwner.CHINA, R.layout.n2_view_holder_search_suggestion_item) {
        public DLSMockAdapter<SearchSuggestionItem> mockAdapter() {
            return new SearchSuggestionItemMockAdapter();
        }

        public SearchSuggestionItem createView(Context context, AttributeSet attrs) {
            return new SearchSuggestionItem(context, attrs);
        }
    };
    public static final DLSComponent<SectionHeader> SectionHeader = new DLSComponent(SectionHeader.class, DLSComponentType.Core, "SectionHeader", " Used to separate sections within the page.\n <p>\n Two styles are available depending on if the header is the first of the section or not. Additional styles are supplied to make the button gray or\n babu. Gray is the default and should be used when the emphasis is on the content below the header. Babu when the button needs to be emphasized.\n <p>\n The button purposefully does not include a chevron as it doesn't fit Android's design guidelines.\n", TeamOwner.DLS, R.layout.n2_view_holder_section_header) {
        public DLSMockAdapter<SectionHeader> mockAdapter() {
            return new SectionHeaderMockAdapter();
        }

        public SectionHeader createView(Context context, AttributeSet attrs) {
            return new SectionHeader(context, attrs);
        }
    };
    public static final DLSComponent<SelectAmenityItem> SelectAmenityItem = new DLSComponent(SelectAmenityItem.class, DLSComponentType.Team, "SelectAmenityItem", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_amenity_item) {
        public DLSMockAdapter<SelectAmenityItem> mockAdapter() {
            return new SelectAmenityItemMockAdapter();
        }

        public SelectAmenityItem createView(Context context, AttributeSet attrs) {
            return new SelectAmenityItem(context, attrs);
        }
    };
    public static final DLSComponent<SelectBulletList> SelectBulletList = new DLSComponent(SelectBulletList.class, DLSComponentType.Team, "SelectBulletList", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_bullet_list) {
        public DLSMockAdapter<SelectBulletList> mockAdapter() {
            return new SelectBulletListMockAdapter();
        }

        public SelectBulletList createView(Context context, AttributeSet attrs) {
            return new SelectBulletList(context, attrs);
        }
    };
    public static final DLSComponent<SelectHomeRoomItem> SelectHomeRoomItem = new DLSComponent(SelectHomeRoomItem.class, DLSComponentType.Team, "SelectHomeRoomItem", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_home_room_item) {
        public DLSMockAdapter<SelectHomeRoomItem> mockAdapter() {
            return new SelectHomeRoomItemMockAdapter();
        }

        public SelectHomeRoomItem createView(Context context, AttributeSet attrs) {
            return new SelectHomeRoomItem(context, attrs);
        }
    };
    public static final DLSComponent<SelectHomeSummaryRow> SelectHomeSummaryRow = new DLSComponent(SelectHomeSummaryRow.class, DLSComponentType.Team, "SelectHomeSummaryRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_home_summary_row) {
        public DLSMockAdapter<SelectHomeSummaryRow> mockAdapter() {
            return new SelectHomeSummaryRowMockAdapter();
        }

        public SelectHomeSummaryRow createView(Context context, AttributeSet attrs) {
            return new SelectHomeSummaryRow(context, attrs);
        }
    };
    public static final DLSComponent<SelectHostRow> SelectHostRow = new DLSComponent(SelectHostRow.class, DLSComponentType.Team, "SelectHostRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_host_row) {
        public DLSMockAdapter<SelectHostRow> mockAdapter() {
            return new SelectHostRowMockAdapter();
        }

        public SelectHostRow createView(Context context, AttributeSet attrs) {
            return new SelectHostRow(context, attrs);
        }
    };
    public static final DLSComponent<SelectIconBulletRow> SelectIconBulletRow = new DLSComponent(SelectIconBulletRow.class, DLSComponentType.Team, "SelectIconBulletRow", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_icon_bullet_row) {
        public DLSMockAdapter<SelectIconBulletRow> mockAdapter() {
            return new SelectIconBulletRowMockAdapter();
        }

        public SelectIconBulletRow createView(Context context, AttributeSet attrs) {
            return new SelectIconBulletRow(context, attrs);
        }
    };
    public static final DLSComponent<SelectMapInterstitial> SelectMapInterstitial = new DLSComponent(SelectMapInterstitial.class, DLSComponentType.Team, "SelectMapInterstitial", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_map_interstitial) {
        public DLSMockAdapter<SelectMapInterstitial> mockAdapter() {
            return new SelectMapInterstitialMockAdapter();
        }

        public SelectMapInterstitial createView(Context context, AttributeSet attrs) {
            return new SelectMapInterstitial(context, attrs);
        }
    };
    public static final DLSComponent<SelectMarquee> SelectMarquee = new DLSComponent(SelectMarquee.class, DLSComponentType.Team, "SelectMarquee", null, TeamOwner.MARKETPLACE, R.layout.n2_view_holder_select_marquee) {
        public DLSMockAdapter<SelectMarquee> mockAdapter() {
            return new SelectMarqueeMockAdapter();
        }

        public SelectMarquee createView(Context context, AttributeSet attrs) {
            return new SelectMarquee(context, attrs);
        }
    };
    public static final DLSComponent<SheetFormattedIntegerInputText> SheetFormattedIntegerInputText = new DLSComponent(SheetFormattedIntegerInputText.class, DLSComponentType.Team, "SheetFormattedIntegerInputText", " Formatted integer input field for sheets - this allow price/percentage formats.\n", TeamOwner.HOMES, R.layout.n2_view_holder_sheet_formatted_integer_input_text) {
        public DLSMockAdapter<SheetFormattedIntegerInputText> mockAdapter() {
            return new SheetFormattedIntegerInputTextMockAdapter();
        }

        public SheetFormattedIntegerInputText createView(Context context, AttributeSet attrs) {
            return new SheetFormattedIntegerInputText(context, attrs);
        }
    };
    public static final DLSComponent<SheetInputText> SheetInputText = new DLSComponent(SheetInputText.class, DLSComponentType.Core, "SheetInputText", " If you can, use {@link InputField} instead.\n <p>\n Input field component on sheets with four mode.\n <p><ul>\n <li>Default\n <li>Password\n <li>AutoComplete\n <li>NonEditable\n </ul><p>\n For Default,\n no extra change is needed, just apply style style=\"@style/n2_SheetInputText\"\n <p>\n For Password,<br>\n apply style=\"@style/n2_SheetInputText.Password\"\n and implement @onShowPasswordToggleListener if necessary\n <p>\n For AutoComplete,<br>\n apply style=\"@style/n2_SheetInputText.AutoComplete\"\n and implement @setAutoCompleteTextView with the list of options.\n <p>\n For NonEditable,<br>\n apply style=\"@style/n2_SheetInputText.NonEditable\"\n and implement @setOnClickListener if needed\n <p>\n Examples: SingleInputSheetFragment\n\n <p>\n State keeping:<br>\n If there're multiple SheetInputText widget on screen, the EditText will have the same ID and the state will be messed up.\n So we need to manually save/restore the input state.<br>\n Reference: http://stackoverflow.com/questions/4932179/saving-state-on-compound-view-widgets\n <p>\n TODO - Remove Sheet from the name now that multiple styles are supported.\n", TeamOwner.DLS, R.layout.n2_view_holder_sheet_input_text) {
        public DLSMockAdapter<SheetInputText> mockAdapter() {
            return new SheetInputTextMockAdapter();
        }

        public SheetInputText createView(Context context, AttributeSet attrs) {
            return new SheetInputText(context, attrs);
        }
    };
    public static final DLSComponent<SheetMarquee> SheetMarquee = new DLSComponent(SheetMarquee.class, DLSComponentType.Core, "SheetMarquee", " Component meant to be used when composing a sheet as needed. Includes a title and subtitle.\n <p>\n TODO - Remove Sheet from the name now that multiple styles are supported.\n", TeamOwner.DLS, R.layout.n2_view_holder_sheet_marquee) {
        public DLSMockAdapter<SheetMarquee> mockAdapter() {
            return new SheetMarqueeMockAdapter();
        }

        public SheetMarquee createView(Context context, AttributeSet attrs) {
            return new SheetMarquee(context, attrs);
        }
    };
    public static final DLSComponent<SheetProgressBar> SheetProgressBar = new DLSComponent(SheetProgressBar.class, DLSComponentType.Core, "SheetProgressBar", " Component meant to be used when composing a sheet as needed. Generally used at the top of the screen and updated via {@link #setProgress(float)}.\n", TeamOwner.DLS, R.layout.n2_view_holder_sheet_progress_bar) {
        public DLSMockAdapter<SheetProgressBar> mockAdapter() {
            return new SheetProgressBarMockAdapter();
        }

        public SheetProgressBar createView(Context context, AttributeSet attrs) {
            return new SheetProgressBar(context, attrs);
        }
    };
    public static final DLSComponent<SheetStepperRow> SheetStepperRow = new DLSComponent(SheetStepperRow.class, DLSComponentType.Core, "SheetStepperRow", null, TeamOwner.DLS, R.layout.n2_view_holder_sheet_stepper_row) {
        public DLSMockAdapter<SheetStepperRow> mockAdapter() {
            return new SheetStepperRowMockAdapter();
        }

        public SheetStepperRow createView(Context context, AttributeSet attrs) {
            return new SheetStepperRow(context, attrs);
        }
    };
    public static final DLSComponent<SimilarPlaylistCard> SimilarPlaylistCard = new DLSComponent(SimilarPlaylistCard.class, DLSComponentType.Team, "SimilarPlaylistCard", null, TeamOwner.TRIPS, R.layout.n2_view_holder_similar_playlist_card) {
        public DLSMockAdapter<SimilarPlaylistCard> mockAdapter() {
            return new SimilarPlaylistCardMockAdapter();
        }

        public SimilarPlaylistCard createView(Context context, AttributeSet attrs) {
            return new SimilarPlaylistCard(context, attrs);
        }
    };
    public static final DLSComponent<SimpleTextRow> SimpleTextRow = new DLSComponent(SimpleTextRow.class, DLSComponentType.Core, "SimpleTextRow", " Row that can be used to simply present text according to predefined styles. Comes with dividers. If you need\n expanding / collapsing text consider using {@link TextRow} instead\n\n @see TextRow\n @see SmallTextRow\n", TeamOwner.DLS, R.layout.n2_view_holder_simple_text_row) {
        public DLSMockAdapter<SimpleTextRow> mockAdapter() {
            return new SimpleTextRowMockAdapter();
        }

        public SimpleTextRow createView(Context context, AttributeSet attrs) {
            return new SimpleTextRow(context, attrs);
        }
    };
    public static final DLSComponent<SimpleTitleContentRow> SimpleTitleContentRow = new DLSComponent(SimpleTitleContentRow.class, DLSComponentType.Team, "SimpleTitleContentRow", null, TeamOwner.HOMES, R.layout.n2_view_holder_simple_title_content_row) {
        public DLSMockAdapter<SimpleTitleContentRow> mockAdapter() {
            return new SimpleTitleContentRowMockAdapter();
        }

        public SimpleTitleContentRow createView(Context context, AttributeSet attrs) {
            return new SimpleTitleContentRow(context, attrs);
        }
    };
    public static final DLSComponent<SmallMarquee> SmallMarquee = new DLSComponent(SmallMarquee.class, DLSComponentType.Deprecated, "SmallMarquee", " DLS spec marquee that is used as a heading for Listing related pages.\n", TeamOwner.DLS, R.layout.n2_view_holder_small_marquee) {
        public DLSMockAdapter<SmallMarquee> mockAdapter() {
            return new SmallMarqueeMockAdapter();
        }

        public SmallMarquee createView(Context context, AttributeSet attrs) {
            return new SmallMarquee(context, attrs);
        }
    };
    public static final DLSComponent<SmallSheetSwitchRow> SmallSheetSwitchRow = new DLSComponent(SmallSheetSwitchRow.class, DLSComponentType.Team, "SmallSheetSwitchRow", " Used as a regular switch. Has two states: checked or unchecked. E.g. used in tween to specify whether or not you are bringing pets.\n <p>\n Compared to the regular SwitchRow, this one is designe to be used to sheets and has a smaller switch than the original DLS designs.\n The design of the switch is also slightly different. The thumb is always white and has no space between it and the edge of the track.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_small_sheet_switch_row) {
        public DLSMockAdapter<SmallSheetSwitchRow> mockAdapter() {
            return new SmallSheetSwitchRowMockAdapter();
        }

        public SmallSheetSwitchRow createView(Context context, AttributeSet attrs) {
            return new SmallSheetSwitchRow(context, attrs);
        }
    };
    public static final DLSComponent<SmallSheetSwitchRowSwitch> SmallSheetSwitchRowSwitch = new DLSComponent(SmallSheetSwitchRowSwitch.class, DLSComponentType.Team, "SmallSheetSwitchRowSwitch", " Switch used inside of a SmallSheetSwitchRow.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_small_sheet_switch_row_switch) {
        public DLSMockAdapter<SmallSheetSwitchRowSwitch> mockAdapter() {
            return new SmallSheetSwitchRowSwitchMockAdapter();
        }

        public SmallSheetSwitchRowSwitch createView(Context context, AttributeSet attrs) {
            return new SmallSheetSwitchRowSwitch(context, attrs);
        }
    };
    public static final DLSComponent<SmallTextRow> SmallTextRow = new DLSComponent(SmallTextRow.class, DLSComponentType.Core, "SmallTextRow", " Same as {@link TextRow} but with a smaller font by default\n\n @see TextRow\n", TeamOwner.DLS, R.layout.n2_view_holder_small_text_row) {
        public DLSMockAdapter<SmallTextRow> mockAdapter() {
            return new SmallTextRowMockAdapter();
        }

        public SmallTextRow createView(Context context, AttributeSet attrs) {
            return new SmallTextRow(context, attrs);
        }
    };
    public static final DLSComponent<StandardButtonRow> StandardButtonRow = new DLSComponent(StandardButtonRow.class, DLSComponentType.Team, "StandardButtonRow", " StandardButtonRow with title, subtitle, a primary button and a secondary button.\n only one of the primary button and secondary button can be visible. By default, primary button is visible.\n When StandardButtonRow is disabled, title, subtitle turned to grey; both primary button and secondary button are invisible.\n", TeamOwner.UNKNOWN, R.layout.n2_view_holder_standard_button_row) {
        public DLSMockAdapter<StandardButtonRow> mockAdapter() {
            return new StandardButtonRowMockAdapter();
        }

        public StandardButtonRow createView(Context context, AttributeSet attrs) {
            return new StandardButtonRow(context, attrs);
        }
    };
    public static final DLSComponent<StandardRow> StandardRow = new DLSComponent(StandardRow.class, DLSComponentType.Deprecated, "StandardRow", " Please use one of the following classes instead:\n\n @see BasicRow\n @see IconRow\n @see InfoRow\n @see InfoActionRow\n", TeamOwner.DLS, R.layout.n2_view_holder_standard_row) {
        public DLSMockAdapter<StandardRow> mockAdapter() {
            return new StandardRowMockAdapter();
        }

        public StandardRow createView(Context context, AttributeSet attrs) {
            return new StandardRow(context, attrs);
        }
    };
    public static final DLSComponent<StandardRowWithLabel> StandardRowWithLabel = new DLSComponent(StandardRowWithLabel.class, DLSComponentType.Team, "StandardRowWithLabel", null, TeamOwner.HOMES, R.layout.n2_view_holder_standard_row_with_label) {
        public DLSMockAdapter<StandardRowWithLabel> mockAdapter() {
            return new StandardRowWithLabelMockAdapter();
        }

        public StandardRowWithLabel createView(Context context, AttributeSet attrs) {
            return new StandardRowWithLabel(context, attrs);
        }
    };
    public static final DLSComponent<StarRatingSummary> StarRatingSummary = new DLSComponent(StarRatingSummary.class, DLSComponentType.Core, "StarRatingSummary", " Implements the HomeStarRatingSummary DLS component\n\n TODO(nathanael-silverman) Is this deprecated?\n", TeamOwner.DLS, R.layout.n2_view_holder_star_rating_summary) {
        public DLSMockAdapter<StarRatingSummary> mockAdapter() {
            return new StarRatingSummaryMockAdapter();
        }

        public StarRatingSummary createView(Context context, AttributeSet attrs) {
            return new StarRatingSummary(context, attrs);
        }
    };
    public static final DLSComponent<StatusBanner> StatusBanner = new DLSComponent(StatusBanner.class, DLSComponentType.Core, "StatusBanner", null, TeamOwner.DLS, R.layout.n2_view_holder_status_banner) {
        public DLSMockAdapter<StatusBanner> mockAdapter() {
            return new StatusBannerMockAdapter();
        }

        public StatusBanner createView(Context context, AttributeSet attrs) {
            return new StatusBanner(context, attrs);
        }
    };
    public static final DLSComponent<StepperRow> StepperRow = new DLSComponent(StepperRow.class, DLSComponentType.Core, "StepperRow", null, TeamOwner.DLS, R.layout.n2_view_holder_stepper_row) {
        public DLSMockAdapter<StepperRow> mockAdapter() {
            return new StepperRowMockAdapter();
        }

        public StepperRow createView(Context context, AttributeSet attrs) {
            return new StepperRow(context, attrs);
        }
    };
    public static final DLSComponent<SummaryInterstitial> SummaryInterstitial = new DLSComponent(SummaryInterstitial.class, DLSComponentType.Team, "SummaryInterstitial", null, TeamOwner.HOMES, R.layout.n2_view_holder_summary_interstitial) {
        public DLSMockAdapter<SummaryInterstitial> mockAdapter() {
            return new SummaryInterstitialMockAdapter();
        }

        public SummaryInterstitial createView(Context context, AttributeSet attrs) {
            return new SummaryInterstitial(context, attrs);
        }
    };
    public static final DLSComponent<SwitchRow> SwitchRow = new DLSComponent(SwitchRow.class, DLSComponentType.Core, "SwitchRow", " Used as a regular switch. Has two states: checked or unchecked. E.g. used in tween to specify whether or not you are bringing pets\n", TeamOwner.DLS, R.layout.n2_view_holder_switch_row) {
        public DLSMockAdapter<SwitchRow> mockAdapter() {
            return new SwitchRowMockAdapter();
        }

        public SwitchRow createView(Context context, AttributeSet attrs) {
            return new SwitchRow(context, attrs);
        }
    };
    protected static final DLSComponent[] TEAM_CHINA = {ArticleSummaryRow, HomeCardChina, InlineCaution, InputSuggestionActionRowChina, LoadingText, SearchSuggestionItem};
    protected static final DLSComponent[] TEAM_DLS = {AirToolbar, BarRow, BasicRow, ButtonBar, DisplayCard, DocumentMarquee, EditorialMarquee, EntryMarquee, FixedActionFooter, FixedDualActionFooter, FixedFlowActionAdvanceFooter, FixedFlowActionFooter, HeroMarquee, HomeAmenities, HomeCard, HomeHighlights, HomeMarquee, HomeReviewRow, HomeStarRatingBreakdown, IconRow, ImageRow, ImpactDisplayCard, ImpactMarquee, InfoActionRow, InfoRow, InlineContext, InlineInputRow, InlineInputWithContactPickerRow, InlineMultilineInputRow, InputField, InputMarquee, InputSuggestionActionRow, Interstitial, LinkActionRow, MapInterstitial, MapSearchButton, MicroDisplayCard, MicroRow, MicroSectionHeader, MosaicCard, PlaceCard, PriceSummary, PrimaryButton, RangeDisplay, RefreshLoader, SectionHeader, SheetInputText, SheetMarquee, SheetProgressBar, SheetStepperRow, SimpleTextRow, SmallMarquee, SmallTextRow, StandardRow, StarRatingSummary, StatusBanner, StepperRow, SwitchRow, TextRow, ThreadPreviewRow, ToggleActionRow, TweenRow, UserDetailsActionRow};
    protected static final DLSComponent[] TEAM_GROWTH = {InviteRow, MarketingCard, ProfileCompletionBarRow};
    protected static final DLSComponent[] TEAM_HOMES = {BedDetailsCard, BigNumberRow, BottomBarBanner, BottomButtonBar, CheckInGuideAddStepButton, CheckInGuideStepCard, EmptyStateCard, EventScheduleInterstitial, FixedActionFooterWithText, GuideImageMarquee, ImageWithButtonRow, InfoPanelRow, InlineFormattedIntegerInputRow, InlineTipRow, InquiryCard, IntegerFormatInputView, LabelDocumentMarquee, LabeledSectionRow, LargeIconRow, LeftAlignedImageRow, LeftIconRow, ListingInfoCardRow, ListingInfoRow, MessageInputOneRow, MessageInputTwoRows, MultiLineSplitRow, NestedListingChildRow, NestedListingEditRow, NestedListingRow, NestedListingToggleRow, NoProfilePhotoDetailsSummary, NuxCoverCard, PromotionMarquee, RearrangableLabeledPhotoCell, ReportableDetailsSummary, SheetFormattedIntegerInputText, SimpleTitleContentRow, StandardRowWithLabel, SummaryInterstitial, ThreadPreviewRowWithLabel};
    protected static final DLSComponent[] TEAM_MARKETPLACE = {ArticleDocumentMarquee, AutoResizableButtonBar, BookingNavigationView, CalendarBubblePopUp, CategorizedFilterButtons, CollaboratorsRow, DocumentCarouselMarquee, ExpandableCollectionRow, ExploreEmptyState, FilterSuggestionPill, FindInlineFiltersToggleRow, GuestRatingsMarquee, GuestStarRatingBreakdown, ImagePreviewRow, InfiniteDotIndicator, InputSuggestionSubRow, KickerMarquee, ListingDescription, LocationContextCard, NavigationPill, ParticipantRow, PriceFilterButtons, PrimaryTextBottomBar, ProfileLinkRow, RecentSearchCard, RequirementChecklistRow, ReviewMarquee, ScratchMicroRowWithRightText, SearchParamsRow, SelectAmenityItem, SelectBulletList, SelectHomeRoomItem, SelectHomeSummaryRow, SelectHostRow, SelectIconBulletRow, SelectMapInterstitial, SelectMarquee, SmallSheetSwitchRow, SmallSheetSwitchRowSwitch, TagsCollectionRow};
    protected static final DLSComponent[] TEAM_PAYMENTS = {GiftCardPromo, LabelRow, LinkableLegalTextRow, PaymentInputLayout, PaymentOptionRow, PosterRow, PricingBreakdownRow, ThreadBottomActionButton, ValueRow};
    protected static final DLSComponent[] TEAM_PSX = new DLSComponent[0];
    protected static final DLSComponent[] TEAM_TRIPS = {AddToPlanButton, DestinationCard, EditorialSectionHeader, FlightTimeRow, HaloImageTextRow, PosterCard, RecommendationCard, RecommendationCardSquare, RecommendationRow, SimilarPlaylistCard};
    protected static final DLSComponent[] TEAM_TRUST = {CityRegistrationCheckmarkRow, CityRegistrationIconActionRow, CityRegistrationToggleRow, FullImageRow, NumberedSimpleTextRow};
    protected static final DLSComponent[] TEAM_UNKNOWN = {BulletTextRow, FullScreenImageMarquee, MessageTranslationRow, ReviewBulletRow, StandardButtonRow, TeamComponentTemplateCopyMe};
    protected static final DLSComponent[] TYPE_CORE = {AirToolbar, BarRow, BasicRow, ButtonBar, DestinationCard, DisplayCard, DocumentMarquee, EditorialMarquee, FixedActionFooter, FixedDualActionFooter, FixedFlowActionAdvanceFooter, FixedFlowActionFooter, HomeAmenities, HomeCard, HomeHighlights, HomeMarquee, HomeReviewRow, HomeStarRatingBreakdown, IconRow, ImageRow, InfoActionRow, InfoRow, InlineContext, InlineInputRow, InlineMultilineInputRow, InputField, InputMarquee, InputSuggestionActionRow, Interstitial, LinkActionRow, MapInterstitial, MapSearchButton, MicroRow, MicroSectionHeader, MosaicCard, PlaceCard, PriceSummary, RangeDisplay, RefreshLoader, SectionHeader, SheetInputText, SheetMarquee, SheetProgressBar, SheetStepperRow, SimpleTextRow, SmallTextRow, StarRatingSummary, StatusBanner, StepperRow, SwitchRow, TextRow, ThreadPreviewRow, ToggleActionRow, UserDetailsActionRow};
    protected static final DLSComponent[] TYPE_DEPRECATED = {BigNumberRow, EntryMarquee, HeroMarquee, ImpactDisplayCard, ImpactMarquee, MicroDisplayCard, PrimaryButton, SmallMarquee, StandardRow, TweenRow};
    protected static final DLSComponent[] TYPE_TEAM;
    public static final DLSComponent<TagsCollectionRow> TagsCollectionRow = new DLSComponent(TagsCollectionRow.class, DLSComponentType.Team, "TagsCollectionRow", " Collection of Tags\n Uses `TagWithImageAndText` component for creating a tag.\n Uses Google's Flexbox Layout library: https://github.com/google/flexbox-layout\n Note: More customizations (flow order etc.) can be added as per different use cases.\n", TeamOwner.MARKETPLACE, R.layout.n2_view_holder_tags_collection_row) {
        public DLSMockAdapter<TagsCollectionRow> mockAdapter() {
            return new TagsCollectionRowMockAdapter();
        }

        public TagsCollectionRow createView(Context context, AttributeSet attrs) {
            return new TagsCollectionRow(context, attrs);
        }
    };
    public static final DLSComponent<TeamComponentTemplateCopyMe> TeamComponentTemplateCopyMe = new DLSComponent(TeamComponentTemplateCopyMe.class, DLSComponentType.Team, "TeamComponentTemplateCopyMe", " I am a starter template to create new Team components. Please copy me and leave this file intact.\n <p>\n TODO Briefly document the general purpose and use case for your new document\n", TeamOwner.UNKNOWN, R.layout.n2_view_holder_team_component_template_copy_me) {
        public DLSMockAdapter<TeamComponentTemplateCopyMe> mockAdapter() {
            return new TeamComponentTemplateCopyMeMockAdapter();
        }

        public TeamComponentTemplateCopyMe createView(Context context, AttributeSet attrs) {
            return new TeamComponentTemplateCopyMe(context, attrs);
        }
    };
    public static final DLSComponent<TextRow> TextRow = new DLSComponent(TextRow.class, DLSComponentType.Core, "TextRow", " TextRow (aka RegularTextRow) is used to display text.\n <p>\n If a maximum number of lines is set it will start by truncating the text and will expand to\n display everything when clicked. Clicking again will then collapse the view back to its\n original state. This functionality can be disabled by setting expandable to false.\n <p>\n If \"read more\" text is specified and the view is collapsed then it will append an ellipsis and\n that text at the end of the visible content.\n <p>\n Note that any click listeners set by using {@link #setOnClickListener(OnClickListener)} will\n only work while the text fits within the maximum number of lines (if specified) or if the view\n is not expandable. Ie you probably don't want to be setting click listeners on this\n\n @see SmallTextRow\n", TeamOwner.DLS, R.layout.n2_view_holder_text_row) {
        public DLSMockAdapter<TextRow> mockAdapter() {
            return new TextRowMockAdapter();
        }

        public TextRow createView(Context context, AttributeSet attrs) {
            return new TextRow(context, attrs);
        }
    };
    public static final DLSComponent<ThreadBottomActionButton> ThreadBottomActionButton = new DLSComponent(ThreadBottomActionButton.class, DLSComponentType.Team, "ThreadBottomActionButton", " This row is used at the bottom of message thread. It allows text display and it's also clickable.\n", TeamOwner.PAYMENTS, R.layout.n2_view_holder_thread_bottom_action_button) {
        public DLSMockAdapter<ThreadBottomActionButton> mockAdapter() {
            return new ThreadBottomActionButtonMockAdapter();
        }

        public ThreadBottomActionButton createView(Context context, AttributeSet attrs) {
            return new ThreadBottomActionButton(context, attrs);
        }
    };
    public static final DLSComponent<ThreadPreviewRow> ThreadPreviewRow = new DLSComponent(ThreadPreviewRow.class, DLSComponentType.Core, "ThreadPreviewRow", " A preview for a message thread and alert.\n", TeamOwner.DLS, R.layout.n2_view_holder_thread_preview_row) {
        public DLSMockAdapter<ThreadPreviewRow> mockAdapter() {
            return new ThreadPreviewRowMockAdapter();
        }

        public ThreadPreviewRow createView(Context context, AttributeSet attrs) {
            return new ThreadPreviewRow(context, attrs);
        }
    };
    public static final DLSComponent<ThreadPreviewRowWithLabel> ThreadPreviewRowWithLabel = new DLSComponent(ThreadPreviewRowWithLabel.class, DLSComponentType.Team, "ThreadPreviewRowWithLabel", null, TeamOwner.HOMES, R.layout.n2_view_holder_thread_preview_row_with_label) {
        public DLSMockAdapter<ThreadPreviewRowWithLabel> mockAdapter() {
            return new ThreadPreviewRowWithLabelMockAdapter();
        }

        public ThreadPreviewRowWithLabel createView(Context context, AttributeSet attrs) {
            return new ThreadPreviewRowWithLabel(context, attrs);
        }
    };
    public static final DLSComponent<ToggleActionRow> ToggleActionRow = new DLSComponent(ToggleActionRow.class, DLSComponentType.Core, "ToggleActionRow", " Use as a radio button.\n <p>\n For checkbox or switch type scenarios, prefer {@link SwitchRow}.\n\n @see SwitchRow\n @see com.airbnb.n2.utils.RadioRowManager\n", TeamOwner.DLS, R.layout.n2_view_holder_toggle_action_row) {
        public DLSMockAdapter<ToggleActionRow> mockAdapter() {
            return new ToggleActionRowMockAdapter();
        }

        public ToggleActionRow createView(Context context, AttributeSet attrs) {
            return new ToggleActionRow(context, attrs);
        }
    };
    public static final DLSComponent<TweenRow> TweenRow = new DLSComponent(TweenRow.class, DLSComponentType.Deprecated, "TweenRow", " Implements the TweenActionRow DLS component\n", TeamOwner.DLS, R.layout.n2_view_holder_tween_row) {
        public DLSMockAdapter<TweenRow> mockAdapter() {
            return new TweenRowMockAdapter();
        }

        public TweenRow createView(Context context, AttributeSet attrs) {
            return new TweenRow(context, attrs);
        }
    };
    public static final DLSComponent<UserDetailsActionRow> UserDetailsActionRow = new DLSComponent(UserDetailsActionRow.class, DLSComponentType.Core, "UserDetailsActionRow", " View that has a few fields for a summary of listing information including a couple text fields indicating the type of listing, the host and an\n image of the host.\n <p>\n TODO(jose, Saumitra Maheshwari) Use ListingInfoRow for showing listing-only information\n", TeamOwner.DLS, R.layout.n2_view_holder_user_details_action_row) {
        public DLSMockAdapter<UserDetailsActionRow> mockAdapter() {
            return new UserDetailsActionRowMockAdapter();
        }

        public UserDetailsActionRow createView(Context context, AttributeSet attrs) {
            return new UserDetailsActionRow(context, attrs);
        }
    };
    public static final DLSComponent<ValueRow> ValueRow = new DLSComponent(ValueRow.class, DLSComponentType.Team, "ValueRow", " ValueRow component is used as a row that shows value from previous input.\n", TeamOwner.PAYMENTS, R.layout.n2_view_holder_value_row) {
        public DLSMockAdapter<ValueRow> mockAdapter() {
            return new ValueRowMockAdapter();
        }

        public ValueRow createView(Context context, AttributeSet attrs) {
            return new ValueRow(context, attrs);
        }
    };

    static {
        DLSComponent[] dLSComponentArr = new DLSComponent[LDSFile.EF_DG4_TAG];
        dLSComponentArr[0] = AddToPlanButton;
        dLSComponentArr[1] = ArticleDocumentMarquee;
        dLSComponentArr[2] = ArticleSummaryRow;
        dLSComponentArr[3] = AutoResizableButtonBar;
        dLSComponentArr[4] = BedDetailsCard;
        dLSComponentArr[5] = BookingNavigationView;
        dLSComponentArr[6] = BottomBarBanner;
        dLSComponentArr[7] = BottomButtonBar;
        dLSComponentArr[8] = BulletTextRow;
        dLSComponentArr[9] = CalendarBubblePopUp;
        dLSComponentArr[10] = CategorizedFilterButtons;
        dLSComponentArr[11] = CheckInGuideAddStepButton;
        dLSComponentArr[12] = CheckInGuideStepCard;
        dLSComponentArr[13] = CityRegistrationCheckmarkRow;
        dLSComponentArr[14] = CityRegistrationIconActionRow;
        dLSComponentArr[15] = CityRegistrationToggleRow;
        dLSComponentArr[16] = CollaboratorsRow;
        dLSComponentArr[17] = DocumentCarouselMarquee;
        dLSComponentArr[18] = EditorialSectionHeader;
        dLSComponentArr[19] = EmptyStateCard;
        dLSComponentArr[20] = EventScheduleInterstitial;
        dLSComponentArr[21] = ExpandableCollectionRow;
        dLSComponentArr[22] = ExploreEmptyState;
        dLSComponentArr[23] = FilterSuggestionPill;
        dLSComponentArr[24] = FindInlineFiltersToggleRow;
        dLSComponentArr[25] = FixedActionFooterWithText;
        dLSComponentArr[26] = FlightTimeRow;
        dLSComponentArr[27] = FullImageRow;
        dLSComponentArr[28] = FullScreenImageMarquee;
        dLSComponentArr[29] = GiftCardPromo;
        dLSComponentArr[30] = GuestRatingsMarquee;
        dLSComponentArr[31] = GuestStarRatingBreakdown;
        dLSComponentArr[32] = GuideImageMarquee;
        dLSComponentArr[33] = HaloImageTextRow;
        dLSComponentArr[34] = HomeCardChina;
        dLSComponentArr[35] = ImagePreviewRow;
        dLSComponentArr[36] = ImageWithButtonRow;
        dLSComponentArr[37] = InfiniteDotIndicator;
        dLSComponentArr[38] = InfoPanelRow;
        dLSComponentArr[39] = InlineCaution;
        dLSComponentArr[40] = InlineFormattedIntegerInputRow;
        dLSComponentArr[41] = InlineInputWithContactPickerRow;
        dLSComponentArr[42] = InlineTipRow;
        dLSComponentArr[43] = InputSuggestionActionRowChina;
        dLSComponentArr[44] = InputSuggestionSubRow;
        dLSComponentArr[45] = InquiryCard;
        dLSComponentArr[46] = IntegerFormatInputView;
        dLSComponentArr[47] = InviteRow;
        dLSComponentArr[48] = KickerMarquee;
        dLSComponentArr[49] = LabelDocumentMarquee;
        dLSComponentArr[50] = LabelRow;
        dLSComponentArr[51] = LabeledSectionRow;
        dLSComponentArr[52] = LargeIconRow;
        dLSComponentArr[53] = LeftAlignedImageRow;
        dLSComponentArr[54] = LeftIconRow;
        dLSComponentArr[55] = LinkableLegalTextRow;
        dLSComponentArr[56] = ListingDescription;
        dLSComponentArr[57] = ListingInfoCardRow;
        dLSComponentArr[58] = ListingInfoRow;
        dLSComponentArr[59] = LoadingText;
        dLSComponentArr[60] = LocationContextCard;
        dLSComponentArr[61] = MarketingCard;
        dLSComponentArr[62] = MessageInputOneRow;
        dLSComponentArr[63] = MessageInputTwoRows;
        dLSComponentArr[64] = MessageTranslationRow;
        dLSComponentArr[65] = MultiLineSplitRow;
        dLSComponentArr[66] = NavigationPill;
        dLSComponentArr[67] = NestedListingChildRow;
        dLSComponentArr[68] = NestedListingEditRow;
        dLSComponentArr[69] = NestedListingRow;
        dLSComponentArr[70] = NestedListingToggleRow;
        dLSComponentArr[71] = NoProfilePhotoDetailsSummary;
        dLSComponentArr[72] = NumberedSimpleTextRow;
        dLSComponentArr[73] = NuxCoverCard;
        dLSComponentArr[74] = ParticipantRow;
        dLSComponentArr[75] = PaymentInputLayout;
        dLSComponentArr[76] = PaymentOptionRow;
        dLSComponentArr[77] = PosterCard;
        dLSComponentArr[78] = PosterRow;
        dLSComponentArr[79] = PriceFilterButtons;
        dLSComponentArr[80] = PricingBreakdownRow;
        dLSComponentArr[81] = PrimaryTextBottomBar;
        dLSComponentArr[82] = ProfileCompletionBarRow;
        dLSComponentArr[83] = ProfileLinkRow;
        dLSComponentArr[84] = PromotionMarquee;
        dLSComponentArr[85] = RearrangableLabeledPhotoCell;
        dLSComponentArr[86] = RecentSearchCard;
        dLSComponentArr[87] = RecommendationCard;
        dLSComponentArr[88] = RecommendationCardSquare;
        dLSComponentArr[89] = RecommendationRow;
        dLSComponentArr[90] = ReportableDetailsSummary;
        dLSComponentArr[91] = RequirementChecklistRow;
        dLSComponentArr[92] = ReviewBulletRow;
        dLSComponentArr[93] = ReviewMarquee;
        dLSComponentArr[94] = ScratchMicroRowWithRightText;
        dLSComponentArr[95] = SearchParamsRow;
        dLSComponentArr[96] = SearchSuggestionItem;
        dLSComponentArr[97] = SelectAmenityItem;
        dLSComponentArr[98] = SelectBulletList;
        dLSComponentArr[99] = SelectHomeRoomItem;
        dLSComponentArr[100] = SelectHomeSummaryRow;
        dLSComponentArr[101] = SelectHostRow;
        dLSComponentArr[102] = SelectIconBulletRow;
        dLSComponentArr[103] = SelectMapInterstitial;
        dLSComponentArr[104] = SelectMarquee;
        dLSComponentArr[105] = SheetFormattedIntegerInputText;
        dLSComponentArr[106] = SimilarPlaylistCard;
        dLSComponentArr[107] = SimpleTitleContentRow;
        dLSComponentArr[108] = SmallSheetSwitchRow;
        dLSComponentArr[109] = SmallSheetSwitchRowSwitch;
        dLSComponentArr[110] = StandardButtonRow;
        dLSComponentArr[111] = StandardRowWithLabel;
        dLSComponentArr[112] = SummaryInterstitial;
        dLSComponentArr[113] = TagsCollectionRow;
        dLSComponentArr[114] = TeamComponentTemplateCopyMe;
        dLSComponentArr[115] = ThreadBottomActionButton;
        dLSComponentArr[116] = ThreadPreviewRowWithLabel;
        dLSComponentArr[117] = ValueRow;
        TYPE_TEAM = dLSComponentArr;
        DLSComponent[] dLSComponentArr2 = new DLSComponent[CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256];
        dLSComponentArr2[0] = AddToPlanButton;
        dLSComponentArr2[1] = AirToolbar;
        dLSComponentArr2[2] = ArticleDocumentMarquee;
        dLSComponentArr2[3] = ArticleSummaryRow;
        dLSComponentArr2[4] = AutoResizableButtonBar;
        dLSComponentArr2[5] = BarRow;
        dLSComponentArr2[6] = BasicRow;
        dLSComponentArr2[7] = BedDetailsCard;
        dLSComponentArr2[8] = BigNumberRow;
        dLSComponentArr2[9] = BookingNavigationView;
        dLSComponentArr2[10] = BottomBarBanner;
        dLSComponentArr2[11] = BottomButtonBar;
        dLSComponentArr2[12] = BulletTextRow;
        dLSComponentArr2[13] = ButtonBar;
        dLSComponentArr2[14] = CalendarBubblePopUp;
        dLSComponentArr2[15] = CategorizedFilterButtons;
        dLSComponentArr2[16] = CheckInGuideAddStepButton;
        dLSComponentArr2[17] = CheckInGuideStepCard;
        dLSComponentArr2[18] = CityRegistrationCheckmarkRow;
        dLSComponentArr2[19] = CityRegistrationIconActionRow;
        dLSComponentArr2[20] = CityRegistrationToggleRow;
        dLSComponentArr2[21] = CollaboratorsRow;
        dLSComponentArr2[22] = DestinationCard;
        dLSComponentArr2[23] = DisplayCard;
        dLSComponentArr2[24] = DocumentCarouselMarquee;
        dLSComponentArr2[25] = DocumentMarquee;
        dLSComponentArr2[26] = EditorialMarquee;
        dLSComponentArr2[27] = EditorialSectionHeader;
        dLSComponentArr2[28] = EmptyStateCard;
        dLSComponentArr2[29] = EntryMarquee;
        dLSComponentArr2[30] = EventScheduleInterstitial;
        dLSComponentArr2[31] = ExpandableCollectionRow;
        dLSComponentArr2[32] = ExploreEmptyState;
        dLSComponentArr2[33] = FilterSuggestionPill;
        dLSComponentArr2[34] = FindInlineFiltersToggleRow;
        dLSComponentArr2[35] = FixedActionFooter;
        dLSComponentArr2[36] = FixedActionFooterWithText;
        dLSComponentArr2[37] = FixedDualActionFooter;
        dLSComponentArr2[38] = FixedFlowActionAdvanceFooter;
        dLSComponentArr2[39] = FixedFlowActionFooter;
        dLSComponentArr2[40] = FlightTimeRow;
        dLSComponentArr2[41] = FullImageRow;
        dLSComponentArr2[42] = FullScreenImageMarquee;
        dLSComponentArr2[43] = GiftCardPromo;
        dLSComponentArr2[44] = GuestRatingsMarquee;
        dLSComponentArr2[45] = GuestStarRatingBreakdown;
        dLSComponentArr2[46] = GuideImageMarquee;
        dLSComponentArr2[47] = HaloImageTextRow;
        dLSComponentArr2[48] = HeroMarquee;
        dLSComponentArr2[49] = HomeAmenities;
        dLSComponentArr2[50] = HomeCard;
        dLSComponentArr2[51] = HomeCardChina;
        dLSComponentArr2[52] = HomeHighlights;
        dLSComponentArr2[53] = HomeMarquee;
        dLSComponentArr2[54] = HomeReviewRow;
        dLSComponentArr2[55] = HomeStarRatingBreakdown;
        dLSComponentArr2[56] = IconRow;
        dLSComponentArr2[57] = ImagePreviewRow;
        dLSComponentArr2[58] = ImageRow;
        dLSComponentArr2[59] = ImageWithButtonRow;
        dLSComponentArr2[60] = ImpactDisplayCard;
        dLSComponentArr2[61] = ImpactMarquee;
        dLSComponentArr2[62] = InfiniteDotIndicator;
        dLSComponentArr2[63] = InfoActionRow;
        dLSComponentArr2[64] = InfoPanelRow;
        dLSComponentArr2[65] = InfoRow;
        dLSComponentArr2[66] = InlineCaution;
        dLSComponentArr2[67] = InlineContext;
        dLSComponentArr2[68] = InlineFormattedIntegerInputRow;
        dLSComponentArr2[69] = InlineInputRow;
        dLSComponentArr2[70] = InlineInputWithContactPickerRow;
        dLSComponentArr2[71] = InlineMultilineInputRow;
        dLSComponentArr2[72] = InlineTipRow;
        dLSComponentArr2[73] = InputField;
        dLSComponentArr2[74] = InputMarquee;
        dLSComponentArr2[75] = InputSuggestionActionRow;
        dLSComponentArr2[76] = InputSuggestionActionRowChina;
        dLSComponentArr2[77] = InputSuggestionSubRow;
        dLSComponentArr2[78] = InquiryCard;
        dLSComponentArr2[79] = IntegerFormatInputView;
        dLSComponentArr2[80] = Interstitial;
        dLSComponentArr2[81] = InviteRow;
        dLSComponentArr2[82] = KickerMarquee;
        dLSComponentArr2[83] = LabelDocumentMarquee;
        dLSComponentArr2[84] = LabelRow;
        dLSComponentArr2[85] = LabeledSectionRow;
        dLSComponentArr2[86] = LargeIconRow;
        dLSComponentArr2[87] = LeftAlignedImageRow;
        dLSComponentArr2[88] = LeftIconRow;
        dLSComponentArr2[89] = LinkActionRow;
        dLSComponentArr2[90] = LinkableLegalTextRow;
        dLSComponentArr2[91] = ListingDescription;
        dLSComponentArr2[92] = ListingInfoCardRow;
        dLSComponentArr2[93] = ListingInfoRow;
        dLSComponentArr2[94] = LoadingText;
        dLSComponentArr2[95] = LocationContextCard;
        dLSComponentArr2[96] = MapInterstitial;
        dLSComponentArr2[97] = MapSearchButton;
        dLSComponentArr2[98] = MarketingCard;
        dLSComponentArr2[99] = MessageInputOneRow;
        dLSComponentArr2[100] = MessageInputTwoRows;
        dLSComponentArr2[101] = MessageTranslationRow;
        dLSComponentArr2[102] = MicroDisplayCard;
        dLSComponentArr2[103] = MicroRow;
        dLSComponentArr2[104] = MicroSectionHeader;
        dLSComponentArr2[105] = MosaicCard;
        dLSComponentArr2[106] = MultiLineSplitRow;
        dLSComponentArr2[107] = NavigationPill;
        dLSComponentArr2[108] = NestedListingChildRow;
        dLSComponentArr2[109] = NestedListingEditRow;
        dLSComponentArr2[110] = NestedListingRow;
        dLSComponentArr2[111] = NestedListingToggleRow;
        dLSComponentArr2[112] = NoProfilePhotoDetailsSummary;
        dLSComponentArr2[113] = NumberedSimpleTextRow;
        dLSComponentArr2[114] = NuxCoverCard;
        dLSComponentArr2[115] = ParticipantRow;
        dLSComponentArr2[116] = PaymentInputLayout;
        dLSComponentArr2[117] = PaymentOptionRow;
        dLSComponentArr2[118] = PlaceCard;
        dLSComponentArr2[119] = PosterCard;
        dLSComponentArr2[120] = PosterRow;
        dLSComponentArr2[121] = PriceFilterButtons;
        dLSComponentArr2[122] = PriceSummary;
        dLSComponentArr2[123] = PricingBreakdownRow;
        dLSComponentArr2[124] = PrimaryButton;
        dLSComponentArr2[125] = PrimaryTextBottomBar;
        dLSComponentArr2[126] = ProfileCompletionBarRow;
        dLSComponentArr2[127] = ProfileLinkRow;
        dLSComponentArr2[128] = PromotionMarquee;
        dLSComponentArr2[129] = RangeDisplay;
        dLSComponentArr2[130] = RearrangableLabeledPhotoCell;
        dLSComponentArr2[131] = RecentSearchCard;
        dLSComponentArr2[132] = RecommendationCard;
        dLSComponentArr2[133] = RecommendationCardSquare;
        dLSComponentArr2[134] = RecommendationRow;
        dLSComponentArr2[135] = RefreshLoader;
        dLSComponentArr2[136] = ReportableDetailsSummary;
        dLSComponentArr2[137] = RequirementChecklistRow;
        dLSComponentArr2[138] = ReviewBulletRow;
        dLSComponentArr2[139] = ReviewMarquee;
        dLSComponentArr2[140] = ScratchMicroRowWithRightText;
        dLSComponentArr2[141] = SearchParamsRow;
        dLSComponentArr2[142] = SearchSuggestionItem;
        dLSComponentArr2[143] = SectionHeader;
        dLSComponentArr2[144] = SelectAmenityItem;
        dLSComponentArr2[145] = SelectBulletList;
        dLSComponentArr2[146] = SelectHomeRoomItem;
        dLSComponentArr2[147] = SelectHomeSummaryRow;
        dLSComponentArr2[148] = SelectHostRow;
        dLSComponentArr2[149] = SelectIconBulletRow;
        dLSComponentArr2[150] = SelectMapInterstitial;
        dLSComponentArr2[151] = SelectMarquee;
        dLSComponentArr2[152] = SheetFormattedIntegerInputText;
        dLSComponentArr2[153] = SheetInputText;
        dLSComponentArr2[154] = SheetMarquee;
        dLSComponentArr2[155] = SheetProgressBar;
        dLSComponentArr2[156] = SheetStepperRow;
        dLSComponentArr2[157] = SimilarPlaylistCard;
        dLSComponentArr2[158] = SimpleTextRow;
        dLSComponentArr2[159] = SimpleTitleContentRow;
        dLSComponentArr2[160] = SmallMarquee;
        dLSComponentArr2[161] = SmallSheetSwitchRow;
        dLSComponentArr2[162] = SmallSheetSwitchRowSwitch;
        dLSComponentArr2[163] = SmallTextRow;
        dLSComponentArr2[164] = StandardButtonRow;
        dLSComponentArr2[165] = StandardRow;
        dLSComponentArr2[166] = StandardRowWithLabel;
        dLSComponentArr2[167] = StarRatingSummary;
        dLSComponentArr2[168] = StatusBanner;
        dLSComponentArr2[169] = StepperRow;
        dLSComponentArr2[170] = SummaryInterstitial;
        dLSComponentArr2[171] = SwitchRow;
        dLSComponentArr2[172] = TagsCollectionRow;
        dLSComponentArr2[173] = TeamComponentTemplateCopyMe;
        dLSComponentArr2[174] = TextRow;
        dLSComponentArr2[175] = ThreadBottomActionButton;
        dLSComponentArr2[176] = ThreadPreviewRow;
        dLSComponentArr2[177] = ThreadPreviewRowWithLabel;
        dLSComponentArr2[178] = ToggleActionRow;
        dLSComponentArr2[179] = TweenRow;
        dLSComponentArr2[180] = UserDetailsActionRow;
        dLSComponentArr2[181] = ValueRow;
        ALL = dLSComponentArr2;
    }

    public static DLSComponent[] byType(DLSComponentType componentType) {
        switch (componentType) {
            case Team:
                return TYPE_TEAM;
            case Deprecated:
                return TYPE_DEPRECATED;
            default:
                return TYPE_CORE;
        }
    }

    public static DLSComponent[] byTeam(TeamOwner teamOwner) {
        switch (teamOwner) {
            case CHINA:
                return TEAM_CHINA;
            case GROWTH:
                return TEAM_GROWTH;
            case HOMES:
                return TEAM_HOMES;
            case MARKETPLACE:
                return TEAM_MARKETPLACE;
            case PAYMENTS:
                return TEAM_PAYMENTS;
            case PSX:
                return TEAM_PSX;
            case TRIPS:
                return TEAM_TRIPS;
            case TRUST:
                return TEAM_TRUST;
            case UNKNOWN:
                return TEAM_UNKNOWN;
            default:
                return TEAM_DLS;
        }
    }
}
