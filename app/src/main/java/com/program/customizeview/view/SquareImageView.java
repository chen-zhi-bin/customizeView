package com.program.customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.program.customizeview.R;
import com.program.customizeview.uitls.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class SquareImageView extends ViewGroup {

    //行
    public static final int DEFAULT_ROW = 3;

    //列
    public static final int DEFAULT_COLUMN = 3;
    public static final int DEFAULT_MARGIN = SizeUtils.dip2px(4);

    private static final String TAG = "SquareImageView";
    private int mMaxLine;
    private List<String> mData = new ArrayList<>();
    private int mVerticalPadding;
    private int mHorizontalPadding;

    private int row = DEFAULT_ROW;
    private int column = DEFAULT_COLUMN;
    private int mItemMargin;
    private int mBottom;

    public SquareImageView(Context context) {
        this(context,null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
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
        removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            Log.d(TAG,"mImage = "+mData.get(i));
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(mData.get(i)).into(imageView);
            addView(imageView);
        }
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView);
        //设置属性
        mMaxLine = a.getInt(R.styleable.SquareImageView_maxImageLine, DEFAULT_ROW);
        mItemMargin = a.getDimensionPixelSize(R.styleable.SquareImageView_itmeMargin, DEFAULT_MARGIN);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量孩子
        mVerticalPadding = getPaddingBottom() + getPaddingTop();
        mHorizontalPadding = getPaddingLeft() + getPaddingRight();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = widthSize;
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int perItemWidth = (widthSize - (column + 1) * mItemMargin - mHorizontalPadding) / column;
        int perItemHeight = (heightSize - (row + 1) * mItemMargin - mVerticalPadding) / row;
        int normalWidthSpec = MeasureSpec.makeMeasureSpec(perItemWidth, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(perItemHeight, MeasureSpec.EXACTLY);
        for (int i = 0; i < getChildCount(); i++) {
            View item = getChildAt(i);
            item.measure(normalWidthSpec, heightSpec);
        }
        //测量自己
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int left = mItemMargin + paddingLeft;
        int top;
        int right;
        for (int i = 0; i < childCount; i++) {
            //求出当前元素在第几行，第几列
            int rowIndex = i / column;      //行
            int columnIndex = i % column;   //列
            if (columnIndex == 0) {
                left = mItemMargin + paddingLeft;
            }
            View item = getChildAt(i);
            top = rowIndex * item.getMeasuredHeight() + mItemMargin * (rowIndex + 1) + paddingTop;
            right = left + item.getMeasuredWidth();
            mBottom = top + item.getMeasuredHeight();
            item.layout(left, top, right, mBottom);
            left += item.getMeasuredWidth() + mItemMargin;
        }
        Log.d(TAG,"height ="+mBottom);

    }


    public int getfinalItemBottom() {
        return mBottom;
    }

}
