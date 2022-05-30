package com.program.customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.program.customizeview.R;
import com.program.customizeview.uitls.SizeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlowTextLayout extends ViewGroup {
    private static final String TAG="FlowLayout";

    public static final int DEFAULT_LINE =3;
    //后续需要转单位，目前是px不适配
    public static final int DEFAULT_HORIZONTAL_MARGIN = SizeUtils.dip2px(5);
    public static final int DEFAULT_Vertaical_MARGIN = SizeUtils.dip2px(5);
    public static final int DEFAULT_BARDOR_RADIUS = SizeUtils.dip2px(5);

    public static final int DEFAULT_TEXT_MAX_LENGTH =20;
    private int mMaxLine;
    private float mHorizoontalMargin;
    private float mVertaicalMargin;
    private int mTextMaxLength;
    private int mTextColor;
    private int mBorderColor;
    private float mBorderRadius;
    private List<String> mData = new ArrayList<>();
    private OnItemClickListener mItemClickListener=null;

    public FlowTextLayout(Context context) {
        this(context,null);
    }

    public FlowTextLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);

        //拿属性
        mMaxLine = a.getInteger(R.styleable.FlowTextLayout_maxLine, DEFAULT_LINE);
        if (mMaxLine <1){
            throw new IllegalArgumentException("maxLine can not less then 1.");
        }
        mHorizoontalMargin = a.getDimension(R.styleable.FlowTextLayout_itemHorizontalMargin, DEFAULT_HORIZONTAL_MARGIN);
        mVertaicalMargin = a.getDimension(R.styleable.FlowTextLayout_itemVertaicalMargin,DEFAULT_Vertaical_MARGIN);
        mTextMaxLength = a.getInt(R.styleable.FlowTextLayout_textMaxLength, DEFAULT_TEXT_MAX_LENGTH);
        if (mTextMaxLength<0){
            throw new IllegalArgumentException("text length must be max then 0");
        }
        mTextColor = a.getColor(R.styleable.FlowTextLayout_textColor, getResources().getColor(R.color.text_grey));
        mBorderColor = a.getColor(R.styleable.FlowTextLayout_borderColor, getResources().getColor(R.color.text_grey));
        mBorderRadius = a.getDimension(R.styleable.FlowTextLayout_borderRadius, DEFAULT_BARDOR_RADIUS);
        Log.d(TAG,"mMaxLines" + mMaxLine +"\n"+
                "mHorizoontalMargin" +mHorizoontalMargin+"\n"+
                "mVertaicalMargin" +mVertaicalMargin+"\n"+
                "mMaxLength" + mTextMaxLength +"\n"+
                "mTextColor" +mTextColor+"\n"+
                "mBorderColor" +mBorderColor+"\n"+
                "mBorderRadius"+mBorderRadius+"\n");
        a.recycle();
    }

    public void setTextList(List<String> data){
        this.mData.clear();
        this.mData.addAll(data);
        Log.d(TAG,"size"+mData.size()+"data ="+data+"\n"
                +mData.toString());
        //根据数据创建子View
        setUpChildren();
    }

    private void setUpChildren() {
        //先清空原有的内容
        removeAllViews();
        Random random = new Random();
        //添加子view
        for (String datum : mData) {
            TextView textView= (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_flow_text,this,false);
            if (mTextMaxLength!=-1){
                //设置textview的最大长度
                textView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mTextMaxLength)});
            }
            textView.setText(datum);
            Log.d(TAG,"list String ="+textView.getText());
            final String tempData = datum;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClickListener(v,tempData);
                    }
                }
            });

            //
