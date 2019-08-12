package p005cn.jpush.android.data;

import android.content.Context;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.BasicEntity */
public class BasicEntity extends Entity {
    private static final long serialVersionUID = 8278792655444375303L;
    public JSONObject topJsonObject;

    public JSONObject msgContentToJson() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean parseContent(Context congtext, JSONObject thirdJsonObject) {
        return false;
    }

    public void process(Context context) {
    }

    public static Entity getRichPushEntity(BasicEntity basEntity) {
        Entity entity = new Entity() {
            public void process(Context context) {
            }

            /* access modifiers changed from: protected */
            public boolean parseContent(Context congtext, JSONObject thirdJsonObject) {
                return false;
            }

            public JSONObject msgContentToJson() {
                return null;
            }
        };
        entity.senderId = basEntity.senderId;
        entity.appId = basEntity.appId;
        entity.message = basEntity.message;
        entity.contentType = basEntity.contentType;
        entity.title = basEntity.title;
        entity.extras = basEntity.extras;
        entity.messageId = basEntity.messageId;
        entity.overrideMessageId = basEntity.overrideMessageId;
        entity.richPushType = basEntity.richPushType;
        entity.richPushUrl = basEntity.richPushUrl;
        entity.setRichPush(basEntity.isRichPush());
        return entity;
    }
}
