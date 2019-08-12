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

public class DG3File extends CBEFFDataGroup<FingerInfo> {
    private static final ISO781611Decoder DECODER = new ISO781611Decoder(new BiometricDataBlockDecoder<FingerInfo>() {
        public FingerInfo decode(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i, int i2) throws IOException {
            return new FingerInfo(standardBiometricHeader, inputStream);
        }
    });
    private static final ISO781611Encoder<FingerInfo> ENCODER = new ISO781611Encoder<>(new BiometricDataBlockEncoder<FingerInfo>() {
        public void encode(FingerInfo fingerInfo, OutputStream outputStream) throws IOException {
            fingerInfo.writeObject(outputStream);
        }
    });
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -1037522331623814528L;
    private boolean shouldAddRandomDataIfEmpty;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public DG3File(List<FingerInfo> list) {
        this(list, true);
    }

    public DG3File(List<FingerInfo> list, boolean z) {
        super(99, list);
        this.shouldAddRandomDataIfEmpty = z;
    }

    public DG3File(InputStream inputStream) throws IOException {
        super(99, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        for (CBEFFInfo cBEFFInfo : DECODER.decode(inputStream).getSubRecords()) {
            if (!(cBEFFInfo instanceof SimpleCBEFFInfo)) {
                throw new IOException("Was expecting a SimpleCBEFFInfo, found " + cBEFFInfo.getClass().getSimpleName());
            }
            BiometricDataBlock biometricDataBlock = ((SimpleCBEFFInfo) cBEFFInfo).getBiometricDataBlock();
            if (!(biometricDataBlock instanceof FingerInfo)) {
                throw new IOException("Was expecting a FingerInfo, found " + biometricDataBlock.getClass().getSimpleName());
            }
            add((FingerInfo) biometricDataBlock);
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        for (FingerInfo simpleCBEFFInfo : getSubRecords()) {
            complexCBEFFInfo.add(new SimpleCBEFFInfo(simpleCBEFFInfo));
        }
        ENCODER.encode(complexCBEFFInfo, outputStream);
        if (this.shouldAddRandomDataIfEmpty) {
            writeOptionalRandomData(outputStream);
        }
    }

    public String toString() {
        return "DG3File [" + super.toString() + "]";
    }

    public List<FingerInfo> getFingerInfos() {
        return getSubRecords();
    }

    public void addFingerInfo(FingerInfo fingerInfo) {
        add(fingerInfo);
    }

    public void removeFingerInfo(int i) {
        remove(i);
    }
}
