package com.example.connect_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0: Blue 1: Orange 2: Empty

    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winnipostions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer=0;

    boolean gameActive = true;

    public void dropin (View view)
    {
        ImageView counter =(ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter] == 2 && gameActive)
        {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.blue4);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.orange4);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] winningpostion : winnipostions) {
                if (gamestate[winningpostion[0]] == gamestate[winningpostion[1]] && gamestate[winningpostion[1]] == gamestate[winningpostion[2]] && gamestate[winningpostion[0]] != 2) {
                    gameActive = false;
                    String winner;
                    if (activeplayer == 1) {
                        winner = "Blue";
                    } else {
                        winner = "Orange";
                    }
                    Button button =(Button) findViewById(R.id.button);
                    TextView textView =(TextView) findViewById(R.id.textView);
                    textView.setText(winner+" has won!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playagain(View view)
    {
        Button button =(Button) findViewById(R.id.button);
        TextView textView =(TextView) findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount() ;i++)
        {
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<gamestate.length; i++)
        {
            gamestate[i]= 2;
        }

        activeplayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}