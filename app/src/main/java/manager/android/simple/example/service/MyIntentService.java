package manager.android.simple.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }


    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        String arg = intent.getStringExtra("ARG-INTENT-SERVICE");
    }
}
