package pl.edu.agh.logic;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public abstract class Automaton
{
	private Map<CellCoordinates,CellState> cells;
	private CellNeighborhood neighborsStrategy;
	private CellStateFactory stateFactory;
	
	public Automaton( CellNeighborhood neighborsStrategy,
			CellStateFactory stateFactory) 
	{
		this.neighborsStrategy = neighborsStrategy; 
		this.stateFactory = stateFactory;
		this.setCells(new HashMap<CellCoordinates,CellState>());
	}
	public Automaton nextState()
	{
		Automaton newAutomaton=newInstance(stateFactory,neighborsStrategy);
		CellIterator readIterator =cellIterator(this);
		CellIterator writeIterator =cellIterator(newAutomaton);
		while(readIterator.hasNext() && writeIterator.hasNext())
		{
			Cell c = readIterator.next();
			writeIterator.next();
			Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighbors(c.coords);
			Set<Cell> mappedNeighbors = mapCoordinates(neighbors);
			writeIterator.setState(nextCellState(c,mappedNeighbors));	
		}
		return newAutomaton;
	}
	public void insertStructure(Map<? extends CellCoordinates,? extends CellState> structure)
	{
		return;
	}
	public CellIterator cellIterator(Automaton actual)
	{
		return new CellIterator(actual);
		
	}
	public abstract void draw(Graphics g);
	protected abstract Automaton newInstance(CellStateFactory factory ,CellNeighborhood neighborStrategy);
	protected abstract boolean hasNextCoordinates(CellCoordinates coords);
	protected abstract CellCoordinates initialCoordinates();
	protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);
	protected abstract CellState nextCellState(Cell currentCell,Set<Cell> neighborsStates);
	private  Set<Cell> mapCoordinates(Set<CellCoordinates> coords)
	{
		Set<Cell> set = new HashSet<Cell>();
		for(CellCoordinates cellCoords:coords)
		{
			set.add(new Cell(cellCoords,getCells().get(cellCoords)));
		}
		return set;
		
	}
	public Map<CellCoordinates,CellState> getCells() {
		return cells;
	}
	public void setCells(HashMap<CellCoordinates, CellState> cells) {
		this.cells = cells;
	}
	private class CellIterator
	{
		private CellCoordinates currentState;
		private Automaton currentAutomaton;
		public boolean hasNext()
		{
			if (hasNextCoordinates(currentState))
			{
				return true;
			}
			else
				return false;
		}
		public Cell next()
		{
			this.currentState=nextCoordinates(currentState);
			return new Cell(currentState,getCells().get(currentState));
	
		}
		public void setState(CellState state)
		{
			currentAutomaton.getCells().replace(currentState, state);
		}
		public CellIterator(Automaton actual)
		{
			currentState= initialCoordinates();
			currentAutomaton=actual;
		}
		
	}
}
	
	
	
	
