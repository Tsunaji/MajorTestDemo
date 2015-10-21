package com.digitopolis.majortestdemo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppTour  {


    @Override
    public void init(Bundle savedInstanceState) {

        int firstColor = Color.parseColor("#0097A7");
        int secondColor = Color.parseColor("#FFA000");
        int customSlideColor = Color.parseColor("#009866");


        CustomActivity ca = new CustomActivity();
        Drawable myIcon = getResources().getDrawable(R.drawable.movie2_banner);
        ca.setDrawable(myIcon);
        CustomActivity ca2 = new CustomActivity();
        Drawable myIcon2 = getResources().getDrawable(R.drawable.swappysurvey_h);
        ca2.setDrawable(myIcon2);
        //Custom slide
        addSlide(ca, customSlideColor);
        addSlide(ca2, firstColor);
        addSlide(new CustomActivity(), secondColor);
        addSlide(new CustomActivity());


    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onDonePressed() {

    }


}
