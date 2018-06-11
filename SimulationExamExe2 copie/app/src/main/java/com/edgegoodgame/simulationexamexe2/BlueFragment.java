package com.edgegoodgame.simulationexamexe2;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;




public class BlueFragment extends Fragment implements SensorEventListener {//implements SensorEventListener

    public static final String ARG_PARAM1 = "number";

    private SensorManager sensorManager;
    private Sensor lightMeter;
    private static BlueFragment myInstance = null;
    private static int numberSaved = 0;

    TextView lightNB ;


    //Design pattern Singleton (to make sure we have only one object(instance) of that class in memory
    public BlueFragment() {
        // Required empty public constructor
    }



    public static BlueFragment getInstance() {
        //a la creation si la valeur est null seulement
        if(myInstance == null){
            myInstance = new BlueFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PARAM1, BlueFragment.numberSaved);
            myInstance.setArguments(args);
        }
        //retourne la valeur deja save en haut
        return myInstance;
    }


    /* --------------------->> demander plus d explication au prof <<----------------------*/

    // methode pour mettre a jour la variable dans la zone memoire....tjrs call la function a l exterieur
    public static void updateNumber(int x){

        BlueFragment.numberSaved = x;
        BlueFragment myFragment =  BlueFragment.getInstance();
        Bundle args = myFragment.getArguments();
        args.putInt(ARG_PARAM1, BlueFragment.numberSaved);// *** assurer que put est la facon pour changer la valeur arg
        //myFragment.setArguments(args); //pas necessaire
    }

    /***************************************************************/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void initialize() {
        sensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightMeter = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightNB = getActivity().findViewById(R.id.lightNB);

        if (lightMeter != null){
            Toast.makeText(this.getActivity(),"Sensor is available",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this.getActivity(),"Sensor is not available",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blue, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            initialize();

            lightNB = getView().findViewById(R.id.lightNB);
            //text.setText(BlueFragment.numberSaved);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        lightNB = getView().findViewById(R.id.lightNB);
        lightNB.setText(Float.toString(event.values[0]));

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,lightMeter,SensorManager.SENSOR_DELAY_FASTEST);
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
