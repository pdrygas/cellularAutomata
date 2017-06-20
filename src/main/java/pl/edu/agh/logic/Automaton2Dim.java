package pl.edu.agh.logic;

import java.util.Map;


import pl.edu.agh.coords.*;

public abstract class Automaton2Dim extends Automaton
{
	private int width;
	private int height;

	public Automaton2Dim(CellNeighborhood neighborsStrategy,CellStateFactory stateFactory,int height,int width) 
	{
		super(neighborsStrategy, stateFactory);
		this.setWidth(width);
		this.setHeight(height);
		Map<CellCoordinates,CellState> map = this.getCells();
		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				Coords2D coords =new Coords2D(i,j);
				map.put(coords, stateFactory.initialState(coords));
			}
		}
	}
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coords) 
	{
		if(coords.getCoords().get(1)==getHeight()-1)
		{
			if((coords.getCoords().get(2)+1)<getWidth())
				return true;
			else
				return false;
		}
		else
			return true;
	}

	@Override
	protected CellCoordinates initialCoordinates() 
	{
		return new Coords2D(-1,0);
	}

	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coords) 
	{
		if(coords.getCoords().get(1)==getHeight()-1)
		{
			return new Coords2D(0,coords.getCoords().get(2)+1);
		}
		else
			return new Coords2D(coords.getCoords().get(1)+1,coords.getCoords().get(2));
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
		
}