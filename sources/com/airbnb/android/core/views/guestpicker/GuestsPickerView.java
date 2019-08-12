package com.airbnb.android.core.views.guestpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.interfaces.StepperRowInterface;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.Icepick;
import icepick.State;

public class GuestsPickerView extends LinearLayout {
    public static final int DEFAULT_MAX_GUEST_COUNT = 16;
    private static final int MAX_CHILD_COUNT = 5;
    private static final int MAX_INFANT_COUNT = 5;
    private final OnValueChangedListener adultCountChangedListener;
    @BindView
    StepperRowInterface adultsStepperRow;
    @State
    boolean allowChildren;
    @State
    boolean allowInfants;
    @State
    boolean allowPets;
    private final OnValueChangedListener childCountChangedListener;
    @BindView
    StepperRowInterface childrenStepperRow;
    private final SnackbarWrapper exceededChildCountSnackbar;
    private final SnackbarWrapper exceededGuestCountSnackbar;
    private final SnackbarWrapper exceededInfantCountSnackbar;
    @State
    GuestDetails guestDetails;
    private final OnValueChangedListener infantCountChangedListener;
    private Snackbar infantsAndChildrenWarningSnackbar;
    @BindView
    StepperRowInterface infantsStepperRow;
    private OnValueChangeListener listener;
    private int maxGuestCount;
    @State
    int minAdults;
    @BindView
    SimpleTextRow noPetsTextView;
    private final OnCheckedChangeListener petStatusChangedListener;
    @BindView
    SwitchRowInterface petsSwitch;
    private boolean showBlockInstantBookWarning;

    public interface OnValueChangeListener {
        OnValueChangedListener getAdultChangedListener();

        OnValueChangedListener getChildChangedListener();

        OnValueChangedListener getInfantChangedListener();

        OnCheckedChangeListener getPetStatusChangedListener();
    }

    private enum Type {
        NORMAL(1, C0716R.layout.guests_picker_layout),
        SHEET(2, C0716R.layout.guests_picker_sheet_layout),
        WHITE(3, C0716R.layout.guests_picker_sheet_layout_white);
        
        final int layoutId;
        final int value;

        private Type(int value2, int layoutId2) {
            this.value = value2;
            this.layoutId = layoutId2;
        }

        static Type fromValue(int value2) {
            Type[] values;
            for (Type type : values()) {
                if (type.value == value2) {
                    return type;
                }
            }
            return NORMAL;
        }
    }

