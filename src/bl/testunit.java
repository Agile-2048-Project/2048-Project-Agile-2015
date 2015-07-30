package bl;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class testunit {
	Game testgame=new Game();
	@Test
	public void testcompare() {
		
		Tile[] myTiles1 = testgame.getLine(2);
		Tile[] myTiles2 = testgame.getLine(2);	
		boolean check= testgame.compare(myTiles1, myTiles2); 
		assertEquals(check, true);
	
	}
	
	@Test
	public void testlogin(){
		Data db1 = Data.getInstance();
		String Name = db1.getPlayerName();
		Name = "gilad";
		db1.setPlayerName(Name);
		assertNotNull(Name);
		assertEquals(Name, db1.getPlayerName());

	}
	
	@Test
	public void testcell(){
		Optional<Integer> checkcell;
		checkcell=testgame.cell(2, 2);
		assertEquals(checkcell, Optional.of(0));
		
		
	}

	

}
