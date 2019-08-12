package p005cn.jpush.proto.common.imcommands;

/* renamed from: cn.jpush.proto.common.imcommands.IMCommands */
public class IMCommands {
    public static final int PUSH_MSG_TYPE_FOR_IM = 100;

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddBlackList */
    public interface AddBlackList {
        public static final int CMD = 18;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddFriend */
    public interface AddFriend {
        public static final int CMD = 5;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddGroupMember */
    public interface AddGroupMember {
        public static final int CMD = 10;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddMsgnoDisturbGlobal */
    public interface AddMsgnoDisturbGlobal {
        public static final int CMD = 35;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddMsgnoDisturbGroup */
    public interface AddMsgnoDisturbGroup {
        public static final int CMD = 33;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$AddMsgnoDisturbSingle */
    public interface AddMsgnoDisturbSingle {
        public static final int CMD = 31;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$ChatMsgSync */
    public interface ChatMsgSync {
        public static final int CMD = 14;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$CreateGroup */
    public interface CreateGroup {
        public static final int CMD = 8;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DelBlackList */
    public interface DelBlackList {
        public static final int CMD = 19;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DelFriend */
    public interface DelFriend {
        public static final int CMD = 6;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DelGroupMember */
    public interface DelGroupMember {
        public static final int CMD = 11;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DeleteMsgnoDisturbGlobal */
    public interface DeleteMsgnoDisturbGlobal {
        public static final int CMD = 36;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DeleteMsgnoDisturbGroup */
    public interface DeleteMsgnoDisturbGroup {
        public static final int CMD = 34;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$DeleteMsgnoDisturbSingle */
    public interface DeleteMsgnoDisturbSingle {
        public static final int CMD = 32;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$EventAnswer */
    public interface EventAnswer {
        public static final int CMD = 16;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$EventNotification */
    public interface EventNotification {
        public static final int CMD = 13;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$EventSync */
    public interface EventSync {
        public static final int CMD = 15;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$ExitGroup */
    public interface ExitGroup {
        public static final int CMD = 9;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$GroupMsg */
    public interface GroupMsg {
        public static final int CMD = 4;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$Login */
    public interface Login {
        public static final int CMD = 1;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$Logout */
    public interface Logout {
        public static final int CMD = 2;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$ReportInformation */
    public interface ReportInformation {
        public static final int CMD = 23;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$SingleMsg */
    public interface SingleMsg {
        public static final int CMD = 3;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$UpdateGroupInfo */
    public interface UpdateGroupInfo {
        public static final int CMD = 12;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.imcommands.IMCommands$UpdateMemo */
    public interface UpdateMemo {
        public static final int CMD = 7;
        public static final int VERSION = 1;
    }
}
