
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class MappedBackground implements Background {

    protected static int TILE_WIDTH = 50;
    protected static int TILE_HEIGHT = 50;

    private Image water;
    private Image grass;
    private Image path;
    private int maxCols = 0;
    private int maxRows = 0;    

	private int map[][] = new int[][] { 
		{1,1,1,1,1,1,1,1,1,1},
		{3,3,3,3,3,3,3,3,3,1},
		{3,1,1,1,3,1,1,1,1,1},
		{3,1,3,1,3,1,3,3,3,3},
		{3,1,3,1,1,1,3,1,1,1},
		{3,1,3,3,3,3,3,1,3,1},
		{3,1,1,1,1,1,1,1,3,1},
		{3,3,3,3,3,3,3,3,3,1},
		{3,2,2,2,3,1,1,1,1,1},
		{3,2,2,3,3,1,3,3,3,3},
		{3,3,3,3,3,1,1,1,1,1},
		
	};
    public MappedBackground() {
    	try {
    		this.water = ImageIO.read(new File("res/backgrounds/map-tiles/water_tile.jpg"));
    		this.path = ImageIO.read(new File("res/backgrounds/map-tiles/sandStone.png"));   
       		this.grass = ImageIO.read(new File("res/backgrounds/map-tiles/grass.png")); 
    	}
    	catch (IOException e) {
    		System.out.println(e.toString());
    	}
    	maxRows = map.length - 1;
    	maxCols = map[0].length - 1;
    }
	
	public Tile getTile(int col, int row) {
		
		Image image = null;
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		}
		else if (map[row][col] == 1) {
			image = path;
		}
		else if (map[row][col] == 2) {
			image = water;
		}
		else if (map[row][col] == 3) {
			image = grass;
		}
		else {
			image = null;
		}
			
		int x = (col * TILE_WIDTH);
		int y = (row * TILE_HEIGHT);
		
		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);
		
		return newTile;
	}
	
	public int getCol(double x) {
		//which col is x sitting at?
		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (int) (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			}
			else {
				return col;
			}
		}
		else {
			return 0;
		}
	}
	
	public int getRow(double y) {
		//which row is y sitting at?
		int row = 0;
		
		if (TILE_HEIGHT != 0) {
			row = (int) (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			}
			else {
				return row;
			}
		}
		else {
			return 0;
		}
	}
	
	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == 15) {
					barriers.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH, (row + 1) * TILE_HEIGHT, false));
				} 
			}
		}
		return barriers;
	}

	@Override
	public double getShiftX() {
		return 0;
	}

	@Override
	public double getShiftY() {
		return 0;
	}

	@Override
	public void setShiftX(double shiftX) {
		//ignore
	}

	@Override
	public void setShiftY(double shiftY) {
		//ignore
	}
	
}