package pl.edu.agh.logic;

import java.util.Map;

public interface CellCoordinates 
{
	 public boolean equals(Object o);
	 public int hashCode();
	 public abstract Map<Integer,Integer> getCoords();
}