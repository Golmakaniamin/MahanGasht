package com.mahangasht.mahangashtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMahanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mahan);
        ButterKnife.bind(this);
    }

    public void GetResultFromServer(int iType)
    {
        Intent main_intent = new Intent(MainMahanActivity.this, MainMahanDetailActivity.class);
        main_intent.putExtra("IType", iType + "");
        startActivity(main_intent);
    }

    @OnClick(R.id.Button_01)
    public void submit01() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }

    @OnClick(R.id.Button_02)
    public void submit02() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }

    @OnClick(R.id.Button_03)
    public void submit03() {
        // TODO submit data to server...
        GetResultFromServer(6); //4
    }

    @OnClick(R.id.Button_04)
    public void submit04() {
        // TODO submit data to server...
        GetResultFromServer(7);
    }

    @OnClick(R.id.Button_05)
    public void submit05() {
        // TODO submit data to server...
        GetResultFromServer(3);
    }

    @OnClick(R.id.Button_06)
    public void submit06() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }

    @OnClick(R.id.Button_07)
    public void submit07() {
        // TODO submit data to server...
        GetResultFromServer(5);
    }

    @OnClick(R.id.Button_08)
    public void submit08() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }

    @OnClick(R.id.Button_09)
    public void submit09() {
        // TODO submit data to server...
        GetResultFromServer(8);
    }

    @OnClick(R.id.Button_10)
    public void submit10() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }

    @OnClick(R.id.Button_11)
    public void submit11() {
        // TODO submit data to server...
        GetResultFromServer(9);
    }

    @OnClick(R.id.Button_12)
    public void submit12() {
        // TODO submit data to server...
        GetResultFromServer(0);
    }
}
