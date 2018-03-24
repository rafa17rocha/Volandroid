package net.tiproject.volandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
	private SensorManager mySensorManager;
	private SensorManager mySensorManager2;
	private Sensor mySensor;
	private Sensor mySensor2;

	private TextView gameXValue;
	private TextView gameYValue;
	private TextView gameZValue;

	private TextView rotXValue;
	private TextView rotYValue;
	private TextView rotZValue;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mySensorManager2 = (SensorManager) getSystemService(SENSOR_SERVICE);

		mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR); //API 18
		mySensor2 = mySensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); //API  9

		gameXValue = findViewById(R.id.gameXValue);
		gameYValue = findViewById(R.id.gameYValue);
		gameZValue = findViewById(R.id.gameZValue);

		rotXValue = findViewById(R.id.rotXValue);
		rotYValue = findViewById(R.id.rotYValue);
		rotZValue = findViewById(R.id.rotZValue);

		mySensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_FASTEST);
		mySensorManager2.registerListener(this, mySensor2, SensorManager.SENSOR_DELAY_FASTEST);
	}


	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if(event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR)
		{
			gameXValue.setText(String.format("%.2f", event.values[0]));
			gameYValue.setText(String.format("%.2f", event.values[1]));
			gameZValue.setText(String.format("%.2f", event.values[2]));
		}

		if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR)
		{
			rotXValue.setText(String.format("%.2f", event.values[0]));
			rotYValue.setText(String.format("%.2f", event.values[1]));
			rotZValue.setText(String.format("%.2f", event.values[2]));
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
}
