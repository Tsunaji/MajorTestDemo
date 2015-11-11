package com.digitopolis.majortestdemo;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public abstract class   AppTour extends AppCompatActivity {

    private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private final ArrayList<Integer> colors = new ArrayList<>();
    private final List<Fragment> fragments = new Vector<>();
    private ViewPager introViewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private PagerAdapter pagerAdapter;
    private int currentPosition;
    private int activeDotColor;
    private int inactiveDocsColor;
    private int numberOfSlides;

    //listview
    private LinkedList<ItemSection> list_item = new LinkedList<ItemSection>();
    private ListView listview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_tour2);

        introViewPager = (ViewPager) findViewById(R.id.introViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        activeDotColor = Color.RED;
        inactiveDocsColor = Color.WHITE;
        //Instantiate the PagerAdapter.
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        introViewPager.setAdapter(pagerAdapter);

        init(savedInstanceState);

        numberOfSlides = fragments.size();

        //Instantiate the indicator dots if there are more than one slide
        if (numberOfSlides > 1) {
            setViewPagerDots();
        }

        setListeners();

        //listview call
        listview = (ListView) findViewById(R.id.listView2);
        listview.setFocusable(false);

        new ListviewAdapter().execute();
    }

    public abstract void init(@Nullable Bundle savedInstanceState);

    public void addSlide(@NonNull Fragment fragment) {
        fragments.add(fragment);
        addBackgroundColor(Color.TRANSPARENT);
        pagerAdapter.notifyDataSethanged();
    }

    public void addSlide(@NonNull Fragment fragment, @ColorInt int color) {
        fragments.add(fragment);
        addBackgroundColor(color);
        pagerAdapter.notifyDataSetChanged();
    }

    public List<Fragment> getSlides() {
        return pagerAdapter.getFragments();
    }

    public int getCurrentSlide() {
        return introViewPager.getCurrentItem();
    }

    public void setCurrentSlide(int position) {
        introViewPager.setCurrentItem(position, true);
    }

    public void setActiveDotColor(@ColorInt int color) {
        activeDotColor = color;
    }

    public void setInactiveDocsColor(@ColorInt int color) {
        inactiveDocsColor = color;
    }

    public void showIndicatorDots() {
        dotsLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Hide indicator dots
     */
    public void hideIndicatorDots() {
        dotsLayout.setVisibility(View.INVISIBLE);
    }

    protected void addBackgroundColor(@ColorInt int color) {
        colors.add(color);
    }

    private void setListeners() {
        introViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (pagerAdapter.getCount() - 1) && position < (colors.size() - 1)) {
                    int color = (Integer)
                            argbEvaluator.evaluate(positionOffset, colors.get(position), colors.get(position + 1));
                    introViewPager.setBackgroundColor(color);
                } else {
                    int color = colors.get(colors.size() - 1);
                    introViewPager.setBackgroundColor(color);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                //Set dots
                if (numberOfSlides > 1) {
                    //Set current inactive dots color
                    for (int i = 0; i < numberOfSlides; i++) {
                        dots[i].setTextColor(inactiveDocsColor);
                    }

                    //Set current active dot color
                    dots[position].setTextColor(activeDotColor);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setViewPagerDots() {
        dots = new TextView[numberOfSlides];

        //Set first inactive dots color
        for (int i = 0; i < numberOfSlides; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(inactiveDocsColor);
            dotsLayout.addView(dots[i]);
        }

        //Set first active dot color
        dots[0].setTextColor(activeDotColor);
    }

    private void fadeViewOut(final View view) {
        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        view.startAnimation(fadeOut);
    }

    private void fadeViewIn(final View view) {
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        view.startAnimation(fadeIn);
    }

    public class ListviewAdapter extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            list_item.add(new Model("Image1", "Image2", "protrait"));
            list_item.add(new ModelLandscape("Image3", "landscape"));
            list_item.add(new Model("Image4", "Image5", "protrait"));
            list_item.add(new Model("Image6", "Image7", "protrait"));
            list_item.add(new ModelLandscape("Image8", "landscape"));
            list_item.add(new ModelLandscape("Image9", "landscape"));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ModelAdapter modelAdapter = new ModelAdapter(AppTour.this, list_item);
            listview.setAdapter(modelAdapter);
            Help help = new Help();
            help.updateListViewHeight(listview);
        }
    }
}
