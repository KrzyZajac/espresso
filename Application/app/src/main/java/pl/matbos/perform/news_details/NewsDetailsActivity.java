package pl.matbos.perform.news_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String URL_KEY = NewsDetailsActivity.class.getCanonicalName() + "_URL_KEY";

    @BindView(R.id.news_details_web_view)
    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getStartUrl(getIntent()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            handleBackButton();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void handleBackButton() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    private String getStartUrl(Intent intent) {
        return intent.getStringExtra(URL_KEY);
    }

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(URL_KEY, url);
        return intent;
    }
}