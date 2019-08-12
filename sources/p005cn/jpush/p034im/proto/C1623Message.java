package p005cn.jpush.p034im.proto;

import com.google.protobuf.jpush.ByteString;
import com.google.protobuf.jpush.CodedInputStream;
import com.google.protobuf.jpush.CodedOutputStream;
import com.google.protobuf.jpush.ExtensionRegistryLite;
import com.google.protobuf.jpush.GeneratedMessageLite;
import com.google.protobuf.jpush.InvalidProtocolBufferException;
import com.google.protobuf.jpush.MessageLite;
import com.google.protobuf.jpush.MessageLiteOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.im.proto.Message */
public final class C1623Message {

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGlobal */
    public static final class AddMsgnoDisturbGlobal extends GeneratedMessageLite implements AddMsgnoDisturbGlobalOrBuilder {
        public static final int NONE_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final AddMsgnoDisturbGlobal defaultInstance = new AddMsgnoDisturbGlobal(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int none_;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGlobal$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddMsgnoDisturbGlobal, Builder> implements AddMsgnoDisturbGlobalOrBuilder {
            private int bitField0_;
            private int none_;
            private long version_;

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
                this.none_ = 0;
                this.bitField0_ &= -2;
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddMsgnoDisturbGlobal getDefaultInstanceForType() {
                return AddMsgnoDisturbGlobal.getDefaultInstance();
            }

            public AddMsgnoDisturbGlobal build() {
                AddMsgnoDisturbGlobal result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddMsgnoDisturbGlobal buildParsed() throws InvalidProtocolBufferException {
                AddMsgnoDisturbGlobal result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddMsgnoDisturbGlobal buildPartial() {
                AddMsgnoDisturbGlobal result = new AddMsgnoDisturbGlobal(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.none_ = this.none_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(AddMsgnoDisturbGlobal other) {
                if (other != AddMsgnoDisturbGlobal.getDefaultInstance()) {
                    if (other.hasNone()) {
                        setNone(other.getNone());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                            this.none_ = input.readInt32();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.version_ = input.readInt64();
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

            public boolean hasNone() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getNone() {
                return this.none_;
            }

            public Builder setNone(int value) {
                this.bitField0_ |= 1;
                this.none_ = value;
                return this;
            }

            public Builder clearNone() {
                this.bitField0_ &= -2;
                this.none_ = 0;
                return this;
            }

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private AddMsgnoDisturbGlobal(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddMsgnoDisturbGlobal(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddMsgnoDisturbGlobal getDefaultInstance() {
            return defaultInstance;
        }

        public AddMsgnoDisturbGlobal getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasNone() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getNone() {
            return this.none_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.none_ = 0;
            this.version_ = 0;
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
                output.writeInt32(1, this.none_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.version_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.none_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddMsgnoDisturbGlobal parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbGlobal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbGlobal parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbGlobal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddMsgnoDisturbGlobal prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGlobalOrBuilder */
    public interface AddMsgnoDisturbGlobalOrBuilder extends MessageLiteOrBuilder {
        int getNone();

        long getVersion();

        boolean hasNone();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGroup */
    public static final class AddMsgnoDisturbGroup extends GeneratedMessageLite implements AddMsgnoDisturbGroupOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final AddMsgnoDisturbGroup defaultInstance = new AddMsgnoDisturbGroup(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGroup$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddMsgnoDisturbGroup, Builder> implements AddMsgnoDisturbGroupOrBuilder {
            private int bitField0_;
            private long gid_;
            private long version_;

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
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddMsgnoDisturbGroup getDefaultInstanceForType() {
                return AddMsgnoDisturbGroup.getDefaultInstance();
            }

            public AddMsgnoDisturbGroup build() {
                AddMsgnoDisturbGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddMsgnoDisturbGroup buildParsed() throws InvalidProtocolBufferException {
                AddMsgnoDisturbGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddMsgnoDisturbGroup buildPartial() {
                AddMsgnoDisturbGroup result = new AddMsgnoDisturbGroup(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(AddMsgnoDisturbGroup other) {
                if (other != AddMsgnoDisturbGroup.getDefaultInstance()) {
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                            this.version_ = input.readInt64();
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

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private AddMsgnoDisturbGroup(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddMsgnoDisturbGroup(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddMsgnoDisturbGroup getDefaultInstance() {
            return defaultInstance;
        }

        public AddMsgnoDisturbGroup getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.gid_ = 0;
            this.version_ = 0;
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
                output.writeInt64(2, this.version_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddMsgnoDisturbGroup parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbGroup parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbGroup parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbGroup parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddMsgnoDisturbGroup prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbGroupOrBuilder */
    public interface AddMsgnoDisturbGroupOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        long getVersion();

        boolean hasGid();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbSingle */
    public static final class AddMsgnoDisturbSingle extends GeneratedMessageLite implements AddMsgnoDisturbSingleOrBuilder {
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final AddMsgnoDisturbSingle defaultInstance = new AddMsgnoDisturbSingle(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long targetUid_;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbSingle$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<AddMsgnoDisturbSingle, Builder> implements AddMsgnoDisturbSingleOrBuilder {
            private int bitField0_;
            private long targetUid_;
            private long version_;

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
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public AddMsgnoDisturbSingle getDefaultInstanceForType() {
                return AddMsgnoDisturbSingle.getDefaultInstance();
            }

            public AddMsgnoDisturbSingle build() {
                AddMsgnoDisturbSingle result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public AddMsgnoDisturbSingle buildParsed() throws InvalidProtocolBufferException {
                AddMsgnoDisturbSingle result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public AddMsgnoDisturbSingle buildPartial() {
                AddMsgnoDisturbSingle result = new AddMsgnoDisturbSingle(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(AddMsgnoDisturbSingle other) {
                if (other != AddMsgnoDisturbSingle.getDefaultInstance()) {
                    if (other.hasTargetUid()) {
                        setTargetUid(other.getTargetUid());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                        case 16:
                            this.bitField0_ |= 2;
                            this.version_ = input.readInt64();
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

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private AddMsgnoDisturbSingle(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private AddMsgnoDisturbSingle(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static AddMsgnoDisturbSingle getDefaultInstance() {
            return defaultInstance;
        }

        public AddMsgnoDisturbSingle getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.targetUid_ = 0;
            this.version_ = 0;
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
                output.writeInt64(2, this.version_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AddMsgnoDisturbSingle parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbSingle parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static AddMsgnoDisturbSingle parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static AddMsgnoDisturbSingle parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(AddMsgnoDisturbSingle prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$AddMsgnoDisturbSingleOrBuilder */
    public interface AddMsgnoDisturbSingleOrBuilder extends MessageLiteOrBuilder {
        long getTargetUid();

        long getVersion();

        boolean hasTargetUid();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$ChatMsg */
    public static final class ChatMsg extends GeneratedMessageLite implements ChatMsgOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 5;
        public static final int CTIME_FIELD_NUMBER = 6;
        public static final int FROM_GID_FIELD_NUMBER = 2;
        public static final int FROM_UID_FIELD_NUMBER = 1;
        public static final int MSGID_FIELD_NUMBER = 3;
        public static final int MSG_LEVEL_FIELD_NUMBER = 7;
        public static final int MSG_TYPE_FIELD_NUMBER = 4;
        private static final ChatMsg defaultInstance = new ChatMsg(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public MessageContent content_;
        /* access modifiers changed from: private */
        public int ctime_;
        /* access modifiers changed from: private */
        public long fromGid_;
        /* access modifiers changed from: private */
        public long fromUid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int msgLevel_;
        /* access modifiers changed from: private */
        public int msgType_;
        /* access modifiers changed from: private */
        public long msgid_;

        /* renamed from: cn.jpush.im.proto.Message$ChatMsg$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ChatMsg, Builder> implements ChatMsgOrBuilder {
            private int bitField0_;
            private MessageContent content_ = MessageContent.getDefaultInstance();
            private int ctime_;
            private long fromGid_;
            private long fromUid_;
            private int msgLevel_;
            private int msgType_;
            private long msgid_;

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
                this.fromUid_ = 0;
                this.bitField0_ &= -2;
                this.fromGid_ = 0;
                this.bitField0_ &= -3;
                this.msgid_ = 0;
                this.bitField0_ &= -5;
                this.msgType_ = 0;
                this.bitField0_ &= -9;
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -17;
                this.ctime_ = 0;
                this.bitField0_ &= -33;
                this.msgLevel_ = 0;
                this.bitField0_ &= -65;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ChatMsg getDefaultInstanceForType() {
                return ChatMsg.getDefaultInstance();
            }

            public ChatMsg build() {
                ChatMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ChatMsg buildParsed() throws InvalidProtocolBufferException {
                ChatMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ChatMsg buildPartial() {
                ChatMsg result = new ChatMsg(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.fromUid_ = this.fromUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.fromGid_ = this.fromGid_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.msgid_ = this.msgid_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.msgType_ = this.msgType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.content_ = this.content_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.ctime_ = this.ctime_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.msgLevel_ = this.msgLevel_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ChatMsg other) {
                if (other != ChatMsg.getDefaultInstance()) {
                    if (other.hasFromUid()) {
                        setFromUid(other.getFromUid());
                    }
                    if (other.hasFromGid()) {
                        setFromGid(other.getFromGid());
                    }
                    if (other.hasMsgid()) {
                        setMsgid(other.getMsgid());
                    }
                    if (other.hasMsgType()) {
                        setMsgType(other.getMsgType());
                    }
                    if (other.hasContent()) {
                        mergeContent(other.getContent());
                    }
                    if (other.hasCtime()) {
                        setCtime(other.getCtime());
                    }
                    if (other.hasMsgLevel()) {
                        setMsgLevel(other.getMsgLevel());
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
                            this.fromUid_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.fromGid_ = input.readInt64();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.msgid_ = input.readInt64();
                            continue;
                        case 32:
                            this.bitField0_ |= 8;
                            this.msgType_ = input.readInt32();
                            continue;
                        case 42:
                            Builder subBuilder = MessageContent.newBuilder();
                            if (hasContent()) {
                                subBuilder.mergeFrom(getContent());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setContent(subBuilder.buildPartial());
                            continue;
                        case 48:
                            this.bitField0_ |= 32;
                            this.ctime_ = input.readUInt32();
                            continue;
                        case 56:
                            this.bitField0_ |= 64;
                            this.msgLevel_ = input.readInt32();
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

            public boolean hasFromUid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getFromUid() {
                return this.fromUid_;
            }

            public Builder setFromUid(long value) {
                this.bitField0_ |= 1;
                this.fromUid_ = value;
                return this;
            }

            public Builder clearFromUid() {
                this.bitField0_ &= -2;
                this.fromUid_ = 0;
                return this;
            }

            public boolean hasFromGid() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getFromGid() {
                return this.fromGid_;
            }

            public Builder setFromGid(long value) {
                this.bitField0_ |= 2;
                this.fromGid_ = value;
                return this;
            }

            public Builder clearFromGid() {
                this.bitField0_ &= -3;
                this.fromGid_ = 0;
                return this;
            }

            public boolean hasMsgid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getMsgid() {
                return this.msgid_;
            }

            public Builder setMsgid(long value) {
                this.bitField0_ |= 4;
                this.msgid_ = value;
                return this;
            }

            public Builder clearMsgid() {
                this.bitField0_ &= -5;
                this.msgid_ = 0;
                return this;
            }

            public boolean hasMsgType() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getMsgType() {
                return this.msgType_;
            }

            public Builder setMsgType(int value) {
                this.bitField0_ |= 8;
                this.msgType_ = value;
                return this;
            }

            public Builder clearMsgType() {
                this.bitField0_ &= -9;
                this.msgType_ = 0;
                return this;
            }

            public boolean hasContent() {
                return (this.bitField0_ & 16) == 16;
            }

            public MessageContent getContent() {
                return this.content_;
            }

            public Builder setContent(MessageContent value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.content_ = value;
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setContent(Builder builderForValue) {
                this.content_ = builderForValue.build();
                this.bitField0_ |= 16;
                return this;
            }

            public Builder mergeContent(MessageContent value) {
                if ((this.bitField0_ & 16) != 16 || this.content_ == MessageContent.getDefaultInstance()) {
                    this.content_ = value;
                } else {
                    this.content_ = MessageContent.newBuilder(this.content_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder clearContent() {
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -17;
                return this;
            }

            public boolean hasCtime() {
                return (this.bitField0_ & 32) == 32;
            }

            public int getCtime() {
                return this.ctime_;
            }

            public Builder setCtime(int value) {
                this.bitField0_ |= 32;
                this.ctime_ = value;
                return this;
            }

            public Builder clearCtime() {
                this.bitField0_ &= -33;
                this.ctime_ = 0;
                return this;
            }

            public boolean hasMsgLevel() {
                return (this.bitField0_ & 64) == 64;
            }

            public int getMsgLevel() {
                return this.msgLevel_;
            }

            public Builder setMsgLevel(int value) {
                this.bitField0_ |= 64;
                this.msgLevel_ = value;
                return this;
            }

            public Builder clearMsgLevel() {
                this.bitField0_ &= -65;
                this.msgLevel_ = 0;
                return this;
            }
        }

        private ChatMsg(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ChatMsg(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ChatMsg getDefaultInstance() {
            return defaultInstance;
        }

        public ChatMsg getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasFromUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getFromUid() {
            return this.fromUid_;
        }

        public boolean hasFromGid() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getFromGid() {
            return this.fromGid_;
        }

        public boolean hasMsgid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getMsgid() {
            return this.msgid_;
        }

        public boolean hasMsgType() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getMsgType() {
            return this.msgType_;
        }

        public boolean hasContent() {
            return (this.bitField0_ & 16) == 16;
        }

        public MessageContent getContent() {
            return this.content_;
        }

        public boolean hasCtime() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getCtime() {
            return this.ctime_;
        }

        public boolean hasMsgLevel() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getMsgLevel() {
            return this.msgLevel_;
        }

        private void initFields() {
            this.fromUid_ = 0;
            this.fromGid_ = 0;
            this.msgid_ = 0;
            this.msgType_ = 0;
            this.content_ = MessageContent.getDefaultInstance();
            this.ctime_ = 0;
            this.msgLevel_ = 0;
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
                output.writeInt64(1, this.fromUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.fromGid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.msgid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.msgType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, this.content_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeUInt32(6, this.ctime_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.msgLevel_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.fromUid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.fromGid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.msgid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.msgType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, this.content_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeUInt32Size(6, this.ctime_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.msgLevel_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ChatMsg parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ChatMsg parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ChatMsg parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ChatMsg parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ChatMsg parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ChatMsg parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ChatMsg parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ChatMsg parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ChatMsg parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ChatMsg parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ChatMsg prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$ChatMsgOrBuilder */
    public interface ChatMsgOrBuilder extends MessageLiteOrBuilder {
        MessageContent getContent();

        int getCtime();

        long getFromGid();

        long getFromUid();

        int getMsgLevel();

        int getMsgType();

        long getMsgid();

        boolean hasContent();

        boolean hasCtime();

        boolean hasFromGid();

        boolean hasFromUid();

        boolean hasMsgLevel();

        boolean hasMsgType();

        boolean hasMsgid();
    }

    /* renamed from: cn.jpush.im.proto.Message$ChatMsgSync */
    public static final class ChatMsgSync extends GeneratedMessageLite implements ChatMsgSyncOrBuilder {
        public static final int CHAT_MSG_FIELD_NUMBER = 1;
        private static final ChatMsgSync defaultInstance = new ChatMsgSync(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public List<ChatMsg> chatMsg_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Message$ChatMsgSync$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ChatMsgSync, Builder> implements ChatMsgSyncOrBuilder {
            private int bitField0_;
            private List<ChatMsg> chatMsg_ = Collections.emptyList();

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
                this.chatMsg_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ChatMsgSync getDefaultInstanceForType() {
                return ChatMsgSync.getDefaultInstance();
            }

            public ChatMsgSync build() {
                ChatMsgSync result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ChatMsgSync buildParsed() throws InvalidProtocolBufferException {
                ChatMsgSync result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ChatMsgSync buildPartial() {
                ChatMsgSync result = new ChatMsgSync(this);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.chatMsg_ = Collections.unmodifiableList(this.chatMsg_);
                    this.bitField0_ &= -2;
                }
                result.chatMsg_ = this.chatMsg_;
                return result;
            }

            public Builder mergeFrom(ChatMsgSync other) {
                if (other != ChatMsgSync.getDefaultInstance() && !other.chatMsg_.isEmpty()) {
                    if (this.chatMsg_.isEmpty()) {
                        this.chatMsg_ = other.chatMsg_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureChatMsgIsMutable();
                        this.chatMsg_.addAll(other.chatMsg_);
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
                            Builder subBuilder = ChatMsg.newBuilder();
                            input.readMessage(subBuilder, extensionRegistry);
                            addChatMsg(subBuilder.buildPartial());
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

            private void ensureChatMsgIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.chatMsg_ = new ArrayList(this.chatMsg_);
                    this.bitField0_ |= 1;
                }
            }

            public List<ChatMsg> getChatMsgList() {
                return Collections.unmodifiableList(this.chatMsg_);
            }

            public int getChatMsgCount() {
                return this.chatMsg_.size();
            }

            public ChatMsg getChatMsg(int index) {
                return (ChatMsg) this.chatMsg_.get(index);
            }

            public Builder setChatMsg(int index, ChatMsg value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureChatMsgIsMutable();
                this.chatMsg_.set(index, value);
                return this;
            }

            public Builder setChatMsg(int index, Builder builderForValue) {
                ensureChatMsgIsMutable();
                this.chatMsg_.set(index, builderForValue.build());
                return this;
            }

            public Builder addChatMsg(ChatMsg value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureChatMsgIsMutable();
                this.chatMsg_.add(value);
                return this;
            }

            public Builder addChatMsg(int index, ChatMsg value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureChatMsgIsMutable();
                this.chatMsg_.add(index, value);
                return this;
            }

            public Builder addChatMsg(Builder builderForValue) {
                ensureChatMsgIsMutable();
                this.chatMsg_.add(builderForValue.build());
                return this;
            }

            public Builder addChatMsg(int index, Builder builderForValue) {
                ensureChatMsgIsMutable();
                this.chatMsg_.add(index, builderForValue.build());
                return this;
            }

            public Builder addAllChatMsg(Iterable<? extends ChatMsg> values) {
                ensureChatMsgIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.chatMsg_);
                return this;
            }

            public Builder clearChatMsg() {
                this.chatMsg_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder removeChatMsg(int index) {
                ensureChatMsgIsMutable();
                this.chatMsg_.remove(index);
                return this;
            }
        }

        private ChatMsgSync(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ChatMsgSync(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ChatMsgSync getDefaultInstance() {
            return defaultInstance;
        }

        public ChatMsgSync getDefaultInstanceForType() {
            return defaultInstance;
        }

        public List<ChatMsg> getChatMsgList() {
            return this.chatMsg_;
        }

        public List<? extends ChatMsgOrBuilder> getChatMsgOrBuilderList() {
            return this.chatMsg_;
        }

        public int getChatMsgCount() {
            return this.chatMsg_.size();
        }

        public ChatMsg getChatMsg(int index) {
            return (ChatMsg) this.chatMsg_.get(index);
        }

        public ChatMsgOrBuilder getChatMsgOrBuilder(int index) {
            return (ChatMsgOrBuilder) this.chatMsg_.get(index);
        }

        private void initFields() {
            this.chatMsg_ = Collections.emptyList();
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
            for (int i = 0; i < this.chatMsg_.size(); i++) {
                output.writeMessage(1, (MessageLite) this.chatMsg_.get(i));
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.chatMsg_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.chatMsg_.get(i));
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ChatMsgSync parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ChatMsgSync parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ChatMsgSync parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ChatMsgSync parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ChatMsgSync parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ChatMsgSync parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ChatMsgSync parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ChatMsgSync parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ChatMsgSync parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ChatMsgSync parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ChatMsgSync prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$ChatMsgSyncOrBuilder */
    public interface ChatMsgSyncOrBuilder extends MessageLiteOrBuilder {
        ChatMsg getChatMsg(int i);

        int getChatMsgCount();

        List<ChatMsg> getChatMsgList();
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGlobal */
    public static final class DeleteMsgnoDisturbGlobal extends GeneratedMessageLite implements DeleteMsgnoDisturbGlobalOrBuilder {
        public static final int NONE_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final DeleteMsgnoDisturbGlobal defaultInstance = new DeleteMsgnoDisturbGlobal(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int none_;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGlobal$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DeleteMsgnoDisturbGlobal, Builder> implements DeleteMsgnoDisturbGlobalOrBuilder {
            private int bitField0_;
            private int none_;
            private long version_;

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
                this.none_ = 0;
                this.bitField0_ &= -2;
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DeleteMsgnoDisturbGlobal getDefaultInstanceForType() {
                return DeleteMsgnoDisturbGlobal.getDefaultInstance();
            }

            public DeleteMsgnoDisturbGlobal build() {
                DeleteMsgnoDisturbGlobal result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DeleteMsgnoDisturbGlobal buildParsed() throws InvalidProtocolBufferException {
                DeleteMsgnoDisturbGlobal result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DeleteMsgnoDisturbGlobal buildPartial() {
                DeleteMsgnoDisturbGlobal result = new DeleteMsgnoDisturbGlobal(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.none_ = this.none_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(DeleteMsgnoDisturbGlobal other) {
                if (other != DeleteMsgnoDisturbGlobal.getDefaultInstance()) {
                    if (other.hasNone()) {
                        setNone(other.getNone());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                            this.none_ = input.readInt32();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.version_ = input.readInt64();
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

            public boolean hasNone() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getNone() {
                return this.none_;
            }

            public Builder setNone(int value) {
                this.bitField0_ |= 1;
                this.none_ = value;
                return this;
            }

            public Builder clearNone() {
                this.bitField0_ &= -2;
                this.none_ = 0;
                return this;
            }

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private DeleteMsgnoDisturbGlobal(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DeleteMsgnoDisturbGlobal(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DeleteMsgnoDisturbGlobal getDefaultInstance() {
            return defaultInstance;
        }

        public DeleteMsgnoDisturbGlobal getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasNone() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getNone() {
            return this.none_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.none_ = 0;
            this.version_ = 0;
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
                output.writeInt32(1, this.none_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.version_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.none_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbGlobal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbGlobal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DeleteMsgnoDisturbGlobal prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGlobalOrBuilder */
    public interface DeleteMsgnoDisturbGlobalOrBuilder extends MessageLiteOrBuilder {
        int getNone();

        long getVersion();

        boolean hasNone();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGroup */
    public static final class DeleteMsgnoDisturbGroup extends GeneratedMessageLite implements DeleteMsgnoDisturbGroupOrBuilder {
        public static final int GID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final DeleteMsgnoDisturbGroup defaultInstance = new DeleteMsgnoDisturbGroup(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long gid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGroup$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DeleteMsgnoDisturbGroup, Builder> implements DeleteMsgnoDisturbGroupOrBuilder {
            private int bitField0_;
            private long gid_;
            private long version_;

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
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DeleteMsgnoDisturbGroup getDefaultInstanceForType() {
                return DeleteMsgnoDisturbGroup.getDefaultInstance();
            }

            public DeleteMsgnoDisturbGroup build() {
                DeleteMsgnoDisturbGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DeleteMsgnoDisturbGroup buildParsed() throws InvalidProtocolBufferException {
                DeleteMsgnoDisturbGroup result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DeleteMsgnoDisturbGroup buildPartial() {
                DeleteMsgnoDisturbGroup result = new DeleteMsgnoDisturbGroup(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(DeleteMsgnoDisturbGroup other) {
                if (other != DeleteMsgnoDisturbGroup.getDefaultInstance()) {
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                            this.version_ = input.readInt64();
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

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private DeleteMsgnoDisturbGroup(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DeleteMsgnoDisturbGroup(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DeleteMsgnoDisturbGroup getDefaultInstance() {
            return defaultInstance;
        }

        public DeleteMsgnoDisturbGroup getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.gid_ = 0;
            this.version_ = 0;
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
                output.writeInt64(2, this.version_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbGroup parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbGroup parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbGroup parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DeleteMsgnoDisturbGroup prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbGroupOrBuilder */
    public interface DeleteMsgnoDisturbGroupOrBuilder extends MessageLiteOrBuilder {
        long getGid();

        long getVersion();

        boolean hasGid();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbSingle */
    public static final class DeleteMsgnoDisturbSingle extends GeneratedMessageLite implements DeleteMsgnoDisturbSingleOrBuilder {
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final DeleteMsgnoDisturbSingle defaultInstance = new DeleteMsgnoDisturbSingle(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long targetUid_;
        /* access modifiers changed from: private */
        public long version_;

        /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbSingle$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<DeleteMsgnoDisturbSingle, Builder> implements DeleteMsgnoDisturbSingleOrBuilder {
            private int bitField0_;
            private long targetUid_;
            private long version_;

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
                this.version_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DeleteMsgnoDisturbSingle getDefaultInstanceForType() {
                return DeleteMsgnoDisturbSingle.getDefaultInstance();
            }

            public DeleteMsgnoDisturbSingle build() {
                DeleteMsgnoDisturbSingle result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public DeleteMsgnoDisturbSingle buildParsed() throws InvalidProtocolBufferException {
                DeleteMsgnoDisturbSingle result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public DeleteMsgnoDisturbSingle buildPartial() {
                DeleteMsgnoDisturbSingle result = new DeleteMsgnoDisturbSingle(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(DeleteMsgnoDisturbSingle other) {
                if (other != DeleteMsgnoDisturbSingle.getDefaultInstance()) {
                    if (other.hasTargetUid()) {
                        setTargetUid(other.getTargetUid());
                    }
                    if (other.hasVersion()) {
                        setVersion(other.getVersion());
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
                        case 16:
                            this.bitField0_ |= 2;
                            this.version_ = input.readInt64();
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

            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0;
                return this;
            }
        }

        private DeleteMsgnoDisturbSingle(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private DeleteMsgnoDisturbSingle(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static DeleteMsgnoDisturbSingle getDefaultInstance() {
            return defaultInstance;
        }

        public DeleteMsgnoDisturbSingle getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getVersion() {
            return this.version_;
        }

        private void initFields() {
            this.targetUid_ = 0;
            this.version_ = 0;
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
                output.writeInt64(2, this.version_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.version_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbSingle parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static DeleteMsgnoDisturbSingle parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static DeleteMsgnoDisturbSingle parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DeleteMsgnoDisturbSingle prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$DeleteMsgnoDisturbSingleOrBuilder */
    public interface DeleteMsgnoDisturbSingleOrBuilder extends MessageLiteOrBuilder {
        long getTargetUid();

        long getVersion();

        boolean hasTargetUid();

        boolean hasVersion();
    }

    /* renamed from: cn.jpush.im.proto.Message$EventAnswer */
    public static final class EventAnswer extends GeneratedMessageLite implements EventAnswerOrBuilder {
        public static final int ANSWER_FIELD_NUMBER = 3;
        public static final int EVENT_ID_FIELD_NUMBER = 1;
        public static final int EVENT_TYPE_FIELD_NUMBER = 2;
        private static final EventAnswer defaultInstance = new EventAnswer(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int answer_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long eventId_;
        /* access modifiers changed from: private */
        public int eventType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Message$EventAnswer$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<EventAnswer, Builder> implements EventAnswerOrBuilder {
            private int answer_;
            private int bitField0_;
            private long eventId_;
            private int eventType_;

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
                this.eventId_ = 0;
                this.bitField0_ &= -2;
                this.eventType_ = 0;
                this.bitField0_ &= -3;
                this.answer_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EventAnswer getDefaultInstanceForType() {
                return EventAnswer.getDefaultInstance();
            }

            public EventAnswer build() {
                EventAnswer result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public EventAnswer buildParsed() throws InvalidProtocolBufferException {
                EventAnswer result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public EventAnswer buildPartial() {
                EventAnswer result = new EventAnswer(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.eventId_ = this.eventId_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.eventType_ = this.eventType_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.answer_ = this.answer_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(EventAnswer other) {
                if (other != EventAnswer.getDefaultInstance()) {
                    if (other.hasEventId()) {
                        setEventId(other.getEventId());
                    }
                    if (other.hasEventType()) {
                        setEventType(other.getEventType());
                    }
                    if (other.hasAnswer()) {
                        setAnswer(other.getAnswer());
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
                            this.eventId_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.eventType_ = input.readInt32();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.answer_ = input.readInt32();
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

            public boolean hasEventId() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getEventId() {
                return this.eventId_;
            }

            public Builder setEventId(long value) {
                this.bitField0_ |= 1;
                this.eventId_ = value;
                return this;
            }

            public Builder clearEventId() {
                this.bitField0_ &= -2;
                this.eventId_ = 0;
                return this;
            }

            public boolean hasEventType() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getEventType() {
                return this.eventType_;
            }

            public Builder setEventType(int value) {
                this.bitField0_ |= 2;
                this.eventType_ = value;
                return this;
            }

            public Builder clearEventType() {
                this.bitField0_ &= -3;
                this.eventType_ = 0;
                return this;
            }

            public boolean hasAnswer() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getAnswer() {
                return this.answer_;
            }

            public Builder setAnswer(int value) {
                this.bitField0_ |= 4;
                this.answer_ = value;
                return this;
            }

            public Builder clearAnswer() {
                this.bitField0_ &= -5;
                this.answer_ = 0;
                return this;
            }
        }

        private EventAnswer(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private EventAnswer(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static EventAnswer getDefaultInstance() {
            return defaultInstance;
        }

        public EventAnswer getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasEventId() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getEventId() {
            return this.eventId_;
        }

        public boolean hasEventType() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getEventType() {
            return this.eventType_;
        }

        public boolean hasAnswer() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getAnswer() {
            return this.answer_;
        }

        private void initFields() {
            this.eventId_ = 0;
            this.eventType_ = 0;
            this.answer_ = 0;
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
                output.writeInt64(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.answer_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.answer_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EventAnswer parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventAnswer parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventAnswer parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventAnswer parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventAnswer parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventAnswer parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static EventAnswer parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventAnswer parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventAnswer parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventAnswer parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EventAnswer prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$EventAnswerOrBuilder */
    public interface EventAnswerOrBuilder extends MessageLiteOrBuilder {
        int getAnswer();

        long getEventId();

        int getEventType();

        boolean hasAnswer();

        boolean hasEventId();

        boolean hasEventType();
    }

    /* renamed from: cn.jpush.im.proto.Message$EventNotification */
    public static final class EventNotification extends GeneratedMessageLite implements EventNotificationOrBuilder {
        public static final int CTIME_FIELD_NUMBER = 7;
        public static final int DESCRIPTION_FIELD_NUMBER = 6;
        public static final int EVENT_ID_FIELD_NUMBER = 1;
        public static final int EVENT_TYPE_FIELD_NUMBER = 2;
        public static final int EXTRA_FIELD_NUMBER = 8;
        public static final int FROM_UID_FIELD_NUMBER = 3;
        public static final int GID_FIELD_NUMBER = 4;
        public static final int RETURN_CODE_FIELD_NUMBER = 9;
        public static final int TO_UIDLIST_FIELD_NUMBER = 5;
        private static final EventNotification defaultInstance = new EventNotification(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int ctime_;
        /* access modifiers changed from: private */
        public ByteString description_;
        /* access modifiers changed from: private */
        public long eventId_;
        /* access modifiers changed from: private */
        public int eventType_;
        /* access modifiers changed from: private */
        public int extra_;
        /* access modifiers changed from: private */
        public long fromUid_;
        /* access modifiers changed from: private */
        public long gid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int returnCode_;
        /* access modifiers changed from: private */
        public List<Long> toUidlist_;

        /* renamed from: cn.jpush.im.proto.Message$EventNotification$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<EventNotification, Builder> implements EventNotificationOrBuilder {
            private int bitField0_;
            private int ctime_;
            private ByteString description_ = ByteString.EMPTY;
            private long eventId_;
            private int eventType_;
            private int extra_;
            private long fromUid_;
            private long gid_;
            private int returnCode_;
            private List<Long> toUidlist_ = Collections.emptyList();

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
                this.eventId_ = 0;
                this.bitField0_ &= -2;
                this.eventType_ = 0;
                this.bitField0_ &= -3;
                this.fromUid_ = 0;
                this.bitField0_ &= -5;
                this.gid_ = 0;
                this.bitField0_ &= -9;
                this.toUidlist_ = Collections.emptyList();
                this.bitField0_ &= -17;
                this.description_ = ByteString.EMPTY;
                this.bitField0_ &= -33;
                this.ctime_ = 0;
                this.bitField0_ &= -65;
                this.extra_ = 0;
                this.bitField0_ &= -129;
                this.returnCode_ = 0;
                this.bitField0_ &= -257;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EventNotification getDefaultInstanceForType() {
                return EventNotification.getDefaultInstance();
            }

            public EventNotification build() {
                EventNotification result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public EventNotification buildParsed() throws InvalidProtocolBufferException {
                EventNotification result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public EventNotification buildPartial() {
                EventNotification result = new EventNotification(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.eventId_ = this.eventId_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.eventType_ = this.eventType_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.fromUid_ = this.fromUid_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.gid_ = this.gid_;
                if ((this.bitField0_ & 16) == 16) {
                    this.toUidlist_ = Collections.unmodifiableList(this.toUidlist_);
                    this.bitField0_ &= -17;
                }
                result.toUidlist_ = this.toUidlist_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 16;
                }
                result.description_ = this.description_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 32;
                }
                result.ctime_ = this.ctime_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 64;
                }
                result.extra_ = this.extra_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 128;
                }
                result.returnCode_ = this.returnCode_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(EventNotification other) {
                if (other != EventNotification.getDefaultInstance()) {
                    if (other.hasEventId()) {
                        setEventId(other.getEventId());
                    }
                    if (other.hasEventType()) {
                        setEventType(other.getEventType());
                    }
                    if (other.hasFromUid()) {
                        setFromUid(other.getFromUid());
                    }
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (!other.toUidlist_.isEmpty()) {
                        if (this.toUidlist_.isEmpty()) {
                            this.toUidlist_ = other.toUidlist_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureToUidlistIsMutable();
                            this.toUidlist_.addAll(other.toUidlist_);
                        }
                    }
                    if (other.hasDescription()) {
                        setDescription(other.getDescription());
                    }
                    if (other.hasCtime()) {
                        setCtime(other.getCtime());
                    }
                    if (other.hasExtra()) {
                        setExtra(other.getExtra());
                    }
                    if (other.hasReturnCode()) {
                        setReturnCode(other.getReturnCode());
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
                            this.eventId_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.eventType_ = input.readInt32();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.fromUid_ = input.readInt64();
                            continue;
                        case 32:
                            this.bitField0_ |= 8;
                            this.gid_ = input.readInt64();
                            continue;
                        case 40:
                            ensureToUidlistIsMutable();
                            this.toUidlist_.add(Long.valueOf(input.readInt64()));
                            continue;
                        case 42:
                            int limit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                addToUidlist(input.readInt64());
                            }
                            input.popLimit(limit);
                            continue;
                        case 50:
                            this.bitField0_ |= 32;
                            this.description_ = input.readBytes();
                            continue;
                        case 56:
                            this.bitField0_ |= 64;
                            this.ctime_ = input.readUInt32();
                            continue;
                        case 64:
                            this.bitField0_ |= 128;
                            this.extra_ = input.readInt32();
                            continue;
                        case 72:
                            this.bitField0_ |= 256;
                            this.returnCode_ = input.readInt32();
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

            public boolean hasEventId() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getEventId() {
                return this.eventId_;
            }

            public Builder setEventId(long value) {
                this.bitField0_ |= 1;
                this.eventId_ = value;
                return this;
            }

            public Builder clearEventId() {
                this.bitField0_ &= -2;
                this.eventId_ = 0;
                return this;
            }

            public boolean hasEventType() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getEventType() {
                return this.eventType_;
            }

            public Builder setEventType(int value) {
                this.bitField0_ |= 2;
                this.eventType_ = value;
                return this;
            }

            public Builder clearEventType() {
                this.bitField0_ &= -3;
                this.eventType_ = 0;
                return this;
            }

            public boolean hasFromUid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getFromUid() {
                return this.fromUid_;
            }

            public Builder setFromUid(long value) {
                this.bitField0_ |= 4;
                this.fromUid_ = value;
                return this;
            }

            public Builder clearFromUid() {
                this.bitField0_ &= -5;
                this.fromUid_ = 0;
                return this;
            }

            public boolean hasGid() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 8;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -9;
                this.gid_ = 0;
                return this;
            }

            private void ensureToUidlistIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.toUidlist_ = new ArrayList(this.toUidlist_);
                    this.bitField0_ |= 16;
                }
            }

            public List<Long> getToUidlistList() {
                return Collections.unmodifiableList(this.toUidlist_);
            }

            public int getToUidlistCount() {
                return this.toUidlist_.size();
            }

            public long getToUidlist(int index) {
                return ((Long) this.toUidlist_.get(index)).longValue();
            }

            public Builder setToUidlist(int index, long value) {
                ensureToUidlistIsMutable();
                this.toUidlist_.set(index, Long.valueOf(value));
                return this;
            }

            public Builder addToUidlist(long value) {
                ensureToUidlistIsMutable();
                this.toUidlist_.add(Long.valueOf(value));
                return this;
            }

            public Builder addAllToUidlist(Iterable<? extends Long> values) {
                ensureToUidlistIsMutable();
                com.google.protobuf.jpush.GeneratedMessageLite.Builder.addAll(values, this.toUidlist_);
                return this;
            }

            public Builder clearToUidlist() {
                this.toUidlist_ = Collections.emptyList();
                this.bitField0_ &= -17;
                return this;
            }

            public boolean hasDescription() {
                return (this.bitField0_ & 32) == 32;
            }

            public ByteString getDescription() {
                return this.description_;
            }

            public Builder setDescription(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.description_ = value;
                return this;
            }

            public Builder clearDescription() {
                this.bitField0_ &= -33;
                this.description_ = EventNotification.getDefaultInstance().getDescription();
                return this;
            }

            public boolean hasCtime() {
                return (this.bitField0_ & 64) == 64;
            }

            public int getCtime() {
                return this.ctime_;
            }

            public Builder setCtime(int value) {
                this.bitField0_ |= 64;
                this.ctime_ = value;
                return this;
            }

            public Builder clearCtime() {
                this.bitField0_ &= -65;
                this.ctime_ = 0;
                return this;
            }

            public boolean hasExtra() {
                return (this.bitField0_ & 128) == 128;
            }

            public int getExtra() {
                return this.extra_;
            }

            public Builder setExtra(int value) {
                this.bitField0_ |= 128;
                this.extra_ = value;
                return this;
            }

            public Builder clearExtra() {
                this.bitField0_ &= -129;
                this.extra_ = 0;
                return this;
            }

            public boolean hasReturnCode() {
                return (this.bitField0_ & 256) == 256;
            }

            public int getReturnCode() {
                return this.returnCode_;
            }

            public Builder setReturnCode(int value) {
                this.bitField0_ |= 256;
                this.returnCode_ = value;
                return this;
            }

            public Builder clearReturnCode() {
                this.bitField0_ &= -257;
                this.returnCode_ = 0;
                return this;
            }
        }

        private EventNotification(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private EventNotification(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static EventNotification getDefaultInstance() {
            return defaultInstance;
        }

        public EventNotification getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasEventId() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getEventId() {
            return this.eventId_;
        }

        public boolean hasEventType() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getEventType() {
            return this.eventType_;
        }

        public boolean hasFromUid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getFromUid() {
            return this.fromUid_;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getGid() {
            return this.gid_;
        }

        public List<Long> getToUidlistList() {
            return this.toUidlist_;
        }

        public int getToUidlistCount() {
            return this.toUidlist_.size();
        }

        public long getToUidlist(int index) {
            return ((Long) this.toUidlist_.get(index)).longValue();
        }

        public boolean hasDescription() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getDescription() {
            return this.description_;
        }

        public boolean hasCtime() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getCtime() {
            return this.ctime_;
        }

        public boolean hasExtra() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getExtra() {
            return this.extra_;
        }

        public boolean hasReturnCode() {
            return (this.bitField0_ & 128) == 128;
        }

        public int getReturnCode() {
            return this.returnCode_;
        }

        private void initFields() {
            this.eventId_ = 0;
            this.eventType_ = 0;
            this.fromUid_ = 0;
            this.gid_ = 0;
            this.toUidlist_ = Collections.emptyList();
            this.description_ = ByteString.EMPTY;
            this.ctime_ = 0;
            this.extra_ = 0;
            this.returnCode_ = 0;
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
                output.writeInt64(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.fromUid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.gid_);
            }
            for (int i = 0; i < this.toUidlist_.size(); i++) {
                output.writeInt64(5, ((Long) this.toUidlist_.get(i)).longValue());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(6, this.description_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeUInt32(7, this.ctime_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(8, this.extra_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(9, this.returnCode_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.fromUid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.gid_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.toUidlist_.size(); i++) {
                dataSize += CodedOutputStream.computeInt64SizeNoTag(((Long) this.toUidlist_.get(i)).longValue());
            }
            int size3 = size2 + dataSize + (getToUidlistList().size() * 1);
            if ((this.bitField0_ & 16) == 16) {
                size3 += CodedOutputStream.computeBytesSize(6, this.description_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size3 += CodedOutputStream.computeUInt32Size(7, this.ctime_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size3 += CodedOutputStream.computeInt32Size(8, this.extra_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size3 += CodedOutputStream.computeInt32Size(9, this.returnCode_);
            }
            this.memoizedSerializedSize = size3;
            return size3;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EventNotification parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventNotification parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventNotification parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventNotification parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventNotification parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventNotification parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static EventNotification parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventNotification parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventNotification parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventNotification parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EventNotification prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$EventNotificationOrBuilder */
    public interface EventNotificationOrBuilder extends MessageLiteOrBuilder {
        int getCtime();

        ByteString getDescription();

        long getEventId();

        int getEventType();

        int getExtra();

        long getFromUid();

        long getGid();

        int getReturnCode();

        long getToUidlist(int i);

        int getToUidlistCount();

        List<Long> getToUidlistList();

        boolean hasCtime();

        boolean hasDescription();

        boolean hasEventId();

        boolean hasEventType();

        boolean hasExtra();

        boolean hasFromUid();

        boolean hasGid();

        boolean hasReturnCode();
    }

    /* renamed from: cn.jpush.im.proto.Message$EventSync */
    public static final class EventSync extends GeneratedMessageLite implements EventSyncOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 5;
        public static final int EVENT_ID_FIELD_NUMBER = 1;
        public static final int EVENT_TYPE_FIELD_NUMBER = 2;
        public static final int FROM_UID_FIELD_NUMBER = 3;
        public static final int GID_FIELD_NUMBER = 4;
        private static final EventSync defaultInstance = new EventSync(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString description_;
        /* access modifiers changed from: private */
        public long eventId_;
        /* access modifiers changed from: private */
        public int eventType_;
        /* access modifiers changed from: private */
        public long fromUid_;
        /* access modifiers changed from: private */
        public long gid_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;

        /* renamed from: cn.jpush.im.proto.Message$EventSync$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<EventSync, Builder> implements EventSyncOrBuilder {
            private int bitField0_;
            private ByteString description_ = ByteString.EMPTY;
            private long eventId_;
            private int eventType_;
            private long fromUid_;
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
                this.eventId_ = 0;
                this.bitField0_ &= -2;
                this.eventType_ = 0;
                this.bitField0_ &= -3;
                this.fromUid_ = 0;
                this.bitField0_ &= -5;
                this.gid_ = 0;
                this.bitField0_ &= -9;
                this.description_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EventSync getDefaultInstanceForType() {
                return EventSync.getDefaultInstance();
            }

            public EventSync build() {
                EventSync result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public EventSync buildParsed() throws InvalidProtocolBufferException {
                EventSync result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public EventSync buildPartial() {
                EventSync result = new EventSync(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.eventId_ = this.eventId_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.eventType_ = this.eventType_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.fromUid_ = this.fromUid_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.gid_ = this.gid_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.description_ = this.description_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(EventSync other) {
                if (other != EventSync.getDefaultInstance()) {
                    if (other.hasEventId()) {
                        setEventId(other.getEventId());
                    }
                    if (other.hasEventType()) {
                        setEventType(other.getEventType());
                    }
                    if (other.hasFromUid()) {
                        setFromUid(other.getFromUid());
                    }
                    if (other.hasGid()) {
                        setGid(other.getGid());
                    }
                    if (other.hasDescription()) {
                        setDescription(other.getDescription());
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
                            this.eventId_ = input.readInt64();
                            continue;
                        case 16:
                            this.bitField0_ |= 2;
                            this.eventType_ = input.readInt32();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.fromUid_ = input.readInt64();
                            continue;
                        case 32:
                            this.bitField0_ |= 8;
                            this.gid_ = input.readInt64();
                            continue;
                        case 42:
                            this.bitField0_ |= 16;
                            this.description_ = input.readBytes();
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

            public boolean hasEventId() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getEventId() {
                return this.eventId_;
            }

            public Builder setEventId(long value) {
                this.bitField0_ |= 1;
                this.eventId_ = value;
                return this;
            }

            public Builder clearEventId() {
                this.bitField0_ &= -2;
                this.eventId_ = 0;
                return this;
            }

            public boolean hasEventType() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getEventType() {
                return this.eventType_;
            }

            public Builder setEventType(int value) {
                this.bitField0_ |= 2;
                this.eventType_ = value;
                return this;
            }

            public Builder clearEventType() {
                this.bitField0_ &= -3;
                this.eventType_ = 0;
                return this;
            }

            public boolean hasFromUid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getFromUid() {
                return this.fromUid_;
            }

            public Builder setFromUid(long value) {
                this.bitField0_ |= 4;
                this.fromUid_ = value;
                return this;
            }

            public Builder clearFromUid() {
                this.bitField0_ &= -5;
                this.fromUid_ = 0;
                return this;
            }

            public boolean hasGid() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getGid() {
                return this.gid_;
            }

            public Builder setGid(long value) {
                this.bitField0_ |= 8;
                this.gid_ = value;
                return this;
            }

            public Builder clearGid() {
                this.bitField0_ &= -9;
                this.gid_ = 0;
                return this;
            }

            public boolean hasDescription() {
                return (this.bitField0_ & 16) == 16;
            }

            public ByteString getDescription() {
                return this.description_;
            }

            public Builder setDescription(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.description_ = value;
                return this;
            }

            public Builder clearDescription() {
                this.bitField0_ &= -17;
                this.description_ = EventSync.getDefaultInstance().getDescription();
                return this;
            }
        }

        private EventSync(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private EventSync(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static EventSync getDefaultInstance() {
            return defaultInstance;
        }

        public EventSync getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasEventId() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getEventId() {
            return this.eventId_;
        }

        public boolean hasEventType() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getEventType() {
            return this.eventType_;
        }

        public boolean hasFromUid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getFromUid() {
            return this.fromUid_;
        }

        public boolean hasGid() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getGid() {
            return this.gid_;
        }

        public boolean hasDescription() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getDescription() {
            return this.description_;
        }

        private void initFields() {
            this.eventId_ = 0;
            this.eventType_ = 0;
            this.fromUid_ = 0;
            this.gid_ = 0;
            this.description_ = ByteString.EMPTY;
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
                output.writeInt64(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.fromUid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.gid_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, this.description_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.eventType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.fromUid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.gid_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, this.description_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EventSync parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventSync parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventSync parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static EventSync parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static EventSync parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventSync parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static EventSync parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventSync parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static EventSync parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static EventSync parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EventSync prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$EventSyncOrBuilder */
    public interface EventSyncOrBuilder extends MessageLiteOrBuilder {
        ByteString getDescription();

        long getEventId();

        int getEventType();

        long getFromUid();

        long getGid();

        boolean hasDescription();

        boolean hasEventId();

        boolean hasEventType();

        boolean hasFromUid();

        boolean hasGid();
    }

    /* renamed from: cn.jpush.im.proto.Message$GroupMsg */
    public static final class GroupMsg extends GeneratedMessageLite implements GroupMsgOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 2;
        public static final int MSGID_FIELD_NUMBER = 3;
        public static final int TARGET_GID_FIELD_NUMBER = 1;
        private static final GroupMsg defaultInstance = new GroupMsg(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public MessageContent content_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long msgid_;
        /* access modifiers changed from: private */
        public long targetGid_;

        /* renamed from: cn.jpush.im.proto.Message$GroupMsg$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<GroupMsg, Builder> implements GroupMsgOrBuilder {
            private int bitField0_;
            private MessageContent content_ = MessageContent.getDefaultInstance();
            private long msgid_;
            private long targetGid_;

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
                this.targetGid_ = 0;
                this.bitField0_ &= -2;
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -3;
                this.msgid_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public GroupMsg getDefaultInstanceForType() {
                return GroupMsg.getDefaultInstance();
            }

            public GroupMsg build() {
                GroupMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public GroupMsg buildParsed() throws InvalidProtocolBufferException {
                GroupMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public GroupMsg buildPartial() {
                GroupMsg result = new GroupMsg(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetGid_ = this.targetGid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.content_ = this.content_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.msgid_ = this.msgid_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(GroupMsg other) {
                if (other != GroupMsg.getDefaultInstance()) {
                    if (other.hasTargetGid()) {
                        setTargetGid(other.getTargetGid());
                    }
                    if (other.hasContent()) {
                        mergeContent(other.getContent());
                    }
                    if (other.hasMsgid()) {
                        setMsgid(other.getMsgid());
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
                            this.targetGid_ = input.readInt64();
                            continue;
                        case 18:
                            Builder subBuilder = MessageContent.newBuilder();
                            if (hasContent()) {
                                subBuilder.mergeFrom(getContent());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setContent(subBuilder.buildPartial());
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.msgid_ = input.readInt64();
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

            public boolean hasTargetGid() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getTargetGid() {
                return this.targetGid_;
            }

            public Builder setTargetGid(long value) {
                this.bitField0_ |= 1;
                this.targetGid_ = value;
                return this;
            }

            public Builder clearTargetGid() {
                this.bitField0_ &= -2;
                this.targetGid_ = 0;
                return this;
            }

            public boolean hasContent() {
                return (this.bitField0_ & 2) == 2;
            }

            public MessageContent getContent() {
                return this.content_;
            }

            public Builder setContent(MessageContent value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.content_ = value;
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setContent(Builder builderForValue) {
                this.content_ = builderForValue.build();
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeContent(MessageContent value) {
                if ((this.bitField0_ & 2) != 2 || this.content_ == MessageContent.getDefaultInstance()) {
                    this.content_ = value;
                } else {
                    this.content_ = MessageContent.newBuilder(this.content_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearContent() {
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }

            public boolean hasMsgid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getMsgid() {
                return this.msgid_;
            }

            public Builder setMsgid(long value) {
                this.bitField0_ |= 4;
                this.msgid_ = value;
                return this;
            }

            public Builder clearMsgid() {
                this.bitField0_ &= -5;
                this.msgid_ = 0;
                return this;
            }
        }

        private GroupMsg(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private GroupMsg(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static GroupMsg getDefaultInstance() {
            return defaultInstance;
        }

        public GroupMsg getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetGid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetGid() {
            return this.targetGid_;
        }

        public boolean hasContent() {
            return (this.bitField0_ & 2) == 2;
        }

        public MessageContent getContent() {
            return this.content_;
        }

        public boolean hasMsgid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getMsgid() {
            return this.msgid_;
        }

        private void initFields() {
            this.targetGid_ = 0;
            this.content_ = MessageContent.getDefaultInstance();
            this.msgid_ = 0;
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
                output.writeInt64(1, this.targetGid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.content_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.msgid_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.targetGid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, this.content_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.msgid_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static GroupMsg parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static GroupMsg parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static GroupMsg parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static GroupMsg parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static GroupMsg parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static GroupMsg parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static GroupMsg parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static GroupMsg parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static GroupMsg parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static GroupMsg parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(GroupMsg prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$GroupMsgOrBuilder */
    public interface GroupMsgOrBuilder extends MessageLiteOrBuilder {
        MessageContent getContent();

        long getMsgid();

        long getTargetGid();

        boolean hasContent();

        boolean hasMsgid();

        boolean hasTargetGid();
    }

    /* renamed from: cn.jpush.im.proto.Message$MessageContent */
    public static final class MessageContent extends GeneratedMessageLite implements MessageContentOrBuilder {
        public static final int ANDROID_PACKAGE_FIELD_NUMBER = 2;
        public static final int CONTENT_FIELD_NUMBER = 1;
        public static final int NOTIFICATION_FIELD_NUMBER = 3;
        private static final MessageContent defaultInstance = new MessageContent(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public ByteString androidPackage_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString content_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString notification_;

        /* renamed from: cn.jpush.im.proto.Message$MessageContent$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<MessageContent, Builder> implements MessageContentOrBuilder {
            private ByteString androidPackage_ = ByteString.EMPTY;
            private int bitField0_;
            private ByteString content_ = ByteString.EMPTY;
            private ByteString notification_ = ByteString.EMPTY;

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
                this.content_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.androidPackage_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.notification_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public MessageContent getDefaultInstanceForType() {
                return MessageContent.getDefaultInstance();
            }

            public MessageContent build() {
                MessageContent result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public MessageContent buildParsed() throws InvalidProtocolBufferException {
                MessageContent result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public MessageContent buildPartial() {
                MessageContent result = new MessageContent(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.content_ = this.content_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.androidPackage_ = this.androidPackage_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.notification_ = this.notification_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(MessageContent other) {
                if (other != MessageContent.getDefaultInstance()) {
                    if (other.hasContent()) {
                        setContent(other.getContent());
                    }
                    if (other.hasAndroidPackage()) {
                        setAndroidPackage(other.getAndroidPackage());
                    }
                    if (other.hasNotification()) {
                        setNotification(other.getNotification());
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
                            this.content_ = input.readBytes();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.androidPackage_ = input.readBytes();
                            continue;
                        case 26:
                            this.bitField0_ |= 4;
                            this.notification_ = input.readBytes();
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

            public boolean hasContent() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getContent() {
                return this.content_;
            }

            public Builder setContent(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.content_ = value;
                return this;
            }

            public Builder clearContent() {
                this.bitField0_ &= -2;
                this.content_ = MessageContent.getDefaultInstance().getContent();
                return this;
            }

            public boolean hasAndroidPackage() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getAndroidPackage() {
                return this.androidPackage_;
            }

            public Builder setAndroidPackage(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.androidPackage_ = value;
                return this;
            }

            public Builder clearAndroidPackage() {
                this.bitField0_ &= -3;
                this.androidPackage_ = MessageContent.getDefaultInstance().getAndroidPackage();
                return this;
            }

            public boolean hasNotification() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getNotification() {
                return this.notification_;
            }

            public Builder setNotification(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.notification_ = value;
                return this;
            }

            public Builder clearNotification() {
                this.bitField0_ &= -5;
                this.notification_ = MessageContent.getDefaultInstance().getNotification();
                return this;
            }
        }

        private MessageContent(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private MessageContent(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static MessageContent getDefaultInstance() {
            return defaultInstance;
        }

        public MessageContent getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasContent() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getContent() {
            return this.content_;
        }

        public boolean hasAndroidPackage() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getAndroidPackage() {
            return this.androidPackage_;
        }

        public boolean hasNotification() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getNotification() {
            return this.notification_;
        }

        private void initFields() {
            this.content_ = ByteString.EMPTY;
            this.androidPackage_ = ByteString.EMPTY;
            this.notification_ = ByteString.EMPTY;
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
                output.writeBytes(1, this.content_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.androidPackage_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.notification_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.content_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.androidPackage_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, this.notification_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MessageContent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static MessageContent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static MessageContent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static MessageContent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static MessageContent parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static MessageContent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static MessageContent parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static MessageContent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static MessageContent parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static MessageContent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(MessageContent prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$MessageContentOrBuilder */
    public interface MessageContentOrBuilder extends MessageLiteOrBuilder {
        ByteString getAndroidPackage();

        ByteString getContent();

        ByteString getNotification();

        boolean hasAndroidPackage();

        boolean hasContent();

        boolean hasNotification();
    }

    /* renamed from: cn.jpush.im.proto.Message$SingleMsg */
    public static final class SingleMsg extends GeneratedMessageLite implements SingleMsgOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 2;
        public static final int MSGID_FIELD_NUMBER = 3;
        public static final int TARGET_UID_FIELD_NUMBER = 1;
        private static final SingleMsg defaultInstance = new SingleMsg(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public MessageContent content_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long msgid_;
        /* access modifiers changed from: private */
        public long targetUid_;

        /* renamed from: cn.jpush.im.proto.Message$SingleMsg$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<SingleMsg, Builder> implements SingleMsgOrBuilder {
            private int bitField0_;
            private MessageContent content_ = MessageContent.getDefaultInstance();
            private long msgid_;
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
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -3;
                this.msgid_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public SingleMsg getDefaultInstanceForType() {
                return SingleMsg.getDefaultInstance();
            }

            public SingleMsg build() {
                SingleMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public SingleMsg buildParsed() throws InvalidProtocolBufferException {
                SingleMsg result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public SingleMsg buildPartial() {
                SingleMsg result = new SingleMsg(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.targetUid_ = this.targetUid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.content_ = this.content_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.msgid_ = this.msgid_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(SingleMsg other) {
                if (other != SingleMsg.getDefaultInstance()) {
                    if (other.hasTargetUid()) {
                        setTargetUid(other.getTargetUid());
                    }
                    if (other.hasContent()) {
                        mergeContent(other.getContent());
                    }
                    if (other.hasMsgid()) {
                        setMsgid(other.getMsgid());
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
                            Builder subBuilder = MessageContent.newBuilder();
                            if (hasContent()) {
                                subBuilder.mergeFrom(getContent());
                            }
                            input.readMessage(subBuilder, extensionRegistry);
                            setContent(subBuilder.buildPartial());
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.msgid_ = input.readInt64();
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

            public boolean hasContent() {
                return (this.bitField0_ & 2) == 2;
            }

            public MessageContent getContent() {
                return this.content_;
            }

            public Builder setContent(MessageContent value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.content_ = value;
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setContent(Builder builderForValue) {
                this.content_ = builderForValue.build();
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeContent(MessageContent value) {
                if ((this.bitField0_ & 2) != 2 || this.content_ == MessageContent.getDefaultInstance()) {
                    this.content_ = value;
                } else {
                    this.content_ = MessageContent.newBuilder(this.content_).mergeFrom(value).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearContent() {
                this.content_ = MessageContent.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }

            public boolean hasMsgid() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getMsgid() {
                return this.msgid_;
            }

            public Builder setMsgid(long value) {
                this.bitField0_ |= 4;
                this.msgid_ = value;
                return this;
            }

            public Builder clearMsgid() {
                this.bitField0_ &= -5;
                this.msgid_ = 0;
                return this;
            }
        }

        private SingleMsg(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private SingleMsg(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static SingleMsg getDefaultInstance() {
            return defaultInstance;
        }

        public SingleMsg getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasTargetUid() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTargetUid() {
            return this.targetUid_;
        }

        public boolean hasContent() {
            return (this.bitField0_ & 2) == 2;
        }

        public MessageContent getContent() {
            return this.content_;
        }

        public boolean hasMsgid() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getMsgid() {
            return this.msgid_;
        }

        private void initFields() {
            this.targetUid_ = 0;
            this.content_ = MessageContent.getDefaultInstance();
            this.msgid_ = 0;
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
                output.writeMessage(2, this.content_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.msgid_);
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
                size2 += CodedOutputStream.computeMessageSize(2, this.content_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.msgid_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static SingleMsg parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static SingleMsg parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static SingleMsg parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static SingleMsg parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static SingleMsg parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static SingleMsg parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static SingleMsg parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static SingleMsg parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static SingleMsg parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static SingleMsg parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(SingleMsg prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.Message$SingleMsgOrBuilder */
    public interface SingleMsgOrBuilder extends MessageLiteOrBuilder {
        MessageContent getContent();

        long getMsgid();

        long getTargetUid();

        boolean hasContent();

        boolean hasMsgid();

        boolean hasTargetUid();
    }

    private C1623Message() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }
}
