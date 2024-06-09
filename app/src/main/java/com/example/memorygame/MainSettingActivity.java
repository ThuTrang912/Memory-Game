package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainSettingActivity extends AppCompatActivity {

    Button btHighScore, btOk;
    Button[] types = {btHighScore, btOk};
    FloatingActionButton btSound;
    SoundEffect soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);

        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();
        btHighScore = findViewById(R.id.btMain1);
        btOk = findViewById(R.id.btMain3);
        btSound = findViewById(R.id.btSound);

        if(SoundEffect.loaded == true) {
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
        }else{
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
        }

        MainSelectListener selectListener = new MainSelectListener();
        btHighScore.setOnClickListener(selectListener);
        btOk.setOnClickListener(selectListener);
        btSound.setOnClickListener(selectListener);

        MainActivity.previousClass = "MainSettingActivity";
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.action_menu_back){
            soundEffect.playClick();
            soundEffect.stopEffectMusic();
            SoundEffect.loadedEffect = false;
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class MainSelectListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent;
            switch(view.getId()){
                case R.id.btMain1:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),HighScoreActivity.class);
                    soundEffect.stopEffectMusic();
                    startActivity(intent);
                    break;
                case R.id.btMain3:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),MainActivity.class);
                    soundEffect.stopEffectMusic();
                    startActivity(intent);
                    break;
                case R.id.btSound:
                    if(SoundEffect.loaded == true) {
                        SoundEffect.loaded = false;
                        btSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
                        soundEffect.stopEffectMusic();
                    }else{
                        SoundEffect.loaded = true;
                        btSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
                        intent = new Intent(getApplication(),MainSettingActivity.class);
                        startActivity(intent);
                    }
                    Log.d("soundLoaded", Boolean.toString(SoundEffect.loaded));
                    break;
            }
        }
    }
}