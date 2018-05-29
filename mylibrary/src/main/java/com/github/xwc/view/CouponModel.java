package com.github.xwc.view;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by xwc on 2018/5/26.
 */

public class CouponModel extends DrawModel {

    public CouponModel(CouponView view) {
        super(view);
    }

    @Override
    protected void drawCircle() {

        //遍历垂直方向可绘制size
        for (int i = 0; i < verItemSize; i++) {
            //圆心位置 = 间隙 + item半径 + 垂直方向多余部分/2 + （间隙 + item半径*2） * i
            float y = view.getDashGap() + view.getDashWidth() + verRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            if (view.isDrawLeftShape()) {
                mCanvas.drawCircle(0, y, view.getDashWidth(), shapePaint);
            }
            if (view.isDrawRightShape()) {
                mCanvas.drawCircle(view.getWidth(), y, view.getDashWidth(), shapePaint);
            }
        }

        //遍历水平方向可绘制size
        for (int i = 0; i < horItemSize; i++) {
            float x = view.getDashGap() + view.getDashWidth() + horRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            mCanvas.drawCircle(x, 0, view.getDashWidth(), shapePaint);
            if (view.isDrawTopShape()) {
                mCanvas.drawCircle(x, 0, view.getDashWidth(), shapePaint);
            }
            if (view.isDrawBottomShape()) {
                mCanvas.drawCircle(x, view.getHeight(), view.getDashWidth(), shapePaint);
            }
        }
    }

    @Override
    protected void drawOval() {
        for (int i = 0; i < horItemSize; i++) {
            float x = view.getDashGap() + view.getDashWidth() + horRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);

            RectF rectf = new RectF();
            // 设置椭圆大小
            rectf.left = x - view.getDashWidth();
            rectf.right = x + view.getDashWidth();
            // 绘制上面的椭圆
            if (view.isDrawTopShape()) {
                rectf.top = 0;
                rectf.bottom = view.getDashWidth();
                mCanvas.drawOval(rectf, shapePaint);
            }
            // 绘制下面的椭圆
            if (view.isDrawBottomShape()) {
                rectf.top = view.getHeight() - view.getDashWidth();
                rectf.bottom = view.getHeight();
                mCanvas.drawOval(rectf, shapePaint);
            }
        }

