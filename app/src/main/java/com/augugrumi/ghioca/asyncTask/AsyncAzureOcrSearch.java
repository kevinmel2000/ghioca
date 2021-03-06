package com.augugrumi.ghioca.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.augugrumi.ghioca.MyApplication;
import com.augugrumi.ghioca.R;
import com.augugrumi.ghioca.listener.AzureOcrSearchListener;

import it.polpetta.libris.opticalCharacterRecognition.OpticalCharacterRecognitionSearch;
import it.polpetta.libris.opticalCharacterRecognition.azure.contract.IAzureOcrResult;

import java.net.URL;

/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 */

public class AsyncAzureOcrSearch extends AsyncTask<Void, Void, Void> {

    //todo manage azure key
    private static String azureKey = MyApplication.getAppContext().getString(R.string.AZURE_KEY);

    private AzureOcrSearchListener listener;
    private IAzureOcrResult result;
    private boolean error;
    private String url;
    private Exception e;

    public AsyncAzureOcrSearch (String url, AzureOcrSearchListener listener) {
        this.listener = listener;
        this.url = url;
        error = false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        listener.onStart();
    }


    @Override
    protected Void doInBackground(Void... params) {
        try {
            result = OpticalCharacterRecognitionSearch
                    .getAzureServices(azureKey)
                    .imageSearchBuildQuery()
                    .setImage(new URL(url))
                    .build()
                    .search();
            Log.i("SEARCH_RESULT", result.toJSONString());
        } catch (Exception error) {
            e = error;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // TODO think if it could be the right thing to do
        if (error)
            listener.onFailure(e);
        else
            listener.onSuccess(result);
    }
}
