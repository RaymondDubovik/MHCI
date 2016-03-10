package mhci.mhci.Fragments;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mhci.mhci.MainActivity;
import mhci.mhci.R;

public class MapFragment extends Fragment implements MenuListener {
    private ImageView map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_map, container, false);
		map = (ImageView) view.findViewById(R.id.ivMap);

		MainActivity activity = (MainActivity) getActivity();
		activity.registerReceiver(new MenuReceiver(this), new IntentFilter(MainActivity.INTENT_FILTER_FRAGMENT_MENU_CLICK));
		if (activity.isMenuPressed()) {
			onMenuPressed();
		}

		return view;
	}


	@Override
	public void onMenuPressed() {
		map.setImageResource(R.drawable.map_works);
	}


	@Override
	public void onMenuDepressed() {
		map.setImageResource(R.drawable.map_no_works);
	}
}
