package com.theartofdev.edmodo.cropper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.MediaStore.Images.Media;
import android.support.p000v4.app.Fragment;
import com.theartofdev.edmodo.cropper.CropImageView.CropResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CropImage {

    public static final class ActivityBuilder {
        private final CropImageOptions mOptions;
        private final Uri mSource;

        private ActivityBuilder(Uri source) {
            this.mSource = source;
            this.mOptions = new CropImageOptions();
        }

        public Intent getIntent(Context context) {
            return getIntent(context, CropImageActivity.class);
        }

        public Intent getIntent(Context context, Class<?> cls) {
            this.mOptions.validate();
            Intent intent = new Intent();
            intent.setClass(context, cls);
            intent.putExtra("CROP_IMAGE_EXTRA_SOURCE", this.mSource);
            intent.putExtra("CROP_IMAGE_EXTRA_OPTIONS", this.mOptions);
            return intent;
        }

        public void start(Context context, Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), 203);
        }

        public void start(Context context, android.app.Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), 203);
        }

        public ActivityBuilder setFixAspectRatio(boolean fixAspectRatio) {
            this.mOptions.fixAspectRatio = fixAspectRatio;
            return this;
        }

        public ActivityBuilder setAspectRatio(int aspectRatioX, int aspectRatioY) {
            this.mOptions.aspectRatioX = aspectRatioX;
            this.mOptions.aspectRatioY = aspectRatioY;
            this.mOptions.fixAspectRatio = true;
            return this;
        }

        public ActivityBuilder setAllowFlipping(boolean allowFlipping) {
            this.mOptions.allowFlipping = allowFlipping;
            return this;
        }
    }

    public static final class ActivityResult extends CropResult implements Parcelable {
        public static final Creator<ActivityResult> CREATOR = new Creator<ActivityResult>() {
            public ActivityResult createFromParcel(Parcel in) {
                return new ActivityResult(in);
            }

            public ActivityResult[] newArray(int size) {
                return new ActivityResult[size];
            }
        };

        public ActivityResult(Uri originalUri, Uri uri, Exception error, float[] cropPoints, Rect cropRect, int rotation, int sampleSize) {
            super(null, originalUri, null, uri, error, cropPoints, cropRect, rotation, sampleSize);
        }

        protected ActivityResult(Parcel in) {
            super(null, (Uri) in.readParcelable(Uri.class.getClassLoader()), null, (Uri) in.readParcelable(Uri.class.getClassLoader()), (Exception) in.readSerializable(), in.createFloatArray(), (Rect) in.readParcelable(Rect.class.getClassLoader()), in.readInt(), in.readInt());
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(getOriginalUri(), flags);
            dest.writeParcelable(getUri(), flags);
            dest.writeSerializable(getError());
            dest.writeFloatArray(getCropPoints());
            dest.writeParcelable(getCropRect(), flags);
            dest.writeInt(getRotation());
            dest.writeInt(getSampleSize());
        }

        public int describeContents() {
            return 0;
        }
    }

    public static void startPickImageActivity(Activity activity) {
        activity.startActivityForResult(getPickImageChooserIntent(activity), 200);
    }

    public static Intent getPickImageChooserIntent(Context context) {
        return getPickImageChooserIntent(context, context.getString(R.string.pick_image_intent_chooser_title), false, true);
    }

    public static Intent getPickImageChooserIntent(Context context, CharSequence title, boolean includeDocuments, boolean includeCamera) {
        Intent target;
        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        if (!isExplicitCameraPermissionRequired(context) && includeCamera) {
            allIntents.addAll(getCameraIntents(context, packageManager));
        }
        List<Intent> galleryIntents = getGalleryIntents(packageManager, "android.intent.action.GET_CONTENT", includeDocuments);
        if (galleryIntents.size() == 0) {
            galleryIntents = getGalleryIntents(packageManager, "android.intent.action.PICK", includeDocuments);
        }
        allIntents.addAll(galleryIntents);
        if (allIntents.isEmpty()) {
            target = new Intent();
        } else {
            target = (Intent) allIntents.get(allIntents.size() - 1);
            allIntents.remove(allIntents.size() - 1);
        }
        Intent chooserIntent = Intent.createChooser(target, title);
        chooserIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }

    public static List<Intent> getCameraIntents(Context context, PackageManager packageManager) {
        List<Intent> allIntents = new ArrayList<>();
        Uri outputFileUri = getCaptureImageOutputUri(context);
        Intent captureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        for (ResolveInfo res : packageManager.queryIntentActivities(captureIntent, 0)) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra("output", outputFileUri);
            }
            allIntents.add(intent);
        }
        return allIntents;
    }

    public static List<Intent> getGalleryIntents(PackageManager packageManager, String action, boolean includeDocuments) {
        Intent galleryIntent;
        List<Intent> intents = new ArrayList<>();
        if (action == "android.intent.action.GET_CONTENT") {
            galleryIntent = new Intent(action);
        } else {
            galleryIntent = new Intent(action, Media.EXTERNAL_CONTENT_URI);
        }
        galleryIntent.setType("image/*");
        for (ResolveInfo res : packageManager.queryIntentActivities(galleryIntent, 0)) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            intents.add(intent);
        }
        if (!includeDocuments) {
            Iterator it = intents.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Intent intent2 = (Intent) it.next();
                if (intent2.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                    intents.remove(intent2);
                    break;
                }
            }
        }
        return intents;
    }

    public static boolean isExplicitCameraPermissionRequired(Context context) {
        if (VERSION.SDK_INT < 23 || !hasPermissionInManifest(context, "android.permission.CAMERA") || context.checkSelfPermission("android.permission.CAMERA") == 0) {
            return false;
        }
        return true;
    }

    public static boolean hasPermissionInManifest(Context context, String permissionName) {
        try {
            String[] declaredPermisisons = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (declaredPermisisons == null || declaredPermisisons.length <= 0) {
                return false;
            }
            for (String p : declaredPermisisons) {
                if (p.equalsIgnoreCase(permissionName)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static Uri getCaptureImageOutputUri(Context context) {
        File getImage = context.getExternalCacheDir();
        if (getImage != null) {
            return Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public static Uri getPickImageResultUri(Context context, Intent data) {
        boolean isCamera = true;
        if (!(data == null || data.getData() == null)) {
            String action = data.getAction();
            isCamera = action != null && action.equals("android.media.action.IMAGE_CAPTURE");
        }
        return (isCamera || data.getData() == null) ? getCaptureImageOutputUri(context) : data.getData();
    }

    public static boolean isReadExternalStoragePermissionsRequired(Context context, Uri uri) {
        return VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 && isUriRequiresPermissions(context, uri);
    }

    public static boolean isUriRequiresPermissions(Context context, Uri uri) {
        try {
            context.getContentResolver().openInputStream(uri).close();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static ActivityBuilder activity(Uri uri) {
        return new ActivityBuilder(uri);
    }

    public static ActivityResult getActivityResult(Intent data) {
        if (data != null) {
            return (ActivityResult) data.getParcelableExtra("CROP_IMAGE_EXTRA_RESULT");
        }
        return null;
    }
}
