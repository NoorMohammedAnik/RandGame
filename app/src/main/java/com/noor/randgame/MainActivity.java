package com.noor.randgame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    // Declare Object
    TextView txtHighScore,txtScore,txtPlaying;
    Button btnPlay,btnReset;

    //Declare SharedPreferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String PREFS_GAME ="com.noor.randgame.Game ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        txtHighScore=(TextView) findViewById(R.id.txtHighScore);
        txtScore=(TextView) findViewById(R.id.txtScore);

        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnReset=(Button)findViewById(R.id.btnReset);

        //Creating SharedPreferences name game and mode is private
        pref=getSharedPreferences(PREFS_GAME,MODE_PRIVATE);
        editor=pref.edit();

        //set initial score
        final int highScore = pref.getInt("highScore", 0);
        txtHighScore.setText("High Score : "+highScore);



        //Click listener for play button
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int score = random.nextInt(1000);
                txtScore.setText(String.valueOf(score));//convert int to String


                int getSaveScore=pref.getInt("highScore",0);

                //Checking score is greater than high score
                if(score>getSaveScore)
                {
                    txtHighScore.setText("High Score : "+score);
                    editor.putInt("highScore", score);
                    editor.commit();
                }
            }
        });

       //set click listener for reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("highScore", 0);
                editor.commit();

                //set the values to 0
                txtScore.setText(String.valueOf(0));
                txtHighScore.setText("High Score : 0");

                //In setText method,we can not write int value directly.
                //setText method accept only String values



            }
        });

    }
}
