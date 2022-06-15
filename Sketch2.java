import processing.core.PApplet;
import processing.core.PImage;

/**
* Descriptions
* @author: Marcus L.
*
*/

public class Sketch2 extends PApplet {
  
  int playerSize = 20;
  float player1X = playerSize * 8 + playerSize / 2;
  float player1Y = playerSize * 23 + playerSize / 2;
  float ghostX = playerSize * 11 + playerSize / 2;
  float ghostY = playerSize * 23 + playerSize / 2;

  int gridX = 20;
  int gridY = 30;
  int[][] intlevel;
  
  int player1Direction = 0;
  int player1NextDirection = 0;
  char ghostDirection = ' ';
  char ghostNextDirection = ' ';

  int timer = 0;
  boolean ghostWhite;
  int whiteTimer = 400;

  PImage imgCherry;
  int lifeCount = 3;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(400, 700);
    intlevel = arrayValues();
    ghostWhite = false;
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imgCherry = loadImage("kisspng-pac-man-cherry-post-it-note-t-shirt-sticker-pacman-cherry-png-5ab14380c31e67.9789147315215665927992.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    timer ++;
    //System.out.println(timer);
    background(0);
    drawBoard();
    player1();
    player2();
    //ghost();

    fill(250);
    text("Score: ", 10, 15);
    
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
      ghostNextDirection = key;
      // Check if the next direction is the opposite of the current
      // direction. If so, we can change the current direction immediately.
      if (
        (ghostDirection == 'w' && ghostNextDirection == 's') ||
        (ghostDirection == 's' && ghostNextDirection == 'w') ||
        (ghostDirection == 'a' && ghostNextDirection == 'd') ||
        (ghostDirection == 'd' && ghostNextDirection == 'a')
      ) {
        ghostDirection = ghostNextDirection;
      }
    }
  }
  
  public void drawBoard() {
    //spawns bonus fruit after specific amount of time
    if(timer == 300){
      intlevel[23][9] = 0;
      intlevel[23][10] = 0;
    }else if(timer == 800){
      intlevel[23][9] = 4;
    }else if(timer == 1400){
      intlevel[23][9] = 0;
    }

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
          //bonus cherry
          image(imgCherry, x * playerSize + playerSize / 2, y * playerSize + playerSize / 2, 10, 10);
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
      {1,2,2,3,2,2,1,1,2,1,1,2,1,1,2,2,3,2,2,1},
      {1,2,1,1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,2,1},
      {1,2,1,2,2,2,1,1,1,1,1,1,1,1,2,2,2,1,2,1},
      {1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
      {1,2,1,2,1,2,2,2,2,2,3,2,2,2,2,1,2,1,2,1},
      {1,2,2,2,1,2,1,1,1,1,1,1,1,1,2,1,2,2,2,1},
      {1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1},
      {1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1},
      {1,1,1,2,1,2,2,2,2,1,1,2,2,2,2,1,2,1,1,1},
      {1,1,1,2,1,2,1,1,0,1,1,0,1,1,2,1,2,1,1,1},
      {1,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,1},
      {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,2,2,2,2,1,1,2,1,1,1,2,1},
      {1,2,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,2,1},
      {1,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
  }
  public void player1(){
    int player1GridX = (int)player1X / playerSize;
    int player1GridY = (int)player1Y / playerSize;
    if(intlevel[player1GridY][player1GridX] == 2 || intlevel[player1GridY][player1GridX] == 3 || intlevel[player1GridY][player1GridX] == 4){
      if(intlevel[player1GridY][player1GridX] == 3){
        ghostWhite = true;
      }
      
      //TODO add to player score
      intlevel[player1GridY][player1GridX] = 0;
    }
    
    // Only check if the direction can be changed if the player is close enough to the center of a cell.
    int player1XRem = (int)player1X % playerSize;
    int player1YRem = (int)player1Y % playerSize;
    int nextX;
    int nextY;
    int code;
    if (player1XRem == 10 && player1YRem == 10) {
      // TODO Can also check here if a pellet is available.
      // TODO Portal handling

      if (player1NextDirection != 0) {
        // Draw a box around the current player 1 cell.
        stroke(255);
        rect(player1GridX * playerSize, player1GridY * playerSize, playerSize, playerSize);
        stroke(0);

        nextX = player1GridX;
        nextY = player1GridY;
        if (player1NextDirection == UP) {
          nextY-=0.9;
        } else if (player1NextDirection == DOWN) {
          nextY+=0.9;
        } else if (player1NextDirection == LEFT) {
          nextX-=0.9;
        } else if (player1NextDirection == RIGHT) {
          nextX+=0.9;
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

      //wall collision
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

  public void ghost(){
    int ghostGridX = (int)ghostX / playerSize;
    int ghostGridY = (int)ghostY / playerSize;

    // Only check if the direction can be changed if the player is close enough to the center of a cell.
    int ghostXRem = (int)ghostX % playerSize;
    int ghostYRem = (int)ghostY % playerSize;
    int nextX;
    int nextY;
    int code;
    if (ghostXRem == 10 && ghostYRem == 10) {
      // TODO Can also check here if a pellet is available.
      // TODO Portal handling

      if (ghostNextDirection != ' ') {
        // Draw a box around the current player 2 cell.
        stroke(255);
        fill(0);
        rect(ghostGridX * playerSize, ghostGridY * playerSize, playerSize, playerSize);
        stroke(0);

        nextX = ghostGridX;
        nextY = ghostGridY;
        if (ghostNextDirection == 'w') {
          nextY--;
        } else if (ghostNextDirection == 's') {
          nextY++;
        } else if (ghostNextDirection == 'a') {
          nextX--;
        } else if (ghostNextDirection == 'd') {
          nextX++;
        }

        code = intlevel[nextY][nextX];
        if (code != 1) {
          // Not a wall.
          ghostDirection = ghostNextDirection;
        }
      }

      // Check if there is a wall in the player's way.
      nextX = ghostGridX;
      nextY = ghostGridY;
      if (ghostDirection == 'w') {
        nextY--;
      } else if (ghostDirection == 's') {
        nextY++;
      } else if (ghostDirection == 'a') {
        nextX--;
      } else if (ghostDirection == 'd') {
        nextX++;
      }

      code = intlevel[nextY][nextX];
      if (code == 1) {
        // A wall.
        ghostDirection = ' ';
      }
    }
        
    if (ghostDirection != ' ') {
      // Update the player position according to the direction.
      if(ghostDirection == 'w') {
        ghostY-= 1;
      } else if (ghostDirection == 's') {
        ghostY+= 1;
      } else if (ghostDirection == 'a') {
        ghostX-= 1;
      } else if (ghostDirection == 'd') {
        ghostX+= 1;
      }
    }

    //tracks how long a ghost is edible for
    if(ghostWhite){
      whiteTimer--;
      if(whiteTimer == 0){
        ghostWhite = false;
        whiteTimer = 400;
      }
    }
    
    if(ghostWhite == true){
      fill(255);
      ellipse(ghostX, ghostY, 20, 20);
    }else{
      fill(255, 0, 0);
      ellipse(ghostX, ghostY, 20, 20);
    }

    if(ghostX <= -10){
      ghostX = width + 10;
    }else if(ghostX >= width + 10){
      ghostX = -10;
    }
  }
  public void playerCollision(){
    int playersDistanceX = (int)player1X - (int)ghostX;
    int playersDistanceY = (int)player1Y - (int)ghostY;
    if(playersDistanceX >= -15 && playersDistanceX <= 15 && playersDistanceY >= -15 && playersDistanceY <= 15 && ghostWhite == true){
      ghostX = playerSize * 11 + playerSize / 2;
      ghostY = playerSize * 23 + playerSize / 2;
      ghostWhite = false;
    }else if(playersDistanceX >= -15 && playersDistanceX <= 15 && playersDistanceY >= -15 && playersDistanceY <= 15 && ghostWhite == false){
      player1X = playerSize * 8 + playerSize / 2;
      player1Y = playerSize * 23 + playerSize / 2;
      ghostX = playerSize * 11 + playerSize / 2;
      ghostY = playerSize * 23 + playerSize / 2;
      timer = 0;
      intlevel[23][9] = 1;
      intlevel[23][10] = 1;
      lifeCount--;
      ghostWhite = false;
    }
  }
  public void lifeDisplay(){
     String lives = ("Lives");
    fill(255);
    textSize(20);
    text(lives, 35, 625);
    if(lifeCount == 3){
      fill(255, 255, 0);
      ellipse(45, 640, 20, 20);
      ellipse(65, 640, 20, 20);
      ellipse(85, 640, 20, 20);
    }else if(lifeCount == 2){
      fill(255, 255, 0);
      ellipse(45, 640, 20, 20);
      ellipse(65, 640, 20, 20);
    }else if(lifeCount == 1){
        fill(255, 255, 0);
       ellipse(45, 640, 20, 20);
    }else if(lifeCount == 0){
        fill(0);
        ellipse(45, 640, 20, 20);
    }
  }
}

