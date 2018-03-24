package pe.aloha.app.aloha.Core;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

        phoneIntent.setData(Uri.parse("tel:987928878"));

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            createToast(context, R.string.something_wrong);

            return;
        }

        createToast(context, R.string.welcome);
        context.startActivity(phoneIntent);
    }

    public static void openWhatsapp(Context context) {
        String whastappLink = "https://api.whatsapp.com/send?phone=51987928878";
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse(whastappLink));
        context.startActivity(intent);
    }

    public static String convertDate(String myDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date newDate = null;
        try {
            newDate = format.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(newDate);


        format .setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parsed = null; // => Date is in UTC now
        try {
            parsed = format .parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimeZone tz = TimeZone.getTimeZone("America/Lima");
        Locale esLocale = new Locale("es", "ES");
        SimpleDateFormat destFormat = new SimpleDateFormat("EEEE d ' de ' MMMM ', ' hh:mm aa", esLocale);
        destFormat.setTimeZone(tz);

        date = destFormat.format(parsed);
        Log.e("date",date);

        return date;
    }
}
