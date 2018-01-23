package manager.android.simple.example.vibration;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import manager.android.simple.example.R;

public class VibrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);
        vibratePhone();
    }

    private void vibratePhone() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent vibrationIntent = new Intent(this, VibrationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123456789, vibrationIntent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2000, pendingIntent);
        Toast.makeText(this, "Phone will vibrate after 2 seconds", Toast.LENGTH_SHORT).show();
    }
}
