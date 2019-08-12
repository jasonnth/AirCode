package com.devbrackets.android.exomedia.core.listener;

import com.google.android.exoplayer.metadata.id3.Id3Frame;
import java.util.List;

public interface Id3MetadataListener {
    void onId3Metadata(List<Id3Frame> list);
}
