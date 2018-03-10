package pe.aloha.app.aloha.Core;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by loualcala on 20/02/18.
 */

public class Utils {
    public static void changeActivity(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public static void createToast(Context context, int textResource) {
        Toast.makeText(context, textResource, Toast.LENGTH_SHORT).show();
    }
}
