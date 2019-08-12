package com.jumio.clientlib.impl.livenessAndTM;

public class jniLivenessAndTM {
    public static SWIGTYPE_p_FrameProcessorPtr make_async(SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr) {
        return new SWIGTYPE_p_FrameProcessorPtr(jniLivenessAndTMJNI.make_async(SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr)), true);
    }

    public static String GetCollectedPerformanceStatistics() {
        return jniLivenessAndTMJNI.GetCollectedPerformanceStatistics();
    }
}
