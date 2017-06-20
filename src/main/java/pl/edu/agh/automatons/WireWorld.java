package pl.edu.agh.automatons;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Set;
import pl.edu.agh.states.WireElectronState;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.Automaton2Dim;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;

public class WireWorld extends Automaton2Dim
{

	public WireWorld(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory,int height, int width) 
	{
		super(neighborsStrategy, stateFactory, height, width);
	}

	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborStrategy)
	{
		return new WireWorld(neighborStrategy,factory,getHeight(),getWidth());
	}

	@Override
	protected CellState nextCellState(Cell currentCell, Set<Cell> neighbors) 
	{
		CellState currentState=currentCell.state;
		if (currentState==WireElectronState.ELECTRON_HEAD)
		{
			return WireElectronState.ELECTRON_TAIL;
		}
		else if (currentState==WireElectronState.ELECTRON_TAIL)
		{
			return WireElectronState.WIRE;
		}
		else if (currentState==WireElectronState.VOID)
		{
			return WireElectronState.VOID;
		}
		else 
		{
			int headNeighbors=0;
			for (Cell cell:neighbors)
			{
				if (cell.state==WireElectronState.ELECTRON_HEAD)
				{
					headNeighbors++;
				}
			}
			if (headNeighbors==1 || headNeighbors==2)
				return WireElectronState.ELECTRON_HEAD;
			else
				return WireElectronState.WIRE ;
		}
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
            	CellState state=cells.get(new Coords2D(i,j));
                if (state==WireElectronState.ELECTRON_HEAD)
                	g.setColor(Color.BLUE);
                else if(state==WireElectronState.ELECTRON_TAIL)
                	g.setColor(Color.RED);
                else if(state==WireElectronState.WIRE)
                	g.setColor(Color.YELLOW);
               	else
               		g.setColor(Color.BLACK);
                g.fillRect(x,y,size, size);
                y += size + 1;
            }
            x += size + 1;
        }
	}
}