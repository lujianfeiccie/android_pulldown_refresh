package com.example.wbd;

import com.example.wbd.PullListView.OnRefreshListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnRefreshListener {
	private String tag = getClass().getSimpleName();
	
	private Context context;

	private ImgTxtAdapter adapter;

	PullListView mListView;

	public static LinearLayout head;
	
	public static LinearLayout footer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;

		setContentView(R.layout.activity_main);

		mListView = (PullListView) findViewById(R.id.gridview);

		head = (LinearLayout) findViewById(R.id.head);
		footer=(LinearLayout) findViewById(R.id.footer);


		LayoutParams p = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, Gravity.CENTER);

		head.addView(mListView.getHeadView(), p);
		
		LayoutParams p1 = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, Gravity.CENTER);
		
		footer.addView(mListView.getFooterView(), p1);

		adapter = new ImgTxtAdapter(context);

		mListView.setAdapter(adapter);

		mListView.setonRefreshListener(MainActivity.this);

		for (int i = 0; i < 5; i++) {
			ImgTxtBean b = new ImgTxtBean();
			b.setResid(R.drawable.ic_launcher);
			b.setText("item" + (i + 1));
			adapter.addObject(b);
		}

	}

	@Override
	public void onRefresh() {
		log("onRefresh");
		//Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
		mHandler.postDelayed(taskFinish, 3000);
	}

	Handler mHandler = new Handler();
	
	Runnable taskFinish = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mListView.onRefreshComplete();
			Toast.makeText(MainActivity.this, "新增了N条记录",200).show();
		}
	};
	@Override
	public void onMore() {
		log("onMore");
		mHandler.postDelayed(taskFinish, 3000);
	}
	void log(String msg){
		Log.d(tag, msg);
	}
}