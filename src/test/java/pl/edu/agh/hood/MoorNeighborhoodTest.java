package pl.edu.agh.hood;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.junit.Assert;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.CellCoordinates;

@RunWith(Enclosed.class)
public class MoorNeighborhoodTest
{
		public static class CellNeighborsTest
		{
		private  Set<CellCoordinates> set;
		@Before
		public  void init()
		{
			set = new HashSet<CellCoordinates>();
		}
		@Test
		public void AssertingAllSetOfNeighborsForCoords00FoldingR1()
		{		
			MoorNeighborhood hood = new MoorNeighborhood(1,10,10,true);
			Set<CellCoordinates> setTmp = new HashSet<CellCoordinates>();
			setTmp.add(new Coords2D(9,9));
			setTmp.add(new Coords2D(0,1));
			setTmp.add(new Coords2D(1,0));
			setTmp.add(new Coords2D(1,1));
			setTmp.add(new Coords2D(0,9));
			setTmp.add(new Coords2D(9,0));
			setTmp.add(new Coords2D(1,9));
			setTmp.add(new Coords2D(9,1));
			set=hood.cellNeighbors(new Coords2D(0,0));
			Assert.assertEquals(setTmp,set);
		}
		@Test
		public void CheckingIfNeighborsContainCellItselfForCoords99WithoutFoldingR1()
		{
			MoorNeighborhood hood = new MoorNeighborhood(1,10,10,false);
			set=hood.cellNeighbors(new Coords2D(9,9));
			Assert.assertEquals(3,set.size());
		}
		@Test
		public void AssertingAllSetOfNeighborsR6andIsGreaterThanBothDimensionsFolding() 
		{
			MoorNeighborhood hood = new MoorNeighborhood(6,4,5,true);
			set=hood.cellNeighbors(new Coords2D(2,1));
			Set<CellCoordinates> setTmp = new HashSet<CellCoordinates>();
			for(int i=0;i<4;i++)
			{
				for(int j=0;j<5;j++)
				{
					setTmp.add(new Coords2D(j,i));
				}
			}
			setTmp.remove(new Coords2D(2,1));
			Assert.assertEquals(setTmp,set);
		}
	}
}