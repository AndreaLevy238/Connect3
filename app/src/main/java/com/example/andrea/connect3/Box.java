package com.example.andrea.connect3;


public class Box {
   private boolean isOccupied;
   private int color;

   public Box() {
      isOccupied = false;
   }

   public void play(int playerColor) {
      if (!isOccupied) {
         isOccupied = true;
         color = playerColor;
      }
   }
   public boolean isOccupied()
   {
      return isOccupied;
   }

   public int getColor() {
      return color;
   }

}
