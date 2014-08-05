package com.josh.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class MagneticFieldUncalibrated implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor		magneticFieldUncalib;
	
	// Magnetic Field values and units
	private TextView	uncalibMagneticFieldVal_X, uncalibMagneticFieldVal_Y, uncalibMagneticFieldVal_Z,
						uncalibMagneticFieldUnit_X, uncalibMagneticFieldUnit_Y, uncalibMagneticFieldUnit_Z,
	 					
						biasMagneticFieldVal_X, biasMagneticFieldVal_Y, biasMagneticFieldVal_Z,
						biasMagneticFieldUnit_X, biasMagneticFieldUnit_Y, biasMagneticFieldUnit_Z;

	// Variable to change values to match units
	private double 		uncalibMagneticFieldConvertUnit, biasMagneticFieldConvertUnit;
		
	// Variable to store measurement units
	private String 		uncalibMagneticFieldUnit, biasMagneticFieldUnit;

	private Activity	activity;
	private Context		context;
		
/****************************************************************************************************/
		
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public MagneticFieldUncalibrated(Activity mActivity, Context mContext) {
			
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		magneticFieldUncalib = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
		
		// Uncalibrated
		uncalibMagneticFieldVal_X =	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_x);
		uncalibMagneticFieldUnit_X = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_unit_x);
			
		uncalibMagneticFieldVal_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_y);
		uncalibMagneticFieldUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_unit_y);
			
		uncalibMagneticFieldVal_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_z);
		uncalibMagneticFieldUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_uncalibrated_unit_z);
			
		uncalibMagneticFieldConvertUnit = 1;
		uncalibMagneticFieldUnit = "\u00B5" + "T";
		
		// Bias
		biasMagneticFieldVal_X =	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_x);
		biasMagneticFieldUnit_X = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_unit_x);
			
		biasMagneticFieldVal_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_y);
		biasMagneticFieldUnit_Y = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_unit_y);
			
		biasMagneticFieldVal_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_z);
		biasMagneticFieldUnit_Z = 	(TextView) 	this.activity.findViewById(R.id.magnetic_field_bias_unit_z);
			
		biasMagneticFieldConvertUnit = 1;
		biasMagneticFieldUnit = "\u00B5" + "T";
		}

	/****************************************************************************************************/
	
	public void changeMagneticFieldUncalibValues(SensorEvent event) {
		
		// Uncalibrated
		uncalibMagneticFieldVal_X.setText(String.valueOf(" X = " + (event.values[0]) * uncalibMagneticFieldConvertUnit));
		uncalibMagneticFieldUnit_X.setText(String.valueOf(uncalibMagneticFieldUnit));
		
		uncalibMagneticFieldVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * uncalibMagneticFieldConvertUnit));
		uncalibMagneticFieldUnit_Y.setText(String.valueOf(uncalibMagneticFieldUnit));
		
		uncalibMagneticFieldVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * uncalibMagneticFieldConvertUnit));
		uncalibMagneticFieldUnit_Z.setText(String.valueOf(uncalibMagneticFieldUnit));
		
		// Bias
		biasMagneticFieldVal_X.setText(String.valueOf(" X = " + (event.values[3]) * biasMagneticFieldConvertUnit));
		biasMagneticFieldUnit_X.setText(String.valueOf(biasMagneticFieldUnit));
		
		biasMagneticFieldVal_Y.setText(String.valueOf(" Y = " + (event.values[4]) * biasMagneticFieldConvertUnit));
		biasMagneticFieldUnit_Y.setText(String.valueOf(biasMagneticFieldUnit));
		
		biasMagneticFieldVal_Z.setText(String.valueOf(" Z = " + (event.values[5]) * biasMagneticFieldConvertUnit));
		biasMagneticFieldUnit_Z.setText(String.valueOf(biasMagneticFieldUnit));
	}
	
/****************************************************************************************************/
	
	public void changeUnitsMagneticFieldUncalib() {
		
		if (uncalibMagneticFieldUnit == "\u00B5" + "T") {
			uncalibMagneticFieldUnit = "mT";
			uncalibMagneticFieldConvertUnit = 0.001;
			
		} else if (uncalibMagneticFieldUnit == "mT") {
			uncalibMagneticFieldUnit = "T";
			uncalibMagneticFieldConvertUnit = .000001;
			
		} else if (uncalibMagneticFieldUnit == "T") {
			uncalibMagneticFieldUnit = "pT";
			uncalibMagneticFieldConvertUnit = 1000000;
			
		} else if (uncalibMagneticFieldUnit == "pT") {
			uncalibMagneticFieldUnit = "nT";
			uncalibMagneticFieldConvertUnit = 1000;
			
		} else if (uncalibMagneticFieldUnit == "nT") {
			uncalibMagneticFieldUnit = "\u00B5" + "T";
			uncalibMagneticFieldConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public void changeUnitsMagneticFieldBias() {
		
		if (biasMagneticFieldUnit == "\u00B5" + "T") {
			biasMagneticFieldUnit = "mT";
			biasMagneticFieldConvertUnit = 0.001;
			
		} else if (biasMagneticFieldUnit == "mT") {
			biasMagneticFieldUnit = "T";
			biasMagneticFieldConvertUnit = .000001;
			
		} else if (biasMagneticFieldUnit == "T") {
			biasMagneticFieldUnit = "pT";
			biasMagneticFieldConvertUnit = 1000000;
			
		} else if (biasMagneticFieldUnit == "pT") {
			biasMagneticFieldUnit = "nT";
			biasMagneticFieldConvertUnit = 1000;
			
		} else if (biasMagneticFieldUnit == "nT") {
			biasMagneticFieldUnit = "\u00B5" + "T";
			biasMagneticFieldConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Accelerometer is available or not	
		if (magneticFieldUncalib == null) {
					
			TextView magneticFieldUncalibNotAvailable = (TextView) this.activity.findViewById(R.id.magnetic_field_uncalibrated_not_available);
			magneticFieldUncalibNotAvailable.setVisibility(View.VISIBLE);;
			magneticFieldUncalibNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			uncalibMagneticFieldVal_X.setVisibility(View.GONE);
			uncalibMagneticFieldUnit_X.setVisibility(View.GONE);
			
			uncalibMagneticFieldVal_Y.setVisibility(View.GONE);
			uncalibMagneticFieldUnit_Y.setVisibility(View.GONE);
			
			uncalibMagneticFieldVal_Z.setVisibility(View.GONE);
			uncalibMagneticFieldUnit_Z.setVisibility(View.GONE);
			
			TextView magneticFieldBiasNotAvailable = (TextView) this.activity.findViewById(R.id.magnetic_field_bias_not_available);
			magneticFieldBiasNotAvailable.setVisibility(View.VISIBLE);;
			magneticFieldBiasNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			biasMagneticFieldVal_X.setVisibility(View.GONE);
			biasMagneticFieldUnit_X.setVisibility(View.GONE);
			
			biasMagneticFieldVal_Y.setVisibility(View.GONE);
			biasMagneticFieldUnit_Y.setVisibility(View.GONE);
			
			biasMagneticFieldVal_Z.setVisibility(View.GONE);
			biasMagneticFieldUnit_Z.setVisibility(View.GONE);
			
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
