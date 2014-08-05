package com.josh.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements SensorEventListener {
	
	private SensorManager mSensorManager;

	// Nexus 7 Sensors
	private Sensor	accelerometer,
					linearAcceleration,
					ambientLight,
					gyroscope,
					gyroscopeUncalib,
					magneticField,
					magneticFieldUncalib,
					pressure,
					ambientTemperature;
	
	// Create objects of each sensor class
	private Accelerometer 		accelerometerObj;
	private LinearAcceleration 	linearAccelerationObj;
	private AmbientLight 		ambientLightObj;
	private Gyroscope			gyroscopeObj;
	private GyroscopeUncalibrated	gyroscopeUncalibObj;
	private MagneticField 		magneticFieldObj;
	private MagneticFieldUncalibrated	magneticFieldUncalibObj;
	private Pressure			pressureObj;
	private AmbientTemperature	ambientTemperatureObj;
	
	// Each sensor's last update time 
	private long	mLastUpdateAccel,
					mLastUpdateLinearAccel,
					mLastUpdateLight,
					mLastUpdateGyroscope,
					mLastUpdategyroscopeUncalib,
					mLastUpdateMagneticField,
					mLastUpdateMagneticFieldUncalib,
					mLastUpdatePressure,
					mLastUpdateTemperature;

	// Set how often sensors update
	private static final int UPDATE_THRESHOLD = 500;
	
	public static final String SENSOR_NOT_AVAILABLE = "Sensor is not available on this device";
	
/****************************************************************************************************/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create SensorManager
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		// Accelerometer
		accelerometerObj = new Accelerometer(this, this);
		accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		// Linear Acceleration
		linearAccelerationObj = new LinearAcceleration(this, this);
		linearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		
		// Ambient light
		ambientLightObj = new AmbientLight(this, this);
		ambientLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		// Gyroscope
		gyroscopeObj = new Gyroscope(this, this);
		gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		
		// Gyroscope
		gyroscopeUncalibObj = new GyroscopeUncalibrated(this, this);
		gyroscopeUncalib = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
		
		// Geomagnetic Field
		magneticFieldObj = new MagneticField(this, this);
		magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		// Geomagnetic Field Uncalibrated
		magneticFieldUncalibObj = new MagneticFieldUncalibrated(this, this);
		magneticFieldUncalib = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
		
		// Pressure
		pressureObj = new Pressure(this, this);
		pressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		
		// Temperature
		ambientTemperatureObj = new AmbientTemperature(this, this);
		ambientTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

	}
	
/****************************************************************************************************/
	
	@Override
	public void onSensorChanged(SensorEvent event) {

		long actualTime = System.currentTimeMillis();

		// Accelerometer data change
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			if (actualTime - mLastUpdateAccel > UPDATE_THRESHOLD) {
				mLastUpdateAccel = actualTime;
				accelerometerObj.changeAccelerometerValues(event);	
			}
		}
		
		// Accelerometer data change
		else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {

			if (actualTime - mLastUpdateLinearAccel > UPDATE_THRESHOLD) {
				mLastUpdateLinearAccel = actualTime;
				linearAccelerationObj.changeLinearAccelerationValues(event);
			}
		}

		// Light Sensor data change
		else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
			
			if (actualTime - mLastUpdateLight > UPDATE_THRESHOLD) {
				mLastUpdateLight = actualTime;
				ambientLightObj.changeAmbientLightValues(event);
			}
		}
		
		// Gyroscope data change
		else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {

			if (actualTime - mLastUpdateGyroscope > UPDATE_THRESHOLD) {
				mLastUpdateGyroscope = actualTime;
				gyroscopeObj.changeGyroscopeValues(event);
			}
		}
		
		// Uncalibrated Gyroscope data change
		else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED) {

			if (actualTime - mLastUpdategyroscopeUncalib > UPDATE_THRESHOLD) {
				mLastUpdategyroscopeUncalib = actualTime;
				gyroscopeUncalibObj.changeGyroscopeUncalibValues(event);
			}
		}
		
		// Magnetic Field data change
		else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

			if (actualTime - mLastUpdateMagneticField > UPDATE_THRESHOLD) {
				mLastUpdateMagneticField = actualTime;
				magneticFieldObj.changeMagneticFieldValues(event);	
			}
		}
		
		// Uncalibrated Magnetic Field data change
		else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED) {

			if (actualTime - mLastUpdateMagneticFieldUncalib > UPDATE_THRESHOLD) {
				mLastUpdateMagneticFieldUncalib = actualTime;
				magneticFieldUncalibObj.changeMagneticFieldUncalibValues(event);	
			}
		}
		
		// Pressure data change
		else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
			
			if (actualTime - mLastUpdatePressure > UPDATE_THRESHOLD) {
				mLastUpdatePressure = actualTime;
				pressureObj.changePressureValues(event);
			}
		}
		
		// Temperature data change
		else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
			
			if (actualTime - mLastUpdateTemperature > UPDATE_THRESHOLD) {
				mLastUpdateTemperature = actualTime;
				ambientTemperatureObj.changeAmbientTemperatureValues(event);
			}
		}
	}
	
