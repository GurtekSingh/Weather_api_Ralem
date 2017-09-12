package com.example.gurtek.weather_api_ralem.custom_views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


import com.example.gurtek.weather_api_ralem.R;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Gurtek Singh on 6/14/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class DTextView extends AppCompatTextView {
    private Context context;

    public DTextView(Context context) {
        super(context);
        init(context,null);
    }

    public DTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs)  {
        this.context = context;
        if (attrs!=null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DTextView, 0, 0);
            String font = ta.getString(R.styleable.DTextView_fontstyle);
            if (font!=null && font.length()>0) {
                try {
                    if (Arrays.asList(getResources().getAssets().list("")).contains(font)) {
                        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), font);
                        setTypeface(custom_font);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "lato_regular.ttf");
                setTypeface(custom_font);
            }
            ta.recycle();
        }
    }

    public void setFontRegular(){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "lato_regular.ttf");
        setTypeface(custom_font);
    }

    public void setFontLight(){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "lato_light.ttf");
        setTypeface(custom_font);
    }

    public void setFontBold(){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "lato_bold.ttf");
        setTypeface(custom_font);
    }

    public void setFontXBold(){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "lato_xbold.ttf");
        setTypeface(custom_font);
    }


    public void setLightColor() {
        setColor(R.color.grey_zumthor);
    }

    private void setColor(int color){
        setTextColor(ContextCompat.getColor(context, color));
    }

    public void setNormalColor() {
        setColor(R.color.snow_white);
    }
}
