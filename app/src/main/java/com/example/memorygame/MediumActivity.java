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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MediumActivity extends AppCompatActivity implements View.OnTouchListener , View.OnClickListener{
    public static final String SHARED_PREFERENCE_NAME = "MediumActivityContinue";
    private int SIZE = 6;
    private int MAX_STEP = 12;
    private View vScore;
    private TextView tvScore;
    private ImageView ivScore;
    private ImageView[] ivQs = new ImageView[SIZE];
    private View[] btQs = new View[SIZE];
    private int score = 0;
    private int[] idQs =  new int[SIZE];
    private int[] rdIdQs = new int[SIZE];
    private int idBack = R.drawable.nen;
    private int idWhite = R.drawable.trang;
    private int idWin = R.drawable.namtay;
    private int idLose = R.drawable.lebitreo;
    private int[] btJudge = new int[2];
    private int turn = 0;
    private int count = 0;
    private boolean canClick = true;
    private boolean[] btCanClick = new boolean[SIZE];
    private int[] statusId = new int[SIZE];
    SoundEffect soundEffect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        HighScoreActivity.mediumHighScore = 0;
        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();

        vScore = findViewById(R.id.vScore);
        tvScore = findViewById(R.id.tvScore);
        ivScore = findViewById(R.id.ivScore);

        tvScore.setText("FIGHTING");
        ivScore.setImageResource(idWhite);

        ivQs[0] = findViewById(R.id.iv5);
        ivQs[1] = findViewById(R.id.iv6);
        ivQs[2] = findViewById(R.id.iv7);
        ivQs[3] = findViewById(R.id.iv8);
        ivQs[4] = findViewById(R.id.iv9);
        ivQs[5] = findViewById(R.id.iv10);

        btQs[0] = findViewById(R.id.bt5);
        btQs[1] = findViewById(R.id.bt6);
        btQs[2] = findViewById(R.id.bt7);
        btQs[3] = findViewById(R.id.bt8);
        btQs[4] = findViewById(R.id.bt9);
        btQs[5] = findViewById(R.id.bt10);
        randIdImage();
        randIdQs();
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            for (int i = 0; i < SIZE; i++) {
                rdIdQs[i] = sharedPreferences.getInt("continueRdIdQs" + i, rdIdQs[i]);
            }
            for (int i = 0; i < SIZE; i++) {
                statusId[i] = sharedPreferences.getInt("continueStatusId" + i, idBack);
            }
            for (int i = 0; i < 2; i++) {
                btJudge[i] = sharedPreferences.getInt("continueBtJudge" + i, 0);
            }
            score = sharedPreferences.getInt("continueScore", 0);
            turn = sharedPreferences.getInt("continueTurn", 0);
            count = sharedPreferences.getInt("continueCount", 0);
            canClick = sharedPreferences.getBoolean("continueCanClick", true);

            editor.clear();
            editor.apply(); // (*)editer.commit();
        }catch (NullPointerException e){
            Log.d("debugSharePreferences", e.getMessage());
        }
        Log.d("debug onCreate" , "setBack");
        for(int i = 0 ; i < SIZE ; i++){
            btCanClick[i] = true;
            ivQs[i].setImageResource(statusId[i]);
        }
        Log.d("debug onCreate" , "setOnClick");
        btQs[0].setOnClickListener(this);
        btQs[1].setOnClickListener(this);
        btQs[2].setOnClickListener(this);
        btQs[3].setOnClickListener(this);
        btQs[4].setOnClickListener(this);
        btQs[5].setOnClickListener(this);
        vScore.setOnTouchListener(this);
        MainActivity.previousClass = "MediumActivity";
        for(int i = 0 ; i < SIZE ; i++) {
            Log.d("debug continueRdIdQs", Integer.toString(rdIdQs[i]));
        }
        for(int i = 0 ; i < SIZE ; i++) {
            Log.d("debug continueStatusId", Integer.toString(statusId[i]));
        }
        for(int i = 0 ; i < 2 ; i++) {
            Log.d("debug continueBtJudge", Integer.toString(btJudge[i]));
        }
        Log.d("debug continueScore" ,  Integer.toString(score));
        Log.d("debug ontinueTurn" ,  Integer.toString(turn));
        Log.d("debug continueCanClick" ,Boolean.toString(canClick));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_back_set,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_menu_back){
            soundEffect.playClick();
            soundEffect.stopEffectMusic();
            SoundEffect.loadedEffect = false;
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }else if(itemId == R.id.action_menu_set){
            soundEffect.playClick();
            soundEffect.stopEffectMusic();
            SoundEffect.loadedEffect = false;
            Intent intent = new Intent(getApplication(), MediumSettingActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for(int i = 0; i < SIZE ; i++){
                editor.putInt("continueRdIdQs"+ i ,rdIdQs[i]);
            }
            for(int i = 0; i < SIZE ; i++){
                editor.putInt("continueStatusId"+ i,statusId[i]);
            }
            for(int i = 0; i < 2 ; i++){
                editor.putInt("continueBtJudge"+ i, btJudge[i]);
            }
            editor.putInt("continueScore", score);
            editor.putInt("continueTurn", turn);
            editor.putInt("continueCount", count);
            editor.putBoolean("continueCanClick", canClick);
            editor.apply(); // (*)editer.commit();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(canClick == false) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    switch (view.getId()) {
                        case R.id.vScore:
                            soundEffect.playClick();
                            soundEffect.stopEffectMusic();
                            SoundEffect.loadedEffect = false;
                            Intent intent = new Intent(getApplication(), MediumSettingActivity.class);
                            startActivity(intent);
                            break;
                    }
            }
        }
        return true;
    }
    @Override
    public void onClick(View view) {
        soundEffect.playClick();
        if(canClick == true) {
            count++;
            switch (view.getId()) {
                case R.id.bt5:
                    if (btCanClick[0] == true) {
                        btJudge[turn] = 0;
                        btCanClick[0] = false;
                    }
                    break;
                case R.id.bt6:
                    if (btCanClick[1] == true) {
                        btJudge[turn] = 1;
                        btCanClick[1] = false;
                    }
                    break;
                case R.id.bt7:
                    if (btCanClick[2] == true) {
                        btJudge[turn] = 2;
                        btCanClick[2] = false;
                    }
                    break;
                case R.id.bt8:
                    if (btCanClick[3] == true) {
                        btJudge[turn] = 3;
                        btCanClick[3] = false;
                    }
                    break;
                case R.id.bt9:
                    if (btCanClick[4] == true) {
                        btJudge[turn] = 4;
                        btCanClick[4] = false;
                    }
                    break;
                case R.id.bt10:
                    if (btCanClick[5] == true) {
                        btJudge[turn] = 5;
                        btCanClick[5] = false;
                    }
                    break;
            }
            if (turn == 0) {
                for (int i = 0; i < SIZE; i++) {
                    btCanClick[i] = true;
                    ivQs[i].setImageResource(statusId[i]);
                }
            }
            tvScore.setText("SCORE : " + score + "        " + "STEP LEFT : " + (MAX_STEP - count) );

            ivQs[btJudge[turn]].setImageResource(rdIdQs[btJudge[turn]]);
            if (turn == 1) {
                judge();
            }
            Log.d("debug", "on_Click");
            Log.d("debug", "turn : " + turn);
            Log.d("debug", "btJudge : " + btJudge[turn]);
            Log.d("debug", "count : " + count);
            Log.d("debug", " ");
//            tvScore.setText("SCORE : " + score + "        " + "STEP LEFT : " + (MAX_STEP - count));

            if (turn == 0) turn = 1;
            else if (turn == 1) turn = 0;

            for(int i = 0 ; i < SIZE ; i++){
                if(rdIdQs[i] != idWhite) break;
                if (i == SIZE-1 && rdIdQs[i] == idWhite) {
                    canClick = false;
                    Log.d("debug", " can not click");
                }
            }

        }
    }

    public void randIdImage(){
        for(int i = 0 ; i < SIZE ; i++){
            int rand = (int)(Math.random()*MainActivity.idImage.length);
            idQs[i] = MainActivity.idImage[rand];
            idQs[++i] = MainActivity.idImage[rand];

        }
    }

    public void randIdQs(){
        for(int i = SIZE - 1 ; i >= 0 ; i--){
            for(int j = 0 ; j <= i ; j++) {
                int rand = (int)(Math.random()*i);
                rdIdQs[i] = idQs[rand];
                int swap = idQs[i];
                idQs[i] = idQs[rand];
                idQs[rand] = swap;
            }


        }
    }

    public void judge(){

        if(rdIdQs[btJudge[0]] == rdIdQs[btJudge[1]]) {
            soundEffect.playCorrect();
            score += 10;
            statusId[btJudge[0]] = idWhite;
            statusId[btJudge[1]] = idWhite;
            rdIdQs[btJudge[0]] = idWhite;
            rdIdQs[btJudge[1]] = idWhite;
            ivQs[btJudge[0]].setImageResource(idWhite);
            ivQs[btJudge[1]].setImageResource(idWhite);
            tvScore.setText("SCORE : " + score + "        " + "STEP LEFT : " + (MAX_STEP - count) );

        }else{
            soundEffect.playWrong();
        }
        if(count == MAX_STEP || score == SIZE*5){
            isWin();
            canClick = false;
        }
    }
    public void isWin(){
        if(score == SIZE*5 && count <= MAX_STEP ){
            soundEffect.playWin();
            for(int i = 0 ; i < SIZE ; i++){
                ivQs[i].setImageResource(statusId[i]);
            }
            score = (SIZE*5) + (MAX_STEP-count)*10;
            tvScore.setText("SCORE : " + score +"        " + " YOU WON " +"        " + "CLICK HERE");
            ivScore.setImageResource(idWin);
            HighScoreActivity.mediumHighScore = score;
        }else {
            soundEffect.playLose();
            tvScore.setText("YOU LOSE" + "        " + "CLICK HERE");
            ivScore.setImageResource(idLose);
        }
    }

}