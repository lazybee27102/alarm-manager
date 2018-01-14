package manager.alarm.simple.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getCanonicalName();

    private Button btnStartService, btnStopService, btnStartServiceAt10;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        setupViews();
        registerEvents();
    }

    private void setupViews() {
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnStartServiceAt10 = findViewById(R.id.btn_start_service_at_10);
    }

    private void registerEvents() {
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnStartServiceAt10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startAlarmService();
                break;
            case R.id.btn_stop_service:
                stopAlarmService();
                break;
            case R.id.btn_start_service_at_10:
                startAlarmServiceAt10();
                break;
        }
    }

    private void startAlarmServiceAt10() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 20 * 60 * 1000; // 20 minutes in milliseconds

        // Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, interval, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Alarm start at 10:30 Am and repeat after 20 minutes", Toast.LENGTH_SHORT).show();
    }

    private void stopAlarmService() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm stop", Toast.LENGTH_SHORT).show();
    }

    private void startAlarmService() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 60 * 1000; // set interval

        // RTC_WAKEUP = right time clock: base on system time and wake up the device
        // ELAPSED_REALTIME_WAKEUP: time since boot.
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                interval, pendingIntent);
        Toast.makeText(this, "Alarm set, it will wake after 60 seconds", Toast.LENGTH_SHORT).show();
    }
}
