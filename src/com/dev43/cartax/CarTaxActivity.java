package com.dev43.cartax;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dev43.cartax.graph.IntentConstants;
import com.dev43.cartax.graph.provider.DataContentProvider;

public class CarTaxActivity extends Activity implements View.OnClickListener {
	/** Called when the activity is first created. */
	private Button buttonShowGraph;
	private ListView carList;
	private ArrayList<Car> cars;
	static final String GOOGLE_CODE_URL = "http://chartdroid.googlecode.com/";

	static final String TAG = "CarTax";

	private static final int ADD_NEW_CAR = 0;
	private static final int EDIT_CAR = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.buttonShowGraph = (Button) findViewById(R.id.buttonShowGraph);
		this.buttonShowGraph.setOnClickListener(this);

		this.carList = (ListView) findViewById(R.id.listViewCars);
		cars = new ArrayList<Car>();
		cars.add(new Car("Lada", 2.0, 3, 2011));
		carList.setAdapter(new ArrayAdapter<Car>(this, android.R.layout.simple_list_item_1, cars));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.add_car:
	        newCar();
	        return true;
	    case R.id.help:
	        showHelp();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void newCar() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, AddCarActivity.class);
		startActivityForResult(intent, ADD_NEW_CAR);
	}

	private void showHelp() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // If the request went well (OK) and the request was PICK_CONTACT_REQUEST
	    if (resultCode == Activity.RESULT_OK && requestCode == ADD_NEW_CAR) {
	        // Perform a query to the contact's content provider for the contact's name
	    	Log.i(TAG, "Received activity result");
	    }
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