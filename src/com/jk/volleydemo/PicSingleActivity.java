package com.jk.volleydemo;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.jk.cache.BitmapCache;
import com.jk.my.circle.iv.CircleImageView;
import com.jk.my.rounde.iv.RoundedImageView;

public class PicSingleActivity extends Activity {

	public static final String IMAGE_URL = "http://img.trip.elong.com/guide/attachments/31/bb/31bba6e1e11501533bc63f0b03dc1836.jpg?t=1433396312";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_pic);

		ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this),
				new BitmapCache());

		NetworkImageView iv = (NetworkImageView) findViewById(R.id.iv);
		iv.setDefaultImageResId(R.drawable.ic_launcher);
		iv.setErrorImageResId(R.drawable.ic_launcher);

		iv.setImageUrl(IMAGE_URL, imageLoader);

		// 圆形的imageView,将CircleImageView继承volley的NetworkImageView
		CircleImageView circleImageView = (CircleImageView) findViewById(R.id.circle_iv);
		circleImageView.setDefaultImageResId(R.drawable.ic_launcher);
		circleImageView.setErrorImageResId(R.drawable.ic_launcher);
		circleImageView.setImageUrl(IMAGE_URL, imageLoader);

		// 带圆角的iamgeView，将RoundedImageView继承volley的NetworkImageView
		RoundedImageView roundedImageView = (RoundedImageView) findViewById(R.id.rounded_iv);
		roundedImageView.setDefaultImageResId(R.drawable.ic_launcher);
		roundedImageView.setErrorImageResId(R.drawable.ic_launcher);
		roundedImageView.setImageUrl(IMAGE_URL, imageLoader);
	}
}
