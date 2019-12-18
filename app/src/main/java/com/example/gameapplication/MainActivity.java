package com.example.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: Yellow, 1: Red.
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;

//----------dropIn Method is Started------------------
    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
//        Toast.makeText(this, "count:"+tappedCounter, Toast.LENGTH_SHORT).show();
        if(gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]&&gameState[winningPosition[1]]
                        == gameState[winningPosition[2]]&&gameState[winningPosition[0]] != 2){
                    //someone has won!
                    gameActive = false;
                    String winner ="";
                    if(activePlayer == 1){
                        winner = "yellow";
                    }else{
                        winner = "red";
                    }

//                    Toast.makeText(this, winner+" has Win", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won.");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);


                }
            }
        }
    }
//----------dropIn Method is End------------------

//----------------Play Again Button Function Start--------------------
    public void playAgain(View view) {

        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i = 0; i<gameState.length; i++){

            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;

    }
//----------------Play Again Button Function End--------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
