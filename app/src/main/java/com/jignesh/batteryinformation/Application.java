package com.jignesh.batteryinformation;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import java.lang.reflect.Field;

public final class Application extends android.app.Application {


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        setDefaultFont(this, "DEFAULT", "app_font/Montserrat-Light.otf");
        setDefaultFont(this, "MONOSPACE", "app_font/Montserrat-Light.otf");
        setDefaultFont(this, "SERIF", "app_font/Montserrat-Light.otf");

    }


    public void setDefaultFont(Context context, String staticTypefaceFieldName, String
            fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
