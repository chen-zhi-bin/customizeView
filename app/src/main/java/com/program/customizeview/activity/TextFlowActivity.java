package com.program.customizeview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.program.customizeview.R;
import com.program.customizeview.view.FlowTextLayout;

import java.util.ArrayList;
import java.util.List;

public class TextFlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_flow);
        FlowTextLayout flowTextLayout =this.findViewById(R.id.flow_text_layout);
        List<String> list = new ArrayList<>();
        list.add("android");
        list.add("java");
        list.add("学习");
        list.add("短字符测试1");
        list.add("稍微长那么一点点的字符测试1");
        list.add("超超超超超超超超超超超超长字符测试1");
        list.add("android");
        list.add("java");
        list.add("学习");
        list.add("学习");
        list.add("学习");
        list.add("短字符测试1");
        list.add("瀑布流文字");
        flowTextLayout.setTextList(list);
        flowTextLayout.setOnItemClickListener(new FlowTextLayout.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, String text) {
                Toast.makeText(TextFlowActivity.this,"你点击了"+text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}