package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.HelpThreadAmenity;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UpdateHelpThreadRequest extends BaseRequestV2<HelpThreadResponse> {
    private final Body body;
    private final NodeSelection selection;
    private final long threadId;

    private static class Body {
        @JsonProperty("action")
        final String action;
        @JsonProperty("input_values")
        List<String> inputValues;
        @JsonProperty("max_major_version")
        int maxMajorVersion;
        @JsonProperty("node_id")
        long nodeId;
        @JsonProperty("option_value")
        String optionValue;

        public Body(HelpThreadNode node, HelpThreadOption option, int maxMajorVersion2) {
            this(node, option, maxMajorVersion2, Collections.emptyList());
        }

        public Body(HelpThreadNode node, HelpThreadOption option, int maxMajorVersion2, List<String> inputValues2) {
            this.action = "option_selected";
            this.nodeId = node.getId();
            this.optionValue = option.getValue();
            this.maxMajorVersion = maxMajorVersion2;
            this.inputValues = inputValues2;
        }
    }

    private UpdateHelpThreadRequest(HelpThread thread, NodeSelection selection2, int maxMajorVersion, List<String> inputValues) {
        this.selection = selection2;
        this.threadId = thread.getId();
        this.body = new Body(selection2.node(), selection2.option(), maxMajorVersion, inputValues);
    }

    public static UpdateHelpThreadRequest forOptionSelected(HelpThread thread, NodeSelection nodeSelection, int maxMajorVersion) {
        return new UpdateHelpThreadRequest(thread, nodeSelection, maxMajorVersion, Collections.emptyList());
    }

    public static UpdateHelpThreadRequest forAmenitiesSelected(HelpThread thread, NodeSelection selection2, int maxMajorVersion, List<HelpThreadAmenity> selectedAmenities) {
        return new UpdateHelpThreadRequest(thread, selection2, maxMajorVersion, FluentIterable.from((Iterable<E>) selectedAmenities).transform(UpdateHelpThreadRequest$$Lambda$1.lambdaFactory$()).toList());
    }

    public Type successResponseType() {
        return HelpThreadResponse.class;
    }

    public Body getBody() {
        return this.body;
    }

    public String getPath() {
        return "help_threads/" + this.threadId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public NodeSelection getSelection() {
        return this.selection;
    }
}
