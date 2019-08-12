package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.FixItReport;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class FixItReportResponse extends BaseResponse {
    @JsonProperty("fixit_report")
    public FixItReport report;
    @JsonProperty("fixit_reports")
    public List<FixItReport> reports;

    public FixItReport getReport() {
        if (this.reports != null) {
            return (FixItReport) FluentIterable.from((Iterable<E>) this.reports).first().orNull();
        }
        return this.report;
    }
}
