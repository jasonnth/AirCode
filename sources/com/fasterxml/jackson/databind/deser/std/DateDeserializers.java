package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    @JacksonStdImpl
    public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        protected final Class<? extends Calendar> _calendarClass;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public CalendarDeserializer() {
            super(Calendar.class);
            this._calendarClass = null;
        }

        public CalendarDeserializer(Class<? extends Calendar> cc) {
            super(cc);
            this._calendarClass = cc;
        }

        public CalendarDeserializer(CalendarDeserializer src, DateFormat df, String formatString) {
            super(src, df, formatString);
            this._calendarClass = src._calendarClass;
        }

        /* access modifiers changed from: protected */
        public CalendarDeserializer withDateFormat(DateFormat df, String formatString) {
            return new CalendarDeserializer(this, df, formatString);
        }

        public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            Date d = _parseDate(p, ctxt);
            if (d == null) {
                return null;
            }
            if (this._calendarClass == null) {
                return ctxt.constructCalendar(d);
            }
            try {
                Calendar c = (Calendar) this._calendarClass.newInstance();
                c.setTimeInMillis(d.getTime());
                TimeZone tz = ctxt.getTimeZone();
                if (tz == null) {
                    return c;
                }
                c.setTimeZone(tz);
                return c;
            } catch (Exception e) {
                return (Calendar) ctxt.handleInstantiationProblem(this._calendarClass, d, e);
            }
        }
    }

    @JacksonStdImpl
    public static class DateDeserializer extends DateBasedDeserializer<Date> {
        public static final DateDeserializer instance = new DateDeserializer();

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public DateDeserializer() {
            super(Date.class);
        }

        public DateDeserializer(DateDeserializer base, DateFormat df, String formatString) {
            super(base, df, formatString);
        }

        /* access modifiers changed from: protected */
        public DateDeserializer withDateFormat(DateFormat df, String formatString) {
            return new DateDeserializer(this, df, formatString);
        }

        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return _parseDate(jp, ctxt);
        }
    }

    protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
        protected final DateFormat _customFormat;
        protected final String _formatString;

        /* access modifiers changed from: protected */
        public abstract DateBasedDeserializer<T> withDateFormat(DateFormat dateFormat, String str);

        protected DateBasedDeserializer(Class<?> clz) {
            super(clz);
            this._customFormat = null;
            this._formatString = null;
        }

        protected DateBasedDeserializer(DateBasedDeserializer<T> base, DateFormat format, String formatStr) {
            super(base._valueClass);
            this._customFormat = format;
            this._formatString = formatStr;
        }

        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            DateFormat df;
            if (property == null) {
                return this;
            }
            Value format = findFormatOverrides(ctxt, property, handledType());
            if (format == null) {
                return this;
            }
            TimeZone tz = format.getTimeZone();
            if (format.hasPattern()) {
                String pattern = format.getPattern();
                SimpleDateFormat df2 = new SimpleDateFormat(pattern, format.hasLocale() ? format.getLocale() : ctxt.getLocale());
                if (tz == null) {
                    tz = ctxt.getTimeZone();
                }
                df2.setTimeZone(tz);
                return withDateFormat(df2, pattern);
            } else if (tz == null) {
                return this;
            } else {
                DateFormat df3 = ctxt.getConfig().getDateFormat();
                if (df3.getClass() == StdDateFormat.class) {
                    df = ((StdDateFormat) df3).withTimeZone(tz).withLocale(format.hasLocale() ? format.getLocale() : ctxt.getLocale());
                } else {
                    df = (DateFormat) df3.clone();
                    df.setTimeZone(tz);
                }
                return withDateFormat(df, this._formatString);
            }
        }

        /* access modifiers changed from: protected */
        public Date _parseDate(JsonParser p, DeserializationContext ctxt) throws IOException {
            Date parse;
            if (this._customFormat != null) {
                JsonToken t = p.getCurrentToken();
                if (t == JsonToken.VALUE_STRING) {
                    String str = p.getText().trim();
                    if (str.length() == 0) {
                        return (Date) getEmptyValue(ctxt);
                    }
                    synchronized (this._customFormat) {
                        try {
                            parse = this._customFormat.parse(str);
                        } catch (ParseException e) {
                            return (Date) ctxt.handleWeirdStringValue(handledType(), str, "expected format \"%s\"", this._formatString);
                        }
                    }
                    return parse;
                } else if (t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    p.nextToken();
                    Date parsed = _parseDate(p, ctxt);
                    if (p.nextToken() != JsonToken.END_ARRAY) {
                        handleMissingEndArrayForSingle(p, ctxt);
                    }
                    return parsed;
                }
            }
            return super._parseDate(p, ctxt);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer<java.sql.Date> {
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public SqlDateDeserializer() {
            super(java.sql.Date.class);
        }

        public SqlDateDeserializer(SqlDateDeserializer src, DateFormat df, String formatString) {
            super(src, df, formatString);
        }

        /* access modifiers changed from: protected */
        public SqlDateDeserializer withDateFormat(DateFormat df, String formatString) {
            return new SqlDateDeserializer(this, df, formatString);
        }

        public java.sql.Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            Date d = _parseDate(jp, ctxt);
            if (d == null) {
                return null;
            }
            return new java.sql.Date(d.getTime());
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public TimestampDeserializer() {
            super(Timestamp.class);
        }

        public TimestampDeserializer(TimestampDeserializer src, DateFormat df, String formatString) {
            super(src, df, formatString);
        }

        /* access modifiers changed from: protected */
        public TimestampDeserializer withDateFormat(DateFormat df, String formatString) {
            return new TimestampDeserializer(this, df, formatString);
        }

        public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            Date d = _parseDate(jp, ctxt);
            if (d == null) {
                return null;
            }
            return new Timestamp(d.getTime());
        }
    }

    static {
        for (Class name : new Class[]{Calendar.class, GregorianCalendar.class, java.sql.Date.class, Date.class, Timestamp.class}) {
            _classNames.add(name.getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> rawType, String clsName) {
        if (_classNames.contains(clsName)) {
            if (rawType == Calendar.class) {
                return new CalendarDeserializer();
            }
            if (rawType == Date.class) {
                return DateDeserializer.instance;
            }
            if (rawType == java.sql.Date.class) {
                return new SqlDateDeserializer();
            }
            if (rawType == Timestamp.class) {
                return new TimestampDeserializer();
            }
            if (rawType == GregorianCalendar.class) {
                return new CalendarDeserializer(GregorianCalendar.class);
            }
        }
        return null;
    }
}
