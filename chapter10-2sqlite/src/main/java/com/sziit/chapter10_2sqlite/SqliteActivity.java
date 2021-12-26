package com.sziit.chapter10_2sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sziit.chapter10_2sqlite.bean.UserInfo;
import com.sziit.chapter10_2sqlite.db.MySQLiteOpenHelper;


public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtName;
    private EditText mEtNo;
    private EditText mEtClass;
    private EditText mEtHobby;
    private Button mBtnQuery;
    private Button mBtnSave;
    private Button mBtnUpdate;
    private Button mBtnDelete;
    private TextView mTvDatabase;
    private MySQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private final String DB_NAME = "Student.db";
    UserInfo mUserInfo =new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();
        initData();
    }

    private void initData() {
        mDbHelper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        mSQLiteDatabase = mDbHelper.getWritableDatabase();
        queryAll();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNo = (EditText) findViewById(R.id.et_no);
        mEtClass = (EditText) findViewById(R.id.et_class);
        mEtHobby = (EditText) findViewById(R.id.et_hobby);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mTvDatabase = (TextView) findViewById(R.id.tv_database);

        mBtnQuery.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_save:
                insert();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_delete:
                delete();
                break;
        }
    }
    /*向数据库增加一条记录*/
    private void insert() {
        submit();
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("name", mUserInfo.getsName());
        mContentValues.put("number", mUserInfo.getiNumber());
        mContentValues.put("cls", mUserInfo.getStrClass());
        mContentValues.put("hobby", mUserInfo.getStrHobby());
        mSQLiteDatabase.insert("user", null, mContentValues);
        Toast.makeText(this, " 插入数据成功", Toast.LENGTH_SHORT).show();
        queryAll();
    }
    /*数据库删除一条记录*/
    private void delete() {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        mSQLiteDatabase.delete("user", "name=?", new String[]{name});
        Toast.makeText(this, "  删除成功", Toast.LENGTH_SHORT).show();
        queryAll();

    }
    /*数据库更新一条记录*/
    private void update() {
        submit();
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("name", mUserInfo.getsName());
        mContentValues.put("number", mUserInfo.getiNumber());
        mContentValues.put("cls", mUserInfo.getStrClass());
        mContentValues.put("hobby", mUserInfo.getStrHobby());
        mSQLiteDatabase.update("user", mContentValues,"name=?",new String[]{mUserInfo.getsName()});
        Toast.makeText(this, "更新数据成功", Toast.LENGTH_SHORT).show();
        queryAll();

    }
    /*数据库查询一条记录*/
    private void query() {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor cursor = mSQLiteDatabase.query("user", null,"name=?", new String[]{name},null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                String strName = cursor.getString(cursor.getColumnIndex("name"));
                int iNumber = cursor.getInt(cursor.getColumnIndex("number"));
                String strCls = cursor.getString(cursor.getColumnIndex("cls"));
                String strHobby = cursor.getString(cursor.getColumnIndex("hobby"));
                mEtNo.setText(String.valueOf(iNumber));
                mEtClass.setText(strCls);
                mEtHobby.setText(strHobby);
                Toast.makeText(this, " 查询成功", Toast.LENGTH_SHORT).show();
                return ;
            }while (cursor.moveToNext());
        }
        Toast.makeText(this, " 查询失败", Toast.LENGTH_SHORT).show();
        return ;
    }
    /*检查EditText是否空白,并将EditText内容全部存入数据类对象*/
    private void submit() {
        // validate
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String no = mEtNo.getText().toString().trim();
        if (TextUtils.isEmpty(no)) {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }
        String cls = mEtClass.getText().toString().trim();
        if (TextUtils.isEmpty(cls)) {
            Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
            return;
        }
        String hobby = mEtHobby.getText().toString().trim();
        if (TextUtils.isEmpty(hobby)) {
            Toast.makeText(this, "请输入爱好:", Toast.LENGTH_SHORT).show();
            return;
        }
        mUserInfo.setsName(name);
        mUserInfo.setStrHobby(hobby);
        mUserInfo.setStrClass(cls);
        mUserInfo.setiNumber(Integer.parseInt(no));
    }
    /*查询数据库所有的记录*/
    public void queryAll() {
        String content = "";
        Cursor cursor = mSQLiteDatabase.query("user", null, null, null, null, null, null);
        cursor.getCount();
        content+=" 数据库总共有"+cursor.getCount()+"记录\n";
        while (cursor.moveToNext()) {
            String strName = cursor.getString(cursor.getColumnIndex("name"));
            int iNumber = cursor.getInt(cursor.getColumnIndex("number"));
            String strCls = cursor.getString(cursor.getColumnIndex("cls"));
            String strHobby = cursor.getString(cursor.getColumnIndex("hobby"));
            content = String.format("%s　姓名：%s\n", content, strName);
            content = String.format("%s　学号：%s\n", content, iNumber);
            content = String.format("%s　班级：%s\n", content, strCls);
            content = String.format("%s　爱好：%s\n", content, strHobby);
        }
        mTvDatabase.setText(content);
    }

}
