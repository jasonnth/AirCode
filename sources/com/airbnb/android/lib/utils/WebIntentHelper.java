package com.airbnb.android.lib.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.mparticle.commerce.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class WebIntentHelper {
    private static final String DAY = "(0[1-9]|[12][0-9]|3[01])";
    private static final String MONTH = "(0[1-9]|1[012])";
    private static final String SEPARATOR = "\\D";
    private static final String YEAR = "20[1-2][0-9]";

    public enum DateParamHelper {
        DATE_PARAM_ISO("yyyy-MM-dd", "20[1-2][0-9]\\D(0[1-9]|1[012])\\D(0[1-9]|[12][0-9]|3[01])"),
        DATE_PARAM_USA("MM-dd-yyyy", "(0[1-9]|1[012])\\D(0[1-9]|[12][0-9]|3[01])\\D20[1-2][0-9]"),
        DATE_PARAM_ALTERNATIVE("dd-MM-yyyy", "(0[1-9]|[12][0-9]|3[01])\\D(0[1-9]|1[012])\\D20[1-2][0-9]");
        
        final Pattern pattern;
        public final SimpleDateFormat simpleDateFormat;

        private DateParamHelper(String dateFormat, String regexForPattern) {
            this.simpleDateFormat = new SimpleDateFormat(dateFormat);
            this.pattern = Pattern.compile(regexForPattern);
        }

        private boolean matches(String dateString) {
            return this.pattern.matcher(dateString).matches();
        }

        private Date parse(String dateString) throws ParseException {
            return this.simpleDateFormat.parse(dateString);
        }

        public static AirDate getDateForString(String dateString, boolean useAmericanDateFormat) {
            String format;
            if (TextUtils.isEmpty(dateString)) {
                return null;
            }
            String originalDateString = dateString;
            SimpleDateFormat loggingDateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);
            String dateString2 = dateString.replaceAll(WebIntentHelper.SEPARATOR, "-");
            Date resultDate = null;
            if (useAmericanDateFormat) {
                try {
                    if (DATE_PARAM_USA.matches(dateString2)) {
                        resultDate = DATE_PARAM_USA.parse(dateString2);
                    }
                } catch (ParseException e) {
                }
            } else if (DATE_PARAM_ISO.matches(dateString2)) {
                resultDate = DATE_PARAM_ISO.parse(dateString2);
            } else if (DATE_PARAM_ALTERNATIVE.matches(dateString2)) {
                resultDate = DATE_PARAM_ALTERNATIVE.parse(dateString2);
            } else if (DATE_PARAM_USA.matches(dateString2)) {
                resultDate = DATE_PARAM_USA.parse(dateString2);
            }
            String str = "android_eng";
            Strap kv = Strap.make().mo11639kv("web_date_format", "v2").mo11639kv("original", originalDateString);
            String str2 = "parsed_as";
            if (resultDate == null) {
                format = "null";
            } else {
                format = loggingDateFormat.format(resultDate);
            }
            AirbnbEventLogger.track(str, kv.mo11639kv(str2, format).mo11640kv("american", useAmericanDateFormat));
            if (resultDate != null) {
                AirDate date = DateHelper.toAirDate(resultDate);
                if (date.isSameDayOrAfter(AirDate.today())) {
                    return date;
                }
            }
            return null;
        }
    }

    public static AirDate parseCheckIn(Uri uri) {
        return DateParamHelper.getDateForString(uri.getQueryParameter(UpdateReviewRequest.KEY_CHECKIN), "www.airbnb.com".equals(uri.getHost()));
    }

    public static AirDate parseCheckOut(Uri uri) {
        return DateParamHelper.getDateForString(uri.getQueryParameter(Product.CHECKOUT), "www.airbnb.com".equals(uri.getHost()));
    }

    public static int parseBedCount(Uri uri) {
        return parseIntParam(uri, "min_beds", 0);
    }

    public static int parseBathroomCount(Uri uri) {
        return parseIntParam(uri, "min_bathrooms", 0);
    }

    public static int parseBedRoomCount(Uri uri) {
        return parseIntParam(uri, "min_bedrooms", 0);
    }

    public static ImmutableList<C6120RoomType> parseRoomTypes(Uri uri) {
        return FluentIterable.from((Iterable<E>) uri.getQueryParameters("room_types[]")).transform(WebIntentHelper$$Lambda$1.lambdaFactory$()).transform(WebIntentHelper$$Lambda$2.lambdaFactory$()).filter(WebIntentHelper$$Lambda$3.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$parseRoomTypes$1(C6120RoomType type) {
        return type != null;
    }

    public static ImmutableList<PrimaryCategory> parseExperienceCategories(Uri uri) {
        return FluentIterable.from((Iterable<E>) uri.getQueryParameters("experience_categories[]")).transform(WebIntentHelper$$Lambda$4.lambdaFactory$()).transform(WebIntentHelper$$Lambda$5.lambdaFactory$()).filter(WebIntentHelper$$Lambda$6.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$parseExperienceCategories$3(PrimaryCategory type) {
        return type != null;
    }

    public static boolean parseInstantBook(Uri uri) {
        return "true".equals(uri.getQueryParameter("ib"));
    }

    public static int parseGuestCount(Uri uri) {
        return parseIntParam(uri, FindTweenAnalytics.GUESTS, 1);
    }

    public static String parseTabId(Uri uri) {
        return uri.getQueryParameter("tab");
    }

    public static int parseAdultCount(Uri uri) {
        return parseIntParam(uri, FindTweenAnalytics.ADULTS, 0);
    }

    public static int parseChildCount(Uri uri) {
        return parseIntParam(uri, FindTweenAnalytics.CHILDREN, 0);
    }

    public static int parseInfantCount(Uri uri) {
        return parseIntParam(uri, FindTweenAnalytics.INFANTS, 0);
    }

    protected static int parseIntParam(Uri uri, String key, int defaultValue) {
        String stringValue = uri.getQueryParameter(key);
        try {
            if (!TextUtils.isEmpty(stringValue)) {
                return Integer.parseInt(stringValue);
            }
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
