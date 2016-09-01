package com.example.andrea.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   private static final int RED = 1;
   private static final int YELLOW = 2;

   private Board board = new Board();
   private boolean playerRed;
   private boolean win;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerRed = true;
        win = false;

    }


   public void playAgain(View view)
   {
      board = new Board();
      playerRed = true;
      removeImages(findViewById(R.id.board));
      win = false;

   }
   private void removeImages(View view)
   {
      for (int i = 0; i < 9; i++)
      {
         ImageView imageView = (ImageView) view.findViewWithTag(String.valueOf(i));
         imageView.setImageResource(0);
      }
      LinearLayout winnerPage = (LinearLayout) findViewById(R.id.winner);
      winnerPage.setVisibility(View.INVISIBLE);
   }


   private void changeTurn()
   {
      playerRed = !playerRed;
   }
   private int getPlayerColor()
   {
      if (playerRed)
      {
         return RED;
      }
      else
      {
         return YELLOW;
      }
   }

   public void dropIn(View view)
   {
      ImageView piece = (ImageView) view;
      int[] coord = getCoordinates(Integer.parseInt(piece.getTag().toString()));
      Box box = board.getBoxAtCoords(coord[0], coord[1]);
      if (!(box.isOccupied() || win))
      {
         int playerColor = getPlayerColor();
         box.play(playerColor);
         setPiece(piece);
         win = board.checkWin(playerColor);
         if (win)
         {
            declareWinner();
         }
         if (board.checkAllOccupied())
         {
            TextView congrats = (TextView) findViewById(R.id.congrats);
            congrats.setText(R.string.drawGame);
            LinearLayout winnerPage = (LinearLayout) findViewById(R.id.winner);
            winnerPage.setVisibility(View.VISIBLE);
         }
         changeTurn();
      }
   }

   private void declareWinner()
   {
      TextView congrats = (TextView) findViewById(R.id.congrats);
      if (playerRed) {
         congrats.setText(R.string.redWin);
      }
      else {
         congrats.setText(R.string.yellowWin);
      }
      LinearLayout winnerPage = (LinearLayout) findViewById(R.id.winner);
      winnerPage.setVisibility(View.VISIBLE);
   }

   private int[] getCoordinates(int tag)
   {
      int [] result = new int[2];
      switch (tag)
      {
         case 0:
            result[0] = 0;
            result[1] = 0;
            break;
         case 1:
            result[0] = 0;
            result[1] = 1;
            break;
         case 2:
            result[0] = 0;
            result[1] = 2;
            break;
         case 3:
            result[0] = 1;
            result[1] = 0;
            break;
         case 4:
            result[0] = 1;
            result[1] = 1;
            break;
         case 5:
            result[0] = 1;
            result[1] = 2;
            break;
         case 6:
            result[0] = 2;
            result[1] = 0;
            break;
         case 7:
            result[0] = 2;
            result[1] = 1;
            break;
         case 8:
            result[0] = 2;
            result[1] = 2;
            break;
      }
      return result;
   }


   private void setPiece(ImageView piece)
   {
      piece.setTranslationY(-1000f);
      if (playerRed)
      {
         piece.setImageResource(R.drawable.red);
      }
      else
      {
         piece.setImageResource(R.drawable.yellow);
      }
      piece.animate().translationYBy(1000f).rotation(360f).setDuration(300);
   }







}
