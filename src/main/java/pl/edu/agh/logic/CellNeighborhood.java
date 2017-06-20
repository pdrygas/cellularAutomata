package pl.edu.agh.logic;

import java.util.Set;

public interface CellNeighborhood
{
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cell); 
}