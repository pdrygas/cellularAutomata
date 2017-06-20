package pl.edu.agh.factories;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;

public class GeneralStateFactory implements CellStateFactory
{
	private Map<CellCoordinates,CellState> states;
	private CellState defaultState;
	public GeneralStateFactory(HashMap<CellCoordinates, CellState> states, CellState defaultState) 
	{
		this.states = new HashMap<CellCoordinates, CellState>(states);
		this.defaultState = defaultState;
	}
	public CellState initialState(CellCoordinates coords)
	{
		return states.get(coords)!=null?states.get(coords):defaultState;
	}	
}