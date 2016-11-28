package org.t_robop.kido.wi_ficonection;

import android.app.ListActivity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.util.List;


/**
 * Created by black ehu on 2016/11/05.
 */

public class SSIDSearcher extends ListActivity {
    public Context ctx;
    public boolean isSearchSSID;

    //コンストラクタ
    SSIDSearcher() {

    }

    //命令
    public boolean search(Context context, final String searchSSID) {
        //このClass用のContext変数の初期化
        this.ctx = context;
        //WifiManagerを使用するための変数の初期化
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        //Wi-FiのON OFFを確認
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            //ONの時
            // 周辺にあるSSIDから目的のSSIDを探し出す
            List<ScanResult> results = wifiManager.getScanResults();
            for (int i = 0; i < results.size(); ++i) {
                final String listSSID = results.get(i).SSID;
                //目的のSSIDを探す
                //見つかった場合
                if (listSSID.equals(searchSSID)) {
                    //トーストで通知
                    Toast.makeText(ctx, searchSSID + "が見つかりました!", Toast.LENGTH_SHORT).show();
                    isSearchSSID = true;
                    break;
                }//見つからない場合
                if (i == results.size() - 1) {
                    //トーストで通知
                    Toast.makeText(ctx, searchSSID + "はありません", Toast.LENGTH_SHORT).show();
                    isSearchSSID = false;
                    break;
                }
            }
        } else {
            //OFFの時
            Toast.makeText(ctx, "Wi-FiをONにしてください", Toast.LENGTH_SHORT).show();
            isSearchSSID = false;
        }
        return isSearchSSID;
    }
}
