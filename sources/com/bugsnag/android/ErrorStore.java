package com.bugsnag.android;

import android.content.Context;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Locale;

class ErrorStore {
    final Configuration config;
    final String path;

    ErrorStore(Configuration config2, Context appContext) {
        String path2;
        this.config = config2;
        try {
            path2 = appContext.getCacheDir().getAbsolutePath() + "/bugsnag-errors/";
            File outFile = new File(path2);
            outFile.mkdirs();
            if (!outFile.exists()) {
                Logger.warn("Could not prepare error storage directory");
                path2 = null;
            }
        } catch (Exception e) {
            Logger.warn("Could not prepare error storage directory", e);
            path2 = null;
        }
        this.path = path2;
    }

    /* access modifiers changed from: 0000 */
    public void flush() {
        if (this.path != null) {
            Async.run(new Runnable() {
                public void run() {
                    File exceptionDir = new File(ErrorStore.this.path);
                    if (exceptionDir.exists() && exceptionDir.isDirectory()) {
                        File[] errorFiles = exceptionDir.listFiles();
                        if (errorFiles != null && errorFiles.length > 0) {
                            Logger.info(String.format(Locale.US, "Sending %d saved error(s) to Bugsnag", new Object[]{Integer.valueOf(errorFiles.length)}));
                            for (File errorFile : errorFiles) {
                                try {
                                    HttpClient.post(ErrorStore.this.config.getEndpoint(), new Report(ErrorStore.this.config.getApiKey(), errorFile));
                                    Logger.info("Deleting sent error file " + errorFile.getName());
                                    if (!errorFile.delete()) {
                                        errorFile.deleteOnExit();
                                    }
                                } catch (NetworkException e) {
                                    Logger.warn("Could not send previously saved error(s) to Bugsnag, will try again later", e);
                                } catch (Exception e2) {
                                    Logger.warn("Problem sending unsent error from disk", e2);
                                    if (!errorFile.delete()) {
                                        errorFile.deleteOnExit();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void write(Error error) {
        if (this.path != null) {
            File exceptionDir = new File(this.path);
            if (exceptionDir.isDirectory()) {
                File[] files = exceptionDir.listFiles();
                if (files != null && files.length >= 100) {
                    Arrays.sort(files);
                    Logger.warn(String.format("Discarding oldest error as stored error limit reached (%s)", new Object[]{files[0].getPath()}));
                    if (!files[0].delete()) {
                        files[0].deleteOnExit();
                    }
                }
            }
            String filename = String.format(Locale.US, "%s%d.json", new Object[]{this.path, Long.valueOf(System.currentTimeMillis())});
            Writer out = null;
            try {
                Writer out2 = new FileWriter(filename);
                try {
                    JsonStream stream = new JsonStream(out2);
                    stream.value((Streamable) error);
                    stream.close();
                    Logger.info(String.format("Saved unsent error to disk (%s) ", new Object[]{filename}));
                    IOUtils.closeQuietly(out2);
                    FileWriter fileWriter = out2;
                } catch (Exception e) {
                    e = e;
                    out = out2;
                    try {
                        Logger.warn(String.format("Couldn't save unsent error to disk (%s) ", new Object[]{filename}), e);
                        IOUtils.closeQuietly(out);
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(out);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    out = out2;
                    IOUtils.closeQuietly(out);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                Logger.warn(String.format("Couldn't save unsent error to disk (%s) ", new Object[]{filename}), e);
                IOUtils.closeQuietly(out);
            }
        }
    }
}
