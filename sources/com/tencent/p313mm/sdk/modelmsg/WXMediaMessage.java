package com.tencent.p313mm.sdk.modelmsg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.util.Log;
import java.io.ByteArrayOutputStream;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage */
public final class WXMediaMessage {
    public String description;
    public IMediaObject mediaObject;
    public String mediaTagName;
    public String messageAction;
    public String messageExt;
    public int sdkVer;
    public byte[] thumbData;
    public String title;

    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$Builder */
    public static class Builder {
        public static Bundle toBundle(WXMediaMessage obj) {
            Bundle data = new Bundle();
            data.putInt("_wxobject_sdkVer", obj.sdkVer);
            data.putString("_wxobject_title", obj.title);
            data.putString("_wxobject_description", obj.description);
            data.putByteArray("_wxobject_thumbdata", obj.thumbData);
            if (obj.mediaObject != null) {
                data.putString("_wxobject_identifier_", pathNewToOld(obj.mediaObject.getClass().getName()));
                obj.mediaObject.serialize(data);
            }
            data.putString("_wxobject_mediatagname", obj.mediaTagName);
            data.putString("_wxobject_message_action", obj.messageAction);
            data.putString("_wxobject_message_ext", obj.messageExt);
            return data;
        }

        public static WXMediaMessage fromBundle(Bundle data) {
            WXMediaMessage obj = new WXMediaMessage();
            obj.sdkVer = data.getInt("_wxobject_sdkVer");
            obj.title = data.getString("_wxobject_title");
            obj.description = data.getString("_wxobject_description");
            obj.thumbData = data.getByteArray("_wxobject_thumbdata");
            obj.mediaTagName = data.getString("_wxobject_mediatagname");
            obj.messageAction = data.getString("_wxobject_message_action");
            obj.messageExt = data.getString("_wxobject_message_ext");
            String ident = pathOldToNew(data.getString("_wxobject_identifier_"));
            if (ident != null && ident.length() > 0) {
                try {
                    obj.mediaObject = (IMediaObject) Class.forName(ident).newInstance();
                    obj.mediaObject.unserialize(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("WXMediaMessage", "get media object from bundle failed: unknown ident " + ident + ", ex = " + e.getMessage());
                }
            }
            return obj;
        }

        private static String pathNewToOld(String newPath) {
            if (newPath != null && newPath.length() != 0) {
                return newPath.replace("com.tencent.mm.sdk.modelmsg", "com.tencent.mm.sdk.openapi");
            }
            Log.e("WXMediaMessage", "pathNewToOld fail, newPath is null");
            return newPath;
        }

        private static String pathOldToNew(String oldPath) {
            if (oldPath != null && oldPath.length() != 0) {
                return oldPath.replace("com.tencent.mm.sdk.openapi", "com.tencent.mm.sdk.modelmsg");
            }
            Log.e("WXMediaMessage", "pathOldToNew fail, oldPath is null");
            return oldPath;
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject */
    public interface IMediaObject {
        boolean checkArgs();

        void serialize(Bundle bundle);

        int type();

        void unserialize(Bundle bundle);
    }

    public WXMediaMessage() {
        this(null);
    }

    public WXMediaMessage(IMediaObject object) {
        this.mediaObject = object;
    }

    public int getType() {
        if (this.mediaObject == null) {
            return 0;
        }
        return this.mediaObject.type();
    }

    public void setThumbImage(Bitmap bm) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            bm.compress(CompressFormat.JPEG, 85, os);
            this.thumbData = os.toByteArray();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("WXMediaMessage", "put thumb failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean checkArgs() {
        if (getType() == 8 && (this.thumbData == null || this.thumbData.length == 0)) {
            Log.e("WXMediaMessage", "checkArgs fail, thumbData should not be null when send emoji");
            return false;
        } else if (this.thumbData != null && this.thumbData.length > 32768) {
            Log.e("WXMediaMessage", "checkArgs fail, thumbData is invalid");
            return false;
        } else if (this.title != null && this.title.length() > 512) {
            Log.e("WXMediaMessage", "checkArgs fail, title is invalid");
            return false;
        } else if (this.description != null && this.description.length() > 1024) {
            Log.e("WXMediaMessage", "checkArgs fail, description is invalid");
            return false;
        } else if (this.mediaObject == null) {
            Log.e("WXMediaMessage", "checkArgs fail, mediaObject is null");
            return false;
        } else if (this.mediaTagName != null && this.mediaTagName.length() > 64) {
            Log.e("WXMediaMessage", "checkArgs fail, mediaTagName is too long");
            return false;
        } else if (this.messageAction != null && this.messageAction.length() > 2048) {
            Log.e("WXMediaMessage", "checkArgs fail, messageAction is too long");
            return false;
        } else if (this.messageExt == null || this.messageExt.length() <= 2048) {
            return this.mediaObject.checkArgs();
        } else {
            Log.e("WXMediaMessage", "checkArgs fail, messageExt is too long");
            return false;
        }
    }
}
