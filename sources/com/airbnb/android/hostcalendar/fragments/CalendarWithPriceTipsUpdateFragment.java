package com.airbnb.android.hostcalendar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarUpdateListener;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.DynamicPricingExplanation;
import com.airbnb.android.core.models.ListingCalendar;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.CalendarUpdateHelper;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.activities.HostCalendarUpdateActivity;
import com.airbnb.android.hostcalendar.adapters.HostSmartPromoAdapter;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragmentPager.TabType;
import com.airbnb.android.hostcalendar.utils.NetworkErrorUtil;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.DsNightAvailabilityStatus.p086v1.C1987DsNightAvailabilityStatus;
import com.airbnb.jitney.event.logging.PriceSuggestionContext.p205v1.C2569PriceSuggestionContext;
import com.airbnb.jitney.event.logging.PriceTipDaysType.p206v1.C2571PriceTipDaysType;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.jitney.event.logging.SinglePriceChangeContext.p258v1.C2742SinglePriceChangeContext;
import com.airbnb.jitney.event.logging.SinglePriceChangeContext.p258v1.C2742SinglePriceChangeContext.Builder;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineFormattedIntegerInputRow;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.TriStateSwitchRow;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.utils.RadioRowManager;
import com.airbnb.p027n2.utils.RadioRowManagerSelectionListener;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CalendarWithPriceTipsUpdateFragment extends AirFragment implements ArgumentConstants {
    private static final int MAX_NOTES_LINES = 3;
    public static final String NUM_OF_DAYS_EDITED = "num_of_days_edited";
    public static final int REQ_CODE_EDIT_NOTES = 100;
    private static final int REQ_MULTI_DAY_PRICE_TIP = 200;
    private MenuItem addOrEditNoteLink;
    /* access modifiers changed from: private */
    public boolean appliedPriceTips;
    @BindView
    SectionHeader availabilityHeader;
    private RadioRowManager availabilityRadioRowManager = new RadioRowManager(this.radioListener);
    @BindView
    ToggleActionRow availableToggle;
    @State
    String blockedReason;
    @BindView
    ToggleActionRow blockedToggle;
    @BindView
    ToggleActionRow blockedUntilToggle;
    private CalendarRule calendarRule;
    protected CalendarStore calendarStore;
    private final CalendarUpdateListener calendarUpdateListener = new CalendarUpdateListener() {
        public void onCalendarUpdateSuccess(Set<Long> set, AirDate startDate, AirDate endDate) {
            if (CalendarWithPriceTipsUpdateFragment.this.isResumed()) {
                Integer newPrice = CalendarWithPriceTipsUpdateFragment.this.collectPriceChange();
                if (newPrice != null || CalendarWithPriceTipsUpdateFragment.this.appliedPriceTips) {
                    List<C2742SinglePriceChangeContext> singlePriceChangeContexts = new ArrayList<>();
                    Iterator it = CalendarWithPriceTipsUpdateFragment.this.selectedDays.iterator();
                    while (it.hasNext()) {
                        CalendarDay calendarDay = (CalendarDay) it.next();
                        CalendarDayPriceInfo priceInfo = calendarDay.getPriceInfo();
                        int priceTip = priceInfo.getNativeSuggestedPrice();
                        String isoDateString = calendarDay.getDate().getIsoDateString();
                        C1987DsNightAvailabilityStatus dsNightAvailabilityStatus = calendarDay.getDsNightAvailabilityStatus();
                        Long valueOf = Long.valueOf((long) priceInfo.getNativePrice());
                        Long valueOf2 = Long.valueOf((long) priceTip);
                        if (!CalendarWithPriceTipsUpdateFragment.this.appliedPriceTips) {
                            priceTip = newPrice.intValue();
                        }
                        singlePriceChangeContexts.add(new Builder(isoDateString, dsNightAvailabilityStatus, valueOf, valueOf2, Long.valueOf((long) priceTip), PricingJitneyLogger.getBucket(priceInfo), Boolean.valueOf(priceInfo.isDemandBasedPricingOverridden())).build());
                    }
                    CalendarWithPriceTipsUpdateFragment.this.pricingJitneyLogger.changeCalendarDailyPrice(CalendarWithPriceTipsUpdateFragment.this.helper.getCurrency(), singlePriceChangeContexts, CalendarWithPriceTipsUpdateFragment.this.isSmartPricingEnabledForListing);
                }
                CalendarWithPriceTipsUpdateFragment.this.saveButton.setState(AirButton.State.Success);
                CalendarWithPriceTipsUpdateFragment.this.showLoader(false);
                CalendarWithPriceTipsUpdateFragment.this.getActivity().setResult(-1, new Intent().putExtra(CalendarWithPriceTipsUpdateFragment.NUM_OF_DAYS_EDITED, CalendarWithPriceTipsUpdateFragment.this.selectedDays.size()));
                CalendarWithPriceTipsUpdateFragment.this.getActivity().finish();
            }
        }

        public void onCalendarError(NetworkException e) {
            if (CalendarWithPriceTipsUpdateFragment.this.isResumed()) {
                CalendarWithPriceTipsUpdateFragment.this.saveButton.setState(AirButton.State.Normal);
                CalendarWithPriceTipsUpdateFragment.this.showLoader(false);
                if (!e.hasErrorResponse() || !((ErrorResponse) e.errorResponse()).isValidationError()) {
                    NetworkUtil.tryShowRetryableErrorWithSnackbar(CalendarWithPriceTipsUpdateFragment.this.getView(), e, CalendarWithPriceTipsUpdateFragment$1$$Lambda$1.lambdaFactory$(this));
                } else {
                    NetworkErrorUtil.promptNetworkError(CalendarWithPriceTipsUpdateFragment.this.getContext(), ((ErrorResponse) e.errorResponse()).errorDetails(), C6418R.string.calendar_update_validation_error_title, C6418R.string.calendar_update_validation_error_body, CalendarWithPriceTipsUpdateFragment.this.getFragmentManager());
                }
            }
        }
    };
    @BindView
    DocumentMarquee calendarUpdateMarquee;
    @BindView
    SimpleTextRow disclaimer;
    @State
    boolean hasLoggedJitneyPriceTipImpression;
    /* access modifiers changed from: private */
    public CalendarUpdateHelper helper;
    @State
    String hostNotes;
    /* access modifiers changed from: private */
    public boolean isSmartPricingEnabledForListing = false;
    CalendarJitneyLogger jitneyLogger;
    private long listingId;
    LoggingContextFactory loggingContextFactory;
    private final Listener priceChangedListener = CalendarWithPriceTipsUpdateFragment$$Lambda$2.lambdaFactory$(this);
    @BindView
    InlineFormattedIntegerInputRow priceField;
    @BindView
    SectionHeader pricingHeader;
    /* access modifiers changed from: private */
    public PricingJitneyLogger pricingJitneyLogger;
    private final RadioRowManagerSelectionListener radioListener = CalendarWithPriceTipsUpdateFragment$$Lambda$1.lambdaFactory$();
    @BindView
    SimpleTextRow reasons;
    @BindView
    AirButton saveButton;
    @BindView
    VerboseScrollView scrollView;
    @State
    ArrayList<CalendarDay> selectedDays;
    @BindView
    SwitchRow smartPriceSwitch;
    @BindView
    TriStateSwitchRow smartPriceTriSwitch;
    private AirDateTime smartPricingUpdatedAt = null;
    @BindView
    RecyclerView smartPromoRow;
    @BindView
    AirToolbar toolbar;
    @BindView
    SimpleTextRow viewPriceTipsRow;

    static /* synthetic */ void lambda$new$0(Object selectedValue) {
    }

    static /* synthetic */ void lambda$new$1(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment, Integer newValue) {
        calendarWithPriceTipsUpdateFragment.setSmartPriceState(ToggleState.OFF);
        calendarWithPriceTipsUpdateFragment.onPriceTipAppliedChanged(false);
    }

    public static CalendarWithPriceTipsUpdateFragment newInstance(long listingId2, ArrayList<CalendarDay> daysToUpdate, TabType tabType, CalendarRule calendarRule2) {
        return (CalendarWithPriceTipsUpdateFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CalendarWithPriceTipsUpdateFragment()).putLong("listing_id", listingId2)).putParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS, daysToUpdate)).putSerializable(ArgumentConstants.ARG_TAB_TYPE, tabType)).putParcelable(ArgumentConstants.ARG_CALENDAR_RULE, calendarRule2)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        ViewGroup view = (ViewGroup) inflater.inflate(C6418R.layout.fragment_host_calendar_with_price_tips_update, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.toolbar.scrollWith((Scrollable<?>) this.scrollView);
        this.listingId = getArguments().getLong("listing_id");
        this.selectedDays = getArguments().getParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.Calendar, ((TabType) getArguments().getSerializable(ArgumentConstants.ARG_TAB_TYPE)) == TabType.Month ? C2586PricingSettingsSectionType.Month : C2586PricingSettingsSectionType.Detail, this.listingId);
        this.calendarRule = (CalendarRule) getArguments().getParcelable(ArgumentConstants.ARG_CALENDAR_RULE);
        ListingCalendar listingCalendar = this.calendarStore.getListingCalendar(this.listingId);
        if (listingCalendar != null) {
            this.isSmartPricingEnabledForListing = listingCalendar.isDynamicPricingControlIsEnabled();
            this.smartPricingUpdatedAt = listingCalendar.getDynamicPricingMetadataUpdatedAt();
        }
        this.helper = new CalendarUpdateHelper(getResources(), this.selectedDays);
        if (savedInstanceState == null) {
            this.hostNotes = this.helper.getHostNotesAsString();
        }
        initMarquee();
        initAvailabilityToggles();
        initSmartPricing();
        if (this.helper.getDemandBasedPricingState() == ToggleState.ON) {
            z = true;
        } else {
            z = false;
        }
        updateSmartPricingReasons(z);
        updateDisclaimer(shouldShowDisclaimer());
        initNightlyPrice();
        updateMultiDayPriceTipsView();
        this.blockedReason = checkForBlackout();
        initSubtitle();
        initSmartPromo();
        return view;
    }

    private void initSmartPromo() {
        if (this.helper.hasSmartPromo() && PricingFeatureToggles.showHostSmartPromo()) {
            Set<CalendarDayPromotion> smartPromotions = new HashSet<>();
            Iterator it = this.selectedDays.iterator();
            while (it.hasNext()) {
                CalendarDayPromotion promo = ((CalendarDay) it.next()).getPromotion();
                if (promo != null && promo.getDiscountPercentage() > 0) {
                    smartPromotions.add(promo);
                }
            }
            HostSmartPromoAdapter controller = new HostSmartPromoAdapter(getContext(), this.requestManager);
            this.smartPromoRow.setAdapter(controller.getAdapter());
            controller.setData(smartPromotions);
            this.smartPromoRow.setVisibility(0);
            this.disclaimer.showDivider(true);
        }
    }

    private void updateMultiDayPriceTipsView() {
        this.viewPriceTipsRow.setText((CharSequence) TextUtil.fromHtmlSafe(getString(this.appliedPriceTips ? C6418R.string.host_calendar_multi_day_price_tips_applied : C6418R.string.host_calendar_multi_day_price_tips_entry_point)));
        ViewLibUtils.setVisibleIf(this.viewPriceTipsRow, getSmartPriceState() != ToggleState.ON && this.selectedDays.size() > 1 && !areSuggestionsZero() && PricingFeatureToggles.showMultiDayPriceTips());
    }

    private boolean areSuggestionsZero() {
        boolean isAllZero = true;
        Iterator it = this.selectedDays.iterator();
        while (it.hasNext()) {
            isAllZero = isAllZero && ((CalendarDay) it.next()).getPriceInfo().getNativeSuggestedPrice() == 0;
        }
        return isAllZero;
    }

    private void onPriceTipAppliedChanged(boolean applied) {
        this.appliedPriceTips = applied;
        updateMultiDayPriceTipsView();
        if (applied) {
            setAvailabilityState(AvailabilityType.Available);
            setSmartPriceState(ToggleState.OFF);
            setNightlyPrice(this.helper.getMinPriceTip(), this.helper.getMaxPriceTip());
        }
    }

    private String checkForBlackout() {
        CalendarDay blockedCalendarDay = (CalendarDay) FluentIterable.from((Iterable<E>) this.selectedDays).filter(CalendarWithPriceTipsUpdateFragment$$Lambda$3.lambdaFactory$()).first().orNull();
        if (blockedCalendarDay == null) {
            return null;
        }
        this.availabilityHeader.setVisibility(8);
        this.availableToggle.setVisibility(8);
        this.blockedToggle.setVisibility(8);
        this.blockedUntilToggle.setVisibility(8);
        String blockedReason2 = blockedCalendarDay.getReason();
        if (TextUtils.isEmpty(blockedReason2)) {
            return getString(C6418R.string.calendar_details_blocked);
        }
        return blockedReason2;
    }

    static /* synthetic */ boolean lambda$checkForBlackout$2(CalendarDay calendarDay) {
        return calendarDay != null && calendarDay.isBlockedForBlackout();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.toolbar.onCreateOptionsMenu(menu, inflater);
        this.addOrEditNoteLink = menu.findItem(C6418R.C6420id.add_edit_note_link);
        initAddOrEditLink();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6418R.C6420id.add_edit_note_link) {
            return false;
        }
        this.jitneyLogger.editSheetNotesClicked(this.listingId, this.selectedDays, TextUtils.isEmpty(this.hostNotes));
        startActivityForResult(HostCalendarUpdateActivity.intentForUpdateNotes(getContext(), this.listingId, this.selectedDays, this.hostNotes), 100);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == -1) {
                    this.hostNotes = data.getExtras().getString("notes");
                    initSubtitle();
                    initAddOrEditLink();
                    return;
                }
                return;
            case 200:
                if (resultCode == -1) {
                    onPriceTipAppliedChanged(true);
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void initMarquee() {
        this.calendarUpdateMarquee.setTitle((CharSequence) getTitle());
        this.calendarUpdateMarquee.setCaptionMaxLines(3);
    }

    private void initSubtitle() {
        if (this.blockedReason != null && !this.blockedReason.isEmpty()) {
            this.calendarUpdateMarquee.setCaption((CharSequence) getResources().getString(C6418R.string.calendar_update_note_display, new Object[]{this.blockedReason}));
        } else if (this.hostNotes == null || this.hostNotes.isEmpty()) {
            this.calendarUpdateMarquee.setCaption((CharSequence) "");
        } else {
            this.calendarUpdateMarquee.setCaption((CharSequence) getResources().getString(C6418R.string.calendar_update_note_display, new Object[]{this.hostNotes}));
        }
    }

    private void initAddOrEditLink() {
        if (this.addOrEditNoteLink == null) {
            return;
        }
        if (this.hostNotes == null || this.hostNotes.isEmpty()) {
            this.addOrEditNoteLink.setTitle(getString(C6418R.string.calendar_update_add_note));
        } else {
            this.addOrEditNoteLink.setTitle(getString(C6418R.string.calendar_update_edit_note));
        }
    }

    private void initAvailabilityToggles() {
        this.availabilityHeader.setVisibility(0);
        this.availableToggle.setVisibility(0);
        this.blockedToggle.setVisibility(0);
        this.availabilityRadioRowManager.addToggleActionRow(this.availableToggle, AvailabilityType.Available);
        this.availabilityRadioRowManager.addToggleActionRow(this.blockedToggle, AvailabilityType.UnavailablePersistent);
        if (this.calendarRule != null && isDayPastBookingWindow((CalendarDay) this.selectedDays.get(0))) {
            int bookingWindowDays = this.calendarRule.getMaxDaysNotice().getDays();
            AirDate blockedUntil = ((CalendarDay) this.selectedDays.get(0)).getDate().plusDays(-bookingWindowDays);
            this.blockedUntilToggle.setTitle((CharSequence) getContext().getString(C6418R.string.calendar_update_blocked_until_date, new Object[]{blockedUntil.getDateString(getContext())}));
            this.blockedUntilToggle.setSubtitle((CharSequence) getContext().getString(C6418R.string.calendar_update_blocked_until_subtitle, new Object[]{Integer.valueOf(MaxDaysNoticeSetting.daysToMonths(bookingWindowDays))}));
            this.blockedUntilToggle.setVisibility(0);
            this.availabilityRadioRowManager.addToggleActionRow(this.blockedUntilToggle, AvailabilityType.UnavailableByBookingWindow);
        }
        this.availabilityRadioRowManager.setCurrentValue(this.helper.getAvailabilityToggleValue());
    }

    private boolean isDayPastBookingWindow(CalendarDay day) {
        return day.getSubtype() != null && day.getSubtype().equals(CalendarRulesRequestConstants.MAX_DAYS_NOTICE);
    }

    private void initSmartPricing() {
        boolean z = true;
        this.pricingHeader.setVisibility(0);
        ViewUtils.setVisibleIf((View) this.smartPriceTriSwitch, this.isSmartPricingEnabledForListing && this.helper.isMixedMode());
        SwitchRow switchRow = this.smartPriceSwitch;
        if (!this.isSmartPricingEnabledForListing || this.helper.isMixedMode()) {
            z = false;
        }
        ViewUtils.setVisibleIf((View) switchRow, z);
        if (!this.isSmartPricingEnabledForListing) {
            return;
        }
        if (this.helper.isMixedMode()) {
            this.smartPriceTriSwitch.setState(this.helper.getDemandBasedPricingState());
            this.smartPriceTriSwitch.setDescription(getSmartPricingDescription());
            this.smartPriceTriSwitch.setOnCheckedChangeListener(CalendarWithPriceTipsUpdateFragment$$Lambda$4.lambdaFactory$(this));
            return;
        }
        this.smartPriceSwitch.setChecked(this.helper.isDemandBasePriceEnabled(), false);
        this.smartPriceSwitch.setDescription(getSmartPricingDescription());
        this.smartPriceSwitch.setOnCheckedChangeListener(CalendarWithPriceTipsUpdateFragment$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initSmartPricing$3(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment, TriStateSwitch v, ToggleState isChecked) {
        calendarWithPriceTipsUpdateFragment.smartPriceTriSwitch.setDescription("");
        boolean enabled = isChecked == ToggleState.ON;
        calendarWithPriceTipsUpdateFragment.updateNightlyPriceRange(enabled);
        calendarWithPriceTipsUpdateFragment.updateMultiDayPriceTipsFromSmartPricing(enabled);
    }

    static /* synthetic */ void lambda$initSmartPricing$4(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment, SwitchRowInterface v, boolean isChecked) {
        calendarWithPriceTipsUpdateFragment.smartPriceSwitch.setDescription(calendarWithPriceTipsUpdateFragment.getSmartPricingDescription());
        calendarWithPriceTipsUpdateFragment.updateNightlyPriceRange(isChecked);
        calendarWithPriceTipsUpdateFragment.updateDisclaimer(!isChecked);
        calendarWithPriceTipsUpdateFragment.updateSmartPricingReasons(isChecked);
        calendarWithPriceTipsUpdateFragment.updateMultiDayPriceTipsFromSmartPricing(isChecked);
    }

    private void updateMultiDayPriceTipsFromSmartPricing(boolean enabled) {
        if (enabled) {
            onPriceTipAppliedChanged(false);
        } else {
            updateMultiDayPriceTipsView();
        }
    }

    private void updateSmartPricingReasons(boolean smartPricingChecked) {
        boolean smartPricingEnabledAndOn;
        boolean shouldShowReasons;
        boolean z = true;
        if (!this.isSmartPricingEnabledForListing || !smartPricingChecked) {
            smartPricingEnabledAndOn = false;
        } else {
            smartPricingEnabledAndOn = true;
        }
        boolean priceTipShown = showPriceTip();
        SpannableStringBuilder reasonsBody = null;
        if (this.selectedDays.size() != 1 || (!priceTipShown && !smartPricingEnabledAndOn)) {
            shouldShowReasons = false;
        } else {
            shouldShowReasons = true;
        }
        if (shouldShowReasons) {
            String reasonsTitle = getString(priceTipShown ? C6418R.string.host_calendar_pricing_reasons_price_tip : C6418R.string.host_calendar_pricing_reasons_smart_pricing);
            reasonsBody = getSmartPricingReasonText((CalendarDay) this.selectedDays.get(0));
            SpannableStringBuilder builder = new SpannableStringBuilder(SpannableUtils.makeBookString(reasonsTitle + "\n", getContext()));
            builder.append(reasonsBody);
            this.reasons.setText((CharSequence) builder);
            this.reasons.showDivider(showPriceTip());
        }
        SimpleTextRow simpleTextRow = this.reasons;
        if (!shouldShowReasons || TextUtils.isEmpty(reasonsBody)) {
            z = false;
        }
        ViewUtils.setVisibleIf((View) simpleTextRow, z);
    }

    private SpannableStringBuilder getSmartPricingReasonText(CalendarDay day) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String currencySymbol = Currency.getInstance(this.helper.getCurrency()).getSymbol();
        List<DynamicPricingExplanation> explanationList = day.getPriceInfo().getDynamicPricingExplanations();
        if (explanationList != null) {
            for (int i = 0; i < explanationList.size(); i++) {
                DynamicPricingExplanation explanation = (DynamicPricingExplanation) explanationList.get(i);
                boolean isPositive = explanation.isPositive();
                String priceChange = (isPositive ? "+" : "-") + currencySymbol;
                builder.append(TextUtil.makeColored(getContext(), isPositive ? C6418R.color.n2_babu : C6418R.color.c_red, priceChange, priceChange));
                builder.append(" ").append(explanation.getReason());
                if (i < explanationList.size() - 1) {
                    builder.append("\n");
                }
            }
        }
        return builder;
    }

    private void updateDisclaimer(boolean showDisclaimer) {
        ViewUtils.setVisibleIf((View) this.disclaimer, showDisclaimer);
        if (showDisclaimer) {
            this.disclaimer.setText((CharSequence) TextUtil.fromHtmlSafe(getString(C6418R.string.host_calendar_pricing_disclaimer)));
        }
    }

    private boolean shouldShowDisclaimer() {
        return this.helper.isMixedMode() || !this.smartPriceSwitch.isChecked();
    }

    private String getTitle() {
        if (this.helper.isSingleDay()) {
            return this.helper.getFirstDate().formatDate(getString(C6418R.string.hh_day_week_date_name_format));
        }
        if (this.helper.areConsecutiveDays()) {
            return this.helper.getFirstDate().getDateSpanString(getContext(), this.helper.getLastDate());
        }
        return getResources().getString(C6418R.string.calendar_update_count_selected_title, new Object[]{Integer.valueOf(this.helper.getNumDays())});
    }

    private CharSequence getSmartPricingDescription() {
        int countSmartPriceOff = this.helper.getDemandBasedPriceOffCount();
        if (this.helper.isMixedDemandBasedPricing()) {
            return getResources().getString(C6418R.string.calendar_smart_pricing_off_some);
        }
        if (countSmartPriceOff > 0) {
            return getResources().getQuantityText(C6418R.plurals.calendar_smart_pricing_off, countSmartPriceOff);
        }
        if (this.smartPricingUpdatedAt == null) {
            return "";
        }
        return getResources().getString(C6418R.string.updated_elapsed_time, new Object[]{this.smartPricingUpdatedAt.getElapsedTime(getContext())});
    }

    private void initNightlyPrice() {
        this.priceField.setTitle(getResources().getString(C6418R.string.listing_setting_price_per_night));
        this.priceField.setNumberFormat(this.helper.getPriceFormat());
        setNightlyPrice(this.helper.getMinPrice(), this.helper.getMaxPrice());
    }

    private void setNightlyPrice(int min, int max) {
        this.priceField.setInputListener(null);
        if (min == max) {
            this.priceField.setAmount(Integer.valueOf(min));
        } else {
            this.priceField.setHint(this.helper.getPriceFormat().format((long) min) + " - " + this.helper.getPriceFormat().format((long) max));
            this.priceField.setAmount(null);
        }
        this.priceField.setInputListener(this.priceChangedListener);
        if (showPriceTip()) {
            List<C2569PriceSuggestionContext> contextList = PricingJitneyLogger.getPriceSuggestionContextList(this.selectedDays);
            int recommendedPrice = ((CalendarDay) this.selectedDays.get(0)).getPriceInfo().getNativeSuggestedPrice();
            if (recommendedPrice > 0) {
                this.priceField.setTip(getResources().getString(getTipRes(), new Object[]{CurrencyUtils.formatAmount((double) recommendedPrice, Currency.getInstance(this.helper.getCurrency()))}));
                this.priceField.setTipAmount(Integer.valueOf(recommendedPrice));
                this.priceField.setOnTipClickListener(CalendarWithPriceTipsUpdateFragment$$Lambda$6.lambdaFactory$(this, contextList));
                if (!this.hasLoggedJitneyPriceTipImpression) {
                    this.pricingJitneyLogger.viewCalendarPriceTip(this.listingId, contextList, C2571PriceTipDaysType.SingleDay);
                    this.hasLoggedJitneyPriceTipImpression = true;
                }
            }
        }
    }

    public static int getTipRes() {
        return PricingFeatureToggles.showUseTipCopy() ? C6418R.string.use_tip : C6418R.string.price_tip;
    }

    private boolean showPriceTip() {
        return this.selectedDays.size() == 1 && !this.isSmartPricingEnabledForListing;
    }

    private void updateNightlyPriceRange(boolean smartPricingOn) {
        ToggleState origState = this.helper.getDemandBasedPricingState();
        if (((origState == ToggleState.ON && smartPricingOn) || (origState == ToggleState.OFF && !smartPricingOn)) || !smartPricingOn) {
            setNightlyPrice(this.helper.getMinPrice(), this.helper.getMaxPrice());
        } else {
            setNightlyPrice(this.helper.getMinSmartPrice(), this.helper.getMaxSmartPrice());
        }
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    @OnClick
    public void onClickSaveButton() {
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        save();
    }

    @OnClick
    public void onClickViewPriceTips() {
        startActivityForResult(HostCalendarUpdateActivity.intentForMultiDayPriceTips(getContext(), this.listingId, this.selectedDays, this.appliedPriceTips), 200);
    }

    @OnClick
    public void onClickDisclaimerButton() {
        ((HostCalendarUpdateActivity) getActivity()).showAboutSmartPricingFragment();
    }

    /* access modifiers changed from: private */
    public void save() {
        boolean hasChanges;
        Boolean newEnableSmartPricing = collectSmartPricingChange();
        Integer newPrice = collectPriceChange();
        AvailabilityType newAvailability = collectAvailabilityChange();
        if (newAvailability == null && newPrice == null && newEnableSmartPricing == null && !this.appliedPriceTips) {
            hasChanges = false;
        } else {
            hasChanges = true;
        }
        if (hasChanges) {
            this.saveButton.setState(AirButton.State.Loading);
            logEditChanges(newEnableSmartPricing, newPrice, newAvailability);
            showLoader(true);
            this.calendarStore.updateCalendar(this.listingId, this.selectedDays, newAvailability, newPrice, newEnableSmartPricing, null, this.calendarUpdateListener, this.appliedPriceTips);
            return;
        }
        getActivity().setResult(-1, new Intent().putExtra(NUM_OF_DAYS_EDITED, 0));
        getActivity().onBackPressed();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CalendarEditSheet;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.listingId);
    }

    private AvailabilityType collectAvailabilityChange() {
        return calculateStateChangeFromRadioRows(getAvailableState(), this.helper.getAvailabilityToggleValue());
    }

    private Boolean collectSmartPricingChange() {
        if (this.isSmartPricingEnabledForListing) {
            return calculateStateChange(getSmartPriceState(), this.helper.getDemandBasedPricingState());
        }
        return null;
    }

    private Boolean calculateStateChange(ToggleState currentState, ToggleState originalState) {
        if (currentState == originalState) {
            return null;
        }
        return Boolean.valueOf(currentState == ToggleState.ON);
    }

    private AvailabilityType calculateStateChangeFromRadioRows(AvailabilityType currentState, AvailabilityType originalState) {
        if (currentState == null || currentState.equals(originalState)) {
            return null;
        }
        return currentState;
    }

    /* access modifiers changed from: private */
    public Integer collectPriceChange() {
        Integer currentPriceValue = this.priceField.getAmount();
        if (this.appliedPriceTips || currentPriceValue == null || (currentPriceValue.equals(Integer.valueOf(this.helper.getMinPrice())) && currentPriceValue.equals(Integer.valueOf(this.helper.getMaxPrice())))) {
            return null;
        }
        return currentPriceValue;
    }

    private void setAvailabilityState(AvailabilityType availability) {
        this.availabilityRadioRowManager.setCurrentValue(availability);
    }

    private AvailabilityType getAvailableState() {
        return (AvailabilityType) this.availabilityRadioRowManager.getCurrentValue();
    }

    private void setSmartPriceState(ToggleState state) {
        if (this.helper.isMixedMode()) {
            this.smartPriceTriSwitch.setState(state);
        } else {
            this.smartPriceSwitch.setChecked(state == ToggleState.ON);
        }
    }

    private ToggleState getSmartPriceState() {
        if (this.helper.isMixedMode()) {
            return this.smartPriceTriSwitch.getState();
        }
        return this.smartPriceSwitch.isChecked() ? ToggleState.ON : ToggleState.OFF;
    }

    private void logEditChanges(Boolean newEnableSmartPricing, Integer newPrice, AvailabilityType newAvailability) {
        if (newAvailability != null) {
            this.jitneyLogger.editSheetChangeAvailabilitySaved(this.listingId, this.selectedDays, newAvailability.equals(AvailabilityType.Available));
        }
        if (newPrice != null) {
            this.jitneyLogger.editSheetChangePriceSaved(this.listingId, this.selectedDays, newPrice.longValue());
        }
        if (newEnableSmartPricing != null) {
            this.jitneyLogger.editSheetChangeSmartPricingSaved(this.listingId, this.selectedDays, newEnableSmartPricing.booleanValue());
        }
    }
}
