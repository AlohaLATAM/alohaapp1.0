package pe.aloha.app.aloha.Core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by loualcala on 20/02/18.
 */

public class Persist {
    public static void Set(String key, String value, Context context) {
        SharedPreferences sp = context.getSharedPreferences("aloha_sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static String Get(String key, Context context) {
        return context.getSharedPreferences("aloha_sp", Context.MODE_PRIVATE).getString(key, "");
    }

    public static void Clear(Context context) {
        context.getSharedPreferences("aloha_sp", 0).edit().clear().apply();
    }
}
