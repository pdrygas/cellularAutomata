package pl.edu.agh.logic;
public class Cell
{
	final public CellCoordinates coords;
	final public CellState state;
	public Cell(CellCoordinates coords,CellState state)
	{
		this.coords=coords;
		this.state=state;
		
	}
}