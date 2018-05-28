package com.github.xwc.couponview;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.xwc.view.CouponView;

public class MainActivity extends AppCompatActivity {

    private CouponView couponView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        couponView = findViewById(R.id.couponView);

        couponView.setBgc(Color.parseColor("#C0C0C0"))
            .setDashGap(dpToPx(5))
            .setDashWidth(dpToPx(5))
            .setDrawType(CouponView.SQUARE)
            .setDrawBottomShape(true);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
