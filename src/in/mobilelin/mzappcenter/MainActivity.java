package in.mobilelin.mzappcenter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.meizu.smartbar.SmartBarUtils;

/**
 * This is the launcher activity
 * 
 * @author shawn
 * @since 2013-5-31
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get the instance of actionbar
		final ActionBar actionBar = getActionBar();

		// set the navigation mode of actionbar,here is the Tab Navigation mode
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Then add two tabs.
		// The MyTabListener is a customized listener that extends Fragment
		// and implements TabListener of ActionBar
		// you can find this usage at android's api demos.
		actionBar.addTab(actionBar
				.newTab()
				.setIcon(R.drawable.ic_tab_single_send)
				.setTabListener(
						new MyTabListener<FirstTabFragment>(this, "first",
								FirstTabFragment.class)));
		actionBar.addTab(actionBar
				.newTab()
				.setIcon(R.drawable.ic_tab_circle_send)
				.setTabListener(
						new MyTabListener<SecondTabFragment>(this, "second",
								SecondTabFragment.class)));

		// You have to reflect this method so the actionbar tab can be shown
		// at the bottom of your screen.
		// Note that this method will not work at other phones because they do
		// not contain meizu's api and you will even get a terrible layout!!!
		SmartBarUtils.setActionBarTabsShowAtBottom(actionBar, true);

	}

	public static class MyTabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;

		public MyTabListener(Activity activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}

		public MyTabListener(Activity activity, String tag, Class<T> clz,
				Bundle args) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
			mArgs = args;

			// Check to see if we already have a fragment for this tab, probably
			// from a previously saved state. If so, deactivate it, because our
			// initial state is that a tab isn't shown.
			mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
			if (mFragment != null && !mFragment.isDetached()) {
				FragmentTransaction ft = mActivity.getFragmentManager()
						.beginTransaction();
				ft.detach(mFragment);
				ft.commit();
			}
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName(),
						mArgs);
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				ft.attach(mFragment);
			}

			mActivity.getActionBar().setTitle(mTag);
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
		}
	}

}
