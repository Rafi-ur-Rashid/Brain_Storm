package com.example.rf.puzzle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link short_term_memory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class short_term_memory extends Fragment {
    ImageView imageView[][];
    String[] imageName;
    TextView time;
    int count;
    ValueAnimator mFlipAnimator;
    CountDownTimer countDownTimer;
    View view;
    int j=0,k=0;
    private OnFragmentInteractionListener mListener;
    static final int LARGE=99999;
    int prev=-1;
    public short_term_memory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_short_term_memory, container, false);
        imageView=new ImageView[12][2];
        initialize();
        time=(TextView)view.findViewById(R.id.time);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    public void initialize(){
        String[] imgName={"mango","banana","coconut","banana","strawBerry","guava","coconut","strawBerry","mango","apple","apple","guava"};
        imageName=imgName;
        imageView[0][0]=(ImageView)view.findViewById(R.id.ImageView1);
        imageView[0][1]=(ImageView)view.findViewById(R.id.imageView1b);
        imageView[1][0]=(ImageView)view.findViewById(R.id.ImageView2);
        imageView[1][1]=(ImageView)view.findViewById(R.id.ImageView2b);
        imageView[2][0]=(ImageView)view.findViewById(R.id.ImageView3);
        imageView[2][1]=(ImageView)view.findViewById(R.id.imageView3b);
        imageView[3][0]=(ImageView)view.findViewById(R.id.ImageView4);
        imageView[3][1]=(ImageView)view.findViewById(R.id.imageView4b);
        imageView[4][0]=(ImageView)view.findViewById(R.id.ImageView5);
        imageView[4][1]=(ImageView)view.findViewById(R.id.imageView5b);
        imageView[5][0]=(ImageView)view.findViewById(R.id.ImageView6);
        imageView[5][1]=(ImageView)view.findViewById(R.id.imageView6b);
        imageView[6][0]=(ImageView)view.findViewById(R.id.ImageView7);
        imageView[6][1]=(ImageView)view.findViewById(R.id.imageView7b);
        imageView[7][0]=(ImageView)view.findViewById(R.id.ImageView8);
        imageView[7][1]=(ImageView)view.findViewById(R.id.imageView8b);
        imageView[8][0]=(ImageView)view.findViewById(R.id.ImageView9);
        imageView[8][1]=(ImageView)view.findViewById(R.id.imageView9b);
        imageView[9][0]=(ImageView)view.findViewById(R.id.ImageView10);
        imageView[9][1]=(ImageView)view.findViewById(R.id.imageView10b);
        imageView[10][0]=(ImageView)view.findViewById(R.id.ImageView11);
        imageView[10][1]=(ImageView)view.findViewById(R.id.imageView11b);
        imageView[11][0]=(ImageView)view.findViewById(R.id.ImageView12);
        imageView[11][1]=(ImageView)view.findViewById(R.id.imageView12b);
        for(int I=0;I<12;I++){
            final int i=I;
                imageView[i][0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFlipAnimator = ValueAnimator.ofFloat(0f, 1f);
                        mFlipAnimator.addUpdateListener(new FlipListener(imageView[i][0],imageView[i][1]));
                        mFlipAnimator.start();
                        if(prev==-1){
                            prev=i;
                        }
                        else {
                            if(!imageName[i].equals(imageName[prev])) {
                                final int Prev = prev;
                                Runnable r = new Runnable() {
                                    @Override
                                    public void run() {
                                        while (!isFlipped()) {
                                            for (int i=0;i<12;i++)
                                            {
                                                imageView[i][0].setClickable(false);
                                            }
                                        }
                                        try {
                                            Thread.sleep(300);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        for (int i=0;i<12;i++)
                                        {
                                            imageView[i][0].setClickable(true);
                                        }
                                        imageView[i][1].post(new Runnable() {
                                            @Override
                                            public void run() {
                                                mFlipAnimator.reverse();
                                                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                                                ;
                                                valueAnimator.addUpdateListener(new FlipListener(imageView[Prev][1], imageView[Prev][0]));
                                                valueAnimator.start();
                                            }
                                        });
                                    }

                                };
                                Thread t = new Thread(r);
                                t.start();
                            }
                            else
                                count++;
                            if(count>=(imageName.length/2))
                                countDownTimer.cancel();
                            prev=-1;
                        }
                    }

                });
        }
    }
    private boolean isFlipped() {

        return mFlipAnimator.getAnimatedFraction() == 1;
    }
}
