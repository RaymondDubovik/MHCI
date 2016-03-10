package mhci.mhci.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mhci.mhci.MainActivity;
import mhci.mhci.R;

public class NewsFragment extends Fragment implements MenuListener, View.OnClickListener {
	private MainActivity activity;

	private LinearLayout llOtherCouncil;
	private TextView tvNotice;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);

		tvNotice = (TextView) view.findViewById(R.id.tvNotice);

		llOtherCouncil = (LinearLayout) view.findViewById(R.id.llOtherCouncil);

		activity = (MainActivity) getActivity();
		activity.registerReceiver(new MenuReceiver(this), new IntentFilter(MainActivity.INTENT_FILTER_FRAGMENT_MENU_CLICK));
		if (activity.isMenuPressed()) {
			onMenuPressed();
		}

		ImageView ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
		ivAdd.setOnClickListener(this);

		return view;
    }


	@Override
	public void onMenuPressed() {
		llOtherCouncil.setVisibility(View.VISIBLE);
	}


	@Override
	public void onMenuDepressed() {
		llOtherCouncil.setVisibility(View.GONE);
	}


	@Override
	public void onClick(View v) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
		LayoutInflater inflater = activity.getLayoutInflater();
		final View dialogView = inflater.inflate(R.layout.dialog_add_notice, null);
		dialogBuilder.setView(dialogView);

		final EditText edt = (EditText) dialogView.findViewById(R.id.etNotice);

		dialogBuilder.setTitle("Post on the noticeboard");
		dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				tvNotice.setText(edt.getText().toString());
				tvNotice.setVisibility(View.VISIBLE);
				Toast.makeText(activity, "Successfully posted!", Toast.LENGTH_SHORT).show();
			}
		});

		AlertDialog b = dialogBuilder.create();
		b.show();
	}
}