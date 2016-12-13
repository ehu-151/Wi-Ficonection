package org.t_robop.kido.wi_ficonection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoginFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        //スレッドを使う
        Thread downloadThread = new Thread() {
            //IOExceptionのスローをする
            public void run() {
                try {
                    //Webのhtmlを取得
                    Connection.Response response = Jsoup.connect("https://www21.atwiki.jp/gamemusicbest100/pages/6120.html").execute();
                    Document document = response.parse();
//                    System.out.println(document.html());
                    //IDとPassWordの値をテキストボックスに挿入する
                    Elements elements = document.getElementsByAttributeValue("name","keyword")
                            .select("input");
                    //value属性の値を変更
                    elements.val("password");
                    System.out.println("form" + elements.forms());

                } catch (IOException e) {
                }
            }
        };
        downloadThread.start();
    }
}
