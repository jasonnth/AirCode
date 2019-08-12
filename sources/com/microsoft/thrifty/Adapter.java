package com.microsoft.thrifty;

import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

public interface Adapter<T, B extends StructBuilder<T>> {
    void write(Protocol protocol, T t) throws IOException;
}
