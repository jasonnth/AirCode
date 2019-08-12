package com.jumio.sdk.extraction;

import com.jumio.commons.camera.CameraUtils.IYuvConversion;
import com.jumio.ocr.impl.smartEngines.swig.YuvUtils;

public class YuvConversionWrapper implements IYuvConversion {
    public int yuvCutRotateScale2rgb(byte[] yuv_data, int yuv_width, int yuv_height, int hole_left, int hole_top, int hole_width, int hole_height, byte[] out, int out_width, int out_height, int num_rotations, StringBuilder extendedLog) {
        return YuvUtils.yuvCutRotateScale2rgb(yuv_data, yuv_width, yuv_height, hole_left, hole_top, hole_width, hole_height, out, out_width, out_height, num_rotations);
    }
}
