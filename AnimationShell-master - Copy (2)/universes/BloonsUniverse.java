import java.util.ArrayList;

public class BloonsUniverse implements Universe {

	private boolean complete = false;
	private ArrayList<Background> backgrounds = null;
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private double xCenter = 0;
	private double yCenter = 0;
	
	public BloonsUniverse () {

		background = new MappedBackground();
		ArrayList<DisplayableSprite> barriers = ((MappedBackground)background).getBarriers();
		backgrounds =new ArrayList<Background>();
		backgrounds.add(background);
		
		player1 = new CollidingSprite(MappedBackground.TILE_HEIGHT * 2, MappedBackground.TILE_WIDTH * 2, MappedBackground.TILE_HEIGHT * 0.9, MappedBackground.TILE_HEIGHT * 0.9);
		
		sprites.add(player1);
		sprites.addAll(barriers);
		
		for (int i = 0; i < 10; i++) {
			sprites.add(new RedBalloonSprite(25, 25));
			
		}
		
	}

	public double getScale() {
		return 1;
	}	
	
	public double getXCenter() {
		return this.xCenter;
	}

	public double getYCenter() {
		return this.yCenter;
	}
	
	public void setXCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public void setYCenter(double yCenter) {
		this.yCenter = yCenter;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}	

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
		
	public boolean centerOnPlayer() {
		return true;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
	}

	public String toString() {
		return "MappedUniverse";
	}	

}
