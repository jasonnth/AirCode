package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateThreadRequest extends BaseRequestV2<Object> {
    private final Action action;
    protected final boolean state;
    protected final Thread thread;

    protected enum Action {
        Unread("unread"),
        Archived("archived");
        
        final String serverKey;

        private Action(String serverKey2) {
            this.serverKey = serverKey2;
        }
    }

    public static UpdateThreadRequest forMarkRead(Thread thread2) {
        return new UpdateThreadRequest(thread2, Action.Unread, false);
    }

    public static UpdateThreadRequest forArchive(Thread thread2, boolean archive) {
        return new UpdateThreadRequest(thread2, Action.Archived, archive);
    }

    protected UpdateThreadRequest(Thread thread2, Action action2, boolean state2) {
        this.thread = thread2;
        this.action = action2;
        this.state = state2;
    }

    public String getPath() {
        return "threads/" + this.thread.getId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getBody() {
        Strap strap = Strap.make().mo11639kv(this.action.serverKey, Boolean.toString(this.state));
        try {
            JSONObject jsonObject = new JSONObject();
            for (String property : strap.keySet()) {
                jsonObject.put(property, strap.get(property));
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    public Thread thread() {
        return this.thread;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
