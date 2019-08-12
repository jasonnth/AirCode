package com.airbnb.android.lib.tripassistant;

import android.os.Parcelable;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;

abstract class NodeSelection implements Parcelable {

    public static abstract class Builder {
        public abstract NodeSelection build();

        public abstract Builder issue(HelpThreadIssue helpThreadIssue);

        public abstract Builder node(HelpThreadNode helpThreadNode);

        public abstract Builder option(HelpThreadOption helpThreadOption);
    }

    public abstract HelpThreadIssue issue();

    public abstract HelpThreadNode node();

    public abstract HelpThreadOption option();

    public abstract Builder toBuilder();

    NodeSelection() {
    }

    public static Builder builder() {
        return new Builder();
    }
}
