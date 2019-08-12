package com.airbnb.android.aireventlogger;

import com.airbnb.jitney.event.p304v1.RawMessage;
import com.airbnb.jitney.event.p304v1.RawMessageList.Builder;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import okio.Buffer;

final class JitneyData implements Data {
    private final int firstId;
    private final int lastId;
    private final List<RawMessage> rawMessages;

    JitneyData(List<RawMessage> rawMessages2, int firstId2, int lastId2) {
        this.rawMessages = rawMessages2;
        this.firstId = firstId2;
        this.lastId = lastId2;
    }

    public int length() {
        try {
            return messagesToBuffer().readByteArray().length;
        } catch (IOException e) {
            throw new RuntimeException("Somehow received an IO exception when writing to in memory buffer", e);
        }
    }

    public void writeTo(OutputStream stream) throws IOException {
        messagesToBuffer().writeTo(stream);
    }

    public int firstId() {
        return this.firstId;
    }

    public int lastId() {
        return this.lastId;
    }

    private Buffer messagesToBuffer() throws IOException {
        return Utils.toBuffer(new Builder(this.rawMessages).build());
    }
}
