package com.example.rf.puzzle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link puzzle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class puzzle extends Fragment {
    ImageView imageView[];
    TextView time;
    CountDownTimer countDownTimer;
    View view;
    int j=0,k=0;
    Drawable[] correctImage;
    boolean move=false,clickable[];
    int tempB,r;
    private OnFragmentInteractionListener mListener;
    Context context;
    public puzzle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random random=new Random();
        r=random.nextInt(3);
        if(r==0)
            view= inflater.inflate(R.layout.fragment_puzzle, container, false);
        else if(r==1)
            view= inflater.inflate(R.layout.fragment_puzzle2, container, false);
        else
            view= inflater.inflate(R.layout.fragment_puzzle3, container, false);
        imageView=new ImageView[9];
        time=(TextView)view.findViewById(R.id.time);
        correctImage=new Drawable[9];
        clickable=new boolean[9];
        imageView[0]=(ImageView)view.findViewById(R.id.ImageView1);
        imageView[1]=(ImageView)view.findViewById(R.id.ImageView2);
        imageView[2]=(ImageView)view.findViewById(R.id.ImageView3);
        imageView[3]=(ImageView)view.findViewById(R.id.ImageView4);
        imageView[4]=(ImageView)view.findViewById(R.id.ImageView5);
        imageView[5]=(ImageView)view.findViewById(R.id.ImageView6);
        imageView[6]=(ImageView)view.findViewById(R.id.ImageView7);
        imageView[7]=(ImageView)view.findViewById(R.id.ImageView8);
        imageView[8]=(ImageView)view.findViewById(R.id.ImageView9);
        correctImages();
        for (int i=0;i<9;i++)
            clickable[i]=true;
        if(r==2)
            clickable[6]=false;
        else
            clickable[8]=false;
        for (int i=0;i<9;i++){
            final int I=i;
            imageView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((I-1)>=0 && (I)%3!=0 && !clickable[I-1]){
                        move=true;
                        tempB=I-1;
                    }else if((I+1)<9 && (I+1)%3!=0 && !clickable[I+1]){

                        move=true;
                        tempB=I+1;
                    }else if((I-3)>=0 && !clickable[I-3]){

                        move=true;
                        tempB=I-3;
                    }else if((I+3)<9 && !clickable[I+3]){

                        move=true;
                        tempB=I+3;
                    }
                    if(move)
                    {
                        clickable[I]=false;
                        clickable[tempB]=true;
                        imageView[I].setVisibility(view.INVISIBLE);
                        imageView[tempB].setImageDrawable(imageView[I].getDrawable());
                        imageView[tempB].setVisibility(View.VISIBLE);
                        move=false;
                        if(matchPuzzle()) {
                            countDownTimer.cancel();
                            imageView[I].setVisibility(view.VISIBLE);
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(r==2)
                            {
                                imageView[6].setImageResource(R.mipmap.tiger6);
                                imageView[6].setVisibility(View.VISIBLE);
                            }
                            else {
                                imageView[8].setImageResource(R.mipmap.image_part_009);
                                imageView[8].setVisibility(View.VISIBLE);
                            }
                            for(int i=0;i<9;i++)
                                imageView[i].setClickable(false);
                        }
                    }
                }

            });
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
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
        super.onAttach(context);
        this.context=context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void correctImages(){
        if(r==0){
            correctImage[0]=imageView[0].getDrawable();
            correctImage[1]=imageView[1].getDrawable();
            correctImage[2]=imageView[4].getDrawable();
            correctImage[3]=imageView[2].getDrawable();
            correctImage[4]=imageView[5].getDrawable();
            correctImage[5]=imageView[3].getDrawable();
            correctImage[6]=imageView[7].getDrawable();
            correctImage[7]=imageView[6].getDrawable();
            correctImage[8]=null;
        }
        else if(r==1)
        {
            correctImage[0]=imageView[7].getDrawable();
            correctImage[1]=imageView[1].getDrawable();
            correctImage[2]=imageView[2].getDrawable();
            correctImage[3]=imageView[4].getDrawable();
            correctImage[4]=imageView[6].getDrawable();
            correctImage[5]=imageView[5].getDrawable();
            correctImage[6]=imageView[0].getDrawable();
            correctImage[7]=imageView[3].getDrawable();
            correctImage[8]=null;
        }
        else
        {
            correctImage[0]=imageView[0].getDrawable();
            correctImage[1]=imageView[3].getDrawable();
            correctImage[2]=imageView[5].getDrawable();
            correctImage[3]=imageView[2].getDrawable();
            correctImage[4]=imageView[1].getDrawable();
            correctImage[5]=imageView[4].getDrawable();
            correctImage[6]=null;
            correctImage[7]=imageView[7].getDrawable();
            correctImage[8]=imageView[8].getDrawable();
        }
    }
    public boolean matchPuzzle(){
        int count=0;
        for(int i=0;i<9;i++)
        {
            if(correctImage[i]==null || imageView[i].getDrawable()==correctImage[i]){
                //System.out.print("myTest: "+"matched "+i+" ");
                count++;
            }
        }
        //System.out.println();
        if(count<9)
            return false;
        return true;
    }
}
