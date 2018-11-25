package tw.noel.sung.com.app_painter.draw.model;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;

public class ColorData {

    /**
     * index : 0
     * color : #000000
     */

    private int index;
    private String color;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @BindingAdapter("setColor")
    public static void setColor(View view,String color){
        view.setBackgroundColor(Color.parseColor(color));
    }
}
