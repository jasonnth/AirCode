package p315io.branch.referral.network;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.utils.AirbnbConstants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.PrefHelper;
import p315io.branch.referral.network.BranchRemoteInterface.BranchRemoteException;
import p315io.branch.referral.network.BranchRemoteInterface.BranchResponse;

/* renamed from: io.branch.referral.network.BranchRemoteInterfaceUrlConnection */
public class BranchRemoteInterfaceUrlConnection extends BranchRemoteInterface {
    PrefHelper prefHelper;

    BranchRemoteInterfaceUrlConnection(Context context) {
        this.prefHelper = PrefHelper.getInstance(context);
    }

    public BranchResponse doRestfulGet(String url) throws BranchRemoteException {
        return doRestfulGet(url, 0);
    }

    public BranchResponse doRestfulPost(String url, JSONObject payload) throws BranchRemoteException {
        return doRestfulPost(url, payload, 0);
    }

    private BranchResponse doRestfulGet(String url, int retryNumber) throws BranchRemoteException {
        BranchResponse doRestfulGet;
        HttpsURLConnection connection = null;
        try {
            int timeout = this.prefHelper.getTimeout();
            if (timeout <= 0) {
                timeout = 3000;
            }
            connection = (HttpsURLConnection) new URL(url + (url.contains("?") ? "&" : "?") + "retryNumber" + "=" + retryNumber).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            int responseCode = connection.getResponseCode();
            if (responseCode < 500 || retryNumber >= this.prefHelper.getRetryCount()) {
                if (responseCode != 200) {
                    try {
                        if (connection.getErrorStream() != null) {
                            doRestfulGet = new BranchResponse(getResponseString(connection.getErrorStream()), responseCode);
                            if (connection != null) {
                                connection.disconnect();
                            }
                            return doRestfulGet;
                        }
                    } catch (FileNotFoundException e) {
                        PrefHelper.Debug("BranchSDK", "A resource conflict occurred with this request " + url);
                        doRestfulGet = new BranchResponse(null, responseCode);
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }
                doRestfulGet = new BranchResponse(getResponseString(connection.getInputStream()), responseCode);
                if (connection != null) {
                    connection.disconnect();
                }
                return doRestfulGet;
            }
            try {
                Thread.sleep((long) this.prefHelper.getRetryInterval());
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            retryNumber++;
            doRestfulGet = doRestfulGet(url, retryNumber);
            if (connection != null) {
                connection.disconnect();
            }
            return doRestfulGet;
        } catch (SocketException ex) {
            PrefHelper.Debug(getClass().getSimpleName(), "Http connect exception: " + ex.getMessage());
            throw new BranchRemoteException(-113);
        } catch (SocketTimeoutException e3) {
            if (retryNumber < this.prefHelper.getRetryCount()) {
                try {
                    Thread.sleep((long) this.prefHelper.getRetryInterval());
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                doRestfulGet = doRestfulGet(url, retryNumber + 1);
                if (connection != null) {
                    connection.disconnect();
                }
            } else {
                throw new BranchRemoteException(AirbnbConstants.SUPERHERO_TEST_ID);
            }
        } catch (IOException ex2) {
            PrefHelper.Debug(getClass().getSimpleName(), "Branch connect exception: " + ex2.getMessage());
            throw new BranchRemoteException(-113);
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
    }

    private BranchResponse doRestfulPost(String url, JSONObject payload, int retryNumber) throws BranchRemoteException {
        BranchResponse branchResponse;
        HttpsURLConnection connection = null;
        int timeout = this.prefHelper.getTimeout();
        if (timeout <= 0) {
            timeout = 3000;
        }
        try {
            payload.put("retryNumber", retryNumber);
        } catch (JSONException e) {
        }
        try {
            connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(payload.toString());
            outputStreamWriter.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode < 500 || retryNumber >= this.prefHelper.getRetryCount()) {
                if (responseCode != 200) {
                    try {
                        if (connection.getErrorStream() != null) {
                            branchResponse = new BranchResponse(getResponseString(connection.getErrorStream()), responseCode);
                            if (connection != null) {
                                connection.disconnect();
                            }
                            return branchResponse;
                        }
                    } catch (FileNotFoundException e2) {
                        PrefHelper.Debug("BranchSDK", "A resource conflict occurred with this request " + url);
                        branchResponse = new BranchResponse(null, responseCode);
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }
                branchResponse = new BranchResponse(getResponseString(connection.getInputStream()), responseCode);
                if (connection != null) {
                    connection.disconnect();
                }
                return branchResponse;
            }
            try {
                Thread.sleep((long) this.prefHelper.getRetryInterval());
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            retryNumber++;
            branchResponse = doRestfulPost(url, payload, retryNumber);
            if (connection != null) {
                connection.disconnect();
            }
            return branchResponse;
        } catch (SocketTimeoutException e4) {
            if (retryNumber < this.prefHelper.getRetryCount()) {
                try {
                    Thread.sleep((long) this.prefHelper.getRetryInterval());
                } catch (InterruptedException e5) {
                    e5.printStackTrace();
                }
                branchResponse = doRestfulPost(url, payload, retryNumber + 1);
                if (connection != null) {
                    connection.disconnect();
                }
            } else {
                throw new BranchRemoteException(AirbnbConstants.SUPERHERO_TEST_ID);
            }
        } catch (IOException ex) {
            PrefHelper.Debug(getClass().getSimpleName(), "Http connect exception: " + ex.getMessage());
            throw new BranchRemoteException(-113);
        } catch (Exception ex2) {
            PrefHelper.Debug(getClass().getSimpleName(), "Exception: " + ex2.getMessage());
            if (VERSION.SDK_INT >= 11 && (ex2 instanceof NetworkOnMainThreadException)) {
                Log.i("BranchSDK", "Branch Error: Don't call our synchronous methods on the main thread!!!");
            }
            branchResponse = new BranchResponse(null, 500);
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
    }

    private String getResponseString(InputStream inputStream) {
        String responseString = null;
        if (inputStream == null) {
            return responseString;
        }
        try {
            return new BufferedReader(new InputStreamReader(inputStream)).readLine();
        } catch (IOException e) {
            return responseString;
        }
    }
}
