package com.example.dimitri.soireehockey;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ReceiveService extends IntentService {
    public ReceiveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
