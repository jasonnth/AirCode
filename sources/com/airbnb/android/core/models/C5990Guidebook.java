package com.airbnb.android.core.models;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.generated.GenGuidebook;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;

/* renamed from: com.airbnb.android.core.models.Guidebook */
public class C5990Guidebook extends GenGuidebook {
    public static final Creator<C5990Guidebook> CREATOR = new Creator<C5990Guidebook>() {
        public C5990Guidebook[] newArray(int size) {
            return new C5990Guidebook[size];
        }

        public C5990Guidebook createFromParcel(Parcel source) {
            C5990Guidebook object = new C5990Guidebook();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String NATIVE_TOKEN = "SKSSNKWHOQJQETYPVD";

    public String getGuidebookUrl(Context context) {
        return context.getString(C0716R.string.airbnb_base_url) + getGuidebookPath() + "?is_header_hidden=true &native_token=" + NATIVE_TOKEN;
    }

    public Intent buildIntentForUrl(Context context) {
        String url = getGuidebookUrl(context);
        CoreApplication.instance(context).component().debugSettings();
        if (DebugSettings.REACT_NATIVE_GUIDEBOOKS.isEnabled() || Trebuchet.launch(TrebuchetKeys.RN_GUIDEBOOK)) {
            return ReactNativeIntents.intentForGuidebook(context, url);
        }
        return WebViewIntentBuilder.newBuilder(context, url).authenticate().title(getTitle()).toIntent();
    }
}
