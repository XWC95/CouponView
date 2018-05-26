package com.github.xwc.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * 优惠卷
 * Created by xwc on 2017/5/25.
 */
public class CouponView extends FrameLayout {
    private Paint mPaint;

    //item间距 默认是5dp
    private float mGap;

    //绘制的图层
    private Bitmap mBitmap;
    private Canvas mCanvas;

    //item半径 默认是10dp
    private float mRadius;

    //item数量
    private int mCircleNum_H;
    private int verticalItemSize;

    //除过item和间隙外多余出来的部分
    private float mRemain_H;//水平
    private float mRemain_V;//垂直


    //画笔颜色
    private int mPaintColor;


    //是否绘制左边,默认绘制
    private boolean isDrawLeftSide = true;
    private boolean isDrawTopSide = true;
    private boolean isDrawRightSide = true;
    private boolean isDrawBottomSide = true;


    //锯齿形状 （圆形，椭圆，三角形，正方形）
    private int drawType;
    public static final int CIRCLE = 0;
    public static final int ELLIPSE = 1;
    public static final int TRIANGLE = 2;
    public static final int SQUARE = 3;


    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CouponView, 0, 0);
            drawType = typedArray.getInt(R.styleable.CouponView_drawType, CIRCLE);
            mGap = typedArray.getDimensionPixelOffset(R.styleable.CouponView_dashGap, 5);
            mRadius = typedArray.getDimensionPixelOffset(R.styleable.CouponView_dashWidth, 10);
            mPaintColor = typedArray.getColor(R.styleable.CouponView_bgc, 0xFFc0c0c0);
            isDrawLeftSide = typedArray.getBoolean(R.styleable.CouponView_isDrawLeftSide, true);
            isDrawTopSide = typedArray.getBoolean(R.styleable.CouponView_isDrawTopSide, true);
            isDrawRightSide = typedArray.getBoolean(R.styleable.CouponView_isDrawRightSide, true);
            isDrawBottomSide = typedArray.getBoolean(R.styleable.CouponView_isDrawBottomSide, true);
            typedArray.recycle();
        }
        initPaint();
    }

    private void initPaint() {//边缘锯齿画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * item数量的 计算公式 ：
     * circleNum = (int) ((w-gap)/(2*radius+gap));
     */

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawCanvas(w, h);


        if (isDrawLeftSide || isDrawRightSide) {
            measureVelNum(h);
        }
        if (isDrawTopSide || isDrawBottomSide) {
            measureHorNum(w);
        }

    }


    /**
     * 初始化绘制图层
     *
     * @param w
     * @param h
     */
    private void initDrawCanvas(int w, int h) {

        if (getBackground() == null) {//背景未设置情况下，设置为透明背景
            setBackgroundColor(Color.TRANSPARENT);
        }

        // 初始化锯齿遮盖图层
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        // 绘制图层颜色
        mCanvas.drawColor(mPaintColor);
    }

    /**
     * 测量水平的item数目
     *
     * @param w
     */
    private void measureHorNum(int w) {
        if (mRemain_H == 0) {
            mRemain_H = (w - mGap) % (mRadius * 2 + mGap);
        }
        mCircleNum_H = (int) ((w - mGap) / (mRadius * 2 + mGap));
    }

    /**
     * 测量垂直item数目
     *
     * @param h
     */
    private void measureVelNum(int h) {
        if (mRemain_V == 0) {
            mRemain_V = (h - mGap) % (mRadius * 2 + mGap);
        }
        verticalItemSize = (int) ((h - mGap) / (mRadius * 2 + mGap));
    }


    /**
     * 绘制锯齿
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);//绘制图层


        if (drawType == CIRCLE) {
            drawCircle();

        } else if (drawType == ELLIPSE) {
            drawOval();

        } else if (drawType == TRIANGLE) {
            drawTriangle();

        } else if (drawType == SQUARE) {
            drawSquare();

        }


    }

    private void drawSquare() {
        for (int i = 0; i < verticalItemSize; i++) {
            float y = mGap + mRadius + mRemain_V / 2 + ((mGap + mRadius * 2) * i);
            // 定义椭圆对象
            RectF rectf = new RectF();
            // 设置椭圆大小
            rectf.top = y - mRadius / 2;
            rectf.bottom = y + mRadius;
            // 绘制椭圆
            if (isDrawLeftSide) {
                rectf.left = 0;
                rectf.right = mRadius / 2;
                mCanvas.drawRect(rectf, mPaint);
            }

            if (isDrawRightSide) {
                rectf.left = getWidth() - mRadius;
                rectf.right = getWidth();
                mCanvas.drawRect(rectf, mPaint);
            }
        }

        for (int i = 0; i < mCircleNum_H; i++) {
            float x = mGap + mRadius + mRemain_H / 2 + ((mGap + mRadius * 2) * i);

            mCanvas.drawRect(0, x, 0, mRadius, mPaint);
            // 定义正方形对象
            RectF rectf = new RectF();
            // 设置正方形大小
            rectf.left = x - mRadius / 2;
            rectf.right = x + mRadius / 2;

            // 绘制上面的正方形
            if (isDrawTopSide) {
                rectf.top = 0;
                rectf.bottom = mRadius;
                mCanvas.drawRect(rectf, mPaint);
            }

            // 绘制下面的正方形
            if (isDrawBottomSide) {
                rectf.top = getHeight() - mRadius;
                rectf.bottom = getHeight();
                mCanvas.drawRect(rectf, mPaint);
            }
        }

    }

    private void drawTriangle() {
        for (int i = 0; i < verticalItemSize; i++) {
            float y = mGap + mRadius + mRemain_V / 2 + ((mGap + mRadius * 2) * i);
            // 绘制三角形
            Path path = new Path();
            // 设置多边形的点
            if (isDrawLeftSide) {
                path.moveTo(0, y - mRadius);
                path.lineTo(0, y + mRadius);
                path.lineTo(mRadius, y);
                path.lineTo(0, y - mRadius);
                // 使这些点构成封闭的多边形
                path.close();
                mCanvas.drawPath(path, mPaint);
            }

            //绘制下边缘
            if (isDrawRightSide) {
                path.moveTo(getWidth(), y - mRadius);
                path.lineTo(getWidth(), y + mRadius);
                path.lineTo(getWidth() - mRadius, y);
                path.lineTo(getWidth(), y - mRadius);
                // 使这些点构成封闭的多边形
                path.close();
                mCanvas.drawPath(path, mPaint);
            }

        }

        for (int i = 0; i < mCircleNum_H; i++) {
            float x = mGap + mRadius + mRemain_H / 2 + ((mGap + mRadius * 2) * i);
            // 绘制三角形
            Path path = new Path();
            // 设置多边形的点
            if (isDrawTopSide) {
                path.moveTo(x - mRadius, 0);
                path.lineTo(x + mRadius, 0);
                path.lineTo(x, mRadius);
                path.lineTo(x - mRadius, 0);
                // 使这些点构成封闭的多边形
                path.close();
                mCanvas.drawPath(path, mPaint);
            }


            //绘制下边缘
            if (isDrawBottomSide) {
                path.moveTo(x - mRadius, getHeight());
                path.lineTo(x + mRadius, getHeight());
                path.lineTo(x, getHeight() - mRadius);
                path.lineTo(x - mRadius, getHeight());
                // 使这些点构成封闭的多边形
                path.close();
                mCanvas.drawPath(path, mPaint);
            }

        }


    }

    /**
     * 绘制椭圆
     */
    private void drawOval() {
        for (int i = 0; i < mCircleNum_H; i++) {
            float x = mGap + mRadius + mRemain_H / 2 + ((mGap + mRadius * 2) * i);
            // 定义椭圆对象
            RectF rectf = new RectF();
            // 设置椭圆大小
            rectf.left = x - mRadius;
            rectf.right = x + mRadius;
            // 绘制上面的椭圆
            if (isDrawTopSide) {
                rectf.top = 0;
                rectf.bottom = mRadius;
                mCanvas.drawOval(rectf, mPaint);
            }
            // 绘制下面的椭圆
            if (isDrawBottomSide) {
                rectf.top = getHeight() - mRadius;
                rectf.bottom = getHeight();
                mCanvas.drawOval(rectf, mPaint);
            }
        }

        for (int i = 0; i < verticalItemSize; i++) {
            float y = mGap + mRadius + mRemain_V / 2 + ((mGap + mRadius * 2) * i);
            // 定义椭圆对象
            RectF rectf = new RectF();
            // 设置椭圆大小
            rectf.top = y - mRadius;
            rectf.bottom = y + mRadius;
            // 绘制椭圆
            if (isDrawLeftSide) {
                rectf.left = 0;
                rectf.right = mRadius;
                mCanvas.drawOval(rectf, mPaint);
            }

            // 绘制椭圆
            if (isDrawRightSide) {
                rectf.left = getWidth() - mRadius;
                rectf.right = getWidth();
                mCanvas.drawOval(rectf, mPaint);
            }
        }

    }

    private void drawCircle() {

        for (int i = 0; i < verticalItemSize; i++) {
            float y = mGap + mRadius + mRemain_V / 2 + ((mGap + mRadius * 2) * i);
            if (isDrawLeftSide) {
                mCanvas.drawCircle(0, y, mRadius, mPaint);
            }
            if (isDrawRightSide) {
                mCanvas.drawCircle(getWidth(), y, mRadius, mPaint);
            }

        }

        for (int i = 0; i < mCircleNum_H; i++) {
            float x = mGap + mRadius + mRemain_H / 2 + ((mGap + mRadius * 2) * i);
            mCanvas.drawCircle(x, 0, mRadius, mPaint);
            if (isDrawTopSide) {
                mCanvas.drawCircle(x, 0, mRadius, mPaint);
            }
            if (isDrawBottomSide) {
                mCanvas.drawCircle(x, getHeight(), mRadius, mPaint);
            }
        }
    }

    public int getDrawType() {
        return drawType;
    }
}
