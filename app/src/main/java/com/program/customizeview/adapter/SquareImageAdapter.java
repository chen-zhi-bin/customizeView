package com.program.customizeview.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.program.customizeview.R;
import com.program.customizeview.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SquareImageAdapter extends RecyclerView.Adapter<SquareImageAdapter.InnerHolder> {

    private List<List<String>> mData =new ArrayList<>();

    public static final String TAG="SquareImageAdapter";

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_square_image, parent, false);
        Log.d(TAG,"mData onCreateViewHolder");
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Log.d(TAG,"mData onBindViewHolder="+mData);
        holder.setData(mData.get(position));
        holder.setViewheight();
//        ViewGroup.LayoutParams layoutParams = holder.mLayout.getLayoutParams();
//        layoutParams.height=300;
//        Log.d(TAG, "height="+holder.mImages.getHeight());
//        holder.mLayout.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setData(List<List<String>> data){
        mData.clear();
        mData.addAll(data);
        for (List<String> datum : mData) {

            Log.d(TAG,"mData size="+datum.size());
        }
        Log.d(TAG,"mData setData="+mData);
        notifyDataSetChanged();
    }

    public void addData(List<List<String>> images) {
        int olderSize = mData.size();
        mData.addAll(images);
        notifyItemChanged(olderSize,images.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {


        private List<String> mDataUrl;

        @BindView(R.id.square_Iv)
        public SquareImageView mImages;
        @BindView(R.id.item_image_text)
        public TextView mIext;
        @BindView(R.id.item_layout)
        public RelativeLayout mLayout;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(List<String> data) {
            mDataUrl = data;
            mImages.setTextList(mDataUrl);
        }

        public List<String> getData() {
            return mDataUrl;
        }

        public void setViewheight() {
            ViewTreeObserver vto = mImages.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mImages.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                    mImages.getHeight();
//                    mImages.getWidth();
//                    Log.d(TAG,"mImages height="+mImages.getfinalItemBottom());
                    mLayout.getLayoutParams().height=mImages.getfinalItemBottom()+mIext.getHeight();
                }
            });

        }
    }
}
