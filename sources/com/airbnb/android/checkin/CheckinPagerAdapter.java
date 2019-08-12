package com.airbnb.android.checkin;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInStep;

public class CheckinPagerAdapter extends FragmentStatePagerAdapter {
    private boolean forGuest;
    private CheckInGuide guide;

    public CheckinPagerAdapter(FragmentManager fm, CheckInGuide guide2, boolean forGuest2) {
        super(fm);
        this.guide = guide2;
        this.forGuest = forGuest2;
    }

    public void setGuide(CheckInGuide guide2) {
        this.guide = guide2;
        notifyDataSetChanged();
    }

    public int getItemPosition(Object object) {
        return -2;
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return CheckInIntroFragment.create(this.guide);
        }
        int stepIndex = position - 1;
        if (stepIndex < this.guide.getSteps().size()) {
            return CheckinStepFragment.create((CheckInStep) this.guide.getSteps().get(stepIndex), this.guide.hasLocalizedNotes(), stepIndex);
        }
        return CheckInActionFragment.create(this.guide);
    }

    public int getCount() {
        int count = this.guide.getSteps().size() + 1;
        if (this.forGuest) {
            return count + 1;
        }
        return count;
    }
}
