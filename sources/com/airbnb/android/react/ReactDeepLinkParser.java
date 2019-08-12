package com.airbnb.android.react;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.utils.IOUtils;
import com.airbnb.android.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReactDeepLinkParser {
    private static final String FILE_NAME = "deeplinks.json";
    private final Context context;
    private final ObjectMapper objectMapper;

    private static class DeepLinkJsonFile {
        /* access modifiers changed from: private */
        @JsonProperty("deeplinks")
        public List<DeepLinkJsonItem> items;

        private DeepLinkJsonFile() {
        }
    }

    private static class DeepLinkJsonItem {
        /* access modifiers changed from: private */
        @JsonProperty("moduleName")
        public String moduleName;
        /* access modifiers changed from: private */
        @JsonProperty("uri")
        public String uri;

        private DeepLinkJsonItem() {
        }
    }

    public ReactDeepLinkParser(Context context2, ObjectMapper objectMapper2) {
        this.context = context2;
        this.objectMapper = objectMapper2;
    }

    public Map<String, String> parseJSON() {
        Map<String, String> deepLinkMap;
        InputStream stream = null;
        try {
            stream = this.context.getAssets().open(FILE_NAME);
            DeepLinkJsonFile deepLinkData = (DeepLinkJsonFile) JacksonUtils.readerForType(this.objectMapper, DeepLinkJsonFile.class).readValue(stream);
            IOUtils.closeQuietly(stream);
            deepLinkMap = new LinkedHashMap<>(deepLinkData.items.size());
            for (DeepLinkJsonItem item : deepLinkData.items) {
                deepLinkMap.put(item.uri, item.moduleName);
            }
        } catch (IOException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException(e));
            deepLinkMap = Collections.emptyMap();
            IOUtils.closeQuietly(stream);
        } catch (Throwable th) {
            IOUtils.closeQuietly(stream);
            throw th;
        }
        return deepLinkMap;
    }
}
