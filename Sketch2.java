import processing.core.PApplet;

public class Sketch2 extends PApplet {

	
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
    background(0);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    player1();
  }
  
  public void player1(){
    float player1X = 200;
    float player1Y = 500;
    float playerSize = 35;
    if(keyPressed){
      if(keyCode == UP){
        player1Y--;
      }else if(keyCode == DOWN){
        player1Y ++;
      }else if(keyCode == LEFT){
        player1X --;
      }else if(keyCode == RIGHT){
        player1X ++;
      }
    }
    fill(255, 255, 0);
    ellipse(player1X, player1Y, playerSize, playerSize);
  }
}
