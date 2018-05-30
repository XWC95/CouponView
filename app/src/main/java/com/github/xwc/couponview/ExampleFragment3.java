package com.github.xwc.couponview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
}
