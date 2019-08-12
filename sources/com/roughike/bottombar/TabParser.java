package com.roughike.bottombar;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.p000v4.content.ContextCompat;
import com.roughike.bottombar.BottomBarTab.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

class TabParser {
    private final Context context;
    private final Config defaultTabConfig;
    private final XmlResourceParser parser;
    private ArrayList<BottomBarTab> tabs = new ArrayList<>();
    private BottomBarTab workingTab;

    private class TabParserException extends RuntimeException {
        private TabParserException() {
        }
    }

    TabParser(Context context2, Config defaultTabConfig2, int tabsXmlResId) {
        this.context = context2;
        this.defaultTabConfig = defaultTabConfig2;
        this.parser = context2.getResources().getXml(tabsXmlResId);
        parse();
    }

    private void parse() {
        try {
            this.parser.next();
            int eventType = this.parser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    parseNewTab(this.parser);
                } else if (eventType == 3 && this.parser.getName().equals("tab") && this.workingTab != null) {
                    this.tabs.add(this.workingTab);
                    this.workingTab = null;
                }
                eventType = this.parser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            throw new TabParserException();
        }
    }

    private void parseNewTab(XmlResourceParser parser2) {
        if (this.workingTab == null) {
            this.workingTab = tabWithDefaults();
        }
        this.workingTab.setIndexInContainer(this.tabs.size());
        for (int i = 0; i < parser2.getAttributeCount(); i++) {
            String attrName = parser2.getAttributeName(i);
            char c = 65535;
            switch (attrName.hashCode()) {
                case -1765033179:
                    if (attrName.equals("barColorWhenSelected")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1077332995:
                    if (attrName.equals("activeColor")) {
                        c = 4;
                        break;
                    }
                    break;
                case -690091498:
                    if (attrName.equals("hideBadgeWhenSelected")) {
                        c = 7;
                        break;
                    }
                    break;
                case -424740686:
                    if (attrName.equals("badgeBackgroundColor")) {
                        c = 6;
                        break;
                    }
                    break;
                case 3355:
                    if (attrName.equals("id")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3226745:
                    if (attrName.equals("icon")) {
                        c = 1;
                        break;
                    }
                    break;
                case 110371416:
                    if (attrName.equals("title")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1162188184:
                    if (attrName.equals("inActiveColor")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.workingTab.setId(parser2.getIdAttributeResourceValue(i));
                    break;
                case 1:
                    this.workingTab.setIconResId(parser2.getAttributeResourceValue(i, 0));
                    break;
                case 2:
                    this.workingTab.setTitle(getTitleValue(i, parser2));
                    break;
                case 3:
                    Integer inActiveColor = getColorValue(i, parser2);
                    if (inActiveColor == null) {
                        break;
                    } else {
                        this.workingTab.setInActiveColor(inActiveColor.intValue());
                        break;
                    }
                case 4:
                    Integer activeColor = getColorValue(i, parser2);
                    if (activeColor == null) {
                        break;
                    } else {
                        this.workingTab.setActiveColor(activeColor.intValue());
                        break;
                    }
                case 5:
                    Integer barColorWhenSelected = getColorValue(i, parser2);
                    if (barColorWhenSelected == null) {
                        break;
                    } else {
                        this.workingTab.setBarColorWhenSelected(barColorWhenSelected.intValue());
                        break;
                    }
                case 6:
                    Integer badgeBackgroundColor = getColorValue(i, parser2);
                    if (badgeBackgroundColor == null) {
                        break;
                    } else {
                        this.workingTab.setBadgeBackgroundColor(badgeBackgroundColor.intValue());
                        break;
                    }
                case 7:
                    this.workingTab.setHideBadgeWhenSelected(parser2.getAttributeBooleanValue(i, true));
                    break;
            }
        }
    }

    private BottomBarTab tabWithDefaults() {
        BottomBarTab tab = new BottomBarTab(this.context);
        tab.setConfig(this.defaultTabConfig);
        return tab;
    }

    private String getTitleValue(int attrIndex, XmlResourceParser parser2) {
        int titleResource = parser2.getAttributeResourceValue(attrIndex, 0);
        if (titleResource != 0) {
            return this.context.getString(titleResource);
        }
        return parser2.getAttributeValue(attrIndex);
    }

    private Integer getColorValue(int attrIndex, XmlResourceParser parser2) {
        int colorResource = parser2.getAttributeResourceValue(attrIndex, 0);
        if (colorResource != 0) {
            return Integer.valueOf(ContextCompat.getColor(this.context, colorResource));
        }
        try {
            return Integer.valueOf(Color.parseColor(parser2.getAttributeValue(attrIndex)));
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<BottomBarTab> getTabs() {
        return this.tabs;
    }
}
