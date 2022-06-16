import processing.core.PApplet;
import processing.core.PImage;

/**
* Descriptions
* @author: Marcus L.
*
*/

public class Sketch2 extends PApplet {
  
  int playerSize = 20;
  float PacManX = playerSize * 8 + playerSize / 2;
  float PacManY = playerSize * 23 + playerSize / 2;
  float ghostX = playerSize * 11 + playerSize / 2;
  float ghostY = playerSize * 23 + playerSize / 2;

  int gridX = 20;
  int gridY = 30;
  int[][] intlevel;
  
  int PacManDirection = 0;
  int PacManNextDirection = 0;
  char ghostDirection = ' ';
  char ghostNextDirection = ' ';

  int timer = 0;
  boolean ghostWhite;
  int whiteTimer = 500;

  PImage imgCherry;
  int lifeCount = 3;
  int pelletsEaten = 0;
  
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
    PacMan();
    ghost();
    playerCollision();
    lifeDisplay();
    winScreens();

    fill(250);
    text("Score: ", 10, 15);
  }
  
  public void keyPressed(){
    if (keyCode == UP || keyCode == DOWN || keyCode == LEFT || keyCode == RIGHT) {
      PacManNextDirection = keyCode;
      // Check if the next direction is the opposite of the current
      // direction. If so, we can change the current direction immediately.
      if (
        (PacManDirection == UP && PacManNextDirection == DOWN) ||
        (PacManDirection == DOWN && PacManNextDirection == UP) ||
        (PacManDirection == LEFT && PacManNextDirection == RIGHT) ||
        (PacManDirection == RIGHT && PacManNextDirection == LEFT)
      ) {
        PacManDirection = PacManNextDirection;
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
          //empty space
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
  /**
  *Creates 20 by 30 grid for the map
  *@return The grid and the the values of each x and y value
  */
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
  public void PacMan(){
    int PacManGridX = (int)PacManX / playerSize;
    int PacManGridY = (int)PacManY / playerSize;

    //What happens when Pac-Man eats specific things on the map (i.e pellets disappearing and Pac-Man score increasing)
    if(intlevel[PacManGridY][PacManGridX] == 2 || intlevel[PacManGridY][PacManGridX] == 3 || intlevel[PacManGridY][PacManGridX] == 4){
      if(intlevel[PacManGridY][PacManGridX] == 2 || intlevel[PacManGridY][PacManGridX] == 3){
        pelletsEaten++;
        System.out.println(pelletsEaten);
      }
      if(intlevel[PacManGridY][PacManGridX] == 3){
        ghostWhite = true;
      }
      
      intlevel[PacManGridY][PacManGridX] = 0;
    }
    
    // Only check if the direction can be changed if the player is close enough to the center of a cell.
    int PacManXRem = (int)PacManX % playerSize;
    int PacManYRem = (int)PacManY % playerSize;
    int nextX;
    int nextY;
    int code;
    if (PacManXRem == 10 && PacManYRem == 10) {
      // TODO Can also check here if a pellet is available.
      // TODO Portal handling

      if (PacManNextDirection != 0) {
        // Draw a box around the current player 1 cell.
        stroke(255);
        rect(PacManGridX * playerSize, PacManGridY * playerSize, playerSize, playerSize);
        stroke(0);

        nextX = PacManGridX;
        nextY = PacManGridY;
        if (PacManNextDirection == UP) {
          nextY-=0.9;
        } else if (PacManNextDirection == DOWN) {
          nextY+=0.9;
        } else if (PacManNextDirection == LEFT) {
          nextX-=0.9;
        } else if (PacManNextDirection == RIGHT) {
          nextX+=0.9;
        }

        code = intlevel[nextY][nextX];
        if (code != 1) {
          // Not a wall.
          PacManDirection = PacManNextDirection;
        }
      }

      // Check if there is a wall in the player's way.
      nextX = PacManGridX;
      nextY = PacManGridY;
      if (PacManDirection == UP) {
        nextY--;
      } else if (PacManDirection == DOWN) {
        nextY++;
      } else if (PacManDirection == LEFT) {
        nextX--;
      } else if (PacManDirection == RIGHT) {
        nextX++;
      }

      //wall collision
      code = intlevel[nextY][nextX];
      if (code == 1) {
        // A wall.
        PacManDirection = 0;
      }
    }
        
    if (PacManDirection != 0) {
      // Update the player position according to the direction.
      if (PacManDirection == UP) {
        PacManY--;
      } else if (PacManDirection == DOWN) {
        PacManY++;
      } else if (PacManDirection == LEFT) {
        PacManX--;
      } else if (PacManDirection == RIGHT) {
        PacManX++;
      }
    }
    
    
    fill(255,255, 0);
    ellipse(PacManX, PacManY, 20, 20);
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
        whiteTimer = 500;
      }
    }

    //tracks how long a ghost is edible for
    if(ghostWhite == true){
      fill(255);
      ellipse(ghostX, ghostY, 20, 20);
    }else{
      fill(255, 0, 0);
      ellipse(ghostX, ghostY, 20, 20);
    }
  }

  //checks what happens if both players collide
  public void playerCollision(){
    int playersDistanceX = (int)PacManX - (int)ghostX;
    int playersDistanceY = (int)PacManY - (int)ghostY;
    if(playersDistanceX >= -15 && playersDistanceX <= 15 && playersDistanceY >= -15 && playersDistanceY <= 15 && ghostWhite == true){
      ghostX = playerSize * 11 + playerSize / 2;
      ghostY = playerSize * 23 + playerSize / 2;
      ghostWhite = false;
    }else if(playersDistanceX >= -15 && playersDistanceX <= 15 && playersDistanceY >= -15 && playersDistanceY <= 15 && ghostWhite == false){
      PacManX = playerSize * 8 + playerSize / 2;
      PacManY = playerSize * 23 + playerSize / 2;
      ghostX = playerSize * 11 + playerSize / 2;
      ghostY = playerSize * 23 + playerSize / 2;
      timer = 0;
      intlevel[23][9] = 1;
      intlevel[23][10] = 1;
      lifeCount--;
      ghostWhite = false;
    }
  }

  //shows how many lived Pac-Man has on screen
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
  //displays which player has won at the end of the game
  public void winScreens(){
    String playerWin = ("Game Over, Pac-Man Wins");
    String ghostWin = ("Game Over, Ghost Wins");
    if(pelletsEaten == 236){
      fill(255, 255, 0);
      textSize(15);
      text(playerWin, 130, 615);
    }else if(lifeCount <= 0){
      fill(255, 0, 0);
      textSize(15);
      text(ghostWin, 130, 615); 
    }
  }
}

