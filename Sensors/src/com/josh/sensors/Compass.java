package com.josh.sensors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Compass extends View implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor 		accelerometer,
						magneticField;
	
	private float[] mAccelerometer,
					mMagneticField;
	
	private double rotationInDegress;
	
	private RelativeLayout mArrow;
	
	private Compass	mCompass;
	
	
	
	private Bitmap mBitmap;
	private int mBitmapWidth;
	
	// Height and Width of Main View
	private int mParentWidth;
	private int mParentHeight;
	
	// Center of Main View
	private int mParentCenterX;
	private int mParentCenterY;
	
	// Top left position of this View
	private int mViewTopX;
	private int mViewLeftY;
	
	
	// TextView for Light Sensor value and units
	//public TextView		pressureSensorVal, pressureSensorUnit;
	
	// Variable to change value to match units
	//private double		pressureConvertUnit;
	
	// Variable to store measurement units
	//private String		pressureUnit;
	
	private Activity	activity;
	private Context		context;
	
	public Compass (Activity mActivity, Context mContext) {
		super(mContext);
		this.activity = mActivity;
		this.context = mContext;
		
		mSensorManager = (SensorManager) context.getSystemService(android.content.Context.SENSOR_SERVICE);
		accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		mBitmap = BitmapFactory.decodeResource(this.activity.getResources(), R.drawable.arrow);
		mBitmapWidth = mBitmap.getWidth();
		
//		mArrow = (RelativeLayout) this.activity.findViewById(R.id.arrow_view);
		
		mParentWidth = mArrow.getWidth();
		
		mParentCenterX = mParentWidth / 2;
		mParentCenterY = mBitmapWidth / 2;
		
		mViewLeftY = mParentCenterX - mBitmapWidth / 2;
		mViewTopX = mParentCenterY - mBitmapWidth / 2;
		
		
	}
	
	public void changeCompassValues(SensorEvent event) {
		
		// Acquire accelerometer event data
		
				if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

					mAccelerometer = new float[3];
					System.arraycopy(event.values, 0, mAccelerometer, 0, 3);

				} 
				
				// Acquire magnetometer event data
				
				else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

					mMagneticField = new float[3];
					System.arraycopy(event.values, 0, mMagneticField, 0, 3);

				}

				// If we have readings from both sensors then
				// use the readings to compute the device's orientation
				// and then update the display.

				if (mAccelerometer != null && mMagneticField != null) {

					float rotationMatrix[] = new float[9];

					// Users the accelerometer and magnetometer readings
					// to compute the device's rotation with respect to
					// a real world coordinate system

					boolean success = SensorManager.getRotationMatrix(rotationMatrix,
							null, mAccelerometer, mMagneticField);

					if (success) {

						float orientationMatrix[] = new float[3];

						// Returns the device's orientation given
						// the rotationMatrix

						SensorManager.getOrientation(rotationMatrix, orientationMatrix);

						// Get the rotation, measured in radians, around the Z-axis
						// Note: This assumes the device is held flat and parallel
						// to the ground

						float rotationInRadians = orientationMatrix[0];

						// Convert from radians to degrees
						rotationInDegress = Math.toDegrees(rotationInRadians);

						try {
						// Request redraw
						mCompass.invalidate();
						} catch (NullPointerException e){
							
						}
						
						// Reset sensor event data arrays
						mAccelerometer = mMagneticField = null;

					}
				}
		
	}
	
	// Redraw the compass arrow
	@Override
	protected void onDraw(Canvas canvas) {
		
		// Save the convas
		canvas.save();
		
		// Rotate this View
		canvas.rotate((float) -rotationInDegress);
		
		canvas.drawBitmap(mBitmap, mViewLeftY, mViewTopX, null);
		
		canvas.restore();
	}

	
	
	
	
	
	
	
	
	
public void unregister() {
		
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	

}
