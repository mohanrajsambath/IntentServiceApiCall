package com.apple.intentserviceapicall.soundcloud;

import android.app.IntentService;
import android.content.Intent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

/**
 * Created by Mohanraj.S on 21/12/16.
 */

public class SoundCloudService extends IntentService {

    private static String SOUND_CLOUD_URL ="http://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json";

    public SoundCloudService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*Stock stock = intent.getParcelableExtra("stock");
        final ResultReceiver rec = (ResultReceiver) intent.getParcelableExtra("rec");*/
        SoundCloud mSoundCloud = intent.getParcelableExtra("sound");
        final SoundCloudServiceReceiver mScReceiver= (SoundCloudServiceReceiver)intent.getParcelableExtra("SoundCloudServiceReceiver");

        try {
            String response="";
            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 20000);
            HttpConnectionParams.setSoTimeout(httpParameters, 20000);

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;


            HttpGet httpGet = new HttpGet(SOUND_CLOUD_URL);
            //httpGet.addHeader(BusinessAccessLayer.AUTHORIZATIONS, "Bearer "+authorisation);

            httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            System.out.println("SOUND_CLOUD_URL_RESPONSE:"+response);

        } catch (UnsupportedEncodingException | SocketException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
