package manager.alarm.simple.alarmclock.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class MultiThreadingService extends Service {
    private static final String TAG = MultiThreadingService.class.getSimpleName();
    private ServiceHandler serviceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            Log.d(TAG, "handleMessage: stopping service " + msg.arg2);
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread(TAG, THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        Looper looper = handlerThread.getLooper();
        serviceHandler = new ServiceHandler(looper);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = serviceHandler.obtainMessage();
        message.arg1 = startId;
        int no = 0;

        if (intent != null) {
            no = intent.getIntExtra("service-no", 0);
        }
        message.arg2 = no;

        serviceHandler.sendMessage(message);
        Toast.makeText(this, "Service " + no + " is starting after call startService()", Toast.LENGTH_SHORT).show();

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service is destroying...", Toast.LENGTH_SHORT).show();
    }
}
