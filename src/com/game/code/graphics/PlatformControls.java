package com.game.code.graphics;
import java.util.Timer;
import java.util.TimerTask;

public class PlatformControls {
	Timer platformTimer;
	
	
	public PlatformControls(){
	platformTimer = new Timer();
	platformTimer.schedule(new RemindTask(), 0, //initial delay (this could be set higher to accommodate level start)
	        1 * 1000); //subsequent rate (1000 =  1 sec)
	  }
	class RemindTask extends TimerTask {

		public void run() {
			new FallingPlatform();
			//platformTimer.cancel(); (can be used to end timer if necessary)
        
      }
    }
  }


	
	
	


