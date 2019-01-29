package com.transerve.genericdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.transerve.genericdialog.models.GenericDialog;
import com.transerve.genericdialog.models.GenericDialogOnClickListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        final GenericDialog genericDialog = new GenericDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Success !").setTitleTextColor(R.color.colorAccent)
                .setMessage("Data collected successfully").setMessageTextColor(R.color.colorPrimary)
                        .setPositiveButton("OK",R.drawable.back_round_corner,R.color.colorWhite,R.color.colorAccent,new GenericDialogOnClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(MainActivity.this, "LCNJds", Toast.LENGTH_SHORT).show();
                            }
                        })
                .generate();
    }
}
