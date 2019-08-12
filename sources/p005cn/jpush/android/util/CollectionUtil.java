package p005cn.jpush.android.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

/* renamed from: cn.jpush.android.util.CollectionUtil */
public class CollectionUtil {
    public static ArrayList<String> getListForJSONArray(JSONArray array) {
        ArrayList<String> list = new ArrayList<>();
        if (!(array == null || array.length() == 0)) {
            for (int i = 0; i < array.length(); i++) {
                String item = array.optString(i);
                if (!TextUtils.isEmpty(item)) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    public static JSONArray getJsonArrayFromList(ArrayList<String> list) {
        JSONArray array = new JSONArray();
        if (list != null && list.size() > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                array.put((String) it.next());
            }
        }
        return array;
    }
}
