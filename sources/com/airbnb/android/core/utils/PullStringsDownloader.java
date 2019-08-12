package com.airbnb.android.core.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.requests.DownloadPhrasesRequest;
import com.airbnb.android.core.responses.DownloadPhrasesResponse;
import com.airbnb.android.utils.LocaleUtil;
import java.util.Map.Entry;

public class PullStringsDownloader {
    private final Context context;
    private final SharedPreferences stringsPreferences;

    public PullStringsDownloader(Context context2) {
        this.context = context2;
        this.stringsPreferences = CoreApplication.instance(context2).component().airbnbPreferences().getStringPreferences();
    }

    public void pullStrings() {
        newDownloadPhrasesRequest(this.context).execute(NetworkUtil.singleFireExecutor());
    }

    public BaseRequest<DownloadPhrasesResponse> newDownloadPhrasesRequest(Context context2) {
        final String locale = LocaleUtil.getCurrentDeviceLocale(context2).getLanguage();
        return DownloadPhrasesRequest.forLocale(locale).withListener(new NonResubscribableRequestListener<DownloadPhrasesResponse>() {
            public void onResponse(DownloadPhrasesResponse data) {
                PullStringsDownloader.this.addStringsToSharedPreferences(data, locale);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                BugsnagWrapper.notify((Throwable) new NetworkErrorException("Failed to download phrases"));
            }
        });
    }

    public void addStringsToSharedPreferences(DownloadPhrasesResponse data, String locale) {
        Editor editor = this.stringsPreferences.edit();
        editor.clear();
        for (Entry<String, String> stringEntry : data.phrases.entrySet()) {
            String fullStringKey = (String) stringEntry.getKey();
            int stringId = this.context.getResources().getIdentifier(fullStringKey.substring(fullStringKey.lastIndexOf(46) + 1), "string", this.context.getPackageName());
            if (stringId > 0) {
                editor.putString(String.valueOf(stringId), (String) stringEntry.getValue());
            }
        }
        editor.putString(PullStringsScheduler.FETCHED_LANGUAGE_KEY, locale);
        editor.apply();
    }
}
