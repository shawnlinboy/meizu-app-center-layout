package in.mobilelin.mzappcenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is the viewpager's customized adapter. You have to override at least the
 * following 4 methods. I used a List<View> in order to collect those 3
 * viewpagers here.
 * 
 * @author shawn
 * @since 2013-5-31
 */
public class MyPagerAdapter extends PagerAdapter {

	Context context;
	List<View> viewList;

	public MyPagerAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		viewList = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(context);
		View pg1 = inflater.inflate(R.layout.pg1_layout, null);
		View pg2 = inflater.inflate(R.layout.pg2_layout, null);
		View pg3 = inflater.inflate(R.layout.pg3_layout, null);
		viewList.add(pg1);
		viewList.add(pg2);
		viewList.add(pg3);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(viewList.get(position));
		return viewList.get(position);
	}

	/**
	 * Attention here! If you got a android ANR problem here,comment the super()
	 * method of it.
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(viewList.get(position));
	}
}
