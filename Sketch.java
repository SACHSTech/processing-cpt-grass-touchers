import processing.core.PApplet;

public class Sketch extends PApplet {
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
    player1();
  }
  
  public void player1(){
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

  // public void player2(){
  //   if(keyPressed){
  //     if(key == 'w'){
  //       player2Y--;
  //     }else if(key == 's'){
  //       player2Y ++;
  //     }else if(key == 'a'){
  //       player2X --;
  //     }else if(key == 'd'){
  //       player2X ++;
  //     }
  //   }
  //   fill(0, 255, 0);
  //   ellipse(player2X, player2Y, playerSize, playerSize);
  // }
}