package com.github.xwc.couponview;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;

import com.github.xwc.view.CouponView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExampleFragment3 extends Fragment {

    @BindView(R.id.couponView)
    CouponView couponView;
    @BindView(R.id.shapeLeft)
    CheckBox shapeLeft;
    @BindView(R.id.shapeTop)
    CheckBox shapeTop;
    @BindView(R.id.shapeRight)
    CheckBox shapeRight;
    @BindView(R.id.shapeBottom)
    CheckBox shapeBottom;
    @BindView(R.id.lineLeft)
    CheckBox lineLeft;
    @BindView(R.id.lineTop)
    CheckBox lineTop;
    @BindView(R.id.lineRight)
    CheckBox lineRight;
    @BindView(R.id.lineBottom)
    CheckBox lineBottom;

    @BindView(R.id.controlDashWidth)
    SeekBar controlDashWidth;
    @BindView(R.id.controlLineMargin)
    SeekBar controlLineMargin;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //通过代码设置属性
        couponView.setDrawType(CouponView.CIRCLE)
            .setBgc(Color.parseColor("#AD5A5A"))
            .setDashGap(dpToPx(5))
            .setDashWidth(dpToPx(5))
            .setDrawRightShape(true)
            .setDrawLeftShape(true)
            .setDrawTopLine(true)
            .setDrawBottomLine(true)
            .setLineMarginBottom(dpToPx(10))
            .setLineMarginTop(dpToPx(10))
            .setLineMarginLeft(dpToPx(10))
            .setLineMarginRight(dpToPx(10))
            .setLineColor(Color.WHITE);

        //控制半径大小
        controlDashWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                couponView.setDashWidth(dpToPx(progress));
                couponView.reDraw();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //控制虚线边距
        controlLineMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                couponView.setLineMarginLeft(dpToPx(progress));
                couponView.reDraw();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick({R.id.circle, R.id.oval, R.id.triangle, R.id.square, R.id.shapeLeft, R.id.shapeTop, R.id.shapeRight, R.id.shapeBottom, R.id.lineLeft, R.id.lineTop, R.id.lineRight, R.id.lineBottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.circle:
                couponView.setDrawType(CouponView.CIRCLE);
                break;
            case R.id.oval:
                couponView.setDrawType(CouponView.OVAL);
                break;
            case R.id.triangle:
                couponView.setDrawType(CouponView.TRIANGLE);
                break;
            case R.id.square:
                couponView.setDrawType(CouponView.SQUARE);
                break;
            case R.id.shapeLeft:
                if (shapeLeft.isChecked()) {
                    couponView.setDrawLeftShape(true);
                } else {
                    couponView.setDrawLeftShape(false);
                }
                break;
            case R.id.shapeTop:
                if (shapeTop.isChecked()) {
                    couponView.setDrawTopShape(true);
                } else {
                    couponView.setDrawTopShape(false);
                }
                break;
            case R.id.shapeRight:
                if (shapeRight.isChecked()) {
                    couponView.setDrawRightShape(true);
                } else {
                    couponView.setDrawRightShape(false);
                }
                break;
            case R.id.shapeBottom:
                if (shapeBottom.isChecked()) {
                    couponView.setDrawBottomShape(true);
                } else {
                    couponView.setDrawBottomShape(false);
                }
                break;
            case R.id.lineLeft:
                if (lineLeft.isChecked()) {
                    couponView.setDrawLeftLine(true);
                } else {
                    couponView.setDrawLeftLine(false);
                }
                break;
            case R.id.lineTop:
                if (lineTop.isChecked()) {
                    couponView.setDrawTopLine(true);
                } else {
                    couponView.setDrawTopLine(false);
                }
                break;
            case R.id.lineRight:
                if (lineRight.isChecked()) {
                    couponView.setDrawRightLine(true);
                } else {
                    couponView.setDrawRightLine(false);
                }
                break;
            case R.id.lineBottom:
                if (lineBottom.isChecked()) {
                    couponView.setDrawBottomLine(true);
                } else {
                    couponView.setDrawBottomLine(false);
                }
                break;
        }

        //重新绘制
        couponView.reDraw();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
