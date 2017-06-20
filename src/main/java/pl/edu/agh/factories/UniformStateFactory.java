package pl.edu.agh.factories;

import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;

public class UniformStateFactory implements CellStateFactory
{
	private CellState state;
	public UniformStateFactory(CellState state)
	{
		this.state = state;
	}
	public CellState initialState(CellCoordinates coords)
	{
		return state;
	}
}