package admin.raj.shiv.mydream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;


public class MainActivity extends Activity {

    private WebView mWebView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=(Button)findViewById(R.id.but1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWebView = (WebView) findViewById(R.id.activity_main_webview);
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                mWebView.loadUrl("https://www.facebook.com");
                mWebView.setWebViewClient(new MyAppWebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
findViewById(R.id.but1).setVisibility(View.GONE);
                        //show webview
                        findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        findViewById(R.id.but1).setVisibility(View.VISIBLE);
        //show webview
        findViewById(R.id.activity_main_webview).setVisibility(View.GONE);
        if(mWebView.canGoBack()) {

            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }






}

