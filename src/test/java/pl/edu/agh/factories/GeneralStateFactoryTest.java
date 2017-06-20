package pl.edu.agh.factories;

import java.util.HashMap;
import org.junit.*;
import org.junit.Assert;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.LangtonCell;

@RunWith(Enclosed.class)
public class GeneralStateFactoryTest
{
	public static class InitialStateTest
	{
		private  HashMap<CellCoordinates, CellState> map;
		@Test
		public void FactoryForBinaryStateWithCoords10MappedToAliveInItsStatesField()
		{
			map=new HashMap<CellCoordinates,CellState>();	
			map.put(new Coords2D(1,0),BinaryState.ALIVE);
			GeneralStateFactory factory=new GeneralStateFactory(map,BinaryState.DEAD);
			Assert.assertEquals(BinaryState.ALIVE,factory.initialState(new Coords2D(1,0)));
		}
		@Test 
		public void FactoryForLangtonCellCheckingDefault()
		{
			map=new HashMap<CellCoordinates,CellState>();
			GeneralStateFactory factory=new GeneralStateFactory(map,new LangtonCell(BinaryState.DEAD));
			Assert.assertEquals(new LangtonCell(BinaryState.DEAD),factory.initialState(new Coords2D(1,0)));
		}
	}
}