        for (int i = 0; i < verItemSize; i++) {
            float y = view.getDashGap() + view.getDashWidth() + verRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            // 定义椭圆对象
            RectF rectf = new RectF();
            // 设置椭圆大小
            rectf.top = y - view.getDashWidth();
            rectf.bottom = y + view.getDashWidth();
            // 绘制椭圆
            if (view.isDrawLeftShape()) {
                rectf.left = 0;
                rectf.right = view.getDashWidth();
                mCanvas.drawOval(rectf, shapePaint);
            }

            // 绘制椭圆
            if (view.isDrawRightShape()) {
                rectf.left = view.getWidth() - view.getDashWidth();
                rectf.right = view.getWidth();
                mCanvas.drawOval(rectf, shapePaint);
            }
        }
    }

    @Override
    protected void drawSquare() {

        for (int i = 0; i < verItemSize; i++) {
            float y = view.getDashGap() + view.getDashWidth() + verRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);

            RectF rectf = new RectF();
            // 设置正方形大小
            rectf.top = y - view.getDashWidth() / 2;
            rectf.bottom = y + view.getDashWidth();

            // 如果isDrawLeftSide为true
            if (view.isDrawLeftShape()) {
                rectf.left = 0;
                rectf.right = view.getDashWidth() ;
                mCanvas.drawRect(rectf, shapePaint);
            }

            if (view.isDrawRightShape()) {
                rectf.left = view.getWidth() - view.getDashWidth();
                rectf.right = view.getWidth();
                mCanvas.drawRect(rectf, shapePaint);
            }
        }

        for (int i = 0; i < horItemSize; i++) {
            float x = view.getDashGap() + view.getDashWidth() + horRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            mCanvas.drawRect(0, x, 0, view.getDashWidth(), shapePaint);

            RectF rectf = new RectF();
            // 设置正方形大小
            rectf.left = x - view.getDashWidth() / 2;
            rectf.right = x + view.getDashWidth() / 2;

            // 如果isDrawTopSide为true
            if (view.isDrawTopShape()) {
                rectf.top = 0;
                rectf.bottom = view.getDashWidth();
                mCanvas.drawRect(rectf, shapePaint);
            }

            if (view.isDrawBottomShape()) {
                rectf.top = view.getHeight() - view.getDashWidth();
                rectf.bottom = view.getHeight();
                mCanvas.drawRect(rectf, shapePaint);
            }
        }
    }

    @Override
    protected void drawTriangle() {
        for (int i = 0; i < verItemSize; i++) {
            float y = view.getDashGap() + view.getDashWidth() + verRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);

            Path path = new Path();
            //如果isDrawLeftSide为true
            if (view.isDrawLeftShape()) {
                // 设置三角形的点
                path.moveTo(0, y - view.getDashWidth());
                path.lineTo(0, y + view.getDashWidth());
                path.lineTo(view.getDashWidth(), y);
                path.close();
                mCanvas.drawPath(path, shapePaint);
            }

            if (view.isDrawRightShape()) {
                path.moveTo(view.getWidth(), y - view.getDashWidth());
                path.lineTo(view.getWidth(), y + view.getDashWidth());
                path.lineTo(view.getWidth() - view.getDashWidth(), y);
                path.close();
                mCanvas.drawPath(path, shapePaint);
            }
        }

        for (int i = 0; i < horItemSize; i++) {
            float x = view.getDashGap() + view.getDashWidth() + horRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);

            Path path = new Path();
            //如果isDrawTopSide为true
            if (view.isDrawTopShape()) {
                // 设置三角形的点
                path.moveTo(x - view.getDashWidth(), 0);
                path.lineTo(x + view.getDashWidth(), 0);
                path.lineTo(x, view.getDashWidth());
                path.close();
                mCanvas.drawPath(path, shapePaint);
            }

            if (view.isDrawBottomShape()) {
                path.moveTo(x - view.getDashWidth(), view.getHeight());
                path.lineTo(x + view.getDashWidth(), view.getHeight());
                path.lineTo(x, view.getHeight() - view.getDashWidth());
                path.close();
                mCanvas.drawPath(path, shapePaint);
            }
        }
    }

    @Override
    protected void drawLine() {
        if (!view.isDrawLeftLine() && !view.isDrawRightLine() && !view.isDrawTopLine() && !view.isDrawTopLine()) {
            return;
        }

        //遍历垂直方向可绘制size
        for (int i = 0; i < verItemSize; i++) {
            //圆心位置 = 间隙 + item半径 + 垂直方向多余部分/2 + （间隙 + item半径*2） * i
            float y = view.getDashGap() + view.getDashWidth() + verRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            if (view.isDrawLeftLine()) {
                mCanvas.drawLine(view.getLineMarginLeft(), y - view.getDashWidth(), view.getLineMarginLeft(), y + view.getDashWidth(), linePaint);
            }
            if (view.isDrawRightLine()) {
                mCanvas.drawLine(view.getWidth() - view.getLineMarginRight(), y - view.getDashWidth(), view.getWidth() - view.getLineMarginRight(), y + view.getDashWidth(), linePaint);
            }
        }

        //遍历水平方向可绘制size
        for (int i = 0; i < horItemSize; i++) {
            float x = view.getDashGap() + view.getDashWidth() + horRedundancy / 2 + ((view.getDashGap() + view.getDashWidth() * 2) * i);
            if (view.isDrawTopLine()) {
                mCanvas.drawLine(x - view.getDashWidth(), view.getLineMarginTop(), x + view.getDashWidth(), view.getLineMarginTop(), linePaint);
            }
            if (view.isDrawBottomLine()) {
                mCanvas.drawLine(x - view.getDashWidth(), view.getHeight() - view.getLineMarginBottom(), x + view.getDashWidth(), view.getHeight() - view.getLineMarginBottom(), linePaint);
            }
        }
    }
}
