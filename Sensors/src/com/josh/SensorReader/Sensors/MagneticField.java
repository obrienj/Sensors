package com.josh.SensorReader.Sensors;

import com.josh.SensorReader.MainActivity;
import com.josh.sensors.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class MagneticField implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor		magneticField;
	
	// Magnetic Field values and units
	private TextView	magneticFieldVal_X, magneticFieldVal_Y, magneticFieldVal_Z,
 						magneticFieldUnit_X, magneticFieldUnit_Y, magneticFieldUnit_Z;
	
	// Variable to change values to match units
	private double 		magneticFieldConvertUnit;
	
	// Variable to store measurement units
	private String 		magneticFieldUnit;

	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public MagneticField(Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		magneticFieldVal_X =	(TextView) 	this.activity.findViewById(R.id.magnetic_field_x);
		magneticFieldUnit_X = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_unit_x);
		
		magneticFieldVal_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_y);
		magneticFieldUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_unit_y);
		
		magneticFieldVal_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_z);
		magneticFieldUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_unit_z);
		
		magneticFieldConvertUnit = 1;
		magneticFieldUnit = "\u00B5" + "T";
	}

/****************************************************************************************************/

	public void changeMagneticFieldValues(SensorEvent event) {
		
		magneticFieldVal_X.setText(String.valueOf(" X = " + (event.values[0]) * magneticFieldConvertUnit));
		magneticFieldUnit_X.setText(String.valueOf(magneticFieldUnit));
		
		magneticFieldVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * magneticFieldConvertUnit));
		magneticFieldUnit_Y.setText(String.valueOf(magneticFieldUnit));
		
		magneticFieldVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * magneticFieldConvertUnit));
		magneticFieldUnit_Z.setText(String.valueOf(magneticFieldUnit));
	}
	
/****************************************************************************************************/
	
	public void changeUnitsMagneticField() {
		
		if (magneticFieldUnit == "\u00B5" + "T") {
			magneticFieldUnit = "mT";
			magneticFieldConvertUnit = 0.001;
			
		} else if (magneticFieldUnit == "mT") {
			magneticFieldUnit = "T";
			magneticFieldConvertUnit = .000001;
			
		} else if (magneticFieldUnit == "T") {
			magneticFieldUnit = "pT";
			magneticFieldConvertUnit = 1000000;
			
		} else if (magneticFieldUnit == "pT") {
			magneticFieldUnit = "nT";
			magneticFieldConvertUnit = 1000;
			
		} else if (magneticFieldUnit == "nT") {
			magneticFieldUnit = "\u00B5" + "T";
			magneticFieldConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/

	public boolean isAvailable() {
		
		// Checks if the Accelerometer is available or not	
		if (magneticField == null) {
					
			TextView magneticFieldNotAvailable = (TextView) this.activity.findViewById(R.id.magnetic_field_not_available);
			magneticFieldNotAvailable.setVisibility(View.VISIBLE);;
			magneticFieldNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			magneticFieldVal_X.setVisibility(View.GONE);
			magneticFieldUnit_X.setVisibility(View.GONE);
			
			magneticFieldVal_Y.setVisibility(View.GONE);
			magneticFieldUnit_Y.setVisibility(View.GONE);
			
			magneticFieldVal_Z.setVisibility(View.GONE);
			magneticFieldUnit_Z.setVisibility(View.GONE);
			
			return false;
					
		} else return true;
	}
	
/****************************************************************************************************/

	public void unregister() {
		
		mSensorManager.unregisterListener(this);
	}

/****************************************************************************************************/

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub	
	}
	
}
