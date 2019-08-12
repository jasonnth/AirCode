package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.mparticle.internal.o */
class C4622o extends JSONObject {
    C4622o(JSONObject jSONObject, Context context) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("cms");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = new JSONObject();
            if (jSONArray.getJSONObject(i).has("pr")) {
                JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("pr");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                    SharedPreferences sharedPreferences = context.getSharedPreferences(jSONObject3.getString("f"), jSONObject3.getInt("m"));
                    JSONArray jSONArray3 = jSONObject3.getJSONArray("ps");
                    Editor edit = sharedPreferences.edit();
                    for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                        int i4 = jSONArray3.getJSONObject(i3).getInt("t");
                        String string = jSONArray3.getJSONObject(i3).getString("k");
                        String string2 = jSONArray3.getJSONObject(i3).getString("n");
                        String str = "mp::" + string2;
                        if (sharedPreferences.contains(str)) {
                            jSONObject2.put(string2, sharedPreferences.getString(str, null));
                        } else {
                            String str2 = null;
                            if (sharedPreferences.contains(string)) {
                                switch (i4) {
                                    case 1:
                                        str2 = sharedPreferences.getString(string, null);
                                        break;
                                    case 2:
                                        str2 = Integer.toString(sharedPreferences.getInt(string, 0));
                                        break;
                                    case 3:
                                        str2 = Boolean.toString(sharedPreferences.getBoolean(string, false));
                                        break;
                                    case 4:
                                        str2 = Float.toString(sharedPreferences.getFloat(string, 0.0f));
                                        break;
                                    case 5:
                                        str2 = Long.toString(sharedPreferences.getLong(string, 0));
                                        break;
                                }
                            } else {
                                str2 = m2334a(jSONArray3.getJSONObject(i3).getString("d"));
                            }
                            edit.putString(str, str2);
                            edit.apply();
                            jSONObject2.put(string2, str2);
                        }
                    }
                }
            }
            put(Integer.toString(jSONArray.getJSONObject(i).getInt("id")), jSONObject2);
        }
    }

    /* renamed from: a */
    private static String m2334a(String str) {
        if (MPUtility.isEmpty(str) || !str.startsWith("%")) {
            return str;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.equalsIgnoreCase("%gn%")) {
            return UUID.randomUUID().toString().replace("-", "");
        }
        if (lowerCase.equals("%oaid%")) {
            return m2333a();
        }
        if (lowerCase.equals("%g%")) {
            return UUID.randomUUID().toString();
        }
        if (lowerCase.equals("%ts%")) {
            return Long.toString(System.currentTimeMillis());
        }
        if (lowerCase.equals("%glsb%")) {
            return Long.toString(UUID.randomUUID().getLeastSignificantBits());
        }
        return lowerCase;
    }

    /* renamed from: a */
    private static String m2333a() {
        String upperCase = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        Pattern compile = Pattern.compile("^[89A-F]");
        Pattern compile2 = Pattern.compile("^[4-9A-F]");
        Matcher matcher = compile.matcher(upperCase.substring(0, 16));
        Matcher matcher2 = compile2.matcher(upperCase.substring(16, 32));
        SecureRandom secureRandom = new SecureRandom();
        String replaceAll = matcher.replaceAll(String.valueOf(secureRandom.nextInt(7)));
        String replaceAll2 = matcher2.replaceAll(String.valueOf(secureRandom.nextInt(3)));
        StringBuilder sb = new StringBuilder(33);
        sb.append(replaceAll);
        sb.append("-");
        sb.append(replaceAll2);
        return sb.toString();
    }
}
