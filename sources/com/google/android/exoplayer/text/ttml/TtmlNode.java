package com.google.android.exoplayer.text.ttml;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.util.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

final class TtmlNode {
    private List<TtmlNode> children;
    public final long endTimeUs;
    public final boolean isTextNode;
    private final HashMap<String, Integer> nodeEndsByRegion;
    private final HashMap<String, Integer> nodeStartsByRegion;
    public final String regionId;
    public final long startTimeUs;
    public final TtmlStyle style;
    private final String[] styleIds;
    public final String tag;
    public final String text;

    public static TtmlNode buildTextNode(String text2) {
        return new TtmlNode(null, TtmlRenderUtil.applyTextElementSpacePolicy(text2), -1, -1, null, null, "");
    }

    public static TtmlNode buildNode(String tag2, long startTimeUs2, long endTimeUs2, TtmlStyle style2, String[] styleIds2, String regionId2) {
        return new TtmlNode(tag2, null, startTimeUs2, endTimeUs2, style2, styleIds2, regionId2);
    }

    private TtmlNode(String tag2, String text2, long startTimeUs2, long endTimeUs2, TtmlStyle style2, String[] styleIds2, String regionId2) {
        this.tag = tag2;
        this.text = text2;
        this.style = style2;
        this.styleIds = styleIds2;
        this.isTextNode = text2 != null;
        this.startTimeUs = startTimeUs2;
        this.endTimeUs = endTimeUs2;
        this.regionId = (String) Assertions.checkNotNull(regionId2);
        this.nodeStartsByRegion = new HashMap<>();
        this.nodeEndsByRegion = new HashMap<>();
    }

    public boolean isActive(long timeUs) {
        return (this.startTimeUs == -1 && this.endTimeUs == -1) || (this.startTimeUs <= timeUs && this.endTimeUs == -1) || ((this.startTimeUs == -1 && timeUs < this.endTimeUs) || (this.startTimeUs <= timeUs && timeUs < this.endTimeUs));
    }

