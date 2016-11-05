package org.t_robop.kido.wi_ficonection;

import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //EditTextの宣言
    EditText editTextID;
    EditText editTextPassword;
    EditText editTextSSID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextの関連付け
        editTextID =(EditText) findViewById(R.id.id);
        editTextPassword =(EditText) findViewById(R.id.password);
        editTextSSID =(EditText) findViewById(R.id.ssid);

        SharedPreferences data = getSharedPreferences("IdInfo", MODE_PRIVATE);
        editTextID.setText(data.getString("ID",""));
        editTextPassword.setText(data.getString("password",""));
        editTextSSID.setText(data.getString("SSID",""));
    }

    //IDとPasswordの保存
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                //保存
                SharedPreferences data = getSharedPreferences("IdInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putString("ID", editTextID.getText().toString());
                editor.putString("password", editTextPassword.getText().toString());
                editor.putString("SSID", editTextSSID.getText().toString());
                editor.apply();
                //トーストで通知する
                Toast.makeText(this, "保存しました", Toast.LENGTH_SHORT).show();
                break;
            case R.id.connect:
                final WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                //Wi-FiのON OFFを確認
                if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
                    //ONの時
                    //トーストで通知
                    Toast.makeText(this, "Wi-FiがONです", Toast.LENGTH_SHORT).show();
                    //目的のSSIDを変数に入れる
                    final String searchSSID = editTextSSID.getText().toString();
                    // SSIDを取り出す
                    List<ScanResult> results = wifiManager.getScanResults();
                    for (int i=0;i<results.size();++i) {
                        final String listSSID = results.get(i).SSID;
                        //目的のSSIDを探す
                        //見つかった場合
                        if(listSSID.equals(searchSSID)){
                            //トーストで通知
                            Toast.makeText(this, searchSSID+"が見つかりました!", Toast.LENGTH_SHORT).show();
                            break;
                        }//見つからない場合
                        if (i==results.size()-1){
                            //トーストで通知
                            Toast.makeText(this, searchSSID+"はありません", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    //OFFの時
                    Toast.makeText(this, "Wi-FiをONにしてください", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
