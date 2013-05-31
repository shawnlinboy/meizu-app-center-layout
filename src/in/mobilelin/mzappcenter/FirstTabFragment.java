package in.mobilelin.mzappcenter;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the first Tab's fragment
 * 
 * @author shawn
 * @since 2013-5-31
 * 
 */
public class FirstTabFragment extends Fragment {

	private static final String TAG = FirstTabFragment.class.getSimpleName();

	// Declare those view that you are going to use here
	private ImageView mScroll1;
	private ImageView mScroll2;
	private ImageView mScroll3;
	private TextView mTextView1;
	private TextView mTextView2;
	private TextView mTextView3;
	private ViewPager pager;

	/**
	 * You have to get your rootview and instantiate the viewpager here.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_firsttab, null);
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		pager.setPageMargin(8);
		pager.setPageMarginDrawable(android.R.drawable.divider_horizontal_bright);

		// The adapter is a customized adapter that extends PagerAdapter.

		// Looks like your adapter shouldn't extends FragmentPagerAdapter
		// because in that case,you have to pass a fragmentmanager to it
		// while you're trying to instantiate it and it's impossible to get that
		// in a Fragment.I'm still working on it.
		pager.setAdapter(new MyPagerAdapter(getActivity()));
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// setCurrentScroll(arg0);
				Log.i(TAG, "Page" + arg0 + "has been selected!");
				setCurrentScroll(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		return rootView;
	}

	/**
	 * The mScroolX is the short blue line shown under "PgX" text. The
	 * mTextViews behave like tabs that shown "PgX" text.As needed,when we
	 * scroll those the pages, the short blue line should indicate which page
	 * was selected and the mTextViews should respond to click events and bring
	 * user to the selected page. So we handle these affairs here.
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		View customActionBarView;
		customActionBarView = LayoutInflater.from(getActivity()).inflate(
				R.layout.custom_tab_view, null);
		getActivity().getActionBar().setCustomView(customActionBarView);
		mScroll1 = (ImageView) customActionBarView.findViewById(R.id.scroll_1);
		mScroll2 = (ImageView) customActionBarView.findViewById(R.id.scroll_2);
		mScroll3 = (ImageView) customActionBarView.findViewById(R.id.scroll_3);
		mTextView1 = (TextView) customActionBarView
				.findViewById(R.id.tab_text_1);
		mTextView2 = (TextView) customActionBarView
				.findViewById(R.id.tab_text_2);
		mTextView3 = (TextView) customActionBarView
				.findViewById(R.id.tab_text_3);
		mTextView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pager.setCurrentItem(0, true);
			}
		});
		mTextView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pager.setCurrentItem(1, true);
			}
		});
		mTextView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pager.setCurrentItem(2, true);
			}
		});
	}

	/**
	 * We user navigate back to FirstTabFragment.Reset the actionbar's
	 * customview and set the right position of that short blue line.
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ActionBar bar = getActivity().getActionBar();
		int displayOptions = bar.getDisplayOptions();
		getActivity().getActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_CUSTOM,
				ActionBar.DISPLAY_SHOW_CUSTOM | displayOptions);
		setCurrentScroll(pager.getCurrentItem());
		Log.i(TAG,
				"onResume()!!!" + "pager.getCurrentItem()"
						+ pager.getCurrentItem());
	}

	/**
	 * Set the short blue line visible or invisible by passing a page index to
	 * it.
	 * 
	 * @param position
	 */
	private void setCurrentScroll(int position) {
		if (mScroll1 != null && mScroll2 != null && mScroll3 != null) {
			mScroll1.setVisibility(position == 0 ? View.VISIBLE
					: View.INVISIBLE);
			mScroll2.setVisibility(position == 1 ? View.VISIBLE
					: View.INVISIBLE);
			mScroll3.setVisibility(position == 2 ? View.VISIBLE
					: View.INVISIBLE);
		}
	}

}
