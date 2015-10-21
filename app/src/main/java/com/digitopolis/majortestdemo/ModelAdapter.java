package com.digitopolis.majortestdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by User on 10/14/2015.
 */
public class ModelAdapter extends ArrayAdapter<ItemSection> {

    private Context context;
    private LinkedList<ItemSection> items;
    private LayoutInflater vi;

    public ModelAdapter(Context context, LinkedList<ItemSection> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ItemSection i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                ModelLandscape modelLandscape = (ModelLandscape) i;
                v = vi.inflate(R.layout.activity_item_listview_landscape, null);

                final TextView text = (TextView) v
                        .findViewById(R.id.textView_landscape);
                final LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linear_listview_landscape);

                if (text != null)
                    text.setText(modelLandscape.getImage());

                linearLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int color = linearLayout.getSolidColor();
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                linearLayout.setBackgroundColor(Color.GRAY);
                                return true;
                            }
                            case MotionEvent.ACTION_UP:{
                                linearLayout.setBackgroundColor(color);
                                return false;
                            }
                            case MotionEvent.ACTION_CANCEL:{
                                linearLayout.setBackgroundColor(color);
                                return false;
                            }
                        }
                        return false;
                    }
                });
            }
            else {
                Model model = (Model) i;
                v = vi.inflate(R.layout.activity_item_listview_portrait, null);

                final LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linear_listview_portrait_left);
                final LinearLayout linearLayout2 = (LinearLayout) v.findViewById(R.id.linear_listview_portrait_right);

                final TextView textLeft = (TextView) v
                        .findViewById(R.id.textView_portrait1);
                final TextView textRight = (TextView) v
                        .findViewById(R.id.textView2_portrait2);

                if (textLeft != null)
                    textLeft.setText(model.getImage());
                if (textRight != null)
                    textRight.setText(model.getImage2());

                linearLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int color = linearLayout.getSolidColor();
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                linearLayout.setBackgroundColor(Color.GRAY);
                                return true;
                            }
                            case MotionEvent.ACTION_UP:{
                                linearLayout.setBackgroundColor(color);
                                return false;
                            }
                            case MotionEvent.ACTION_CANCEL:{
                                linearLayout.setBackgroundColor(color);
                                return false;
                            }
                        }
                        return false;
                    }
                });
                linearLayout2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int color = linearLayout2.getSolidColor();
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                linearLayout2.setBackgroundColor(Color.GRAY);
                                return true;
                            }
                            case MotionEvent.ACTION_UP:{
                                linearLayout2.setBackgroundColor(color);
                                return false;
                            }
                            case MotionEvent.ACTION_CANCEL:{
                                linearLayout2.setBackgroundColor(color);
                                return false;
                            }
                        }
                        return false;
                    }
                });
            }
        }
        return v;
    }

}
