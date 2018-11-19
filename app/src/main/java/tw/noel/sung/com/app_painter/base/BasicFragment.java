package tw.noel.sung.com.app_painter.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import tw.noel.sung.com.app_painter.main.MainActivity;


/**
 * Created by noel on 2018/6/9.
 */

public abstract class BasicFragment extends Fragment {
    public MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }
    //----------------------

    /**
     * 第一層 箱子 container用 無bundle
     */
    public void replaceBasicFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }
    //----------------------

    /**
     * 第一層 箱子container用 有 bundle
     */
    public void replaceBasicFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }
    //-------------

    /**
     * 第二層以後用
     * 無bundle
     */
    public void replaceBasicFragment2(int layoutId, Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }


    //-------------

    /**
     * 第二層以後用
     * 有bundle
     */
    public void replaceBasicFragment2(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    //-------------

    /***
     * 返回事件
     * @return
     */
    public boolean popFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    public void onBack() {
        activity.onBackPressed();
    }

    //------------------------------


    // todo 以下 viewpager中 fragment 若要替換整個ViewPager顯示區塊時使用

    /***
     * 替換inner的container為外層的用  無bundle
     * @param layoutId
     * @param fragment
     * @param addToBackStack
     */
    public void replaceParentFragment(int layoutId, Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    //------------------------------

    /***
     * 替換inner的container為外層的用  有bundle
     * @param layoutId
     * @param fragment
     * @param addToBackStack
     * @param bundle
     */
    public void replaceParentFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getParentFragment().getChildFragmentManager().executePendingTransactions();
    }
}
