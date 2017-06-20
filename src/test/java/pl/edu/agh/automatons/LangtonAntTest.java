package pl.edu.agh.automatons;


import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.UniformStateFactory;
import pl.edu.agh.hood.VonNeumanNeighborhood;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.states.AntState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.LangtonCell;

@RunWith(Enclosed.class)
public class LangtonAntTest
{
	private static LangtonAnt game;
	private static Set<Cell> set;
	public static class NextCellStateTest
	{
		@Before
		public void init()
		{
			game= new LangtonAnt(new VonNeumanNeighborhood(1,20,20,true),new UniformStateFactory(new LangtonCell(BinaryState.DEAD)),20,20);
			set= new HashSet<Cell>();		
		}
		@Test 
		public void CellHasOneAntAndIsDead()
		{
			Map<Integer,AntState> ants = new HashMap<Integer,AntState>();
			ants.put(1, AntState.EAST);
			Cell actualCell=new Cell(new Coords2D(4,4),new LangtonCell(BinaryState.DEAD,ants));
			LangtonCell expected = new LangtonCell(BinaryState.ALIVE);			
			LangtonCell actual = (LangtonCell) game.nextCellState(actualCell, set);
			Assert.assertEquals(expected, actual);
		}
		@Test
		public void CellHas2AntsAndIsAlive()
		{
			Map<Integer,AntState> ants = new HashMap<Integer,AntState>();
			ants.put(1, AntState.EAST);
			ants.put(2, AntState.NONE);
			Cell actualCell=new Cell(new Coords2D(4,4),new LangtonCell(BinaryState.ALIVE,ants));
			LangtonCell expected = new LangtonCell(BinaryState.ALIVE);			
			LangtonCell actual = (LangtonCell) game.nextCellState(actualCell, set);
			Assert.assertEquals(expected, actual);
			
		}
		@Test
		public void CellHasNoAntsIsAliveAndLeftNeighborHasAntWithStateEast()
		{
			Map<Integer,AntState> antsExpected = new HashMap<Integer,AntState>();
			Map<Integer,AntState> antsNeighbor = new HashMap<Integer,AntState>();
			antsExpected.put(1, AntState.NORTH);
			antsNeighbor.put(1, AntState.EAST);			
			Cell leftNeighbor = new Cell(new Coords2D(4,3),new LangtonCell(BinaryState.ALIVE,antsNeighbor));
			Cell actualCell=new Cell(new Coords2D(4,4),new LangtonCell(BinaryState.ALIVE));		
			set.add(leftNeighbor);
			LangtonCell expected = new LangtonCell(BinaryState.ALIVE,antsExpected);			
			LangtonCell actual = (LangtonCell) game.nextCellState(actualCell, set);
			Assert.assertEquals(expected, actual);			
		}
		@Test
		public void CellHasCoords00WithFoldingIsAliveAndAntWithStateSouthIsInLowerLeftCorner()
		{
			Map<Integer,AntState> antsExpected = new HashMap<Integer,AntState>();
			Map<Integer,AntState> antsNeighbor = new HashMap<Integer,AntState>();
			antsExpected.put(1, AntState.EAST);
			antsNeighbor.put(1, AntState.SOUTH);			
			Cell upperNeighbor = new Cell(new Coords2D(19,0),new LangtonCell(BinaryState.ALIVE,antsNeighbor));
			Cell actualCell=new Cell(new Coords2D(0,0),new LangtonCell(BinaryState.ALIVE));		
			set.add(upperNeighbor);
			LangtonCell expected = new LangtonCell(BinaryState.ALIVE,antsExpected);			
			LangtonCell actual = (LangtonCell) game.nextCellState(actualCell, set);
			Assert.assertEquals(expected, actual);			
		}		
	}
}