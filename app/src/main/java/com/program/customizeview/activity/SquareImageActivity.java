package com.program.customizeview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.program.customizeview.Api;
import com.program.customizeview.R;
import com.program.customizeview.uitls.RetrofitManager;
import com.program.customizeview.adapter.SquareImageAdapter;
import com.program.customizeview.domain.ImageString;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SquareImageActivity extends AppCompatActivity {

    public static final String TAG="SquareImageActivity";


    //模拟多组数据
    private List<List<String>> mImages=new ArrayList<>();
    private String loadMoreAndRefreshTempUrl ="";

    @BindView(R.id.images_rv)
    public RecyclerView mImagesRv;
    private SquareImageAdapter mSquareImageAdapter;

    @BindView(R.id.refresh_layout)
    public SmartRefreshLayout mRefreshLayout;


    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Log.d(TAG,"mData mImage ="+ mImages);
                    mSquareImageAdapter.setData(mImages);
//                    mSquareImageView.setTextList(mImages.get(0));
                    break;
                case 1:
                    if (mRefreshLayout != null) {
                        mRefreshLayout.finishRefresh();
                        mSquareImageAdapter.setData(mImages);
                    }
                    break;
                case 2:
                    if (mRefreshLayout != null) {
                        mRefreshLayout.finishLoadMore();
                        mSquareImageAdapter.addData(mImages);
                    }
            }
        }
    };
    private Api mApi;
    //    private SquareImageView mSquareImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_image);
        ButterKnife.bind(this);

        initView();

        initRetrofit();

        initListener();

        getImages();

    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               refreshList();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreList();
            }
        });
    }

    //加载更多
    private void loadMoreList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mImages = new ArrayList<>();
                Message message=new Message();
                message.what = 2;
                refreshOrLoadingMore();
                mHandler.sendMessage(message);
            }
        }).start();
    }

    //刷新或加载更多时的模拟数据
    private void refreshOrLoadingMore() {
        List<String> images = null;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int tempNumber = random.nextInt(9);
            images = new ArrayList<>();
            for (int j = 0; j <= tempNumber; j++) {
                Log.d(TAG, "j=" + j);
                images.add(loadMoreAndRefreshTempUrl);
            }
            mImages.add(images);
        }
    }

    //刷新
    private void refreshList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mImages = new ArrayList<>();
                Message message=new Message();
                message.what = 1;
                refreshOrLoadingMore();
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private void initRetrofit() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private void getImages() {
        Call<ImageString> task = mApi.getImage();
        task.enqueue(new Callback<ImageString>() {
            @Override
            public void onResponse(Call<ImageString> call, Response<ImageString> response) {
                Log.d(TAG,"data "+response.body());
                int code = response.code();
                ImageString data = response.body();
                loadMoreAndRefreshTempUrl = "https://cn.bing.com"+data.getImages().get(0).getUrl();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //消息对象
                        Message message=new Message();
                        message.what = 0;

                        List<String> images =null;
                        Random random = new Random();
                        for (int i = 0; i < 5; i++) {
                            int tempNumber = random.nextInt(9);
                            Log.d(TAG,"随机数:"+tempNumber);
                            images = new ArrayList<>();
                            for (int j = 0; j <= tempNumber; j++) {
                                Log.d(TAG,"j="+j);
                                images.add("https://cn.bing.com"+data.getImages().get(0).getUrl());//必应每日一图，就一图，所以get(0)
                            }
                            mImages.add(images);
                        }
                        for (List<String> image : mImages) {
                            Log.d(TAG,"images"+image.toString());
                            Log.d(TAG,"size:"+image.size());
                        }
                        mHandler.sendMessage(message);
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<ImageString> call, Throwable t) {

            }
        });
    }

    private void initView() {
//        mSquareImageView = this.findViewById(R.id.Square_Iv);
//        mRecyclerView = this.findViewById(R.id.images_rv);
        mImagesRv.setLayoutManager(new LinearLayoutManager(this));
        mSquareImageAdapter = new SquareImageAdapter();
        mImagesRv.setAdapter(mSquareImageAdapter);
        Log.d(TAG,"mData initView");
    }
}