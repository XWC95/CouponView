package com.github.xwc.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import static com.github.xwc.view.CouponView.CIRCLE;
import static com.github.xwc.view.CouponView.ELLIPSE;
import static com.github.xwc.view.CouponView.SQUARE;
import static com.github.xwc.view.CouponView.TRIANGLE;

/**
 * Created by xwc on 2018/5/28.
 */

public abstract class DrawModel {

    protected final CouponView view;

    //item数量
    protected int horItemSize;
    protected int verItemSize;

    //水平方向除item外多余的部分
    protected float horRedundancy;
    //垂直方向除item外多余的部分
    protected float verRedundancy;

    protected Paint shapePaint;
    protected Paint linePaint;

    protected Canvas mCanvas;

    private void initPaint() {
        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shapePaint.setDither(true);
        shapePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        shapePaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(view.getLineColor());
        linePaint.setStrokeWidth(view.getLineWidth());
    }

    public DrawModel(CouponView view) {
        this.view = view;
        initPaint();
    }

    public void setCanvas(Canvas canvas) {
        this.mCanvas = canvas;
    }

    public void drawShape() {

        if (view.getDrawType() == CIRCLE) {
            drawCircle();
        } else if (view.getDrawType() == ELLIPSE) {
            drawOval();
        } else if (view.getDrawType() == TRIANGLE) {
            drawTriangle();
        } else if (view.getDrawType() == SQUARE) {
            drawSquare();
        }
    }

    protected abstract void drawLine();

    /**
     * 测量垂直item数目
     *
     * @param h
     */
    public void measureVelNum(int h) {
        if (verRedundancy == 0) {
            verRedundancy = (h - view.getDashGap()) % (view.getDashWidth() * 2 + view.getDashGap());
        }
        verItemSize = (int) ((h - view.getDashGap()) / (view.getDashWidth() * 2 + view.getDashGap()));
    }

    /**
     * 测量水平的item数目
     *
     * @param w
     */
    public void measureHorNum(int w) {
        if (horRedundancy == 0) {
            horRedundancy = (w - view.getDashGap()) % (view.getDashWidth() * 2 + view.getDashGap());
        }
        horItemSize = (int) ((w - view.getDashGap()) / (view.getDashWidth() * 2 + view.getDashGap()));
    }

    /**
     * 绘制正方形
     */
    protected abstract void drawSquare();

    /**
     * 绘制三角形
     */
    protected abstract void drawTriangle();

    /**
     * 绘制椭圆
     */
    protected abstract void drawOval();

    /**
     * 绘制三角形
     */
    protected abstract void drawCircle();
}
