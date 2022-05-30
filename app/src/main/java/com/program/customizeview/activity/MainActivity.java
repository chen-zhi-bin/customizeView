package com.program.customizeview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.program.customizeview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView toFlowTextbtn = this.findViewById(R.id.text_flow_layout_btn);
        TextView toImagebtn = this.findViewById(R.id.text_square_Image_btn);
        TextView toLoopImagebtn = this.findViewById(R.id.to_loop_view);

    }
    public void toFlowText(View view){
        startActivity(new Intent(this, TextFlowActivity.class));
    }

    public void toSquareImage(View view){
        startActivity(new Intent(this, SquareImageActivity.class));
    }

    public void toLoopImage(View view){
        startActivity(new Intent(this, LooperPageActivity.class));
    }
}