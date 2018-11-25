package tw.noel.sung.com.app_painter.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FIleUtil {
    public static final String _FILE_COLORS = "colors.json";
    private Context context;

    public FIleUtil(Context context) {
        this.context = context;
    }

    //------------

    /***
     * 讀取asset中的檔案
     */
    public String getStringFromAssets(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName), "utf-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            reader.close();
            inputStreamReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