/****************************************************************************************************/

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

/****************************************************************************************************/
	
	public void changeUnitsAccel(View v) {
		
		accelerometerObj.changeUnitsAccelerometer();
	}
	
	public void changeUnitsLinearAccel(View v) {
		
		linearAccelerationObj.changeUnitsLinearAcceleration();
	}
	
	public void changeUnitsAmbientLight(View v) {
		
		ambientLightObj.changeUnitsAmbientLightSensor();
	}
	
	public void changeUnitsGyroscope(View v) {
		
		gyroscopeObj.changeUnitsGyroscope();
	}
	
	public void changeUnitsGyroscopeUncalibrated (View v) {
		
		gyroscopeUncalibObj.changeUnitsGyroscopeUncalib();
	}
	
	public void changeUnitsGyroscopeDrift (View v) {
		
		gyroscopeUncalibObj.changeUnitsGyroscopeDrift();
	}
	
	public void changeUnitsMagneticField(View v) {
		
		magneticFieldObj.changeUnitsMagneticField();
	}
	
	public void changeUnitsMagneticFieldUncalibrated(View v) {
		
		magneticFieldUncalibObj.changeUnitsMagneticFieldUncalib();
	}

	public void changeUnitsMagneticFieldBias(View v) {
	
		magneticFieldUncalibObj.changeUnitsMagneticFieldBias();
	}
	
	public void changeUnitsPressure (View v) {
		
		pressureObj.changeUnitsPressureSensor();
	}
	
	public void changeUnitsTemperature (View v) {
		
		ambientTemperatureObj.changeUnitsAmbientTemperatureSensor();
	}

/****************************************************************************************************/
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// Checks if the Accelerometer is available or not	
		if (accelerometerObj.isAvailable())
				mSensorManager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the LinearAcceleration sensor is available or not
		if (linearAccelerationObj.isAvailable())
				mSensorManager.registerListener(this, linearAcceleration,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Ambient Light sensor is available or not
		if (ambientLightObj.isAvailable())
				mSensorManager.registerListener(this, ambientLight,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Gyroscope is available or not
		if (gyroscopeObj.isAvailable())
				mSensorManager.registerListener(this, gyroscope,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Uncalibrated Gyroscope sensor is available or not
		if (gyroscopeUncalibObj.isAvailable())
				mSensorManager.registerListener(this, gyroscopeUncalib,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Magnetic Field sensor is available or not	
		if (magneticFieldObj.isAvailable())
				mSensorManager.registerListener(this, magneticField,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Uncalibrated Magnetic Field sensor is available or not
		if (magneticFieldUncalibObj.isAvailable())
				mSensorManager.registerListener(this, magneticFieldUncalib,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Pressure sensor is available or not
		if (pressureObj.isAvailable())
				mSensorManager.registerListener(this, pressure,
					SensorManager.SENSOR_DELAY_UI);
		
		// Checks if the Pressure sensor is available or not
		if (ambientTemperatureObj.isAvailable())
				mSensorManager.registerListener(this, ambientTemperature,
					SensorManager.SENSOR_DELAY_UI);
	}
	
/****************************************************************************************************/
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// Unregister all SensorManagers
		mSensorManager.unregisterListener(this);
		accelerometerObj.unregister();
		linearAccelerationObj.unregister();
		ambientLightObj.unregister();
		gyroscopeObj.unregister();
		gyroscopeUncalibObj.unregister();
		magneticFieldObj.unregister();
		magneticFieldUncalibObj.unregister();
		pressureObj.unregister();
		ambientTemperatureObj.unregister();
	}
	
}