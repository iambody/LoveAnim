package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Iron Man on 2016/7/10.
 * Toast的工具类
 */
public class ToastUtil {
    public static void  show(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
    }


}
