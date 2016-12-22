package com.apple.intentserviceapicall.soundcloud;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mohanraj.S on 21/12/16.
 */

public class SoundCloud implements Parcelable{
    public SoundCloud() {

    }
    /*{
	"version": 1,
	"type": "rich",
	"provider_name": "SoundCloud",
	"provider_url": "http://soundcloud.com",
	"height": 400,
	"width": "100%",
	"title": "Flickermood by Forss",
	"description": "From the Soulhack album,&nbsp;recently featured in this ad <a href=\"https://www.dswshoes.com/tv_commercial.jsp?m=october2007\">https://www.dswshoes.com/tv_commercial.jsp?m=october2007</a> ",
	"thumbnail_url": "http://i1.sndcdn.com/artworks-000067273316-smsiqx-t500x500.jpg",
	"html": "<iframe width=\"100%\" height=\"400\" scrolling=\"no\" frameborder=\"no\" src=\"https://w.soundcloud.com/player/?visual=true&url=http%3A%2F%2Fapi.soundcloud.com%2Ftracks%2F293&show_artwork=true\"></iframe>",
	"author_name": "Forss",
	"author_url": "http://soundcloud.com/forss"
}*/

    public String getmSoundStr() {
        return mSoundStr;
    }

    public void setmSoundStr(String mSoundStr) {
        this.mSoundStr = mSoundStr;
    }

    private String mSoundStr;

    protected SoundCloud(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSoundStr);
    }

    public static final Creator<SoundCloud> CREATOR = new Creator<SoundCloud>() {
        @Override
        public SoundCloud createFromParcel(Parcel in) {
            SoundCloud mSoundCloud= new SoundCloud();
            mSoundCloud.setmSoundStr(in.readString());
            return mSoundCloud;
        }

        @Override
        public SoundCloud[] newArray(int size) {
            return new SoundCloud[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
