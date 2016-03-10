package mhci.mhci.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import mhci.mhci.R;

public class VoteFragment extends Fragment {


    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private Button submitButton;
    private ArrayList<RadioGroup> questions;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vote, container, false);
        submitButton = (Button) view.findViewById(R.id.voteBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
                Toast.makeText(getActivity(), "Thanks for your vote, good citizen!", Toast.LENGTH_LONG).show();
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

}
