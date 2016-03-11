package mhci.mhci;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mhci.mhci.Fragments.InfoFragment;
import mhci.mhci.Fragments.MapFragment;
import mhci.mhci.Fragments.NewsFragment;
import mhci.mhci.Fragments.ReportFragment;
import mhci.mhci.Fragments.VoteFragment;


/**
 * Author: Raymond Dubovik (https://github.com/RaymondDubovik)
 * Date: 17.11.2015
 */
public class MainActivity extends AppCompatActivity {
	public static final String PARAM_BUNDLE_MENU = "menu";
	public static final String INTENT_FILTER_FRAGMENT_MENU_CLICK = "fragmentmenuclick";
	private boolean menuPressed = false;

	private SectionPagerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

	private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
        initPager();
    }


    private void initPager() {
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.VISIBLE);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setVisibility(View.VISIBLE);

        findViewById(R.id.content_fragment).setVisibility(View.GONE);

		/*
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        */

        adapter = new SectionPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.select();
                changeTabColor(tab, getResources().getColor(R.color.colorPrimaryDark));
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabColor(tab, getResources().getColor(R.color.colorPrimary));
            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {}


            private void changeTabColor(TabLayout.Tab tab, int color) {
                View view = tab.getCustomView().findViewById(R.id.tabContainer);
                view.setBackgroundColor(color);
            }
        });
        adapter.initTabs();

		// this is how I change color of the first tab. quick and dirty
		tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(0).select();
    }


	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return switchStatus();
		}

		return super.onKeyUp(keyCode, event);
	}


	private boolean switchStatus() {
		menuPressed = !menuPressed;
		Intent data = new Intent(INTENT_FILTER_FRAGMENT_MENU_CLICK);
		data.putExtra(PARAM_BUNDLE_MENU, menuPressed);
		sendBroadcast(data);
		return true;
	}


	public boolean isMenuPressed() {
		return menuPressed;
	}


	private static final int CAMERA_CODE = 50;
	public void callCamera() {
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAMERA_CODE);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Toast.makeText(this, "returned from camera", Toast.LENGTH_LONG).show();
		switchStatus();
	}


	private class SectionPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[]{"Map", "News", "Info", "Vote", "Report"};
        private int[] imageResId = {R.drawable.ic_map, R.drawable.ic_news, R.drawable.ic_info, R.drawable.ic_vote, R.drawable.ic_report};


        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MapFragment();
                case 1:
                    return new NewsFragment();
                case 2:
                    return new InfoFragment();
				case 3:
					return new VoteFragment();
				case 4:
					return new ReportFragment();
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return tabTitles.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }


        public void initTabs() {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                tab.setCustomView(adapter.getTabView(i));
            }
        }


        public View getTabView(final int position) {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_custom, null);
            View viewContainer = v.findViewById(R.id.tabContainer);
            viewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabLayout.getTabAt(position).select();
                    viewPager.setCurrentItem(position);
                }
            });

            TextView tv = (TextView) v.findViewById(R.id.tabText);
            tv.setText(tabTitles[position]);
            ImageView img = (ImageView) v.findViewById(R.id.tabImage);
            img.setImageResource(imageResId[position]);
            return v;
        }
    }
}
