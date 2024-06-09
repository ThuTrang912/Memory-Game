package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyContinueActivity extends AppCompatActivity implements View.OnTouchListener{
    Intent continueIntent;

    private int SIZE = 4;
    private int MAX_STEP = 8;
    private View vScore;
    private TextView tvScore;
    private ImageView ivScore;
    private ImageView[] ivQs = new ImageView[SIZE];
    private View[] btQs = new View[SIZE];

    private int score = 0;

    private int[] rdIdQs = new int[SIZE];

    private int idBack = R.drawable.nen;
    private int idWhite = R.drawable.trang;
    private int idWin = R.drawable.namtay;
    private int idLose = R.drawable.lebitreo;
    private int[] btJudge = new int[2];
    private int turn = 0;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_continue);

        vScore = findViewById(R.id.vScore);
        tvScore = findViewById(R.id.tvScore);
        ivScore = findViewById(R.id.ivScore);

        continueIntent = getIntent();
        rdIdQs = continueIntent.getIntArrayExtra("continueRdIdQs");
        score = continueIntent.getIntExtra("continueTurn",0);
        turn = continueIntent.getIntExtra("continueTurn",0);
        count = continueIntent.getIntExtra("continueCount",0);
        btJudge = continueIntent.getIntArrayExtra("continueBtJudge");

        tvScore.setText("FIGHTING");
        ivScore.setImageResource(idWhite);

        ivQs[0] = findViewById(R.id.iv1);
        ivQs[1] = findViewById(R.id.iv2);
        ivQs[2] = findViewById(R.id.iv3);
        ivQs[3] = findViewById(R.id.iv4);

        btQs[0] = findViewById(R.id.bt1);
        btQs[1] = findViewById(R.id.bt2);
        btQs[2] = findViewById(R.id.bt3);
        btQs[3] = findViewById(R.id.bt4);

        for(int i = 0 ; i < SIZE ; i++){
            if(rdIdQs[i] != idWhite) {
                ivQs[i].setImageResource(idBack);
            }else{
                ivQs[i].setImageResource(idWhite);
            }
        }
//        for(int i = 0 ; i < SIZE ; i++){
//            ivQs[i].setImageResource(rdIdQs[i]);
//        }
        if(count < MAX_STEP){
            btQs[0].setOnTouchListener(this);
            btQs[1].setOnTouchListener(this);
            btQs[2].setOnTouchListener(this);
            btQs[3].setOnTouchListener(this);
        }
        vScore.setOnTouchListener(this);

        MainActivity.previousClass = "EasyContinueActivity";

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
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }else if(itemId == R.id.action_menu_set){
            Intent intent = new Intent(getApplication(), EasySettingActivity.class);
            intent.putExtra("continueRdIdQs",rdIdQs);
            intent.putExtra("continueScore", score);
            intent.putExtra("continueTurn", turn);
            intent.putExtra("continueCount", count);
            intent.putExtra("continueBtJudge",btJudge);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        if(turn == 0 && count%2 == 0){
//            turn = 1;
//        }else if(turn == 1 && count%2 == 0){
//            turn = 0;
//        }
//        count++;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                count++;
                Log.d("debug", "turn : " + turn);
                Log.d("debug", "btJudge : " + btJudge[turn]);
                Log.d("debug", "count : " + count);
                Log.d("debug", " ");

                switch (view.getId()) {
                    case R.id.vScore:
                        count--;
                        Intent intent = new Intent(getApplication(), EasySettingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.bt1:
                        btJudge[turn] = 0;
                        break;
                    case R.id.bt2:
                        btJudge[turn] = 1;
                        break;
                    case R.id.bt3:
                        btJudge[turn] = 2;
                        break;
                    case R.id.bt4:
                        btJudge[turn] = 3;
                        break;
                }
                ivQs[btJudge[turn]].setImageResource(rdIdQs[btJudge[turn]]);
                tvScore.setText("SCORE : " + score + "        " + "STEP LEFT : " + (MAX_STEP - count));

                if(turn == 0){
                    turn = 1;
                }else if(turn == 1){
                    turn = 0;
                }
            case MotionEvent.ACTION_UP:
                if (turn == 0) {
                    judge();
                }

        }
        return true;
    }


    public void judge(){
        if(rdIdQs[btJudge[0]] == rdIdQs[btJudge[1]]) {
            score += 5;
            rdIdQs[btJudge[0]] = idWhite;
            rdIdQs[btJudge[1]] = idWhite;
            ivQs[btJudge[0]].setImageResource(rdIdQs[btJudge[0]]);
            ivQs[btJudge[1]].setImageResource(rdIdQs[btJudge[1]]);
        }else{
            ivQs[btJudge[0]].setImageResource(idBack);
            ivQs[btJudge[1]].setImageResource(idBack);
        }
        if(score == (SIZE*5) && count <= MAX_STEP){
            score = (SIZE*5) + (MAX_STEP-count)*10;
            tvScore.setText("SCORE : " + score + "        " + "STEP LEFT : " + (MAX_STEP - count));
            ivScore.setImageResource(idWin);
        }else if(count > MAX_STEP){
            tvScore.setText("YOU LOSE");
            ivScore.setImageResource(idLose);
        }
    }
}