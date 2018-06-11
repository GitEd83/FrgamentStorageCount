package com.edgegoodgame.simulationexamexe2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


public class GreenFragment extends Fragment implements OnClickListener {

    private static final String ARG_PARAM1 = "stepperNumber";

    private static GreenFragment myInstance = null;
    private static int x = 0;
    TextView number ;


    public GreenFragment() {
        // Required empty public constructor
    }


    public static GreenFragment getInstance() {
        if (myInstance == null) {
            myInstance = new GreenFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PARAM1, GreenFragment.x);

            myInstance.setArguments(args);
        }
        return myInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_green, container, false);

        Button btnMinus = rootView.findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);
        Button btnPlus = rootView.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);
        number = rootView.findViewById(R.id.textViewNumber);

        UpdateCount(x);

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMinus:
                UpdateCount( x - 1);
                break;

            case R.id.btnPlus:
                UpdateCount(x + 1);
                break;
        }

    }

    void UpdateCount(int n){
        x = n;
        number.setText(Integer.toString(x));
    }
}
