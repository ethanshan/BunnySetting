package net.codingpark.bunnysetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.softwinner.SystemMix;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG                         = BootReceiver.class.getSimpleName();
    private static final String SVC_START_CMD               = "ctl.start";
    private static final String CPU_UP_SVC                  = "cpu_up";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        // 1. Obtain SharedPreferences object
        final SharedPreferences sp = context.getSharedPreferences(
                MainActivity.PREFERENCE_DATA, Context.MODE_PRIVATE);

        // 2. Listening connectivity change action
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d(TAG, "BOOT_COMPLETED");
            if (sp.getBoolean(MainActivity.AUTO_ENABLE_EIGHT_CORE_PREF_KEY, true)) {
                Log.d(TAG, "Enable 8 cores!");
                SystemMix.setProperty(SVC_START_CMD, CPU_UP_SVC);
            }
        }
    }
}
