package com.skyfishjy.ripplebackground.sample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;
import com.skyfishjy.library.ripplebackground.sample.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ImageView foundDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RippleBackground rippleBackground = findViewById(R.id.content);

        final Handler handler = new Handler();

        foundDevice = findViewById(R.id.foundDevice);
        ImageView button = findViewById(R.id.centerImage);
        button.setOnClickListener(view -> {
            rippleBackground.startRippleAnimation();
            handler.postDelayed(() -> {
                foundDevice();
                rippleBackground.stopRippleAnimation();
            }, 3000);
        });
    }

    private void foundDevice() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        foundDevice.setVisibility(View.VISIBLE);
        animatorSet.start();
    }
}
