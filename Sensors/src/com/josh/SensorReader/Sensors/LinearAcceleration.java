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

public class LinearAcceleration implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor		linearAcceleration;
	
	// Linear Accelerometer values and units
	private TextView	linearAccelVal_X, linearAccelVal_Y, linearAccelVal_Z,
 						linearAccelUnit_X, linearAccelUnit_Y, linearAccelUnit_Z;
	
	// Variable to change value to match units
	private double 		linearAccelConvertUnit;
	
	// Variable to store measurement units
	private String 		linearAccelUnit;
	
	private Activity 	activity;
	private Context 	context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public LinearAcceleration (Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		linearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		
		linearAccelVal_X = (TextView) this.activity.findViewById(R.id.linear_acceleration_x);
		linearAccelUnit_X = (TextView) this.activity.findViewById(R.id.linear_acceleration_unit_x);
		
		linearAccelVal_Y = (TextView) this.activity.findViewById(R.id.linear_acceleration_y);
		linearAccelUnit_Y = (TextView) this.activity.findViewById(R.id.linear_acceleration_unit_y);
		
		linearAccelVal_Z = (TextView) this.activity.findViewById(R.id.linear_acceleration_z);
		linearAccelUnit_Z = (TextView) this.activity.findViewById(R.id.linear_acceleration_unit_z);
		
		linearAccelConvertUnit = 1;
		linearAccelUnit = "m/s^2";
	}
	
/****************************************************************************************************/
	
	public void changeLinearAccelerationValues(SensorEvent event) {
		
		linearAccelVal_X.setText(String.valueOf(" X = " + (event.values[0]) * linearAccelConvertUnit));
		linearAccelUnit_X.setText(String.valueOf(linearAccelUnit));
		
		linearAccelVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * linearAccelConvertUnit));
		linearAccelUnit_Y.setText(String.valueOf(linearAccelUnit));
		
		linearAccelVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * linearAccelConvertUnit));
		linearAccelUnit_Z.setText(String.valueOf(linearAccelUnit));	
	}
	
/****************************************************************************************************/
	
	public void changeUnitsLinearAcceleration() {
		
		if (linearAccelUnit == "m/s^2") {
			linearAccelUnit = "cm/s^2";
			linearAccelConvertUnit = 100;
			
		} else if (linearAccelUnit == "cm/s^2") {
			linearAccelUnit = "mm/s^2";
			linearAccelConvertUnit = 1000;
			
		} else if (linearAccelUnit == "mm/s^2") {
			linearAccelUnit = "ft/s^2";
			linearAccelConvertUnit = 3.2808398950131;
			
		} else if (linearAccelUnit == "ft/s^2") {
			linearAccelUnit = "in/s^2";
			linearAccelConvertUnit = 39.370078740156;
			
		} else if (linearAccelUnit == "in/s^2") {
			linearAccelUnit = "m/s^2";
			linearAccelConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the LinearAcceleration is available or not
		if (linearAcceleration == null) {
			
			TextView linearAccelNotAvailable = (TextView) this.activity.findViewById(R.id.linear_acceleration_not_available);
			linearAccelNotAvailable.setVisibility(View.VISIBLE);
			linearAccelNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
			
			linearAccelVal_X.setVisibility(View.GONE);
			linearAccelUnit_X.setVisibility(View.GONE);
			
			linearAccelVal_Y.setVisibility(View.GONE);
			linearAccelUnit_Y.setVisibility(View.GONE);
			
			linearAccelVal_Z.setVisibility(View.GONE);
			linearAccelUnit_Z.setVisibility(View.GONE);
			
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
