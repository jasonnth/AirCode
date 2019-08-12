package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends ContainerNode<ArrayNode> {
    private final List<JsonNode> _children = new ArrayList();

    public ArrayNode(JsonNodeFactory nf) {
        super(nf);
    }

    public boolean isEmpty(SerializerProvider serializers) {
        return this._children.isEmpty();
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    public JsonNode get(String fieldName) {
        return null;
    }

    public void serialize(JsonGenerator f, SerializerProvider provider) throws IOException {
        List<JsonNode> c = this._children;
        int size = c.size();
        f.writeStartArray(size);
        for (int i = 0; i < size; i++) {
            JsonNode n = (JsonNode) c.get(i);
            if (n instanceof BaseJsonNode) {
                ((BaseJsonNode) n).serialize(f, provider);
            } else {
                n.serialize(f, provider);
            }
        }
        f.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jg, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForArray(this, jg);
        for (JsonNode n : this._children) {
            ((BaseJsonNode) n).serialize(jg, provider);
        }
        typeSer.writeTypeSuffixForArray(this, jg);
    }

    public ArrayNode add(JsonNode value) {
        if (value == null) {
            value = nullNode();
        }
        _add(value);
        return this;
    }

    public ArrayNode addNull() {
        _add(nullNode());
        return this;
    }

    public ArrayNode add(double v) {
        return _add(numberNode(v));
    }

    public ArrayNode add(String v) {
        if (v == null) {
            return addNull();
        }
        return _add(textNode(v));
    }

    public ArrayNode add(boolean v) {
        return _add(booleanNode(v));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof ArrayNode)) {
            return false;
        }
        return this._children.equals(((ArrayNode) o)._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 16);
        sb.append('[');
        int len = this._children.size();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(((JsonNode) this._children.get(i)).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public ArrayNode _add(JsonNode node) {
        this._children.add(node);
        return this;
    }
}