//            int colorrrrr = random.nextInt();
//            GradientDrawable drawable = new GradientDrawable();
//            drawable.setCornerRadius(5);
//            drawable.setStroke(1, Color.parseColor("#FF03DAC5"));//设置边框颜色
//            drawable.setColor(colorrrrr%2==0?Color.parseColor("#eeeeee"):Color.parseColor("#FF03DAC5"));//设置颜色
//            textView.setBackgroundDrawable(drawable);

            //设置TextView相关属性
            addView(textView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(View v,String text);
    }

    private List<List<View>> mLines = new ArrayList<>();
    /**
     * 两个值来自于父控件，包含值和模式
     * int类型 ===>4字节==>4*8 bit ==>32位
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLines.clear();//每次测量先清空一次，不然会多次添加
        int mode = MeasureSpec.getMode(widthMeasureSpec);       //从父控件拿，但还是可以不遵守
        int parentWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int parentHightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();//获取孩子个数
        if (childCount==0){
            return;
        }
        //添加默认行
        List<View> line=null;
        int childWidthSpace = MeasureSpec.makeMeasureSpec(parentWidthSize, MeasureSpec.AT_MOST);
        int childHeightSpace = MeasureSpec.makeMeasureSpec(parentHightSize, MeasureSpec.AT_MOST);
        Log.d(TAG,"view childCount ="+childCount);
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) getChildAt(i);
            Log.d(TAG,"chile data"+child.getText());
            if (child.getVisibility()!=View.VISIBLE){
                continue;
            }
            //测量孩子
            measureChild(child,childWidthSpace,childHeightSpace);
            if (line==null){
                //可以添加
                line = new ArrayList<>();
                line.add(child);
            }else {
                //判断是否可以添加到当行
                boolean canBeAdd = checkChildCanBeAdd(line,child,parentWidthSize);
                if (!canBeAdd) {
                    /*if (mLines.size()>=mMaxLine){
                        //跳出循环，不在添加
                        break;
                    }*/
                    mLines.add(line);
                    line = new ArrayList<>();
                }
                line.add(child);
            }
            if (i==getChildCount()-1){
                mLines.add(line);
            }

        }
        Log.d(TAG,"chile data======================");
        //根据尺寸计算行高
        View child = getChildAt(0);
        int childHeight = child.getMeasuredHeight();
        int parentHeightTargetSize = childHeight*mLines.size()
                +(mLines.size()+1)*(int) mVertaicalMargin
                +getPaddingTop()
                +getPaddingBottom();
        //测量自己
        setMeasuredDimension(parentWidthSize,parentHeightTargetSize);
    }

    private boolean checkChildCanBeAdd(List<View> line, View child, int parentWidthSize) {
        int measuredWidth = child.getMeasuredWidth();
        int totalWidth=(int) mHorizoontalMargin + getPaddingLeft();
        for (View view : line) {
            totalWidth +=view.getMeasuredWidth()+mHorizoontalMargin;
        }
        totalWidth+=measuredWidth+mHorizoontalMargin+getPaddingRight();
        //如果超出限制宽度，则不可以再添加
        //否则可以添加
        return totalWidth<=parentWidthSize;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View fistChild = getChildAt(0);
        int currentLeft = (int)mHorizoontalMargin + getPaddingLeft();
        int currentTop = (int) mVertaicalMargin;
        int currentRight = (int) mHorizoontalMargin + getPaddingLeft();
        int currentBottom=fistChild.getMeasuredHeight()+(int)mVertaicalMargin;
        Log.d(TAG,"mLines ="+mLines.size());
        //摆放
        for (List<View> line : mLines) {
            for (View view : line) {
                //布局每一行
                int width = view.getMeasuredWidth();
                currentRight += width +(int)mHorizoontalMargin;

                //判断边界
                if (currentRight>getMeasuredWidth()-mHorizoontalMargin){
                    currentRight = (int) (getMeasuredWidth()-mHorizoontalMargin);
                }
                view.layout(currentLeft,currentTop,currentRight,currentBottom);
                currentLeft = currentRight+(int)mHorizoontalMargin;
                currentRight+=(int)mHorizoontalMargin;
            }
            currentLeft = (int)mHorizoontalMargin+getPaddingLeft();
            currentRight=(int)mHorizoontalMargin+getPaddingLeft();
            currentBottom+=fistChild.getMeasuredHeight()+(int)mVertaicalMargin;
            currentTop+=fistChild.getMeasuredHeight()+(int)mVertaicalMargin;
        }
    }

    public int getMaxLine() {
        return mMaxLine;
    }

    public void setMaxLine(int maxLine) {
        mMaxLine = maxLine;
    }

    public float getHorizoontalMargin() {
        return mHorizoontalMargin;
    }

    public void setHorizoontalMargin(float horizoontalMargin) {
        mHorizoontalMargin = horizoontalMargin;
    }

    public float getVertaicalMargin() {
        return mVertaicalMargin;
    }

    public void setVertaicalMargin(float vertaicalMargin) {
        mVertaicalMargin = vertaicalMargin;
    }

    public int getTextMaxLength() {
        return mTextMaxLength;
    }

    public void setTextMaxLength(int textMaxLength) {
        mTextMaxLength = textMaxLength;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
    }

    public float getBorderRadius() {
        return mBorderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        mBorderRadius = borderRadius;
    }
}
