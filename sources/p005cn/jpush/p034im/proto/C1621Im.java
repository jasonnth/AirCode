package p005cn.jpush.p034im.proto;

import com.google.protobuf.jpush.ByteString;
import com.google.protobuf.jpush.CodedInputStream;
import com.google.protobuf.jpush.CodedOutputStream;
import com.google.protobuf.jpush.ExtensionRegistryLite;
import com.google.protobuf.jpush.GeneratedMessageLite;
import com.google.protobuf.jpush.InvalidProtocolBufferException;
import com.google.protobuf.jpush.MessageLiteOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;
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

/* renamed from: cn.jpush.im.proto.Im */
public final class C1621Im {

    /* renamed from: cn.jpush.im.proto.Im$Cookie */
    public static final class Cookie extends GeneratedMessageLite implements CookieOrBuilder {
        public static final int RES_FIELD_NUMBER = 1;
        private static final Cookie defaultInstance = new Cookie(true);
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> res_;

        /* renamed from: cn.jpush.im.proto.Im$Cookie$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<Cookie, Builder> implements CookieOrBuilder {
            private int bitField0_;
            private List<Integer> res_ = Collections.emptyList();

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                this.res_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Cookie getDefaultInstanceForType() {
                return Cookie.getDefaultInstance();
            }

            public Cookie build() {
                Cookie result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public Cookie buildParsed() throws InvalidProtocolBufferException {
                Cookie result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public Cookie buildPartial() {
                Cookie result = new Cookie(this);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.res_ = Collections.unmodifiableList(this.res_);
                    this.bitField0_ &= -2;
                }
                result.res_ = this.res_;
                return result;
            }

            public Builder mergeFrom(Cookie other) {
                if (other != Cookie.getDefaultInstance() && !other.res_.isEmpty()) {
                    if (this.res_.isEmpty()) {
                        this.res_ = other.res_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureResIsMutable();
                        this.res_.addAll(other.res_);
                    }
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            break;
                        case 8:
                            ensureResIsMutable();
                            this.res_.add(Integer.valueOf(input.readInt32()));
                            continue;
                        case 10:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addRes(input.readInt32());
                            }
                            input.popLimit(limit);
                            continue;
                        default:
                            if (!parseUnknownField(input, extensionRegistry, tag)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }

            private void ensureResIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.res_ = new ArrayList(this.res_);
                    this.bitField0_ |= 1;
                }
            }

            public List<Integer> getResList() {
                return Collections.unmodifiableList(this.res_);
            }

            public int getResCount() {
                return this.res_.size();
            }

            public int getRes(int index) {
                return ((Integer) this.res_.get(index)).intValue();
            }

            public Builder setRes(int index, int value) {
                ensureResIsMutable();
                this.res_.set(index, Integer.valueOf(value));
                return this;
            }

            public Builder addRes(int value) {
                ensureResIsMutable();
                this.res_.add(Integer.valueOf(value));
                return this;
            }

            public Builder addAllRes(Iterable<? extends Integer> values) {
                ensureResIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.res_);
                return this;
            }

            public Builder clearRes() {
                this.res_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }
        }

        private Cookie(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private Cookie(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static Cookie getDefaultInstance() {
            return defaultInstance;
        }

        public Cookie getDefaultInstanceForType() {
            return defaultInstance;
        }

        public List<Integer> getResList() {
            return this.res_;
        }

        public int getResCount() {
            return this.res_.size();
        }

        public int getRes(int index) {
            return ((Integer) this.res_.get(index)).intValue();
        }

        private void initFields() {
            this.res_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == -1) {
                this.memoizedIsInitialized = 1;
                return true;
            } else if (isInitialized == 1) {
                return true;
            } else {
                return false;
            }
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.res_.size(); i++) {
                output.writeInt32(1, ((Integer) this.res_.get(i)).intValue());
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.res_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.res_.get(i)).intValue());
            }
            int size2 = 0 + dataSize + (getResList().size() * 1);
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Cookie parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Cookie parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Cookie parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Cookie parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Cookie parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Cookie parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static Cookie parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Cookie parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Cookie parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Cookie parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Cookie prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Im$CookieOrBuilder */
    public interface CookieOrBuilder extends MessageLiteOrBuilder {
        int getRes(int i);

        int getResCount();

        List<Integer> getResList();
    }

