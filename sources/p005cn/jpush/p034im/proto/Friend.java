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

/* renamed from: cn.jpush.im.proto.Friend */
public final class Friend {

    /* renamed from: cn.jpush.im.proto.Friend$AddBlackList */
    public static final class AddBlackList extends GeneratedMessageLite implements AddBlackListOrBuilder {
        public static final int TARGET_LIST_FIELD_NUMBER = 1;
        private static final AddBlackList defaultInstance = new AddBlackList(true);
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Long> targetList_;

        /* renamed from: cn.jpush.im.proto.Friend$AddBlackList$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddBlackList, Builder> implements AddBlackListOrBuilder {
            private int bitField0_;
            private List<Long> targetList_ = Collections.emptyList();

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
                this.targetList_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddBlackList getDefaultInstanceForType() {
                return AddBlackList.getDefaultInstance();
            }

            public AddBlackList build() {
                AddBlackList result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddBlackList buildParsed() throws InvalidProtocolBufferException {
                AddBlackList result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddBlackList buildPartial() {
                AddBlackList result = new AddBlackList(this);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.targetList_ = Collections.unmodifiableList(this.targetList_);
                    this.bitField0_ &= -2;
                }
                result.targetList_ = this.targetList_;
                return result;
            }

            public Builder mergeFrom(AddBlackList other) {
                if (other != AddBlackList.getDefaultInstance() && !other.targetList_.isEmpty()) {
                    if (this.targetList_.isEmpty()) {
                        this.targetList_ = other.targetList_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTargetListIsMutable();
                        this.targetList_.addAll(other.targetList_);
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
                            ensureTargetListIsMutable();
                            this.targetList_.add(Long.valueOf(input.readInt64()));
                            continue;
                        case 10:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addTargetList(input.readInt64());
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

            private void ensureTargetListIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.targetList_ = new ArrayList(this.targetList_);
                    this.bitField0_ |= 1;
                }
            }

            public List<Long> getTargetListList() {
                return Collections.unmodifiableList(this.targetList_);
            }

            public int getTargetListCount() {
                return this.targetList_.size();
            }

            public long getTargetList(int index) {
                return ((Long) this.targetList_.get(index)).longValue();
            }

            public Builder setTargetList(int index, long value) {
                ensureTargetListIsMutable();
                this.targetList_.set(index, Long.valueOf(value));
                return this;
            }

            public Builder addTargetList(long value) {
                ensureTargetListIsMutable();
                this.targetList_.add(Long.valueOf(value));
                return this;
            }

            public Builder addAllTargetList(Iterable<? extends Long> values) {
                ensureTargetListIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.targetList_);
                return this;
            }

            public Builder clearTargetList() {
                this.targetList_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }
        }

        private AddBlackList(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddBlackList(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddBlackList getDefaultInstance() {
            return defaultInstance;
        }

        public AddBlackList getDefaultInstanceForType() {
            return defaultInstance;
        }

        public List<Long> getTargetListList() {
            return this.targetList_;
        }

        public int getTargetListCount() {
            return this.targetList_.size();
        }

        public long getTargetList(int index) {
            return ((Long) this.targetList_.get(index)).longValue();
        }

        private void initFields() {
            this.targetList_ = Collections.emptyList();
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
            for (int i = 0; i < this.targetList_.size(); i++) {
                output.writeInt64(1, ((Long) this.targetList_.get(i)).longValue());
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.targetList_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(((Long) this.targetList_.get(i)).longValue());
            }
            int size2 = 0 + dataSize + (getTargetListList().size() * 1);
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddBlackList parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddBlackList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddBlackList parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddBlackList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddBlackList parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddBlackList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddBlackList parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddBlackList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddBlackList parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddBlackList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddBlackList prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Friend$AddBlackListOrBuilder */
    public interface AddBlackListOrBuilder extends MessageLiteOrBuilder {
        long getTargetList(int i);

        int getTargetListCount();

        List<Long> getTargetListList();
    }

