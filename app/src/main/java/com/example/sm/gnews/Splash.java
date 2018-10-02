package com.example.sm.gnews;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private final int splash_time=3000;
    Animation top,bot;
    ImageView im1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        im1=(ImageView)findViewById(R.id.image);
        t1=(TextView)findViewById(R.id.text);
        top= AnimationUtils.loadAnimation(this,R.anim.anima);
        bot= AnimationUtils.loadAnimation(this,R.anim.frombott);
        t1.setAnimation(bot);
        im1.setAnimation(top);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(intent);
                Splash.this.finish();
            }
        },splash_time);
    }
}
