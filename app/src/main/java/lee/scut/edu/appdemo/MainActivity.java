package lee.scut.edu.appdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.MyAnno;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public class MainActivity extends AppCompatActivity {

    @MyAnno("nothing anno")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void testReflect() throws Exception {
        List<String> lists = new ArrayList<>();
        Class classList = Class.forName("java.util.ArrayList");
        List<String> refList = (List<String>) classList.newInstance();

        for (int i = 0; i < 1000; i++) {

        }
    }

    private void testWebview() {
        WebView webView= new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i("js in index",consoleMessage.message());
                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.i("js in alert",message);
                return false;
            }
        });
        webView.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public String toString(){
                return "hello js";
            }
        },"jsinterface");
//        webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadData("", "text/html", null);
//        webView.loadUrl("javascript:alert(\"hello my js\")");
//        Toast.makeText(this,"加载完成", Toast.LENGTH_SHORT).show();
    }

    private void testProxy() {
        final List list = new ArrayList();
        List proxy = (List) Proxy.newProxyInstance(Proxy.class.getClassLoader(), new Class[]{List.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("add".equals(method.getName())) {
                            throw new UnsupportedOperationException("can't add in a list");
                        } else {
                            return method.invoke(list, args);
                        }
                    }
                });
        proxy.add("something");
    }

    private void init() {
        multiParams(1,2,3);
    }

    private void multiParams(int... objs) {
        for (int obj : objs) {
            Log.i("lee.",""+obj);
        }
    }
}
