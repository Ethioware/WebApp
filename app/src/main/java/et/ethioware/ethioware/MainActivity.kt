package et.ethioware.ethioware

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val myWebView = WebView(this@MainActivity)
            setContentView(myWebView)

            onBackPressedDispatcher.addCallback {
                // Check whether there's history.
                if (myWebView.canGoBack()) {
                    myWebView.goBack()
                }
            }
            myWebView.webViewClient = MyWebViewClient()
            myWebView.settings.javaScriptEnabled = true
            myWebView.addJavascriptInterface(WebAppInterface(this), "Android")
            myWebView.loadUrl("https://www.ethioware.et")

        }catch (e: Exception){
            Log.d("error", "onCreate: "+ e.printStackTrace().toString())}
    }

}

/** Instantiate the interface and set the context.  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}
private class MyWebViewClient : WebViewClient() {

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "www.ethioware.et") {

        }
        // Don't leave the app
        return false
}
}

