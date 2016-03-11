package mhci.mhci.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mhci.mhci.Lists.ExpandableListAdapter;
import mhci.mhci.R;

public class InfoFragment extends Fragment {
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_info, container, false);

		expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		return view;
	}


	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<>();
		listDataChild = new HashMap<>();

		// Adding child data
		listDataHeader.add("Hospitals");
		listDataHeader.add("Schools");
		listDataHeader.add("Kindergartens");

		// Adding child data
		List<String> hospitals = new ArrayList<>();
		hospitals.add("Gartnavel General Hospital\n1053 Great Western Rd, G12 0YN\n0141 211 3000");
		hospitals.add("Yorkhill Sick Childrens Fund\n" +
				"Dalnair Street, G3 8SJ\n" +
				"0141 337 3101");

		hospitals.add("Beatson West of Scotland Cancer Centre\n1053 Great Western Road, G12 0YN\n" +
				"0141 301 7000");
		hospitals.add("Glasgow Dental Hospital & School\n378 Sauchiehall Street, G2 3JZ\n" +
				"0141 211 9600");

		List<String> schools = new ArrayList<>();
		schools.add("Hillhead Primary School\n" +
				"110 Otago Street, G12 8NS\n" +
				"0141 339 1365\n" +
				"hillheadprimaryglasgow.org/");
		schools.add("Hillhead High School\n" +
				"Oakfield Avenue, G12 8LJ\n" +
				"0141 582 0100\n" +
				"http://www.hillheadhigh.glasgow.sch.uk/");

		List<String> kindergartens = new ArrayList<>();
		kindergartens.add("Kelvinside Kindergarten\n" +
				"17 Lancaster Crescent Lane, G12 0RS\n" +
				"0141 334 1124\n" +
				"http://kelvinside-kindergarten.co.uk/");

		listDataChild.put(listDataHeader.get(0), hospitals);
		listDataChild.put(listDataHeader.get(1), schools);
		listDataChild.put(listDataHeader.get(2), kindergartens);
	}
}
