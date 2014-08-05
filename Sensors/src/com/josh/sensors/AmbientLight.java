package com.josh.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class AmbientLight implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor 		ambientLightSensor;
	
	// TextView for Light Sensor value and units
	public TextView		lightSensorVal, lightSensorUnit;
	
	// Variable to change value to match units
	private double		lightConvertUnit;
	
	// Variable to store measurement units
	private String		lightUnit;
	
	private Activity	activity;
	private Context		context;
	
/****************************************************************************************************/
	
	// Used to create AmbientLight object and pass the activity and context
	// from MainActivity to use findViewById and SENSOR_SERVICE
	public AmbientLight(Activity mActivity, Context mContext) {
		
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		ambientLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		lightSensorVal = (TextView) this.activity.findViewById(R.id.light_sensor);
		lightSensorUnit = (TextView) this.activity.findViewById(R.id.light_sensor_unit);
		
		lightConvertUnit = 1;
		lightUnit = "lux";
	}

/****************************************************************************************************/
	
	public void changeAmbientLightValues(SensorEvent event){
		
		lightSensorVal.setText(" Intensity = " + (event.values[0] * lightConvertUnit));
		lightSensorUnit.setText(lightUnit);
	}
	
/****************************************************************************************************/

	public void changeUnitsAmbientLightSensor() {
		
		// Other units for lux not known by me
		// If conversion found put here
		// Make TextView id: light_sensor_unit clickable = true
	}

/****************************************************************************************************/
	
	public boolean isAvailable() {
		
		// Checks if the Ambient Light sensor is available or not
		if (ambientLightSensor == null) {
					
			TextView ambientLightNotAvailable = (TextView) this.activity.findViewById(R.id.ambient_light_not_available);
			ambientLightNotAvailable.setVisibility(View.VISIBLE);
			ambientLightNotAvailable.setText(MainActivity.SENSOR_NOT_AVAILABLE);
					
			lightSensorVal.setVisibility(View.GONE);
			lightSensorUnit.setVisibility(View.GONE);
					
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
