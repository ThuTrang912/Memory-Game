package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EasySettingActivity extends AppCompatActivity {
    Intent continueIntent;
    int[] continueRdIdQs;
    int continueScore;
    int continueTurn;
    int continueCount;
    int[] continueBtJudge;

    Button btNextLevel, btPlayAgain, btHomePage;
    FloatingActionButton btSound;
    Button[] types = {btNextLevel, btPlayAgain, btHomePage};
    SoundEffect soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_setting);

        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();

        btNextLevel = findViewById(R.id.btEasy1);
        btPlayAgain = findViewById(R.id.btEasy2);
        btHomePage = findViewById(R.id.btEasy3);
        btSound = findViewById(R.id.btSound);

        if(SoundEffect.loaded == true) {
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
        }else{
            btSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
        }


//        continueIntent = getIntent();
//        continueRdIdQs = continueIntent.getIntArrayExtra("continueRdIdQs");
//        continueScore = continueIntent.getIntExtra("continueTurn",0);
//        continueTurn = continueIntent.getIntExtra("continueTurn",0);
//        continueCount = continueIntent.getIntExtra("continueCount",0);
//        continueBtJudge = continueIntent.getIntArrayExtra("continueBtJudge");

        EasySelectListener selectListener = new EasySelectListener();
        btNextLevel.setOnClickListener(selectListener);
        btPlayAgain.setOnClickListener(selectListener);
        btHomePage.setOnClickListener(selectListener);
        btSound.setOnClickListener(selectListener);

        MainActivity.previousClass = "EasySettingActivity";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_menu_back) {
            soundEffect.playClick();
            soundEffect.stopEffectMusic();
            SoundEffect.loadedEffect = false;


//            if (MainActivity.previousClass == "EasyContinueActivity") {
//                continueIntent.putExtra("continueRdIdQs", continueRdIdQs);
//                continueIntent.putExtra("continueScore", continueScore);
//                continueIntent.putExtra("continueTurn", continueTurn);
//                continueIntent.putExtra("continueCount", continueCount);
//                continueIntent.putExtra("continueBtJudge", continueBtJudge);
//                Intent intent = new Intent(getApplication(), EasyContinueActivity.class);
//                startActivity(intent);
//            } else if (MainActivity.previousClass == "EasyActivity") {
//                Intent intent = new Intent(getApplication(), EasyActivity.class);
//                startActivity(intent);
//            }
            Intent intent = new Intent(getApplication(), EasyActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class EasySelectListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent;
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor editor;
            switch(view.getId()){
                case R.id.btEasy1:
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
                case R.id.btEasy2:
                    soundEffect.playClick();
                    soundEffect.stopEffectMusic();
                    SoundEffect.loadedEffect = false;
                    intent = new Intent(getApplication(),EasyActivity.class);
                    sharedPreferences = getSharedPreferences(EasyActivity.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply(); // (*)editer.commit();

                    startActivity(intent);
                    break;
                case R.id.btEasy3:
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
                        intent = new Intent(getApplication(),EasySettingActivity.class);
                        startActivity(intent);
                    }
                    Log.d("soundLoaded", Boolean.toString(SoundEffect.loaded));
                    break;

            }
        }
    }
}