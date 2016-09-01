package com.example.andrea.connect3;

public class Board {
   // note : null = 0, red = 1, yellow = 2
   private Box[][] board;

   public Board()
   {
      board = createBoard();
   }
   public Box getBoxAtCoords(int row, int col)
   {
      return board[row][col];
   }

   private Box[][] createBoard() {
      Box[][] board = new Box[3][3];
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            board[i][j] = new Box();
         }
      }
      return board;
   }

   private boolean checkRowWin(int playerColor, int rowNum) {
      for (int i = 0; i < 3; i++) {
         int boxColor = board[rowNum][i].getColor();
         if (boxColor != playerColor) {
            return false;
         }
      }
      return true;
   }

   private boolean checkColWin(int playerColor, int colNum) {
      for (int i = 0; i < 3; i++) {
         int boxColor = board[i][colNum].getColor();
         if (boxColor != playerColor) {
            return false;
         }
      }
      return true;
   }

   private boolean checkHorizontalWin(int playerColor) {
      for (int i = 0; i < 3; i++) {
         if (checkRowWin(playerColor, i)) {
            return true;
         }
      }
      return false;
   }

   private boolean checkVerticalWin(int playerColor) {
      for (int i = 0; i < 3; i++) {
         if (checkColWin(playerColor, i)) {
            return true;
         }
      }
      return false;
   }

   private boolean checkDiagonalWin(int playerColor) {
      int center = board[1][1].getColor();
      if (center != playerColor) {
         return false;
      }
      int topLeft = board[0][0].getColor();
      int bottomRight = board[2][2].getColor();
      if (topLeft == playerColor && bottomRight == playerColor) {
         return true;
      }
      int topRight = board[0][2].getColor();
      int bottomLeft = board[2][0].getColor();
      return topRight == playerColor && bottomLeft == playerColor;
   }

   public boolean checkWin(int playerColor) {
      return checkDiagonalWin(playerColor) || checkHorizontalWin(playerColor) || checkVerticalWin(playerColor);
   }

   public boolean checkAllOccupied()
   {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (!board[i][j].isOccupied())
            {
               return false;
            }
         }
      }
      return true;
   }
}
