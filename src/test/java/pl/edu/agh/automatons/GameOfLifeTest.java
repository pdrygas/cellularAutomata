package pl.edu.agh.automatons;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.junit.Assert;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.UniformStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.states.BinaryState;

@RunWith(Enclosed.class)
public class GameOfLifeTest
{
	private static GameOfLife game;
	private static Set<Cell> set;
	public static class NextCellStateTest
	{
		@Before
		public void init()
		{
			game = new GameOfLife(new MoorNeighborhood(1,20,20,true),new UniformStateFactory(BinaryState.DEAD),20,20);
			set = new HashSet<Cell>();
		}
		@Test
		public void CellIsDeadAndAllNeighborsAreDead()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.DEAD,game.nextCellState(new Cell(new Coords2D(1,0),BinaryState.DEAD),set));
		}
		@Test 
		public void CellIsDeadAnd2NeighborsAreAlive()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,0),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.DEAD,game.nextCellState(new Cell(new Coords2D(0,1),BinaryState.DEAD),set));			
		}
		@Test
		public void CellIsDeadAnd3NeighborsAreAlive()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,1),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.ALIVE,game.nextCellState(new Cell(new Coords2D(0,1),BinaryState.DEAD),set));		
		}
		@Test
		public void CellIsAliveAndAllNeighborsAreDead()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.DEAD,game.nextCellState(new Cell(new Coords2D(1,0),BinaryState.ALIVE),set));		
		}
		@Test
		public void CellIsAliveAnd2NeighborsAreAlive()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,0),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.ALIVE,game.nextCellState(new Cell(new Coords2D(0,1),BinaryState.ALIVE),set));			
		}
		@Test
		public void CellIsAliveAnd3NeighborsAreAlive()
		{
			set.add(new Cell(new Coords2D(0,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,0),BinaryState.ALIVE));
			set.add(new Cell(new Coords2D(1,0),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.ALIVE,game.nextCellState(new Cell(new Coords2D(0,1),BinaryState.ALIVE),set));						
		}
		public void CellIsAliveAndEmptySetOfNeighbors()
		{
			Assert.assertEquals(BinaryState.DEAD,game.nextCellState(new Cell(new Coords2D(0,1),BinaryState.ALIVE),set));				
		}
	}
}