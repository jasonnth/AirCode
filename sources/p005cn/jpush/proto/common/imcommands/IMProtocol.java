package p005cn.jpush.proto.common.imcommands;

import com.google.protobuf.jpush.ByteString;
import com.google.protobuf.jpush.InvalidProtocolBufferException;
import p005cn.jpush.p034im.proto.C1621Im.Packet;
import p005cn.jpush.p034im.proto.C1621Im.ProtocolBody;
import p005cn.jpush.p034im.proto.C1621Im.ProtocolHead;
import p005cn.jpush.p034im.proto.C1621Im.Response;
import p005cn.jpush.p034im.proto.C1621Im.Response.Builder;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGlobal;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGroup;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbSingle;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGlobal;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGroup;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbSingle;
import p005cn.jpush.p034im.proto.C1623Message.EventAnswer;
import p005cn.jpush.p034im.proto.C1623Message.EventNotification;
import p005cn.jpush.p034im.proto.C1623Message.EventSync;
import p005cn.jpush.p034im.proto.C1623Message.GroupMsg;
import p005cn.jpush.p034im.proto.C1623Message.SingleMsg;
import p005cn.jpush.p034im.proto.Friend.AddBlackList;
import p005cn.jpush.p034im.proto.Friend.AddFriend;
import p005cn.jpush.p034im.proto.Friend.DelBlackList;
import p005cn.jpush.p034im.proto.Friend.DelFriend;
import p005cn.jpush.p034im.proto.Friend.UpdateMemo;
import p005cn.jpush.p034im.proto.Group.AddGroupMember;
import p005cn.jpush.p034im.proto.Group.CreateGroup;
import p005cn.jpush.p034im.proto.Group.DelGroupMember;
import p005cn.jpush.p034im.proto.Group.ExitGroup;
import p005cn.jpush.p034im.proto.Group.UpdateGroupInfo;
import p005cn.jpush.p034im.proto.User.Login;
import p005cn.jpush.p034im.proto.User.Logout;
import p005cn.jpush.p034im.proto.User.ReportInformation;
import p005cn.jpush.proto.common.utils.SimpleLog;

/* renamed from: cn.jpush.proto.common.imcommands.IMProtocol */
public class IMProtocol {
    String appKey;
    int command;
    Object entity;
    Response response;
    int responseCode = -1;
    String responseMessage;
    long uid;
    int version;

    public IMProtocol(int command2, int version2, long uid2, String appKey2, Object entity2) {
        this.command = command2;
        this.version = version2;
        this.uid = uid2;
        this.appKey = appKey2;
        this.entity = entity2;
    }

    public void setUid(long uid2) {
        this.uid = uid2;
    }

    public void clearEntity() {
        this.entity = null;
    }

    public void setEntity(Object entity2) {
        this.entity = entity2;
    }

    public void setResponse(int code, String error) {
        this.responseCode = code;
        this.responseMessage = error;
        Builder builder = Response.newBuilder().setCode(code);
        if (error != null) {
            builder.setMessage(ByteString.copyFromUtf8(error));
        }
        this.response = builder.build();
    }

    public Packet toProtocolBuffer() {
        ProtocolHead.Builder head = ProtocolHead.newBuilder().setCmd(this.command).setVer(this.version).setUid(this.uid);
        if (this.appKey != null && !"".equalsIgnoreCase(this.appKey.trim())) {
            head.setAppkey(ByteString.copyFromUtf8(this.appKey));
        }
        return Packet.newBuilder().setHead(head.build()).setBody(buildBody(this.command, this.entity, this.response)).build();
    }

