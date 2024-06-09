package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private View btEasy;
    private View btMedium;
    private View btHard;
    public static String previousClass;

    SoundEffect soundEffect;

    public static int[] idImage = {R.drawable.bacha,R.drawable.bap,R.drawable.bapcai,R.drawable.bi,R.drawable.bixanh,
            R.drawable.bo,R.drawable.cachua,R.drawable.cai2,R.drawable.caitrang,R.drawable.cam,R.drawable.carot,
            R.drawable.catim,R.drawable.chanh,R.drawable.chanhday,R.drawable.chanhday2,R.drawable.chanhxanh,
            R.drawable.cusen,R.drawable.dau,R.drawable.dau2,R.drawable.daubap,R.drawable.daubap2png,
            R.drawable.dauhalan,R.drawable.dua,R.drawable.duaf,R.drawable.dualeo,R.drawable.dualuoi,
            R.drawable.dualuoi2,R.drawable.dualuoi3,R.drawable.duas,R.drawable.dudu,R.drawable.hanh,
            R.drawable.khoaimon,R.drawable.kiwi,R.drawable.lac,R.drawable.luou,R.drawable.man,R.drawable.mangcut,
            R.drawable.manhanoi,R.drawable.muopdang,R.drawable.namkimcham,R.drawable.nhan,R.drawable.nho,R.drawable.oi,
            R.drawable.ot,R.drawable.otdo,R.drawable.san,R.drawable.saurieng,R.drawable.tao, R.drawable.vietquat,R.drawable.xoai};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HighScoreActivity.highScore = 0;
        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();
        btEasy = findViewById(R.id.btEasy);
        btMedium = findViewById(R.id.btMedium);
        btHard = findViewById(R.id.btHard);

        btEasy.setOnTouchListener(this);
        btMedium.setOnTouchListener(this);
        btHard.setOnTouchListener(this);

        previousClass = "MainActivity";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_set,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_menu_set){
            soundEffect.playClick();
            soundEffect.stopEffectMusic();
            SoundEffect.loadedEffect = false;
            Intent intent = new Intent(getApplication(), MainSettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        soundEffect.playClick();
        soundEffect.stopEffectMusic();
        SoundEffect.loadedEffect = false;
        Intent intent;
        switch(view.getId()){
            case R.id.btEasy:
                intent = new Intent(getApplication(),EasyActivity.class);
                startActivity(intent);
                break;
            case R.id.btMedium:
                intent = new Intent(getApplication(),MediumActivity.class);
                startActivity(intent);
                break;
            case R.id.btHard:
                intent = new Intent(getApplication(),HardActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}