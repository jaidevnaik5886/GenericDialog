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
implementation 'com.github.jaidevnaik5886:GenericDialog:1.8'

```
## Example
```
  new GenericDialog.Builder(this)
                .setDialogFont(R.font.nunito_bold)
                .setDialogTheme(R.style.GenericDialogTheme)
                .setIcon(R.drawable.icon)
                .setTitle("Success!")
                .setMessage("Completed successfully").setMessageAppearance(0, 14)
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
```
