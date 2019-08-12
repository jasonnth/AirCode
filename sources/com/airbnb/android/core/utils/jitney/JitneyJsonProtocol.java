package com.airbnb.android.core.utils.jitney;

import com.microsoft.thrifty.protocol.SimpleJsonProtocol;
import com.microsoft.thrifty.transport.Transport;
import java.io.IOException;

public class JitneyJsonProtocol extends SimpleJsonProtocol {
    public JitneyJsonProtocol(Transport transport) {
        super(transport);
    }

    public void writeStructBegin(String structName) throws IOException {
        writeMapBegin(0, 0, 0);
    }
}
