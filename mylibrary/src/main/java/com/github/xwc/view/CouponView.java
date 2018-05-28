package com.github.xwc.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 优惠卷
 * Created by xwc on 2017/5/25.
 */
public class CouponView extends FrameLayout {

    //锯齿形状 （圆形，椭圆，三角形，正方形）
    private int drawType;
    public static final int CIRCLE = 0;
    public static final int ELLIPSE = 1;
    public static final int TRIANGLE = 2;
    public static final int SQUARE = 3;
    public static final int LINE = 4;

    //绘制的图层
    private Bitmap mBitmap;

    private Canvas mCanvas;

    //背景颜色
    private int bgc = Color.parseColor("#C0C0C0");

    //item间距
    private float dashGap;

    //item半径
    private float dashWidth;

    //虚线的颜色
    @ColorInt
    private int lineColor;

    private int lineWidth;

    //是否绘制左边图形,默认不绘制
    private boolean isDrawLeftShape = false;
    private boolean isDrawTopShape = false;
    private boolean isDrawRightShape = false;
    private boolean isDrawBottomShape = false;

    //是否绘制左边图形,默认不绘制
    private boolean isDrawLeftLine = false;
    private boolean isDrawTopLine = false;
    private boolean isDrawRightLine = false;
    private boolean isDrawBottomLine = false;

    private DrawModel drawModel;

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
            drawType = typedArray.getInt(R.styleable.CouponView_drawShapeType, CIRCLE);
            dashGap = typedArray.getDimensionPixelOffset(R.styleable.CouponView_dashGap, 5);
            dashWidth = typedArray.getDimensionPixelOffset(R.styleable.CouponView_dashWidth, 10);
            bgc = typedArray.getColor(R.styleable.CouponView_bgc, bgc);

            lineColor = typedArray.getColor(R.styleable.CouponView_lineColor, Color.WHITE);
            lineWidth = typedArray.getDimensionPixelOffset(R.styleable.CouponView_lineWidth, 10);

            isDrawLeftLine = typedArray.getBoolean(R.styleable.CouponView_isDrawLeftSide, false);
            isDrawTopLine = typedArray.getBoolean(R.styleable.CouponView_isDrawTopSide, false);
            isDrawRightLine = typedArray.getBoolean(R.styleable.CouponView_isDrawRightSide, false);
            isDrawBottomLine = typedArray.getBoolean(R.styleable.CouponView_isDrawBottomSide, false);

            isDrawLeftShape = typedArray.getBoolean(R.styleable.CouponView_isDrawLeftShape, false);
            isDrawTopShape = typedArray.getBoolean(R.styleable.CouponView_isDrawTopShape, false);
            isDrawRightShape = typedArray.getBoolean(R.styleable.CouponView_isDrawRightShape, false);
            isDrawBottomShape = typedArray.getBoolean(R.styleable.CouponView_isDrawBottomShape, false);

            typedArray.recycle();
        }
    }

    /**
     * item数量的 计算公式 ：
     * circleNum = (int) ((w-gap)/(2*radius+gap));
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawCanvas(w, h);

        if (isDrawLeftLine || isDrawRightLine || isDrawLeftShape || isDrawRightShape) {
            drawModel.measureVelNum(h);
        }
        if (isDrawTopLine || isDrawBottomLine || isDrawTopShape || isDrawBottomShape) {
            drawModel.measureHorNum(w);
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
        mCanvas.drawColor(bgc);

        drawModel = new CouponModel(this);
        drawModel.setCanvas(mCanvas);
    }

    /**
     * 绘制锯齿
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        drawModel.drawShape();
        drawModel.drawLine();
    }

    public boolean isDrawLeftShape() {

        return isDrawLeftShape;
    }

    public void setDrawLeftShape(boolean drawLeftShape) {
        isDrawLeftShape = drawLeftShape;
    }

    public boolean isDrawTopShape() {
        return isDrawTopShape;
    }

    public void setDrawTopShape(boolean drawTopShape) {
        isDrawTopShape = drawTopShape;
    }

    public boolean isDrawRightShape() {
        return isDrawRightShape;
    }

    public void setDrawRightShape(boolean drawRightShape) {
        isDrawRightShape = drawRightShape;
    }

    public boolean isDrawBottomShape() {
        return isDrawBottomShape;
    }

    public void setDrawBottomShape(boolean drawBottomShape) {
        isDrawBottomShape = drawBottomShape;
    }

    public boolean isDrawLeftLine() {
        return isDrawLeftLine;
    }

    public void setDrawLeftLine(boolean drawLeftLine) {
        isDrawLeftLine = drawLeftLine;
    }

    public boolean isDrawTopLine() {
        return isDrawTopLine;
    }

    public void setDrawTopLine(boolean drawTopLine) {
        isDrawTopLine = drawTopLine;
    }

    public boolean isDrawRightLine() {
        return isDrawRightLine;
    }

    public void setDrawRightLine(boolean drawRightLine) {
        isDrawRightLine = drawRightLine;
    }

    public boolean isDrawBottomLine() {
        return isDrawBottomLine;
    }

    public void setDrawBottomLine(boolean drawBottomLine) {
        isDrawBottomLine = drawBottomLine;
    }

    public int getDrawType() {
        return drawType;
    }

    public float getDashGap() {
        return dashGap;
    }

    public float getDashWidth() {
        return dashWidth;
    }

    public CouponView setBgc(int bgc) {
        this.bgc = bgc;
        return this;
    }

    public CouponView setDrawType(int drawType) {
        this.drawType = drawType;
        return this;
    }

    public CouponView setDashGap(float dashGap) {
        this.dashGap = dashGap;
        return this;
    }

    public CouponView setDashWidth(float dashWidth) {
        this.dashWidth = dashWidth;
        return this;
    }

    public int getLineColor() {
        return lineColor;
    }

    public CouponView setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public CouponView setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }
}
