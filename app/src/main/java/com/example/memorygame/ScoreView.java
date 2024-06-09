package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ScoreView extends AppCompatActivity implements View.OnTouchListener {

    private TextView tvYourScore;
    private View vYourScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);

        int highScore = HighScoreActivity.easyHighScore + HighScoreActivity.mediumHighScore +HighScoreActivity.hardHighScore;

        tvYourScore = findViewById(R.id.tvYourScore);
        vYourScore = findViewById(R.id.vYourScore);

        tvYourScore.setText(Integer.toString(highScore));
        vYourScore.setOnTouchListener(this);


//        Intent intent = new Intent(getApplication(),HighScoreActivity.class);
//        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intent intent = new Intent(getApplication(),HighScoreActivity.class);
        startActivity(intent);
        return false;
    }
}