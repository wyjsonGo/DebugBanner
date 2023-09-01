package com.wyjson.debug_banner.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wyjson.debug_banner.Banner;
import com.wyjson.debug_banner.demo.R;
import com.wyjson.debug_banner.BannerGravity;
import com.wyjson.debug_banner.BannerView;

public class TwoActivity extends AppCompatActivity implements BannerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TwoActivity.this, ThreeActivity.class));
            }
        });
    }

    @Override
    public Banner newBanner() {
        return new Banner(BannerGravity.START, android.R.color.holo_blue_light, android.R.color.black, "BETA");
    }
}
