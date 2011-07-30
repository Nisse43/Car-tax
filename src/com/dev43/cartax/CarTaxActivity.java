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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev43.cartax.graph.IntentConstants;
import com.dev43.cartax.graph.provider.DataContentProvider;

/**
 * @author Kim Nylund
 * @author Joakim Stoor
 *
 */
public class CarTaxActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {
	/** Called when the activity is first created. */
	private Button buttonShowGraph;
	private ListView carList;
	private ArrayAdapter<Car> carListAdapter;
	private int deleteId=0;

	static final String GOOGLE_CODE_URL = "http://chartdroid.googlecode.com/";
	static final String SAVE_NAME = "cars";

	static final String TAG = "CarTax";

	private static final int ADD_NEW_CAR = 0;
	private static final int EDIT_CAR = 1;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.buttonShowGraph = (Button) findViewById(R.id.buttonShowGraph);
		this.buttonShowGraph.setOnClickListener(this);
		if(savedInstanceState!=null){
			CostData.CARS = (ArrayList<Car>)savedInstanceState.getSerializable("cars");
		}
		else {
			if(CostData.CARS==null){
				//Load data from private storage
				FileInputStream fis;
				try {
					fis = openFileInput(SAVE_NAME);
					ObjectInputStream ois = new ObjectInputStream(fis);
					CostData.CARS = (ArrayList<Car>) ois.readObject();
				} catch (FileNotFoundException e) {
					Toast.makeText(this, "No cars stored", Toast.LENGTH_SHORT).show();
					CostData.CARS = new ArrayList<Car>();
				} catch (StreamCorruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					Toast.makeText(this, "No cars stored", Toast.LENGTH_SHORT).show();
					CostData.CARS = new ArrayList<Car>();
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.carList = (ListView) findViewById(R.id.listViewCars);

		carListAdapter = new ArrayAdapter<Car>(this, android.R.layout.simple_list_item_1, CostData.CARS);
		carList.setAdapter(carListAdapter);
		carList.setOnItemClickListener(this);
		carList.setOnItemLongClickListener(this);
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
		Intent intent = new Intent(this, AddCarActivity.class);
		startActivityForResult(intent, ADD_NEW_CAR);
	}

	private void showHelp() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.help_text).setNegativeButton(android.R.string.ok, this).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// If the request went well (OK) and the request was PICK_CONTACT_REQUEST
		if (resultCode == Activity.RESULT_OK && requestCode == ADD_NEW_CAR) {
			// Perform a query to the contact's content provider for the contact's name
			Log.i(TAG, "Received new activity result");
			Bundle bundle = data.getExtras();
			CostData.CARS.add(new Car(bundle.getString("name"), bundle.getBoolean("diesel"), bundle.getInt("year"), bundle.getInt("weight"), bundle.getInt("co2"), bundle.getDouble("lpkm")));
			carListAdapter.notifyDataSetChanged();
		}
		if (resultCode == Activity.RESULT_OK && requestCode == EDIT_CAR) {
			Log.i(TAG, "Received edit activity result");
			Bundle bundle = data.getExtras();
			Car aCar = CostData.CARS.get(bundle.getInt("index",0));
			aCar.setName(bundle.getString("name"));
			aCar.setCo(bundle.getInt("co2"));
			aCar.setDiesel(bundle.getBoolean("diesel"));
			aCar.setLpkm(bundle.getDouble("lpkm"));
			aCar.setWeight(bundle.getInt("weight"));
			aCar.setYear(bundle.getInt("year"));
			carListAdapter.notifyDataSetChanged();
			Toast.makeText(this, String.format(getString(R.string.car_updated), aCar.getName()), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putSerializable("cars", CostData.CARS);
		//Save data to private storage
		try {
			FileOutputStream fos = openFileOutput(SAVE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(CostData.CARS); oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if(CostData.CARS==null){
			CostData.CARS = (ArrayList<Car>)savedInstanceState.getSerializable("cars");
		}
	}

	// ========================================================================
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonShowGraph:
		{
			Intent i = new Intent(Intent.ACTION_VIEW, DataContentProvider.PROVIDER_URI);
			i.putExtra(Intent.EXTRA_TITLE, CostData.DEMO_CHART_TITLE);
			i.putExtra(IntentConstants.Meta.Axes.EXTRA_FORMAT_STRING_Y, "%.0f€");

			Market.intentLaunchMarketFallback(this, Market.MARKET_CHARTDROID_DETAILS_STRING, i, Market.NO_RESULT);
			break;
		}
		}

	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		final Bundle bundle = new Bundle();
		bundle.putInt("index", position);
		Intent intent = new Intent(this, AddCarActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, EDIT_CAR);
	}

	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		deleteId = position;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(String.format(getString(R.string.delete_confirmation), ((TextView) view).getText())).setPositiveButton(android.R.string.yes, this)
		.setNegativeButton(android.R.string.no, this).setTitle("Delete car").show();
		return false;
	}

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch (which){
		case DialogInterface.BUTTON_POSITIVE:

			if(deleteId<CostData.CARS.size()){
				Toast.makeText(this, "Deleted " + CostData.CARS.get(deleteId), Toast.LENGTH_SHORT).show();
				CostData.CARS.remove(deleteId);
				carListAdapter.notifyDataSetChanged();
			}
			break;

		case DialogInterface.BUTTON_NEGATIVE:
			Toast.makeText(this, "Result no", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}