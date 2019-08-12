package com.miteksystems.misnap.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.hardware.Camera.Parameters;
import android.os.Environment;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.Window;
import android.view.WindowManager;
import com.miteksystems.misnap.p312a.C4550d;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapConstants;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.storage.PreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class Utils {
    static int sCameraFacing;
    static int sCameraId;

    public static void setCameraFacing(int i) {
        sCameraFacing = i;
    }

    public static void setCameraId(int i) {
        sCameraId = i;
    }

    public static boolean isScreenLandscapeLeft(Context context) {
        int deviceCurrentOrientation = getDeviceCurrentOrientation(context);
        if (deviceCurrentOrientation == 1 || deviceCurrentOrientation == 0) {
            return true;
        }
        return false;
    }

    public static boolean isScreenPortraitNormal(Context context) {
        int deviceCurrentOrientation = getDeviceCurrentOrientation(context);
        return deviceCurrentOrientation == 0 || deviceCurrentOrientation == 2;
    }

    public static boolean needToRotateFrameBy180Degrees(Context context) {
        new C4550d();
        return C4550d.m2089a(context, sCameraId) >= 180;
    }

    public static int getCameraRotationDegrees(Context context) {
        new C4550d();
        return C4550d.m2089a(context, sCameraId);
    }

    public static void uxpEvent(Context context, int i) {
        if (context != null) {
            try {
                UXPManager instance = UXPManager.getInstance();
                if (instance != null) {
                    instance.addMessageToMetrics(context.getResources().getString(i));
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void uxpEvent(Context context, int i, int i2) {
        if (context != null) {
            try {
                UXPManager instance = UXPManager.getInstance();
                if (instance != null) {
                    instance.addMessageToMetrics(context.getResources().getString(i), i2);
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void uxpEvent(Context context, int i, String str) {
        if (context != null) {
            try {
                UXPManager instance = UXPManager.getInstance();
                if (instance != null) {
                    instance.addMessageToMetrics(context.getResources().getString(i), str);
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMsgToCameraMgr(Context context, int i) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.CAMERA_MANAGER_BROADCASTER);
            intent.putExtra(SDKConstants.CAM_BROADCAST_MESSAGE_ID, i);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void sendMsgToCameraMgr(Context context, int i, int i2) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.CAMERA_MANAGER_BROADCASTER);
            intent.putExtra(SDKConstants.CAM_BROADCAST_MESSAGE_ID, i);
            intent.putExtra(SDKConstants.CAM_BROADCAST_MESSAGE_PARAM1, i2);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void broadcastMsgToMiSnap(Context context, int i) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.MISNAP_BROADCASTER);
            intent.putExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_ID, i);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void broadcastMsgToMiSnap(Context context, int i, String str) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.MISNAP_BROADCASTER);
            intent.putExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_ID, i);
            intent.putExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_PARAM1, str);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void sendMsgToUIFragment(Context context, int i) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.UI_FRAGMENT_BROADCASTER);
            intent.putExtra(SDKConstants.UI_FRAGMENT_BROADCAST_MESSAGE_ID, i);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static int getDeviceCurrentOrientation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static void sendMsgToUIFragment(Context context, int i, byte[] bArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(SDKConstants.UI_FRAGMENT_BROADCASTER);
            intent.putExtra(SDKConstants.UI_FRAGMENT_BROADCAST_MESSAGE_ID, i);
            intent.putExtra(SDKConstants.ANALYZER_BROADCAST_FRAME_ARRAY, bArr);
            intent.putExtra(SDKConstants.ANALYZER_BROADCAST_FRAME_CORNER1, iArr);
            intent.putExtra(SDKConstants.ANALYZER_BROADCAST_FRAME_CORNER2, iArr2);
            intent.putExtra(SDKConstants.ANALYZER_BROADCAST_FRAME_CORNER3, iArr3);
            intent.putExtra(SDKConstants.ANALYZER_BROADCAST_FRAME_CORNER4, iArr4);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void sendFinalFrame(Context context, byte[] bArr) {
        Intent intent = new Intent();
        intent.putExtra(MiSnapAPI.RESULT_PICTURE_DATA, bArr);
        intent.setAction(SDKConstants.MISNAP_BROADCASTER);
        intent.putExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_ID, SDKConstants.MISNAP_FINAL_FRAME_RECEIVED);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static boolean loadCameraSizes(Context context, Parameters parameters) {
        PreferenceManager preferenceManager = new PreferenceManager(sCameraFacing);
        if (context == null || !preferenceManager.getBoolean(context, MiSnapConstants.PREF_PICTURE_SIZE_CALCULATION_DONE_KEY) || !preferenceManager.getBoolean(context, MiSnapConstants.PREF_PREVIEW_SIZE_CALCULATION_DONE_KEY)) {
            return false;
        }
        parameters.setPreviewSize(Integer.parseInt(preferenceManager.getString(context, MiSnapConstants.PREF_PREVIEW_WIDTH_KEY)), Integer.parseInt(preferenceManager.getString(context, MiSnapConstants.PREF_PREVIEW_HEIGHT_KEY)));
        parameters.setPictureSize(Integer.parseInt(preferenceManager.getString(context, MiSnapConstants.PREF_PICTURE_WIDTH_KEY)), Integer.parseInt(preferenceManager.getString(context, MiSnapConstants.PREF_PICTURE_HEIGHT_KEY)));
        return true;
    }

    public static void savePictureSizeInPrefFile(Context context, int i, int i2) {
        PreferenceManager preferenceManager = new PreferenceManager(sCameraFacing);
        if (context != null) {
            preferenceManager.save(context, MiSnapConstants.PREF_PICTURE_WIDTH_KEY, String.valueOf(i));
            preferenceManager.save(context, MiSnapConstants.PREF_PICTURE_HEIGHT_KEY, String.valueOf(i2));
            preferenceManager.save(context, MiSnapConstants.PREF_PICTURE_SIZE_CALCULATION_DONE_KEY, true);
        }
    }

    public static void unlockAndTurnOnScreen(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(PKIFailureInfo.badSenderNonce);
        window.addFlags(4194304);
        window.addFlags(524288);
    }

    public static byte[] readByteArrayFromImageFile(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeStream.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String saveByteArrayToFile(byte[] bArr, String str) {
        File file;
        File file2 = null;
        try {
            File file3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MiSnap");
            if (!file3.exists()) {
                file3.mkdirs();
            }
            file = new File(file3.getPath(), str + ".jpg");
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e = e;
                file2 = file;
                e.printStackTrace();
                file = file2;
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                return file.toString();
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            file = file2;
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            fileOutputStream2.write(bArr);
            fileOutputStream2.close();
            return file.toString();
        }
        try {
            FileOutputStream fileOutputStream22 = new FileOutputStream(file);
            fileOutputStream22.write(bArr);
            fileOutputStream22.close();
        } catch (FileNotFoundException | Exception e3) {
        }
        return file.toString();
    }
}
