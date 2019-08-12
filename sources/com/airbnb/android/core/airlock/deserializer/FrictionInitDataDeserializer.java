package com.airbnb.android.core.airlock.deserializer;

import com.airbnb.android.core.airlock.models.AirlockFrictionType;
import com.airbnb.android.core.airlock.models.BaseAirlockFriction;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FrictionInitDataDeserializer extends JsonDeserializer<Map<String, BaseAirlockFriction>> {
    public Map<String, BaseAirlockFriction> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode frictions = (JsonNode) codec.readTree(p);
        Map<String, BaseAirlockFriction> parsedFrictionInitData = Maps.newHashMap();
        Iterator<Entry<String, JsonNode>> fieldIterator = frictions.fields();
        while (fieldIterator.hasNext()) {
            Entry<String, JsonNode> entry = (Entry) fieldIterator.next();
            AirlockFrictionType frictionType = AirlockFrictionType.fromString((String) entry.getKey());
            parsedFrictionInitData.put(getFrictionKey(frictionType, (String) entry.getKey()), codec.treeToValue((TreeNode) entry.getValue(), frictionType.getFrictionClass()));
        }
        return parsedFrictionInitData;
    }

    private String getFrictionKey(AirlockFrictionType frictionType, String parsedKey) {
        return frictionType.equals(AirlockFrictionType.Unknown) ? parsedKey : frictionType.getServerKey();
    }
}