    public void addChild(TtmlNode child) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(child);
    }

    public TtmlNode getChild(int index) {
        if (this.children != null) {
            return (TtmlNode) this.children.get(index);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        if (this.children == null) {
            return 0;
        }
        return this.children.size();
    }

    public long[] getEventTimesUs() {
        TreeSet<Long> eventTimeSet = new TreeSet<>();
        getEventTimes(eventTimeSet, false);
        long[] eventTimes = new long[eventTimeSet.size()];
        int i = 0;
        Iterator it = eventTimeSet.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            eventTimes[i] = ((Long) it.next()).longValue();
            i = i2;
        }
        return eventTimes;
    }

    private void getEventTimes(TreeSet<Long> out, boolean descendsPNode) {
        boolean isPNode = "p".equals(this.tag);
        if (descendsPNode || isPNode) {
            if (this.startTimeUs != -1) {
                out.add(Long.valueOf(this.startTimeUs));
            }
            if (this.endTimeUs != -1) {
                out.add(Long.valueOf(this.endTimeUs));
            }
        }
        if (this.children != null) {
            for (int i = 0; i < this.children.size(); i++) {
                ((TtmlNode) this.children.get(i)).getEventTimes(out, descendsPNode || isPNode);
            }
        }
    }

    public List<Cue> getCues(long timeUs, Map<String, TtmlStyle> globalStyles, Map<String, TtmlRegion> regionMap) {
        TreeMap<String, SpannableStringBuilder> regionOutputs = new TreeMap<>();
        traverseForText(timeUs, false, this.regionId, regionOutputs);
        traverseForStyle(globalStyles, regionOutputs);
        List<Cue> cues = new ArrayList<>();
        for (Entry<String, SpannableStringBuilder> entry : regionOutputs.entrySet()) {
            TtmlRegion region = (TtmlRegion) regionMap.get(entry.getKey());
            cues.add(new Cue(cleanUpText((SpannableStringBuilder) entry.getValue()), null, region.line, region.lineType, Integer.MIN_VALUE, region.position, Integer.MIN_VALUE, region.width));
        }
        return cues;
    }

    private void traverseForText(long timeUs, boolean descendsPNode, String inheritedRegion, Map<String, SpannableStringBuilder> regionOutputs) {
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        String resolvedRegionId = this.regionId;
        if ("".equals(resolvedRegionId)) {
            resolvedRegionId = inheritedRegion;
        }
        if (this.isTextNode && descendsPNode) {
            getRegionOutput(resolvedRegionId, regionOutputs).append(this.text);
        } else if ("br".equals(this.tag) && descendsPNode) {
            getRegionOutput(resolvedRegionId, regionOutputs).append(10);
        } else if (!"metadata".equals(this.tag) && isActive(timeUs)) {
            boolean isPNode = "p".equals(this.tag);
            for (Entry<String, SpannableStringBuilder> entry : regionOutputs.entrySet()) {
                this.nodeStartsByRegion.put(entry.getKey(), Integer.valueOf(((SpannableStringBuilder) entry.getValue()).length()));
            }
            for (int i = 0; i < getChildCount(); i++) {
                getChild(i).traverseForText(timeUs, descendsPNode || isPNode, resolvedRegionId, regionOutputs);
            }
            if (isPNode) {
                TtmlRenderUtil.endParagraph(getRegionOutput(resolvedRegionId, regionOutputs));
            }
            for (Entry<String, SpannableStringBuilder> entry2 : regionOutputs.entrySet()) {
                this.nodeEndsByRegion.put(entry2.getKey(), Integer.valueOf(((SpannableStringBuilder) entry2.getValue()).length()));
            }
        }
    }

    private static SpannableStringBuilder getRegionOutput(String resolvedRegionId, Map<String, SpannableStringBuilder> regionOutputs) {
        if (!regionOutputs.containsKey(resolvedRegionId)) {
            regionOutputs.put(resolvedRegionId, new SpannableStringBuilder());
        }
        return (SpannableStringBuilder) regionOutputs.get(resolvedRegionId);
    }

    private void traverseForStyle(Map<String, TtmlStyle> globalStyles, Map<String, SpannableStringBuilder> regionOutputs) {
        for (Entry<String, Integer> entry : this.nodeEndsByRegion.entrySet()) {
            String regionId2 = (String) entry.getKey();
            applyStyleToOutput(globalStyles, (SpannableStringBuilder) regionOutputs.get(regionId2), this.nodeStartsByRegion.containsKey(regionId2) ? ((Integer) this.nodeStartsByRegion.get(regionId2)).intValue() : 0, ((Integer) entry.getValue()).intValue());
            for (int i = 0; i < getChildCount(); i++) {
                getChild(i).traverseForStyle(globalStyles, regionOutputs);
            }
        }
    }

    private void applyStyleToOutput(Map<String, TtmlStyle> globalStyles, SpannableStringBuilder regionOutput, int start, int end) {
        if (start != end) {
            TtmlStyle resolvedStyle = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, globalStyles);
            if (resolvedStyle != null) {
                TtmlRenderUtil.applyStylesToSpan(regionOutput, start, end, resolvedStyle);
            }
        }
    }

    private SpannableStringBuilder cleanUpText(SpannableStringBuilder builder) {
        int builderLength = builder.length();
        for (int i = 0; i < builderLength; i++) {
            if (builder.charAt(i) == ' ') {
                int j = i + 1;
                while (j < builder.length() && builder.charAt(j) == ' ') {
                    j++;
                }
                int spacesToDelete = j - (i + 1);
                if (spacesToDelete > 0) {
                    builder.delete(i, i + spacesToDelete);
                    builderLength -= spacesToDelete;
                }
            }
        }
        if (builderLength > 0 && builder.charAt(0) == ' ') {
            builder.delete(0, 1);
            builderLength--;
        }
        for (int i2 = 0; i2 < builderLength - 1; i2++) {
            if (builder.charAt(i2) == 10 && builder.charAt(i2 + 1) == ' ') {
                builder.delete(i2 + 1, i2 + 2);
                builderLength--;
            }
        }
        if (builderLength > 0 && builder.charAt(builderLength - 1) == ' ') {
            builder.delete(builderLength - 1, builderLength);
            builderLength--;
        }
        for (int i3 = 0; i3 < builderLength - 1; i3++) {
            if (builder.charAt(i3) == ' ' && builder.charAt(i3 + 1) == 10) {
                builder.delete(i3, i3 + 1);
                builderLength--;
            }
        }
        if (builderLength > 0 && builder.charAt(builderLength - 1) == 10) {
            builder.delete(builderLength - 1, builderLength);
        }
        return builder;
    }
}
