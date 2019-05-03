package com.transerve.genericdialog2;

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

import com.transerve.genericdialog2.R;

import java.util.ArrayList;
import java.util.List;


public class GenericDialog {

    private String title, message;
    private int icon, titleTextColor, messageTextColor, buttonOrientation, dialogFont, dialogTheme;
    private float titleTextSize, messageTextSize;
    private boolean isDialogCancelable;

    private GenericDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.icon = builder.icon;
        this.titleTextColor = builder.titleTextColor;
        this.messageTextColor = builder.messageTextColor;
        this.buttonOrientation = builder.buttonOrientation;
        this.dialogFont = builder.dialogFont;
        this.isDialogCancelable = builder.isDialogCancelable;
        this.titleTextSize = builder.titleTextSize;
        this.messageTextSize = builder.messageTextSize;
        this.dialogTheme = builder.dialogTheme;
    }

    public static class Builder {

        private String title, message;
        private int icon, titleTextColor, messageTextColor, dialogFont, buttonOrientation, dialogTheme;
        private float titleTextSize, messageTextSize;
        private boolean isDialogCancelable;
        private View view;
        private Context context;
        private AlertDialog.Builder dialog;
        private List<Button> buttonList = new ArrayList<>();
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

        public Builder setTitleAppearance(int titleTextColor, float titleTextSize) {
            this.titleTextColor = titleTextColor;
            this.titleTextSize = titleTextSize;
            return this;
        }

        public Builder setMessageAppearance(int messageTextColor, float messageTextSize) {
            this.messageTextColor = messageTextColor;
            this.messageTextSize = messageTextSize;
            return this;
        }

        LinearLayout llContainer;
        TextView txtTitle;
        TextView txtMessage;
        ImageView ivIcon;
        AlertDialog displayDialog;

        public GenericDialog generate() {
            if (dialogTheme != 0) {
                dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, dialogTheme));
            } else {
                dialog = new AlertDialog.Builder(context);
            }
            dialog.setCancelable(true);
            view = LayoutInflater.from(context).inflate(R.layout.layout_generic_dialog, null);
            initViews();
            dialog.setView(view);
            dialog.setCancelable(isDialogCancelable);
            displayDialog = dialog.show();
            return new GenericDialog(this);
        }

        private void initViews() {
            llContainer = view.findViewById(R.id.ll_container);
            txtTitle = view.findViewById(R.id.txt_title);
            txtMessage = view.findViewById(R.id.txt_message);
            ivIcon = view.findViewById(R.id.iv_icon);

            if (dialogFont != 0) {
                typeface = ResourcesCompat.getFont(context, dialogFont);
            }

            //Title
            if (title != null) {
                txtTitle.setVisibility(View.VISIBLE);
                txtTitle.setText(title);
                if (titleTextColor != 0) {
                    txtTitle.setTextColor(ResourcesCompat.getColor(context.getResources(), titleTextColor, null));
                }
                if (titleTextSize != 0) {
                    txtTitle.setTextSize(titleTextSize);
                }
                if (typeface != null) {
                    txtTitle.setTypeface(typeface);
                }
            }

            //Message
            if (message != null) {
                txtMessage.setVisibility(View.VISIBLE);
                txtMessage.setText(message);
                if (messageTextColor != 0) {
                    txtMessage.setTextColor(ResourcesCompat.getColor(context.getResources(), messageTextColor, null));
                }
                if (messageTextSize != 0) {
                    txtMessage.setTextSize(messageTextSize);
                }
                if (typeface != null) {
                    txtMessage.setTypeface(typeface);
                }
            }

            //icon
            if (icon != 0) {
                ivIcon.setVisibility(View.VISIBLE);
                ivIcon.setImageResource(icon);
            }

            llContainer.setOrientation(buttonOrientation);
            for (int i = 0; i < 2; i++) {
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
            this.buttonOrientation = orientation;
            return this;
        }

        public Builder setDialogFont(@FontRes int font) {
            this.dialogFont = font;
            return this;
        }

        public Builder setCancelable(boolean cancel) {
            this.isDialogCancelable = cancel;
            return this;
        }

        public Builder setDialogTheme(@StyleRes int genericDialogTheme) {
            this.dialogTheme = genericDialogTheme;
            return this;
        }
    }
}
