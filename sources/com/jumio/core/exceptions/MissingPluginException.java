package com.jumio.core.exceptions;

import com.jumio.core.data.document.DocumentScanMode;

public class MissingPluginException extends RuntimeException {
    public MissingPluginException(DocumentScanMode s) {
        super("Plugin for " + s + " is not linked! check your build file!");
    }
}
