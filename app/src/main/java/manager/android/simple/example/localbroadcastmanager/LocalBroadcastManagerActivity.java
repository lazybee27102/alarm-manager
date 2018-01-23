package manager.android.simple.example.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import manager.android.simple.example.R;

public class LocalBroadcastManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ARG_MESSAGE_CONTENT = "arg-message-content";

    private TextView tvMessage;
    private EditText etMessage;
    private Button btSend;

    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(ARG_MESSAGE_CONTENT);
            tvMessage.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast_manager);
        tvMessage = findViewById(R.id.tv_message);
        etMessage = findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);

        btSend.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("my-message-action-name");
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("my-message-action-name");
        intent.putExtra(ARG_MESSAGE_CONTENT, etMessage.getText().toString());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
