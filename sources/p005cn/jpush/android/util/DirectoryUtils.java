package p005cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;
import java.util.regex.Pattern;

/* renamed from: cn.jpush.android.util.DirectoryUtils */
public class DirectoryUtils {
    public static final int MAX__RICH_FILE_NUM = 10;
    public static final String PATH_APP = "/1";
    public static final String PATH_VIDEO = "/2";
    public static final String RICH_PATH = (File.separator + "rich" + File.separator);
    public static final String SDCARD_PATH = "/Android/data/";
    private static final String TAG = "DirectoryUtils";

    public static void createPath(Context context) {
        try {
            if (AndroidUtil.isSdcardExist()) {
                String dir = getDir(context);
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(sdPath + dir);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                File file2 = new File(sdPath + dir + PATH_APP);
                if (!file2.isDirectory()) {
                    file2.mkdirs();
                }
                File file3 = new File(sdPath + dir + PATH_VIDEO);
                if (!file3.isDirectory()) {
                    file3.mkdirs();
                }
                File file4 = new File(sdPath + dir + File.separator + context.getPackageName());
                if (!file4.isDirectory()) {
                    file4.mkdirs();
                    return;
                }
                return;
            }
            Logger.m1420e(TAG, "sd card is not found !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRichStorageDir(Context context, String name) {
        String dirPath = context.getFilesDir() + RICH_PATH + name;
        try {
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirPath + "/";
    }

    public static String getStorageDir(Context context, String name) {
        String dirPath = context.getFilesDir() + "/" + name;
        try {
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirPath + "/";
    }

    public static String getDir(Context context) {
        File[] children;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String dir = preferences.getString("dir", "");
        if (TextUtils.isEmpty(dir)) {
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String prefix = SDCARD_PATH;
            String target = null;
            File dataDir = new File(sdPath + prefix);
            try {
                if (dataDir.exists()) {
                    ArrayList<String> folderList = new ArrayList<>();
                    for (File child : dataDir.listFiles()) {
                        if (child.isDirectory()) {
                            folderList.add(child.getName());
                            Logger.m1428v(TAG, "data dir: " + child.getName());
                        }
                    }
                    int size = folderList.size();
                    if (size > 0) {
                        target = prefix + ((String) folderList.get(size / 2));
                    } else {
                        target = prefix + UUID.randomUUID().toString().substring(0, 5);
                    }
                } else {
                    dataDir.mkdirs();
                    target = prefix + UUID.randomUUID().toString().substring(0, 5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Logger.m1424i(TAG, "The target dir: " + target);
            preferences.edit().putString("dir", target).commit();
        }
        return dir;
    }

    public static String getDirectoryAppPath(Context context) {
        if (!AndroidUtil.isSdcardExist()) {
            return "";
        }
        String kkpath = addFileSeparatorAtLast(Environment.getExternalStorageDirectory().getAbsolutePath()) + getDir(context) + PATH_APP;
        if (!new File(kkpath).isDirectory()) {
            createPath(context);
        }
        return kkpath + File.separator;
    }

    public static String addFileSeparatorAtLast(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        if (filePath.lastIndexOf(File.separator) != 0) {
            return filePath + File.separator;
        }
        return filePath;
    }

    public static String getDirectoryVideoPath(Context context) {
        if (!AndroidUtil.isSdcardExist()) {
            return "";
        }
        String kkpath = addFileSeparatorAtLast(Environment.getExternalStorageDirectory().getAbsolutePath()) + getDir(context) + PATH_VIDEO;
        if (!new File(kkpath).isDirectory()) {
            createPath(context);
        }
        return kkpath + "/";
    }

    public static String getDirectoryRichPush(Context context, String name) {
        try {
            if (AndroidUtil.isSdcardExist()) {
                String kkpath = Environment.getExternalStorageDirectory().getAbsolutePath() + SDCARD_PATH + context.getPackageName() + File.separator + name + File.separator;
                File file = new File(kkpath);
                if (file.exists()) {
                    return kkpath;
                }
                file.mkdirs();
                return kkpath;
            }
            deleteOldDir(context);
            return getRichStorageDir(context, name);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void deleteOldDir(Context context) {
        File file = new File(context.getFilesDir() + RICH_PATH);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length > 10) {
                Arrays.sort(files, new Comparator<File>() {
                    public int compare(File o1, File o2) {
                        if (o1.lastModified() > o2.lastModified()) {
                            return -1;
                        }
                        if (o1.lastModified() < o2.lastModified()) {
                            return 1;
                        }
                        return 0;
                    }
                });
                deleteDirectory(files[files.length - 1]);
            }
        }
    }

    public static boolean deleteDirectory(File dir) {
        boolean z = false;
        try {
            if (!dir.exists()) {
                return z;
            }
            if (dir.isFile()) {
                return dir.delete();
            }
            for (String file : dir.list()) {
                File f = new File(dir, file);
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
            return dir.delete();
        } catch (Exception e) {
            Logger.m1420e(TAG, "Delete dir error");
            return z;
        }
    }

    public static void deleteMsgData(Context context) {
        File[] files;
        try {
            for (File f : context.getFilesDir().listFiles()) {
                if (checkPushDataDirName(f.getName())) {
                    FileUtil.deepDeleteFile(f.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkPushDataDirName(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return false;
        }
        return Pattern.compile("^[0-9]{3}$").matcher(fileName).matches();
    }
}
