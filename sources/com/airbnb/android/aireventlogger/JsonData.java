package com.airbnb.android.aireventlogger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class JsonData implements Data {
    private final LinkedList<byte[]> data = new LinkedList<>();
    private final int firstId;
    private final int lastId;
    private int length = 0;

    JsonData(List<byte[]> data2, int firstId2, int lastId2) {
        this.firstId = firstId2;
        this.lastId = lastId2;
        begin();
        for (int i = 0; i < data2.size(); i++) {
            add((byte[]) data2.get(i));
            if (i != data2.size() - 1) {
                next();
            }
        }
        end();
    }

    private boolean begin() {
        return add("[");
    }

    private boolean end() {
        return add("]");
    }

    private boolean next() {
        return add(",");
    }

    private boolean add(byte[] object) {
        this.length += object.length;
        return this.data.add(object);
    }

    private boolean add(String object) {
        byte[] bytes = object.getBytes();
        this.length += bytes.length;
        return this.data.add(bytes);
    }

    public int length() {
        return this.length;
    }

    public void writeTo(OutputStream sink) throws IOException {
        Iterator it = this.data.iterator();
        while (it.hasNext()) {
            sink.write((byte[]) it.next());
        }
    }

    public int firstId() {
        return this.firstId;
    }

    public int lastId() {
        return this.lastId;
    }
}
