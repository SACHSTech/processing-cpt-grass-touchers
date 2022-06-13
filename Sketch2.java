import processing.core.PApplet;

/**
* Descriptions
* @author: Marcus L.
*
*/

public class Sketch2 extends PApplet {
  
  int playerSize = 20;
  int player1X = playerSize * 9 + playerSize / 2;
  int player1Y = playerSize * 23 + playerSize / 2;
  int player2X = playerSize * 10 + playerSize / 2;
  int player2Y = playerSize * 23 + playerSize / 2;

  int gridX = 20;
  int gridY = 30;
  int[][] intlevel;
  
  int player1Direction = 0;
  int player1NextDirection = 0;
  int player2Direction = 0;
  int player2NextDirection = 0;

  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(400, 600);
    intlevel = arrayValues();
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);
    drawBoard();
    player1();
    player2();
    
  }
  
  public void keyPressed(){
    if (keyCode == UP || keyCode == DOWN || keyCode == LEFT || keyCode == RIGHT) {
      player1NextDirection = keyCode;
      // Check if the next direction is the opposite of the current
      // direction. If so, we can change the current direction immediately.
      if (
        (player1Direction == UP && player1NextDirection == DOWN) ||
        (player1Direction == DOWN && player1NextDirection == UP) ||
        (player1Direction == LEFT && player1NextDirection == RIGHT) ||
        (player1Direction == RIGHT && player1NextDirection == LEFT)
      ) {
        player1Direction = player1NextDirection;
      }
    }
    if(key == 'w' || key == 'a' || key == 's' || key == 'd') {
      player2NextDirection = key;
      // Check if the next direction is the opposite of the current
      // direction. If so, we can change the current direction immediately.
      if (
        (player2Direction == 'w' && player2NextDirection == 's') ||
        (player2Direction == 's' && player2NextDirection == 'w') ||
        (player2Direction == 'a' && player2NextDirection == 'd') ||
        (player2Direction == 'd' && player2NextDirection == 'a')
      ) {
        player2Direction = player2NextDirection;
      }
    }
  }
    
  
   


  public void drawBoard() {
    for (int y = 0; y < intlevel.length; y++) {
      for (int x = 0; x < intlevel[0].length; x++) {
        int code = intlevel[y][x];

        if (code == 0) {
          //empty
        } else if (code == 1) {
          //wall
          fill(0, 0, 200);
          rect(x * playerSize, y * playerSize, 20, 20);
        } else if (code == 2) {
          //pellet
          fill(255, 255, 255);
          ellipse(x * playerSize + playerSize / 2, y * playerSize + playerSize / 2, 5, 5);
        } else if (code == 3) {
          //power pellet
          fill(255, 255, 255);
          ellipse(x * playerSize + playerSize / 2, y * playerSize + playerSize / 2, 10, 10);
        } else if (code == 4) {
          //portal 1
        } else if (code == 5) {
          //portal 2
        }
      }
    }
  }
  
  public int[][] arrayValues() {
    return new int[][] {
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,2,2,2,3,2,2,2,2,2,1,1,1,2,1},
      {1,2,2,2,2,2,1,1,2,1,1,2,1,1,2,2,2,2,2,1},
      {1,2,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,2,2,2,1,1,2,2,2,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,1},
      {1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1},
      {1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1},
      {4,2,2,3,2,2,1,1,2,1,1,2,1,1,2,2,3,2,2,5},
      {1,2,1,1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,2,1},
      {1,2,1,2,2,2,1,1,1,1,1,1,1,1,2,2,2,1,2,1},
      {1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
      {1,2,1,2,1,2,2,2,2,2,3,2,2,2,2,1,2,1,2,1},
      {1,2,2,2,1,2,1,1,1,1,1,1,1,1,2,1,2,2,2,1},
      {1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1},
      {1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1},
      {1,1,1,2,1,2,2,2,2,1,1,2,2,2,2,1,2,1,1,1},
      {1,1,1,2,1,2,1,1,2,0,0,2,1,1,2,1,2,1,1,1},
      {1,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,2,2,2,2,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,2,1},
      {1,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
  }
  public void player1(){
    int player1GridX = player1X / playerSize;
    int player1GridY = player1Y / playerSize;
    if(intlevel[player1GridY][player1GridX] == 2 || intlevel[player1GridY][player1GridX] == 3){
      //TODO add to player score
      intlevel[player1GridY][player1GridX] = 0;
    }

    // Only check if the direction can be changed if the player is close enough to the center of a cell.
    int player1XRem = player1X % playerSize;
    int player1YRem = player1Y % playerSize;
    int nextX;
    int nextY;
    int code;
    if (player1XRem == 10 && player1YRem == 10) {
      // TODO Can also check here if a pellet is available.
      // TODO Portal handling

      if (player1NextDirection != 0) {
        // Draw a box around the current player 1 cell.
        // TODO remove this. For debugging only.
        stroke(255);
        rect(player1GridX * playerSize, player1GridY * playerSize, playerSize, playerSize);
        stroke(0);

        nextX = player1GridX;
        nextY = player1GridY;
        if (player1NextDirection == UP) {
          nextY--;
        } else if (player1NextDirection == DOWN) {
          nextY++;
        } else if (player1NextDirection == LEFT) {
          nextX--;
        } else if (player1NextDirection == RIGHT) {
          nextX++;
        }

        code = intlevel[nextY][nextX];
        if (code != 1) {
          // Not a wall.
          player1Direction = player1NextDirection;
        }
      }

      // Check if there is a wall in the player's way.
      nextX = player1GridX;
      nextY = player1GridY;
      if (player1Direction == UP) {
        nextY--;
      } else if (player1Direction == DOWN) {
        nextY++;
      } else if (player1Direction == LEFT) {
        nextX--;
      } else if (player1Direction == RIGHT) {
        nextX++;
      }

      code = intlevel[nextY][nextX];
      if (code == 1) {
        // A wall.
        player1Direction = 0;
      }
    }
        
    if (player1Direction != 0) {
      // Update the player position according to the direction.
      if (player1Direction == UP) {
        player1Y--;
      } else if (player1Direction == DOWN) {
        player1Y++;
      } else if (player1Direction == LEFT) {
        player1X--;
      } else if (player1Direction == RIGHT) {
        player1X++;
      }
    }
    
    
    fill(255,255, 0);
    ellipse(player1X, player1Y, 20, 20);

    if(player1X <= -10){
      player1X = width + 10;
    }else if(player1X >= width + 10){
      player1X = -10;
    }
  }

  public void player2(){
    int player2GridX = player2X / playerSize;
    int player2GridY = player2Y / playerSize;
    if(intlevel[player2GridY][player2GridX] == 2 || intlevel[player2GridY][player2GridX] == 3){
      //TODO add to player score
      intlevel[player2GridY][player2GridX] = 0;
    }

    // Only check if the direction can be changed if the player is close enough to the center of a cell.
    int player2XRem = player2X % playerSize;
    int player2YRem = player2Y % playerSize;
    int nextX;
    int nextY;
    int code;
    if (player2XRem == 10 && player2YRem == 10) {
      // TODO Can also check here if a pellet is available.
      // TODO Portal handling

      if (player2NextDirection != 0) {
        // Draw a box around the current player 2 cell.
        // TODO remove this. For debugging only.
        stroke(255);
        rect(player2GridX * playerSize, player2GridY * playerSize, playerSize, playerSize);
        stroke(0);

        nextX = player2GridX;
        nextY = player2GridY;
        if (player2NextDirection == UP) {
          nextY--;
        } else if (player2NextDirection == DOWN) {
          nextY++;
        } else if (player2NextDirection == LEFT) {
          nextX--;
        } else if (player2NextDirection == RIGHT) {
          nextX++;
        }

        code = intlevel[nextY][nextX];
        if (code != 1) {
          // Not a wall.
          player2Direction = player2NextDirection;
        }
      }

      // Check if there is a wall in the player's way.
      nextX = player2GridX;
      nextY = player2GridY;
      if (player2Direction == UP) {
        nextY--;
      } else if (player2Direction == DOWN) {
        nextY++;
      } else if (player2Direction == LEFT) {
        nextX--;
      } else if (player2Direction == RIGHT) {
        nextX++;
      }

      code = intlevel[nextY][nextX];
      if (code == 1) {
        // A wall.
        player2Direction = 0;
      }
    }
        
    if (player2Direction != 0) {
      // Update the player position according to the direction.
      if (player2Direction == UP) {
        player2Y--;
      } else if (player2Direction == DOWN) {
        player2Y++;
      } else if (player2Direction == LEFT) {
        player2X--;
      } else if (player2Direction == RIGHT) {
        player2X++;
      }
    }
    
    
    fill(0, 255, 0);
    ellipse(player2X, player2Y, 20, 20);

    if(player2X <= -10){
      player2X = width + 10;
    }else if(player2X >= width + 10){
      player2X = -10;
    }
  }
}
