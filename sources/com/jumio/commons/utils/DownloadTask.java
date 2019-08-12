package com.jumio.commons.utils;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import com.facebook.common.util.UriUtil;
import com.jumio.commons.log.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class DownloadTask extends AsyncTask<Void, Integer, byte[]> {
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final String TAG = "DownloadTask";
    private ProgressListener mListener;
    /* access modifiers changed from: private */
    public final Timer mTaskKiller;
    /* access modifiers changed from: private */
    public int mTimeout;
    private final String mUrl;

    public interface ProgressListener {
        void onProgressDone(byte[] bArr);

        void onProgressUpdate(float f);
    }

    private class TimeoutTimerTask extends TimerTask {
        private final AsyncTask mTask;

        public TimeoutTimerTask(AsyncTask _taskToKill) {
            this.mTask = _taskToKill;
        }

        public void run() {
            if (this.mTask != null && this.mTask.getStatus() != Status.FINISHED && !this.mTask.isCancelled()) {
                this.mTask.cancel(true);
                Log.m1924v(DownloadTask.TAG, "timer fired - killing task");
            }
        }
    }

    public DownloadTask(String resourceUrl) {
        this(resourceUrl, DEFAULT_TIMEOUT);
    }

    public DownloadTask(String resourceUrl, int timeout) {
        this.mUrl = resourceUrl;
        if (this.mUrl == null || !this.mUrl.startsWith(UriUtil.HTTP_SCHEME)) {
            Log.m1929w(TAG, "URL " + this.mUrl + " might not be valid");
        }
        this.mTaskKiller = new Timer("TimoutTaskKiller");
        this.mTimeout = timeout;
    }

    public void start() {
        execute(new Void[0]);
    }

    /* access modifiers changed from: protected */
    public byte[] doInBackground(Void... params) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        HttpURLConnection connection = null;
        byte[] buffer = null;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            URL url = new URL(this.mUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            int len = connection.getContentLength();
            if (connection.getResponseCode() != 200) {
                Log.m1914e(TAG, "Requesting file " + url.toString() + " returned " + connection.getResponseCode());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.m1915e(TAG, "", (Throwable) e);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        Log.m1915e(TAG, "", (Throwable) e2);
                    }
                }
                if (connection == null) {
                    return null;
                }
                try {
                    connection.disconnect();
                    return null;
                } catch (IOException e3) {
                    e = e3;
                }
            } else {
                inputStream = connection.getInputStream();
                buffer = new byte[1024];
                ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                int downloadedSize = 0;
                while (true) {
                    try {
                        int bufferlen = inputStream.read(buffer);
                        if (bufferlen <= 0) {
                            break;
                        }
                        outputStream2.write(buffer, 0, bufferlen);
                        downloadedSize += bufferlen;
                        publishProgress(new Integer[]{Integer.valueOf((downloadedSize * 100) / len)});
                    } catch (Throwable th) {
                        th = th;
                        outputStream = outputStream2;
                    }
                }
                buffer = outputStream2.toByteArray();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        Log.m1915e(TAG, "", (Throwable) e4);
                    }
                }
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                    } catch (IOException e5) {
                        Log.m1915e(TAG, "", (Throwable) e5);
                    }
                }
                if (connection != null) {
                    try {
                        connection.disconnect();
                    } catch (IOException e6) {
                        e = e6;
                        ByteArrayOutputStream byteArrayOutputStream = outputStream2;
                        Log.m1915e(TAG, "", (Throwable) e);
                        return buffer;
                    }
                }
                ByteArrayOutputStream byteArrayOutputStream2 = outputStream2;
                return buffer;
            }
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    Log.m1915e(TAG, "", (Throwable) e7);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e8) {
                    Log.m1915e(TAG, "", (Throwable) e8);
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.mTaskKiller.schedule(new TimeoutTimerTask(this), (long) this.mTimeout);
        Log.m1924v(TAG, "started timer at " + this.mTimeout + " s");
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.mListener != null) {
            this.mListener.onProgressUpdate((float) values[0].intValue());
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        if (this.mListener != null) {
            this.mListener.onProgressDone(bytes);
        }
        if (this.mTaskKiller != null) {
            this.mTaskKiller.cancel();
            this.mTaskKiller.purge();
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled(byte[] bytes) {
        super.onCancelled(bytes);
    }

    public byte[] getData() {
        try {
            if (this.mUrl != null) {
                return (byte[]) execute(new Void[0]).get();
            }
            throw new IllegalStateException("no Url was specified!");
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e2) {
            return null;
        } catch (CancellationException e3) {
            return null;
        }
    }

    public void setListener(ProgressListener mListener2) {
        this.mListener = mListener2;
    }

    public boolean exists() throws Exception {
        final Exception[] ex = new Exception[1];
        boolean result = false;
        try {
            result = ((Boolean) new AsyncTask<String, Integer, Boolean>() {
                /* access modifiers changed from: protected */
                public void onPreExecute() {
                    DownloadTask.this.mTaskKiller.schedule(new TimeoutTimerTask(this), (long) DownloadTask.this.mTimeout);
                    Log.m1924v(DownloadTask.TAG, "started timer at " + DownloadTask.this.mTimeout + " s");
                    super.onPreExecute();
                }

                /* access modifiers changed from: protected */
                public Boolean doInBackground(String... params) {
                    boolean b = true;
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        if (connection.getResponseCode() != 200) {
                            b = false;
                        }
                        return Boolean.valueOf(b);
                    } catch (Exception le) {
                        ex[0] = le;
                        Log.m1915e(DownloadTask.TAG, "", (Throwable) le);
                        return Boolean.valueOf(false);
                    }
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Boolean aBoolean) {
                    if (DownloadTask.this.mTaskKiller != null) {
                        Log.m1924v(DownloadTask.TAG, "killing timer");
                        DownloadTask.this.mTaskKiller.cancel();
                        DownloadTask.this.mTaskKiller.purge();
                    }
                }
            }.execute(new String[]{this.mUrl}).get()).booleanValue();
        } catch (Exception e) {
            Log.m1915e(TAG, "", (Throwable) e);
            ex[0] = e;
        }
        if (ex[0] == null) {
            return result;
        }
        throw new Exception(ex[0]);
    }
}
