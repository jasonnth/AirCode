package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class AdaptationSet {
    public final List<ContentProtection> contentProtections;

    /* renamed from: id */
    public final int f3149id;
    public final List<Representation> representations;
    public final int type;

    public AdaptationSet(int id, int type2, List<Representation> representations2, List<ContentProtection> contentProtections2) {
        this.f3149id = id;
        this.type = type2;
        this.representations = Collections.unmodifiableList(representations2);
        if (contentProtections2 == null) {
            this.contentProtections = Collections.emptyList();
        } else {
            this.contentProtections = Collections.unmodifiableList(contentProtections2);
        }
    }

    public boolean hasContentProtection() {
        return !this.contentProtections.isEmpty();
    }
}
