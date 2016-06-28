package com.mrpeng.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * <一句话功能描述>
 * <功能详细描述>
 *
 * @author pengjingnag
 * @see [相关类/方法]
 * @since [产品/模板版本]
 * @deprecated
 */
public class CustomTitleView extends View  implements ChangeView
{

    public void setTitle(String title)
    {
        mTitle = title;
    }

    private String mTitle;
    private int mTitleTextColor;
    private int mTitleTestSize;
    private Paint mPaint;
    private Rect mBound;

    /**
     * 第一个构造 java代码创建视图的时候被调用，如果是从xml填充的视图，就不会调用这个
     * 第二个构造 在xml创建时调用
     * 第三个构造，不知道
     * @param context
     */
    public CustomTitleView(Context context)
    {
        super(context);
    }

    public CustomTitleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);


//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, null, 0);
        int n = typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int attr = typedArray.getIndex(i);
            switch(attr){
                case R.styleable.CustomTitleView_titleText_customview:
                    mTitle = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor_customview:
                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize_custome:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTestSize = typedArray.getDimensionPixelSize(attr,
                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()

                            ));
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTestSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
        this.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTitle = randomText();
                postInvalidate();
            }
        });
    }


    public  String randomText()
    {
//        Random random = new Random();
//        HashSet<Integer> set = new HashSet<>();
//        while(set.size()<4){
//            int randomInt = random.nextInt(10);
//            set.add(randomInt);
//        }
//        StringBuffer sb = new StringBuffer();
//        for(Integer i:set){
//            sb.append(""+i);
//        }
//        return sb.toString();

        StringBuffer sb = new StringBuffer();

        for(int j=0;j<4;j++){
            Random random1 = new Random();
            int h = random1.nextInt(10);
            sb.append(""+h);
        }
        return sb.toString();
    }

//    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr)
//    {
//        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
//        int n = typedArray.getIndexCount();
//        for(int i=0;i<n;i++){
//            int attr = typedArray.getIndex(i);
//            switch(attr){
//                case R.styleable.CustomTitleView_titleText_customview:
//                    mTitle = typedArray.getString(attr);
//                    break;
//                case R.styleable.CustomTitleView_titleTextColor_customview:
//                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
//                    break;
//                case R.styleable.CustomTitleView_titleTextSize_custome:
//                    // 默认设置为16sp，TypeValue也可以把sp转化为px
//                    mTitleTestSize = typedArray.getDimensionPixelSize(attr,
//                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()
//
//                            ));
//                    break;
//            }
//        }
//        typedArray.recycle();
//        mPaint = new Paint();
//        mPaint.setTextSize(mTitleTestSize);
//        mBound = new Rect();
//        mPaint.getTextBounds(mTitle,0,mTitle.length(),mBound);
//
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        /**
         * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
         * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
         * UNSPECIFIED：表示子布局想要多大就多大，很少使用
         */
        int wideMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if(wideMode==MeasureSpec.EXACTLY){//已经设置明确值，或者match_parent
            width=widthSize;
        }else {
            mPaint.setTextSize(mTitleTestSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mTitleTestSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop()+ textHeight + getPaddingBottom() );
            height = desired;
        }
        /**
         * 重要方法，onMeasure调用
         */
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleTextColor);
//        canvas.drawText(mTitle,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
        int start=getWidth()/2-mBound.width()/2;
        /**
         * 数字随机摆放
         */
        for(int i=0;i<4;i++){
            canvas.drawText(mTitle.substring(i,i+1),start,CheckUtil.getTextPosition(getMeasuredHeight()),mPaint);
            start+=mBound.width()/5;
        }
        /**
         * 画随机位置的线
         */
        mPaint.setStrokeWidth(3);
        for(int i=0;i<8;i++){
            int[] line = CheckUtil.getLine(getMeasuredWidth(), getMeasuredHeight());
            canvas.drawLine(line[0],line[1], line[2], line[3],mPaint);

//            float top = (float)(Math.random()*getMeasuredHeight());
//            float bottom = top+1;
//            float left = (float)(0.1*getMeasuredWidth());
//            float right = (float)(0.9*getMeasuredWidth());
//            canvas.drawRect(left,top,right,bottom,mPaint);
        }

        /**
         * 画点
         */
        for(int i=0;i<80;i++){
            canvas.drawCircle(CheckUtil.getPosition(getMeasuredWidth()),CheckUtil.getPosition(getMeasuredHeight()),1,mPaint);
        }


    }

    @Override
    public void updateView()
    {
        mTitle = randomText();
        postInvalidate();
    }
}
