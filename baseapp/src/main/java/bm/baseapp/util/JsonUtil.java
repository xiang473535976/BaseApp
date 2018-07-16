/**
 * 2014-3-3 下午3:55:51 Created By niexiaoqiang
 */

package bm.baseapp.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * String转JSON
 */
public class JsonUtil {
    private static Gson gson;

    private static Gson getGson() {
        if (null == gson)
            synchronized (JsonUtil.class) {
                if (null == gson)
                    gson = new Gson();
            }
        return gson;
    }

    /**
     * 从字符串转位对应的对象
     *
     * @param str
     * @param beanObj
     * @return
     */
    public static <T> T StringToObj(String str, Class<T> beanObj) {
        return getGson().fromJson(str, beanObj);
    }
    public static <T> List<T> Object2List(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 从对象转为String
     *
     * @param object
     * @return
     */

    public static String ObjToString(Object object) {
        return getGson().toJson(object);
    }

}
