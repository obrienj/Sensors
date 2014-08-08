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

public class Pressure implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor 		pressureSensor;
	
	// TextView for Light Sensor value and units
	public TextView		pressureSensorVal, pressureSensorUnit;
	
	// Variable to change value to match units
	private double		pressureConvertUnit;
	
	// Variable to store measurement units
	private String		pressureUnit;
	
	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public Pressure(Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		pressureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		
		pressureSensorVal = (TextView) this.activity.findViewById(R.id.pressure_sensor);
		pressureSensorUnit = (TextView) this.activity.findViewById(R.id.pressure_sensor_unit);
		
		pressureConvertUnit = 1;
		pressureUnit = "hPa";
	}

/****************************************************************************************************/
	
	public void changePressureValues(SensorEvent event){
		
		pressureSensorVal.setText(" Pressure = " + (event.values[0] * pressureConvertUnit));
		pressureSensorUnit.setText(pressureUnit);
	}
	
/****************************************************************************************************/

	public void changeUnitsPressureSensor() {
		
		if (pressureUnit == "hPa") {
			pressureUnit = "Pa";
			pressureConvertUnit = 100;
			
		} else if (pressureUnit == "Pa") {
			pressureUnit = "Torr";
			pressureConvertUnit = 0.7500616827042;
			
		} else if (pressureUnit == "Torr") {
			pressureUnit = "atm";
			pressureConvertUnit = 0.000986923266716;
			
		} else if (pressureUnit == "atm") {
			pressureUnit = "psi";
			pressureConvertUnit = 0.01450378911491;
			
		} else if (pressureUnit == "psi") {
			pressureUnit = "hPa";
			pressureConvertUnit = 1;
		}
	}

/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Ambient Light sensor is available or not
		if (pressureSensor == null) {
					
			TextView pressureNotAvailable = (TextView) this.activity.findViewById(R.id.pressure_not_available);
			pressureNotAvailable.setVisibility(View.VISIBLE);
			pressureNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			pressureSensorVal.setVisibility(View.GONE);
			pressureSensorUnit.setVisibility(View.GONE);
					
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
