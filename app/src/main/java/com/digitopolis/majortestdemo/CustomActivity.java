package com.digitopolis.majortestdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Bank on 10/19/2015.
 */
public class CustomActivity extends Fragment {
    LinearLayout image;
    Drawable drawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_custom, container, false);
        image = (LinearLayout) rootView.findViewById(R.id.layoutimage);
        Drawable myIcon = getResources().getDrawable(R.mipmap.ic_launcher);
        if(drawable==null)
            image.setBackground(myIcon);
        else
            image.setBackground(drawable);

        return rootView;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    public void setImage(Drawable drawable) {
        image.setBackground(drawable);
    }

    public void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }


}
