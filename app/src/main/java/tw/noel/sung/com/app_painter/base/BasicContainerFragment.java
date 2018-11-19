package tw.noel.sung.com.app_painter.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.noel.sung.com.app_painter.R;


/**
 * Created by noel on 2018/6/9.
 */

public abstract class BasicContainerFragment extends BasicFragment {
    //view 是否已經生成
    private boolean isViewCreated;


    //     ---------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module_container, null);
    }



    // ---------------------------------------------------
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!isViewCreated) {
            isViewCreated = true;
            init();
        }
    }

    // ---------------------------------------------------
    public abstract void init();
}
