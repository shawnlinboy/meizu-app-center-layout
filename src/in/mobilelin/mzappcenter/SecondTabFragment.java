package in.mobilelin.mzappcenter;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is the second Tab's. Pretty dummy isn't it? Remember you have to handle
 * some actionbar's affairs here!
 * 
 * @author shawn
 * @since 2013-5-31
 * 
 */

public class SecondTabFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_secondtab, null);
		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		View customActionBarView;
		customActionBarView = LayoutInflater.from(getActivity()).inflate(
				R.layout.custon_tab_view2, null);
		getActivity().getActionBar().setCustomView(customActionBarView);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ActionBar bar = getActivity().getActionBar();
		int displayOptions = bar.getDisplayOptions();
		getActivity().getActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_CUSTOM,
				ActionBar.DISPLAY_SHOW_CUSTOM | displayOptions);
	}

}
