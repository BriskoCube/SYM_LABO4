package ch.heigvd.iict.sym_labo4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Array;
import java.util.Arrays;

import ch.heigvd.iict.sym_labo4.gl.OpenGLRenderer;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    //opengl
    private OpenGLRenderer  opglr           = null;
    private GLSurfaceView   m3DView         = null;

    private SensorManager sensorManager     = null;
    private Sensor accelerometer            = null;
    private Sensor magnetic                 = null;

    private float[] gravity                 = null;
    private float[] geomagnetic             = null;

    private float[] rotationMatrix          = new float[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // we need fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // we initiate the view
        setContentView(R.layout.activity_compass);

        //we create the renderer
        this.opglr = new OpenGLRenderer(getApplicationContext());

        // link to GUI
        this.m3DView = findViewById(R.id.compass_opengl);

        //init opengl surface view
        this.m3DView.setRenderer(this.opglr);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                this.gravity = sensorEvent.values;
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                this.geomagnetic = sensorEvent.values;
                break;
        }

        if(this.gravity != null && this.geomagnetic != null && SensorManager.getRotationMatrix(this.rotationMatrix, null, this.gravity, this.geomagnetic)) {
            this.rotationMatrix = this.opglr.swapRotMatrix(this.rotationMatrix);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
