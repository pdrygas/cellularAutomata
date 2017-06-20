package pl.edu.agh.states;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.logic.CellState;

public class LangtonCell implements CellState
{
	public BinaryState cellState ;
	public Map<Integer,AntState> ants; 
	public LangtonCell(BinaryState cellState)
	{
		this.cellState = cellState;
		ants= new HashMap<Integer,AntState>();
	}
	public LangtonCell(BinaryState cellState,Map<Integer,AntState> map)
	{
		this.cellState=cellState;
		this.ants=map;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ants == null) ? 0 : ants.hashCode());
		result = prime * result + ((cellState == null) ? 0 : cellState.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LangtonCell other = (LangtonCell) obj;
		if (ants == null) {
			if (other.ants != null)
				return false;
		} else if (!ants.equals(other.ants))
			return false;
		if (cellState != other.cellState)
			return false;
		return true;
	}
	
}