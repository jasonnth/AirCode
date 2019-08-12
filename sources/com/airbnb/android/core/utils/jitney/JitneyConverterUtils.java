package com.airbnb.android.core.utils.jitney;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.transport.BufferTransport;
import java.io.IOException;
import okio.Buffer;

public class JitneyConverterUtils {
    private static String toJsonBase(Struct struct) throws IOException {
        Buffer buffer = new Buffer();
        struct.write(new JitneyJsonProtocol(new BufferTransport(buffer)));
        return buffer.readUtf8();
    }

    public static String toJson(C2731SearchContext searchContext) {
        if (searchContext != null) {
            try {
                return toJsonBase(searchContext);
            } catch (IOException e) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Tried to parse jitney SearchContext struct as JSON but failed"));
            }
        }
        return "";
    }
}
