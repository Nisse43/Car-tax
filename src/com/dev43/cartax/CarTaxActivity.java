package com.dev43.cartax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dev43.cartax.graph.IntentConstants;
import com.dev43.cartax.graph.provider.DataContentProvider;

public class CarTaxActivity extends Activity implements View.OnClickListener {
	/** Called when the activity is first created. */
	private Button buttonShowGraph;

	static final String GOOGLE_CODE_URL = "http://chartdroid.googlecode.com/";

	static final String TAG = "CarTax";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.buttonShowGraph = (Button) findViewById(R.id.buttonShowGraph);
		this.buttonShowGraph.setOnClickListener(this);

	}

	// ========================================================================
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonShowGraph:
		{
			Intent i = new Intent(Intent.ACTION_VIEW, DataContentProvider.PROVIDER_URI);
			i.putExtra(Intent.EXTRA_TITLE, CostData.DEMO_CHART_TITLE);
			i.putExtra(IntentConstants.Meta.Axes.EXTRA_FORMAT_STRING_Y, "%.1f°C");

			Market.intentLaunchMarketFallback(this, Market.MARKET_CHARTDROID_DETAILS_STRING, i, Market.NO_RESULT);
			break;
		}
		}
	}
}