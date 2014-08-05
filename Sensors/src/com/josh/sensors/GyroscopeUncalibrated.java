package com.josh.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class GyroscopeUncalibrated implements SensorEventListener{
	
	private SensorManager mSensorManager;
	private Sensor		magneticFieldUncalib;
	
	// Magnetic Field values and units
	private TextView	uncalibGyroscopeVal_X, uncalibGyroscopeVal_Y, uncalibGyroscopeVal_Z,
						uncalibGyroscopeUnit_X, uncalibGyroscopeUnit_Y, uncalibGyroscopeUnit_Z,
	 					
						driftGyroscopeVal_X, driftGyroscopeVal_Y, driftGyroscopeVal_Z,
						driftGyroscopeUnit_X, driftGyroscopeUnit_Y, driftGyroscopeUnit_Z;

	// Variable to change values to match units
	private double 		uncalibGyroscopeConvertUnit, driftGyroscopeConvertUnit;
		
	// Variable to store measurement units
	private String 		uncalibGyroscopeUnit, driftGyroscopeUnit;

	private Activity	activity;
	private Context		context;
		
/****************************************************************************************************/
		
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public GyroscopeUncalibrated(Activity mActivity, Context mContext) {
			
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		magneticFieldUncalib = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
		
		// Uncalibrated
		uncalibGyroscopeVal_X =	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_x);
		uncalibGyroscopeUnit_X = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_unit_x);
			
		uncalibGyroscopeVal_Y = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_y);
		uncalibGyroscopeUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_unit_y);
			
		uncalibGyroscopeVal_Z = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_z);
		uncalibGyroscopeUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_uncalibrated_unit_z);
			
		uncalibGyroscopeConvertUnit = 1;
		uncalibGyroscopeUnit = "rad/sec";
		
		// Bias
		driftGyroscopeVal_X =	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_x);
		driftGyroscopeUnit_X = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_unit_x);
			
		driftGyroscopeVal_Y = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_y);
		driftGyroscopeUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_unit_y);
			
		driftGyroscopeVal_Z = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_z);
		driftGyroscopeUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.gyroscope_drift_unit_z);
			
		driftGyroscopeConvertUnit = 1;
		driftGyroscopeUnit = "rad/sec";
		}

	/****************************************************************************************************/
	
	public void changeGyroscopeUncalibValues(SensorEvent event) {
		
		// Uncalibrated
		uncalibGyroscopeVal_X.setText(String.valueOf(" X = " + (event.values[0]) * uncalibGyroscopeConvertUnit));
		uncalibGyroscopeUnit_X.setText(String.valueOf(uncalibGyroscopeUnit));
		
		uncalibGyroscopeVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * uncalibGyroscopeConvertUnit));
		uncalibGyroscopeUnit_Y.setText(String.valueOf(uncalibGyroscopeUnit));
		
		uncalibGyroscopeVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * uncalibGyroscopeConvertUnit));
		uncalibGyroscopeUnit_Z.setText(String.valueOf(uncalibGyroscopeUnit));
		
		// Drift
		driftGyroscopeVal_X.setText(String.valueOf(" X = " + (event.values[3]) * driftGyroscopeConvertUnit));
		driftGyroscopeUnit_X.setText(String.valueOf(driftGyroscopeUnit));
		
		driftGyroscopeVal_Y.setText(String.valueOf(" Y = " + (event.values[4]) * driftGyroscopeConvertUnit));
		driftGyroscopeUnit_Y.setText(String.valueOf(driftGyroscopeUnit));
		
		driftGyroscopeVal_Z.setText(String.valueOf(" Z = " + (event.values[5]) * driftGyroscopeConvertUnit));
		driftGyroscopeUnit_Z.setText(String.valueOf(driftGyroscopeUnit));
	}
	
/****************************************************************************************************/
	
	public void changeUnitsGyroscopeUncalib() {
		
		if (uncalibGyroscopeUnit == "rad/sec") {
			uncalibGyroscopeUnit = "Hz";
			uncalibGyroscopeConvertUnit = 0.15915494327;
			
		} else if (uncalibGyroscopeUnit == "Hz") {
			uncalibGyroscopeUnit = "degrees/sec";
			uncalibGyroscopeConvertUnit = 57.29578;
			
		} else if (uncalibGyroscopeUnit == "degrees/sec") {
			uncalibGyroscopeUnit = "rpm";
			uncalibGyroscopeConvertUnit = 9.5493;
			
		} else if (uncalibGyroscopeUnit == "rpm") {
			uncalibGyroscopeUnit = "rad/sec";
			uncalibGyroscopeConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public void changeUnitsGyroscopeDrift() {
		
		if (driftGyroscopeUnit == "rad/sec") {
			driftGyroscopeUnit = "Hz";
			driftGyroscopeConvertUnit = 0.15915494327;
			
		} else if (driftGyroscopeUnit == "Hz") {
			driftGyroscopeUnit = "degrees/sec";
			driftGyroscopeConvertUnit = 57.29578;
			
		} else if (driftGyroscopeUnit == "degrees/sec") {
			driftGyroscopeUnit = "rpm";
			driftGyroscopeConvertUnit = 9.5493;
			
		} else if (driftGyroscopeUnit == "rpm") {
			driftGyroscopeUnit = "rad/sec";
			driftGyroscopeConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Accelerometer is available or not	
		if (magneticFieldUncalib == null) {
					
			TextView gyroscopeUncalibNotAvailable = (TextView) this.activity.findViewById(R.id.gyroscope_uncalibrated_not_available);
			gyroscopeUncalibNotAvailable.setVisibility(View.VISIBLE);;
			gyroscopeUncalibNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			uncalibGyroscopeVal_X.setVisibility(View.GONE);
			uncalibGyroscopeUnit_X.setVisibility(View.GONE);
			
			uncalibGyroscopeVal_Y.setVisibility(View.GONE);
			uncalibGyroscopeUnit_Y.setVisibility(View.GONE);
			
			uncalibGyroscopeVal_Z.setVisibility(View.GONE);
			uncalibGyroscopeUnit_Z.setVisibility(View.GONE);
			
			TextView gyroscopeDriftNotAvailable = (TextView) this.activity.findViewById(R.id.gyroscope_drift_not_available);
			gyroscopeDriftNotAvailable.setVisibility(View.VISIBLE);;
			gyroscopeDriftNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			driftGyroscopeVal_X.setVisibility(View.GONE);
			driftGyroscopeUnit_X.setVisibility(View.GONE);
			
			driftGyroscopeVal_Y.setVisibility(View.GONE);
			driftGyroscopeUnit_Y.setVisibility(View.GONE);
			
			driftGyroscopeVal_Z.setVisibility(View.GONE);
			driftGyroscopeUnit_Z.setVisibility(View.GONE);
			
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