    /* renamed from: cn.jpush.im.proto.Im$Packet */
    public static final class Packet extends GeneratedMessageLite implements PacketOrBuilder {
        public static final int BODY_FIELD_NUMBER = 2;
        public static final int HEAD_FIELD_NUMBER = 1;
        private static final Packet defaultInstance = new Packet(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ProtocolBody body_;
        /* access modifiers changed from: private */
        public ProtocolHead head_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Im$Packet$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<Packet, Builder> implements PacketOrBuilder {
            private int bitField0_;
            private ProtocolBody body_ = ProtocolBody.getDefaultInstance();
            private ProtocolHead head_ = ProtocolHead.getDefaultInstance();

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                this.head_ = ProtocolHead.getDefaultInstance();
                this.bitField0_ &= -2;
                this.body_ = ProtocolBody.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Packet getDefaultInstanceForType() {
                return Packet.getDefaultInstance();
            }

            public Packet build() {
                Packet result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public Packet buildParsed() throws InvalidProtocolBufferException {
                Packet result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public Packet buildPartial() {
                Packet result = new Packet(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.head_ = this.head_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.body_ = this.body_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Packet other) {
                if (other != Packet.getDefaultInstance()) {
                    if (other.hasHead()) {
                        mergeHead(other.getHead());
                    }
                    if (other.hasBody()) {
                        mergeBody(other.getBody());
                    }
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            break;
                        case 10:
                            Builder subBuilder = ProtocolHead.newBuilder();
                            if (hasHead()) {
                                subBuilder.mergeFrom(getHead());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setHead(subBuilder.buildPartial());
                            continue;
                        case 18:
                            Builder subBuilder2 = ProtocolBody.newBuilder();
                            if (hasBody()) {
                                subBuilder2.mergeFrom(getBody());
                            }
                            input.readMessage(subBuilder2, extensionRegistry);
                            setBody(subBuilder2.buildPartial());
                            continue;
                        default:
                            if (!parseUnknownField(input, extensionRegistry, tag)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }

            public boolean hasHead() {
                return (this.bitField0_ & 1) == 1;
            }

            public ProtocolHead getHead() {
                return this.head_;
            }

            public Builder setHead(ProtocolHead value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.head_ = value;
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setHead(Builder builderForValue) {
                this.head_ = builderForValue.build();
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeHead(ProtocolHead value) {
                if ((this.bitField0_ & 1) != 1 || this.head_ == ProtocolHead.getDefaultInstance()) {
                    this.head_ = value;
                } else {
                    this.head_ = ProtocolHead.newBuilder(this.head_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearHead() {
                this.head_ = ProtocolHead.getDefaultInstance();
                this.bitField0_ &= -2;
                return this;
            }

            public boolean hasBody() {
                return (this.bitField0_ & 2) == 2;
            }

            public ProtocolBody getBody() {
                return this.body_;
            }

            public Builder setBody(ProtocolBody value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.body_ = value;
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setBody(Builder builderForValue) {
                this.body_ = builderForValue.build();
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeBody(ProtocolBody value) {
                if ((this.bitField0_ & 2) != 2 || this.body_ == ProtocolBody.getDefaultInstance()) {
                    this.body_ = value;
                } else {
                    this.body_ = ProtocolBody.newBuilder(this.body_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearBody() {
                this.body_ = ProtocolBody.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }
        }

        private Packet(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private Packet(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static Packet getDefaultInstance() {
            return defaultInstance;
        }

        public Packet getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasHead() {
            return (this.bitField0_ & 1) == 1;
        }

        public ProtocolHead getHead() {
            return this.head_;
        }

        public boolean hasBody() {
            return (this.bitField0_ & 2) == 2;
        }

        public ProtocolBody getBody() {
            return this.body_;
        }

        private void initFields() {
            this.head_ = ProtocolHead.getDefaultInstance();
            this.body_ = ProtocolBody.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == -1) {
                this.memoizedIsInitialized = 1;
                return true;
            } else if (isInitialized == 1) {
                return true;
            } else {
                return false;
            }
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.head_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.body_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeMessageSize(1, this.head_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, this.body_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Packet parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Packet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Packet parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Packet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Packet parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Packet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static Packet parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Packet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Packet parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Packet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Packet prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Im$PacketOrBuilder */
    public interface PacketOrBuilder extends MessageLiteOrBuilder {
        ProtocolBody getBody();

        ProtocolHead getHead();

        boolean hasBody();

        boolean hasHead();
    }

    /* renamed from: cn.jpush.im.proto.Im$ProtocolBody */
    public static final class ProtocolBody extends GeneratedMessageLite implements ProtocolBodyOrBuilder {
        public static final int ADD_BLACKLIST_FIELD_NUMBER = 18;
        public static final int ADD_FRIEND_FIELD_NUMBER = 5;
        public static final int ADD_GROUP_MEMBER_FIELD_NUMBER = 10;
        public static final int ADD_MSG_NO_DISTURB_GLOBAL_FIELD_NUMBER = 35;
        public static final int ADD_MSG_NO_DISTURB_GROUP_FIELD_NUMBER = 33;
        public static final int ADD_MSG_NO_DISTURB_SINGLE_FIELD_NUMBER = 31;
        public static final int CHAT_MSG_FIELD_NUMBER = 14;
        public static final int COMMON_REP_FIELD_NUMBER = 20;
        public static final int CREATE_GROUP_FIELD_NUMBER = 8;
        public static final int DELETE_MSG_NO_DISTURB_GLOBAL_FIELD_NUMBER = 36;
        public static final int DELETE_MSG_NO_DISTURB_GROUP_FIELD_NUMBER = 34;
        public static final int DELETE_MSG_NO_DISTURB_SINGLE_FIELD_NUMBER = 32;
        public static final int DEL_BLACKLIST_FIELD_NUMBER = 19;
        public static final int DEL_FRIEND_FIELD_NUMBER = 6;
        public static final int DEL_GROUP_MEMBER_FIELD_NUMBER = 11;
        public static final int EVENT_ANSWER_FIELD_NUMBER = 16;
        public static final int EVENT_NOTIFICATION_FIELD_NUMBER = 13;
        public static final int EVENT_SYNC_FIELD_NUMBER = 15;
        public static final int EXIT_GROUP_FIELD_NUMBER = 9;
        public static final int GROUP_MSG_FIELD_NUMBER = 4;
        public static final int LOGIN_FIELD_NUMBER = 1;
        public static final int LOGOUT_FIELD_NUMBER = 2;
        public static final int REPORT_INFO_FIELD_NUMBER = 23;
        public static final int SINGLE_MSG_FIELD_NUMBER = 3;
        public static final int UPDATE_GROUP_INFO_FIELD_NUMBER = 12;
        public static final int UPDATE_MEMO_FIELD_NUMBER = 7;
        private static final ProtocolBody defaultInstance = new ProtocolBody(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public AddBlackList addBlacklist_;
        /* access modifiers changed from: private */
        public AddFriend addFriend_;
        /* access modifiers changed from: private */
        public AddGroupMember addGroupMember_;
        /* access modifiers changed from: private */
        public AddMsgnoDisturbGlobal addMsgNoDisturbGlobal_;
        /* access modifiers changed from: private */
        public AddMsgnoDisturbGroup addMsgNoDisturbGroup_;
        /* access modifiers changed from: private */
        public AddMsgnoDisturbSingle addMsgNoDisturbSingle_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ChatMsgSync chatMsg_;
        /* access modifiers changed from: private */
        public Response commonRep_;
        /* access modifiers changed from: private */
        public CreateGroup createGroup_;
        /* access modifiers changed from: private */
        public DelBlackList delBlacklist_;
        /* access modifiers changed from: private */
        public DelFriend delFriend_;
        /* access modifiers changed from: private */
        public DelGroupMember delGroupMember_;
        /* access modifiers changed from: private */
        public DeleteMsgnoDisturbGlobal deleteMsgNoDisturbGlobal_;
        /* access modifiers changed from: private */
        public DeleteMsgnoDisturbGroup deleteMsgNoDisturbGroup_;
        /* access modifiers changed from: private */
        public DeleteMsgnoDisturbSingle deleteMsgNoDisturbSingle_;
        /* access modifiers changed from: private */
        public EventAnswer eventAnswer_;
        /* access modifiers changed from: private */
        public EventNotification eventNotification_;
        /* access modifiers changed from: private */
        public EventSync eventSync_;
        /* access modifiers changed from: private */
        public ExitGroup exitGroup_;
        /* access modifiers changed from: private */
        public GroupMsg groupMsg_;
        /* access modifiers changed from: private */
        public Login login_;
        /* access modifiers changed from: private */
        public Logout logout_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ReportInformation reportInfo_;
        /* access modifiers changed from: private */
        public SingleMsg singleMsg_;
        /* access modifiers changed from: private */
        public UpdateGroupInfo updateGroupInfo_;
        /* access modifiers changed from: private */
        public UpdateMemo updateMemo_;

        /* renamed from: cn.jpush.im.proto.Im$ProtocolBody$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ProtocolBody, Builder> implements ProtocolBodyOrBuilder {
            private AddBlackList addBlacklist_ = AddBlackList.getDefaultInstance();
            private AddFriend addFriend_ = AddFriend.getDefaultInstance();
            private AddGroupMember addGroupMember_ = AddGroupMember.getDefaultInstance();
            private AddMsgnoDisturbGlobal addMsgNoDisturbGlobal_ = AddMsgnoDisturbGlobal.getDefaultInstance();
            private AddMsgnoDisturbGroup addMsgNoDisturbGroup_ = AddMsgnoDisturbGroup.getDefaultInstance();
            private AddMsgnoDisturbSingle addMsgNoDisturbSingle_ = AddMsgnoDisturbSingle.getDefaultInstance();
            private int bitField0_;
            private ChatMsgSync chatMsg_ = ChatMsgSync.getDefaultInstance();
            private Response commonRep_ = Response.getDefaultInstance();
            private CreateGroup createGroup_ = CreateGroup.getDefaultInstance();
            private DelBlackList delBlacklist_ = DelBlackList.getDefaultInstance();
            private DelFriend delFriend_ = DelFriend.getDefaultInstance();
            private DelGroupMember delGroupMember_ = DelGroupMember.getDefaultInstance();
            private DeleteMsgnoDisturbGlobal deleteMsgNoDisturbGlobal_ = DeleteMsgnoDisturbGlobal.getDefaultInstance();
            private DeleteMsgnoDisturbGroup deleteMsgNoDisturbGroup_ = DeleteMsgnoDisturbGroup.getDefaultInstance();
            private DeleteMsgnoDisturbSingle deleteMsgNoDisturbSingle_ = DeleteMsgnoDisturbSingle.getDefaultInstance();
            private EventAnswer eventAnswer_ = EventAnswer.getDefaultInstance();
            private EventNotification eventNotification_ = EventNotification.getDefaultInstance();
            private EventSync eventSync_ = EventSync.getDefaultInstance();
            private ExitGroup exitGroup_ = ExitGroup.getDefaultInstance();
            private GroupMsg groupMsg_ = GroupMsg.getDefaultInstance();
            private Login login_ = Login.getDefaultInstance();
            private Logout logout_ = Logout.getDefaultInstance();
            private ReportInformation reportInfo_ = ReportInformation.getDefaultInstance();
            private SingleMsg singleMsg_ = SingleMsg.getDefaultInstance();
            private UpdateGroupInfo updateGroupInfo_ = UpdateGroupInfo.getDefaultInstance();
            private UpdateMemo updateMemo_ = UpdateMemo.getDefaultInstance();

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                this.login_ = Login.getDefaultInstance();
                this.bitField0_ &= -2;
                this.logout_ = Logout.getDefaultInstance();
                this.bitField0_ &= -3;
                this.singleMsg_ = SingleMsg.getDefaultInstance();
                this.bitField0_ &= -5;
                this.groupMsg_ = GroupMsg.getDefaultInstance();
                this.bitField0_ &= -9;
                this.addFriend_ = AddFriend.getDefaultInstance();
                this.bitField0_ &= -17;
                this.delFriend_ = DelFriend.getDefaultInstance();
                this.bitField0_ &= -33;
                this.updateMemo_ = UpdateMemo.getDefaultInstance();
                this.bitField0_ &= -65;
                this.createGroup_ = CreateGroup.getDefaultInstance();
                this.bitField0_ &= -129;
                this.exitGroup_ = ExitGroup.getDefaultInstance();
                this.bitField0_ &= -257;
                this.addGroupMember_ = AddGroupMember.getDefaultInstance();
                this.bitField0_ &= -513;
                this.delGroupMember_ = DelGroupMember.getDefaultInstance();
                this.bitField0_ &= -1025;
                this.updateGroupInfo_ = UpdateGroupInfo.getDefaultInstance();
                this.bitField0_ &= -2049;
                this.eventNotification_ = EventNotification.getDefaultInstance();
                this.bitField0_ &= -4097;
                this.chatMsg_ = ChatMsgSync.getDefaultInstance();
                this.bitField0_ &= -8193;
                this.eventSync_ = EventSync.getDefaultInstance();
                this.bitField0_ &= -16385;
                this.eventAnswer_ = EventAnswer.getDefaultInstance();
                this.bitField0_ &= -32769;
                this.addBlacklist_ = AddBlackList.getDefaultInstance();
                this.bitField0_ &= -65537;
                this.delBlacklist_ = DelBlackList.getDefaultInstance();
                this.bitField0_ &= -131073;
                this.commonRep_ = Response.getDefaultInstance();
                this.bitField0_ &= -262145;
                this.reportInfo_ = ReportInformation.getDefaultInstance();
                this.bitField0_ &= -524289;
                this.addMsgNoDisturbSingle_ = AddMsgnoDisturbSingle.getDefaultInstance();
                this.bitField0_ &= -1048577;
                this.deleteMsgNoDisturbSingle_ = DeleteMsgnoDisturbSingle.getDefaultInstance();
                this.bitField0_ &= -2097153;
                this.addMsgNoDisturbGroup_ = AddMsgnoDisturbGroup.getDefaultInstance();
                this.bitField0_ &= -4194305;
                this.deleteMsgNoDisturbGroup_ = DeleteMsgnoDisturbGroup.getDefaultInstance();
                this.bitField0_ &= -8388609;
                this.addMsgNoDisturbGlobal_ = AddMsgnoDisturbGlobal.getDefaultInstance();
                this.bitField0_ &= -16777217;
                this.deleteMsgNoDisturbGlobal_ = DeleteMsgnoDisturbGlobal.getDefaultInstance();
                this.bitField0_ &= -33554433;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ProtocolBody getDefaultInstanceForType() {
                return ProtocolBody.getDefaultInstance();
            }

            public ProtocolBody build() {
                ProtocolBody result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ProtocolBody buildParsed() throws InvalidProtocolBufferException {
                ProtocolBody result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ProtocolBody buildPartial() {
                ProtocolBody result = new ProtocolBody(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.login_ = this.login_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.logout_ = this.logout_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.singleMsg_ = this.singleMsg_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.groupMsg_ = this.groupMsg_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.addFriend_ = this.addFriend_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.delFriend_ = this.delFriend_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.updateMemo_ = this.updateMemo_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 128;
                }
                result.createGroup_ = this.createGroup_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.exitGroup_ = this.exitGroup_;
                if ((from_bitField0_ & 512) == 512) {
                    to_bitField0_ |= 512;
                }
                result.addGroupMember_ = this.addGroupMember_;
                if ((from_bitField0_ & 1024) == 1024) {
                    to_bitField0_ |= 1024;
                }
                result.delGroupMember_ = this.delGroupMember_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.updateGroupInfo_ = this.updateGroupInfo_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.eventNotification_ = this.eventNotification_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8192;
                }
                result.chatMsg_ = this.chatMsg_;
                if ((from_bitField0_ & 16384) == 16384) {
                    to_bitField0_ |= 16384;
                }
                result.eventSync_ = this.eventSync_;
                if ((from_bitField0_ & 32768) == 32768) {
                    to_bitField0_ |= 32768;
                }
                result.eventAnswer_ = this.eventAnswer_;
                if ((from_bitField0_ & 65536) == 65536) {
                    to_bitField0_ |= 65536;
                }
                result.addBlacklist_ = this.addBlacklist_;
                if ((from_bitField0_ & 131072) == 131072) {
                    to_bitField0_ |= 131072;
                }
                result.delBlacklist_ = this.delBlacklist_;
                if ((from_bitField0_ & 262144) == 262144) {
                    to_bitField0_ |= 262144;
                }
                result.commonRep_ = this.commonRep_;
                if ((from_bitField0_ & 524288) == 524288) {
                    to_bitField0_ |= 524288;
                }
                result.reportInfo_ = this.reportInfo_;
                if ((1048576 & from_bitField0_) == 1048576) {
                    to_bitField0_ |= 1048576;
                }
                result.addMsgNoDisturbSingle_ = this.addMsgNoDisturbSingle_;
                if ((2097152 & from_bitField0_) == 2097152) {
                    to_bitField0_ |= PKIFailureInfo.badSenderNonce;
                }
                result.deleteMsgNoDisturbSingle_ = this.deleteMsgNoDisturbSingle_;
                if ((4194304 & from_bitField0_) == 4194304) {
                    to_bitField0_ |= 4194304;
                }
                result.addMsgNoDisturbGroup_ = this.addMsgNoDisturbGroup_;
                if ((8388608 & from_bitField0_) == 8388608) {
                    to_bitField0_ |= 8388608;
                }
                result.deleteMsgNoDisturbGroup_ = this.deleteMsgNoDisturbGroup_;
                if ((16777216 & from_bitField0_) == 16777216) {
                    to_bitField0_ |= 16777216;
                }
                result.addMsgNoDisturbGlobal_ = this.addMsgNoDisturbGlobal_;
                if ((33554432 & from_bitField0_) == 33554432) {
                    to_bitField0_ |= 33554432;
                }
                result.deleteMsgNoDisturbGlobal_ = this.deleteMsgNoDisturbGlobal_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ProtocolBody other) {
                if (other != ProtocolBody.getDefaultInstance()) {
                    if (other.hasLogin()) {
                        mergeLogin(other.getLogin());
                    }
                    if (other.hasLogout()) {
                        mergeLogout(other.getLogout());
                    }
                    if (other.hasSingleMsg()) {
                        mergeSingleMsg(other.getSingleMsg());
                    }
                    if (other.hasGroupMsg()) {
                        mergeGroupMsg(other.getGroupMsg());
                    }
                    if (other.hasAddFriend()) {
                        mergeAddFriend(other.getAddFriend());
                    }
                    if (other.hasDelFriend()) {
                        mergeDelFriend(other.getDelFriend());
                    }
                    if (other.hasUpdateMemo()) {
                        mergeUpdateMemo(other.getUpdateMemo());
                    }
                    if (other.hasCreateGroup()) {
                        mergeCreateGroup(other.getCreateGroup());
                    }
                    if (other.hasExitGroup()) {
                        mergeExitGroup(other.getExitGroup());
                    }
                    if (other.hasAddGroupMember()) {
                        mergeAddGroupMember(other.getAddGroupMember());
                    }
                    if (other.hasDelGroupMember()) {
                        mergeDelGroupMember(other.getDelGroupMember());
                    }
                    if (other.hasUpdateGroupInfo()) {
                        mergeUpdateGroupInfo(other.getUpdateGroupInfo());
                    }
                    if (other.hasEventNotification()) {
                        mergeEventNotification(other.getEventNotification());
                    }
                    if (other.hasChatMsg()) {
                        mergeChatMsg(other.getChatMsg());
                    }
                    if (other.hasEventSync()) {
                        mergeEventSync(other.getEventSync());
                    }
                    if (other.hasEventAnswer()) {
                        mergeEventAnswer(other.getEventAnswer());
                    }
                    if (other.hasAddBlacklist()) {
                        mergeAddBlacklist(other.getAddBlacklist());
                    }
                    if (other.hasDelBlacklist()) {
                        mergeDelBlacklist(other.getDelBlacklist());
                    }
                    if (other.hasCommonRep()) {
                        mergeCommonRep(other.getCommonRep());
                    }
                    if (other.hasReportInfo()) {
                        mergeReportInfo(other.getReportInfo());
                    }
                    if (other.hasAddMsgNoDisturbSingle()) {
                        mergeAddMsgNoDisturbSingle(other.getAddMsgNoDisturbSingle());
                    }
                    if (other.hasDeleteMsgNoDisturbSingle()) {
                        mergeDeleteMsgNoDisturbSingle(other.getDeleteMsgNoDisturbSingle());
                    }
                    if (other.hasAddMsgNoDisturbGroup()) {
                        mergeAddMsgNoDisturbGroup(other.getAddMsgNoDisturbGroup());
                    }
                    if (other.hasDeleteMsgNoDisturbGroup()) {
                        mergeDeleteMsgNoDisturbGroup(other.getDeleteMsgNoDisturbGroup());
                    }
                    if (other.hasAddMsgNoDisturbGlobal()) {
                        mergeAddMsgNoDisturbGlobal(other.getAddMsgNoDisturbGlobal());
                    }
                    if (other.hasDeleteMsgNoDisturbGlobal()) {
                        mergeDeleteMsgNoDisturbGlobal(other.getDeleteMsgNoDisturbGlobal());
                    }
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            break;
                        case 10:
                            p005cn.jpush.p034im.proto.User.Login.Builder subBuilder = Login.newBuilder();
                            if (hasLogin()) {
                                subBuilder.mergeFrom(getLogin());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setLogin(subBuilder.buildPartial());
                            continue;
                        case 18:
                            p005cn.jpush.p034im.proto.User.Logout.Builder subBuilder2 = Logout.newBuilder();
                            if (hasLogout()) {
                                subBuilder2.mergeFrom(getLogout());
                            }
                            input.readMessage(subBuilder2, extensionRegistry);
                            setLogout(subBuilder2.buildPartial());
                            continue;
                        case 26:
                            p005cn.jpush.p034im.proto.C1623Message.SingleMsg.Builder subBuilder3 = SingleMsg.newBuilder();
                            if (hasSingleMsg()) {
                                subBuilder3.mergeFrom(getSingleMsg());
                            }
                            input.readMessage(subBuilder3, extensionRegistry);
                            setSingleMsg(subBuilder3.buildPartial());
                            continue;
                        case 34:
                            p005cn.jpush.p034im.proto.C1623Message.GroupMsg.Builder subBuilder4 = GroupMsg.newBuilder();
                            if (hasGroupMsg()) {
                                subBuilder4.mergeFrom(getGroupMsg());
                            }
                            input.readMessage(subBuilder4, extensionRegistry);
                            setGroupMsg(subBuilder4.buildPartial());
                            continue;
                        case 42:
                            p005cn.jpush.p034im.proto.Friend.AddFriend.Builder subBuilder5 = AddFriend.newBuilder();
                            if (hasAddFriend()) {
                                subBuilder5.mergeFrom(getAddFriend());
                            }
                            input.readMessage(subBuilder5, extensionRegistry);
                            setAddFriend(subBuilder5.buildPartial());
                            continue;
                        case 50:
                            p005cn.jpush.p034im.proto.Friend.DelFriend.Builder subBuilder6 = DelFriend.newBuilder();
                            if (hasDelFriend()) {
                                subBuilder6.mergeFrom(getDelFriend());
                            }
                            input.readMessage(subBuilder6, extensionRegistry);
                            setDelFriend(subBuilder6.buildPartial());
                            continue;
                        case 58:
                            p005cn.jpush.p034im.proto.Friend.UpdateMemo.Builder subBuilder7 = UpdateMemo.newBuilder();
                            if (hasUpdateMemo()) {
                                subBuilder7.mergeFrom(getUpdateMemo());
                            }
                            input.readMessage(subBuilder7, extensionRegistry);
                            setUpdateMemo(subBuilder7.buildPartial());
                            continue;
                        case 66:
                            p005cn.jpush.p034im.proto.Group.CreateGroup.Builder subBuilder8 = CreateGroup.newBuilder();
                            if (hasCreateGroup()) {
                                subBuilder8.mergeFrom(getCreateGroup());
                            }
                            input.readMessage(subBuilder8, extensionRegistry);
                            setCreateGroup(subBuilder8.buildPartial());
                            continue;
                        case 74:
                            p005cn.jpush.p034im.proto.Group.ExitGroup.Builder subBuilder9 = ExitGroup.newBuilder();
                            if (hasExitGroup()) {
                                subBuilder9.mergeFrom(getExitGroup());
                            }
                            input.readMessage(subBuilder9, extensionRegistry);
                            setExitGroup(subBuilder9.buildPartial());
                            continue;
                        case 82:
                            p005cn.jpush.p034im.proto.Group.AddGroupMember.Builder subBuilder10 = AddGroupMember.newBuilder();
                            if (hasAddGroupMember()) {
                                subBuilder10.mergeFrom(getAddGroupMember());
                            }
                            input.readMessage(subBuilder10, extensionRegistry);
                            setAddGroupMember(subBuilder10.buildPartial());
                            continue;
                        case 90:
                            p005cn.jpush.p034im.proto.Group.DelGroupMember.Builder subBuilder11 = DelGroupMember.newBuilder();
                            if (hasDelGroupMember()) {
                                subBuilder11.mergeFrom(getDelGroupMember());
                            }
                            input.readMessage(subBuilder11, extensionRegistry);
                            setDelGroupMember(subBuilder11.buildPartial());
                            continue;
                        case 98:
                            p005cn.jpush.p034im.proto.Group.UpdateGroupInfo.Builder subBuilder12 = UpdateGroupInfo.newBuilder();
                            if (hasUpdateGroupInfo()) {
                                subBuilder12.mergeFrom(getUpdateGroupInfo());
                            }
                            input.readMessage(subBuilder12, extensionRegistry);
                            setUpdateGroupInfo(subBuilder12.buildPartial());
                            continue;
                        case 106:
                            p005cn.jpush.p034im.proto.C1623Message.EventNotification.Builder subBuilder13 = EventNotification.newBuilder();
                            if (hasEventNotification()) {
                                subBuilder13.mergeFrom(getEventNotification());
                            }
                            input.readMessage(subBuilder13, extensionRegistry);
                            setEventNotification(subBuilder13.buildPartial());
                            continue;
                        case 114:
                            p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync.Builder subBuilder14 = ChatMsgSync.newBuilder();
                            if (hasChatMsg()) {
                                subBuilder14.mergeFrom(getChatMsg());
                            }
                            input.readMessage(subBuilder14, extensionRegistry);
                            setChatMsg(subBuilder14.buildPartial());
                            continue;
                        case EACTags.SECURITY_SUPPORT_TEMPLATE /*122*/:
                            p005cn.jpush.p034im.proto.C1623Message.EventSync.Builder subBuilder15 = EventSync.newBuilder();
                            if (hasEventSync()) {
                                subBuilder15.mergeFrom(getEventSync());
                            }
                            input.readMessage(subBuilder15, extensionRegistry);
                            setEventSync(subBuilder15.buildPartial());
                            continue;
                        case ISO781611.BIOMETRIC_SUBTYPE_TAG /*130*/:
                            p005cn.jpush.p034im.proto.C1623Message.EventAnswer.Builder subBuilder16 = EventAnswer.newBuilder();
                            if (hasEventAnswer()) {
                                subBuilder16.mergeFrom(getEventAnswer());
                            }
                            input.readMessage(subBuilder16, extensionRegistry);
                            setEventAnswer(subBuilder16.buildPartial());
                            continue;
                        case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /*146*/:
                            p005cn.jpush.p034im.proto.Friend.AddBlackList.Builder subBuilder17 = AddBlackList.newBuilder();
                            if (hasAddBlacklist()) {
                                subBuilder17.mergeFrom(getAddBlacklist());
                            }
                            input.readMessage(subBuilder17, extensionRegistry);
                            setAddBlacklist(subBuilder17.buildPartial());
                            continue;
                        case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /*154*/:
                            p005cn.jpush.p034im.proto.Friend.DelBlackList.Builder subBuilder18 = DelBlackList.newBuilder();
                            if (hasDelBlacklist()) {
                                subBuilder18.mergeFrom(getDelBlacklist());
                            }
                            input.readMessage(subBuilder18, extensionRegistry);
                            setDelBlacklist(subBuilder18.buildPartial());
                            continue;
                        case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /*162*/:
                            Builder subBuilder19 = Response.newBuilder();
                            if (hasCommonRep()) {
                                subBuilder19.mergeFrom(getCommonRep());
                            }
                            input.readMessage(subBuilder19, extensionRegistry);
                            setCommonRep(subBuilder19.buildPartial());
                            continue;
                        case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*186*/:
                            p005cn.jpush.p034im.proto.User.ReportInformation.Builder subBuilder20 = ReportInformation.newBuilder();
                            if (hasReportInfo()) {
                                subBuilder20.mergeFrom(getReportInfo());
                            }
                            input.readMessage(subBuilder20, extensionRegistry);
                            setReportInfo(subBuilder20.buildPartial());
                            continue;
                        case 250:
                            p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbSingle.Builder subBuilder21 = AddMsgnoDisturbSingle.newBuilder();
                            if (hasAddMsgNoDisturbSingle()) {
                                subBuilder21.mergeFrom(getAddMsgNoDisturbSingle());
                            }
                            input.readMessage(subBuilder21, extensionRegistry);
                            setAddMsgNoDisturbSingle(subBuilder21.buildPartial());
                            continue;
                        case 258:
                            p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbSingle.Builder subBuilder22 = DeleteMsgnoDisturbSingle.newBuilder();
                            if (hasDeleteMsgNoDisturbSingle()) {
                                subBuilder22.mergeFrom(getDeleteMsgNoDisturbSingle());
                            }
                            input.readMessage(subBuilder22, extensionRegistry);
                            setDeleteMsgNoDisturbSingle(subBuilder22.buildPartial());
                            continue;
                        case 266:
                            p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGroup.Builder subBuilder23 = AddMsgnoDisturbGroup.newBuilder();
                            if (hasAddMsgNoDisturbGroup()) {
                                subBuilder23.mergeFrom(getAddMsgNoDisturbGroup());
                            }
                            input.readMessage(subBuilder23, extensionRegistry);
                            setAddMsgNoDisturbGroup(subBuilder23.buildPartial());
                            continue;
                        case TiffUtil.TIFF_TAG_ORIENTATION /*274*/:
                            p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGroup.Builder subBuilder24 = DeleteMsgnoDisturbGroup.newBuilder();
                            if (hasDeleteMsgNoDisturbGroup()) {
                                subBuilder24.mergeFrom(getDeleteMsgNoDisturbGroup());
                            }
                            input.readMessage(subBuilder24, extensionRegistry);
                            setDeleteMsgNoDisturbGroup(subBuilder24.buildPartial());
                            continue;
                        case 282:
                            p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGlobal.Builder subBuilder25 = AddMsgnoDisturbGlobal.newBuilder();
                            if (hasAddMsgNoDisturbGlobal()) {
                                subBuilder25.mergeFrom(getAddMsgNoDisturbGlobal());
                            }
                            input.readMessage(subBuilder25, extensionRegistry);
                            setAddMsgNoDisturbGlobal(subBuilder25.buildPartial());
                            continue;
                        case 290:
                            p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGlobal.Builder subBuilder26 = DeleteMsgnoDisturbGlobal.newBuilder();
                            if (hasDeleteMsgNoDisturbGlobal()) {
                                subBuilder26.mergeFrom(getDeleteMsgNoDisturbGlobal());
                            }
                            input.readMessage(subBuilder26, extensionRegistry);
                            setDeleteMsgNoDisturbGlobal(subBuilder26.buildPartial());
                            continue;
                        default:
                            if (!parseUnknownField(input, extensionRegistry, tag)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }

            public boolean hasLogin() {
                return (this.bitField0_ & 1) == 1;
            }

            public Login getLogin() {
                return this.login_;
            }

            public Builder setLogin(Login value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.login_ = value;
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setLogin(p005cn.jpush.p034im.proto.User.Login.Builder builderForValue) {
                this.login_ = builderForValue.build();
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeLogin(Login value) {
                if ((this.bitField0_ & 1) != 1 || this.login_ == Login.getDefaultInstance()) {
                    this.login_ = value;
                } else {
                    this.login_ = Login.newBuilder(this.login_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearLogin() {
                this.login_ = Login.getDefaultInstance();
                this.bitField0_ &= -2;
                return this;
            }

            public boolean hasLogout() {
                return (this.bitField0_ & 2) == 2;
            }

            public Logout getLogout() {
                return this.logout_;
            }

            public Builder setLogout(Logout value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.logout_ = value;
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setLogout(p005cn.jpush.p034im.proto.User.Logout.Builder builderForValue) {
                this.logout_ = builderForValue.build();
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeLogout(Logout value) {
                if ((this.bitField0_ & 2) != 2 || this.logout_ == Logout.getDefaultInstance()) {
                    this.logout_ = value;
                } else {
                    this.logout_ = Logout.newBuilder(this.logout_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearLogout() {
                this.logout_ = Logout.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }

            public boolean hasSingleMsg() {
                return (this.bitField0_ & 4) == 4;
            }

            public SingleMsg getSingleMsg() {
                return this.singleMsg_;
            }

            public Builder setSingleMsg(SingleMsg value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.singleMsg_ = value;
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setSingleMsg(p005cn.jpush.p034im.proto.C1623Message.SingleMsg.Builder builderForValue) {
                this.singleMsg_ = builderForValue.build();
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeSingleMsg(SingleMsg value) {
                if ((this.bitField0_ & 4) != 4 || this.singleMsg_ == SingleMsg.getDefaultInstance()) {
                    this.singleMsg_ = value;
                } else {
                    this.singleMsg_ = SingleMsg.newBuilder(this.singleMsg_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearSingleMsg() {
                this.singleMsg_ = SingleMsg.getDefaultInstance();
                this.bitField0_ &= -5;
                return this;
            }

            public boolean hasGroupMsg() {
                return (this.bitField0_ & 8) == 8;
            }

            public GroupMsg getGroupMsg() {
                return this.groupMsg_;
            }

            public Builder setGroupMsg(GroupMsg value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.groupMsg_ = value;
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setGroupMsg(p005cn.jpush.p034im.proto.C1623Message.GroupMsg.Builder builderForValue) {
                this.groupMsg_ = builderForValue.build();
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeGroupMsg(GroupMsg value) {
                if ((this.bitField0_ & 8) != 8 || this.groupMsg_ == GroupMsg.getDefaultInstance()) {
                    this.groupMsg_ = value;
                } else {
                    this.groupMsg_ = GroupMsg.newBuilder(this.groupMsg_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearGroupMsg() {
                this.groupMsg_ = GroupMsg.getDefaultInstance();
                this.bitField0_ &= -9;
                return this;
            }

            public boolean hasAddFriend() {
                return (this.bitField0_ & 16) == 16;
            }

            public AddFriend getAddFriend() {
                return this.addFriend_;
            }

            public Builder setAddFriend(AddFriend value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addFriend_ = value;
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setAddFriend(p005cn.jpush.p034im.proto.Friend.AddFriend.Builder builderForValue) {
                this.addFriend_ = builderForValue.build();
                this.bitField0_ |= 16;
                return this;
            }

            public Builder mergeAddFriend(AddFriend value) {
                if ((this.bitField0_ & 16) != 16 || this.addFriend_ == AddFriend.getDefaultInstance()) {
                    this.addFriend_ = value;
                } else {
                    this.addFriend_ = AddFriend.newBuilder(this.addFriend_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder clearAddFriend() {
                this.addFriend_ = AddFriend.getDefaultInstance();
                this.bitField0_ &= -17;
                return this;
            }

            public boolean hasDelFriend() {
                return (this.bitField0_ & 32) == 32;
            }

            public DelFriend getDelFriend() {
                return this.delFriend_;
            }

            public Builder setDelFriend(DelFriend value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.delFriend_ = value;
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setDelFriend(p005cn.jpush.p034im.proto.Friend.DelFriend.Builder builderForValue) {
                this.delFriend_ = builderForValue.build();
                this.bitField0_ |= 32;
                return this;
            }

            public Builder mergeDelFriend(DelFriend value) {
                if ((this.bitField0_ & 32) != 32 || this.delFriend_ == DelFriend.getDefaultInstance()) {
                    this.delFriend_ = value;
                } else {
                    this.delFriend_ = DelFriend.newBuilder(this.delFriend_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder clearDelFriend() {
                this.delFriend_ = DelFriend.getDefaultInstance();
                this.bitField0_ &= -33;
                return this;
            }

            public boolean hasUpdateMemo() {
                return (this.bitField0_ & 64) == 64;
            }

            public UpdateMemo getUpdateMemo() {
                return this.updateMemo_;
            }

            public Builder setUpdateMemo(UpdateMemo value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.updateMemo_ = value;
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setUpdateMemo(p005cn.jpush.p034im.proto.Friend.UpdateMemo.Builder builderForValue) {
                this.updateMemo_ = builderForValue.build();
                this.bitField0_ |= 64;
                return this;
            }

            public Builder mergeUpdateMemo(UpdateMemo value) {
                if ((this.bitField0_ & 64) != 64 || this.updateMemo_ == UpdateMemo.getDefaultInstance()) {
                    this.updateMemo_ = value;
                } else {
                    this.updateMemo_ = UpdateMemo.newBuilder(this.updateMemo_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder clearUpdateMemo() {
                this.updateMemo_ = UpdateMemo.getDefaultInstance();
                this.bitField0_ &= -65;
                return this;
            }

            public boolean hasCreateGroup() {
                return (this.bitField0_ & 128) == 128;
            }

            public CreateGroup getCreateGroup() {
                return this.createGroup_;
            }

            public Builder setCreateGroup(CreateGroup value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.createGroup_ = value;
                this.bitField0_ |= 128;
                return this;
            }

            public Builder setCreateGroup(p005cn.jpush.p034im.proto.Group.CreateGroup.Builder builderForValue) {
                this.createGroup_ = builderForValue.build();
                this.bitField0_ |= 128;
                return this;
            }

            public Builder mergeCreateGroup(CreateGroup value) {
                if ((this.bitField0_ & 128) != 128 || this.createGroup_ == CreateGroup.getDefaultInstance()) {
                    this.createGroup_ = value;
                } else {
                    this.createGroup_ = CreateGroup.newBuilder(this.createGroup_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder clearCreateGroup() {
                this.createGroup_ = CreateGroup.getDefaultInstance();
                this.bitField0_ &= -129;
                return this;
            }

            public boolean hasExitGroup() {
                return (this.bitField0_ & 256) == 256;
            }

            public ExitGroup getExitGroup() {
                return this.exitGroup_;
            }

            public Builder setExitGroup(ExitGroup value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.exitGroup_ = value;
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setExitGroup(p005cn.jpush.p034im.proto.Group.ExitGroup.Builder builderForValue) {
                this.exitGroup_ = builderForValue.build();
                this.bitField0_ |= 256;
                return this;
            }

            public Builder mergeExitGroup(ExitGroup value) {
                if ((this.bitField0_ & 256) != 256 || this.exitGroup_ == ExitGroup.getDefaultInstance()) {
                    this.exitGroup_ = value;
                } else {
                    this.exitGroup_ = ExitGroup.newBuilder(this.exitGroup_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder clearExitGroup() {
                this.exitGroup_ = ExitGroup.getDefaultInstance();
                this.bitField0_ &= -257;
                return this;
            }

            public boolean hasAddGroupMember() {
                return (this.bitField0_ & 512) == 512;
            }

            public AddGroupMember getAddGroupMember() {
                return this.addGroupMember_;
            }

            public Builder setAddGroupMember(AddGroupMember value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addGroupMember_ = value;
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setAddGroupMember(p005cn.jpush.p034im.proto.Group.AddGroupMember.Builder builderForValue) {
                this.addGroupMember_ = builderForValue.build();
                this.bitField0_ |= 512;
                return this;
            }

            public Builder mergeAddGroupMember(AddGroupMember value) {
                if ((this.bitField0_ & 512) != 512 || this.addGroupMember_ == AddGroupMember.getDefaultInstance()) {
                    this.addGroupMember_ = value;
                } else {
                    this.addGroupMember_ = AddGroupMember.newBuilder(this.addGroupMember_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder clearAddGroupMember() {
                this.addGroupMember_ = AddGroupMember.getDefaultInstance();
                this.bitField0_ &= -513;
                return this;
            }

            public boolean hasDelGroupMember() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public DelGroupMember getDelGroupMember() {
                return this.delGroupMember_;
            }

            public Builder setDelGroupMember(DelGroupMember value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.delGroupMember_ = value;
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder setDelGroupMember(p005cn.jpush.p034im.proto.Group.DelGroupMember.Builder builderForValue) {
                this.delGroupMember_ = builderForValue.build();
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder mergeDelGroupMember(DelGroupMember value) {
                if ((this.bitField0_ & 1024) != 1024 || this.delGroupMember_ == DelGroupMember.getDefaultInstance()) {
                    this.delGroupMember_ = value;
                } else {
                    this.delGroupMember_ = DelGroupMember.newBuilder(this.delGroupMember_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder clearDelGroupMember() {
                this.delGroupMember_ = DelGroupMember.getDefaultInstance();
                this.bitField0_ &= -1025;
                return this;
            }

            public boolean hasUpdateGroupInfo() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public UpdateGroupInfo getUpdateGroupInfo() {
                return this.updateGroupInfo_;
            }

            public Builder setUpdateGroupInfo(UpdateGroupInfo value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.updateGroupInfo_ = value;
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder setUpdateGroupInfo(p005cn.jpush.p034im.proto.Group.UpdateGroupInfo.Builder builderForValue) {
                this.updateGroupInfo_ = builderForValue.build();
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder mergeUpdateGroupInfo(UpdateGroupInfo value) {
                if ((this.bitField0_ & 2048) != 2048 || this.updateGroupInfo_ == UpdateGroupInfo.getDefaultInstance()) {
                    this.updateGroupInfo_ = value;
                } else {
                    this.updateGroupInfo_ = UpdateGroupInfo.newBuilder(this.updateGroupInfo_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder clearUpdateGroupInfo() {
                this.updateGroupInfo_ = UpdateGroupInfo.getDefaultInstance();
                this.bitField0_ &= -2049;
                return this;
            }

            public boolean hasEventNotification() {
                return (this.bitField0_ & 4096) == 4096;
            }

            public EventNotification getEventNotification() {
                return this.eventNotification_;
            }

            public Builder setEventNotification(EventNotification value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.eventNotification_ = value;
                this.bitField0_ |= 4096;
                return this;
            }

            public Builder setEventNotification(p005cn.jpush.p034im.proto.C1623Message.EventNotification.Builder builderForValue) {
                this.eventNotification_ = builderForValue.build();
                this.bitField0_ |= 4096;
                return this;
            }

            public Builder mergeEventNotification(EventNotification value) {
                if ((this.bitField0_ & 4096) != 4096 || this.eventNotification_ == EventNotification.getDefaultInstance()) {
                    this.eventNotification_ = value;
                } else {
                    this.eventNotification_ = EventNotification.newBuilder(this.eventNotification_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 4096;
                return this;
            }

            public Builder clearEventNotification() {
                this.eventNotification_ = EventNotification.getDefaultInstance();
                this.bitField0_ &= -4097;
                return this;
            }

            public boolean hasChatMsg() {
                return (this.bitField0_ & 8192) == 8192;
            }

            public ChatMsgSync getChatMsg() {
                return this.chatMsg_;
            }

            public Builder setChatMsg(ChatMsgSync value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.chatMsg_ = value;
                this.bitField0_ |= 8192;
                return this;
            }

            public Builder setChatMsg(p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync.Builder builderForValue) {
                this.chatMsg_ = builderForValue.build();
                this.bitField0_ |= 8192;
                return this;
            }

            public Builder mergeChatMsg(ChatMsgSync value) {
                if ((this.bitField0_ & 8192) != 8192 || this.chatMsg_ == ChatMsgSync.getDefaultInstance()) {
                    this.chatMsg_ = value;
                } else {
                    this.chatMsg_ = ChatMsgSync.newBuilder(this.chatMsg_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 8192;
                return this;
            }

            public Builder clearChatMsg() {
                this.chatMsg_ = ChatMsgSync.getDefaultInstance();
                this.bitField0_ &= -8193;
                return this;
            }

            public boolean hasEventSync() {
                return (this.bitField0_ & 16384) == 16384;
            }

            public EventSync getEventSync() {
                return this.eventSync_;
            }

            public Builder setEventSync(EventSync value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.eventSync_ = value;
                this.bitField0_ |= 16384;
                return this;
            }

            public Builder setEventSync(p005cn.jpush.p034im.proto.C1623Message.EventSync.Builder builderForValue) {
                this.eventSync_ = builderForValue.build();
                this.bitField0_ |= 16384;
                return this;
            }

            public Builder mergeEventSync(EventSync value) {
                if ((this.bitField0_ & 16384) != 16384 || this.eventSync_ == EventSync.getDefaultInstance()) {
                    this.eventSync_ = value;
                } else {
                    this.eventSync_ = EventSync.newBuilder(this.eventSync_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 16384;
                return this;
            }

            public Builder clearEventSync() {
                this.eventSync_ = EventSync.getDefaultInstance();
                this.bitField0_ &= -16385;
                return this;
            }

            public boolean hasEventAnswer() {
                return (this.bitField0_ & 32768) == 32768;
            }

            public EventAnswer getEventAnswer() {
                return this.eventAnswer_;
            }

            public Builder setEventAnswer(EventAnswer value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.eventAnswer_ = value;
                this.bitField0_ |= 32768;
                return this;
            }

            public Builder setEventAnswer(p005cn.jpush.p034im.proto.C1623Message.EventAnswer.Builder builderForValue) {
                this.eventAnswer_ = builderForValue.build();
                this.bitField0_ |= 32768;
                return this;
            }

            public Builder mergeEventAnswer(EventAnswer value) {
                if ((this.bitField0_ & 32768) != 32768 || this.eventAnswer_ == EventAnswer.getDefaultInstance()) {
                    this.eventAnswer_ = value;
                } else {
                    this.eventAnswer_ = EventAnswer.newBuilder(this.eventAnswer_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 32768;
                return this;
            }

            public Builder clearEventAnswer() {
                this.eventAnswer_ = EventAnswer.getDefaultInstance();
                this.bitField0_ &= -32769;
                return this;
            }

            public boolean hasAddBlacklist() {
                return (this.bitField0_ & 65536) == 65536;
            }

            public AddBlackList getAddBlacklist() {
                return this.addBlacklist_;
            }

            public Builder setAddBlacklist(AddBlackList value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addBlacklist_ = value;
                this.bitField0_ |= 65536;
                return this;
            }

            public Builder setAddBlacklist(p005cn.jpush.p034im.proto.Friend.AddBlackList.Builder builderForValue) {
                this.addBlacklist_ = builderForValue.build();
                this.bitField0_ |= 65536;
                return this;
            }

            public Builder mergeAddBlacklist(AddBlackList value) {
                if ((this.bitField0_ & 65536) != 65536 || this.addBlacklist_ == AddBlackList.getDefaultInstance()) {
                    this.addBlacklist_ = value;
                } else {
                    this.addBlacklist_ = AddBlackList.newBuilder(this.addBlacklist_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 65536;
                return this;
            }

            public Builder clearAddBlacklist() {
                this.addBlacklist_ = AddBlackList.getDefaultInstance();
                this.bitField0_ &= -65537;
                return this;
            }

            public boolean hasDelBlacklist() {
                return (this.bitField0_ & 131072) == 131072;
            }

            public DelBlackList getDelBlacklist() {
                return this.delBlacklist_;
            }

            public Builder setDelBlacklist(DelBlackList value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.delBlacklist_ = value;
                this.bitField0_ |= 131072;
                return this;
            }

            public Builder setDelBlacklist(p005cn.jpush.p034im.proto.Friend.DelBlackList.Builder builderForValue) {
                this.delBlacklist_ = builderForValue.build();
                this.bitField0_ |= 131072;
                return this;
            }

            public Builder mergeDelBlacklist(DelBlackList value) {
                if ((this.bitField0_ & 131072) != 131072 || this.delBlacklist_ == DelBlackList.getDefaultInstance()) {
                    this.delBlacklist_ = value;
                } else {
                    this.delBlacklist_ = DelBlackList.newBuilder(this.delBlacklist_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 131072;
                return this;
            }

            public Builder clearDelBlacklist() {
                this.delBlacklist_ = DelBlackList.getDefaultInstance();
                this.bitField0_ &= -131073;
                return this;
            }

            public boolean hasCommonRep() {
                return (this.bitField0_ & 262144) == 262144;
            }

            public Response getCommonRep() {
                return this.commonRep_;
            }

            public Builder setCommonRep(Response value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.commonRep_ = value;
                this.bitField0_ |= 262144;
                return this;
            }

            public Builder setCommonRep(Builder builderForValue) {
                this.commonRep_ = builderForValue.build();
                this.bitField0_ |= 262144;
                return this;
            }

            public Builder mergeCommonRep(Response value) {
                if ((this.bitField0_ & 262144) != 262144 || this.commonRep_ == Response.getDefaultInstance()) {
                    this.commonRep_ = value;
                } else {
                    this.commonRep_ = Response.newBuilder(this.commonRep_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 262144;
                return this;
            }

            public Builder clearCommonRep() {
                this.commonRep_ = Response.getDefaultInstance();
                this.bitField0_ &= -262145;
                return this;
            }

            public boolean hasReportInfo() {
                return (this.bitField0_ & 524288) == 524288;
            }

            public ReportInformation getReportInfo() {
                return this.reportInfo_;
            }

            public Builder setReportInfo(ReportInformation value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.reportInfo_ = value;
                this.bitField0_ |= 524288;
                return this;
            }

            public Builder setReportInfo(p005cn.jpush.p034im.proto.User.ReportInformation.Builder builderForValue) {
                this.reportInfo_ = builderForValue.build();
                this.bitField0_ |= 524288;
                return this;
            }

            public Builder mergeReportInfo(ReportInformation value) {
                if ((this.bitField0_ & 524288) != 524288 || this.reportInfo_ == ReportInformation.getDefaultInstance()) {
                    this.reportInfo_ = value;
                } else {
                    this.reportInfo_ = ReportInformation.newBuilder(this.reportInfo_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 524288;
                return this;
            }

            public Builder clearReportInfo() {
                this.reportInfo_ = ReportInformation.getDefaultInstance();
                this.bitField0_ &= -524289;
                return this;
            }

            public boolean hasAddMsgNoDisturbSingle() {
                return (this.bitField0_ & 1048576) == 1048576;
            }

            public AddMsgnoDisturbSingle getAddMsgNoDisturbSingle() {
                return this.addMsgNoDisturbSingle_;
            }

            public Builder setAddMsgNoDisturbSingle(AddMsgnoDisturbSingle value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addMsgNoDisturbSingle_ = value;
                this.bitField0_ |= 1048576;
                return this;
            }

            public Builder setAddMsgNoDisturbSingle(p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbSingle.Builder builderForValue) {
                this.addMsgNoDisturbSingle_ = builderForValue.build();
                this.bitField0_ |= 1048576;
                return this;
            }

            public Builder mergeAddMsgNoDisturbSingle(AddMsgnoDisturbSingle value) {
                if ((this.bitField0_ & 1048576) != 1048576 || this.addMsgNoDisturbSingle_ == AddMsgnoDisturbSingle.getDefaultInstance()) {
                    this.addMsgNoDisturbSingle_ = value;
                } else {
                    this.addMsgNoDisturbSingle_ = AddMsgnoDisturbSingle.newBuilder(this.addMsgNoDisturbSingle_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 1048576;
                return this;
            }

            public Builder clearAddMsgNoDisturbSingle() {
                this.addMsgNoDisturbSingle_ = AddMsgnoDisturbSingle.getDefaultInstance();
                this.bitField0_ &= -1048577;
                return this;
            }

            public boolean hasDeleteMsgNoDisturbSingle() {
                return (this.bitField0_ & PKIFailureInfo.badSenderNonce) == 2097152;
            }

            public DeleteMsgnoDisturbSingle getDeleteMsgNoDisturbSingle() {
                return this.deleteMsgNoDisturbSingle_;
            }

            public Builder setDeleteMsgNoDisturbSingle(DeleteMsgnoDisturbSingle value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.deleteMsgNoDisturbSingle_ = value;
                this.bitField0_ |= PKIFailureInfo.badSenderNonce;
                return this;
            }

            public Builder setDeleteMsgNoDisturbSingle(p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbSingle.Builder builderForValue) {
                this.deleteMsgNoDisturbSingle_ = builderForValue.build();
                this.bitField0_ |= PKIFailureInfo.badSenderNonce;
                return this;
            }

            public Builder mergeDeleteMsgNoDisturbSingle(DeleteMsgnoDisturbSingle value) {
                if ((this.bitField0_ & PKIFailureInfo.badSenderNonce) != 2097152 || this.deleteMsgNoDisturbSingle_ == DeleteMsgnoDisturbSingle.getDefaultInstance()) {
                    this.deleteMsgNoDisturbSingle_ = value;
                } else {
                    this.deleteMsgNoDisturbSingle_ = DeleteMsgnoDisturbSingle.newBuilder(this.deleteMsgNoDisturbSingle_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= PKIFailureInfo.badSenderNonce;
                return this;
            }

            public Builder clearDeleteMsgNoDisturbSingle() {
                this.deleteMsgNoDisturbSingle_ = DeleteMsgnoDisturbSingle.getDefaultInstance();
                this.bitField0_ &= -2097153;
                return this;
            }

            public boolean hasAddMsgNoDisturbGroup() {
                return (this.bitField0_ & 4194304) == 4194304;
            }

            public AddMsgnoDisturbGroup getAddMsgNoDisturbGroup() {
                return this.addMsgNoDisturbGroup_;
            }

            public Builder setAddMsgNoDisturbGroup(AddMsgnoDisturbGroup value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addMsgNoDisturbGroup_ = value;
                this.bitField0_ |= 4194304;
                return this;
            }

            public Builder setAddMsgNoDisturbGroup(p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGroup.Builder builderForValue) {
                this.addMsgNoDisturbGroup_ = builderForValue.build();
                this.bitField0_ |= 4194304;
                return this;
            }

            public Builder mergeAddMsgNoDisturbGroup(AddMsgnoDisturbGroup value) {
                if ((this.bitField0_ & 4194304) != 4194304 || this.addMsgNoDisturbGroup_ == AddMsgnoDisturbGroup.getDefaultInstance()) {
                    this.addMsgNoDisturbGroup_ = value;
                } else {
                    this.addMsgNoDisturbGroup_ = AddMsgnoDisturbGroup.newBuilder(this.addMsgNoDisturbGroup_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 4194304;
                return this;
            }

            public Builder clearAddMsgNoDisturbGroup() {
                this.addMsgNoDisturbGroup_ = AddMsgnoDisturbGroup.getDefaultInstance();
                this.bitField0_ &= -4194305;
                return this;
            }

            public boolean hasDeleteMsgNoDisturbGroup() {
                return (this.bitField0_ & 8388608) == 8388608;
            }

            public DeleteMsgnoDisturbGroup getDeleteMsgNoDisturbGroup() {
                return this.deleteMsgNoDisturbGroup_;
            }

            public Builder setDeleteMsgNoDisturbGroup(DeleteMsgnoDisturbGroup value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.deleteMsgNoDisturbGroup_ = value;
                this.bitField0_ |= 8388608;
                return this;
            }

            public Builder setDeleteMsgNoDisturbGroup(p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGroup.Builder builderForValue) {
                this.deleteMsgNoDisturbGroup_ = builderForValue.build();
                this.bitField0_ |= 8388608;
                return this;
            }

            public Builder mergeDeleteMsgNoDisturbGroup(DeleteMsgnoDisturbGroup value) {
                if ((this.bitField0_ & 8388608) != 8388608 || this.deleteMsgNoDisturbGroup_ == DeleteMsgnoDisturbGroup.getDefaultInstance()) {
                    this.deleteMsgNoDisturbGroup_ = value;
                } else {
                    this.deleteMsgNoDisturbGroup_ = DeleteMsgnoDisturbGroup.newBuilder(this.deleteMsgNoDisturbGroup_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 8388608;
                return this;
            }

            public Builder clearDeleteMsgNoDisturbGroup() {
                this.deleteMsgNoDisturbGroup_ = DeleteMsgnoDisturbGroup.getDefaultInstance();
                this.bitField0_ &= -8388609;
                return this;
            }

            public boolean hasAddMsgNoDisturbGlobal() {
                return (this.bitField0_ & 16777216) == 16777216;
            }

            public AddMsgnoDisturbGlobal getAddMsgNoDisturbGlobal() {
                return this.addMsgNoDisturbGlobal_;
            }

            public Builder setAddMsgNoDisturbGlobal(AddMsgnoDisturbGlobal value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.addMsgNoDisturbGlobal_ = value;
                this.bitField0_ |= 16777216;
                return this;
            }

            public Builder setAddMsgNoDisturbGlobal(p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGlobal.Builder builderForValue) {
                this.addMsgNoDisturbGlobal_ = builderForValue.build();
                this.bitField0_ |= 16777216;
                return this;
            }

            public Builder mergeAddMsgNoDisturbGlobal(AddMsgnoDisturbGlobal value) {
                if ((this.bitField0_ & 16777216) != 16777216 || this.addMsgNoDisturbGlobal_ == AddMsgnoDisturbGlobal.getDefaultInstance()) {
                    this.addMsgNoDisturbGlobal_ = value;
                } else {
                    this.addMsgNoDisturbGlobal_ = AddMsgnoDisturbGlobal.newBuilder(this.addMsgNoDisturbGlobal_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 16777216;
                return this;
            }

            public Builder clearAddMsgNoDisturbGlobal() {
                this.addMsgNoDisturbGlobal_ = AddMsgnoDisturbGlobal.getDefaultInstance();
                this.bitField0_ &= -16777217;
                return this;
            }

            public boolean hasDeleteMsgNoDisturbGlobal() {
                return (this.bitField0_ & 33554432) == 33554432;
            }

            public DeleteMsgnoDisturbGlobal getDeleteMsgNoDisturbGlobal() {
                return this.deleteMsgNoDisturbGlobal_;
            }

            public Builder setDeleteMsgNoDisturbGlobal(DeleteMsgnoDisturbGlobal value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.deleteMsgNoDisturbGlobal_ = value;
                this.bitField0_ |= 33554432;
                return this;
            }

            public Builder setDeleteMsgNoDisturbGlobal(p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGlobal.Builder builderForValue) {
                this.deleteMsgNoDisturbGlobal_ = builderForValue.build();
                this.bitField0_ |= 33554432;
                return this;
            }

            public Builder mergeDeleteMsgNoDisturbGlobal(DeleteMsgnoDisturbGlobal value) {
                if ((this.bitField0_ & 33554432) != 33554432 || this.deleteMsgNoDisturbGlobal_ == DeleteMsgnoDisturbGlobal.getDefaultInstance()) {
                    this.deleteMsgNoDisturbGlobal_ = value;
                } else {
                    this.deleteMsgNoDisturbGlobal_ = DeleteMsgnoDisturbGlobal.newBuilder(this.deleteMsgNoDisturbGlobal_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 33554432;
                return this;
            }

            public Builder clearDeleteMsgNoDisturbGlobal() {
                this.deleteMsgNoDisturbGlobal_ = DeleteMsgnoDisturbGlobal.getDefaultInstance();
                this.bitField0_ &= -33554433;
                return this;
            }
        }

        private ProtocolBody(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ProtocolBody(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ProtocolBody getDefaultInstance() {
            return defaultInstance;
        }

        public ProtocolBody getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasLogin() {
            return (this.bitField0_ & 1) == 1;
        }

        public Login getLogin() {
            return this.login_;
        }

        public boolean hasLogout() {
            return (this.bitField0_ & 2) == 2;
        }

        public Logout getLogout() {
            return this.logout_;
        }

        public boolean hasSingleMsg() {
            return (this.bitField0_ & 4) == 4;
        }

        public SingleMsg getSingleMsg() {
            return this.singleMsg_;
        }

        public boolean hasGroupMsg() {
            return (this.bitField0_ & 8) == 8;
        }

        public GroupMsg getGroupMsg() {
            return this.groupMsg_;
        }

        public boolean hasAddFriend() {
            return (this.bitField0_ & 16) == 16;
        }

        public AddFriend getAddFriend() {
            return this.addFriend_;
        }

        public boolean hasDelFriend() {
            return (this.bitField0_ & 32) == 32;
        }

        public DelFriend getDelFriend() {
            return this.delFriend_;
        }

        public boolean hasUpdateMemo() {
            return (this.bitField0_ & 64) == 64;
        }

        public UpdateMemo getUpdateMemo() {
            return this.updateMemo_;
        }

        public boolean hasCreateGroup() {
            return (this.bitField0_ & 128) == 128;
        }

        public CreateGroup getCreateGroup() {
            return this.createGroup_;
        }

        public boolean hasExitGroup() {
            return (this.bitField0_ & 256) == 256;
        }

        public ExitGroup getExitGroup() {
            return this.exitGroup_;
        }

        public boolean hasAddGroupMember() {
            return (this.bitField0_ & 512) == 512;
        }

        public AddGroupMember getAddGroupMember() {
            return this.addGroupMember_;
        }

        public boolean hasDelGroupMember() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public DelGroupMember getDelGroupMember() {
            return this.delGroupMember_;
        }

        public boolean hasUpdateGroupInfo() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public UpdateGroupInfo getUpdateGroupInfo() {
            return this.updateGroupInfo_;
        }

        public boolean hasEventNotification() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public EventNotification getEventNotification() {
            return this.eventNotification_;
        }

        public boolean hasChatMsg() {
            return (this.bitField0_ & 8192) == 8192;
        }

        public ChatMsgSync getChatMsg() {
            return this.chatMsg_;
        }

        public boolean hasEventSync() {
            return (this.bitField0_ & 16384) == 16384;
        }

        public EventSync getEventSync() {
            return this.eventSync_;
        }

        public boolean hasEventAnswer() {
            return (this.bitField0_ & 32768) == 32768;
        }

        public EventAnswer getEventAnswer() {
            return this.eventAnswer_;
        }

        public boolean hasAddBlacklist() {
            return (this.bitField0_ & 65536) == 65536;
        }

        public AddBlackList getAddBlacklist() {
            return this.addBlacklist_;
        }

        public boolean hasDelBlacklist() {
            return (this.bitField0_ & 131072) == 131072;
        }

        public DelBlackList getDelBlacklist() {
            return this.delBlacklist_;
        }

        public boolean hasCommonRep() {
            return (this.bitField0_ & 262144) == 262144;
        }

        public Response getCommonRep() {
            return this.commonRep_;
        }

        public boolean hasReportInfo() {
            return (this.bitField0_ & 524288) == 524288;
        }

        public ReportInformation getReportInfo() {
            return this.reportInfo_;
        }

        public boolean hasAddMsgNoDisturbSingle() {
            return (this.bitField0_ & 1048576) == 1048576;
        }

        public AddMsgnoDisturbSingle getAddMsgNoDisturbSingle() {
            return this.addMsgNoDisturbSingle_;
        }

        public boolean hasDeleteMsgNoDisturbSingle() {
            return (this.bitField0_ & PKIFailureInfo.badSenderNonce) == 2097152;
        }

        public DeleteMsgnoDisturbSingle getDeleteMsgNoDisturbSingle() {
            return this.deleteMsgNoDisturbSingle_;
        }

        public boolean hasAddMsgNoDisturbGroup() {
            return (this.bitField0_ & 4194304) == 4194304;
        }

        public AddMsgnoDisturbGroup getAddMsgNoDisturbGroup() {
            return this.addMsgNoDisturbGroup_;
        }

        public boolean hasDeleteMsgNoDisturbGroup() {
            return (this.bitField0_ & 8388608) == 8388608;
        }

        public DeleteMsgnoDisturbGroup getDeleteMsgNoDisturbGroup() {
            return this.deleteMsgNoDisturbGroup_;
        }

        public boolean hasAddMsgNoDisturbGlobal() {
            return (this.bitField0_ & 16777216) == 16777216;
        }

        public AddMsgnoDisturbGlobal getAddMsgNoDisturbGlobal() {
            return this.addMsgNoDisturbGlobal_;
        }

        public boolean hasDeleteMsgNoDisturbGlobal() {
            return (this.bitField0_ & 33554432) == 33554432;
        }

        public DeleteMsgnoDisturbGlobal getDeleteMsgNoDisturbGlobal() {
            return this.deleteMsgNoDisturbGlobal_;
        }

        private void initFields() {
            this.login_ = Login.getDefaultInstance();
            this.logout_ = Logout.getDefaultInstance();
            this.singleMsg_ = SingleMsg.getDefaultInstance();
            this.groupMsg_ = GroupMsg.getDefaultInstance();
            this.addFriend_ = AddFriend.getDefaultInstance();
            this.delFriend_ = DelFriend.getDefaultInstance();
            this.updateMemo_ = UpdateMemo.getDefaultInstance();
            this.createGroup_ = CreateGroup.getDefaultInstance();
            this.exitGroup_ = ExitGroup.getDefaultInstance();
            this.addGroupMember_ = AddGroupMember.getDefaultInstance();
            this.delGroupMember_ = DelGroupMember.getDefaultInstance();
            this.updateGroupInfo_ = UpdateGroupInfo.getDefaultInstance();
            this.eventNotification_ = EventNotification.getDefaultInstance();
            this.chatMsg_ = ChatMsgSync.getDefaultInstance();
            this.eventSync_ = EventSync.getDefaultInstance();
            this.eventAnswer_ = EventAnswer.getDefaultInstance();
            this.addBlacklist_ = AddBlackList.getDefaultInstance();
            this.delBlacklist_ = DelBlackList.getDefaultInstance();
            this.commonRep_ = Response.getDefaultInstance();
            this.reportInfo_ = ReportInformation.getDefaultInstance();
            this.addMsgNoDisturbSingle_ = AddMsgnoDisturbSingle.getDefaultInstance();
            this.deleteMsgNoDisturbSingle_ = DeleteMsgnoDisturbSingle.getDefaultInstance();
            this.addMsgNoDisturbGroup_ = AddMsgnoDisturbGroup.getDefaultInstance();
            this.deleteMsgNoDisturbGroup_ = DeleteMsgnoDisturbGroup.getDefaultInstance();
            this.addMsgNoDisturbGlobal_ = AddMsgnoDisturbGlobal.getDefaultInstance();
            this.deleteMsgNoDisturbGlobal_ = DeleteMsgnoDisturbGlobal.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == -1) {
                this.memoizedIsInitialized = 1;
                return true;
            } else if (isInitialized == 1) {
                return true;
            } else {
                return false;
            }
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.login_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.logout_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.singleMsg_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.groupMsg_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, this.addFriend_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(6, this.delFriend_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(7, this.updateMemo_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(8, this.createGroup_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeMessage(9, this.exitGroup_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeMessage(10, this.addGroupMember_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeMessage(11, this.delGroupMember_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeMessage(12, this.updateGroupInfo_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeMessage(13, this.eventNotification_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeMessage(14, this.chatMsg_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeMessage(15, this.eventSync_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeMessage(16, this.eventAnswer_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeMessage(18, this.addBlacklist_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeMessage(19, this.delBlacklist_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                output.writeMessage(20, this.commonRep_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                output.writeMessage(23, this.reportInfo_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                output.writeMessage(31, this.addMsgNoDisturbSingle_);
            }
            if ((this.bitField0_ & PKIFailureInfo.badSenderNonce) == 2097152) {
                output.writeMessage(32, this.deleteMsgNoDisturbSingle_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                output.writeMessage(33, this.addMsgNoDisturbGroup_);
            }
            if ((this.bitField0_ & 8388608) == 8388608) {
                output.writeMessage(34, this.deleteMsgNoDisturbGroup_);
            }
            if ((this.bitField0_ & 16777216) == 16777216) {
                output.writeMessage(35, this.addMsgNoDisturbGlobal_);
            }
            if ((this.bitField0_ & 33554432) == 33554432) {
                output.writeMessage(36, this.deleteMsgNoDisturbGlobal_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeMessageSize(1, this.login_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, this.logout_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, this.singleMsg_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, this.groupMsg_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, this.addFriend_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(6, this.delFriend_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeMessageSize(7, this.updateMemo_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeMessageSize(8, this.createGroup_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeMessageSize(9, this.exitGroup_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeMessageSize(10, this.addGroupMember_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeMessageSize(11, this.delGroupMember_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeMessageSize(12, this.updateGroupInfo_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeMessageSize(13, this.eventNotification_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeMessageSize(14, this.chatMsg_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeMessageSize(15, this.eventSync_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeMessageSize(16, this.eventAnswer_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeMessageSize(18, this.addBlacklist_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeMessageSize(19, this.delBlacklist_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                size2 += CodedOutputStream.computeMessageSize(20, this.commonRep_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                size2 += CodedOutputStream.computeMessageSize(23, this.reportInfo_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                size2 += CodedOutputStream.computeMessageSize(31, this.addMsgNoDisturbSingle_);
            }
            if ((this.bitField0_ & PKIFailureInfo.badSenderNonce) == 2097152) {
                size2 += CodedOutputStream.computeMessageSize(32, this.deleteMsgNoDisturbSingle_);
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                size2 += CodedOutputStream.computeMessageSize(33, this.addMsgNoDisturbGroup_);
            }
            if ((this.bitField0_ & 8388608) == 8388608) {
                size2 += CodedOutputStream.computeMessageSize(34, this.deleteMsgNoDisturbGroup_);
            }
            if ((this.bitField0_ & 16777216) == 16777216) {
                size2 += CodedOutputStream.computeMessageSize(35, this.addMsgNoDisturbGlobal_);
            }
            if ((this.bitField0_ & 33554432) == 33554432) {
                size2 += CodedOutputStream.computeMessageSize(36, this.deleteMsgNoDisturbGlobal_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ProtocolBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ProtocolBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ProtocolBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ProtocolBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ProtocolBody parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ProtocolBody parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ProtocolBody parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ProtocolBody parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ProtocolBody parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ProtocolBody parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ProtocolBody prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Im$ProtocolBodyOrBuilder */
    public interface ProtocolBodyOrBuilder extends MessageLiteOrBuilder {
        AddBlackList getAddBlacklist();

        AddFriend getAddFriend();

        AddGroupMember getAddGroupMember();

        AddMsgnoDisturbGlobal getAddMsgNoDisturbGlobal();

        AddMsgnoDisturbGroup getAddMsgNoDisturbGroup();

        AddMsgnoDisturbSingle getAddMsgNoDisturbSingle();

        ChatMsgSync getChatMsg();

        Response getCommonRep();

        CreateGroup getCreateGroup();

        DelBlackList getDelBlacklist();

        DelFriend getDelFriend();

        DelGroupMember getDelGroupMember();

        DeleteMsgnoDisturbGlobal getDeleteMsgNoDisturbGlobal();

        DeleteMsgnoDisturbGroup getDeleteMsgNoDisturbGroup();

        DeleteMsgnoDisturbSingle getDeleteMsgNoDisturbSingle();

        EventAnswer getEventAnswer();

        EventNotification getEventNotification();

        EventSync getEventSync();

        ExitGroup getExitGroup();

        GroupMsg getGroupMsg();

        Login getLogin();

        Logout getLogout();

        ReportInformation getReportInfo();

        SingleMsg getSingleMsg();

        UpdateGroupInfo getUpdateGroupInfo();

        UpdateMemo getUpdateMemo();

        boolean hasAddBlacklist();

        boolean hasAddFriend();

        boolean hasAddGroupMember();

        boolean hasAddMsgNoDisturbGlobal();

        boolean hasAddMsgNoDisturbGroup();

        boolean hasAddMsgNoDisturbSingle();

        boolean hasChatMsg();

        boolean hasCommonRep();

        boolean hasCreateGroup();

        boolean hasDelBlacklist();

        boolean hasDelFriend();

        boolean hasDelGroupMember();

        boolean hasDeleteMsgNoDisturbGlobal();

        boolean hasDeleteMsgNoDisturbGroup();

        boolean hasDeleteMsgNoDisturbSingle();

        boolean hasEventAnswer();

        boolean hasEventNotification();

        boolean hasEventSync();

        boolean hasExitGroup();

        boolean hasGroupMsg();

        boolean hasLogin();

        boolean hasLogout();

        boolean hasReportInfo();

        boolean hasSingleMsg();

        boolean hasUpdateGroupInfo();

        boolean hasUpdateMemo();
    }

    /* renamed from: cn.jpush.im.proto.Im$ProtocolHead */
    public static final class ProtocolHead extends GeneratedMessageLite implements ProtocolHeadOrBuilder {
        public static final int APPKEY_FIELD_NUMBER = 4;
        public static final int CMD_FIELD_NUMBER = 1;
        public static final int COOKIE_FIELD_NUMBER = 5;
        public static final int PLATFORM_FIELD_NUMBER = 6;
        public static final int UID_FIELD_NUMBER = 3;
        public static final int VER_FIELD_NUMBER = 2;
        private static final ProtocolHead defaultInstance = new ProtocolHead(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public ByteString appkey_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int cmd_;
        /* access modifiers changed from: private */
        public Cookie cookie_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int platform_;
        /* access modifiers changed from: private */
        public long uid_;
        /* access modifiers changed from: private */
        public int ver_;

        /* renamed from: cn.jpush.im.proto.Im$ProtocolHead$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ProtocolHead, Builder> implements ProtocolHeadOrBuilder {
            private ByteString appkey_ = ByteString.EMPTY;
            private int bitField0_;
            private int cmd_;
            private Cookie cookie_ = Cookie.getDefaultInstance();
            private int platform_;
            private long uid_;
            private int ver_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                this.cmd_ = 0;
                this.bitField0_ &= -2;
                this.ver_ = 0;
                this.bitField0_ &= -3;
                this.uid_ = 0;
                this.bitField0_ &= -5;
                this.appkey_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.cookie_ = Cookie.getDefaultInstance();
                this.bitField0_ &= -17;
                this.platform_ = 0;
                this.bitField0_ &= -33;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ProtocolHead getDefaultInstanceForType() {
                return ProtocolHead.getDefaultInstance();
            }

            public ProtocolHead build() {
                ProtocolHead result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ProtocolHead buildParsed() throws InvalidProtocolBufferException {
                ProtocolHead result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ProtocolHead buildPartial() {
                ProtocolHead result = new ProtocolHead(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.cmd_ = this.cmd_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.ver_ = this.ver_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.uid_ = this.uid_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.appkey_ = this.appkey_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.cookie_ = this.cookie_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.platform_ = this.platform_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ProtocolHead other) {
                if (other != ProtocolHead.getDefaultInstance()) {
                    if (other.hasCmd()) {
                        setCmd(other.getCmd());
                    }
                    if (other.hasVer()) {
                        setVer(other.getVer());
                    }
                    if (other.hasUid()) {
                        setUid(other.getUid());
                    }
                    if (other.hasAppkey()) {
                        setAppkey(other.getAppkey());
                    }
                    if (other.hasCookie()) {
                        mergeCookie(other.getCookie());
                    }
                    if (other.hasPlatform()) {
                        setPlatform(other.getPlatform());
                    }
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.cmd_ = input.readInt32();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.ver_ = input.readInt32();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.uid_ = input.readInt64();
                            continue;
                        case 34:
                            this.bitField0_ |= 8;
                            this.appkey_ = input.readBytes();
                            continue;
                        case 42:
                            Builder subBuilder = Cookie.newBuilder();
                            if (hasCookie()) {
                                subBuilder.mergeFrom(getCookie());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setCookie(subBuilder.buildPartial());
                            continue;
                        case 48:
                            this.bitField0_ |= 32;
                            this.platform_ = input.readInt32();
                            continue;
                        default:
                            if (!parseUnknownField(input, extensionRegistry, tag)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }

            public boolean hasCmd() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getCmd() {
                return this.cmd_;
            }

            public Builder setCmd(int value) {
                this.bitField0_ |= 1;
                this.cmd_ = value;
                return this;
            }

            public Builder clearCmd() {
                this.bitField0_ &= -2;
                this.cmd_ = 0;
                return this;
            }

            public boolean hasVer() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getVer() {
                return this.ver_;
            }

            public Builder setVer(int value) {
                this.bitField0_ |= 2;
                this.ver_ = value;
                return this;
            }

            public Builder clearVer() {
                this.bitField0_ &= -3;
                this.ver_ = 0;
                return this;
            }

            public boolean hasUid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getUid() {
                return this.uid_;
            }

            public Builder setUid(long value) {
                this.bitField0_ |= 4;
                this.uid_ = value;
                return this;
            }

            public Builder clearUid() {
                this.bitField0_ &= -5;
                this.uid_ = 0;
                return this;
            }

            public boolean hasAppkey() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getAppkey() {
                return this.appkey_;
            }

            public Builder setAppkey(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.appkey_ = value;
                return this;
            }

            public Builder clearAppkey() {
                this.bitField0_ &= -9;
                this.appkey_ = ProtocolHead.getDefaultInstance().getAppkey();
                return this;
            }

            public boolean hasCookie() {
                return (this.bitField0_ & 16) == 16;
            }

            public Cookie getCookie() {
                return this.cookie_;
            }

            public Builder setCookie(Cookie value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.cookie_ = value;
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setCookie(Builder builderForValue) {
                this.cookie_ = builderForValue.build();
                this.bitField0_ |= 16;
                return this;
            }

            public Builder mergeCookie(Cookie value) {
                if ((this.bitField0_ & 16) != 16 || this.cookie_ == Cookie.getDefaultInstance()) {
                    this.cookie_ = value;
                } else {
                    this.cookie_ = Cookie.newBuilder(this.cookie_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder clearCookie() {
                this.cookie_ = Cookie.getDefaultInstance();
                this.bitField0_ &= -17;
                return this;
            }

            public boolean hasPlatform() {
                return (this.bitField0_ & 32) == 32;
            }

            public int getPlatform() {
                return this.platform_;
            }

            public Builder setPlatform(int value) {
                this.bitField0_ |= 32;
                this.platform_ = value;
                return this;
            }

            public Builder clearPlatform() {
                this.bitField0_ &= -33;
                this.platform_ = 0;
                return this;
            }
        }

        private ProtocolHead(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ProtocolHead(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ProtocolHead getDefaultInstance() {
            return defaultInstance;
        }

        public ProtocolHead getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasCmd() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getCmd() {
            return this.cmd_;
        }

        public boolean hasVer() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getVer() {
            return this.ver_;
        }

        public boolean hasUid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getUid() {
            return this.uid_;
        }

        public boolean hasAppkey() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getAppkey() {
            return this.appkey_;
        }

        public boolean hasCookie() {
            return (this.bitField0_ & 16) == 16;
        }

        public Cookie getCookie() {
            return this.cookie_;
        }

        public boolean hasPlatform() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getPlatform() {
            return this.platform_;
        }

        private void initFields() {
            this.cmd_ = 0;
            this.ver_ = 0;
            this.uid_ = 0;
            this.appkey_ = ByteString.EMPTY;
            this.cookie_ = Cookie.getDefaultInstance();
            this.platform_ = 0;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == -1) {
                this.memoizedIsInitialized = 1;
                return true;
            } else if (isInitialized == 1) {
                return true;
            } else {
                return false;
            }
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.cmd_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.ver_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.uid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.appkey_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, this.cookie_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.platform_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.cmd_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.ver_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.uid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, this.appkey_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, this.cookie_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.platform_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ProtocolHead parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ProtocolHead parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ProtocolHead parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ProtocolHead parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ProtocolHead parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ProtocolHead parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ProtocolHead parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ProtocolHead parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ProtocolHead parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ProtocolHead parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ProtocolHead prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Im$ProtocolHeadOrBuilder */
    public interface ProtocolHeadOrBuilder extends MessageLiteOrBuilder {
        ByteString getAppkey();

        int getCmd();

        Cookie getCookie();

        int getPlatform();

        long getUid();

        int getVer();

        boolean hasAppkey();

        boolean hasCmd();

        boolean hasCookie();

        boolean hasPlatform();

        boolean hasUid();

        boolean hasVer();
    }

    /* renamed from: cn.jpush.im.proto.Im$Response */
    public static final class Response extends GeneratedMessageLite implements ResponseOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        private static final Response defaultInstance = new Response(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int code_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString message_;

        /* renamed from: cn.jpush.im.proto.Im$Response$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<Response, Builder> implements ResponseOrBuilder {
            private int bitField0_;
            private int code_;
            private ByteString message_ = ByteString.EMPTY;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                this.code_ = 0;
                this.bitField0_ &= -2;
                this.message_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Response getDefaultInstanceForType() {
                return Response.getDefaultInstance();
            }

            public Response build() {
                Response result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public Response buildParsed() throws InvalidProtocolBufferException {
                Response result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public Response buildPartial() {
                Response result = new Response(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.code_ = this.code_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.message_ = this.message_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Response other) {
                if (other != Response.getDefaultInstance()) {
                    if (other.hasCode()) {
                        setCode(other.getCode());
                    }
                    if (other.hasMessage()) {
                        setMessage(other.getMessage());
                    }
                }
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.code_ = input.readInt32();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.message_ = input.readBytes();
                            continue;
                        default:
                            if (!parseUnknownField(input, extensionRegistry, tag)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }

            public boolean hasCode() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getCode() {
                return this.code_;
            }

            public Builder setCode(int value) {
                this.bitField0_ |= 1;
                this.code_ = value;
                return this;
            }

            public Builder clearCode() {
                this.bitField0_ &= -2;
                this.code_ = 0;
                return this;
            }

            public boolean hasMessage() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getMessage() {
                return this.message_;
            }

            public Builder setMessage(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.message_ = value;
                return this;
            }

            public Builder clearMessage() {
                this.bitField0_ &= -3;
                this.message_ = Response.getDefaultInstance().getMessage();
                return this;
            }
        }

        private Response(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private Response(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static Response getDefaultInstance() {
            return defaultInstance;
        }

        public Response getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasCode() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getCode() {
            return this.code_;
        }

        public boolean hasMessage() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getMessage() {
            return this.message_;
        }

        private void initFields() {
            this.code_ = 0;
            this.message_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == -1) {
                this.memoizedIsInitialized = 1;
                return true;
            } else if (isInitialized == 1) {
                return true;
            } else {
                return false;
            }
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.code_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.message_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.code_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.message_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Response parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Response parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Response parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Response parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Response parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Response parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static Response parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Response parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Response parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Response parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Response prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Im$ResponseOrBuilder */
    public interface ResponseOrBuilder extends MessageLiteOrBuilder {
        int getCode();

        ByteString getMessage();

        boolean hasCode();

        boolean hasMessage();
    }

    private C1621Im() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }
}
