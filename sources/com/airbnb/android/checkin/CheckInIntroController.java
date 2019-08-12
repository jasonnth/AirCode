package com.airbnb.android.checkin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LargeIconRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class CheckInIntroController extends AirEpoxyController {
    private static final int TOP_PADDING = 48;
    private final String address;
    StandardRowEpoxyModel_ addressRow;
    private final List<CheckInInformation> checkInMethods;
    private final String checkInTime;
    private final Context context;
    DocumentMarqueeEpoxyModel_ header;
    LargeIconRowEpoxyModel_ iconRow;
    private final Listener listener;
    private final int numSteps;
    ToolbarSpacerEpoxyModel_ toolbarSpacer;
    ListSpacerEpoxyModel_ topPadding;
    private final int visibilityStatus;
    SimpleTextRowEpoxyModel_ visibleSoonTextRow;
    private final AirDateTime visibleStartTime;

    public interface Listener {
        void onAddressSelected(String str);
    }

    public CheckInIntroController(Context context2, int numSteps2, AirDateTime visibleStartTime2, int visibilityStatus2, String address2, String checkInTime2, List<CheckInInformation> checkInMethods2, Listener listener2) {
        this.context = context2;
        this.numSteps = numSteps2;
        this.visibilityStatus = visibilityStatus2;
        this.visibleStartTime = visibleStartTime2;
        this.address = address2;
        this.checkInTime = checkInTime2;
        this.checkInMethods = checkInMethods2;
        this.listener = listener2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (this.visibilityStatus == 0) {
            setupGuideIntroScreen();
        } else if (this.visibilityStatus == 1) {
            setupVisibleSoonScreen();
        } else if (this.visibilityStatus == 2) {
            setupTooLateScreen();
        } else {
            setupGenericUnavailableScreen();
        }
    }

    private void setupGuideIntroScreen() {
        setupHeader();
        this.addressRow.title((CharSequence) this.address).titleMaxLine(5).rowDrawable(getDirectionsIcon()).clickListener(CheckInIntroController$$Lambda$1.lambdaFactory$(this)).addIf(!TextUtils.isEmpty(this.address), (EpoxyController) this);
        addCheckInMethodRows();
    }

    private void setupVisibleSoonScreen() {
        setupHeader();
        this.visibleSoonTextRow.textRes(C5618R.string.check_in_visible_soon_description_no_inputs).addTo(this);
    }

    private void setupTooLateScreen() {
        setupHeader();
        this.visibleSoonTextRow.textRes(C5618R.string.check_in_past_visible_description).addTo(this);
    }

    private void setupGenericUnavailableScreen() {
        setupHeader();
        this.visibleSoonTextRow.textRes(C5618R.string.check_in_not_visible_description).addTo(this);
    }

    private void setupHeader() {
        boolean isVisible;
        if (this.visibilityStatus == 0) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        this.toolbarSpacer.addTo(this);
        this.topPadding.spaceHeight(48).addTo(this);
        this.iconRow.drawableRes(isVisible ? C5618R.C5619drawable.n2_ic_entire_place : C5618R.C5619drawable.n2_ic_stopwatch).addTo(this);
        switch (this.visibilityStatus) {
            case 0:
                this.header.titleText((CharSequence) this.context.getResources().getQuantityString(C5618R.plurals.x_steps_to_checkin, this.numSteps, new Object[]{Integer.valueOf(this.numSteps)}));
                break;
            case 1:
                this.header.titleText((CharSequence) this.context.getString(C5618R.string.check_in_visible_soon_title, new Object[]{getFormattedDate()}));
                break;
            case 2:
            case 3:
                this.header.titleText((CharSequence) this.context.getString(C5618R.string.check_in_past_visible_title));
                break;
        }
        if (!(this.checkInTime == null || this.visibilityStatus == 2)) {
            this.header.captionText((CharSequence) this.context.getString(C5618R.string.check_in_time_caption, new Object[]{this.checkInTime}));
        }
        this.header.withNoTopPaddingLayout().addTo(this);
    }

    private String getFormattedDate() {
        return this.visibleStartTime.formatDate(new SimpleDateFormat(this.context.getString(C5618R.string.full_month_day_format), Locale.getDefault()));
    }

    private void addCheckInMethodRows() {
        if (!ListUtils.isEmpty((Collection<?>) this.checkInMethods)) {
            for (CheckInInformation checkInMethod : this.checkInMethods) {
                if (((Boolean) SanitizeUtils.defaultIfNull(checkInMethod.isIsOptionAvailable(), Boolean.valueOf(false))).booleanValue() && !TextUtils.isEmpty(checkInMethod.getInstruction())) {
                    Amenity amenity = Amenity.forId(checkInMethod.getAmenityNumber());
                    if (amenity != null) {
                        String methodTitle = checkInMethod.getAmenity().getName();
                        new TextRowEpoxyModel_().m5686id((long) amenity.f8471id).text(SpannableUtils.makeBoldedSubString(methodTitle + ": " + checkInMethod.getInstruction(), this.context, methodTitle)).addTo(this);
                    }
                }
            }
        }
    }

    private Drawable getDirectionsIcon() {
        Drawable icon = this.context.getResources().getDrawable(C5618R.C5619drawable.n2_ic_map_marker_alt);
        DrawableCompat.setTint(icon, ContextCompat.getColor(this.context, C5618R.color.n2_babu));
        return icon;
    }
}
