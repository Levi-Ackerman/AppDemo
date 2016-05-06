package lee.scut.edu.appdemo;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(Looper.getMainLooper());
        handler.sendEmptyMessage(1);
        new Thread(){
            @Override
            public void run() {
                handler = new Handler(Looper.getMainLooper());
                handler.sendEmptyMessage(1);
            }
        }.start();
        NGProxy<IWebView> proxy = new NGProxy<IWebView>();
        proxy.inter.setState();
    }
}
