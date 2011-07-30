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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class AddCarActivity extends Activity implements View.OnClickListener {

	private static final String TAG = "AddCarActivity";
	private Button buttonCancel;
	private Button buttonOk;
	public EditText carName;
	public EditText year;
	public EditText co2;
	public EditText lpkm;
	public EditText weight;
	public ToggleButton diesel;
	private int index = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
		this.buttonCancel = (Button) findViewById(R.id.buttonOk);
		this.buttonOk = (Button) findViewById(R.id.buttonCancel);
		this.carName = (EditText) findViewById(R.id.editTextCar);
		this.year = (EditText) findViewById(R.id.editTextYear);
		this.co2 = (EditText) findViewById(R.id.editTextGKM);
		this.lpkm = (EditText) findViewById(R.id.editTextLKM);
		this.weight = (EditText) findViewById(R.id.editTextWeight);
		this.diesel = (ToggleButton) findViewById(R.id.toggleButtonDiesel);
		this.buttonCancel.setOnClickListener(this);
		this.buttonOk.setOnClickListener(this);

		Bundle bundle = getIntent().getExtras();
		if (bundle!=null && bundle.getInt("index",-1)>=0){
			index = bundle.getInt("index");
			Toast.makeText(this, "Editing Car", Toast.LENGTH_LONG).show();;
			Car aCar = CostData.CARS.get(bundle.getInt("index"));
			year.setText(Integer.toString(aCar.getYear()));
			co2.setText(Integer.toString(aCar.getCo()));
			lpkm.setText(Double.toString(aCar.getLpkm()));
			weight.setText(Integer.toString(aCar.getWeight()));
			diesel.setChecked(aCar.getDiesel());
			carName.setText(aCar.getName());
		}
	}

	// ========================================================================
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCancel:{
			setResult(RESULT_CANCELED);
			finish();
			break;
		}
		case R.id.buttonOk:{
			Log.i(TAG, carName.getText().toString());
			if(carName.getText().toString().equals("")){
				Toast.makeText(this, R.string.inv_car_name, Toast.LENGTH_LONG).show();
				break;
			}
			Bundle bundle = new Bundle();
			try {
				bundle.putInt("year", Integer.parseInt(year.getText().toString()));
				bundle.putInt("co2", Integer.parseInt(co2.getText().toString()));
				bundle.putDouble("lpkm", Double.parseDouble(lpkm.getText().toString()));
				bundle.putInt("weight", Integer.parseInt(weight.getText().toString()));
				bundle.putBoolean("diesel", diesel.isChecked());
				bundle.putString("name", carName.getText().toString());
			} catch (NumberFormatException e){
				Toast.makeText(this, "Please fill in all data before saving", Toast.LENGTH_LONG).show();
				break;
			}
			bundle.putInt("index", index);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);

			finish();
			break;
		}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		/*try {
			savedInstanceState.putInt("year", Integer.parseInt(year.getText().toString()));
			savedInstanceState.putInt("co2", Integer.parseInt(co2.getText().toString()));
			savedInstanceState.putDouble("lpkm", Double.parseDouble(lpkm.getText().toString()));
			savedInstanceState.putInt("weight", Integer.parseInt(weight.getText().toString()));
			savedInstanceState.putBoolean("diesel", diesel.isChecked());
			savedInstanceState.putString("name", carName.getText().toString());
			savedInstanceState.putInt("index", index);
		 */
		//CostData.CARS.add(new Car(carName.getText().toString(), diesel.isChecked(), y, w, c, lk));
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		/*year.setText(Integer.toString(savedInstanceState.getInt("year",2011)));
		co2.setText(Integer.toString(savedInstanceState.getInt("co2",150)));
		lpkm.setText(Double.toString(savedInstanceState.getDouble("lpkm", 5.5)));
		weight.setText(Integer.toString(savedInstanceState.getInt("weight",1800)));
		diesel.setChecked(savedInstanceState.getBoolean("diesel",false));
		carName.setText(savedInstanceState.getString("name"));
		index = savedInstanceState.getInt("index", -1);
		*/
	}

}
