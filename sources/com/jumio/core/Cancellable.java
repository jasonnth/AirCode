package com.jumio.core;

import java.io.IOException;

@Deprecated
public interface Cancellable {
    void cancel() throws IOException;
}
