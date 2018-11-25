package tw.noel.sung.com.app_painter.draw;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import tw.noel.sung.com.app_painter.R;
import tw.noel.sung.com.app_painter.base.BasicFragment;
import tw.noel.sung.com.app_painter.databinding.DrawFragmentBinding;
import tw.noel.sung.com.app_painter.draw.adapter.ColorDataAdapter;
import tw.noel.sung.com.app_painter.draw.model.ColorData;
import tw.noel.sung.com.app_painter.util.FIleUtil;

public class DrawFragment extends BasicFragment implements ColorDataAdapter.OnColorChooseListener {
    private View view;
    private DrawFragmentBinding binding;
    private ColorDataAdapter colorDataAdapter;
    private Gson gson;
    private String currentColor = "#000000";
    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
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
    private void init() {
        gson = new Gson();
        colorDataAdapter = new ColorDataAdapter(activity);
        colorDataAdapter.setOnColorChooseListener(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.fragment_draw, null, false);
        view = binding.getRoot();
        binding.recyclerView.setAdapter(colorDataAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        ArrayList<ColorData> data =  gson.fromJson(new FIleUtil(activity).getStringFromAssets(FIleUtil._FILE_COLORS), new TypeToken<ArrayList<ColorData>>(){}.getType());
        colorDataAdapter.setData(data);
    }
    //-----

    /***
     *  當點選顏色
     * @param position
     * @param color
     */
    @Override
    public void onColorChose(int position, String color) {
        currentColor = color;

    }
}
