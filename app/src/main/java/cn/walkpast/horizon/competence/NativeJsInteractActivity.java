package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.bridge.CallBackFunction;
import cn.walkpast.core.bridge.JsHandler;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.CaptureStrategy;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.widget.PopupWindowTools;
import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2019/1/28 16:07
 * Description: This is..
 */

public class NativeJsInteractActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NativeJsInteractActivity";

    @BindView(R.id.js_icon)
    public ImageView mJsIcon;
    @BindView(R.id.js_menu)
    public ImageView mJsMenu;
    @BindView(R.id.js_title)
    public TextView mJsTitle;
    @BindView(R.id.js_container)
    public FrameLayout mJsContainer;
    @BindView(R.id.js_input)
    public EditText mJsInput;
    @BindView(R.id.js_btn_send)
    public Button mJsBtnSend;

    private Horizon mHorizon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_native_js_interact);
        ButterKnife.bind(this);

        mHorizon = Horizon.with(this)
                .setProgressConfig(ProgressConfig
                        .with(this)
                        .config()
                )
                .setCoreConfig(CoreConfig
                        .with(this)
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mJsContainer)
                .setOriginalUrl("file:///android_asset/brigde/native_js_interact.html")
                .preview();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (mHorizon != null) {
            mHorizon.onTrimMemory(level);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mHorizon != null) {
            mHorizon.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHorizon != null) {
            mHorizon.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mHorizon != null) {
            mHorizon.onStop();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHorizon != null) {
            mHorizon.onDestroy();
        }
    }


    HorizonClient mHorizonClient = new HorizonClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJSCallback(String scheme) {
            return super.onJSCallback(scheme);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    };

    private JsHandler mJsHandler = new JsHandler() {
        @Override
        public void onHandler(String handlerName, String responseData, CallBackFunction function) {
            super.onHandler(handlerName, responseData, function);

            ToastUtils.showShort("ResponseData=" + responseData);
        }
    };

    @OnClick({R.id.js_menu, R.id.js_btn_send})
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.js_menu) {

            PopupWindowTools
                    .getInstance()
                    .setActivity(this)
                    .setTargetView(mJsMenu)
                    .setItems("Alert", "Prompt", "Confirm", "TYPE_CONTAINS_URL")
                    .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            switch (position) {

                                case 0://

                                    //String script1 = "javascript:" + "window.alert('hello,i'm a alert box')";
                                    mHorizon.sendJs("javascript:callAlert()", mJsHandler);

                                    break;
                                case 1://

                                    //String script2 = "javascript:" + "window.prompt('please input something...')";
                                    mHorizon.sendJs("javascript:callPrompt()", mJsHandler);

                                    break;
                                case 2://

                                    String script3 = "javascript:" + "window.confirm('hello,i'm a confirm box.')";
                                    mHorizon.sendJs(script3 /*"javascript:callConfirm()"*/, mJsHandler);

                                    break;
                                case 3:

                                    break;
                            }
                        }
                    })
                    .show();

        } else if (v.getId() == R.id.js_btn_send) {


//            String script = "     var div = document.getElementById(\"div1\");\n" +
//                    "        var br = document.createElement(\"br\");\n" +
//                    "        div.appendChild(br);\n" +
//                    "        var lable = document.createElement(\"label\");\n" +
//                    "        lable.innerText = data[n].QualitativeTargetName;\n" +
//                    "        div.appendChild(lable);";
//

            String script = "document.getElementById(\"message1\").innerText = \"" + (TextUtils.isEmpty(mJsInput.getText().toString()) ? "null" : mJsInput.getText().toString()) + "\";";

            mHorizon.sendJs(script, mJsHandler);

        }
    }
}
