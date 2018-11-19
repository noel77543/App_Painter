package tw.noel.sung.com.app_painter.draw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.noel.sung.com.app_painter.R;
import tw.noel.sung.com.app_painter.base.BasicFragment;

public class DrawFragment extends BasicFragment{
    private View view;
    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_draw, container, false);
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }
    //-----
    private void init(){

    }
}
