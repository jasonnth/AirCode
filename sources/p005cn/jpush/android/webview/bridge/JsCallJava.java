package p005cn.jpush.android.webview.bridge;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.google.gson.jpush.Gson;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.webview.bridge.JsCallJava */
public class JsCallJava {
    private static final String RETURN_RESULT_FORMAT = "{\"code\": %d, \"result\": %s}";
    private static final String TAG = "JsCallJava";
    private Gson mGson;
    private String mInjectedName;
    private HashMap<String, Method> mMethodsMap;
    private String mPreloadInterfaceJS;

    public JsCallJava(String injectedName, Class injectedCls) {
        try {
            if (TextUtils.isEmpty(injectedName)) {
                throw new Exception("injected name can not be null");
            }
            this.mInjectedName = injectedName;
            this.mMethodsMap = new HashMap<>();
            Method[] methods = injectedCls.getDeclaredMethods();
            StringBuilder sb = new StringBuilder("javascript:(function(b){console.log(\"");
            sb.append(this.mInjectedName);
            sb.append(" initialization begin\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
            for (Method method : methods) {
                if (method.getModifiers() == 9) {
                    String sign = genJavaMethodSign(method);
                    if (sign != null) {
                        this.mMethodsMap.put(sign, method);
                        sb.append(String.format("a.%s=", new Object[]{method.getName()}));
                    }
                }
            }
            sb.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
            sb.append(this.mInjectedName);
            sb.append(" call error, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var g=JSON.parse(prompt(JSON.stringify({method:f.shift(),types:e,args:f})));if(g.code!=200){throw\"");
            sb.append(this.mInjectedName);
            sb.append(" call error, code:\"+g.code+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
            sb.append(this.mInjectedName);
            sb.append("=a;console.log(\"");
            sb.append(this.mInjectedName);
            sb.append(" initialization end\")})(window);");
            this.mPreloadInterfaceJS = sb.toString();
            Logger.m1416d(TAG, "----------" + sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "init js error:" + e.getMessage());
        }
    }

    private String genJavaMethodSign(Method method) {
        String sign = method.getName();
        Class[] argsTypes = method.getParameterTypes();
        int len = argsTypes.length;
        if (len < 1 || argsTypes[0] != WebView.class) {
            Log.w(TAG, "method(" + sign + ") must use webview to be first parameter, will be pass");
            return null;
        }
        for (int k = 1; k < len; k++) {
            Class cls = argsTypes[k];
            if (cls == String.class) {
                sign = sign + "_S";
            } else if (cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) {
                sign = sign + "_N";
            } else if (cls == Boolean.TYPE) {
                sign = sign + "_B";
            } else if (cls == JSONObject.class) {
                sign = sign + "_O";
            } else {
                sign = sign + "_P";
            }
        }
        return sign;
    }

    public String getPreloadInterfaceJS() {
        return this.mPreloadInterfaceJS;
    }

    public String call(WebView webView, String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return getReturn(jsonStr, 500, "call data empty");
        }
        try {
            JSONObject callJson = new JSONObject(jsonStr);
            String methodName = callJson.getString("method");
            JSONArray argsTypes = callJson.getJSONArray("types");
            JSONArray argsVals = callJson.getJSONArray("args");
            String sign = methodName;
            int len = argsTypes.length();
            Object[] values = new Object[(len + 1)];
            int numIndex = 0;
            values[0] = webView;
            int k = 0;
            while (k < len) {
                String currType = argsTypes.optString(k);
                if ("string".equals(currType)) {
                    sign = sign + "_S";
                    values[k + 1] = argsVals.isNull(k) ? null : argsVals.getString(k);
                } else if ("number".equals(currType)) {
                    sign = sign + "_N";
                    numIndex = (numIndex * 10) + k + 1;
                } else if ("boolean".equals(currType)) {
                    sign = sign + "_B";
                    values[k + 1] = Boolean.valueOf(argsVals.getBoolean(k));
                } else if ("object".equals(currType)) {
                    sign = sign + "_O";
                    values[k + 1] = argsVals.isNull(k) ? null : argsVals.getJSONObject(k);
                } else {
                    sign = sign + "_P";
                }
                k++;
            }
            Method currMethod = (Method) this.mMethodsMap.get(sign);
            if (currMethod == null) {
                return getReturn(jsonStr, 500, "not found method(" + sign + ") with valid parameters");
            }
            if (numIndex > 0) {
                Class[] methodTypes = currMethod.getParameterTypes();
                while (numIndex > 0) {
                    int currIndex = numIndex - ((numIndex / 10) * 10);
                    Class currCls = methodTypes[currIndex];
                    if (currCls == Integer.TYPE) {
                        values[currIndex] = Integer.valueOf(argsVals.getInt(currIndex - 1));
                    } else if (currCls == Long.TYPE) {
                        values[currIndex] = Long.valueOf(Long.parseLong(argsVals.getString(currIndex - 1)));
                    } else {
                        values[currIndex] = Double.valueOf(argsVals.getDouble(currIndex - 1));
                    }
                    numIndex /= 10;
                }
            }
            return getReturn(jsonStr, 200, currMethod.invoke(null, values));
        } catch (Exception e) {
            if (e.getCause() != null) {
                return getReturn(jsonStr, 500, "method execute error:" + e.getCause().getMessage());
            }
            return getReturn(jsonStr, 500, "method execute error:" + e.getMessage());
        }
    }

    private String getReturn(String reqJson, int stateCode, Object result) {
        String insertRes;
        if (result == null) {
            insertRes = "null";
        } else if (result instanceof String) {
            insertRes = "\"" + ((String) result).replace("\"", "\\\"") + "\"";
        } else if ((result instanceof Integer) || (result instanceof Long) || (result instanceof Boolean) || (result instanceof Float) || (result instanceof Double) || (result instanceof JSONObject)) {
            insertRes = String.valueOf(result);
        } else {
            if (this.mGson == null) {
                this.mGson = new Gson();
            }
            insertRes = this.mGson.toJson(result);
        }
        String resStr = String.format(RETURN_RESULT_FORMAT, new Object[]{Integer.valueOf(stateCode), insertRes});
        Log.d(TAG, this.mInjectedName + " call json: " + reqJson + " result:" + resStr);
        return resStr;
    }
}
