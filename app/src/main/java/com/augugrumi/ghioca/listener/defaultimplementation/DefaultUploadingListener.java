package com.augugrumi.ghioca.listener.defaultimplementation;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import com.augugrumi.ghioca.UploadingErrorDialog;
import com.augugrumi.ghioca.listener.UploadingListener;

/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 */

//TODO -> this could use fragments instead of alerts
public class DefaultUploadingListener implements UploadingListener {

    public static String URL_INTENT_EXTRA = "url";
    public static String FILE_PATH_INTENT_EXTRA = "path";

    private String filePath;
    private Activity activity;
    private Dialog errorDialogFragment;
    private Class toStart;

    public DefaultUploadingListener(String filePath, Activity activity, Class toStart) {
        this.filePath = filePath;
        this.activity = activity;
        this.toStart = toStart;
    }

    @Override
    public void onStart() {}

    @Override
    public void onProgressUpdate(int progress) {
    }

    @Override
    public void onFinish(String url) {
        Intent intent = new Intent(activity, toStart);
        intent.putExtra(URL_INTENT_EXTRA, url);
        intent.putExtra(FILE_PATH_INTENT_EXTRA, filePath);
        activity.startActivity(intent);
    }

    @Override
    public void onFailure(Throwable error) {
        errorDialogFragment = new UploadingErrorDialog(activity);
        errorDialogFragment.show();
    }
}
