package manager.android.simple.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import manager.android.simple.example.vibration.VibrationActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private SampleAdapter adapter;
    private List<Sample> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDataSource();
        setupRecyclerView();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new SampleAdapter(this, list, new SampleAdapter.SampleClickListener() {
            @Override
            public void onSampleClick(Sample sample) {
                switch (sample.getSampleName()) {
                    case SERVICE_INTENT_SERVICE:
                        break;
                    case ALARM_MANAGER:
                        Toast.makeText(MainActivity.this, "Start alarm manager", Toast.LENGTH_SHORT).show();
                        break;
                    case JOB_SCHEDULER:
                        break;
                    case VIBRATION_BROADCAST_RECEIVER:
                        startActivity(new Intent(MainActivity.this, VibrationActivity.class));
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_sample);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupDataSource() {
        list = new ArrayList<>();
        list.add(new Sample("AlarmManager", "This is alar manager", Sample.SampleName.ALARM_MANAGER));
        list.add(new Sample("JobScheduler", "This is job scheduler", Sample.SampleName.JOB_SCHEDULER));
        list.add(new Sample("Service And IntentService", "This is Service and IntentService", Sample.SampleName.SERVICE_INTENT_SERVICE));
        list.add(new Sample("BroadcastReceiver", "Vibration", Sample.SampleName.VIBRATION_BROADCAST_RECEIVER));
    }
}