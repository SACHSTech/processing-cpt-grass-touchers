import processing.core.PApplet;
import processing.core.PImage; //import the PImage library

/**
 * 
 * 
 * 
 * 
 * 
 * @author: Matthew S.
 * */
public class Sketch1 extends PApplet {

  PImage imgPacman; // declare a global image variable
  //

  float fltPacmanX = 200;
  float fltPacmanY = 200;
  int score = 0;

  PImage pacmanspritesheet;
  PImage pacman_walkingsheet;

  PImage[] pacman_frames;
  int intPacman_frames = 3;
  int intPacman_frameWidth = 15;

  int intPacmanX = 20;
  int intPacmanY = 20;


  PImage[] ghostspritesheet;
  PImage[] ghost_runningsheet;
  PImage[] ghost_frames;
  int intGhost_frames = 8;
  int intGhost_frameWidth = 18;
  int intGhost_frameHeight = 19;
  int intGhostX = 20;
  int intGhostY = 80;

  int pelletX = 0;
  int pelletY = 0;
  float player1X = 190;
  float player1Y = 500;
  float player2X = 210;
  float player2Y = 500;
  float playerSize = 20;

  public void settings() {
    size(400, 400);
  
  }

  public void setup() {
    

    // load the spritesheet image
    pacmanspritesheet = loadImage("pacman sprite sheet.png");


    // load pacman spritesheet
    pacmanspritesheet = loadImage("pacman sprite sheet.png");
    pacman_walkingsheet = pacmanspritesheet.get(0,16,intPacman_frameWidth*intPacman_frames,15);

  
    // sonic
    //ghostspritesheet = loadImage("pacman sprite sheet.png");
    //ghost_frames = ghostspritesheet.get(17,16, intGhost_frameWidth*intGhost_frames, 19);



    //load the frames from the walking sheet
    pacman_frames = new PImage[intPacman_frames];
    for(int frameNum = 0; frameNum < intPacman_frames; frameNum++ ){
      System.out.println("load frames");
      pacman_frames[frameNum] = pacman_walkingsheet.get(intPacman_frameWidth*frameNum, 0, intPacman_frameWidth, pacman_walkingsheet.height );
    }

    // load the sonic frames
    ghost_frames = new PImage[intGhost_frames];
    for(int frameNum = 0; frameNum < intGhost_frames; frameNum++ ){
      System.out.println("load frames");
      //ghost_frames[frameNum] = ghost_runningsheet.get(intGhost_frameWidth*frameNum, 0, intGhost_frameWidth, intGhost_frameHeight );
    }

  }


  public void draw() {
    background(0);

    // draw mario
    image(pacman_frames[(frameCount/3)%intPacman_frames], intPacmanX, intPacmanY);
    intPacmanX++;
    if(intPacmanX > width){
      intPacmanX = 0 - intPacman_frameWidth;
    }

    // draw sonic
    image(ghost_frames[(frameCount/3)%intGhost_frames], intGhostX, intGhostY);
    intGhostX += 2;

    if(intGhostX > width){
      intGhostX = 0 - intGhost_frameWidth;
    }
    

    // score system
    
    fill(250);
    text("Score: ", 10, 15);

    if (player1X > pelletX - 15 && player1X < pelletX + 15 && player1Y > pelletY - 15 && player1Y < pelletY + 15){
    score += 100;
    }
  }
}
