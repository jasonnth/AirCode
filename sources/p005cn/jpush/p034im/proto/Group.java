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

/* renamed from: cn.jpush.im.proto.Group */
public final class Group {

    /* renamed from: cn.jpush.im.proto.Group$AddGroupMember */
    public static final class AddGroupMember extends GeneratedMessageLite implements AddGroupMemberOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        public static final int MEMBER_COUNT_FIELD_NUMBER = 2;
        public static final int MEMBER_UIDLIST_FIELD_NUMBER = 3;
        private static final AddGroupMember defaultInstance = new AddGroupMember(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        /* access modifiers changed from: private */
        public int memberCount_;
        /* access modifiers changed from: private */
        public List<Long> memberUidlist_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Group$AddGroupMember$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddGroupMember, Builder> implements AddGroupMemberOrBuilder {
            private int bitField0_;
            private long gid_;
            private int memberCount_;
            private List<Long> memberUidlist_ = Collections.emptyList();

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
                this.gid_ = 0;
                this.bitField0_ &= -2;
                this.memberCount_ = 0;
                this.bitField0_ &= -3;
                this.memberUidlist_ = Collections.emptyList();
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddGroupMember getDefaultInstanceForType() {
                return AddGroupMember.getDefaultInstance();
            }

            public AddGroupMember build() {
                AddGroupMember result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddGroupMember buildParsed() throws InvalidProtocolBufferException {
                AddGroupMember result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddGroupMember buildPartial() {
                AddGroupMember result = new AddGroupMember(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.memberCount_ = this.memberCount_;
                if ((this.bitField0_ & 4) == 4) {
                    this.memberUidlist_ = Collections.unmodifiableList(this.memberUidlist_);
                    this.bitField0_ &= -5;
                }
                result.memberUidlist_ = this.memberUidlist_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(AddGroupMember other) {
                if (other != AddGroupMember.getDefaultInstance()) {
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasMemberCount()) {
                        setMemberCount(other.getMemberCount());
                    }
                    if (!other.memberUidlist_.isEmpty()) {
                        if (this.memberUidlist_.isEmpty()) {
                            this.memberUidlist_ = other.memberUidlist_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureMemberUidlistIsMutable();
                            this.memberUidlist_.addAll(other.memberUidlist_);
                        }
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
                            this.gid_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.memberCount_ = input.readInt32();
                            continue;
                        case 24:
                            ensureMemberUidlistIsMutable();
                            this.memberUidlist_.add(Long.valueOf(input.readInt64()));
                            continue;
                        case 26:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addMemberUidlist(input.readInt64());
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

            public boolean hasGid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 1;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -2;
                this.gid_ = 0;
                return this;
            }

            public boolean hasMemberCount() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getMemberCount() {
                return this.memberCount_;
            }

            public Builder setMemberCount(int value) {
                this.bitField0_ |= 2;
                this.memberCount_ = value;
                return this;
            }

            public Builder clearMemberCount() {
                this.bitField0_ &= -3;
                this.memberCount_ = 0;
                return this;
            }

            private void ensureMemberUidlistIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.memberUidlist_ = new ArrayList(this.memberUidlist_);
                    this.bitField0_ |= 4;
                }
            }

            public List<Long> getMemberUidlistList() {
                return Collections.unmodifiableList(this.memberUidlist_);
            }

            public int getMemberUidlistCount() {
                return this.memberUidlist_.size();
            }

            public long getMemberUidlist(int index) {
                return ((Long) this.memberUidlist_.get(index)).longValue();
            }

            public Builder setMemberUidlist(int index, long value) {
                ensureMemberUidlistIsMutable();
                this.memberUidlist_.set(index, Long.valueOf(value));
                return this;
            }

            public Builder addMemberUidlist(long value) {
                ensureMemberUidlistIsMutable();
                this.memberUidlist_.add(Long.valueOf(value));
                return this;
            }

            public Builder addAllMemberUidlist(Iterable<? extends Long> values) {
                ensureMemberUidlistIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.memberUidlist_);
                return this;
            }

            public Builder clearMemberUidlist() {
                this.memberUidlist_ = Collections.emptyList();
                this.bitField0_ &= -5;
                return this;
            }
        }

        private AddGroupMember(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddGroupMember(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddGroupMember getDefaultInstance() {
            return defaultInstance;
        }

        public AddGroupMember getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasMemberCount() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getMemberCount() {
            return this.memberCount_;
        }

        public List<Long> getMemberUidlistList() {
            return this.memberUidlist_;
        }

        public int getMemberUidlistCount() {
            return this.memberUidlist_.size();
        }

        public long getMemberUidlist(int index) {
            return ((Long) this.memberUidlist_.get(index)).longValue();
        }

        private void initFields() {
            this.gid_ = 0;
            this.memberCount_ = 0;
            this.memberUidlist_ = Collections.emptyList();
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
                output.writeInt64(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.memberCount_);
            }
            for (int i = 0; i < this.memberUidlist_.size(); i++) {
                output.writeInt64(3, ((Long) this.memberUidlist_.get(i)).longValue());
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.memberCount_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.memberUidlist_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(((Long) this.memberUidlist_.get(i)).longValue());
            }
            int size3 = size2 + dataSize + (getMemberUidlistList().size() * 1);
            this.memoizedSerializedSize = size3;
            return size3;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddGroupMember parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddGroupMember parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddGroupMember parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddGroupMember parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddGroupMember parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddGroupMember parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddGroupMember parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddGroupMember parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddGroupMember parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddGroupMember parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddGroupMember prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Group$AddGroupMemberOrBuilder */
    public interface AddGroupMemberOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        int getMemberCount();

        long getMemberUidlist(int i);

        int getMemberUidlistCount();

        List<Long> getMemberUidlistList();

        boolean hasGid();

        boolean hasMemberCount();
    }

    /* renamed from: cn.jpush.im.proto.Group$CreateGroup */
    public static final class CreateGroup extends GeneratedMessageLite implements CreateGroupOrBuilder {
        public static final int FLAG_FIELD_NUMBER = 4;
        public static final int GID_FIELD_NUMBER = 5;
        public static final int GROUP_DESC_FIELD_NUMBER = 2;
        public static final int GROUP_LEVEL_FIELD_NUMBER = 3;
        public static final int GROUP_NAME_FIELD_NUMBER = 1;
        private static final CreateGroup defaultInstance = new CreateGroup(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int flag_;
        /* access modifiers changed from: private */
        public long gid_;
        /* access modifiers changed from: private */
        public ByteString groupDesc_;
        /* access modifiers changed from: private */
        public int groupLevel_;
        /* access modifiers changed from: private */
        public ByteString groupName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Group$CreateGroup$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<CreateGroup, Builder> implements CreateGroupOrBuilder {
            private int bitField0_;
            private int flag_;
            private long gid_;
            private ByteString groupDesc_ = ByteString.EMPTY;
            private int groupLevel_;
            private ByteString groupName_ = ByteString.EMPTY;

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
                this.groupName_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.groupDesc_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.groupLevel_ = 0;
                this.bitField0_ &= -5;
                this.flag_ = 0;
                this.bitField0_ &= -9;
                this.gid_ = 0;
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public CreateGroup getDefaultInstanceForType() {
                return CreateGroup.getDefaultInstance();
            }

            public CreateGroup build() {
                CreateGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public CreateGroup buildParsed() throws InvalidProtocolBufferException {
                CreateGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public CreateGroup buildPartial() {
                CreateGroup result = new CreateGroup(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.groupName_ = this.groupName_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.groupDesc_ = this.groupDesc_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.groupLevel_ = this.groupLevel_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.flag_ = this.flag_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.gid_ = this.gid_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(CreateGroup other) {
                if (other != CreateGroup.getDefaultInstance()) {
                    if (other.hasGroupName()) {
                        setGroupName(other.getGroupName());
                    }
                    if (other.hasGroupDesc()) {
                        setGroupDesc(other.getGroupDesc());
                    }
                    if (other.hasGroupLevel()) {
                        setGroupLevel(other.getGroupLevel());
                    }
                    if (other.hasFlag()) {
                        setFlag(other.getFlag());
                    }
                    if (other.hasGid()) {
                        setGid(other.getGid());
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
                            this.bitField0_ |= 1;
                            this.groupName_ = input.readBytes();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.groupDesc_ = input.readBytes();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.groupLevel_ = input.readInt32();
                            continue;
                        case 32:
                            this.bitField0_ |= 8;
                            this.flag_ = input.readInt32();
                            continue;
                        case 40:
                            this.bitField0_ |= 16;
                            this.gid_ = input.readInt64();
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

            public boolean hasGroupName() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getGroupName() {
                return this.groupName_;
            }

            public Builder setGroupName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.groupName_ = value;
                return this;
            }

            public Builder clearGroupName() {
                this.bitField0_ &= -2;
                this.groupName_ = CreateGroup.getDefaultInstance().getGroupName();
                return this;
            }

            public boolean hasGroupDesc() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getGroupDesc() {
                return this.groupDesc_;
            }

            public Builder setGroupDesc(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.groupDesc_ = value;
                return this;
            }

            public Builder clearGroupDesc() {
                this.bitField0_ &= -3;
                this.groupDesc_ = CreateGroup.getDefaultInstance().getGroupDesc();
                return this;
            }

            public boolean hasGroupLevel() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getGroupLevel() {
                return this.groupLevel_;
            }

            public Builder setGroupLevel(int value) {
                this.bitField0_ |= 4;
                this.groupLevel_ = value;
                return this;
            }

            public Builder clearGroupLevel() {
                this.bitField0_ &= -5;
                this.groupLevel_ = 0;
                return this;
            }

            public boolean hasFlag() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getFlag() {
                return this.flag_;
            }

            public Builder setFlag(int value) {
                this.bitField0_ |= 8;
                this.flag_ = value;
                return this;
            }

            public Builder clearFlag() {
                this.bitField0_ &= -9;
                this.flag_ = 0;
                return this;
            }

            public boolean hasGid() {
                return (this.bitField0_ & 16) == 16;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 16;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -17;
                this.gid_ = 0;
                return this;
            }
        }

        private CreateGroup(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private CreateGroup(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static CreateGroup getDefaultInstance() {
            return defaultInstance;
        }

        public CreateGroup getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGroupName() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getGroupName() {
            return this.groupName_;
        }

        public boolean hasGroupDesc() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getGroupDesc() {
            return this.groupDesc_;
        }

        public boolean hasGroupLevel() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getGroupLevel() {
            return this.groupLevel_;
        }

        public boolean hasFlag() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getFlag() {
            return this.flag_;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 16) == 16;
        }

        public long getGid() {
            return this.gid_;
        }

        private void initFields() {
            this.groupName_ = ByteString.EMPTY;
            this.groupDesc_ = ByteString.EMPTY;
            this.groupLevel_ = 0;
            this.flag_ = 0;
            this.gid_ = 0;
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
                output.writeBytes(1, this.groupName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.groupDesc_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.groupLevel_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.flag_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.gid_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.groupName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.groupDesc_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.groupLevel_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.flag_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.gid_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static CreateGroup parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static CreateGroup parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static CreateGroup parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static CreateGroup parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static CreateGroup parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static CreateGroup parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static CreateGroup parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static CreateGroup parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static CreateGroup parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static CreateGroup parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(CreateGroup prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Group$CreateGroupOrBuilder */
    public interface CreateGroupOrBuilder extends MessageLiteOrBuilder {
        int getFlag();

        long getGid();

        ByteString getGroupDesc();

        int getGroupLevel();

        ByteString getGroupName();

        boolean hasFlag();

        boolean hasGid();

        boolean hasGroupDesc();

        boolean hasGroupLevel();

        boolean hasGroupName();
    }

    /* renamed from: cn.jpush.im.proto.Group$DelGroupMember */
    public static final class DelGroupMember extends GeneratedMessageLite implements DelGroupMemberOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        public static final int MEMBER_COUNT_FIELD_NUMBER = 2;
        public static final int MEMBER_UIDLIST_FIELD_NUMBER = 3;
        private static final DelGroupMember defaultInstance = new DelGroupMember(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        /* access modifiers changed from: private */
        public int memberCount_;
        /* access modifiers changed from: private */
        public List<Long> memberUidlist_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Group$DelGroupMember$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DelGroupMember, Builder> implements DelGroupMemberOrBuilder {
            private int bitField0_;
            private long gid_;
            private int memberCount_;
            private List<Long> memberUidlist_ = Collections.emptyList();

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
                this.gid_ = 0;
                this.bitField0_ &= -2;
                this.memberCount_ = 0;
                this.bitField0_ &= -3;
                this.memberUidlist_ = Collections.emptyList();
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DelGroupMember getDefaultInstanceForType() {
                return DelGroupMember.getDefaultInstance();
            }

            public DelGroupMember build() {
                DelGroupMember result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DelGroupMember buildParsed() throws InvalidProtocolBufferException {
                DelGroupMember result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DelGroupMember buildPartial() {
                DelGroupMember result = new DelGroupMember(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.memberCount_ = this.memberCount_;
                if ((this.bitField0_ & 4) == 4) {
                    this.memberUidlist_ = Collections.unmodifiableList(this.memberUidlist_);
                    this.bitField0_ &= -5;
                }
                result.memberUidlist_ = this.memberUidlist_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(DelGroupMember other) {
                if (other != DelGroupMember.getDefaultInstance()) {
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasMemberCount()) {
                        setMemberCount(other.getMemberCount());
                    }
                    if (!other.memberUidlist_.isEmpty()) {
                        if (this.memberUidlist_.isEmpty()) {
                            this.memberUidlist_ = other.memberUidlist_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureMemberUidlistIsMutable();
                            this.memberUidlist_.addAll(other.memberUidlist_);
                        }
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
                            this.gid_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.memberCount_ = input.readInt32();
                            continue;
                        case 24:
                            ensureMemberUidlistIsMutable();
                            this.memberUidlist_.add(Long.valueOf(input.readInt64()));
                            continue;
                        case 26:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addMemberUidlist(input.readInt64());
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

            public boolean hasGid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 1;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -2;
                this.gid_ = 0;
                return this;
            }

            public boolean hasMemberCount() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getMemberCount() {
                return this.memberCount_;
            }

            public Builder setMemberCount(int value) {
                this.bitField0_ |= 2;
                this.memberCount_ = value;
                return this;
            }

            public Builder clearMemberCount() {
                this.bitField0_ &= -3;
                this.memberCount_ = 0;
                return this;
            }

            private void ensureMemberUidlistIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.memberUidlist_ = new ArrayList(this.memberUidlist_);
                    this.bitField0_ |= 4;
                }
            }

            public List<Long> getMemberUidlistList() {
                return Collections.unmodifiableList(this.memberUidlist_);
            }

            public int getMemberUidlistCount() {
                return this.memberUidlist_.size();
            }

            public long getMemberUidlist(int index) {
                return ((Long) this.memberUidlist_.get(index)).longValue();
            }

            public Builder setMemberUidlist(int index, long value) {
                ensureMemberUidlistIsMutable();
                this.memberUidlist_.set(index, Long.valueOf(value));
                return this;
            }

            public Builder addMemberUidlist(long value) {
                ensureMemberUidlistIsMutable();
                this.memberUidlist_.add(Long.valueOf(value));
                return this;
            }

            public Builder addAllMemberUidlist(Iterable<? extends Long> values) {
                ensureMemberUidlistIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.memberUidlist_);
                return this;
            }

            public Builder clearMemberUidlist() {
                this.memberUidlist_ = Collections.emptyList();
                this.bitField0_ &= -5;
                return this;
            }
        }

        private DelGroupMember(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DelGroupMember(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DelGroupMember getDefaultInstance() {
            return defaultInstance;
        }

        public DelGroupMember getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasMemberCount() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getMemberCount() {
            return this.memberCount_;
        }

        public List<Long> getMemberUidlistList() {
            return this.memberUidlist_;
        }

        public int getMemberUidlistCount() {
            return this.memberUidlist_.size();
        }

        public long getMemberUidlist(int index) {
            return ((Long) this.memberUidlist_.get(index)).longValue();
        }

        private void initFields() {
            this.gid_ = 0;
            this.memberCount_ = 0;
            this.memberUidlist_ = Collections.emptyList();
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
                output.writeInt64(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.memberCount_);
            }
            for (int i = 0; i < this.memberUidlist_.size(); i++) {
                output.writeInt64(3, ((Long) this.memberUidlist_.get(i)).longValue());
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.memberCount_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.memberUidlist_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(((Long) this.memberUidlist_.get(i)).longValue());
            }
            int size3 = size2 + dataSize + (getMemberUidlistList().size() * 1);
            this.memoizedSerializedSize = size3;
            return size3;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DelGroupMember parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelGroupMember parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelGroupMember parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelGroupMember parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelGroupMember parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelGroupMember parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DelGroupMember parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelGroupMember parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelGroupMember parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelGroupMember parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DelGroupMember prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Group$DelGroupMemberOrBuilder */
    public interface DelGroupMemberOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        int getMemberCount();

        long getMemberUidlist(int i);

        int getMemberUidlistCount();

        List<Long> getMemberUidlistList();

        boolean hasGid();

        boolean hasMemberCount();
    }

    /* renamed from: cn.jpush.im.proto.Group$ExitGroup */
    public static final class ExitGroup extends GeneratedMessageLite implements ExitGroupOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        private static final ExitGroup defaultInstance = new ExitGroup(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Group$ExitGroup$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ExitGroup, Builder> implements ExitGroupOrBuilder {
            private int bitField0_;
            private long gid_;

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
                this.gid_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ExitGroup getDefaultInstanceForType() {
                return ExitGroup.getDefaultInstance();
            }

            public ExitGroup build() {
                ExitGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ExitGroup buildParsed() throws InvalidProtocolBufferException {
                ExitGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ExitGroup buildPartial() {
                ExitGroup result = new ExitGroup(this);
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ExitGroup other) {
                if (other != ExitGroup.getDefaultInstance() && other.hasGid()) {
                    setGid(other.getGid());
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
                            this.gid_ = input.readInt64();
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

            public boolean hasGid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 1;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -2;
                this.gid_ = 0;
                return this;
            }
        }

        private ExitGroup(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ExitGroup(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ExitGroup getDefaultInstance() {
            return defaultInstance;
        }

        public ExitGroup getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        private void initFields() {
            this.gid_ = 0;
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
                output.writeInt64(1, this.gid_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.gid_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ExitGroup parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ExitGroup parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ExitGroup parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ExitGroup parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ExitGroup parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ExitGroup parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ExitGroup parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ExitGroup parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ExitGroup parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ExitGroup parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ExitGroup prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Group$ExitGroupOrBuilder */
    public interface ExitGroupOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        boolean hasGid();
    }

    /* renamed from: cn.jpush.im.proto.Group$UpdateGroupInfo */
    public static final class UpdateGroupInfo extends GeneratedMessageLite implements UpdateGroupInfoOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        public static final int INFO_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 2;
        private static final UpdateGroupInfo defaultInstance = new UpdateGroupInfo(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        /* access modifiers changed from: private */
        public ByteString info_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString name_;

        /* renamed from: cn.jpush.im.proto.Group$UpdateGroupInfo$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<UpdateGroupInfo, Builder> implements UpdateGroupInfoOrBuilder {
            private int bitField0_;
            private long gid_;
            private ByteString info_ = ByteString.EMPTY;
            private ByteString name_ = ByteString.EMPTY;

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
                this.gid_ = 0;
                this.bitField0_ &= -2;
                this.name_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.info_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public UpdateGroupInfo getDefaultInstanceForType() {
                return UpdateGroupInfo.getDefaultInstance();
            }

            public UpdateGroupInfo build() {
                UpdateGroupInfo result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public UpdateGroupInfo buildParsed() throws InvalidProtocolBufferException {
                UpdateGroupInfo result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public UpdateGroupInfo buildPartial() {
                UpdateGroupInfo result = new UpdateGroupInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.info_ = this.info_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(UpdateGroupInfo other) {
                if (other != UpdateGroupInfo.getDefaultInstance()) {
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasName()) {
                        setName(other.getName());
                    }
                    if (other.hasInfo()) {
                        setInfo(other.getInfo());
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
                            this.gid_ = input.readInt64();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.name_ = input.readBytes();
                            continue;
                        case 26:
                            this.bitField0_ |= 4;
                            this.info_ = input.readBytes();
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

            public boolean hasGid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 1;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -2;
                this.gid_ = 0;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getName() {
                return this.name_;
            }

            public Builder setName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.name_ = value;
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -3;
                this.name_ = UpdateGroupInfo.getDefaultInstance().getName();
                return this;
            }

            public boolean hasInfo() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getInfo() {
                return this.info_;
            }

            public Builder setInfo(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.info_ = value;
                return this;
            }

            public Builder clearInfo() {
                this.bitField0_ &= -5;
                this.info_ = UpdateGroupInfo.getDefaultInstance().getInfo();
                return this;
            }
        }

        private UpdateGroupInfo(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private UpdateGroupInfo(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static UpdateGroupInfo getDefaultInstance() {
            return defaultInstance;
        }

        public UpdateGroupInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getName() {
            return this.name_;
        }

        public boolean hasInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getInfo() {
            return this.info_;
        }

        private void initFields() {
            this.gid_ = 0;
            this.name_ = ByteString.EMPTY;
            this.info_ = ByteString.EMPTY;
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
                output.writeInt64(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.info_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.gid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, this.info_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateGroupInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static UpdateGroupInfo parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static UpdateGroupInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static UpdateGroupInfo parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static UpdateGroupInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateGroupInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Group$UpdateGroupInfoOrBuilder */
    public interface UpdateGroupInfoOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        ByteString getInfo();

        ByteString getName();

        boolean hasGid();

        boolean hasInfo();

        boolean hasName();
    }

    private Group() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }
}
