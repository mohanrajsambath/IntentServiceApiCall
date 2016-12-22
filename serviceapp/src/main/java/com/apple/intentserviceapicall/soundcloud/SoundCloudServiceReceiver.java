package com.apple.intentserviceapicall.soundcloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Mohanraj.S on 21/12/16.
 */

public class SoundCloudServiceReceiver extends ResultReceiver {



    private Listener listener;

    public SoundCloudServiceReceiver(Handler handler) {
        super(handler);
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (listener != null)
            listener.onReceiveResult(resultCode, resultData);

    }

      public static interface Listener {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
