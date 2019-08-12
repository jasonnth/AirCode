package com.google.common.p309io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

/* renamed from: com.google.common.io.LineReader */
public final class LineReader {
    private final char[] buf = this.cbuf.array();
    private final CharBuffer cbuf = CharStreams.createBuffer();
    private final LineBuffer lineBuf = new LineBuffer() {
        /* access modifiers changed from: protected */
        public void handleLine(String line, String end) {
            LineReader.this.lines.add(line);
        }
    };
    /* access modifiers changed from: private */
    public final Queue<String> lines = new LinkedList();
    private final Readable readable;
    private final Reader reader;

    public LineReader(Readable readable2) {
        this.readable = (Readable) Preconditions.checkNotNull(readable2);
        this.reader = readable2 instanceof Reader ? (Reader) readable2 : null;
    }

    public String readLine() throws IOException {
        int read;
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            this.cbuf.clear();
            if (this.reader != null) {
                read = this.reader.read(this.buf, 0, this.buf.length);
            } else {
                read = this.readable.read(this.cbuf);
            }
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return (String) this.lines.poll();
    }
}
