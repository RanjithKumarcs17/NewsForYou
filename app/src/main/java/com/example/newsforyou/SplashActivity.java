package com.example.newsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation topanim,botanim,leftanim,rightanim;
    ImageView splas_image;
    TextView splas_text,splas_text2;
    ProgressBar progressBar;
    int progress = 0;
    CharSequence charSequence;
    int index;
    long delay=300;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topanim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        botanim= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        leftanim= AnimationUtils.loadAnimation(this, R.anim.left_animation);
        rightanim= AnimationUtils.loadAnimation(this, R.anim.right_animation);



        splas_image= findViewById(R.id.splash_image);
        splas_text = findViewById(R.id.splash_text);
        splas_text2= findViewById(R.id.splash_text2);
        progressBar= findViewById(R.id.progressBar);

        progressBar.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        splas_image.setAnimation(topanim);
        animatText("NEWS FOR YOU");
        splas_text2.setAnimation(rightanim);

        setProgressBarValue(progress);

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },5000);
    }

    private void setProgressBarValue(final int progress){
        progressBar.setProgress(progress);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   Thread.sleep(1000);
               }
               catch (Exception e){

                   e.printStackTrace();
               }
               setProgressBarValue(progress+20);


            }
        });
        thread.start();
    }

    Runnable runnable= new Runnable() {
        @Override
        public void run() {
            splas_text.setText(charSequence.subSequence(0,index++));

            if(index <= charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void animatText(CharSequence cs){
        charSequence = cs;

        index = 0;

        splas_text.setText("");

        handler.removeCallbacks(runnable);

        handler.postDelayed(runnable,delay);
    }
}