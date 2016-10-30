package org.t_robop.kido.wi_ficonection;

import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //EditTextの宣言
    EditText id;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextの関連付け
        id =(EditText) findViewById(R.id.id);
        password =(EditText) findViewById(R.id.password);

        SharedPreferences data = getSharedPreferences("IdInfo", MODE_PRIVATE);
        id.setText(data.getString("id",""));
        password.setText(data.getString("password",""));
    }

    //IDとPasswordの保存
    public void save(View v){
        //保存
        SharedPreferences data = getSharedPreferences("IdInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("id", id.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();
    }
    public void connect(View v){
        final WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        //Wi-FiのON OFFを確認
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            //ONの時
        }else {
            //OFFの時
            Toast.makeText(this, "Wi-FiをONにしてください", Toast.LENGTH_SHORT).show();
        }
    }
}
