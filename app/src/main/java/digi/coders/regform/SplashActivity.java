package digi.coders.regform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text1 = findViewById(R.id.st1);
        text2 = findViewById(R.id.st2);
        text3 = findViewById(R.id.st3);
        text4 = findViewById(R.id.st4);
        img1 = findViewById(R.id.img1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent a = new Intent(SplashActivity.this, MainActivity.class);
                a.putExtra("flag", "SUBMIT");
                startActivity(a);
                finish();
            }
        }, 10000);

        Animation rotate = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate);
        Animation alfa1 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate1);
        Animation alfa2 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate2);
        Animation alfa3 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate3);
        Animation alfa4 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate4);

        text1.startAnimation(alfa1);
        text2.startAnimation(alfa2);
        text3.startAnimation(alfa3);
        text4.startAnimation(alfa4);

        img1.startAnimation(rotate);


    }
}