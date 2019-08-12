package com.jumio.commons.log;

import android.os.Environment;
import com.jumio.commons.log.Log.LogLevel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    protected static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DEBUG_DIRECTORY = "/Debug/";
    protected static final String FILE_ANALYTICS_REQUESTS = "analytics.txt";
    protected static final String FILE_OCR_INFO = "OCRImageData.txt";
    protected static final String FILE_SERVER_REQUESTS = "requests.txt";
    public static final String NEW_LINE = "\r\n";
    protected static File logFolder;
    protected static File logFolderTemp;

    public static void init() {
        logFolder = null;
        if (Log.isFileLoggingActivated()) {
            logFolderTemp = getDebugFileRoot(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        }
    }

    public static void appendParameter(StringBuilder builder, String parameter, String value) {
        if (builder != null) {
            builder.append(parameter).append(value).append("\r\n");
        }
    }

    public static void appendParameter(StringBuilder builder, String parameter, long value) {
        if (builder != null) {
            builder.append(parameter).append(value).append("\r\n");
        }
    }

    public static void appendParameter(StringBuilder builder, String parameter, Date value) {
        if (builder != null) {
            builder.append(parameter).append(value).append("\r\n");
        }
    }

    public static void appendParameter(StringBuilder builder, String parameter, char[] value) {
        if (builder != null) {
            builder.append(parameter).append(value).append("\r\n");
        }
    }

    public static void setSesssionLogFolderName(String folderName) {
        if (Log.isFileLoggingActivated()) {
            renameLogFolder(folderName);
            logFolder = getDebugFileRoot(folderName);
        }
    }

    public static File getDebugFileRoot(String folderName) {
        File folder = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            folder = new File(Environment.getExternalStorageDirectory(), DEBUG_DIRECTORY + folderName);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
        }
        return folder;
    }

    public static void logServerRequest(String taskName, String request) {
        Log.m1918i(String.format("%s \r\nREQUEST: %s", new Object[]{taskName, request}) + "\r\n", getLogFolder(), FILE_SERVER_REQUESTS);
    }

    public static void logServerResponse(String taskName, int status, long time, String response) {
        Log.m1918i(String.format("%s (TIME: %d, STATUS: %d)\r\nRESPONSE: %s", new Object[]{taskName, Long.valueOf(time), Integer.valueOf(status), response}) + "\r\n", getLogFolder(), FILE_SERVER_REQUESTS);
    }

    public static File getSubFolder(String folderName) {
        boolean success;
        if (!Log.isFileLoggingActivated()) {
            return null;
        }
        File subFolder = new File((logFolder != null ? logFolder : logFolderTemp) + "/" + folderName);
        if (!subFolder.exists()) {
            success = subFolder.mkdir();
        } else {
            success = true;
        }
        if (!success) {
            subFolder = null;
        }
        return subFolder;
    }

    protected static File getLogFolder() {
        return logFolder != null ? logFolder : logFolderTemp;
    }

    private static void renameLogFolder(String sessionFolderName) {
        if (!Log.getLogLevel().equals(LogLevel.OFF) && Log.isFileLoggingActivated()) {
            File rootFolder = getDebugFileRoot("");
            if (rootFolder != null) {
                File fromFile = new File(rootFolder, getLogFolder().getName());
                File toFile = new File(rootFolder, sessionFolderName);
                if (fromFile.exists() && !toFile.exists()) {
                    fromFile.renameTo(toFile);
                }
            }
        }
    }
}
