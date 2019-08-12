package com.miteksystems.facialcapture.science.api.events;

public class OnFacialCaptureProcessedEvent {
    boolean isBlinkDetected;
    boolean isDeviceUpright;
    boolean isFaceDistanceGood;
    boolean isFaceFound;
    boolean isFaceTooClose;
    boolean isFaceTooFarAway;
    boolean isLightingUniform;
    boolean isLivenessDetected;
    boolean isQualityGood;
    boolean isSharpnessGood;

    public OnFacialCaptureProcessedEvent(boolean isDeviceUpright2, boolean isFaceFound2, boolean isBlinkDetected2, boolean isLivenessDetected2, boolean isLightingUniform2, boolean isFaceDistanceGood2, boolean isFaceTooFarAway2, boolean isFaceTooClose2, boolean isImageSharp, boolean isGoodQuality) {
        this.isDeviceUpright = isDeviceUpright2;
        this.isFaceFound = isFaceFound2;
        this.isBlinkDetected = isBlinkDetected2;
        this.isLivenessDetected = isLivenessDetected2;
        this.isLightingUniform = isLightingUniform2;
        this.isFaceDistanceGood = isFaceDistanceGood2;
        this.isFaceTooFarAway = isFaceTooFarAway2;
        this.isFaceTooClose = isFaceTooClose2;
        this.isSharpnessGood = isImageSharp;
        this.isQualityGood = isGoodQuality;
    }

    public boolean isDeviceUpright() {
        return this.isDeviceUpright;
    }

    public boolean isFaceFound() {
        return this.isFaceFound;
    }

    public boolean isBlinkDetected() {
        return this.isBlinkDetected;
    }

    public boolean isLivenessDetected() {
        return this.isLivenessDetected;
    }

    public boolean isLightingUniform() {
        return this.isLightingUniform;
    }

    public boolean isFaceDistanceGood() {
        return this.isFaceDistanceGood;
    }

    public boolean isFaceTooFarAway() {
        return this.isFaceTooFarAway;
    }

    public boolean isFaceTooClose() {
        return this.isFaceTooClose;
    }

    public boolean isSharpnessGood() {
        return this.isSharpnessGood;
    }

    public boolean isQualityGood() {
        return this.isQualityGood;
    }
}