    private static ProtocolBody buildBody(int command2, Object entity2, Response response2) {
        ProtocolBody.Builder body = ProtocolBody.newBuilder();
        if (response2 != null) {
            body.setCommonRep(response2);
        }
        if (entity2 == null) {
            return body.build();
        }
        switch (command2) {
            case 1:
                body.setLogin((Login) entity2);
                break;
            case 2:
                body.setLogout((Logout) entity2);
                break;
            case 3:
                body.setSingleMsg((SingleMsg) entity2);
                break;
            case 4:
                body.setGroupMsg((GroupMsg) entity2);
                break;
            case 5:
                body.setAddFriend((AddFriend) entity2);
                break;
            case 6:
                body.setDelFriend((DelFriend) entity2);
                break;
            case 7:
                body.setUpdateMemo((UpdateMemo) entity2);
                break;
            case 8:
                body.setCreateGroup((CreateGroup) entity2);
                break;
            case 9:
                body.setExitGroup((ExitGroup) entity2);
                break;
            case 10:
                body.setAddGroupMember((AddGroupMember) entity2);
                break;
            case 11:
                body.setDelGroupMember((DelGroupMember) entity2);
                break;
            case 12:
                body.setUpdateGroupInfo((UpdateGroupInfo) entity2);
                break;
            case 13:
                body.setEventNotification((EventNotification) entity2);
                break;
            case 14:
                body.setChatMsg((ChatMsgSync) entity2);
                break;
            case 15:
                body.setEventSync((EventSync) entity2);
                break;
            case 16:
                body.setEventAnswer((EventAnswer) entity2);
                break;
            case 18:
                body.setAddBlacklist((AddBlackList) entity2);
                break;
            case 19:
                body.setDelBlacklist((DelBlackList) entity2);
                break;
            case 23:
                body.setReportInfo((ReportInformation) entity2);
                break;
            case 31:
                body.setAddMsgNoDisturbSingle((AddMsgnoDisturbSingle) entity2);
                break;
            case 32:
                body.setDeleteMsgNoDisturbSingle((DeleteMsgnoDisturbSingle) entity2);
                break;
            case 33:
                body.setAddMsgNoDisturbGroup((AddMsgnoDisturbGroup) entity2);
                break;
            case 34:
                body.setDeleteMsgNoDisturbGroup((DeleteMsgnoDisturbGroup) entity2);
                break;
            case 35:
                body.setAddMsgNoDisturbGlobal((AddMsgnoDisturbGlobal) entity2);
                break;
            case 36:
                body.setDeleteMsgNoDisturbGlobal((DeleteMsgnoDisturbGlobal) entity2);
                break;
            default:
                SimpleLog.warn("Unhandled cmd - " + command2);
                break;
        }
        return body.build();
    }

    public IMProtocol(byte[] data) throws InvalidProtocolBufferException {
        Packet protocol = Packet.parseFrom(data);
        ProtocolHead head = protocol.getHead();
        ProtocolBody body = protocol.getBody();
        this.command = head.getCmd();
        this.version = head.getVer();
        this.uid = head.getUid();
        if (head.getAppkey() != null) {
            this.appKey = head.getAppkey().toStringUtf8();
        }
        this.response = body.getCommonRep();
        switch (this.command) {
            case 1:
                this.entity = body.getLogin();
                return;
            case 2:
                this.entity = body.getLogout();
                return;
            case 3:
                this.entity = body.getSingleMsg();
                return;
            case 4:
                this.entity = body.getGroupMsg();
                return;
            case 5:
                this.entity = body.getAddFriend();
                return;
            case 6:
                this.entity = body.getDelFriend();
                return;
            case 7:
                this.entity = body.getUpdateMemo();
                return;
            case 8:
                this.entity = body.getCreateGroup();
                return;
            case 9:
                this.entity = body.getExitGroup();
                return;
            case 10:
                this.entity = body.getAddGroupMember();
                return;
            case 11:
                this.entity = body.getDelGroupMember();
                return;
            case 12:
                this.entity = body.getUpdateGroupInfo();
                return;
            case 13:
                this.entity = body.getEventNotification();
                return;
            case 14:
                this.entity = body.getChatMsg();
                return;
            case 15:
                this.entity = body.getEventSync();
                return;
            case 16:
                this.entity = body.getEventAnswer();
                return;
            case 18:
                this.entity = body.getAddBlacklist();
                return;
            case 19:
                this.entity = body.getDelBlacklist();
                return;
            case 23:
                this.entity = body.getReportInfo();
                return;
            case 31:
                this.entity = body.getAddMsgNoDisturbSingle();
                return;
            case 32:
                this.entity = body.getDeleteMsgNoDisturbSingle();
                return;
            case 33:
                this.entity = body.getAddMsgNoDisturbGroup();
                return;
            case 34:
                this.entity = body.getDeleteMsgNoDisturbGroup();
                return;
            case 35:
                this.entity = body.getAddMsgNoDisturbGlobal();
                return;
            case 36:
                this.entity = body.getDeleteMsgNoDisturbGlobal();
                return;
            default:
                SimpleLog.info("Unhandled IM cmd yet - " + this.command);
                this.entity = null;
                return;
        }
    }

    public int getCommand() {
        return this.command;
    }

    public int getVersion() {
        return this.version;
    }

    public long getUid() {
        return this.uid;
    }

    public Response getResponse() {
        return this.response;
    }

    public Object getEntity() {
        return this.entity;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder().append("[IMProtocol] - command:").append(this.command).append(", version:").append(this.version).append(", uid:").append(this.uid).append(", appkey:").append(this.appKey).append(this.responseCode >= 0 ? ", responseCode:" + this.responseCode : "").append((this.responseCode < 0 || this.responseMessage == null) ? "" : ", responseMessage:" + this.responseMessage);
        if (this.entity == null) {
            str = "";
        } else {
            str = ", entity:" + this.entity.toString();
        }
        return append.append(str).toString();
    }
}
