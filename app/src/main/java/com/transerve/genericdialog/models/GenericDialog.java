package com.transerve.genericdialog.models;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transerve.genericdialog.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenericDialog {

    private String title, message;
    private int icon, titleTextColor, messageTextColor, orientationButton, dialogFont;

    private GenericDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.icon = builder.icon;
        this.titleTextColor = builder.titleTextColor;
        this.messageTextColor = builder.messageTextColor;
        this.orientationButton = builder.buttonOrienation;
        this.dialogFont = builder.dialogFont;
    }

    public static class Builder {


        private String title, message;
        private int icon,
                titleTextColor, messageTextColor, dialogFont;
        private View view;
        private float titleTextSize, messageTextSize;
        private Context context;
        private AlertDialog.Builder dialog;
        private List<Button> buttonList = new ArrayList<>();
        private int buttonOrienation;
        private Typeface typeface;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }


        public Builder setIcon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setTitleAppearnce(int titleTextColor, float titleTextSize) {
            this.titleTextColor = titleTextColor;
            this.titleTextSize = titleTextSize;
            return this;
        }

        public Builder setMessageAppearance(int messageTextColor, float messageTextSize) {
            this.messageTextColor = messageTextColor;
            this.messageTextSize = messageTextSize;
            return this;
        }

        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_message)
        TextView txtMessage;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        AlertDialog displayDialog;

        public GenericDialog generate() {
            dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.MyAlertDialogTheme));
            dialog.setCancelable(true);
            view = LayoutInflater.from(context).inflate(R.layout.layout_generic_dialog, null);
            ButterKnife.bind(this, view);
            initViews();
            dialog.setView(view);
            displayDialog = dialog.show();
            return new GenericDialog(this);
        }

        private void initViews() {
            typeface = ResourcesCompat.getFont(context, dialogFont);

            //Title
            if (title != null) {
                txtTitle.setText(title);
                txtTitle.setTextColor(ResourcesCompat.getColor(context.getResources(), titleTextColor, null));
                txtTitle.setTextSize(titleTextSize);
                txtTitle.setTypeface(typeface);
            }

            //Message
            if (message != null) {
                txtMessage.setText(message);
                txtMessage.setTextColor(ResourcesCompat.getColor(context.getResources(), messageTextColor, null));
                txtMessage.setTextSize(messageTextSize);
                txtMessage.setTypeface(typeface);
            }

            //icon
            if (icon != 0) {
                ivIcon.setImageResource(icon);
            }

            llContainer.setOrientation(buttonOrienation);

            for (int i = 0; i < buttonList.size(); i++) {
                llContainer.addView(buttonList.get(i));
            }
        }

        public Builder addNewButton(@StyleRes int style, final GenericDialogOnClickListener addBtnListener) {
            Button addButton = new Button(new ContextThemeWrapper(context, style), null, style);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            layoutParams.setMargins(8, 0, 8, 0);
            addButton.setLayoutParams(layoutParams);
            addButton.setTypeface(typeface);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addBtnListener.onClick();
                    displayDialog.dismiss();
                }
            });
            buttonList.add(addButton);
            return this;
        }

        public Builder setButtonOrientation(int orientation) {
            this.buttonOrienation = orientation;
            return this;
        }

        public Builder setDialogFont(@FontRes int font) {
            this.dialogFont = font;
            return this;
        }
    }
}
