package com.facebook.react.devsupport;

import android.util.Log;
import com.facebook.react.devsupport.JSCHeapCapture.CaptureCallback;
import com.facebook.react.devsupport.JSCHeapCapture.CaptureException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSCHeapUpload {
    public static CaptureCallback captureCallback(final String uploadUrl) {
        return new CaptureCallback() {
            public void onComplete(List<File> captures, List<CaptureException> failures) {
                for (CaptureException e : failures) {
                    Log.e("JSCHeapCapture", e.getMessage());
                }
                OkHttpClient httpClient = new Builder().build();
                for (File path : captures) {
                    httpClient.newCall(new Request.Builder().url(uploadUrl).method("POST", RequestBody.create(MediaType.parse("application/json"), path)).build()).enqueue(new Callback() {
                        public void onFailure(Call call, IOException e) {
                            Log.e("JSCHeapCapture", "Upload of heap capture failed: " + e.toString());
                        }

                        public void onResponse(Call call, Response response) throws IOException {
                            if (!response.isSuccessful()) {
                                Log.e("JSCHeapCapture", "Upload of heap capture failed with code: " + Integer.toString(response.code()));
                            }
                        }
                    });
                }
            }
        };
    }
}
