package org.t_robop.kido.wi_ficonection;

import android.content.Context;
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
    //クラスの初期化
    SSIDSearcher searcher = new SSIDSearcher();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextの関連付け
        editTextID = (EditText) findViewById(R.id.id);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextSSID = (EditText) findViewById(R.id.ssid);
        //起動時にID,Password,SSIDを読み込む
        SharedPreferences data = getSharedPreferences("IdInfo", MODE_PRIVATE);
        editTextID.setText(data.getString("ID", ""));
        editTextPassword.setText(data.getString("password", ""));
        editTextSSID.setText(data.getString("SSID", ""));
    }

    //
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                //ID,Password,SSIDの保存
                SharedPreferences data = getSharedPreferences("IdInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putString("ID", editTextID.getText().toString());
                editor.putString("password", editTextPassword.getText().toString());
                editor.putString("SSID", editTextSSID.getText().toString());
                editor.apply();
                //トーストで通知する
                Toast.makeText(this, "保存しました", Toast.LENGTH_SHORT).show();
                break;
            case R.id.connect:
                //引数用変数
                context = getApplicationContext();
                final String searchSSID = editTextSSID.getText().toString();
                //目的のSSIDがあるか確認
                if (searcher.search(context, searchSSID) == true) {
                    //WebViewを開く
                }
                break;
            default:
        }
    }
}
