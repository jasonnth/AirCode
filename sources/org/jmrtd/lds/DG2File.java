package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;
import org.jmrtd.cbeff.BiometricDataBlock;
import org.jmrtd.cbeff.BiometricDataBlockDecoder;
import org.jmrtd.cbeff.BiometricDataBlockEncoder;
import org.jmrtd.cbeff.CBEFFInfo;
import org.jmrtd.cbeff.ComplexCBEFFInfo;
import org.jmrtd.cbeff.ISO781611Decoder;
import org.jmrtd.cbeff.ISO781611Encoder;
import org.jmrtd.cbeff.SimpleCBEFFInfo;
import org.jmrtd.cbeff.StandardBiometricHeader;

public class DG2File extends CBEFFDataGroup<FaceInfo> {
    private static final ISO781611Decoder DECODER = new ISO781611Decoder(new BiometricDataBlockDecoder<FaceInfo>() {
        public FaceInfo decode(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i, int i2) throws IOException {
            return new FaceInfo(standardBiometricHeader, inputStream);
        }
    });
    private static final ISO781611Encoder<FaceInfo> ENCODER = new ISO781611Encoder<>(new BiometricDataBlockEncoder<FaceInfo>() {
        public void encode(FaceInfo faceInfo, OutputStream outputStream) throws IOException {
            faceInfo.writeObject(outputStream);
        }
    });
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 414300652684010416L;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public DG2File(List<FaceInfo> list) {
        super((int) LDSFile.EF_DG2_TAG, list);
    }

    public DG2File(InputStream inputStream) throws IOException {
        super((int) LDSFile.EF_DG2_TAG, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        for (CBEFFInfo cBEFFInfo : DECODER.decode(inputStream).getSubRecords()) {
            if (!(cBEFFInfo instanceof SimpleCBEFFInfo)) {
                throw new IOException("Was expecting a SimpleCBEFFInfo, found " + cBEFFInfo.getClass().getSimpleName());
            }
            BiometricDataBlock biometricDataBlock = ((SimpleCBEFFInfo) cBEFFInfo).getBiometricDataBlock();
            if (!(biometricDataBlock instanceof FaceInfo)) {
                throw new IOException("Was expecting a FaceInfo, found " + biometricDataBlock.getClass().getSimpleName());
            }
            add((FaceInfo) biometricDataBlock);
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        for (FaceInfo simpleCBEFFInfo : getSubRecords()) {
            complexCBEFFInfo.add(new SimpleCBEFFInfo(simpleCBEFFInfo));
        }
        ENCODER.encode(complexCBEFFInfo, outputStream);
    }

    public String toString() {
        return "DG2File [" + super.toString() + "]";
    }

    public List<FaceInfo> getFaceInfos() {
        return getSubRecords();
    }

    public void addFaceInfo(FaceInfo faceInfo) {
        add(faceInfo);
    }

    public void removeFaceInfo(int i) {
        remove(i);
    }
}
