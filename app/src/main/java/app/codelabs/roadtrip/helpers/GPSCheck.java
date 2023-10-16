package app.codelabs.roadtrip.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

/**
 * Created by paybill on 11/8/2017.
 */

public class GPSCheck extends BroadcastReceiver {
    public static int GPS_OFF   = 0;
    public static int GPS_ON    = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent("gps_status");

        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            Log.d("GPSCheck", "onReceive: GPS ON");
            i.putExtra("message", GPS_ON);
        }
        else
        {
            Log.d("GPSCheck", "onReceive: GPS OFF");
            i.putExtra("message", GPS_OFF);
        }

        context.sendBroadcast(i);
    }
}
