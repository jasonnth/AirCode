package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.AirToolbar;

public class CalendarUpdateNotesFragment_ViewBinding implements Unbinder {
    private CalendarUpdateNotesFragment target;

    public CalendarUpdateNotesFragment_ViewBinding(CalendarUpdateNotesFragment target2, View source) {
        this.target = target2;
        target2.noteText = (EditText) Utils.findRequiredViewAsType(source, C6418R.C6420id.note_text, "field 'noteText'", EditText.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.calendarUpdateNotesFrame = (LinearLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.calendar_update_notes_frame, "field 'calendarUpdateNotesFrame'", LinearLayout.class);
    }

    public void unbind() {
        CalendarUpdateNotesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.noteText = null;
        target2.toolbar = null;
        target2.calendarUpdateNotesFrame = null;
    }
}
