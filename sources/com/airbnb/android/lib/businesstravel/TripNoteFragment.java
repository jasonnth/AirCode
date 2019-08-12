package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.activities.BookingActivityFacade;
import com.airbnb.android.booking.fragments.BookingBaseFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;
import icepick.State;

public class TripNoteFragment extends BookingBaseFragment {
    public static final String ARG_TRIP_NOTE = "trip_note";
    public static final String TAG = TripNoteFragment.class.getSimpleName();
    @BindView
    AirEditTextView editText;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    @State
    String tripNote;

    public static TripNoteFragment newInstanceWithTripNote(String tripNote2) {
        return (TripNoteFragment) ((FragmentBundleBuilder) FragmentBundler.make(new TripNoteFragment()).putString(ARG_TRIP_NOTE, tripNote2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_trip_note, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.tripNote == null) {
            this.tripNote = getArguments().getString(ARG_TRIP_NOTE);
        }
        if (this.tripNote == null || TextUtils.isEmpty(this.tripNote)) {
            this.editText.setHint(C0880R.string.bt_trip_note_hint);
        } else {
            this.editText.setText(this.tripNote);
            this.editText.setSelection(this.tripNote.length());
            this.editText.postDelayed(TripNoteFragment$$Lambda$1.lambdaFactory$(this), 200);
        }
        return view;
    }

    public void onDestroyView() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickSaveTripNote() {
        String tripNote2 = this.editText.getText().toString();
        BookingActivityFacade bookingActivity = getBookingActivity();
        if (TextUtils.isEmpty(tripNote2)) {
            tripNote2 = null;
        }
        bookingActivity.doneWithTripNote(tripNote2);
    }
}
