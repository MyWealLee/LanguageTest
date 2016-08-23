package com.speedlink.languagetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;


import java.util.Locale;

/**
 * Created by stan on 16/1/12.
 */
public class ChangeLanguage {

    private Locale locales[];
    private static int changedType;
    private static boolean[] changedLanguage;
    public static final int kLocaleCount = 3;
    private final static String LANG = "mLanguage";
    private static ChangeLanguage changeLanguage;

    private ChangeLanguage() {
        initLocates();
    }

    public static ChangeLanguage getInstance() {
        if (changedLanguage == null) {
            changedLanguage = new boolean[3];
        }
        if (changeLanguage != null) {
            return changeLanguage;
        } else {
            return new ChangeLanguage();
        }
    }

    public int getDefaultLanguage(Context context) {
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(LANG, Context.MODE_PRIVATE);
        int def = 1;
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry().toLowerCase();
        if ("zh".equals(language)) {
            if ("cn".equals(country)) {
                def = 0;
            } else  {
                def = 1;
            }
        } else if ("en".equals(language)) {
            def = 2;
        }
        return sp.getInt("mLang", def);
    }

    private void initLocates() {
        locales = new Locale[kLocaleCount];
        locales[0] = Locale.SIMPLIFIED_CHINESE;
        locales[1] = Locale.TRADITIONAL_CHINESE;
        locales[2] = Locale.ENGLISH;
    }

    public Boolean changeLanguage(Context context, int type) {
        /*if (getDefaultLanguage(context)==type){
            return false;
        }*/
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = locales[type];
        res.updateConfiguration(conf, dm);
        SharedPreferences.Editor editor = context.getSharedPreferences(LANG, Context.MODE_PRIVATE).edit();
        editor.putInt("mLang", type);
        editor.commit();
        return true;
    }

    public boolean isChangedLanguage(int i) {
        return changedLanguage[i];
    }

    public void setChangedLanguage(int i, boolean changedLanguage) {
        this.changedLanguage[i] = changedLanguage;
    }
}
