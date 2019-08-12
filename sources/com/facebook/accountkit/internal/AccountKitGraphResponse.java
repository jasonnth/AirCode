package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoggingBehavior;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

final class AccountKitGraphResponse {
    private static final IntegerRange HTTP_RANGE_SUCCESS = new IntegerRange(200, 299);
    private static final String TAG = "AccountKitGraphResponse";
    private final HttpURLConnection connection;
    private final AccountKitRequestError error;
    private final String rawResponse;
    private final AccountKitGraphRequest request;
    private final JSONArray responseArray;
    private final JSONObject responseObject;

    private static final class IntegerRange {
        private final int end;
        private final int start;

        private IntegerRange(int start2, int end2) {
            this.start = start2;
            this.end = end2;
        }

        public boolean contains(int value) {
            return this.start <= value && value <= this.end;
        }
    }

    public AccountKitGraphResponse(AccountKitGraphRequest request2, HttpURLConnection connection2, AccountKitRequestError error2) {
        this(request2, connection2, null, null, null, error2);
    }

    private AccountKitGraphResponse(AccountKitGraphRequest request2, HttpURLConnection connection2, String rawResponse2, JSONObject responseObject2, JSONArray responseArray2, AccountKitRequestError error2) {
        this.request = request2;
        this.connection = connection2;
        this.rawResponse = rawResponse2;
        this.responseObject = responseObject2;
        this.responseArray = responseArray2;
        this.error = error2;
    }

    public AccountKitRequestError getError() {
        return this.error;
    }

    public JSONObject getResponseObject() {
        return this.responseObject;
    }

