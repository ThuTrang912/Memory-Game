package com.example.memorygame;

import static com.example.memorygame.R.layout.activity_high_score;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class HighScoreActivity extends AppCompatActivity {
    public static int highScore;
    public static int easyHighScore;
    public static int mediumHighScore;
    public static int hardHighScore;
    public static TreeSet<Integer> highScores = new TreeSet<>();
    public static String highScoreFileName = "HighScore.txt";
    private TextView[] tvRank = new TextView[10];
    private TextView[] tvScore = new TextView[10];
    SoundEffect soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScore = easyHighScore + mediumHighScore + hardHighScore;

        SoundEffect.loadedEffect = true;
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.start();

        tvRank[0] = findViewById(R.id.tvRank1);
        tvRank[1] = findViewById(R.id.tvRank2);
        tvRank[2] = findViewById(R.id.tvRank3);
        tvRank[3] = findViewById(R.id.tvRank4);
        tvRank[4] = findViewById(R.id.tvRank5);
        tvRank[5] = findViewById(R.id.tvRank6);
        tvRank[6] = findViewById(R.id.tvRank7);
        tvRank[7] = findViewById(R.id.tvRank8);
        tvRank[8] = findViewById(R.id.tvRank9);
        tvRank[9] = findViewById(R.id.tvRank10);

        tvScore[0] = findViewById(R.id.tvScore1);
        tvScore[1] = findViewById(R.id.tvScore2);
        tvScore[2] = findViewById(R.id.tvScore3);
        tvScore[3] = findViewById(R.id.tvScore4);
        tvScore[4] = findViewById(R.id.tvScore5);
        tvScore[5] = findViewById(R.id.tvScore6);
        tvScore[6] = findViewById(R.id.tvScore7);
        tvScore[7] = findViewById(R.id.tvScore8);
        tvScore[8] = findViewById(R.id.tvScore9);
        tvScore[9] = findViewById(R.id.tvScore10);

        writeHighScoreToFile(highScoreFileName,highScore);
        readHighScoreFromFile(highScoreFileName);
        try {
            for (int i = 0; i < tvRank.length; i++) {
                try{
                    tvRank[i].setText(Integer.toString(i + 1));
                    tvScore[i].setText(Integer.toString(highScores.last()));
                    highScores.remove(highScores.last());
                }catch(NoSuchElementException e){
                    tvScore[i].setText("0");
                    e.printStackTrace();
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
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
            if(MainActivity.previousClass == "MainSettingActivity") {
                Intent intent = new Intent(getApplication(), MainSettingActivity.class);
                startActivity(intent);
            }else if(MainActivity.previousClass == "HardSettingActivity") {
                Intent intent = new Intent(getApplication(), HardSettingActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void writeHighScoreToFile(String highScoreFileName, int highScore){
        Log.d("write" , Integer.toString(highScore));
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(highScoreFileName, MODE_APPEND); //mở file nếu chưa có và truyền vào fileName và mode là APPEND thì có thể viết thên vào được
            OutputStreamWriter os = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(os);
            bw.write(Integer.toString(highScore));
            bw.write("\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void readHighScoreFromFile(String highScoreFileName){
        FileInputStream fis = null;
        try {
            fis = openFileInput(highScoreFileName);
            InputStreamReader is = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(is);
            String line = null;
            while((line = br.readLine()) != null){
                Log.d("read" , line);
                highScores.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}