package com.fasterxml.jackson.databind.node;

public abstract class NumericNode extends ValueNode {
    protected NumericNode() {
    }

    public final JsonNodeType getNodeType() {
        return JsonNodeType.NUMBER;
    }
}
