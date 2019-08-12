package org.apache.commons.net.ntp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeStamp implements Serializable, Comparable<TimeStamp> {
    private final long ntpTime;
    private DateFormat simpleFormatter;

    public TimeStamp(long ntpTime2) {
        this.ntpTime = ntpTime2;
    }

    public long ntpValue() {
        return this.ntpTime;
    }

    public long getTime() {
        return getTime(this.ntpTime);
    }

    public Date getDate() {
        return new Date(getTime(this.ntpTime));
    }

    public static long getTime(long ntpTimeValue) {
        long seconds = (ntpTimeValue >>> 32) & 4294967295L;
        long fraction = Math.round((1000.0d * ((double) (ntpTimeValue & 4294967295L))) / 4.294967296E9d);
        if ((seconds & 2147483648L) == 0) {
            return 2085978496000L + (seconds * 1000) + fraction;
        }
        return -2208988800000L + (seconds * 1000) + fraction;
    }

    public static TimeStamp getNtpTime(long date) {
        return new TimeStamp(toNtpTime(date));
    }

    public static TimeStamp getCurrentTime() {
        return getNtpTime(System.currentTimeMillis());
    }

    protected static long toNtpTime(long t) {
        long baseTime;
        boolean useBase1 = t < 2085978496000L;
        if (useBase1) {
            baseTime = t - -2208988800000L;
        } else {
            baseTime = t - 2085978496000L;
        }
        long seconds = baseTime / 1000;
        long fraction = ((baseTime % 1000) * 4294967296L) / 1000;
        if (useBase1) {
            seconds |= 2147483648L;
        }
        return (seconds << 32) | fraction;
    }

    public int hashCode() {
        return (int) (this.ntpTime ^ (this.ntpTime >>> 32));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TimeStamp) || this.ntpTime != ((TimeStamp) obj).ntpValue()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return toString(this.ntpTime);
    }

    private static void appendHexString(StringBuilder buf, long l) {
        String s = Long.toHexString(l);
        for (int i = s.length(); i < 8; i++) {
            buf.append('0');
        }
        buf.append(s);
    }

    public static String toString(long ntpTime2) {
        StringBuilder buf = new StringBuilder();
        appendHexString(buf, (ntpTime2 >>> 32) & 4294967295L);
        buf.append('.');
        appendHexString(buf, ntpTime2 & 4294967295L);
        return buf.toString();
    }

    public String toDateString() {
        if (this.simpleFormatter == null) {
            this.simpleFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
            this.simpleFormatter.setTimeZone(TimeZone.getDefault());
        }
        return this.simpleFormatter.format(getDate());
    }

    public int compareTo(TimeStamp anotherTimeStamp) {
        long thisVal = this.ntpTime;
        long anotherVal = anotherTimeStamp.ntpTime;
        if (thisVal < anotherVal) {
            return -1;
        }
        return thisVal == anotherVal ? 0 : 1;
    }
}
