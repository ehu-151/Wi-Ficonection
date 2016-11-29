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
                    Connection.Response response = Jsoup.connect("http://www.apple.com/jp/").execute();
                    Document document = response.parse();
//                    System.out.println(document.html());
                    //IDとPassWordの値をテキストボックスに挿入する
                    Elements doc = document.getElementsByAttribute("name").val("11");
                    System.out.println("Elements"+doc.outerHtml());
                } catch (IOException e) {
                }
            }
        };
        downloadThread.start();
    }
}