    /* renamed from: cn.jpush.im.proto.Friend$AddFriend */
    public static final class AddFriend extends GeneratedMessageLite implements AddFriendOrBuilder {
        public static final int FROM_TYPE_FIELD_NUMBER = 4;
        public static final int MEMO_NAME_FIELD_NUMBER = 2;
        public static final int MEMO_OTHERS_FIELD_NUMBER = 3;
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        public static final int WHY_FIELD_NUMBER = 5;
        private static final AddFriend defaultInstance = new AddFriend(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int fromType_;
        /* access modifiers changed from: private */
        public ByteString memoName_;
        /* access modifiers changed from: private */
        public ByteString memoOthers_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long targetUid_;
        /* access modifiers changed from: private */
        public ByteString why_;

        /* renamed from: cn.jpush.im.proto.Friend$AddFriend$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddFriend, Builder> implements AddFriendOrBuilder {
            private int bitField0_;
            private int fromType_;
            private ByteString memoName_ = ByteString.EMPTY;
            private ByteString memoOthers_ = ByteString.EMPTY;
            private long targetUid_;
            private ByteString why_ = ByteString.EMPTY;

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
                this.targetUid_ = 0;
                this.bitField0_ &= -2;
                this.memoName_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.memoOthers_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.fromType_ = 0;
                this.bitField0_ &= -9;
                this.why_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddFriend getDefaultInstanceForType() {
                return AddFriend.getDefaultInstance();
            }

            public AddFriend build() {
                AddFriend result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddFriend buildParsed() throws InvalidProtocolBufferException {
                AddFriend result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddFriend buildPartial() {
                AddFriend result = new AddFriend(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.memoName_ = this.memoName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.memoOthers_ = this.memoOthers_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.fromType_ = this.fromType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.why_ = this.why_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(AddFriend other) {
                if (other != AddFriend.getDefaultInstance()) {
                    if (other.hasTargetUid()) {
                        setTargetUid(other.getTargetUid());
                    }
                    if (other.hasMemoName()) {
                        setMemoName(other.getMemoName());
                    }
                    if (other.hasMemoOthers()) {
                        setMemoOthers(other.getMemoOthers());
                    }
                    if (other.hasFromType()) {
                        setFromType(other.getFromType());
                    }
                    if (other.hasWhy()) {
                        setWhy(other.getWhy());
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
                            this.targetUid_ = input.readInt64();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.memoName_ = input.readBytes();
                            continue;
                        case 26:
                            this.bitField0_ |= 4;
                            this.memoOthers_ = input.readBytes();
                            continue;
                        case 32:
                            this.bitField0_ |= 8;
                            this.fromType_ = input.readInt32();
                            continue;
                        case 42:
                            this.bitField0_ |= 16;
                            this.why_ = input.readBytes();
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

            public boolean hasTargetUid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getTargetUid() {
                return this.targetUid_;
            }

            public Builder setTargetUid(long value) {
                this.bitField0_ |= 1;
                this.targetUid_ = value;
                return this;
            }

            public Builder clearTargetUid() {
                this.bitField0_ &= -2;
                this.targetUid_ = 0;
                return this;
            }

            public boolean hasMemoName() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getMemoName() {
                return this.memoName_;
            }

            public Builder setMemoName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.memoName_ = value;
                return this;
            }

            public Builder clearMemoName() {
                this.bitField0_ &= -3;
                this.memoName_ = AddFriend.getDefaultInstance().getMemoName();
                return this;
            }

            public boolean hasMemoOthers() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getMemoOthers() {
                return this.memoOthers_;
            }

            public Builder setMemoOthers(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.memoOthers_ = value;
                return this;
            }

            public Builder clearMemoOthers() {
                this.bitField0_ &= -5;
                this.memoOthers_ = AddFriend.getDefaultInstance().getMemoOthers();
                return this;
            }

            public boolean hasFromType() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getFromType() {
                return this.fromType_;
            }

            public Builder setFromType(int value) {
                this.bitField0_ |= 8;
                this.fromType_ = value;
                return this;
            }

            public Builder clearFromType() {
                this.bitField0_ &= -9;
                this.fromType_ = 0;
                return this;
            }

            public boolean hasWhy() {
                return (this.bitField0_ & 16) == 16;
            }

            public ByteString getWhy() {
                return this.why_;
            }

            public Builder setWhy(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.why_ = value;
                return this;
            }

            public Builder clearWhy() {
                this.bitField0_ &= -17;
                this.why_ = AddFriend.getDefaultInstance().getWhy();
                return this;
            }
        }

        private AddFriend(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddFriend(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddFriend getDefaultInstance() {
            return defaultInstance;
        }

        public AddFriend getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        public boolean hasMemoName() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getMemoName() {
            return this.memoName_;
        }

        public boolean hasMemoOthers() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getMemoOthers() {
            return this.memoOthers_;
        }

        public boolean hasFromType() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getFromType() {
            return this.fromType_;
        }

        public boolean hasWhy() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getWhy() {
            return this.why_;
        }

        private void initFields() {
            this.targetUid_ = 0;
            this.memoName_ = ByteString.EMPTY;
            this.memoOthers_ = ByteString.EMPTY;
            this.fromType_ = 0;
            this.why_ = ByteString.EMPTY;
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
                output.writeInt64(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.memoName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.memoOthers_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.fromType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, this.why_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.memoName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, this.memoOthers_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.fromType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, this.why_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddFriend parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddFriend parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddFriend parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddFriend parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddFriend parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddFriend parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddFriend parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddFriend parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddFriend parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddFriend parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddFriend prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Friend$AddFriendOrBuilder */
    public interface AddFriendOrBuilder extends MessageLiteOrBuilder {
        int getFromType();

        ByteString getMemoName();

        ByteString getMemoOthers();

        long getTargetUid();

        ByteString getWhy();

        boolean hasFromType();

        boolean hasMemoName();

        boolean hasMemoOthers();

        boolean hasTargetUid();

        boolean hasWhy();
    }

    /* renamed from: cn.jpush.im.proto.Friend$DelBlackList */
    public static final class DelBlackList extends GeneratedMessageLite implements DelBlackListOrBuilder {
        public static final int TARGET_LIST_FIELD_NUMBER = 1;
        private static final DelBlackList defaultInstance = new DelBlackList(true);
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Long> targetList_;

        /* renamed from: cn.jpush.im.proto.Friend$DelBlackList$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DelBlackList, Builder> implements DelBlackListOrBuilder {
            private int bitField0_;
            private List<Long> targetList_ = Collections.emptyList();

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
                this.targetList_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DelBlackList getDefaultInstanceForType() {
                return DelBlackList.getDefaultInstance();
            }

            public DelBlackList build() {
                DelBlackList result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DelBlackList buildParsed() throws InvalidProtocolBufferException {
                DelBlackList result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DelBlackList buildPartial() {
                DelBlackList result = new DelBlackList(this);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.targetList_ = Collections.unmodifiableList(this.targetList_);
                    this.bitField0_ &= -2;
                }
                result.targetList_ = this.targetList_;
                return result;
            }

            public Builder mergeFrom(DelBlackList other) {
                if (other != DelBlackList.getDefaultInstance() && !other.targetList_.isEmpty()) {
                    if (this.targetList_.isEmpty()) {
                        this.targetList_ = other.targetList_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTargetListIsMutable();
                        this.targetList_.addAll(other.targetList_);
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
                            ensureTargetListIsMutable();
                            this.targetList_.add(Long.valueOf(input.readInt64()));
                            continue;
                        case 10:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addTargetList(input.readInt64());
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

            private void ensureTargetListIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.targetList_ = new ArrayList(this.targetList_);
                    this.bitField0_ |= 1;
                }
            }

            public List<Long> getTargetListList() {
                return Collections.unmodifiableList(this.targetList_);
            }

            public int getTargetListCount() {
                return this.targetList_.size();
            }

            public long getTargetList(int index) {
                return ((Long) this.targetList_.get(index)).longValue();
            }

            public Builder setTargetList(int index, long value) {
                ensureTargetListIsMutable();
                this.targetList_.set(index, Long.valueOf(value));
                return this;
            }

            public Builder addTargetList(long value) {
                ensureTargetListIsMutable();
                this.targetList_.add(Long.valueOf(value));
                return this;
            }

            public Builder addAllTargetList(Iterable<? extends Long> values) {
                ensureTargetListIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.targetList_);
                return this;
            }

            public Builder clearTargetList() {
                this.targetList_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }
        }

        private DelBlackList(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DelBlackList(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DelBlackList getDefaultInstance() {
            return defaultInstance;
        }

        public DelBlackList getDefaultInstanceForType() {
            return defaultInstance;
        }

        public List<Long> getTargetListList() {
            return this.targetList_;
        }

        public int getTargetListCount() {
            return this.targetList_.size();
        }

        public long getTargetList(int index) {
            return ((Long) this.targetList_.get(index)).longValue();
        }

        private void initFields() {
            this.targetList_ = Collections.emptyList();
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
            for (int i = 0; i < this.targetList_.size(); i++) {
                output.writeInt64(1, ((Long) this.targetList_.get(i)).longValue());
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.targetList_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(((Long) this.targetList_.get(i)).longValue());
            }
            int size2 = 0 + dataSize + (getTargetListList().size() * 1);
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DelBlackList parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelBlackList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelBlackList parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelBlackList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelBlackList parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelBlackList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DelBlackList parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelBlackList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelBlackList parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelBlackList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DelBlackList prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Friend$DelBlackListOrBuilder */
    public interface DelBlackListOrBuilder extends MessageLiteOrBuilder {
        long getTargetList(int i);

        int getTargetListCount();

        List<Long> getTargetListList();
    }

    /* renamed from: cn.jpush.im.proto.Friend$DelFriend */
    public static final class DelFriend extends GeneratedMessageLite implements DelFriendOrBuilder {
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        private static final DelFriend defaultInstance = new DelFriend(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long targetUid_;

        /* renamed from: cn.jpush.im.proto.Friend$DelFriend$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DelFriend, Builder> implements DelFriendOrBuilder {
            private int bitField0_;
            private long targetUid_;

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
                this.targetUid_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DelFriend getDefaultInstanceForType() {
                return DelFriend.getDefaultInstance();
            }

            public DelFriend build() {
                DelFriend result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DelFriend buildParsed() throws InvalidProtocolBufferException {
                DelFriend result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DelFriend buildPartial() {
                DelFriend result = new DelFriend(this);
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(DelFriend other) {
                if (other != DelFriend.getDefaultInstance() && other.hasTargetUid()) {
                    setTargetUid(other.getTargetUid());
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
                            this.targetUid_ = input.readInt64();
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

            public boolean hasTargetUid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getTargetUid() {
                return this.targetUid_;
            }

            public Builder setTargetUid(long value) {
                this.bitField0_ |= 1;
                this.targetUid_ = value;
                return this;
            }

            public Builder clearTargetUid() {
                this.bitField0_ &= -2;
                this.targetUid_ = 0;
                return this;
            }
        }

        private DelFriend(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DelFriend(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DelFriend getDefaultInstance() {
            return defaultInstance;
        }

        public DelFriend getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        private void initFields() {
            this.targetUid_ = 0;
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
                output.writeInt64(1, this.targetUid_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.targetUid_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DelFriend parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelFriend parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelFriend parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DelFriend parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DelFriend parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelFriend parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DelFriend parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelFriend parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DelFriend parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DelFriend parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DelFriend prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Friend$DelFriendOrBuilder */
    public interface DelFriendOrBuilder extends MessageLiteOrBuilder {
        long getTargetUid();

        boolean hasTargetUid();
    }

    /* renamed from: cn.jpush.im.proto.Friend$UpdateMemo */
    public static final class UpdateMemo extends GeneratedMessageLite implements UpdateMemoOrBuilder {
        public static final int NEW_MEMO_NAME_FIELD_NUMBER = 2;
        public static final int NEW_MEMO_OTHERS_FIELD_NUMBER = 3;
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        private static final UpdateMemo defaultInstance = new UpdateMemo(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString newMemoName_;
        /* access modifiers changed from: private */
        public ByteString newMemoOthers_;
        /* access modifiers changed from: private */
        public long targetUid_;

        /* renamed from: cn.jpush.im.proto.Friend$UpdateMemo$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<UpdateMemo, Builder> implements UpdateMemoOrBuilder {
            private int bitField0_;
            private ByteString newMemoName_ = ByteString.EMPTY;
            private ByteString newMemoOthers_ = ByteString.EMPTY;
            private long targetUid_;

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
                this.targetUid_ = 0;
                this.bitField0_ &= -2;
                this.newMemoName_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.newMemoOthers_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public UpdateMemo getDefaultInstanceForType() {
                return UpdateMemo.getDefaultInstance();
            }

            public UpdateMemo build() {
                UpdateMemo result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public UpdateMemo buildParsed() throws InvalidProtocolBufferException {
                UpdateMemo result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public UpdateMemo buildPartial() {
                UpdateMemo result = new UpdateMemo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.newMemoName_ = this.newMemoName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.newMemoOthers_ = this.newMemoOthers_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(UpdateMemo other) {
                if (other != UpdateMemo.getDefaultInstance()) {
                    if (other.hasTargetUid()) {
                        setTargetUid(other.getTargetUid());
                    }
                    if (other.hasNewMemoName()) {
                        setNewMemoName(other.getNewMemoName());
                    }
                    if (other.hasNewMemoOthers()) {
                        setNewMemoOthers(other.getNewMemoOthers());
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
                            this.targetUid_ = input.readInt64();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.newMemoName_ = input.readBytes();
                            continue;
                        case 26:
                            this.bitField0_ |= 4;
                            this.newMemoOthers_ = input.readBytes();
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

            public boolean hasTargetUid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getTargetUid() {
                return this.targetUid_;
            }

            public Builder setTargetUid(long value) {
                this.bitField0_ |= 1;
                this.targetUid_ = value;
                return this;
            }

            public Builder clearTargetUid() {
                this.bitField0_ &= -2;
                this.targetUid_ = 0;
                return this;
            }

            public boolean hasNewMemoName() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getNewMemoName() {
                return this.newMemoName_;
            }

            public Builder setNewMemoName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.newMemoName_ = value;
                return this;
            }

            public Builder clearNewMemoName() {
                this.bitField0_ &= -3;
                this.newMemoName_ = UpdateMemo.getDefaultInstance().getNewMemoName();
                return this;
            }

            public boolean hasNewMemoOthers() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getNewMemoOthers() {
                return this.newMemoOthers_;
            }

            public Builder setNewMemoOthers(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.newMemoOthers_ = value;
                return this;
            }

            public Builder clearNewMemoOthers() {
                this.bitField0_ &= -5;
                this.newMemoOthers_ = UpdateMemo.getDefaultInstance().getNewMemoOthers();
                return this;
            }
        }

        private UpdateMemo(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private UpdateMemo(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static UpdateMemo getDefaultInstance() {
            return defaultInstance;
        }

        public UpdateMemo getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        public boolean hasNewMemoName() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getNewMemoName() {
            return this.newMemoName_;
        }

        public boolean hasNewMemoOthers() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getNewMemoOthers() {
            return this.newMemoOthers_;
        }

        private void initFields() {
            this.targetUid_ = 0;
            this.newMemoName_ = ByteString.EMPTY;
            this.newMemoOthers_ = ByteString.EMPTY;
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
                output.writeInt64(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.newMemoName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.newMemoOthers_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.targetUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.newMemoName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, this.newMemoOthers_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateMemo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static UpdateMemo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static UpdateMemo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static UpdateMemo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static UpdateMemo parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static UpdateMemo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static UpdateMemo parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static UpdateMemo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static UpdateMemo parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static UpdateMemo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateMemo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Friend$UpdateMemoOrBuilder */
    public interface UpdateMemoOrBuilder extends MessageLiteOrBuilder {
        ByteString getNewMemoName();

        ByteString getNewMemoOthers();

        long getTargetUid();

        boolean hasNewMemoName();

        boolean hasNewMemoOthers();

        boolean hasTargetUid();
    }

    private Friend() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }
}
