package com.jumio.commons.camera;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.enums.ScreenAngle;
import com.jumio.commons.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageData implements Parcelable, Serializable {
    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        public ImageData createFromParcel(Parcel source) {
            return new ImageData(source);
        }

        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };
    private CameraPosition cameraPosition;
    private String exactImagePath;
    private boolean flashMode;
    private float focusConfidence;
    private String imagePath;
    private Size imageSize;
    private ScreenAngle orientationMode;

    public enum CameraPosition {
        FRONT,
        BACK
    }

    public void clear() {
        new File(this.imagePath).delete();
        new File(this.exactImagePath).delete();
        this.imagePath = null;
        this.exactImagePath = null;
    }

    public ImageData() {
        this.imageSize = null;
        this.flashMode = false;
        this.orientationMode = null;
        this.cameraPosition = null;
        this.focusConfidence = -1.0f;
    }

    private ImageData(Parcel in) {
        ScreenAngle screenAngle;
        boolean z = true;
        CameraPosition cameraPosition2 = null;
        this.imageSize = null;
        this.flashMode = false;
        this.orientationMode = null;
        this.cameraPosition = null;
        this.focusConfidence = -1.0f;
        int imageWidth = in.readInt();
        int imageHeight = in.readInt();
        if (!(imageWidth == -1 || imageHeight == -1)) {
            this.imageSize = new Size(imageWidth, imageHeight);
        }
        if (in.readByte() != 1) {
            z = false;
        }
        this.flashMode = z;
        String orientationModeString = in.readString();
        if (orientationModeString.length() != 0) {
            screenAngle = ScreenAngle.valueOf(orientationModeString);
        } else {
            screenAngle = null;
        }
        this.orientationMode = screenAngle;
        String cameraPositionString = in.readString();
        if (cameraPositionString.length() != 0) {
            cameraPosition2 = CameraPosition.valueOf(cameraPositionString);
        }
        this.cameraPosition = cameraPosition2;
        this.focusConfidence = in.readFloat();
        this.imagePath = in.readString();
        this.exactImagePath = in.readString();
    }

    public Size getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(Size imageSize2) {
        this.imageSize = imageSize2;
    }

    public boolean isFlashMode() {
        return this.flashMode;
    }

    public void setFlashMode(boolean flashMode2) {
        this.flashMode = flashMode2;
    }

    public ScreenAngle getOrientationMode() {
        return this.orientationMode;
    }

    public void setOrientationMode(ScreenAngle orientationMode2) {
        this.orientationMode = orientationMode2;
    }

    public CameraPosition getCameraPosition() {
        return this.cameraPosition;
    }

    public void setCameraPosition(CameraPosition cameraPosition2) {
        this.cameraPosition = cameraPosition2;
    }

    public float getFocusConfidence() {
        return this.focusConfidence;
    }

    public void setFocusConfidence(float focusConfidence2) {
        this.focusConfidence = focusConfidence2;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath2) {
        this.imagePath = imagePath2;
    }

    public boolean hasImagePath() {
        return (this.imagePath == null || this.imagePath.length() == 0) ? false : true;
    }

    public String getExactImagePath() {
        return this.exactImagePath;
    }

    public void setExactImagePath(String exactImagePath2) {
        this.exactImagePath = exactImagePath2;
    }

    public boolean hasExactImagePath() {
        return (this.exactImagePath == null || this.exactImagePath.length() == 0) ? false : true;
    }

    public byte[] getImageData() {
        try {
            return FileUtil.readFile(getImagePath());
        } catch (IOException e) {
            Log.e("ImageData", "Error reading File", e);
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = -1;
        dest.writeInt(this.imageSize == null ? -1 : this.imageSize.width);
        if (this.imageSize != null) {
            i = this.imageSize.height;
        }
        dest.writeInt(i);
        dest.writeByte((byte) (this.flashMode ? 1 : 0));
        dest.writeString(this.orientationMode == null ? "" : this.orientationMode.name());
        dest.writeString(this.cameraPosition == null ? "" : this.cameraPosition.name());
        dest.writeFloat(this.focusConfidence);
        dest.writeString(this.imagePath);
        dest.writeString(this.exactImagePath);
    }
}
