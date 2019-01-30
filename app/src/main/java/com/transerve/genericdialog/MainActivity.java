package com.transerve.genericdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
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
        new GenericDialog.Builder(this)
                .setDialogFont(R.font.nunito_bold)
                .setIcon(R.drawable.icon)
                .setTitle("Success !").setTitleAppearnce(R.color.colorAccent, 20)
                .setMessage("Data collected successfully").setMessageAppearance(R.color.colorPrimary, 14)
                .addNewButton(R.style.NegativeButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this, "No Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .addNewButton(R.style.PositiveButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this, "Yes Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonOrientation(LinearLayout.HORIZONTAL)
                .setCancelable(true)
                .generate();

    }
}
