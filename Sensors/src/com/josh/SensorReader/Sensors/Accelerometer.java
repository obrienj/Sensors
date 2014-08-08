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

public class Accelerometer implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor 		accelerometer;
	
 	// Accelerometer title, description, values and units
	private TextView	accelVal_X, accelVal_Y, accelVal_Z,
	 					accelUnit_X, accelUnit_Y, accelUnit_Z;
	
	// Variable to change value to match units
	private double 		accelConvertUnit;
	
	// Variable to store measurement units
	private String		accelUnit;
	
	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public Accelerometer(Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		accelVal_X = 	(TextView)	this.activity.findViewById(R.id.accelerometer_x);
		accelUnit_X = 	(TextView) 	this.activity.findViewById(R.id.accelerometer_unit_x);
		
		accelVal_Y = 	(TextView) 	this.activity.findViewById(R.id.accelerometer_y);
		accelUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.accelerometer_unit_y);
		
		accelVal_Z = 	(TextView) 	this.activity.findViewById(R.id.accelerometer_z);
		accelUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.accelerometer_unit_z);
		
		accelConvertUnit = 1;
		accelUnit = "m/s^2";
	}
	
/****************************************************************************************************/
	
	public void changeAccelerometerValues(SensorEvent event) {
		
		accelVal_X.setText(String.valueOf(" X = " + (event.values[0]) * accelConvertUnit));
		accelUnit_X.setText(String.valueOf(accelUnit));
		
		accelVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * accelConvertUnit));
		accelUnit_Y.setText(String.valueOf(accelUnit));
		
		accelVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * accelConvertUnit));
		accelUnit_Z.setText(String.valueOf(accelUnit));
	}
	
/****************************************************************************************************/
	
	public void changeUnitsAccelerometer() {
		
		if (accelUnit == "m/s^2") {
			accelUnit = "cm/s^2";
			accelConvertUnit = 100;
			
		} else if (accelUnit == "cm/s^2") {
			accelUnit = "mm/s^2";
			accelConvertUnit = 1000;
			
		} else if (accelUnit == "mm/s^2") {
			accelUnit = "ft/s^2";
			accelConvertUnit = 3.2808398950131;
			
		} else if (accelUnit == "ft/s^2") {
			accelUnit = "in/s^2";
			accelConvertUnit = 39.370078740156;
			
		} else if (accelUnit == "in/s^2") {
			accelUnit = "m/s^2";
			accelConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Accelerometer is available or not
		if (accelerometer == null) {
			
			TextView accelNotAvailable = (TextView) this.activity.findViewById(R.id.accelerometer_not_available);
			accelNotAvailable.setVisibility(View.VISIBLE);;
			accelNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
			
			accelVal_X.setVisibility(View.GONE);
			accelUnit_X.setVisibility(View.GONE);
			
			accelVal_Y.setVisibility(View.GONE);
			accelUnit_Y.setVisibility(View.GONE);
			
			accelVal_Z.setVisibility(View.GONE);
			accelUnit_Z.setVisibility(View.GONE);
			
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
