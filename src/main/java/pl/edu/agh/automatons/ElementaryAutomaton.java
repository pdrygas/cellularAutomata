package pl.edu.agh.automatons;
/**
 * Represent
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import pl.edu.agh.coords.Coords1D;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.Automaton1Dim;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;
import pl.edu.agh.states.BinaryState;

public class ElementaryAutomaton extends Automaton1Dim
{
	private boolean [] ruleBinary = new boolean[8];
	private int rule;
	private int generation;

	public ElementaryAutomaton(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int size,int rule)
	{
		super(neighborsStrategy, stateFactory, size);
		generation=0;
		this.rule=rule;
		for (int i = 7; i >= 0; i--) {
	        this.ruleBinary[i] = (rule & (1 << i)) != 0;
	    }	
		Map<CellCoordinates,CellState> map = this.getCells();
		for (int i=0;i<getSize();i++)
		{
			map.put(new Coords1D(i), stateFactory.initialState(new Coords1D(i)));
		}
	}
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborStrategy) 
	{
		return new ElementaryAutomaton(neighborStrategy,factory,getSize(),rule);
	}
	@Override
	protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) 
	{
		BinaryState left=null;
		BinaryState right=null;
		BinaryState currentState=(BinaryState) currentCell.state;
		int x=currentCell.coords.getCoords().get(1);
		for(Cell neighbor:neighborsStates)
		{
			int xtmp=neighbor.coords.getCoords().get(1);
			if(xtmp==x-1)
			{
				left =(BinaryState) neighbor.state;
			}
			else if(xtmp==x+1)
			{
				right=(BinaryState) neighbor.state;				
			}
		}
		if (left==null || right==null)
			return BinaryState.DEAD;
		if(left==BinaryState.ALIVE && currentState==BinaryState.ALIVE && right==BinaryState.ALIVE)
		{
			return convertBooleanToBinaryState(ruleBinary[7]);
		}
		if(left==BinaryState.ALIVE && currentState==BinaryState.ALIVE && right==BinaryState.DEAD)
		{
			return convertBooleanToBinaryState(ruleBinary[6]);
		}
		if(left==BinaryState.ALIVE && currentState==BinaryState.DEAD && right==BinaryState.ALIVE)
		{
			return convertBooleanToBinaryState(ruleBinary[5]);
		}
		if(left==BinaryState.ALIVE && currentState==BinaryState.DEAD && right==BinaryState.DEAD)
		{
			return convertBooleanToBinaryState(ruleBinary[4]);
		}
		if(left==BinaryState.DEAD && currentState==BinaryState.ALIVE && right==BinaryState.ALIVE)
		{
			return convertBooleanToBinaryState(ruleBinary[3]);
		}
		if(left==BinaryState.DEAD && currentState==BinaryState.ALIVE && right==BinaryState.DEAD)
		{
			return convertBooleanToBinaryState(ruleBinary[2]);
		}
		if(left==BinaryState.DEAD && currentState==BinaryState.DEAD && right==BinaryState.ALIVE)
		{
			return convertBooleanToBinaryState(ruleBinary[1]);
		}
		else 
		{
			return convertBooleanToBinaryState(ruleBinary[0]);
		}		
	}
	private CellState convertBooleanToBinaryState(boolean x) 
	{
		if(x==true)
			return BinaryState.ALIVE;
		else
			return BinaryState.DEAD;
	}
	public void setGeneration(int generation) 
	{
		this.generation = generation;
	}
	@Override
	public void draw(Graphics g) 
	{
		int size=3;
		int y = generation*size+1;
		int x=1;
        HashMap<CellCoordinates,CellState> cells=new HashMap<CellCoordinates,CellState>(getCells());
        for (int i = 0; i < getSize(); i++)
        {  
        	CellState state=cells.get(new Coords1D(i));
        	if (state==BinaryState.ALIVE)
        	{
        		g.setColor(Color.BLACK);
        		g.fillRect(x,y,size, size);
        	}
        	else
        	{
        		g.setColor(Color.WHITE);
        		g.fillRect(x,y,size, size);
        	}
        	x+=size+1;
        }    		
	}
}