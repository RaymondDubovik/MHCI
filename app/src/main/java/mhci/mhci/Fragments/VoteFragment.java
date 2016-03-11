package mhci.mhci.Fragments;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import mhci.mhci.MainActivity;
import mhci.mhci.R;

public class VoteFragment extends Fragment implements MenuListener {
	private MainActivity activity;

    private Button submitButton;
	private Button uploadButton;
    private View view;

	private View voteRegistered;
	private View voteToHide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vote, container, false);
        submitButton = (Button) view.findViewById(R.id.voteBtn);
		uploadButton = (Button) view.findViewById(R.id.voteUpload);

		voteRegistered = view.findViewById(R.id.voteRegistered);
		voteToHide = view.findViewById(R.id.voteToHide);

		activity = (MainActivity) getActivity();
		activity.registerReceiver(new MenuReceiver(this), new IntentFilter(MainActivity.INTENT_FILTER_FRAGMENT_MENU_CLICK));
		if (!activity.isMenuPressed()) {
			onMenuDepressed();
		}

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
                Toast.makeText(getActivity(), "Thanks for your vote, good citizen!", Toast.LENGTH_LONG).show();
            }
        });

		uploadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.callCamera();
			}
		});

        // Inflate the layout for this fragment
        return view;
    }

    private void hide() {

        if (((RadioButton) view.findViewById(R.id.rb1)).isChecked()
                || ((RadioButton) view.findViewById(R.id.rb2)).isChecked()
                ) {
            view.findViewById(R.id.ll1).setVisibility(View.GONE);
        }

        if (((RadioButton) view.findViewById(R.id.rb3)).isChecked()
                || ((RadioButton) view.findViewById(R.id.rb4)).isChecked()
                ) {
            view.findViewById(R.id.ll2).setVisibility(View.GONE);
        }

        if (((RadioButton) view.findViewById(R.id.rb5)).isChecked()
                || ((RadioButton) view.findViewById(R.id.rb6)).isChecked()
                ) {
            view.findViewById(R.id.ll3).setVisibility(View.GONE);
        }
    }

	@Override
	public void onMenuPressed() {
		voteRegistered.setVisibility(View.VISIBLE);
		voteToHide.setVisibility(View.GONE);
	}

	@Override
	public void onMenuDepressed() {
		voteRegistered.setVisibility(View.GONE);
		voteToHide.setVisibility(View.VISIBLE);
	}
}
