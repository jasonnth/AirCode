package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.CalendarHelper;
import com.airbnb.android.lib.utils.DrawableCompositor;
import com.airbnb.android.lib.utils.EasyCache;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseCalendarAdapter extends BaseAdapter {
    private static final SimpleDateFormat MONTH_NAME_FORMATTER = new SimpleDateFormat("MMM");
    private final int mCount;
    protected Calendar mEndTime;
    private final int mIndexOfFirstDay;
    private final int mIndexOfLastDay;
    protected final EasyCache<Integer, Long> mMillisCache;
    private final Calendar mMonth;
    private final Calendar mNextMonth;
    protected final Calendar mNow;
    private final Calendar mPrevMonth;
    private final Calendar mSelectionTest;
    protected Calendar mStartTime;

    public static class Slash extends Drawable {
        public static final float BOTTOM_DIVISOR = 0.05f;
        public static final int GAP_PIXELS = 4;
        public static final float TOP_DIVISOR = 0.25f;
        private int mAlpha;
        private final Paint mPaint = new Paint(1);
        private final State mState;
        private final Path path = new Path();

        public enum State {
            BEGIN,
            MIDDLE,
            END,
            NONE;

            public static Slash generateSlash(boolean yesterday, boolean today, int color, DrawableCompositor compositor) {
                State state;
                if (!today && yesterday) {
                    state = END;
                } else if (today && !yesterday) {
                    state = BEGIN;
                } else if (!today || !yesterday) {
                    state = NONE;
                } else {
                    state = MIDDLE;
                }
                Slash slash = null;
                if (state != NONE) {
                    slash = new Slash(state, color);
                }
                if (!(slash == null || compositor == null)) {
                    compositor.addDrawable(slash);
                }
                return slash;
            }
        }

        public Slash(State state, int color) {
            this.mPaint.setColor(color);
            this.mPaint.setStyle(Style.FILL);
            this.mState = state;
        }

        public void draw(Canvas canvas) {
            this.path.reset();
            int width = getBounds().width();
            int height = getBounds().height();
            switch (this.mState) {
                case BEGIN:
                    this.path.moveTo((((float) width) * 0.05f) + 2.0f, (float) height);
                    this.path.lineTo((float) width, (float) height);
                    this.path.lineTo((float) width, 0.0f);
                    this.path.lineTo((((float) width) * 0.25f) + 2.0f, 0.0f);
                    this.path.close();
                    break;
                case MIDDLE:
                    this.path.moveTo(0.0f, 0.0f);
                    this.path.lineTo((float) width, 0.0f);
                    this.path.lineTo((float) width, (float) height);
                    this.path.lineTo(0.0f, (float) height);
                    this.path.close();
                    break;
                case END:
                    this.path.moveTo(0.0f, 0.0f);
                    this.path.lineTo((((float) width) * 0.25f) - 2.0f, 0.0f);
                    this.path.lineTo((((float) width) * 0.05f) - 2.0f, (float) height);
                    this.path.lineTo(0.0f, (float) height);
                    this.path.close();
                    break;
            }
            canvas.drawPath(this.path, this.mPaint);
        }

        public int getOpacity() {
            return -1;
        }

        public void setAlpha(int alpha) {
            this.mAlpha = alpha;
            this.mPaint.setAlpha(alpha);
        }

        public void setColorFilter(ColorFilter cf) {
            this.mPaint.setColorFilter(cf);
        }
    }

    public BaseCalendarAdapter(Calendar month) {
        this.mMillisCache = new EasyCache<Integer, Long>() {
            public Long transform(Integer position) {
                return Long.valueOf(BaseCalendarAdapter.this.getItemId(position.intValue()));
            }
        };
        this.mMonth = month;
        this.mPrevMonth = (Calendar) this.mMonth.clone();
        this.mPrevMonth.add(2, -1);
        this.mNextMonth = (Calendar) this.mMonth.clone();
        this.mNextMonth.add(2, 1);
        this.mNow = Calendar.getInstance();
        this.mNow.set(11, 0);
        this.mNow.set(12, 0);
        this.mNow.set(13, 0);
        this.mMonth.set(5, 1);
        int firstDayOfMonth = this.mMonth.get(7);
        int daysInMonth = this.mMonth.getActualMaximum(5);
        int firstDayOfWeek = this.mMonth.getFirstDayOfWeek();
        if (firstDayOfMonth >= firstDayOfWeek) {
            this.mIndexOfFirstDay = firstDayOfMonth - firstDayOfWeek;
        } else {
            this.mIndexOfFirstDay = (7 - firstDayOfWeek) + firstDayOfMonth;
        }
        this.mIndexOfLastDay = (this.mIndexOfFirstDay + daysInMonth) - 1;
        int count = this.mIndexOfFirstDay + daysInMonth;
        if (count % 7 != 0) {
            this.mCount = ((count / 7) + 1) * 7;
        } else {
            this.mCount = count;
        }
        this.mSelectionTest = Calendar.getInstance();
    }

    public BaseCalendarAdapter(Calendar month, Calendar startTime, Calendar endTime) {
        this(month);
        this.mStartTime = startTime;
        this.mEndTime = endTime;
    }

    public int getCount() {
        return this.mCount;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        Calendar calendar = this.mMonth;
        if (position < this.mIndexOfFirstDay) {
            calendar = (Calendar) this.mPrevMonth.clone();
            calendar.set(5, (calendar.getActualMaximum(5) - (this.mIndexOfFirstDay - position)) + 1);
        } else if (position > this.mIndexOfLastDay) {
            calendar = (Calendar) this.mNextMonth.clone();
            calendar.set(5, position - this.mIndexOfLastDay);
        } else {
            calendar.set(5, (position - this.mIndexOfFirstDay) + 1);
        }
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(C0880R.layout.calendar_cell, parent, false);
        }
        TextView dateText = (TextView) convertView.findViewById(C0880R.C0882id.txt_date);
        convertView.setTag(dateText);
        int date = (position - this.mIndexOfFirstDay) + 1;
        int textColorId = C0880R.color.c_gray_1;
        int backgroundId = C0880R.C0881drawable.calendar_cell_unselected;
        if (position < this.mIndexOfFirstDay) {
            date = (this.mPrevMonth.getActualMaximum(5) - (this.mIndexOfFirstDay - position)) + 1;
        } else if (position > this.mIndexOfLastDay) {
            date = position - this.mIndexOfLastDay;
        }
        TextView month = (TextView) convertView.findViewById(C0880R.C0882id.month_name);
        if (position == this.mIndexOfFirstDay) {
            month.setVisibility(0);
            month.setText(MONTH_NAME_FORMATTER.format(this.mMonth.getTime()).toUpperCase());
        } else if (position == this.mIndexOfLastDay + 1) {
            month.setVisibility(0);
            month.setText(MONTH_NAME_FORMATTER.format(this.mNextMonth.getTime()).toUpperCase());
        } else {
            month.setVisibility(8);
        }
        this.mSelectionTest.setTimeInMillis(((Long) this.mMillisCache.getEntry(position, Integer.valueOf(position))).longValue());
        if (this.mSelectionTest.before(this.mNow)) {
            textColorId = C0880R.color.c_gray_3;
            convertView.setFocusable(true);
        } else {
            convertView.setFocusable(false);
        }
        dateText.setText(Integer.toString(date));
        dateText.setTextColor(context.getResources().getColor(textColorId));
        convertView.setBackgroundResource(backgroundId);
        convertView.getLayoutParams().height = (parent.getWidth() > 0 ? parent.getWidth() : parent.getMeasuredWidth()) / 7;
        return convertView;
    }

    /* access modifiers changed from: protected */
    public boolean isBeforeToday(int position) {
        this.mSelectionTest.setTimeInMillis(((Long) this.mMillisCache.getEntry(position, Integer.valueOf(position))).longValue());
        return this.mSelectionTest.before(this.mNow);
    }

    public boolean isToday(int position) {
        this.mSelectionTest.setTimeInMillis(((Long) this.mMillisCache.getEntry(position, Integer.valueOf(position))).longValue());
        return this.mSelectionTest.get(1) == this.mNow.get(1) && this.mSelectionTest.get(6) == this.mNow.get(6);
    }

    public Calendar getPreviousMonth() {
        return this.mPrevMonth;
    }

    public Calendar getNextMonth() {
        return this.mNextMonth;
    }

    public Calendar getThisMonth() {
        return this.mMonth;
    }

    public Calendar getStartTime() {
        return this.mStartTime;
    }

    public Calendar getEndTime() {
        return this.mEndTime;
    }

    public void setStartTime(Calendar startTime) {
        if (startTime == null) {
            this.mStartTime = null;
        } else if (!CalendarHelper.isSameDay(this.mStartTime, startTime)) {
            this.mStartTime = (Calendar) startTime.clone();
            this.mStartTime.set(11, 0);
            this.mStartTime.set(12, 0);
            this.mStartTime.set(13, 0);
        }
        notifyDataSetChanged();
    }

    public void setEndTime(Calendar endTime) {
        if (endTime == null) {
            this.mEndTime = null;
        } else if (!CalendarHelper.isSameDay(this.mEndTime, endTime)) {
            this.mEndTime = (Calendar) endTime.clone();
            this.mEndTime.set(11, 22);
            this.mEndTime.set(12, 0);
            this.mEndTime.set(13, 0);
        }
        notifyDataSetChanged();
    }

    public int getSelectionDuration() {
        return CalendarHelper.getStayDuration(this.mStartTime, this.mEndTime);
    }

    public void onDateSelected(long id) {
        this.mSelectionTest.setTimeInMillis(id);
        long selectionMillis = (long) ((int) CalendarHelper.roundDateToMidnight(this.mSelectionTest).getTimeInMillis());
        long startMillis = -1;
        if (this.mStartTime != null) {
            startMillis = (long) ((int) CalendarHelper.roundDateToMidnight(this.mStartTime).getTimeInMillis());
        }
        if (selectionMillis == startMillis) {
            setStartTime(null);
            setEndTime(null);
        } else if (this.mStartTime == null) {
            setStartTime(this.mSelectionTest);
        } else if (this.mEndTime != null) {
            setStartTime(this.mSelectionTest);
            setEndTime(null);
        } else if (this.mSelectionTest.before(this.mStartTime)) {
            setEndTime(this.mStartTime);
            setStartTime(this.mSelectionTest);
        } else if (!CalendarHelper.isSameDay(this.mStartTime, this.mSelectionTest)) {
            setEndTime(this.mSelectionTest);
        }
    }
}
