package pe.aloha.app.aloha.Core;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import pe.aloha.app.aloha.R;

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

    public static void makeCall(Context context) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);

        phoneIntent.setData(Uri.parse("tel:946585495"));

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        createToast(context, R.string.welcome);
        context.startActivity(phoneIntent);
    }

    public static void openWhatsapp(Context context) {
        String whastappLink = "https://api.whatsapp.com/send?phone=51946585495";
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse(whastappLink));
        context.startActivity(intent);
    }
}
