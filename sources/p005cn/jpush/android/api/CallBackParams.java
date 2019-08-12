package p005cn.jpush.android.api;

import java.util.Set;

/* renamed from: cn.jpush.android.api.CallBackParams */
public class CallBackParams {
    public String alias;
    public TagAliasCallback tagAliasCallBack;
    public Set<String> tags;

    public CallBackParams(String alias2, Set<String> tags2, TagAliasCallback callBack) {
        this.alias = alias2;
        this.tags = tags2;
        this.tagAliasCallBack = callBack;
    }

    public String toString() {
        return "CallBackParams - alias:" + this.alias + ", " + "tags:" + this.tags;
    }
}
