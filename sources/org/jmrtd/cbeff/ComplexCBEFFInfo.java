package org.jmrtd.cbeff;

import java.util.ArrayList;
import java.util.List;

public class ComplexCBEFFInfo implements CBEFFInfo {
    private List<CBEFFInfo> subRecords;

    public List<CBEFFInfo> getSubRecords() {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        return new ArrayList(this.subRecords);
    }

    public void add(CBEFFInfo cBEFFInfo) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.add(cBEFFInfo);
    }

    public void addAll(List<CBEFFInfo> list) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.addAll(list);
    }

    public void remove(int i) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.remove(i);
    }

    public boolean equals(Object obj) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(ComplexCBEFFInfo.class)) {
            return false;
        }
        return this.subRecords.equals(((ComplexCBEFFInfo) obj).getSubRecords());
    }

    public int hashCode() {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        return (this.subRecords.hashCode() * 7) + 11;
    }
}
