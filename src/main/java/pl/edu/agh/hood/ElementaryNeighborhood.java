package pl.edu.agh.hood;
import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.coords.Coords1D;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;

public class ElementaryNeighborhood implements CellNeighborhood
{
	private int size;
	
	public ElementaryNeighborhood(int size) 
	{
		this.size = size;
	}

	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cell)
	{
		Set<CellCoordinates> set=new HashSet<CellCoordinates>();
		int x=cell.getCoords().get(1);
		if (x==0)
		{
			set.add(new Coords1D(1));
		}
		else if (x==size-1)
		{
			set.add(new Coords1D(size-2));
		}
		else
		{
			set.add(new Coords1D(x+1));
			set.add(new Coords1D(x-1));
		}
		return set;		
	}
}
	