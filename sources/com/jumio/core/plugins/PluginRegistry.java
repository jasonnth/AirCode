package com.jumio.core.plugins;

import com.jumio.core.data.document.DocumentScanMode;
import java.util.ArrayList;
import java.util.Collection;

public class PluginRegistry {
    private static final String PLUGIN_BAM = "com.jumio.bam.BamPlugin";
    private static String PLUGIN_BARCODE = "com.jumio.nv.barcode.BarcodePlugin";
    private static String PLUGIN_BARCODE_VISION = "com.jumio.nv.barcode.vision.BarcodeVisionPlugin";
    private static final String PLUGIN_FACE = "com.jumio.nv.face.FacePlugin";
    private static final String PLUGIN_LINEFINDER = "com.jumio.nv.linefinder.LineFinderPlugin";
    private static final String PLUGIN_LIVENESS = "com.jumio.nv.liveness.LivenessPlugin";
    private static final String PLUGIN_MANUAL_PICTURE = "com.jumio.sdk.manual.ManualPicturePlugin";
    private static String PLUGIN_MRZ = "com.jumio.nv.mrz.MrzPlugin";
    private static String PLUGIN_NFC = "com.jumio.nv.nfc.NfcPlugin";
    private static String PLUGIN_OCR = "com.jumio.nv.ocr.OcrPlugin";
    private static final Object lock = new Object();

    public enum PluginMode {
        MRZ,
        NFC,
        TEMPLATE_MATCHER,
        BARCODE,
        CARD,
        LINE_FINDER,
        MANUAL,
        FACE_MANUAL,
        FACE
    }

    private static String classNameForPlugin(PluginMode pluginMode) {
        switch (pluginMode) {
            case MRZ:
                return PLUGIN_MRZ;
            case NFC:
                return PLUGIN_NFC;
            case TEMPLATE_MATCHER:
                return PLUGIN_OCR;
            case BARCODE:
                String className = PLUGIN_BARCODE;
                if (getClass(className) == null) {
                    return PLUGIN_BARCODE_VISION;
                }
                return className;
            case CARD:
                return PLUGIN_BAM;
            case LINE_FINDER:
                return PLUGIN_LINEFINDER;
            case MANUAL:
                return PLUGIN_MANUAL_PICTURE;
            case FACE_MANUAL:
                return PLUGIN_FACE;
            case FACE:
                return PLUGIN_LIVENESS;
            default:
                return null;
        }
    }

    private static Class<?> getClass(String className) {
        if (className == null) {
            return null;
        }
        try {
            if (className.isEmpty()) {
                return null;
            }
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Collection<Class> getAvailablePlugins() {
        ArrayList<Class> plugins;
        synchronized (lock) {
            plugins = new ArrayList<>();
            if (hasPlugin(PluginMode.MRZ)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.MRZ)));
            }
            if (hasPlugin(PluginMode.NFC)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.NFC)));
            }
            if (hasPlugin(PluginMode.TEMPLATE_MATCHER)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.TEMPLATE_MATCHER)));
            }
            if (hasPlugin(PluginMode.BARCODE)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.BARCODE)));
            }
            if (hasPlugin(PluginMode.CARD)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.CARD)));
            }
            if (hasPlugin(PluginMode.LINE_FINDER)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.LINE_FINDER)));
            }
            if (hasPlugin(PluginMode.MANUAL)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.MANUAL)));
            }
            if (hasPlugin(PluginMode.FACE_MANUAL)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.FACE_MANUAL)));
            }
            if (hasPlugin(PluginMode.FACE)) {
                plugins.add(getClass(classNameForPlugin(PluginMode.FACE)));
            }
        }
        return plugins;
    }

    public static Plugin getPlugin(PluginMode pluginMode) {
        Plugin result = null;
        synchronized (lock) {
            Class clazz = getClass(classNameForPlugin(pluginMode));
            if (clazz != null) {
                try {
                    result = (Plugin) clazz.newInstance();
                } catch (InstantiationException e) {
                    result = null;
                } catch (IllegalAccessException e2) {
                    result = null;
                }
            }
        }
        return result;
    }

    public static boolean hasPlugin(PluginMode pluginMode) {
        boolean result;
        synchronized (lock) {
            result = getClass(classNameForPlugin(pluginMode)) != null;
        }
        return result;
    }

    public static PluginMode getPluginMode(DocumentScanMode scanMode) {
        switch (scanMode) {
            case CREDIT:
                return PluginMode.CARD;
            case MRP:
            case MRV:
            case TD1:
            case TD2:
            case CNIS:
                return PluginMode.MRZ;
            case PDF417:
                return PluginMode.BARCODE;
            case TEMPLATEMATCHER:
                return PluginMode.TEMPLATE_MATCHER;
            case CSSN:
            case LINEFINDER:
                return PluginMode.LINE_FINDER;
            case FACE_MANUAL:
                return PluginMode.FACE_MANUAL;
            case FACE:
                return PluginMode.FACE;
            case MANUAL:
                return PluginMode.MANUAL;
            case NFC:
                return PluginMode.NFC;
            default:
                return null;
        }
    }

    public static Plugin getPlugin(DocumentScanMode scanMode) {
        return getPlugin(getPluginMode(scanMode));
    }
}
