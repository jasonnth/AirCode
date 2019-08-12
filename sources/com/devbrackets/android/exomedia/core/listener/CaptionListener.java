package com.devbrackets.android.exomedia.core.listener;

import com.google.android.exoplayer.text.Cue;
import java.util.List;

public interface CaptionListener {
    void onCues(List<Cue> list);
}
