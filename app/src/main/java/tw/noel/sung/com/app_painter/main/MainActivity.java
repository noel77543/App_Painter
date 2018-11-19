package tw.noel.sung.com.app_painter.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import tw.noel.sung.com.app_painter.R;
import tw.noel.sung.com.app_painter.cloud.CloudContainerFragment;
import tw.noel.sung.com.app_painter.databinding.MainActivityBinding;
import tw.noel.sung.com.app_painter.draw.DrawContainerFragment;
import tw.noel.sung.com.app_painter.image.ImageContainerFragment;


@RuntimePermissions
public class MainActivity extends FragmentActivity {

    private Resources resources;
    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        resources = getResources();
        MainActivityPermissionsDispatcher.onAllowedWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onAllowed() {
        addTabs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        Toast.makeText(this, getString(R.string.toast_permission_refuse), Toast.LENGTH_SHORT).show();

    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        goToSettingPermissions();
    }


    //---------

    private void addTabs() {
        int[] tabRes = new int[]{R.drawable.selector_tab_draw, R.drawable.selector_tab_image, R.drawable.selector_tab_cloud};
        Class[] classes = new Class[]{DrawContainerFragment.class, ImageContainerFragment.class, CloudContainerFragment.class};
        String[] tabNames = resources.getStringArray(R.array.main_tabs);

        binding.tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < classes.length; i++) {
            binding.tabHost.addTab(binding.tabHost.newTabSpec(tabNames[i]).setIndicator(getTabView(tabRes[i])), classes[i], null);
        }
    }
    //-----------------

    /***
     *  設定tab的背景圖
     * @param imgRes
     * @return
     */
    private View getTabView(int imgRes) {
        View view = LayoutInflater.from(this).inflate(R.layout.module_tab, null);
        ImageView tab = (ImageView) view.findViewById(R.id.image_view);
        tab.setBackground(resources.getDrawable(imgRes));
        return view;
    }

    //-----------------------------

    /**
     * 前往開啟權限
     */
    private void goToSettingPermissions() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(getString(R.string.dialog_message_goto_setting));
        alert.setPositiveButton(getString(R.string.dialog_go), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent settings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                settings.addCategory(Intent.CATEGORY_DEFAULT);
                settings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(settings);
            }
        });
        alert.show();
    }
}
