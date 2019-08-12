package com.mparticle.internal;

import java.util.List;

public interface ReportingManager {
    void log(JsonReportingMessage jsonReportingMessage);

    void logAll(List<? extends JsonReportingMessage> list);
}
