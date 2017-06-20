package pl.edu.agh.coords;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.logic.CellCoordinates;

public class Coords1D implements CellCoordinates
{
	final public int x;
	public Coords1D(int x)
	{
		this.x=x;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coords1D other = (Coords1D) obj;
		if (x != other.x)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		return result;
	}
	public Map<Integer,Integer> getCoords()
	{
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(1);
		map.put(1, this.x);
		return map;
	}
}