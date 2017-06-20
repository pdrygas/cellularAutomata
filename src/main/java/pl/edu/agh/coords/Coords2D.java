package pl.edu.agh.coords;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.logic.CellCoordinates;

public class Coords2D implements CellCoordinates
{
	final public int x;
	final public int y;
	public Coords2D(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	 @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coords2D other = (Coords2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	 @Override
	public Map<Integer,Integer> getCoords()
	{
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(2);
		map.put(1, this.x);
		map.put(2, this.y);
		return map;
	}
}