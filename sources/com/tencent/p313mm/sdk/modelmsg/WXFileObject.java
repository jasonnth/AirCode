package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import java.io.File;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXFileObject */
public class WXFileObject implements IMediaObject {
    private int contentLengthLimit = 10485760;
    public byte[] fileData = null;
    public String filePath = null;

    public void serialize(Bundle data) {
        data.putByteArray("_wxfileobject_fileData", this.fileData);
        data.putString("_wxfileobject_filePath", this.filePath);
    }

    public void unserialize(Bundle data) {
        this.fileData = data.getByteArray("_wxfileobject_fileData");
        this.filePath = data.getString("_wxfileobject_filePath");
    }

    public int type() {
        return 6;
    }

    public void setContentLengthLimit(int limit) {
        this.contentLengthLimit = limit;
    }

    public boolean checkArgs() {
        if ((this.fileData == null || this.fileData.length == 0) && (this.filePath == null || this.filePath.length() == 0)) {
            Log.e("WXFileObject", "checkArgs fail, both arguments is null");
            return false;
        } else if (this.fileData != null && this.fileData.length > this.contentLengthLimit) {
            Log.e("WXFileObject", "checkArgs fail, fileData is too large");
            return false;
        } else if (this.filePath == null || getFileSize(this.filePath) <= this.contentLengthLimit) {
            return true;
        } else {
            Log.e("WXFileObject", "checkArgs fail, fileSize is too large");
            return false;
        }
    }

    private int getFileSize(String filePath2) {
        if (filePath2 == null || filePath2.length() == 0) {
            return 0;
        }
        File file = new File(filePath2);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }
}
