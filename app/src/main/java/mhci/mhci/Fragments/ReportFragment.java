package mhci.mhci.Fragments;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import mhci.mhci.MainActivity;
import mhci.mhci.R;

public class ReportFragment extends Fragment implements MenuListener {

	private MainActivity activity;
    private RadioGroup radioGroup;
    private EditText mEdit;

	private Button uploadButton;
	private View reportRegistered;
	private View reportToHide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

		uploadButton = (Button) view.findViewById(R.id.reportUpload);
		reportRegistered = view.findViewById(R.id.reportRegistered);
		reportToHide = view.findViewById(R.id.reportToHide);

		activity = (MainActivity) getActivity();
		activity.registerReceiver(new MenuReceiver(this), new IntentFilter(MainActivity.INTENT_FILTER_FRAGMENT_MENU_CLICK));
		if (!activity.isMenuPressed()) {
			onMenuDepressed();
		}

        Button submitButton = (Button) view.findViewById(R.id.reportBtn);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        mEdit = (EditText)view.findViewById(R.id.editTextReport);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String editTextContent = mEdit.getText().toString();
                Toast.makeText(getActivity(),"Thanks for reporting, good citizen!",Toast.LENGTH_LONG).show();
                radioGroup.clearCheck();
                mEdit.setText(null);
                InputMethodManager imm = (InputMethodManager)
                ReportFragment.this.getActivity().getSystemService(ReportFragment.this.getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

		uploadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.callCamera();
			}
		});

        return view;
    }

	@Override
	public void onMenuPressed() {
		reportRegistered.setVisibility(View.VISIBLE);
		reportToHide.setVisibility(View.GONE);
	}

	@Override
	public void onMenuDepressed() {
		reportRegistered.setVisibility(View.GONE);
		reportToHide.setVisibility(View.VISIBLE);
	}
}
