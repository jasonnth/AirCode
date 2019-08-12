package com.mparticle.segmentation;

import java.util.ArrayList;
import java.util.Iterator;

public class SegmentMembership {
    StringBuilder list;
    private ArrayList<Segment> segments;

    public SegmentMembership(ArrayList<Segment> ids) {
        this.segments = ids;
    }

    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    public String toString() {
        return getCommaSeparatedIds();
    }

    public String getCommaSeparatedIds() {
        if (this.list == null) {
            this.list = new StringBuilder();
            Iterator it = this.segments.iterator();
            while (it.hasNext()) {
                this.list.append(((Segment) it.next()).getId());
                this.list.append(", ");
            }
            if (this.list.length() > 0) {
                this.list.delete(this.list.length() - 2, this.list.length());
            }
        }
        return this.list.toString();
    }
}
