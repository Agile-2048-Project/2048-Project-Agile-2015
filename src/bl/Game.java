package bl;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class Game implements Board{
	private static final int GRID_WIDTH = 4;
	private static final int[] StartValues = {2,4}; 
	private static final int[] PossibleBValues = {2,4,8,16,32,64,128,256,512,1024,2048};	
	private Tile[] myTiles;
	private int myScore;
	boolean win = false;
	
	
	public Game() {
		
		myTiles = new Tile[GRID_WIDTH * GRID_WIDTH];
		myScore = 0;
		
		initTiles();
		addTile();
		addTile();
	}
	
	public Tile[] getBord() {
		return myTiles;
	}
	
	public int getScore() {
		return myScore;
	}
	private void initTiles() {
		for(int i = 0;i<myTiles.length;i++) {
			myTiles[i] = new Tile();
		}
	}
	
	private void addTile() {
		List<Tile> list = availableSpace();
		
		if(!list.isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile tile = list.get(index);
			tile.value = Optional.of(Math.random() < 0.9 ? StartValues[0] : StartValues[1]);
		}
	}
	
	private List<Tile> availableSpace() {
		final List<Tile> list = new ArrayList<Tile>(GRID_WIDTH*GRID_WIDTH);
		
		for(Tile t : myTiles) {
			if(t.isEmpty()){
				list.add(t);
			}
		}
		return list;
	}
	
	@Override
	public Optional<Integer> cell(int x, int y) {
		return myTiles[x+y*GRID_WIDTH].value;
	}
	
	@Override
	public Board move(Direction dir) {
		switch(dir) {
			case Up: Up();
				break;
			case Down: Down();
				break;
			case Left: Left();
				break;
			case Right:	Right();
				break;
		}
		return this;
	}
	
	private void Up() {
		flipDiagonally();
		Left();
		flipDiagonally();
	}
	
	private void Down() {
		flipHorizontally();
		flipDiagonally();
		Left();
		flipDiagonally();
		flipHorizontally();
	}
	
	private void Left() {
		
		for(int i=0;i<GRID_WIDTH;i++) {
			Tile[] line = getLine(i);
			Tile[] merged = mergeLine(moveLine(line));
			
			if(!compare(line, merged)) 
				setLine(i, merged);
		}
		
		addTile();
	}
	
	private void Right() {
		
		flipDiagonally();
		flipHorizontally();
		flipDiagonally();
		Left();
		flipDiagonally();
		flipHorizontally();
		flipDiagonally();
	}
	
	private boolean compare(Tile[] lineA, Tile[] lineB) {
		
		if (lineA.length != lineB.length) {
			return false;
		}
		
		 for (int i = 0; i < GRID_WIDTH; i++) {
			 if (lineA[i].value.get() != lineB[i].value.get()) {
				 return false;
			 }
		 }
		
		return true;
	}

	private void setLine(int index, Tile[] merged) {
		System.arraycopy(merged, 0, myTiles, index * GRID_WIDTH, GRID_WIDTH);
	}

	private Tile[] mergeLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		
		for(int i=0;i<GRID_WIDTH;i++) {
			int num1 = oldLine[i].value.get();
			
			
			if(i < GRID_WIDTH-1) {
				
				int num2 = oldLine[i+1].value.get();
				
				if(num1==num2) {
					num1*=2;
					myScore+=num1;
					
					if(num1==PossibleBValues[10]) {
						win = true;
					}
					i++;
				}
			}
			list.add(new Tile(Optional.of(num1)));
		}
		
		ensureSize(list);
		return list.toArray(new Tile[GRID_WIDTH]);
	}

	private Tile[] moveLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		
		for(int i=0;i<GRID_WIDTH;i++) {
			if(!oldLine[i].isEmpty())
				list.addLast(oldLine[i]);
		}
		
		if(list.size() == 0) {
			return oldLine;
		} else {
			Tile[] newLine = new Tile[GRID_WIDTH];
			ensureSize(list);
			for (int i = 0; i < GRID_WIDTH; i++) {
				newLine[i] = list.removeFirst();
			}
			
			return newLine;
		}
	}

	private void ensureSize(LinkedList<Tile> list) {
		while (list.size() != GRID_WIDTH) {
			list.add(new Tile());
		}
	}

	private Tile[] getLine(int index) {
		Tile[] line = new Tile[GRID_WIDTH];
		for(int i=0;i<GRID_WIDTH;i++) {
			line[i] = tileAt(i,index);
		}
		return line;
	}

	private Tile tileAt(int x, int y) {
		return myTiles[x+y*GRID_WIDTH];
	}
	
	private boolean canMove() {
		int num1,num2;
		Tile temp = null;
		
		if(availableSpace().size()!=0) {
			return true;
		}
		
		for(int i=0;i<GRID_WIDTH;i++) {
			temp = null;
			for(int j=0;j<GRID_WIDTH;j++){
				
				num1 = cell(i, j).get();
				
				if(i < GRID_WIDTH-1) {
					num2 = cell(i+1, j).get();
					if(num1==num2)
						return true;
				}
				
				if( j < GRID_WIDTH -1) {
					num2 = cell(i, j + 1).get();
					if(num1==num2)
						return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean gameOver() {
		if(!canMove() && !won()) {
			return true;
		}
		return false;
	}
	@Override
	public boolean won() {
		return win;
	}
	@Override
	public Board flipDiagonally() {
		Tile[] list = new Tile[GRID_WIDTH*GRID_WIDTH];
		
		for(int i=0;i<GRID_WIDTH;i++)
			for(int j=0;j<GRID_WIDTH;j++)
				list[i+j*GRID_WIDTH] = myTiles[j+i*GRID_WIDTH];
		
		myTiles = list;
		return this;
	}
	@Override
	public Board flipHorizontally() {
		Tile[] list = new Tile[GRID_WIDTH*GRID_WIDTH];
	
		
		for(int i=0;i<GRID_WIDTH;i++) {
			for(int j=0;j<GRID_WIDTH;j++)
			{
				list[i+((GRID_WIDTH-j-1)*GRID_WIDTH)] = myTiles[i+j*GRID_WIDTH];
			}
		}
		
		myTiles = list;
		return this;
	}
}
