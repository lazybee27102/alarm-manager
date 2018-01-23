package manager.android.simple.example.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getCanonicalName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // Android 6.0 Doze mode minimize battery drain, ignore wake lock, network is suspended...
        Log.d(TAG, "onReceive: ");
        WakeLocker.acquire(context);
    }
}