package com.qingyang.webviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressWebView mWebView;
    private CommonProgressDialog mProgressDialog;
    private final int SPACETIME = 2000;
    private long mLastBackPressTime;

    @Override protected void onRestart() {
        super.onRestart();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new CommonProgressDialog(this);
        mProgressDialog.setTip("正在加载...");
        mProgressDialog.show();
        mWebView = (ProgressWebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.setDownloadListener(new DownloadListener() {
        //    @Override
        //    public void onDownloadStart(String url, String userAgent, String contentDisposition,
        //            String mimetype, long contentLength) {
        //        //if (url != null && url.startsWith("http://"))
        //        //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        //    }
        //});

        mWebView.loadUrl("http://simida5.com");

        WebSettings websettings = mWebView.getSettings();
        //// websettings.setBuiltInZoomControls(true);
        //websettings.setUseWideViewPort(true);
        //websettings.setJavaScriptEnabled(true);
        //websettings.setAllowFileAccess(true);
        //websettings.setDomStorageEnabled(true);
        websettings.setLoadWithOverviewMode(true);
        //// websettings.setUserAgentString("Android");
        websettings.setAppCacheEnabled(true);
        websettings.setSavePassword(true);
    }


    public class WebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //if (url.contains("/friend")) {
            //
            //  }

            view.loadUrl(url);
            return true;
        }

        @Override public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(mProgressDialog != null)
                mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(mWebView.canGoBack())
            {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                if (System.currentTimeMillis() - mLastBackPressTime > SPACETIME) {
                    mLastBackPressTime = System.currentTimeMillis();
                    Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
