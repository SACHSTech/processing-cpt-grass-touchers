import processing.core.PApplet;

public class Sketch extends PApplet {
  boolean P1upPressed = false;
  boolean P1downPressed = false;
  boolean P1leftPressed = false;
  boolean P1rightPressed = false;

  boolean P2upPressed = false;
  boolean P2downPressed = false;
  boolean P2leftPressed = false;
  boolean P2rightPressed = false;
  
  float player1X = 190;
  float player1Y = 500;
  float player2X = 210;
  float player2Y = 500;
  float playerSize = 20;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
 public void settings() {
	// put your size call here
    size(400, 600);
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
  
    if(P1upPressed){
      player1Y--;
    }
    if(P1downPressed){
      player1Y ++;
    }
    if(P1leftPressed){
      player1X --;
    }
    if(P1rightPressed){
      player1X ++;
    }
    
    fill(255, 255, 0);
    ellipse(player1X, player1Y, playerSize, playerSize);

    if(player1X <= -10){
      player1X = width + 10;
    }else if(player1X >= width + 10){
      player1X = -10;
    }

    if(player2X <= -10){
      player2X = width + 10;
    }else if(player2X >= width + 10){
      player2X = -10;
    }

    if(P2upPressed){
      player2Y--;
    }
    if(P2downPressed){
      player2Y ++;
    }
    if(P2leftPressed){
      player2X --;
    }
    if(P2rightPressed){
      player2X ++;
    }
          
    fill(0, 255, 0);
    ellipse(player2X, player2Y, playerSize, playerSize);
    
  }
  
  public void keyPressed(){

    if(keyCode == UP){
       P1upPressed = true;
    }
    if(keyCode == DOWN){
      P1downPressed = true;
    }
    if(keyCode == LEFT){
      P1leftPressed = true;
    }
    if(keyCode == RIGHT){
      P1rightPressed = true;
    }
    
    if(key == 'w'){
      P2upPressed = true;
    }
    if(key == 's'){
      P2downPressed = true;
    }
    if(key == 'a'){
      P2leftPressed = true;
    }
    if(key == 'd'){
      P2rightPressed = true;
    } 
  }
    public void keyReleased(){

    if(keyCode == UP){
       P1upPressed = false;
    }
    if(keyCode == DOWN){
      P1downPressed = false;
    }
    if(keyCode == LEFT){
      P1leftPressed = false;
    }
    if(keyCode == RIGHT){
      P1rightPressed = false;
    }
    
    if(key == 'w'){
      P2upPressed = false;
    }
    if(key == 's'){
      P2downPressed = false;
    }
    if(key == 'a'){
      P2leftPressed = false;
    }
    if(key == 'd'){
      P2rightPressed = false;
    }
  }   
}