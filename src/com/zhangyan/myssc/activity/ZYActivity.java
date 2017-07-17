package com.zhangyan.myssc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;

public class ZYActivity extends Activity {
	
	protected static final String TAG = ZYActivity.class.getSimpleName();

	private ProgressDialog progressDialog;
	private boolean destroyed = false;

	
	//*********************屏蔽自带的返回
	private int mDefaultKeyMode = DEFAULT_KEYS_DISABLE;
	private SpannableStringBuilder mDefaultKeySsb = null;
	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	protected void onDestroy() {
		super.onDestroy();
		destroyed = true;
	}

	// ***************************************
	// Public methods
	// ***************************************
	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	public void showProgressDialog(CharSequence message) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT); 
			progressDialog.setIndeterminate(true);
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	public void dismissProgressDialog() {
		if (progressDialog != null && !destroyed) {
			progressDialog.dismiss();
		}
	}
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getApplicationInfo().targetSdkVersion
                    >= Build.VERSION_CODES.ECLAIR) {
                event.startTracking();
            } else {
                onBackPressed();
            }
            return true;
        }
        
        if (mDefaultKeyMode == DEFAULT_KEYS_DISABLE) {
            return false;
        } else if (mDefaultKeyMode == DEFAULT_KEYS_SHORTCUT) {
            if (getWindow().performPanelShortcut(Window.FEATURE_OPTIONS_PANEL, 
                    keyCode, event, Menu.FLAG_ALWAYS_PERFORM_CLOSE)) {
                return true;
            }
            return false;
        } else {
            // Common code for DEFAULT_KEYS_DIALER & DEFAULT_KEYS_SEARCH_*
            boolean clearSpannable = false;
            boolean handled;
            if ((event.getRepeatCount() != 0) || event.isSystem()) {
                clearSpannable = true;
                handled = false;
            } else {
                handled = TextKeyListener.getInstance().onKeyDown(
                        null, mDefaultKeySsb, keyCode, event);
                if (handled && mDefaultKeySsb.length() > 0) {
                    // something useable has been typed - dispatch it now.

                    final String str = mDefaultKeySsb.toString();
                    clearSpannable = true;
                    
                    switch (mDefaultKeyMode) {
                    case DEFAULT_KEYS_DIALER:
                        Intent intent = new Intent(Intent.ACTION_DIAL,  Uri.parse("tel:" + str));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);    
                        break;
                    case DEFAULT_KEYS_SEARCH_LOCAL:
                        startSearch(str, false, null, false);
                        break;
                    case DEFAULT_KEYS_SEARCH_GLOBAL:
                        startSearch(str, false, null, true);
                        break;
                    }
                }
            }
            if (clearSpannable) {
                mDefaultKeySsb.clear();
                mDefaultKeySsb.clearSpans();
                Selection.setSelection(mDefaultKeySsb,0);
            }
            return handled;
        }
    }
    

}
