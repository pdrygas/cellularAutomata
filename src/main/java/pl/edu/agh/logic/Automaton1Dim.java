package pl.edu.agh.logic;
import pl.edu.agh.coords.*;

public abstract class Automaton1Dim extends Automaton
{
	private int size;

	public Automaton1Dim(CellNeighborhood neighborsStrategy,CellStateFactory stateFactory,int size) 
	{
		super(neighborsStrategy, stateFactory);
		this.setSize(size);
	}
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coords)
	{
		if ((coords.getCoords().get(1))<getSize()+1)
			return true;
		else
			return false;
	}
	@Override
	protected CellCoordinates initialCoordinates() 
	{
		return new Coords1D(-1);
	}
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coords) 
	{
		return new Coords1D(coords.getCoords().get(1)+1);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
		
}