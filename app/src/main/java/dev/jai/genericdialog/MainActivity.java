package dev.jai.genericdialog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import dev.jai.genericdialog2.GenericDialog;
import dev.jai.genericdialog2.GenericDialogOnClickListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewClicked(View view) {
        new GenericDialog.Builder(this)
                .setDialogFont(R.font.nunito_bold)
                .setDialogTheme(R.style.GenericDialogTheme)
                .setIcon(android.R.drawable.btn_radio)
                .setTitle("Alert  !").setTitleAppearance(R.color.colorPrimaryDark, 16)
                .setMessage("Are you sure want to rate the app")
                .addNewButton(R.style.CustomButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Remind me later Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .addNewButton(R.style.PositiveButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .addNewButton(R.style.NegativeButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Cancel Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonOrientation(LinearLayout.HORIZONTAL)
                .setCancelable(true)
                .generate();
    }
}
