package pl.edu.agh.automatons;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.Automaton2Dim;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;
import pl.edu.agh.states.AntState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.LangtonCell;

public class LangtonAnt extends Automaton2Dim
{

	public LangtonAnt(CellNeighborhood neighborsStrategy,
			CellStateFactory stateFactory, int height, int width)
	{
		super( neighborsStrategy, stateFactory, height, width);
	}

	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborStrategy) 
	{
		return new LangtonAnt(neighborStrategy,factory,getHeight(),getWidth());
	}

	@Override
	protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) 
	{
		LangtonCell state = (LangtonCell) currentCell.state;
		BinaryState currentBinaryState = state.cellState;
			LangtonCell upper = null;
			LangtonCell lower = null;
			LangtonCell left = null;
			LangtonCell right = null;
			int x=currentCell.coords.getCoords().get(1);
			int y=currentCell.coords.getCoords().get(2);
			Map<Integer,AntState> antsMap = new HashMap<Integer,AntState>();
			for(Cell neighbor:neighborsStates)
			{
				LangtonCell castedNeighbor = (LangtonCell) neighbor.state;
				if (!castedNeighbor.ants.isEmpty())
				{
					int xtmp=neighbor.coords.getCoords().get(1);
					int ytmp=neighbor.coords.getCoords().get(2);
					if((xtmp==x && ytmp==y-1)||(xtmp==x && ytmp==getWidth()-1))
					{
						left= castedNeighbor;
					}
					if((xtmp==x && ytmp==y+1)||(xtmp==x && ytmp==0))
					{
						right=castedNeighbor;					
					}
					if((xtmp==x+1 && ytmp==y)||(xtmp==0 && ytmp==y))
					{
						lower=castedNeighbor;
					}
					if((xtmp==x-1 && ytmp==y)||(xtmp==getHeight()-1 && ytmp==y))
					{
						upper=castedNeighbor;
					}						
				}
				else
					continue;	
			}
			if (left!=null)
			{
				for(Integer i:left.ants.keySet())
				{
					if (left.ants.get(i)==AntState.EAST)
					{
						if (currentBinaryState==BinaryState.ALIVE)
						{
							antsMap.put(new Integer(i), AntState.NORTH);
						}
						else
						{
							antsMap.put(new Integer(i), AntState.SOUTH);						
						}
					}
					else
						continue;
				}
			}
			if (right!=null)
			{
				for(Integer i:right.ants.keySet())
				{
					if (right.ants.get(i)==AntState.WEST)
					{
						if (currentBinaryState==BinaryState.ALIVE)
						{
							antsMap.put(new Integer(i), AntState.SOUTH);
						}
						else
						{
							antsMap.put(new Integer(i), AntState.NORTH);
						}
					}
					else
						continue;
				}
				
			}
			if (upper!=null)
			{
				for(Integer i:upper.ants.keySet())
				{
					if (upper.ants.get(i)==AntState.SOUTH)
					{
						if (currentBinaryState==BinaryState.ALIVE)
						{
							antsMap.put(new Integer(i), AntState.EAST);
						}
						else
						{
							antsMap.put(new Integer(i), AntState.WEST);
						}
					}
					else
						continue;
				}
			}
			if (lower!=null)
			{
				for(Integer i:lower.ants.keySet())
				{
					if (lower.ants.get(i)==AntState.NORTH)
					{
						if (currentBinaryState==BinaryState.ALIVE)
						{
							antsMap.put(new Integer(i), AntState.WEST);
						}
						else
						{
							antsMap.put(new Integer(i), AntState.EAST);
						}
					}
					else
						continue;
				}				
			}
			BinaryState opposite;
			if (currentBinaryState ==BinaryState.ALIVE)
				opposite=BinaryState.DEAD;
			else
				opposite=BinaryState.ALIVE;	
			return (antsMap.size()%2==0)?new LangtonCell(currentBinaryState,antsMap):new LangtonCell(opposite,antsMap);
	}

	@Override
	public void draw(Graphics g) 
	{
		int size=3;
		int x = 1;
        HashMap<CellCoordinates,CellState> cells=new HashMap<CellCoordinates,CellState>(getCells());
        for (int i = 0; i < getHeight(); i++)
        {  
        	int y = 1;
            for (int j = 0; j < getWidth(); j++) 
            {
            	LangtonCell tmpCell=(LangtonCell) cells.get(new Coords2D(i,j));
                if (tmpCell.cellState==BinaryState.ALIVE) 
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(x,y,size, size);
                }
                else
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(x,y,size, size);
                }
                y += size + 1;
            }
            x += size + 1;
        }
	}
}
