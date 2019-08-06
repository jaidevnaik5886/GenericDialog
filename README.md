[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Generic%20Dialog-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7680)

# GenericDialog

A new AlertDialog for Android is here...!!

## Getting Started
## Installation
Add this into your root build.gradle file:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency to your module build.gradle:
```
implementation 'com.github.jaidevnaik5886:GenericDialog:2.2'
implementation 'com.github.jaidevnaik5886:GenericDialog:2.3' //For AndroidX Support

```
## Usage 
```
    new GenericDialog.Builder(this)
                .setDialogFont(R.font.nunito_bold)
                .setDialogTheme(R.style.GenericDialogTheme)
                .setIcon(android.R.drawable.checkbox_on_background)
                .setTitle("Success  !").setTitleAppearance(R.color.colorPrimaryDark, 16)
                .setMessage("Data Collected Successfully")
                .addNewButton(R.style.CustomButton, new GenericDialogOnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK later Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonOrientation(LinearLayout.HORIZONTAL)
                .setCancelable(true)
                .generate();
```
## ScreenShots

![alt text](https://raw.githubusercontent.com/jaidevnaik5886/GenericDialog/master/img.png)

[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/P5P810RKM)
