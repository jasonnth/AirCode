package com.airbnb.android.lib.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.PassportInformation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class GuestIdentifyInformationView extends LinearLayout {
    @BindView
    View additionalDetailsLayout;
    private boolean caretExpanded;
    @BindView
    GroupedCell fullNameCell;
    @BindView
    GroupedCell secondInfoCell;
    @BindView
    GroupedCell thirdInfoCell;

    public GuestIdentifyInformationView(Context context) {
        super(context);
        init(context);
    }

    public GuestIdentifyInformationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GuestIdentifyInformationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressLint({"SimpleDateFormat"})
    private void init(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(C0880R.layout.guest_information_section, this, true);
        if (!isInEditMode()) {
            ButterKnife.bind((View) this);
        }
        this.fullNameCell.getTooltip().setTooltipIcon(C0880R.C0881drawable.icon_expand_collapse_caret, C0880R.color.c_rausch, false);
    }

    @OnClick
    public void onFullNameClick(GroupedCell fullNameCell2) {
        boolean selected = !fullNameCell2.isSelected();
        fullNameCell2.setSelected(selected);
        ViewUtils.setVisibleIf(this.additionalDetailsLayout, selected);
        if (selected != this.caretExpanded) {
            this.caretExpanded = selected;
            fullNameCell2.getTooltip().startAnimation(AnimationUtils.loadAnimation(getContext(), selected ? C0880R.anim.rotate_counter_clockwise : C0880R.anim.rotate_clockwise));
        }
    }

    public void setGuestIdentification(GuestIdentity guestIdentity) {
        this.fullNameCell.setTitle((CharSequence) getResources().getString(C0880R.string.guest_identity_full_name, new Object[]{guestIdentity.getSurname(), guestIdentity.getGivenNames()}));
        this.secondInfoCell.setTitle(C0880R.string.guest_identification_type);
        switch (guestIdentity.getType()) {
            case Passport:
                this.secondInfoCell.setContent(C0880R.string.passport);
                this.thirdInfoCell.setTitle(C0880R.string.date_of_birth);
                this.thirdInfoCell.setContent((CharSequence) ((PassportInformation) guestIdentity).getLocalizedDateOfBirth());
                return;
            case ChineseNationalID:
                this.secondInfoCell.setContent(C0880R.string.chinese_national_identification);
                this.thirdInfoCell.setTitle(C0880R.string.guest_identification_number);
                this.thirdInfoCell.setContent((CharSequence) guestIdentity.getIdentityString());
                return;
            default:
                return;
        }
    }

    public void setTopBorderVisible(boolean visible) {
        this.fullNameCell.setTopBorderVisibility(visible ? 0 : 8);
    }
}
