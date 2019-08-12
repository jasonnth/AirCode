package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.Gson;
import com.google.gson.jpush.GsonBuilder;
import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.api.BasicCallback;
import p005cn.jpush.proto.common.commands.IMRequest;

/* renamed from: cn.jpush.proto.common.imcommands.ImBaseRequest */
public abstract class ImBaseRequest {
    public static final String ACTION_IM_REQUEST = "cn.jpush.im.android.action.IM_REQUEST";
    public static final String EXTRA_IM_COMMAND = "im_cmd";
    public static final String EXTRA_IM_REQUEST = "im_request_json";
    protected static Gson _gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    protected BasicCallback callback;
    protected int cmd = 4;
    @Expose
    protected long rid = 0;
    @Expose
    protected long uid = 0;

    /* access modifiers changed from: 0000 */
    public abstract IMProtocol toProtocolBuffer(long j, String str);

    public String toJson() {
        return _gson.toJson((Object) this).toString();
    }

    public IMRequest toProtocolBuffer(String appKey) {
        return new IMRequest(this.rid, toProtocolBuffer(this.uid, appKey));
    }

    public static ImBaseRequest fromJson(String json, int cmd2) {
        Class cls;
        switch (cmd2) {
            case 1:
                cls = ImLoginRequest.class;
                break;
            case 2:
                cls = ImLogoutRequest.class;
                break;
            case 3:
                cls = SingleMsgRequest.class;
                break;
            case 4:
                cls = GroupMsgRequest.class;
                break;
            case 5:
                cls = AddFriendRequest.class;
                break;
            case 6:
                cls = DelFriendRequest.class;
                break;
            case 7:
                cls = UpdateMemoRequest.class;
                break;
            case 8:
                cls = CreateGroupRequest.class;
                break;
            case 9:
                cls = ExitGroupRequest.class;
                break;
            case 10:
                cls = AddGroupMemberRequest.class;
                break;
            case 11:
                cls = DelGroupMemberRequest.class;
                break;
            case 12:
                cls = UpdateGroupInfoRequest.class;
                break;
            case 18:
                cls = AddBlackListRequest.class;
                break;
            case 19:
                cls = DelBlackListRequest.class;
                break;
            case 23:
                cls = ReportInfoRequest.class;
                break;
            case 31:
                cls = AddMsgnoDisturbSingleRequest.class;
                break;
            case 32:
                cls = DeleteMsgnoDisturbSingleRequest.class;
                break;
            case 33:
                cls = AddMsgnoDisturbGroupRequest.class;
                break;
            case 34:
                cls = DeleteMsgnoDisturbGroupRequest.class;
                break;
            case 35:
                cls = AddMsgnoDisturbGlobalRequest.class;
                break;
            case 36:
                cls = DeleteMsgnoDisturbGlobalRequest.class;
                break;
            default:
                System.out.println("Unhandled command - " + cmd2);
                return null;
        }
        return (ImBaseRequest) _gson.fromJson(json, cls);
    }

    public int getCmd() {
        return this.cmd;
    }

    public void setCallback(BasicCallback callback2) {
        this.callback = callback2;
    }

    public BasicCallback getCallback() {
        return this.callback;
    }
}
