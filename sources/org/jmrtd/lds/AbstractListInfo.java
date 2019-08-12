package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractListInfo<R> extends AbstractLDSInfo {
    private static final long serialVersionUID = 2970076896364365191L;
    private List<R> subRecords;

    public abstract void readObject(InputStream inputStream) throws IOException;

    public abstract void writeObject(OutputStream outputStream) throws IOException;

    AbstractListInfo() {
    }

    /* access modifiers changed from: 0000 */
    public List<R> getSubRecords() {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        return new ArrayList(this.subRecords);
    }

    /* access modifiers changed from: 0000 */
    public void add(R r) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.add(r);
    }

    /* access modifiers changed from: 0000 */
    public void addAll(List<R> list) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.addAll(list);
    }

    /* access modifiers changed from: 0000 */
    public void remove(int i) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.remove(i);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractListInfo)) {
            return false;
        }
        try {
            AbstractListInfo abstractListInfo = (AbstractListInfo) obj;
            List subRecords2 = getSubRecords();
            List subRecords3 = abstractListInfo.getSubRecords();
            int size = subRecords2.size();
            if (size != subRecords3.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                Object obj2 = subRecords2.get(i);
                Object obj3 = subRecords3.get(i);
                if (obj2 == null) {
                    if (obj3 != null) {
                        return false;
                    }
                } else if (!obj2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        int i = 1234567891;
        for (Object next : getSubRecords()) {
            if (next == null) {
                i = (i * 3) + 5;
            } else {
                i = ((i + next.hashCode()) * 5) + 7;
            }
        }
        return (i * 7) + 11;
    }
}