    public GuestsPickerView(Context context) {
        super(context);
        this.allowInfants = true;
        this.allowPets = true;
        this.allowChildren = true;
        this.maxGuestCount = 16;
        this.exceededGuestCountSnackbar = new SnackbarWrapper().view(this).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$1.lambdaFactory$()).duration(0);
        this.exceededChildCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.children_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$2.lambdaFactory$()).duration(0);
        this.exceededInfantCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.infant_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$3.lambdaFactory$()).duration(0);
        this.childCountChangedListener = GuestsPickerView$$Lambda$4.lambdaFactory$(this);
        this.adultCountChangedListener = GuestsPickerView$$Lambda$5.lambdaFactory$(this);
        this.infantCountChangedListener = GuestsPickerView$$Lambda$6.lambdaFactory$(this);
        this.petStatusChangedListener = GuestsPickerView$$Lambda$7.lambdaFactory$(this);
        init(null);
    }

    public GuestsPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.allowInfants = true;
        this.allowPets = true;
        this.allowChildren = true;
        this.maxGuestCount = 16;
        this.exceededGuestCountSnackbar = new SnackbarWrapper().view(this).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$8.lambdaFactory$()).duration(0);
        this.exceededChildCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.children_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$9.lambdaFactory$()).duration(0);
        this.exceededInfantCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.infant_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$10.lambdaFactory$()).duration(0);
        this.childCountChangedListener = GuestsPickerView$$Lambda$11.lambdaFactory$(this);
        this.adultCountChangedListener = GuestsPickerView$$Lambda$12.lambdaFactory$(this);
        this.infantCountChangedListener = GuestsPickerView$$Lambda$13.lambdaFactory$(this);
        this.petStatusChangedListener = GuestsPickerView$$Lambda$14.lambdaFactory$(this);
        init(attrs);
    }

    public GuestsPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.allowInfants = true;
        this.allowPets = true;
        this.allowChildren = true;
        this.maxGuestCount = 16;
        this.exceededGuestCountSnackbar = new SnackbarWrapper().view(this).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$15.lambdaFactory$()).duration(0);
        this.exceededChildCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.children_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$16.lambdaFactory$()).duration(0);
        this.exceededInfantCountSnackbar = new SnackbarWrapper().view(this).body(getResources().getString(C0716R.string.infant_count_exceeded_message)).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$17.lambdaFactory$()).duration(0);
        this.childCountChangedListener = GuestsPickerView$$Lambda$18.lambdaFactory$(this);
        this.adultCountChangedListener = GuestsPickerView$$Lambda$19.lambdaFactory$(this);
        this.infantCountChangedListener = GuestsPickerView$$Lambda$20.lambdaFactory$(this);
        this.petStatusChangedListener = GuestsPickerView$$Lambda$21.lambdaFactory$(this);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int typeIdx = 0;
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, C0716R.styleable.GuestsPickerView);
            typeIdx = ta.getInt(C0716R.styleable.GuestsPickerView_type, Type.NORMAL.value);
            ta.recycle();
        }
        ButterKnife.bind(this, inflate(getContext(), Type.fromValue(typeIdx).layoutId, this));
        setOrientation(1);
        this.adultsStepperRow.setValueChangedListener(this.adultCountChangedListener);
        setMinNumberAdults(GuestDetails.minNumAdults());
        this.childrenStepperRow.setValueChangedListener(this.childCountChangedListener);
        this.infantsStepperRow.setValueChangedListener(this.infantCountChangedListener);
        this.petsSwitch.setOnCheckedChangeListener(this.petStatusChangedListener);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
        if (this.guestDetails != null) {
            setGuestData(this.guestDetails);
        }
    }

    static /* synthetic */ void lambda$new$0(View v) {
    }

    static /* synthetic */ void lambda$new$1(View v) {
    }

    static /* synthetic */ void lambda$new$2(View v) {
    }

    static /* synthetic */ void lambda$new$3(GuestsPickerView guestsPickerView, int oldChildCount, int childCount) {
        boolean crossingWarningThreshold = true;
        guestsPickerView.checkMaxGuestCount(guestsPickerView.childrenStepperRow);
        if (!(oldChildCount == 0 && childCount == 1)) {
            crossingWarningThreshold = false;
        }
        if (crossingWarningThreshold) {
            guestsPickerView.showInfantsAndChildrenWarningIfNeeded(false);
        }
        if (childCount > 5) {
            guestsPickerView.childrenStepperRow.setValue(5);
            guestsPickerView.exceededChildCountSnackbar.buildAndShow();
        }
        guestsPickerView.invalidateMinAdultSetting();
        guestsPickerView.guestDetails = guestsPickerView.buildGuestData();
        if (guestsPickerView.listener != null) {
            guestsPickerView.listener.getChildChangedListener().onValueChanged(oldChildCount, childCount);
        }
    }

    static /* synthetic */ void lambda$new$4(GuestsPickerView guestsPickerView, int oldAdultCount, int adultCount) {
        guestsPickerView.checkMaxGuestCount(guestsPickerView.adultsStepperRow);
        guestsPickerView.guestDetails = guestsPickerView.buildGuestData();
        if (guestsPickerView.listener != null) {
            guestsPickerView.listener.getAdultChangedListener().onValueChanged(oldAdultCount, adultCount);
        }
    }

    static /* synthetic */ void lambda$new$5(GuestsPickerView guestsPickerView, int oldInfantCount, int infantCount) {
        if (oldInfantCount == 0 && infantCount == 1) {
            guestsPickerView.showInfantsAndChildrenWarningIfNeeded(true);
        }
        if (infantCount > 5) {
            guestsPickerView.infantsStepperRow.setValue(5);
            guestsPickerView.exceededInfantCountSnackbar.buildAndShow();
        }
        guestsPickerView.invalidateMinAdultSetting();
        guestsPickerView.guestDetails = guestsPickerView.buildGuestData();
        if (guestsPickerView.listener != null) {
            guestsPickerView.listener.getInfantChangedListener().onValueChanged(oldInfantCount, infantCount);
        }
    }

    static /* synthetic */ void lambda$new$6(GuestsPickerView guestsPickerView, SwitchRowInterface switchRowView, boolean isChecked) {
        guestsPickerView.invalidateMinAdultSetting();
        guestsPickerView.guestDetails = guestsPickerView.buildGuestData();
        if (guestsPickerView.listener != null) {
            guestsPickerView.listener.getPetStatusChangedListener().onCheckedChanged(switchRowView, isChecked);
        }
    }

    public void setListener(OnValueChangeListener listener2) {
        this.listener = listener2;
    }

    private void invalidateMinAdultSetting() {
        int tempMinAdults = 1;
        if (!(this.childrenStepperRow.getValue() > 0 || this.infantsStepperRow.getValue() > 0 || this.petsSwitch.isChecked()) || this.minAdults >= 1) {
            tempMinAdults = this.minAdults;
        }
        this.adultsStepperRow.setMinValue(tempMinAdults);
    }

    private void checkMaxGuestCount(StepperRowInterface row) {
        if (exceedsMaxGuests()) {
            row.setValue(row.getValue() - 1);
            showMaxGuestsToast();
        }
    }

    private void showInfantsAndChildrenWarningIfNeeded(boolean infantClicked) {
        boolean showChildrenWarning;
        boolean showInfantsWarning;
        if (this.showBlockInstantBookWarning) {
            if (getNumberChildren() <= 0 || isAllowChildren()) {
                showChildrenWarning = false;
            } else {
                showChildrenWarning = true;
            }
            if (getNumberInfants() <= 0 || isAllowInfants()) {
                showInfantsWarning = false;
            } else {
                showInfantsWarning = true;
            }
            String warning = null;
            if (showChildrenWarning && showInfantsWarning) {
                warning = getContext().getString(C0716R.string.host_needs_to_confirm_reservation_infants_and_children);
            } else if (infantClicked && showInfantsWarning) {
                warning = getContext().getString(C0716R.string.host_needs_to_confirm_reservation_infants);
            } else if (!infantClicked && showChildrenWarning) {
                warning = getContext().getString(C0716R.string.host_needs_to_confirm_reservation_children);
            }
            if (warning != null) {
                this.infantsAndChildrenWarningSnackbar = new SnackbarWrapper().view(this).action(getResources().getString(C0716R.string.dismiss), GuestsPickerView$$Lambda$22.lambdaFactory$()).duration(0).body(warning).buildAndShow();
            }
        }
    }

    static /* synthetic */ void lambda$showInfantsAndChildrenWarningIfNeeded$7(View v) {
    }

    private GuestDetails buildGuestData() {
        return new GuestDetails().adultsCount(getNumberAdults()).childrenCount(getNumberChildren()).infantsCount(getNumberInfants()).isBringingPets(hasPets());
    }

    public void setShowBlockInstantBookWarning(boolean showBlockInstantBookWarning2) {
        this.showBlockInstantBookWarning = showBlockInstantBookWarning2;
    }

    public void showExpandedGuestsPicker(boolean showExpandedGuestsPicker) {
        setChildrenStepperVisibility(showExpandedGuestsPicker);
        setInfantsStepperVisibility(showExpandedGuestsPicker);
        setPetsRowVisibility(showExpandedGuestsPicker);
        this.adultsStepperRow.setText(showExpandedGuestsPicker ? C0716R.string.adults : C0716R.string.guests);
    }

    public void setGuestControls(GuestControls controls) {
        setAllowInfants(controls.isAllowsInfants());
        setAllowPets(controls.isAllowsPets());
        setAllowChildren(controls.isAllowsChildren());
    }

    public void setAllowInfants(boolean allow) {
        this.allowInfants = allow;
    }

    public void setAllowPets(boolean allow) {
        ViewUtils.setVisibleIf(this.petsSwitch.getView(), allow);
        ViewUtils.setVisibleIf((View) this.noPetsTextView, !allow);
        this.allowPets = allow;
    }

    public void setAllowChildren(boolean allow) {
        this.allowChildren = allow;
    }

    public boolean isAllowChildren() {
        return this.allowChildren;
    }

    public boolean isAllowInfants() {
        return this.allowInfants;
    }

    public void setNumberAdults(int adults) {
        this.adultsStepperRow.setValue(adults);
    }

    public void setMinNumberAdults(int minAdults2) {
        this.minAdults = minAdults2;
        this.adultsStepperRow.setMinValue(minAdults2);
        invalidateMinAdultSetting();
    }

    public int getNumberAdults() {
        return this.adultsStepperRow.getValue();
    }

    public void setNumberChildren(int children) {
        this.childrenStepperRow.setValue(children);
    }

    public int getNumberChildren() {
        return this.childrenStepperRow.getValue();
    }

    public void setChildrenStepperVisibility(boolean show) {
        ViewUtils.setVisibleIf(this.childrenStepperRow.getView(), show);
    }

    public void setNumberInfants(int infants) {
        this.infantsStepperRow.setValue(infants);
    }

    public int getNumberInfants() {
        return this.infantsStepperRow.getValue();
    }

    public void setInfantsStepperVisibility(boolean show) {
        ViewUtils.setVisibleIf(this.infantsStepperRow.getView(), show);
    }

    public void setHasPets(boolean hasPets) {
        this.petsSwitch.setChecked(hasPets);
    }

    public boolean hasPets() {
        return this.petsSwitch.isChecked();
    }

    public void setPetsRowVisibility(boolean show) {
        boolean z;
        boolean z2 = true;
        View view = this.petsSwitch.getView();
        if (!show || !this.allowPets) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleIf(view, z);
        SimpleTextRow simpleTextRow = this.noPetsTextView;
        if (!show || this.allowPets) {
            z2 = false;
        }
        ViewUtils.setVisibleIf((View) simpleTextRow, z2);
    }

    public GuestDetails getGuestData() {
        return this.guestDetails;
    }

    public void setGuestData(GuestDetails data) {
        setNumberAdults(data.adultsCount());
        setNumberChildren(data.childrenCount());
        setNumberInfants(data.infantsCount());
        setHasPets(data.isBringingPets());
        this.guestDetails = buildGuestData();
    }

    public void setGuestData(ReservationDetails data) {
        setNumberAdults(data.numberOfAdults().intValue());
        setNumberChildren(data.numberOfChildren().intValue());
        setNumberInfants(data.numberOfInfants().intValue());
        setHasPets(data.isBringingPets().booleanValue());
        this.guestDetails = buildGuestData();
    }

    public void showMaxGuestsToast() {
        if (!this.exceededGuestCountSnackbar.isShown()) {
            this.exceededGuestCountSnackbar.buildAndShow();
        }
    }

    public boolean exceedsMaxGuests() {
        return this.childrenStepperRow.getValue() + this.adultsStepperRow.getValue() > this.maxGuestCount;
    }

    public void setMaxGuestsCount(int maxCount) {
        this.maxGuestCount = maxCount;
        this.exceededGuestCountSnackbar.body(getResources().getQuantityString(C0716R.plurals.guest_count_exceeded_message, this.maxGuestCount, new Object[]{Integer.valueOf(this.maxGuestCount)}));
    }

    public String getMaxGuestsDescription() {
        return getResources().getQuantityString(C0716R.plurals.infants_descriptions_with_x_guests_maximum, this.maxGuestCount, new Object[]{Integer.valueOf(this.maxGuestCount)});
    }

    public String getVerboseMaxGuestsDescription() {
        return getResources().getQuantityString(C0716R.plurals.how_many_guests_with_x_guests_maximum, this.maxGuestCount, new Object[]{Integer.valueOf(this.maxGuestCount)});
    }

    public void dismissAllSnackBars() {
        if (this.infantsAndChildrenWarningSnackbar != null && this.infantsAndChildrenWarningSnackbar.isShownOrQueued()) {
            this.infantsAndChildrenWarningSnackbar.dismiss();
        }
        this.exceededGuestCountSnackbar.dismiss();
        this.exceededChildCountSnackbar.dismiss();
        this.exceededInfantCountSnackbar.dismiss();
    }
}
