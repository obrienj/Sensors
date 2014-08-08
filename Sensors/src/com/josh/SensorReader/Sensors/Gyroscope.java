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

public class Gyroscope implements SensorEventListener {
	
	private SensorManager 	mSensorManager;
	private Sensor			gyroscope;
	
	// Gyroscope values and units
	private TextView	gyroscopeVal_X, gyroscopeVal_Y, gyroscopeVal_Z,
						gyroscopeUnit_X, gyroscopeUnit_Y, gyroscopeUnit_Z;
	
	// Variable to change value to match units
	private double		gyroscopeConvertUnit;
	
	// Variable to store measurement units
	private String		gyroscopeUnit;
	
	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/

	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public Gyroscope (Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		
		gyroscopeVal_X = (TextView) this.activity.findViewById(R.id.gyroscope_x);
		gyroscopeUnit_X = (TextView) this.activity.findViewById(R.id.gyroscope_unit_x);
		
		gyroscopeVal_Y = (TextView) this.activity.findViewById(R.id.gyroscope_y);
		gyroscopeUnit_Y = (TextView) this.activity.findViewById(R.id.gyroscope_unit_y);
		
		gyroscopeVal_Z = (TextView) this.activity.findViewById(R.id.gyroscope_z);
		gyroscopeUnit_Z = (TextView) this.activity.findViewById(R.id.gyroscope_unit_z);
		
		gyroscopeConvertUnit = 1;
		gyroscopeUnit = "rad/sec";
	}
	
/****************************************************************************************************/	

	public void changeGyroscopeValues (SensorEvent event) {
		
		gyroscopeVal_X.setText(String.valueOf(" X = " + (event.values[0]) * gyroscopeConvertUnit));
		gyroscopeUnit_X.setText(String.valueOf(gyroscopeUnit));
		
		gyroscopeVal_Y.setText(String.valueOf(" Y = " + (event.values[1]) * gyroscopeConvertUnit));
		gyroscopeUnit_Y.setText(String.valueOf(gyroscopeUnit));
		
		gyroscopeVal_Z.setText(String.valueOf(" Z = " + (event.values[2]) * gyroscopeConvertUnit));
		gyroscopeUnit_Z.setText(String.valueOf(gyroscopeUnit));
	}
	
/****************************************************************************************************/	
	
	public void changeUnitsGyroscope() {
		
		if (gyroscopeUnit == "rad/sec") {
			gyroscopeUnit = "Hz";
			gyroscopeConvertUnit = 0.15915494327;
			
		} else if (gyroscopeUnit == "Hz") {
			gyroscopeUnit = "degrees/sec";
			gyroscopeConvertUnit = 57.29578;
			
		} else if (gyroscopeUnit == "degrees/sec") {
			gyroscopeUnit = "rpm";
			gyroscopeConvertUnit = 9.5493;
			
		} else if (gyroscopeUnit == "rpm") {
			gyroscopeUnit = "rad/sec";
			gyroscopeConvertUnit = 1;
		}
	}
	
/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Gyroscope is available or not
		if (gyroscope == null) {
			
			TextView gyroscopeNotAvailable = (TextView) this.activity.findViewById(R.id.gyroscope_not_available);
			gyroscopeNotAvailable.setVisibility(View.VISIBLE);
			gyroscopeNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
			
			gyroscopeVal_X.setVisibility(View.GONE);
			gyroscopeUnit_X.setVisibility(View.GONE);
			
			gyroscopeVal_Y.setVisibility(View.GONE);
			gyroscopeUnit_Y.setVisibility(View.GONE);
			
			gyroscopeVal_Z.setVisibility(View.GONE);
			gyroscopeUnit_Z.setVisibility(View.GONE);
			
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
