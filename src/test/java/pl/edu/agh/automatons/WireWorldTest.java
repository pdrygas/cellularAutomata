package pl.edu.agh.automatons;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.UniformStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.states.WireElectronState;

@RunWith(Enclosed.class)
public class WireWorldTest
{
	private static WireWorld game;
	private static Set<Cell> set;
	public static class NextCellState
	{
		@Before
		public void init()
		{
			set = new HashSet<Cell>();
			game = new WireWorld(new MoorNeighborhood(1,20,20,true),new UniformStateFactory(WireElectronState.VOID),20,20);
		}
		@Test
		public void CellStateIsElectronHead()
		{
			WireElectronState expected=WireElectronState.ELECTRON_TAIL;
			WireElectronState actual=(WireElectronState) game.nextCellState(new Cell(new Coords2D(0,1),WireElectronState.ELECTRON_HEAD),set);	
			Assert.assertEquals(expected,actual);	
		}
		@Test
		public void CellStateIsElectronTail()
		{
			WireElectronState expected=WireElectronState.WIRE;
			WireElectronState actual=(WireElectronState) game.nextCellState(new Cell(new Coords2D(0,1),WireElectronState.ELECTRON_TAIL),set);
			Assert.assertEquals(expected,actual);	
		}
		@Test
		public void CellStateIsVoid()
		{
			WireElectronState expected=WireElectronState.VOID;
			WireElectronState actual=(WireElectronState) game.nextCellState(new Cell(new Coords2D(0,1),WireElectronState.VOID),set);	
			Assert.assertEquals(expected,actual);	
		}	
		@Test
		public void CellStateIsWireAndHas2ElectronHeadNeighbors()
		{
			WireElectronState expected=WireElectronState.ELECTRON_HEAD;
			set.add(new Cell(new Coords2D(1,0),WireElectronState.ELECTRON_HEAD));
			set.add(new Cell(new Coords2D(1,1),WireElectronState.ELECTRON_HEAD));
			WireElectronState actual=(WireElectronState) game.nextCellState(new Cell(new Coords2D(0,1),WireElectronState.WIRE),set);	
			Assert.assertEquals(expected,actual);	
		}
	}	
}