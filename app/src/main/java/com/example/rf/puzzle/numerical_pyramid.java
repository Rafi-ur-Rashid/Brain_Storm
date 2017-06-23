package com.example.rf.puzzle;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link numerical_pyramid.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class numerical_pyramid extends Fragment {
    TextView[][] pyramid;
    View view;
    Button[] button;
    Button clear;
    Context context;
    CountDownTimer countDownTimer;
    TextView time;
    int j,k,r;
    private OnFragmentInteractionListener mListener;
    int[] last,current;
    public numerical_pyramid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random random=new Random();
        r=random.nextInt(3);
        if(r==0)
            view= inflater.inflate(R.layout.fragment_numerical_pyramid, container, false);
        else if(r==1)
            view= inflater.inflate(R.layout.fragment_numerical_pyramid2, container, false);
        else
            view= inflater.inflate(R.layout.fragment_numerical_pyramid3, container, false);
        pyramid=new TextView[5][];
        for (int i=0;i<5;i++)
            pyramid[i]=new TextView[i+1];

        button=new Button[10];
        clear=(Button)view.findViewById(R.id.buttonClear);
        time=(TextView)view.findViewById(R.id.time);
        last=new int[2];
        current=new int[2];
        getRef(view);
        for(int i=0;i<5;i++){
            for (int j=0;j<pyramid[i].length;j++) {
                final int I = i, J = j;
                if (pyramid[i][j].isClickable()) {
                    pyramid[i][j].setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(View v) {
                        pyramid[I][J].setBackground(ContextCompat.getDrawable(context, R.drawable.border));
                        current[0]=I;
                        current[1]=J;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && pyramid[last[0]][last[1]].isClickable()) {
                                pyramid[last[0]][last[1]].setBackgroundColor(getResources().getColor(android.R.color.background_light,null));
                            }
                        last[0]=I;
                        last[1]=J;
                        }
                    });
                }
            }
        }
        for (int i=0;i<10;i++){
            final int I=i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pyramid[current[0]][current[1]].isClickable() && (pyramid[current[0]][current[1]].getText().toString()).length() < 2) {
                        pyramid[current[0]][current[1]].setText(pyramid[current[0]][current[1]].getText().toString() + I);
                        if(checkResult()){
                            countDownTimer.cancel();
                            clear.setClickable(false);
                        }

                    }
                }
            });
        }
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pyramid[current[0]][current[1]].isClickable())
                    pyramid[current[0]][current[1]].setText("");
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        countDownTimer=new CountDownTimer(3000000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (k < 10) {
                    time.setText("   0" + j + " : " + "0" + k++);
                } else if (k < 60) {
                    time.setText("   0" + j + " : " + k++);
                } else {
                    k = 0;
                    time.setText("   0" + (++j) + " : " + "0" + k++);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void getRef(View view){
        pyramid[0][0]=(TextView)view.findViewById(R.id.t11);
        pyramid[1][0]=(TextView)view.findViewById(R.id.t21);
        pyramid[1][1]=(TextView)view.findViewById(R.id.t22);
        pyramid[2][0]=(TextView)view.findViewById(R.id.t31);
        pyramid[2][1]=(TextView)view.findViewById(R.id.t32);
        pyramid[2][2]=(TextView)view.findViewById(R.id.t33);
        pyramid[3][0]=(TextView)view.findViewById(R.id.t41);
        pyramid[3][1]=(TextView)view.findViewById(R.id.t42);
        pyramid[3][2]=(TextView)view.findViewById(R.id.t43);
        pyramid[3][3]=(TextView)view.findViewById(R.id.t44);
        pyramid[4][0]=(TextView)view.findViewById(R.id.t51);
        pyramid[4][1]=(TextView)view.findViewById(R.id.t52);
        pyramid[4][2]=(TextView)view.findViewById(R.id.t53);
        pyramid[4][3]=(TextView)view.findViewById(R.id.t54);
        pyramid[4][4]=(TextView)view.findViewById(R.id.t55);
        button[0]=(Button)view.findViewById(R.id.button0);
        button[1]=(Button)view.findViewById(R.id.button1);
        button[2]=(Button)view.findViewById(R.id.button2);
        button[3]=(Button)view.findViewById(R.id.button3);
        button[4]=(Button)view.findViewById(R.id.button4);
        button[5]=(Button)view.findViewById(R.id.button5);
        button[6]=(Button)view.findViewById(R.id.button6);
        button[7]=(Button)view.findViewById(R.id.button7);
        button[8]=(Button)view.findViewById(R.id.button8);
        button[9]=(Button)view.findViewById(R.id.button9);
    }
    public boolean checkResult(){
        int f,s,r;
        for(int i=1;i<5;i++){
            for(int j=0;j<pyramid[i].length-1;j++) {
                f = Integer.parseInt("0"+pyramid[i][j].getText().toString());
                s = Integer.parseInt("0"+pyramid[i][j + 1].getText().toString());
                r = Integer.parseInt("0"+pyramid[i - 1][j].getText().toString());
                if ((f + s) != r)
                    return false;
            }
        }
        return true;
    }
}
