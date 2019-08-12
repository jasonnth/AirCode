package com.airbnb.android.hostcalendar;

import android.content.res.Resources;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.airbnb.android.core.models.CalendarDay.Type;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.lib.utils.GiftCardUtils;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CalendarUpdateHelper {
    static final int INVALID_PRICE = -1;
    private String currency;
    private final Type[] dayTypes;
    private int demandBasedPriceOffCount = 0;
    private int demandBasedPriceOnCount = 0;
    private final ArrayList<String> externalCalendarNotes = new ArrayList<>();
    private AirDate firstDate;
    private boolean hasAvailable = false;
    private boolean hasBookingWindowBlock = false;
    private boolean hasBusyRule = false;
    private boolean hasSmartPromo;
    private final ArrayList<String> hostNotes = new ArrayList<>();
    private AirDate lastDate;
    private int maxPrice = -1;
    private int maxPriceTip = -1;
    private int maxSmartPrice = -1;
    private int minPrice = -1;
    private int minPriceTip = -1;
    private int minSmartPrice = -1;
    private final int numDays;
    private NumberFormat priceFormat;

    /* renamed from: r */
    Resources f8926r;

    public CalendarUpdateHelper(Resources r, ArrayList<CalendarDay> daysToUpdate) {
        this.f8926r = r;
        this.numDays = daysToUpdate.size();
        setCurrency(GiftCardUtils.SUPPORTED_CURRENCY_USD);
        Set<Type> dayTypeSet = new HashSet<>();
        Iterator it = daysToUpdate.iterator();
        while (it.hasNext()) {
            CalendarDay day = (CalendarDay) it.next();
            updateFirstLastDate(day);
            updatePrice(day.getPriceInfo());
            dayTypeSet.add(updateDayType(day));
            if (day.getNotes().length() > 0) {
                this.hostNotes.add(day.getNotes());
            }
            this.hasSmartPromo = this.hasSmartPromo || day.getPromotion() != null;
        }
        this.dayTypes = (Type[]) dayTypeSet.toArray(new Type[dayTypeSet.size()]);
    }

    public boolean hasSmartPromo() {
        return this.hasSmartPromo;
    }

    public AirDate getFirstDate() {
        return this.firstDate;
    }

    public AirDate getLastDate() {
        return this.lastDate;
    }

    public int getMinPrice() {
        return this.minPrice;
    }

    public int getMaxPrice() {
        return this.maxPrice;
    }

    public int getMinSmartPrice() {
        return this.minSmartPrice;
    }

    public int getMaxSmartPrice() {
        return this.maxSmartPrice;
    }

    public int getMinPriceTip() {
        return this.minPriceTip;
    }

    public int getMaxPriceTip() {
        return this.maxPriceTip;
    }

    public boolean isSingleDay() {
        return this.numDays == 1;
    }

    public int getNumDays() {
        return this.numDays;
    }

    public boolean areConsecutiveDays() {
        return this.firstDate.getDaysUntil(this.lastDate) + 1 == this.numDays;
    }

    public boolean isAvailable() {
        return this.dayTypes.length == 1 && this.dayTypes[0] == Type.Available;
    }

    public boolean isBlockedByBookingWindow() {
        return this.dayTypes.length == 1 && this.dayTypes[0] == Type.MaxDaysNoticeBusy;
    }

    public boolean isMixedAvailability() {
        return (this.hasBookingWindowBlock || this.hasAvailable) && this.dayTypes.length > 1;
    }

    public ToggleState getAvailableState() {
        if (isAvailable()) {
            return ToggleState.ON;
        }
        if (isMixedAvailability()) {
            return ToggleState.NEITHER;
        }
        return ToggleState.OFF;
    }

    public AvailabilityType getAvailabilityToggleValue() {
        if (isAvailable()) {
            return AvailabilityType.Available;
        }
        if (isBlockedByBookingWindow()) {
            return AvailabilityType.UnavailableByBookingWindow;
        }
        if (!isMixedAvailability()) {
            return AvailabilityType.UnavailablePersistent;
        }
        return null;
    }

    public boolean isMixedMode() {
        return isMixedAvailability() || isMixedDemandBasedPricing();
    }

    public NumberFormat getPriceFormat() {
        return this.priceFormat;
    }

    public String getAvailableDescription() {
        String availableDescriptionString = "";
        if (this.dayTypes.length == 1) {
            switch (this.dayTypes[0]) {
                case Reserved:
                    return this.f8926r.getQuantityString(C6418R.plurals.calendar_availablity_reserved, this.numDays);
                case MinDaysNoticeBusy:
                    return this.f8926r.getQuantityString(C6418R.plurals.calendar_availablity_min_days_notice, this.numDays);
                case TurnoverDaysBusy:
                    return this.f8926r.getQuantityString(C6418R.plurals.calendar_availablity_turnover_days, this.numDays);
                case MaxDaysNoticeBusy:
                    return this.f8926r.getQuantityString(C6418R.plurals.calendar_availablity_max_days_notice, this.numDays);
                case ExpiredRequest:
                    availableDescriptionString = this.f8926r.getQuantityString(C6418R.plurals.calendar_availablity_expired_request, this.numDays);
                    break;
                case ExternalBusy:
                    break;
                default:
                    return availableDescriptionString;
            }
            ArrayList<String> externalCalendarNotes2 = getExternalCalendarNotes();
            if (externalCalendarNotes2.size() > 0) {
                return (String) externalCalendarNotes2.get(0);
            }
            return availableDescriptionString;
        } else if (this.hasBusyRule) {
            return this.f8926r.getString(C6418R.string.calendar_availablity_mixed_settings);
        } else {
            return availableDescriptionString;
        }
    }

    public ArrayList<String> getHostNotes() {
        return this.hostNotes;
    }

    public void resetHostNotes(String notes) {
        this.hostNotes.clear();
        if (notes != null && notes.length() > 0) {
            this.hostNotes.add(notes);
        }
    }

    public boolean hasNotes() {
        return !this.hostNotes.isEmpty();
    }

    public String getHostNotesAsString() {
        return TextUtils.join("\n", this.hostNotes);
    }

    public int getDemandBasedPriceOffCount() {
        return this.demandBasedPriceOffCount;
    }

    public boolean isDemandBasePriceEnabled() {
        return this.demandBasedPriceOffCount == 0;
    }

    public boolean isMixedDemandBasedPricing() {
        return this.demandBasedPriceOffCount > 0 && this.demandBasedPriceOnCount > 0;
    }

    public String getCurrency() {
        return this.currency;
    }

    public ToggleState getDemandBasedPricingState() {
        if (isDemandBasePriceEnabled()) {
            return ToggleState.ON;
        }
        if (isMixedDemandBasedPricing()) {
            return ToggleState.NEITHER;
        }
        return ToggleState.OFF;
    }

    private ArrayList<String> getExternalCalendarNotes() {
        return this.externalCalendarNotes;
    }

    private void updateFirstLastDate(CalendarDay day) {
        if (this.firstDate == null || this.firstDate.isAfter(day.getDate())) {
            this.firstDate = day.getDate();
        }
        if (this.lastDate == null || this.lastDate.isBefore(day.getDate())) {
            this.lastDate = day.getDate();
        }
    }

    private void updatePrice(CalendarDayPriceInfo priceInfo) {
        if (priceInfo != null) {
            int price = priceInfo.getNativePrice();
            this.minPrice = min(this.minPrice, price);
            this.maxPrice = max(this.maxPrice, price);
            int smartPrice = priceInfo.getRecommendedSmartPrice();
            this.minSmartPrice = min(this.minSmartPrice, smartPrice);
            this.maxSmartPrice = max(this.maxSmartPrice, smartPrice);
            int priceTip = priceInfo.getNativeSuggestedPrice();
            this.minPriceTip = min(this.minPriceTip, priceTip);
            this.maxPriceTip = max(this.maxPriceTip, priceTip);
            setCurrency(priceInfo.getNativeCurrency());
            if (priceInfo.getType() == CalendarDayPriceInfo.Type.DemandBased) {
                this.demandBasedPriceOnCount++;
            } else {
                this.demandBasedPriceOffCount++;
            }
        }
    }

    private void setCurrency(String nativeCurrency) {
        this.currency = nativeCurrency;
        this.priceFormat = IntegerNumberFormatHelper.forCurrency(Currency.getInstance(this.currency));
    }

    private Type updateDayType(CalendarDay day) {
        Type calendarDayType = day.getCalendarDayType();
        switch (calendarDayType) {
            case MinDaysNoticeBusy:
            case TurnoverDaysBusy:
                this.hasBusyRule = true;
                break;
            case MaxDaysNoticeBusy:
                this.hasBookingWindowBlock = true;
                this.hasBusyRule = true;
                break;
            case ExternalBusy:
                collectExternalCalendarNotes(this.f8926r, day);
                break;
            case Available:
                this.hasAvailable = true;
                break;
        }
        return calendarDayType;
    }

    private int min(int value1, int value2) {
        return value1 == -1 ? value2 : Math.min(value1, value2);
    }

    private int max(int value1, int value2) {
        return value1 == -1 ? value2 : Math.max(value1, value2);
    }

    private void collectExternalCalendarNotes(Resources r, CalendarDay calendarDay) {
        String notes = calendarDay.getExternalCalendarNotes(r);
        if (notes != null) {
            this.externalCalendarNotes.add(notes);
        }
    }
}
