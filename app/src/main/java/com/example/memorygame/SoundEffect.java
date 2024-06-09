package com.example.memorygame;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Button;

import java.util.ConcurrentModificationException;

public class SoundEffect extends Thread{
    private SoundPool soundPool;
    private static MediaPlayer musicEffect;
    // Maximumn sound stream.
    private static final int MAX_STREAMS = 5;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    public static boolean loaded = true;
    public static boolean loadedEffect = true;
    private int soundIdClick = R.raw.sound_click;
    private int soundIdCorrect  = R.raw.sound_correct;
    private int soundIdWrong = R.raw.sound_wrong;
    private int soundIdWin = R.raw.sound_win;
    private int soundIdLose = R.raw.sound_lose;
    private int soundIdEffect =R.raw.sound_effect;
    private float volume;


    public SoundEffect(Context context){
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundPool.load(context,soundIdClick,1);
        soundPool.load(context,soundIdCorrect,1);
        soundPool.load(context,soundIdWrong,1);
        soundPool.load(context,soundIdWin,1);
        soundPool.load(context,soundIdLose,1);
        soundPool.load(context,soundIdEffect,1);

        musicEffect = MediaPlayer.create(context, R.raw.sound_effect);
    }
    public void playClick(){
        Log.d("soundLoaded", Boolean.toString(loaded));
        if(loaded == true) {
            soundPool.play(1, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
    public void playCorrect() {
        Log.d("soundLoaded", Boolean.toString(loaded));
        if (loaded == true) {
            soundPool.play(2, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
    public void playWrong() {
        Log.d("soundLoaded", Boolean.toString(loaded));
        if (loaded == true) {
            soundPool.play(3, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
    public void playWin(){
        Log.d("soundLoaded", Boolean.toString(loaded));
        if(loaded == true) {
        soundPool.play(4, 1.0f,1.0f,0,0,1.0f);
        }
    }
    public void playLose() {
        Log.d("soundLoaded", Boolean.toString(loaded));
        if (loaded == true) {
            soundPool.play(5, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    @Override
    public void run() {
        super.run();
        while(loaded == true) {
            if (loadedEffect == true) {
                playEffectMusic();
                try {
                    SoundEffect.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                stopEffectMusic();
            }
        }

    }

    public synchronized void playEffectMusic(){
        musicEffect.start();
    }
    public void stopEffectMusic(){
        musicEffect.stop();
    }
}
