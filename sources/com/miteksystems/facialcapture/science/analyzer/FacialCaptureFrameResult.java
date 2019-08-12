package com.miteksystems.facialcapture.science.analyzer;

import com.miteksystems.facialcapture.science.api.events.OnFacialCaptureProcessedEvent;

public class FacialCaptureFrameResult {
    public int eyeDistanceFromScreen;
    public int eyesOpenScore;
    public int faceFoundScore;
    public boolean isBlinkDetected;
    public boolean isDeviceUpright;
    public boolean isFaceDistanceGood;
    public boolean isFaceFound;
    public boolean isFaceTooClose;
    public boolean isFaceTooFarAway;
    public boolean isLightingUniform;
    public boolean isLivenessDetected;
    public boolean isQualityGood;
    public boolean isSharpnessGood;
    public int livenessScore;
    public int qualityScore;
    public int sharpnessScore;
    public int uniformLightingScore;

    public FacialCaptureFrameResult() {
    }

    public FacialCaptureFrameResult(OnFacialCaptureProcessedEvent event) {
        this.isDeviceUpright = event.isDeviceUpright();
        this.isFaceFound = event.isFaceFound();
        this.isBlinkDetected = event.isBlinkDetected();
        this.isLivenessDetected = event.isLivenessDetected();
        this.isLightingUniform = event.isLightingUniform();
        this.isFaceDistanceGood = event.isFaceDistanceGood();
        this.isFaceTooFarAway = event.isFaceTooFarAway();
        this.isFaceTooClose = event.isFaceTooClose();
        this.isSharpnessGood = event.isSharpnessGood();
        this.isQualityGood = event.isQualityGood();
    }

    public FacialCaptureFrameResult(OnFacialCaptureProcessedEvent event, int eyeDistanceFromScreen2, int uniformLightingScore2, int sharpnessScore2, int qualityScore2, int eyesOpenScore2, int livenessScore2) {
        this(event);
        this.eyeDistanceFromScreen = eyeDistanceFromScreen2;
        this.uniformLightingScore = uniformLightingScore2;
        this.sharpnessScore = sharpnessScore2;
        this.qualityScore = qualityScore2;
        this.eyesOpenScore = eyesOpenScore2;
        this.livenessScore = livenessScore2;
    }
}
