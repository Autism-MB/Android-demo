package com.sziit.chapter4_1wexin;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private FrameLayout mFramelayout;
    private RadioButton mRadioButton;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFramelayout = (FrameLayout) findViewById(R.id.framelayout);
        mFramelayout.setOnClickListener(this);
        mRadioButton = (RadioButton) findViewById(R.id.rb);
        mRadioButton.setOnClickListener(this);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton2.setOnClickListener(this);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton3.setOnClickListener(this);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb4);
        mRadioButton4.setOnClickListener(this);
        mRadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        mRadioGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb:
                //replaceFragment方法动态添加WeXinFragment碎片
                replaceFragment(R.id.framelayout, new WeXinFragment());
                break;
            case R.id.rb2:
                replaceFragment(R.id.framelayout, new ContactFragment());
                break;
            case R.id.rb3:
                replaceFragment(R.id.framelayout, new FindFragment());
                break;
            case R.id.rb4:
                replaceFragment(R.id.framelayout, new MeFragment());
                break;
        }
    }

    private void replaceFragment(int layout, Fragment fragment) {
        //1.创建待添加的碎片实例
        FragmentManager manager = getSupportFragmentManager();
        //2.获取manager,开启一个事务,通过调用beginTransaction()方法实现
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //3.向容器内添加或替换碎片,一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
        fragmentTransaction.replace(layout, fragment);
        //4.当您移除或替换一个片段并向返回栈添加事务时，系统会停止（而非销毁）移除的片段。 如果用户执行回退操作进行片段恢复，该片段将重新启动。 如果您不向返回栈添加事务，则系统会在您移除或替换片段时将其销毁。
        fragmentTransaction.addToBackStack(null);
        //5.提交事务,调用commit()方法来完成
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
