package com.dev43.cartax;

import android.app.Activity;
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
			int y = Integer.parseInt(year.getText().toString());
			int c = Integer.parseInt(co2.getText().toString());
			double lk = Double.parseDouble(lpkm.getText().toString());
			int w = Integer.parseInt(weight.getText().toString());
			CostData.CARS.add(new Car(carName.getText().toString(), diesel.isChecked(), y, w, c, lk));
			setResult(RESULT_OK);
			finish();
			break;
		}
		}
	}

}
