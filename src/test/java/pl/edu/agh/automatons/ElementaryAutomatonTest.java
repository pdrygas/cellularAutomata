package pl.edu.agh.automatons;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.coords.Coords1D;
import pl.edu.agh.factories.UniformStateFactory;
import pl.edu.agh.hood.ElementaryNeighborhood;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.states.BinaryState;

@RunWith(Enclosed.class)
public class ElementaryAutomatonTest
{
	private static ElementaryAutomaton automaton; 
	private static Set<Cell> set;
	public static class NextCellState
	{
		@Before
		public void init()
		{
			set= new HashSet<Cell>();
			automaton=new ElementaryAutomaton(new ElementaryNeighborhood(10),new UniformStateFactory(BinaryState.DEAD),10,30);
		}
		@Test
		public void Rule30State111()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.ALIVE));
			set.add(new Cell(new Coords1D(5),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.DEAD, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.ALIVE), set));
		}
		@Test
		public void Rule30State110()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.ALIVE));
			set.add(new Cell(new Coords1D(5),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.DEAD, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.ALIVE), set));
		}
		@Test
		public void Rule30State101()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.ALIVE));
			set.add(new Cell(new Coords1D(5),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.DEAD, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.DEAD), set));
		}
		@Test
		public void Rule30State100()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.ALIVE));
			set.add(new Cell(new Coords1D(5),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.ALIVE, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.DEAD), set));
		}
		@Test
		public void Rule30State011()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.DEAD));
			set.add(new Cell(new Coords1D(5),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.ALIVE, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.ALIVE), set));
		}
		@Test
		public void Rule30State010()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.DEAD));
			set.add(new Cell(new Coords1D(5),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.ALIVE, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.ALIVE), set));
		}
		@Test
		public void Rule30State001()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.DEAD));
			set.add(new Cell(new Coords1D(5),BinaryState.ALIVE));
			Assert.assertEquals(BinaryState.ALIVE, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.DEAD), set));
		}
		@Test
		public void Rule30State000()
		{
			set.add(new Cell(new Coords1D(3),BinaryState.DEAD));
			set.add(new Cell(new Coords1D(5),BinaryState.DEAD));
			Assert.assertEquals(BinaryState.DEAD, automaton.nextCellState(new Cell(new Coords1D(4),BinaryState.DEAD), set));
		}
	}
}