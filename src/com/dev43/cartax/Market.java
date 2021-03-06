/**
 * Copyright (C) <year> by <copyright holders>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package com.dev43.cartax;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class Market {
	public static final int NO_RESULT = -1;

	public static final String TAG = "Market";


	public static final String MARKET_PACKAGE_DETAILS_PREFIX = "market://details?id=";
	public static final String MARKET_AUTHOR_SEARCH_PREFIX = "market://search?q=";

	public static final String MARKET_AUTHOR_PREFIX = "pub:";
	public static final String MARKET_AUTHOR_NAME = "Karl Ostmo";
	public static final String MARKET_AUTHOR_SEARCH_STRING = MARKET_AUTHOR_SEARCH_PREFIX + MARKET_AUTHOR_PREFIX  + "\"" + MARKET_AUTHOR_NAME + "\"";

	public static final String CHARTDROID_PACKAGE_NAME = "com.googlecode.chartdroid";
	public static final String MARKET_CHARTDROID_DETAILS_STRING = MARKET_PACKAGE_DETAILS_PREFIX + CHARTDROID_PACKAGE_NAME;

	public final static String APK_DOWNLOAD_URL_PREFIX = "http://chartdroid.googlecode.com/files/";
	public final static String APK_DOWNLOAD_FILENAME_CHARTDROID = "ChartdroidCore-2.0.0.apk";
	public final static Uri APK_DOWNLOAD_URI_CHARTDROID = Uri.parse(APK_DOWNLOAD_URL_PREFIX + APK_DOWNLOAD_FILENAME_CHARTDROID);

	public static void intentLaunchMarketFallback(Activity context, String market_search, Intent intent, int request_code) {

		Log.d(TAG, "Checking to see whether activity is available...");
		if (isIntentAvailable(context, intent)) {

			Log.i(TAG, "It is!");

			if (request_code < 0)
				context.startActivity(intent);
			else
				context.startActivityForResult(intent, request_code);
		} else {

			Log.e(TAG, "It is not.");

			// Launch market intent
			Uri market_uri = Uri.parse(market_search);
			Intent i = new Intent(Intent.ACTION_VIEW, market_uri);
			try {
				context.startActivity(i);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(context, R.string.market_downdoad_alternative, Toast.LENGTH_LONG).show();
				Intent i2 = getMarketDownloadIntent(APK_DOWNLOAD_FILENAME_CHARTDROID);
				context.startActivity(i2);
			}
		}
	}

	public static Intent getMarketDownloadIntent(String package_name) {
		Uri market_uri = Uri.parse(APK_DOWNLOAD_URL_PREFIX + package_name);
		return new Intent(Intent.ACTION_VIEW, market_uri);
	}

	public static boolean isIntentAvailable(Context context, Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}
