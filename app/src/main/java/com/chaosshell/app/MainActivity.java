package com.chaosshell.app;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.content.Intent;
import android.net.Uri;
public class MainActivity extends Activity {
 private WebView web;
 @Override public void onCreate(Bundle state) {
  super.onCreate(state);
  getWindow().getDecorView().setSystemUiVisibility(5894 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
  web = new WebView(this); setContentView(web);
  WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setLoadsImagesAutomatically(true);
  CookieManager.getInstance().setAcceptCookie(true); CookieManager.getInstance().setAcceptThirdPartyCookies(web,true);
  web.setWebChromeClient(new WebChromeClient());
  web.setWebViewClient(new WebViewClient() {
   @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest req) {
    Uri uri=req.getUrl(); String scheme=uri.getScheme();
    if ("http".equalsIgnoreCase(scheme)||"https".equalsIgnoreCase(scheme)) return false;
    try { startActivity(new Intent(Intent.ACTION_VIEW,uri)); } catch(Exception ignored) {} return true;
   }
  });
  web.loadUrl("https://xaos.mobi/");
 }
 @Override public void onBackPressed() { if(web.canGoBack()) web.goBack(); else super.onBackPressed(); }
 @Override protected void onPause() { CookieManager.getInstance().flush(); web.onPause(); super.onPause(); }
 @Override protected void onResume() { super.onResume(); web.onResume(); }
 @Override protected void onDestroy() { web.destroy(); super.onDestroy(); }
}
