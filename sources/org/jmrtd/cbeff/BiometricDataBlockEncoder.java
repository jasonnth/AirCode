package org.jmrtd.cbeff;

import java.io.IOException;
import java.io.OutputStream;
import org.jmrtd.cbeff.BiometricDataBlock;

public interface BiometricDataBlockEncoder<B extends BiometricDataBlock> {
    void encode(B b, OutputStream outputStream) throws IOException;
}
