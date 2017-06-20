package pl.edu.agh.hood;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.CellCoordinates;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class VonNeumanNeighborhoodTest
{
	public static class CellNeighbors
	{
		private Set<CellCoordinates> set;
		@Before
		public void init()
		{
			set = new HashSet<CellCoordinates>();
		}
		@Test
		public void SimplestCheck()
		{
			VonNeumanNeighborhood hood = new VonNeumanNeighborhood(1,10,10,true);
			Set<CellCoordinates> setTmp = new HashSet<CellCoordinates>();
			setTmp.add(new Coords2D(2,3));
			setTmp.add(new Coords2D(4,3));
			setTmp.add(new Coords2D(3,2));
			setTmp.add(new Coords2D(3,4));
			set=hood.cellNeighbors(new Coords2D(3,3));
			Assert.assertEquals( setTmp,set);
		}
		@Test
		public void Coords00R2WithFolding()
		{
			VonNeumanNeighborhood hood = new VonNeumanNeighborhood(2,10,10,true);
			set=hood.cellNeighbors(new Coords2D(0,0));
			Set<CellCoordinates> setTmp = new HashSet<CellCoordinates>();
			setTmp.add(new Coords2D(0,1));
			setTmp.add(new Coords2D(0,2));
			setTmp.add(new Coords2D(1,0));
			setTmp.add(new Coords2D(2,0));
			setTmp.add(new Coords2D(1,1));
			setTmp.add(new Coords2D(1,9));
			setTmp.add(new Coords2D(0,9));
			setTmp.add(new Coords2D(0,8));
			setTmp.add(new Coords2D(9,9));
			setTmp.add(new Coords2D(9,0));
			setTmp.add(new Coords2D(8,0));
			setTmp.add(new Coords2D(9,1));
			Assert.assertEquals( setTmp,set);	
		}
	}
}
