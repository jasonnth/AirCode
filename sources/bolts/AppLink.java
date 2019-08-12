package bolts;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

public class AppLink {
    private Uri sourceUrl;
    private List<Target> targets;
    private Uri webUrl;

    public static class Target {
        private final String appName;
        private final String className;
        private final String packageName;
        private final Uri url;

        public Target(String packageName2, String className2, Uri url2, String appName2) {
            this.packageName = packageName2;
            this.className = className2;
            this.url = url2;
            this.appName = appName2;
        }
    }

    public AppLink(Uri sourceUrl2, List<Target> targets2, Uri webUrl2) {
        this.sourceUrl = sourceUrl2;
        if (targets2 == null) {
            targets2 = Collections.emptyList();
        }
        this.targets = targets2;
        this.webUrl = webUrl2;
    }
}
