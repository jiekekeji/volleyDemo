package com.jk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.jk.cache.BitmapCache;
import com.jk.volleydemo.R;
import com.jk.volleydemo.R.drawable;
import com.jk.volleydemo.R.id;
import com.jk.volleydemo.R.layout;

public class IvAdapter extends BaseAdapter {

	private static final String URL1 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL2 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL3 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL4 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL5 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL6 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL7 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL8 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	private static final String URL9 = "http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg";

	public static final String[] IMGS = { URL1, URL2, URL3, URL4, URL5, URL6,
			URL7, URL8, URL9 };

	LayoutInflater inflater;

	ImageLoader imageLoader;

	public IvAdapter(Context ct) {
		inflater = LayoutInflater.from(ct);
		imageLoader = new ImageLoader(Volley.newRequestQueue(ct),
				new BitmapCache());
	}

	@Override
	public int getCount() {
		return IMGS.length;
	}

	@Override
	public Object getItem(int position) {
		return IMGS[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_iv, null);

			holder.iv = (NetworkImageView) convertView
					.findViewById(R.id.net_iv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.iv.setDefaultImageResId(R.drawable.ic_launcher);
		holder.iv.setErrorImageResId(R.drawable.ic_launcher);
		holder.iv.setImageUrl(IMGS[position], imageLoader);
		return convertView;
	}

	public static final class ViewHolder {

		public NetworkImageView iv;
	}
}
