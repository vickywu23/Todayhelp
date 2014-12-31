package com.together.todayhelp.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.together.todayhelp.R;

public class BaseActivity extends Activity {

	private List<Activity> activityList = new ArrayList<Activity>();
	protected ImageView backImageView = null;
	protected LinearLayout backImage = null;
	protected ImageView accountImage = null;
	protected Button selectBtn = null;
	protected TextView titleText = null;
	protected View dialogView;
	protected Dialog selectionDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActivity(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.activity_back_in,
				R.anim.activity_back_out);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		removeActivity(this);
	}

	protected void init(int resId) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(resId);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.common_title);

//		backImageView = (ImageView) findViewById(R.id.img_title_back);
//		backImage = (LinearLayout) findViewById(R.id.ll_title_back);
//		accountImage = (ImageView) findViewById(R.id.img_title_account);
//		selectBtn = (Button) findViewById(R.id.btn_title_select);
//		titleText = (TextView) findViewById(R.id.tv_title_text);
	}


	private void addActivity(Activity activity) {
        activityList.add(activity);
    }
	
	public void removeActivity(Activity activity) {

        for (Activity activityExist : activityList) {
            if (activity == activityExist) {
                activityList.remove(activityExist);
                break;
            }
        }
    }

}
