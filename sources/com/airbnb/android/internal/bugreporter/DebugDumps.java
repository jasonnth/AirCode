package com.airbnb.android.internal.bugreporter;

import android.content.Context;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.p309io.CharStreams;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DebugDumps {
    private static final String FILENAME_BREADCRUMBS = "breadcrumbs.txt";
    private static final String FILENAME_EXPERIMENTS = "experiments.txt";
    private static final String FILENAME_LOGCAT = "logcat.txt";
    private static final String LOGCAT_COMMAND = "logcat -v threadtime -d";
    private static final String TAG = "DebugDumps";

    public static ArrayList<String> createFiles(Context context, ExperimentsProvider experimentsProvider) {
        return Lists.newArrayList((Iterable<? extends E>) FluentIterable.m1282of(createLogcatFile(context), createExperimentsFile(context, experimentsProvider), createBreadcrumbsFile(context)).filter(DebugDumps$$Lambda$1.lambdaFactory$()));
    }

    static /* synthetic */ boolean lambda$createFiles$0(String path) {
        return path != null;
    }

    public static String createLogcatFile(Context context) {
        return writeToExternalFile(context, FILENAME_LOGCAT, getLogcatOutput());
    }

    private static String createExperimentsFile(Context context, ExperimentsProvider provider) {
        return writeToExternalFile(context, FILENAME_EXPERIMENTS, provider.getExperimentDebugOutput());
    }

    private static String createBreadcrumbsFile(Context context) {
        return writeToExternalFile(context, FILENAME_BREADCRUMBS, Joiner.m1896on("\n").join((Iterable<?>) BugsnagWrapper.getBreadcrumbs()));
    }

    private static String writeToExternalFile(Context context, String filename, String content) {
        String str = null;
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            C0715L.m1198w(TAG, "Cache directory could not be retrieved");
            return str;
        }
        try {
            File file = new File(externalCacheDir, filename);
            FileWriter writer = new FileWriter(file, false);
            writer.write(content);
            writer.close();
            return file.getPath();
        } catch (IOException e) {
            C0715L.m1192e(TAG, "Failed writing to file: " + filename, e);
            return str;
        }
    }

    private static String getLogcatOutput() {
        try {
            return Joiner.m1896on("\n").join((Iterable<?>) CharStreams.readLines(new InputStreamReader(Runtime.getRuntime().exec(LOGCAT_COMMAND).getInputStream())));
        } catch (IOException e) {
            Log.e(TAG, "Error reading input stream", e);
            return "Error reading input stream: " + e;
        }
    }
}
