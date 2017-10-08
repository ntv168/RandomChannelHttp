package com.example.sam.randommusichttp;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sam on 10/8/2017.
 */

public class MusicSingleton {
    private static volatile MusicSingleton musicSingleton = null;
    private static boolean musicplaying;
    private static List<Integer> soundList;
    private static Context mContext;
    private static MediaPlayer mp;
    private static int randomInt;
    private static int sound;
    private String vtvstate,musicstate,htvstate;

    public static MusicSingleton getInstance(Context context) {
        if (musicSingleton == null) {
            synchronized (MusicSingleton.class) {
                if (musicSingleton == null) {
                    musicSingleton  = new MusicSingleton();
                    musicplaying = false;
                    soundList = new ArrayList<Integer>();
                    soundList.add(R.raw.alan_faded);
                    soundList.add(R.raw.whatcha_say);
                    soundList.add(R.raw.titanium);
                    mContext = context;
                    randomInt = (new Random().nextInt(soundList.size()));
                    sound = soundList.get(randomInt);
                    mp = MediaPlayer.create(mContext, sound);
                }
            }
        }
        return musicSingleton;
    }

    public static boolean isMusicplaying() {
        return musicplaying;
    }

    public static void setMusicplaying(boolean musicplaying) {
        MusicSingleton.musicplaying = musicplaying;
    }

    public void playRandomSound() {
        stopRandomSound();
        randomInt = (new Random().nextInt(soundList.size()));
        sound = soundList.get(randomInt);
        mp = MediaPlayer.create(mContext, sound);
        mp.start();
        musicstate = "on";
    }

    public void playVTVchannel() {
        stopRandomSound();
        sound = R.raw.vtv;
        mp = MediaPlayer.create(mContext, sound);
        mp.start();
        vtvstate = "on";
    }

    public void playHTVchannel() {
        stopRandomSound();
        sound = R.raw.htv;
        mp = MediaPlayer.create(mContext, sound);
        mp.start();
        htvstate = "on";
    }

    public void stopRandomSound() {
        mp.stop();
        htvstate = "off";
        vtvstate = "off";
        musicstate = "off";
    }

    public String getVtvstate() {
        return vtvstate;
    }

    public void setVtvstate(String vtvstate) {
        this.vtvstate = vtvstate;
    }

    public String getMusicstate() {
        return musicstate;
    }

    public void setMusicstate(String musicstate) {
        this.musicstate = musicstate;
    }

    public String getHtvstate() {
        return htvstate;
    }

    public void setHtvstate(String htvstate) {
        this.htvstate = htvstate;
    }
}
