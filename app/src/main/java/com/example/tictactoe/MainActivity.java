package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    GridLayout gridLayout;
    TextView tv;
    Button button;
    boolean gameActive = true;
    //0 for tick, 1 for cross, 2 for empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn1);
        button.setVisibility(View.INVISIBLE);
        tv = (TextView) findViewById(R.id.tv1);
        gridLayout = (GridLayout) findViewById(R.id.gLayout1);
        tv.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(),"Player 1 plays first",Toast.LENGTH_SHORT).show();

    }

    public void dropIn(View view)
    {
            if(gameActive==true) {
                ImageView counter = (ImageView) view;
                if (player == 0 && gameState[Integer.parseInt((String) counter.getTag())] == 2) {
                    gameState[Integer.parseInt((String) counter.getTag())] = 0;
                    counter.setImageResource(R.drawable.ic_baseline_done_24);
                    counter.setY(-1500);
                    counter.animate().translationY(0).rotationBy(3600).setDuration(500);
                    player = 1;

                } else if (player == 1 && gameState[Integer.parseInt((String) counter.getTag())] == 2) {
                    gameState[Integer.parseInt((String) counter.getTag())] = 1;
                    counter.setImageResource(R.drawable.ic_baseline_not_interested_24);
                    counter.setY(-1500);
                    counter.animate().translationY(0).rotationBy(3600).setDuration(500);
                    player = 0;
                }

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                            && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                            && gameState[winningPosition[0]] != 2) {
                        //Toast.makeText(getApplicationContext(), "someone has won", Toast.LENGTH_SHORT).show();
                        tv.setVisibility(VISIBLE);
                        if(player == 0)
                        {
                            tv.setText("Player 2 has won");
                        }
                        else
                        {
                            tv.setText("player 1 has won");
                        }
                        gameActive = false;
                        button.setVisibility(VISIBLE);
                    }
                }
            }
            else
            {

            }
        }

        public void playAgain(View view)
        {
            for(int i = 0; i<gridLayout.getChildCount();i++)
            {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
                gameActive = true;
                button.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.INVISIBLE);





            }
            for(int i = 0; i<gameState.length;i++)
            {
                gameState[i] = 2;
            }
            player = 0;

        }



    }
