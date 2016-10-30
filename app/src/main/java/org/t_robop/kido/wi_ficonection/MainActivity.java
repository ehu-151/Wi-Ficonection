package org.t_robop.kido.wi_ficonection;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //IDとPasswordの保存
    public void save(View v){
        EditText Id =(EditText) findViewById(R.id.id);
        EditText password =(EditText) findViewById(R.id.password);
        //保存
        SharedPreferences data = getSharedPreferences("IdInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("ID", Id.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.apply();
    }
}
