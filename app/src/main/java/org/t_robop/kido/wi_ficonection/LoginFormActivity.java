package org.t_robop.kido.wi_ficonection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoginFormActivity extends AppCompatActivity {


    String ID;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        //画面遷移処理
        //IDとpasswordを受け取る
        Intent intent =getIntent();
        ID=intent.getStringExtra("ID");
        password=intent.getStringExtra("password");

        //スレッドを使う
        Handler handler = new Handler(); // (1)
        handler.post(new Runnable() {
            public void run() {
                try {
                    //Webのhtmlを取得
                    Connection.Response response = Jsoup.connect("http://b.hatena.ne.jp/").execute();
                    Document document = response.parse();
                    System.out.println(document.html());
                    //IDとPassWordの値をテキストボックスに挿入する

                    //name=UserName,inputタグを取得(学籍番号のテキストボックス)
                    Elements UserName = document.getElementsByAttributeValue("name","UserName")
                            .select("input");
                    //name=password,inputタグを取得(パスワードのテキストボックス)
                    Elements password = document.getElementsByAttributeValue("name","password")
                            .select("input");

                    //値を変更する前の要素を表示
                    System.out.println("UserName: " + UserName.outerHtml());
                    System.out.println("password: " + password.outerHtml());
                    //value属性の値を変更
                    UserName.val("UserName");
                    password.val("password");
                    //結果をターミナルに表示
                    System.out.println("UserName: " + UserName.outerHtml());
                    System.out.println("password: " + password.outerHtml());


                } catch (IOException e) {
                }
            }
        });

    }
}
