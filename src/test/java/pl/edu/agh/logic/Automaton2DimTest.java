package pl.edu.agh.logic;


import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.automatons.GameOfLife;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.UniformStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.states.BinaryState;

@RunWith(Enclosed.class)
public class Automaton2DimTest
{
	private static GameOfLife game;
	public static class NextCoordinatesTest
	{
		@Before
		public void init()
		{
			game = new GameOfLife(new MoorNeighborhood(1,20,20,true),new UniformStateFactory(BinaryState.DEAD),20,20);
		}
		@Test 
		public void CellInLastColumn()
		{
			Assert.assertEquals(new Coords2D(0,1), game.nextCoordinates(new Coords2D(19,0)));
		}
		@Test
		public void CellCoordsAre2And5()
		{
			Assert.assertEquals(new Coords2D(3,5), game.nextCoordinates(new Coords2D(2,5)));
		}	
	}
	public static class HasNextCoordinatesTest
	{
		@Before
		public void init()
		{
			game = new GameOfLife(new MoorNeighborhood(1,20,20,true),new UniformStateFactory(BinaryState.DEAD),20,20);
		}
		@Test
		public void CoordsFromInitialCoordinatesMethod()
		{
			Assert.assertEquals(true,game.hasNextCoordinates(game.initialCoordinates()));
		}
		@Test
		public void CoordsOfLastCell()
		{
			Assert.assertEquals(false,game.hasNextCoordinates(new Coords2D(19,19)));
		}
		@Test
		public void CoordsOfCellInLastRowButNotInCorner()
		{
			Assert.assertEquals(true,game.hasNextCoordinates(new Coords2D(19,3)));
		}
		@Test
		public void CoordsInTheMiddle()
		{
			Assert.assertEquals(true,game.hasNextCoordinates(new Coords2D(4,3)));
		}
	}
}
