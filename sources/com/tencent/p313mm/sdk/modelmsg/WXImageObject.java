package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import java.io.File;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXImageObject */
public class WXImageObject implements IMediaObject {
    public byte[] imageData;
    public String imagePath;

    public void setImagePath(String imagePath2) {
        this.imagePath = imagePath2;
    }

    public void serialize(Bundle data) {
        data.putByteArray("_wximageobject_imageData", this.imageData);
        data.putString("_wximageobject_imagePath", this.imagePath);
    }

    public void unserialize(Bundle data) {
        this.imageData = data.getByteArray("_wximageobject_imageData");
        this.imagePath = data.getString("_wximageobject_imagePath");
    }

    public int type() {
        return 2;
    }

    public boolean checkArgs() {
        if ((this.imageData == null || this.imageData.length == 0) && (this.imagePath == null || this.imagePath.length() == 0)) {
            Log.e("WXImageObject", "checkArgs fail, all arguments are null");
            return false;
        } else if (this.imageData != null && this.imageData.length > 10485760) {
            Log.e("WXImageObject", "checkArgs fail, content is too large");
            return false;
        } else if (this.imagePath != null && this.imagePath.length() > 10240) {
            Log.e("WXImageObject", "checkArgs fail, path is invalid");
            return false;
        } else if (this.imagePath == null || getFileSize(this.imagePath) <= 10485760) {
            return true;
        } else {
            Log.e("WXImageObject", "checkArgs fail, image content is too large");
            return false;
        }
    }

    private int getFileSize(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return 0;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }
}
