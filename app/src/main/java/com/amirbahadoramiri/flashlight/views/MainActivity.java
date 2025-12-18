package com.amirbahadoramiri.flashlight.views;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;

import com.amirbahadoramiri.flashlight.R;
import com.amirbahadoramiri.flashlight.tools.cameraflasher.CameraFlasher;

public class MainActivity extends BaseActivity {

    CameraFlasher cameraFlasher = new CameraFlasher();
    AppCompatImageView lightImageview;
    SwitchCompat switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdge();
        setContentView(R.layout.activity_main);
        setViewCompat();
        findViews();
        setupViews();
    }

    public void fadeIn(int duration) {
//        AnimationSet set = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(0.2F, 1F);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
//        set.addAnimation(alphaAnimation);
        lightImageview.startAnimation(alphaAnimation);
    }

    public void fadeOut(int duration) {
//        AnimationSet set = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(1F, 0.2F);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
//        set.addAnimation(alphaAnimation);
        lightImageview.startAnimation(alphaAnimation);
    }

    private void findViews() {
        lightImageview = findViewById(R.id.light_imageview);
        switchButton = findViewById(R.id.switchButton);
        fadeOut(0);
    }

    private void setupViews() {
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cameraFlasher.init(MainActivity.this);
            if (isChecked) {
                cameraFlasher.turnOnFlash(success2 -> {
                    fadeIn(1000);
                });
            } else {
                cameraFlasher.turnOffFlash(success1 -> {
                    fadeOut(1000);
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        cameraFlasher.init(this);
        cameraFlasher.turnOffFlash(success -> {
        });
    }
}