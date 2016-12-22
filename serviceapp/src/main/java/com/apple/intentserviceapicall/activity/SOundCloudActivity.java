package com.apple.intentserviceapicall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apple.intentserviceapicall.R;
import com.apple.intentserviceapicall.soundcloud.SoundCloud;
import com.apple.intentserviceapicall.soundcloud.SoundCloudService;
import com.apple.intentserviceapicall.soundcloud.SoundCloudServiceReceiver;



/**
 * Created by Mohanraj.S on 21/12/16.
 */

public class SOundCloudActivity extends AppCompatActivity implements SoundCloudServiceReceiver.Listener {
    public static String TAG="SOundCloudActivity";

    TextView tV_json_result;
    Button button_myRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundcloud);
         button_myRequest = (Button)findViewById(R.id.button_myRequest);
         tV_json_result =(TextView)findViewById(R.id.tV_json_result);
        final String btnStr=button_myRequest.getText().toString();
        button_myRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Srv", "Client get quote!");
                // Create the stock
                final SoundCloud mSound = new SoundCloud();
                mSound.setmSoundStr(btnStr);
                Log.d("SwA", "Calling method");
                startService(createCallingIntent(mSound));
            }
        });


    }

    private Intent createCallingIntent(SoundCloud sound) {
        Intent i = new Intent(this, SoundCloudService.class);
        SoundCloudServiceReceiver receiver = new SoundCloudServiceReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("SoundCloudServiceReceiver", receiver);
        i.putExtra("sound", sound);
        return i;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        SoundCloud mSound = resultData.getParcelable("sound");
        Log.d(TAG, "sound ["+mSound+"]");
        try {
            button_myRequest.setText(mSound.getmSoundStr());
        }catch(NullPointerException e){e.printStackTrace();}


    }
}
