package com.jk.volleydemo;

import com.jk.adapter.IvAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PicMulActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mul_pic);
		ListView listView = (ListView) findViewById(R.id.listview);

		listView.setAdapter(new IvAdapter(this));
	}

}
