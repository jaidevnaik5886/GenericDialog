package com.transerve.genericdialog.models;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.transerve.genericdialog.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GenericDialog {

    private String title, message, positiveButtonText, negativeButtonText;
    private int icon, positiveButtonColor, negativeButtonColor, positiveButtonTextColor,
            negativeButtonTextColor, titleTextColor, messageTextColor;

    private GenericDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.positiveButtonText = builder.positiveButtonText;
        this.negativeButtonText = builder.negativeButtonText;
        this.icon = builder.icon;
        this.positiveButtonColor = builder.positiveButtonColor;
        this.negativeButtonColor = builder.negativeButtonColor;
        this.positiveButtonTextColor = builder.positiveButtonTextColor;
        this.negativeButtonTextColor = builder.negativeButtonTextColor;
        this.titleTextColor = builder.titleTextColor;
        this.messageTextColor = builder.messageTextColor;
    }

    public static class Builder {

        private String title, message, positiveButtonText, negativeButtonText;
        private int icon, positiveButtonColor, negativeButtonColor, positiveButtonTextColor,
                negativeButtonTextColor, titleTextColor, messageTextColor, positiveButtonStyle, negativeButtonStyle;
        private View view;
        private Context context;
        private GenericDialogOnClickListener positiveBtnListener;
        private GenericDialogOnClickListener negativeBtnListener;
        private AlertDialog.Builder dialog;
        private int positiveButtonDrawable, negativeButtonDrawable;

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

        public Builder setPositiveButton(String positiveButtonText, int positiveButtonDrawable, int positiveButtonColor, int positiveButtonTextColor, GenericDialogOnClickListener positiveBtnListener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonDrawable = positiveButtonDrawable;
            this.positiveButtonColor = positiveButtonColor;
            this.positiveButtonTextColor = positiveButtonTextColor;
            this.positiveBtnListener = positiveBtnListener;
            return this;
        }

        public Builder setNegativeButton(@StyleRes int negativeButtonStyle, GenericDialogOnClickListener negativeBtnListener) {
            this.negativeButtonStyle = negativeButtonStyle;
            this.negativeBtnListener = negativeBtnListener;
            return this;
        }


        public Builder setIcon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setTitleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder setMessageTextColor(int messageTextColor) {
            this.messageTextColor = messageTextColor;
            return this;
        }

        @BindView(R.id.btn_negative)
        Button btnNegative;
        @BindView(R.id.btn_positive)
        Button btnPositive;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_message)
        TextView txtMessage;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        public GenericDialog generate() {
            dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.MyAlertDialogTheme));
            dialog.setCancelable(true);
            view = LayoutInflater.from(context).inflate(R.layout.layout_generic_dialog, null);
            ButterKnife.bind(this, view);
            initViews();
            dialog.setView(view);
            dialog.show();
            return new GenericDialog(this);
        }

        private void initViews() {
            //Title
            txtTitle.setText(title);
            txtTitle.setTextColor(titleTextColor);


            //Message
            txtMessage.setText(message);
            txtMessage.setTextColor(messageTextColor);

            //icon
            ivIcon.setImageResource(icon);


            //PositveButton
            if (positiveBtnListener != null) {
                btnPositive.setVisibility(View.VISIBLE);
                btnPositive.setText(positiveButtonText);
                if (positiveButtonDrawable != 0) {
                    // btnPositive.setBackgroundResource(positiveButtonDrawable);
                }
                if (positiveButtonColor != 0) {
                    btnPositive.setBackgroundColor(ContextCompat.getColor(context, positiveButtonColor));
                }
                if (positiveButtonTextColor != 0) {
                    btnPositive.setTextColor(ContextCompat.getColor(context, positiveButtonTextColor));
                }
                btnPositive.setTextAppearance(R.style.GlobalButton);
            }///

            //NegativeButton
            if (negativeBtnListener != null) {
                btnNegative.setVisibility(View.VISIBLE);
                btnNegative.setText(positiveButtonText);
                btnNegative.setBackgroundColor(ContextCompat.getColor(context, negativeButtonColor));
                btnNegative.setTextColor(ContextCompat.getColor(context, negativeButtonTextColor));
            }
        }

        @OnClick({R.id.btn_negative, R.id.btn_positive})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_negative:
                    if (negativeBtnListener != null) {
                        negativeBtnListener.onClick();
                    }
                    break;
                case R.id.btn_positive:
                    if (positiveBtnListener != null) {
                        positiveBtnListener.onClick();
                    }
                    break;
            }
        }
    }
}
