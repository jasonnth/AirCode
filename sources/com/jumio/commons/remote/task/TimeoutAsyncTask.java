package com.jumio.commons.remote.task;

import android.os.AsyncTask;
import android.os.Handler;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class TimeoutAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected String boundary = ("+++Android_JMSDK_mobile_" + System.currentTimeMillis() + "+++");
    private Handler handler = new Handler();
    protected final String lineEnd = "\r\n";
    protected HttpURLConnection mConnection;
    /* access modifiers changed from: private */
    public boolean timeout = false;
    private Runnable timeoutRunnable = new Runnable() {
        public void run() {
            TimeoutAsyncTask.this.timeout = true;
            try {
                if (TimeoutAsyncTask.this.mConnection != null) {
                    TimeoutAsyncTask.this.mConnection.disconnect();
                }
            } catch (Exception e) {
            }
            TimeoutAsyncTask.this.cancel(true);
        }
    };
    protected final String twoHyphens = "--";

    public interface OnCompleteHandler {
        void onTaskComplete(boolean z);
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
        onPostExecute(result);
    }

    /* access modifiers changed from: protected */
    public void stopTimeout() {
        if (this.handler != null) {
            this.handler.removeCallbacks(this.timeoutRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void checkHttpStatusAndStopTimeout(int httpStatus) throws IOException {
        if (this.handler != null) {
            this.handler.removeCallbacks(this.timeoutRunnable);
        }
        if (isTimeout()) {
            throw new UnexpectedResponseException(0, "Timed out");
        } else if (isCancelled()) {
            throw new UnexpectedResponseException(0, "Cancelled");
        } else if (httpStatus != 200) {
            throw new UnexpectedResponseException(httpStatus, this.mConnection.getResponseMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void startTimeout(int duration) {
        this.handler.postDelayed(this.timeoutRunnable, (long) duration);
    }

    public boolean isTimeout() {
        return this.timeout;
    }

    public void cancelHttpRequest() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (TimeoutAsyncTask.this.mConnection != null) {
                        TimeoutAsyncTask.this.mConnection.disconnect();
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }
}
