package com.speedlink.languagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int langId = ChangeLanguage.getInstance().getDefaultLanguage(this);
        ChangeLanguage.getInstance().changeLanguage(this, langId);

        textView = (TextView) findViewById(R.id.tv);

        radioGroup = (RadioGroup) findViewById(R.id.rg);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) MainActivity.this.findViewById(checkedId);
                String string = (String) radioButton.getText();
                if (string.equals("简体")){
                    refreshUI(0);
                }else if (string.equals("繁体")){
                    refreshUI(1);
                }else {
                    refreshUI(2);
                }
            }
        });
    }

    private void refreshUI(int type){
        ChangeLanguage.getInstance().changeLanguage(MainActivity.this,type);

        textView.setText(getString(R.string.school));
    }
}
