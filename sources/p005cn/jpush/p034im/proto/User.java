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

/* renamed from: cn.jpush.im.proto.User */
public final class User {

    /* renamed from: cn.jpush.im.proto.User$Login */
    public static final class Login extends GeneratedMessageLite implements LoginOrBuilder {
        public static final int PASSWORD_FIELD_NUMBER = 2;
        public static final int PLATFORM_FIELD_NUMBER = 3;
        public static final int SDK_VERSION_FIELD_NUMBER = 4;
        public static final int USERNAME_FIELD_NUMBER = 1;
        private static final Login defaultInstance = new Login(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString password_;
        /* access modifiers changed from: private */
        public int platform_;
        /* access modifiers changed from: private */
        public ByteString sdkVersion_;
        /* access modifiers changed from: private */
        public ByteString username_;

        /* renamed from: cn.jpush.im.proto.User$Login$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<Login, Builder> implements LoginOrBuilder {
            private int bitField0_;
            private ByteString password_ = ByteString.EMPTY;
            private int platform_;
            private ByteString sdkVersion_ = ByteString.EMPTY;
            private ByteString username_ = ByteString.EMPTY;

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
                this.username_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.password_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.platform_ = 0;
                this.bitField0_ &= -5;
                this.sdkVersion_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Login getDefaultInstanceForType() {
                return Login.getDefaultInstance();
            }

            public Login build() {
                Login result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public Login buildParsed() throws InvalidProtocolBufferException {
                Login result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public Login buildPartial() {
                Login result = new Login(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.username_ = this.username_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.password_ = this.password_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.platform_ = this.platform_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.sdkVersion_ = this.sdkVersion_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Login other) {
                if (other != Login.getDefaultInstance()) {
                    if (other.hasUsername()) {
                        setUsername(other.getUsername());
                    }
                    if (other.hasPassword()) {
                        setPassword(other.getPassword());
                    }
                    if (other.hasPlatform()) {
                        setPlatform(other.getPlatform());
                    }
                    if (other.hasSdkVersion()) {
                        setSdkVersion(other.getSdkVersion());
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
                            this.username_ = input.readBytes();
                            continue;
                        case 18:
                            this.bitField0_ |= 2;
                            this.password_ = input.readBytes();
                            continue;
                        case 24:
                            this.bitField0_ |= 4;
                            this.platform_ = input.readInt32();
                            continue;
                        case 34:
                            this.bitField0_ |= 8;
                            this.sdkVersion_ = input.readBytes();
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

            public boolean hasUsername() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getUsername() {
                return this.username_;
            }

            public Builder setUsername(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.username_ = value;
                return this;
            }

            public Builder clearUsername() {
                this.bitField0_ &= -2;
                this.username_ = Login.getDefaultInstance().getUsername();
                return this;
            }

            public boolean hasPassword() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getPassword() {
                return this.password_;
            }

            public Builder setPassword(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.password_ = value;
                return this;
            }

            public Builder clearPassword() {
                this.bitField0_ &= -3;
                this.password_ = Login.getDefaultInstance().getPassword();
                return this;
            }

            public boolean hasPlatform() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getPlatform() {
                return this.platform_;
            }

            public Builder setPlatform(int value) {
                this.bitField0_ |= 4;
                this.platform_ = value;
                return this;
            }

            public Builder clearPlatform() {
                this.bitField0_ &= -5;
                this.platform_ = 0;
                return this;
            }

            public boolean hasSdkVersion() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getSdkVersion() {
                return this.sdkVersion_;
            }

            public Builder setSdkVersion(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.sdkVersion_ = value;
                return this;
            }

            public Builder clearSdkVersion() {
                this.bitField0_ &= -9;
                this.sdkVersion_ = Login.getDefaultInstance().getSdkVersion();
                return this;
            }
        }

        private Login(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private Login(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static Login getDefaultInstance() {
            return defaultInstance;
        }

        public Login getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasUsername() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getUsername() {
            return this.username_;
        }

        public boolean hasPassword() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getPassword() {
            return this.password_;
        }

        public boolean hasPlatform() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getPlatform() {
            return this.platform_;
        }

        public boolean hasSdkVersion() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getSdkVersion() {
            return this.sdkVersion_;
        }

        private void initFields() {
            this.username_ = ByteString.EMPTY;
            this.password_ = ByteString.EMPTY;
            this.platform_ = 0;
            this.sdkVersion_ = ByteString.EMPTY;
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
                output.writeBytes(1, this.username_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.password_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.platform_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.sdkVersion_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.username_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.password_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.platform_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, this.sdkVersion_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Login parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Login parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Login parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Login parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Login parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Login parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static Login parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Login parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Login parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Login parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Login prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.User$LoginOrBuilder */
    public interface LoginOrBuilder extends MessageLiteOrBuilder {
        ByteString getPassword();

        int getPlatform();

        ByteString getSdkVersion();

        ByteString getUsername();

        boolean hasPassword();

        boolean hasPlatform();

        boolean hasSdkVersion();

        boolean hasUsername();
    }

    /* renamed from: cn.jpush.im.proto.User$Logout */
    public static final class Logout extends GeneratedMessageLite implements LogoutOrBuilder {
        public static final int USERNAME_FIELD_NUMBER = 1;
        private static final Logout defaultInstance = new Logout(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString username_;

        /* renamed from: cn.jpush.im.proto.User$Logout$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<Logout, Builder> implements LogoutOrBuilder {
            private int bitField0_;
            private ByteString username_ = ByteString.EMPTY;

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
                this.username_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Logout getDefaultInstanceForType() {
                return Logout.getDefaultInstance();
            }

            public Logout build() {
                Logout result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public Logout buildParsed() throws InvalidProtocolBufferException {
                Logout result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public Logout buildPartial() {
                Logout result = new Logout(this);
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.username_ = this.username_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Logout other) {
                if (other != Logout.getDefaultInstance() && other.hasUsername()) {
                    setUsername(other.getUsername());
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
                            this.username_ = input.readBytes();
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

            public boolean hasUsername() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getUsername() {
                return this.username_;
            }

            public Builder setUsername(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.username_ = value;
                return this;
            }

            public Builder clearUsername() {
                this.bitField0_ &= -2;
                this.username_ = Logout.getDefaultInstance().getUsername();
                return this;
            }
        }

        private Logout(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private Logout(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static Logout getDefaultInstance() {
            return defaultInstance;
        }

        public Logout getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasUsername() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getUsername() {
            return this.username_;
        }

        private void initFields() {
            this.username_ = ByteString.EMPTY;
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
                output.writeBytes(1, this.username_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.username_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Logout parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Logout parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Logout parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static Logout parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static Logout parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Logout parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static Logout parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Logout parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static Logout parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static Logout parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Logout prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.User$LogoutOrBuilder */
    public interface LogoutOrBuilder extends MessageLiteOrBuilder {
        ByteString getUsername();

        boolean hasUsername();
    }

    /* renamed from: cn.jpush.im.proto.User$ReportInformation */
    public static final class ReportInformation extends GeneratedMessageLite implements ReportInformationOrBuilder {
        public static final int SDK_VERSION_FIELD_NUMBER = 1;
        private static final ReportInformation defaultInstance = new ReportInformation(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString sdkVersion_;

        /* renamed from: cn.jpush.im.proto.User$ReportInformation$Builder */
        public static final class Builder extends com.google.protobuf.jpush.GeneratedMessageLite.Builder<ReportInformation, Builder> implements ReportInformationOrBuilder {
            private int bitField0_;
            private ByteString sdkVersion_ = ByteString.EMPTY;

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
                this.sdkVersion_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ReportInformation getDefaultInstanceForType() {
                return ReportInformation.getDefaultInstance();
            }

            public ReportInformation build() {
                ReportInformation result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result);
            }

            /* access modifiers changed from: private */
            public ReportInformation buildParsed() throws InvalidProtocolBufferException {
                ReportInformation result = buildPartial();
                if (result.isInitialized()) {
                    return result;
                }
                throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
            }

            public ReportInformation buildPartial() {
                ReportInformation result = new ReportInformation(this);
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.sdkVersion_ = this.sdkVersion_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ReportInformation other) {
                if (other != ReportInformation.getDefaultInstance() && other.hasSdkVersion()) {
                    setSdkVersion(other.getSdkVersion());
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
                            this.sdkVersion_ = input.readBytes();
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

            public boolean hasSdkVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getSdkVersion() {
                return this.sdkVersion_;
            }

            public Builder setSdkVersion(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.sdkVersion_ = value;
                return this;
            }

            public Builder clearSdkVersion() {
                this.bitField0_ &= -2;
                this.sdkVersion_ = ReportInformation.getDefaultInstance().getSdkVersion();
                return this;
            }
        }

        private ReportInformation(Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        private ReportInformation(boolean noInit) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
        }

        public static ReportInformation getDefaultInstance() {
            return defaultInstance;
        }

        public ReportInformation getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean hasSdkVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getSdkVersion() {
            return this.sdkVersion_;
        }

        private void initFields() {
            this.sdkVersion_ = ByteString.EMPTY;
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
                output.writeBytes(1, this.sdkVersion_);
            }
        }

        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.sdkVersion_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ReportInformation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ReportInformation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ReportInformation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data)).buildParsed();
        }

        public static ReportInformation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return ((Builder) newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
        }

        public static ReportInformation parseFrom(InputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ReportInformation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
        }

        public static ReportInformation parseDelimitedFrom(InputStream input) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ReportInformation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            }
            return null;
        }

        public static ReportInformation parseFrom(CodedInputStream input) throws IOException {
            return ((Builder) newBuilder().mergeFrom(input)).buildParsed();
        }

        public static ReportInformation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ReportInformation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        static {
            defaultInstance.initFields();
        }
    }

    /* renamed from: cn.jpush.im.proto.User$ReportInformationOrBuilder */
    public interface ReportInformationOrBuilder extends MessageLiteOrBuilder {
        ByteString getSdkVersion();

        boolean hasSdkVersion();
    }

    private User() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }
}
