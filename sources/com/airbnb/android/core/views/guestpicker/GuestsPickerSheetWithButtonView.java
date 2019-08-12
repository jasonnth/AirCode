package com.airbnb.android.core.views.guestpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import icepick.Icepick;
import icepick.State;

public class GuestsPickerSheetWithButtonView extends LinearLayout {
    @BindView
    GuestsPickerView guestsPickerView;
    @BindView
    TextView infantDescriptionText;
    private Listener listener;
    @BindView
    FixedActionFooter saveButton;
    @State
    boolean showMaxGuestDescription = true;

    public interface Listener {
        void onSaveButtonClicked();
    }

    public enum Style {
        JELLYFISH(1, C0716R.layout.guests_picker_sheet_with_button_layout),
        WHITE(2, C0716R.layout.guests_picker_sheet_with_button_layout_white);
        
        final int layoutRes;
        final int value;

        private Style(int value2, int layoutRes2) {
            this.value = value2;
            this.layoutRes = layoutRes2;
        }

        static Style fromValue(int value2) {
            Style[] values;
            for (Style style : values()) {
                if (style.value == value2) {
                    return style;
                }
            }
            return JELLYFISH;
        }
    }

    public GuestsPickerSheetWithButtonView(Context context) {
        super(context);
        init(null);
    }

    public GuestsPickerSheetWithButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GuestsPickerSheetWithButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int styleIdx = 0;
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, C0716R.styleable.GuestsPickerSheetWithButtonView);
            styleIdx = ta.getInt(C0716R.styleable.GuestsPickerSheetWithButtonView_style, Style.JELLYFISH.value);
            ta.recycle();
        }
        ButterKnife.bind(this, inflate(getContext(), Style.fromValue(styleIdx).layoutRes, this));
        setOrientation(1);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void saveSelection() {
        if (this.guestsPickerView.exceedsMaxGuests()) {
            this.guestsPickerView.showMaxGuestsToast();
        } else if (this.listener != null) {
            this.listener.onSaveButtonClicked();
        }
    }

    public void shouldShowMaxGuestDescription(boolean showMaxGuestDescription2) {
        this.showMaxGuestDescription = showMaxGuestDescription2;
        updateMaxGuestsDescription();
    }

    public void setMaxGuestsCount(int maxCount) {
        this.guestsPickerView.setMaxGuestsCount(maxCount);
        updateMaxGuestsDescription();
    }

    private void updateMaxGuestsDescription() {
        ViewUtils.setVisibleIf((View) this.infantDescriptionText, this.showMaxGuestDescription);
        this.infantDescriptionText.setText(this.guestsPickerView.getMaxGuestsDescription());
    }

    public void showLoadingState(boolean isLoading) {
        this.saveButton.setButtonLoading(isLoading);
    }

    public void showSuccessState() {
        this.saveButton.setButtonLoading(false);
    }

    public void setShowBlockInstantBookWarning(boolean showBlockInstantBookWarning) {
        this.guestsPickerView.setShowBlockInstantBookWarning(showBlockInstantBookWarning);
    }

    public void showExpandedGuestsPicker(boolean showExpandedGuestsPicker) {
        this.guestsPickerView.showExpandedGuestsPicker(showExpandedGuestsPicker);
    }

    public void setGuestControls(GuestControls controls) {
        this.guestsPickerView.setGuestControls(controls);
    }

    public void setAllowInfants(boolean allow) {
        this.guestsPickerView.setAllowInfants(allow);
    }

    public void setAllowPets(boolean allow) {
        this.guestsPickerView.setAllowPets(allow);
    }

    public void setAllowChildren(boolean allow) {
        this.guestsPickerView.setAllowChildren(allow);
    }

    public boolean isAllowChildren() {
        return this.guestsPickerView.isAllowChildren();
    }

    public boolean isAllowInfants() {
        return this.guestsPickerView.isAllowInfants();
    }

    public void setButtonText(int resId) {
        this.saveButton.setButtonText(resId);
    }

    public void setNumberAdults(int adults) {
        this.guestsPickerView.setNumberAdults(adults);
    }

    public void setMinNumberAdults(int minAdults) {
        this.guestsPickerView.setMinNumberAdults(minAdults);
    }

    public int getNumberAdults() {
        return this.guestsPickerView.getNumberAdults();
    }

    public void setNumberChildren(int children) {
        this.guestsPickerView.setNumberChildren(children);
    }

    public int getNumberChildren() {
        return this.guestsPickerView.getNumberChildren();
    }

    public void setNumberInfants(int infants) {
        this.guestsPickerView.setNumberInfants(infants);
    }

    public int getNumberInfants() {
        return this.guestsPickerView.getNumberInfants();
    }

    public void setHasPets(boolean hasPets) {
        this.guestsPickerView.setHasPets(hasPets);
    }

    public boolean hasPets() {
        return this.guestsPickerView.hasPets();
    }

    public void setGuestsPickerListener(Listener listener2) {
        this.listener = listener2;
    }

    public GuestDetails getGuestData() {
        return this.guestsPickerView.getGuestData();
    }

    public void setGuestData(GuestDetails data) {
        this.guestsPickerView.setGuestData(data);
    }

    public void setGuestData(ReservationDetails data) {
        this.guestsPickerView.setGuestData(data);
    }

    public void dismissAllSnackBars() {
        this.guestsPickerView.dismissAllSnackBars();
    }
}
