package com.mrpeng.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mBtn_change;
    private CustomTitleView mCustomview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_change = (Button)findViewById(R.id.btn_change);
        mBtn_change.setOnClickListener(this);
        Button btn_commit = (Button)findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(this);
        mCustomview = (CustomTitleView)findViewById(R.id.customview);
    }

    @Override
    public void onClick(View v)
    {

        switch(v.getId()){
            case R.id.btn_change:
                changeCustomView();


                break;
            case R.id.btn_commit:

                break;






        }

    }

    private void changeCustomView()
    {
        String randomText = mCustomview.randomText();
        mCustomview.setTitle(randomText);
        mCustomview.invalidate();
    }
}
