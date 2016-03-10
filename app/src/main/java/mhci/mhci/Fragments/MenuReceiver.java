package mhci.mhci.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mhci.mhci.MainActivity;

/**
 * Author: Raymond Dubovik (https://github.com/RaymondDubovik)
 * Date: 10.03.2016
 */
public class MenuReceiver extends BroadcastReceiver {
	private MenuListener listener;


	public MenuReceiver(MenuListener listener) {
		this.listener = listener;
	}


	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getBooleanExtra(MainActivity.PARAM_BUNDLE_MENU, false)) {
			listener.onMenuPressed();
		} else {
			listener.onMenuDepressed();
		}
	}
}
