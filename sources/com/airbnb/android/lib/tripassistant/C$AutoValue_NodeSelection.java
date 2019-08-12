package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;

/* renamed from: com.airbnb.android.lib.tripassistant.$AutoValue_NodeSelection reason: invalid class name */
abstract class C$AutoValue_NodeSelection extends NodeSelection {
    private final HelpThreadIssue issue;
    private final HelpThreadNode node;
    private final HelpThreadOption option;

    /* renamed from: com.airbnb.android.lib.tripassistant.$AutoValue_NodeSelection$Builder */
    static final class Builder extends com.airbnb.android.lib.tripassistant.NodeSelection.Builder {
        private HelpThreadIssue issue;
        private HelpThreadNode node;
        private HelpThreadOption option;

        Builder() {
        }

        private Builder(NodeSelection source) {
            this.issue = source.issue();
            this.node = source.node();
            this.option = source.option();
        }

        public com.airbnb.android.lib.tripassistant.NodeSelection.Builder issue(HelpThreadIssue issue2) {
            if (issue2 == null) {
                throw new NullPointerException("Null issue");
            }
            this.issue = issue2;
            return this;
        }

        public com.airbnb.android.lib.tripassistant.NodeSelection.Builder node(HelpThreadNode node2) {
            if (node2 == null) {
                throw new NullPointerException("Null node");
            }
            this.node = node2;
            return this;
        }

        public com.airbnb.android.lib.tripassistant.NodeSelection.Builder option(HelpThreadOption option2) {
            if (option2 == null) {
                throw new NullPointerException("Null option");
            }
            this.option = option2;
            return this;
        }

        public NodeSelection build() {
            String missing = "";
            if (this.issue == null) {
                missing = missing + " issue";
            }
            if (this.node == null) {
                missing = missing + " node";
            }
            if (this.option == null) {
                missing = missing + " option";
            }
            if (missing.isEmpty()) {
                return new AutoValue_NodeSelection(this.issue, this.node, this.option);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_NodeSelection(HelpThreadIssue issue2, HelpThreadNode node2, HelpThreadOption option2) {
        if (issue2 == null) {
            throw new NullPointerException("Null issue");
        }
        this.issue = issue2;
        if (node2 == null) {
            throw new NullPointerException("Null node");
        }
        this.node = node2;
        if (option2 == null) {
            throw new NullPointerException("Null option");
        }
        this.option = option2;
    }

    public HelpThreadIssue issue() {
        return this.issue;
    }

    public HelpThreadNode node() {
        return this.node;
    }

    public HelpThreadOption option() {
        return this.option;
    }

    public String toString() {
        return "NodeSelection{issue=" + this.issue + ", node=" + this.node + ", option=" + this.option + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NodeSelection)) {
            return false;
        }
        NodeSelection that = (NodeSelection) o;
        if (!this.issue.equals(that.issue()) || !this.node.equals(that.node()) || !this.option.equals(that.option())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.issue.hashCode()) * 1000003) ^ this.node.hashCode()) * 1000003) ^ this.option.hashCode();
    }

    public com.airbnb.android.lib.tripassistant.NodeSelection.Builder toBuilder() {
        return new Builder(this);
    }
}
