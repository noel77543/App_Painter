package tw.noel.sung.com.app_painter.draw;

import tw.noel.sung.com.app_painter.base.BasicContainerFragment;

public class DrawContainerFragment extends BasicContainerFragment {
    @Override
    public void init() {
        replaceBasicFragment(android.R.id.tabcontent, new DrawFragment(), false);
    }
}

