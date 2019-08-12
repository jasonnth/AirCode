package com.jumio.commons.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.jumio.commons.enums.Rotation;
import com.jumio.commons.enums.ScreenAngle;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;

public class DeviceRotationManager {
    private Activity mActivity;
    private ScreenAngle mAngle;
    private int mDefaultOrientation;
    private boolean mIsTablet;
    private int mManifestOrientation;
    private Rotation mRotation;

    public DeviceRotationManager(Activity activity, Rotation rotation) {
        this.mActivity = activity;
        this.mRotation = rotation;
        this.mIsTablet = isTabletDevice(activity);
        this.mDefaultOrientation = getDeviceDefaultOrientation(activity);
        if (isRotation(Rotation.NONE)) {
            this.mActivity.setRequestedOrientation((!this.mIsTablet || getDefaultOrientation() != 2) ? 1 : 0);
        }
        this.mAngle = getScreenOrientation();
        try {
            this.mManifestOrientation = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).screenOrientation;
        } catch (NameNotFoundException e) {
            this.mManifestOrientation = -1;
        }
        if (this.mManifestOrientation == 3 || this.mManifestOrientation == 10) {
            this.mManifestOrientation = 4;
        } else if (this.mManifestOrientation == 11) {
            this.mManifestOrientation = 6;
        } else if (this.mManifestOrientation == 12) {
            this.mManifestOrientation = 7;
        } else if (this.mManifestOrientation == -1 || this.mManifestOrientation == 2) {
            if (System.getInt(this.mActivity.getContentResolver(), "accelerometer_rotation", 0) == 0) {
                int userLockedOrientation = 0;
                switch (((WindowManager) this.mActivity.getSystemService("window")).getDefaultDisplay().getRotation()) {
                    case 0:
                        if (this.mIsTablet && this.mDefaultOrientation == 2) {
                            userLockedOrientation = 0;
                            break;
                        } else {
                            userLockedOrientation = 1;
                            break;
                        }
                        break;
                    case 1:
                        if (this.mIsTablet && this.mDefaultOrientation == 2) {
                            userLockedOrientation = 9;
                            break;
                        } else {
                            userLockedOrientation = 0;
                            break;
                        }
                        break;
                    case 2:
                        if (this.mIsTablet && this.mDefaultOrientation == 2) {
                            userLockedOrientation = 8;
                            break;
                        } else {
                            userLockedOrientation = 9;
                            break;
                        }
                    case 3:
                        if (this.mIsTablet && this.mDefaultOrientation == 2) {
                            userLockedOrientation = 1;
                            break;
                        } else {
                            userLockedOrientation = 8;
                            break;
                        }
                }
                this.mManifestOrientation = userLockedOrientation;
            } else if (this.mIsTablet) {
                this.mManifestOrientation = 10;
            } else {
                this.mManifestOrientation = 4;
            }
        } else if (this.mManifestOrientation == 7) {
            this.mManifestOrientation = 1;
        } else if (this.mManifestOrientation == 6) {
            this.mManifestOrientation = 0;
        }
        setAngleFromScreen();
    }

    @SuppressLint({"NewApi"})
    public static boolean isTabletDevice(Context activityContext) {
        boolean sw600dp = VERSION.SDK_INT >= 13 ? activityContext.getResources().getConfiguration().smallestScreenWidthDp >= 600 : (activityContext.getResources().getConfiguration().screenLayout & 15) == 4;
        if (sw600dp) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) activityContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            if (metrics.densityDpi == 160 || metrics.densityDpi == 240 || metrics.densityDpi == 160 || metrics.densityDpi == 213 || metrics.densityDpi == 320) {
                return true;
            }
        }
        return false;
    }

    private int getDeviceDefaultOrientation(Activity activity) {
        WindowManager lWindowManager = (WindowManager) activity.getSystemService("window");
        Configuration cfg = activity.getResources().getConfiguration();
        int lRotation = lWindowManager.getDefaultDisplay().getRotation();
        if ((lRotation == 0 || lRotation == 2) && cfg.orientation == 2) {
            return 2;
        }
        if ((lRotation == 1 || lRotation == 3) && cfg.orientation == 1) {
            return 2;
        }
        return 1;
    }

    public int getDisplayRotation() {
        return ((WindowManager) this.mActivity.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public ScreenAngle getScreenOrientation() {
        ScreenAngle angle = ScreenAngle.PORTRAIT;
        switch (((WindowManager) this.mActivity.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                return (!this.mIsTablet || this.mDefaultOrientation != 2) ? ScreenAngle.PORTRAIT : ScreenAngle.LANDSCAPE;
            case 1:
                return (!this.mIsTablet || this.mDefaultOrientation != 2) ? ScreenAngle.LANDSCAPE : ScreenAngle.INVERTED_PORTRAIT;
            case 2:
                return (!this.mIsTablet || this.mDefaultOrientation != 2) ? ScreenAngle.INVERTED_PORTRAIT : ScreenAngle.INVERTED_LANDSCAPE;
            case 3:
                return (!this.mIsTablet || this.mDefaultOrientation != 2) ? ScreenAngle.INVERTED_LANDSCAPE : ScreenAngle.PORTRAIT;
            default:
                return angle;
        }
    }

    public Rotation getRotation() {
        return this.mRotation;
    }

    public boolean isRotation(Rotation rotation) {
        return this.mRotation.equals(rotation);
    }

    public boolean isTablet() {
        return this.mIsTablet;
    }

    public ScreenAngle getAngle() {
        return this.mAngle;
    }

    public boolean isAngle(ScreenAngle angle) {
        return this.mAngle.equals(angle);
    }

    public void setAngleFromOrientation(int orientation) {
        if (isTablet() && getDefaultOrientation() == 2) {
            orientation = ((orientation - 90) + FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
        }
        if ((orientation > 355 || orientation < 5) && this.mAngle != ScreenAngle.PORTRAIT) {
            this.mAngle = ScreenAngle.PORTRAIT;
        } else if (orientation > 85 && orientation < 95 && this.mAngle != ScreenAngle.INVERTED_LANDSCAPE) {
            this.mAngle = ScreenAngle.INVERTED_LANDSCAPE;
        } else if (orientation <= 175 || orientation >= 185 || this.mAngle == ScreenAngle.INVERTED_PORTRAIT) {
            if (orientation > 265 && orientation < 275 && this.mAngle != ScreenAngle.LANDSCAPE) {
                this.mAngle = ScreenAngle.LANDSCAPE;
            }
        } else if (isTablet() || this.mManifestOrientation != 4) {
            this.mAngle = ScreenAngle.INVERTED_PORTRAIT;
        }
    }

    public void setAngleFromScreen() {
        this.mAngle = getScreenOrientation();
    }

    public boolean isPortrait() {
        return this.mAngle == ScreenAngle.PORTRAIT || this.mAngle == ScreenAngle.INVERTED_PORTRAIT;
    }

    public boolean isLandscape() {
        return this.mAngle == ScreenAngle.LANDSCAPE || this.mAngle == ScreenAngle.INVERTED_LANDSCAPE;
    }

    public boolean isScreenPortrait() {
        ScreenAngle angle = getScreenOrientation();
        return angle == ScreenAngle.PORTRAIT || angle == ScreenAngle.INVERTED_PORTRAIT;
    }

    public boolean isScreenLandscape() {
        ScreenAngle angle = getScreenOrientation();
        return angle == ScreenAngle.LANDSCAPE || angle == ScreenAngle.INVERTED_LANDSCAPE;
    }

    public boolean isInverted() {
        return this.mAngle == ScreenAngle.INVERTED_PORTRAIT || this.mAngle == ScreenAngle.INVERTED_LANDSCAPE;
    }

    public void setOrientationFromAngle() {
        if (isRotation(Rotation.NATIVE)) {
            int requestedOrientation = 1;
            if (this.mAngle == ScreenAngle.PORTRAIT) {
                requestedOrientation = 1;
            } else if (this.mAngle == ScreenAngle.INVERTED_PORTRAIT) {
                requestedOrientation = isTablet() ? 9 : 1;
            } else if (this.mAngle == ScreenAngle.LANDSCAPE) {
                requestedOrientation = 0;
            } else if (this.mAngle == ScreenAngle.INVERTED_LANDSCAPE) {
                requestedOrientation = 8;
            }
            this.mActivity.setRequestedOrientation(requestedOrientation);
        }
    }

    public void setOrientationFromManifest() {
        this.mActivity.setRequestedOrientation(this.mManifestOrientation);
        setAngleFromScreen();
    }

    public boolean isScreenEqualOrientation() {
        return isAngle(getScreenOrientation());
    }

    public int getDefaultOrientation() {
        return this.mDefaultOrientation;
    }

    public boolean isSensorOrientation() {
        for (int v : new int[]{4, 10, 7, 6}) {
            if (v == this.mManifestOrientation) {
                return true;
            }
        }
        return false;
    }
}
