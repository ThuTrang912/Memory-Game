package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HardSettingActivity extends AppCompatActivity {

    Button btPrevousLevel,btHighScore, btPlayAgain, btHomePage;
    Button[] types = {btPrevousLevel,btHighScore, btPlayAgain, btHomePage};
    FloatingActionButton btSound;
    SoundEffect soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_setting);

        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();

        btPrevousLevel = findViewById(R.id.btHard1_2);
        btHighScore = findViewById(R.id.btHard1);
        btPlayAgain = findViewById(R.id.btHard2);
        btHomePage= findViewById(R.id.btHard3);
        btSound = findViewById(R.id.btSound);

        if(SoundEffect.loaded == true) {
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
        }else{
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
        }

        HardSelectListener selectListener = new HardSelectListener();
        btPrevousLevel.setOnClickListener(selectListener);
        btHighScore.setOnClickListener(selectListener);
        btPlayAgain.setOnClickListener(selectListener);
        btHomePage.setOnClickListener(selectListener);
        btSound.setOnClickListener(selectListener);

        MainActivity.previousClass = "HardSettingActivity";
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
            Intent intent = new Intent(getApplication(),HardActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class HardSelectListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent;
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor editor;
            switch(view.getId()){
                case R.id.btHard1_2:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),MediumActivity.class);
                    sharedPreferences = getSharedPreferences(MediumActivity.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply(); // (*)editer.commit();

                    startActivity(intent);
                    break;
                case R.id.btHard1:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
//                    intent = new Intent(getApplication(),HighScoreActivity.class);
//                    startActivity(intent);
                    intent = new Intent(getApplication(),ScoreView.class);
                    startActivity(intent);
                    break;
                case R.id.btHard2:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),HardActivity.class);
                    sharedPreferences = getSharedPreferences(HardActivity.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply(); // (*)editer.commit();

                    startActivity(intent);
                    break;
                case R.id.btHard3:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),MainActivity.class);
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
                        intent = new Intent(getApplication(),HardSettingActivity.class);
                        startActivity(intent);
                    }
                    Log.d("soundLoaded", Boolean.toString(SoundEffect.loaded));
                    break;
            }
        }
    }
}