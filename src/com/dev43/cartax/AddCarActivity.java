package com.dev43.cartax;

import com.dev43.cartax.graph.IntentConstants;
import com.dev43.cartax.graph.provider.DataContentProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddCarActivity extends Activity implements View.OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
	}
	
	// ========================================================================
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		}
	}

}
