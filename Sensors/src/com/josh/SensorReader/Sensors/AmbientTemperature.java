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

public class AmbientTemperature implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor 		ambientTemperatureSensor;
	
	// TextView for Light Sensor value and units
	public TextView		temperatureSensorVal, temperatureSensorUnit;
	
	// Variable to change value to match units
	private double		temperatureMultiUnit, temperatureAddUnit;
	
	// Variable to store measurement units
	private String		temperatureUnit;
	
	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public AmbientTemperature(Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		ambientTemperatureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		
		temperatureSensorVal = (TextView) this.activity.findViewById(R.id.temperature_sensor);
		temperatureSensorUnit = (TextView) this.activity.findViewById(R.id.temperature_sensor_unit);
		
		temperatureMultiUnit = 1;
		temperatureAddUnit = 0;
		temperatureUnit = "C";
	}

/****************************************************************************************************/
	
	public void changeAmbientTemperatureValues(SensorEvent event){
		
		temperatureSensorVal.setText(" Temperature = " + ((event.values[0] * temperatureMultiUnit) + temperatureAddUnit));
		temperatureSensorUnit.setText(temperatureUnit);
	}
	
/****************************************************************************************************/

	public void changeUnitsAmbientTemperatureSensor() {
		
		if (temperatureUnit == "C") {
			temperatureUnit = "F";
			temperatureMultiUnit = (9 / 5);
			temperatureAddUnit = 32;
			
		} else if (temperatureUnit == "F") {
			temperatureUnit = "C";
			temperatureMultiUnit = 1;
			temperatureAddUnit = 0;
		}
	}

/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Ambient Light sensor is available or not
		if (ambientTemperatureSensor == null) {
					
			TextView ambientLightNotAvailable = (TextView) this.activity.findViewById(R.id.temperature_not_available);
			ambientLightNotAvailable.setVisibility(View.VISIBLE);
			ambientLightNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			temperatureSensorVal.setVisibility(View.GONE);
			temperatureSensorUnit.setVisibility(View.GONE);
					
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