    public JSONArray getResponseArray() {
        return this.responseArray;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public AccountKitGraphRequest getRequest() {
        return this.request;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    static AccountKitGraphResponse fromHttpConnection(HttpURLConnection connection2, AccountKitGraphRequest request2) {
        Throwable th;
        AccountKitGraphResponse accountKitGraphResponse;
        InputStream stream;
        InputStream stream2 = null;
        try {
            if (connection2.getResponseCode() >= 400) {
                stream = connection2.getErrorStream();
            } else {
                stream = connection2.getInputStream();
            }
            accountKitGraphResponse = createResponseFromStream(stream, connection2, request2);
            Utility.closeQuietly(stream);
        } catch (AccountKitException exception) {
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", exception);
            accountKitGraphResponse = new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(exception));
            Utility.closeQuietly(stream2);
        } catch (JSONException e) {
            th = e;
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", th);
            accountKitGraphResponse = new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(new AccountKitException(Type.SERVER_ERROR, th)));
            Utility.closeQuietly(stream2);
        } catch (IOException e2) {
            th = e2;
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", th);
            accountKitGraphResponse = new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(new AccountKitException(Type.SERVER_ERROR, th)));
            Utility.closeQuietly(stream2);
        } catch (SecurityException e3) {
            th = e3;
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", th);
            accountKitGraphResponse = new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(new AccountKitException(Type.SERVER_ERROR, th)));
            Utility.closeQuietly(stream2);
        } catch (Throwable th2) {
            Utility.closeQuietly(stream2);
            throw th2;
        }
        return accountKitGraphResponse;
    }

    private static AccountKitGraphResponse createResponseFromStream(InputStream stream, HttpURLConnection connection2, AccountKitGraphRequest request2) throws AccountKitException, JSONException, IOException {
        String responseString = Utility.readStreamToString(stream);
        ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response:\n%s\n", responseString);
        Object resultObject = new JSONTokener(responseString).nextValue();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("body", resultObject);
            jsonObject.put("code", connection2 != null ? connection2.getResponseCode() : 200);
            return createResponseFromObject(request2, connection2, jsonObject);
        } catch (IOException | JSONException e) {
            return new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE, (Throwable) e)));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00a3, code lost:
        if (r19.has(com.facebook.accountkit.internal.AccountKitGraphConstants.BODY_ERROR_REASON_KEY) != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.facebook.accountkit.internal.AccountKitRequestError checkResponseAndCreateError(org.json.JSONObject r20) {
        /*
            java.lang.String r1 = "code"
            r0 = r20
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x00dc }
            if (r1 == 0) goto L_0x00dd
            java.lang.String r1 = "code"
            r0 = r20
            int r2 = r0.getInt(r1)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "body"
            r0 = r20
            java.lang.Object r16 = com.facebook.accountkit.internal.Utility.getStringPropertyAsJSON(r0, r1)     // Catch:{ JSONException -> 0x00dc }
            if (r16 == 0) goto L_0x00c6
            r0 = r16
            boolean r1 = r0 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x00dc }
            if (r1 == 0) goto L_0x00c6
            r0 = r16
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x00dc }
            r19 = r0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = -1
            r4 = -1
            r18 = 0
            java.lang.String r1 = "error"
            r0 = r19
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x00dc }
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = "error"
            r0 = r19
            java.lang.Object r17 = com.facebook.accountkit.internal.Utility.getStringPropertyAsJSON(r0, r1)     // Catch:{ JSONException -> 0x00dc }
            org.json.JSONObject r17 = (org.json.JSONObject) r17     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "type"
            r8 = 0
            r0 = r17
            java.lang.String r5 = r0.optString(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "message"
            r8 = 0
            r0 = r17
            java.lang.String r6 = r0.optString(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "error_user_msg"
            r0 = r17
            java.lang.String r7 = r0.optString(r1)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "code"
            r8 = -1
            r0 = r17
            int r3 = r0.optInt(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "error_subcode"
            r8 = -1
            r0 = r17
            int r4 = r0.optInt(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            r18 = 1
        L_0x007b:
            if (r18 == 0) goto L_0x00c6
            com.facebook.accountkit.internal.AccountKitRequestError r1 = new com.facebook.accountkit.internal.AccountKitRequestError     // Catch:{ JSONException -> 0x00dc }
            r8 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x00dc }
        L_0x0083:
            return r1
        L_0x0084:
            java.lang.String r1 = "error_code"
            r0 = r19
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x00dc }
            if (r1 != 0) goto L_0x00a5
            java.lang.String r1 = "error_msg"
            r0 = r19
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x00dc }
            if (r1 != 0) goto L_0x00a5
            java.lang.String r1 = "error_reason"
            r0 = r19
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x00dc }
            if (r1 == 0) goto L_0x007b
        L_0x00a5:
            java.lang.String r1 = "error_reason"
            r8 = 0
            r0 = r19
            java.lang.String r5 = r0.optString(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "error_msg"
            r8 = 0
            r0 = r19
            java.lang.String r6 = r0.optString(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r1 = "error_code"
            r8 = -1
            r0 = r19
            int r3 = r0.optInt(r1, r8)     // Catch:{ JSONException -> 0x00dc }
            r18 = 1
            goto L_0x007b
        L_0x00c6:
            com.facebook.accountkit.internal.AccountKitGraphResponse$IntegerRange r1 = HTTP_RANGE_SUCCESS     // Catch:{ JSONException -> 0x00dc }
            boolean r1 = r1.contains(r2)     // Catch:{ JSONException -> 0x00dc }
            if (r1 != 0) goto L_0x00dd
            com.facebook.accountkit.internal.AccountKitRequestError r8 = new com.facebook.accountkit.internal.AccountKitRequestError     // Catch:{ JSONException -> 0x00dc }
            r10 = -1
            r11 = -1
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r9 = r2
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x00dc }
            r1 = r8
            goto L_0x0083
        L_0x00dc:
            r1 = move-exception
        L_0x00dd:
            r1 = 0
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AccountKitGraphResponse.checkResponseAndCreateError(org.json.JSONObject):com.facebook.accountkit.internal.AccountKitRequestError");
    }

    private static AccountKitGraphResponse createResponseFromObject(AccountKitGraphRequest request2, HttpURLConnection connection2, Object object) {
        try {
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                AccountKitRequestError requestError = checkResponseAndCreateError(jsonObject);
                if (requestError != null) {
                    return new AccountKitGraphResponse(request2, connection2, requestError);
                }
                Object body = Utility.getStringPropertyAsJSON(jsonObject, "body");
                if (body instanceof JSONObject) {
                    return new AccountKitGraphResponse(request2, connection2, body.toString(), (JSONObject) body, null, null);
                } else if (body instanceof JSONArray) {
                    return new AccountKitGraphResponse(request2, connection2, body.toString(), null, (JSONArray) body, null);
                } else {
                    object = JSONObject.NULL;
                }
            }
            if (object == JSONObject.NULL) {
                return new AccountKitGraphResponse(request2, connection2, object.toString(), null, null, null);
            }
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_OBJECT_TYPE_RESPONSE, object.getClass().getSimpleName());
        } catch (JSONException e) {
            return new AccountKitGraphResponse(request2, connection2, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE, (Throwable) e)));
        }
    }

    public String toString() {
        String responseCode;
        try {
            Locale locale = Locale.US;
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            responseCode = String.format(locale, str, objArr);
        } catch (IOException e) {
            responseCode = "unknown";
        }
        return "{Response:  responseCode: " + responseCode + ", responseObject: " + this.responseObject + ", error: " + this.error + "}";
    }
}
