package pl.edu.agh.automatons;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.Automaton2Dim;
import pl.edu.agh.logic.Cell;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.logic.CellStateFactory;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.QuadState;

public class GameOfLife extends Automaton2Dim
{
	private Set<Integer> living=new HashSet<Integer>();
	private Set<Integer> dying =new HashSet<Integer>();
	private Integer livingInt;
	private Integer dyingInt;
	private boolean quadLife;
	public GameOfLife( CellNeighborhood neighborsStrategy,
			CellStateFactory stateFactory, int height, int width) 
	{
		super(neighborsStrategy,stateFactory, height, width);
		living.add(2);
		living.add(3);
		dying.add(3);
		livingInt= new Integer(23);
		dyingInt=new Integer(3);
		this.setQuadLife(false);
	}
	public GameOfLife( CellNeighborhood neighborsStrategy,
			CellStateFactory stateFactory, int height,
			int width,Integer livingInt,Integer dyingInt,boolean quadlife)
	{
		super(neighborsStrategy,stateFactory, height, width);
		String tmpLiving = Integer.toString(livingInt);
		for (int i = 0; i < tmpLiving.length(); i++)
		{
		    this.living.add(tmpLiving.charAt(i) - '0');
		}
		String tmpDying = Integer.toString(dyingInt);
		for (int i = 0; i < tmpDying.length(); i++)
		{
		    this.dying.add(tmpDying.charAt(i) - '0');
		}	
		this.livingInt=livingInt;
		this.dyingInt=dyingInt;
		this.setQuadLife(quadlife);
	}
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborStrategy)
	{
		return new GameOfLife(neighborStrategy,factory,
				getHeight(),getWidth(),getLivingInt(),getDyingInt(),isQuadLife());
	}
	@Override
	protected CellState nextCellState(Cell currentCell, Set<Cell> neighbors) 
	{
		if(!isQuadLife())
		{
			Integer aliveNeighbors=0;
			for (Cell cell:neighbors)
			{
				if (cell.state==BinaryState.ALIVE)
				{
					aliveNeighbors++;
				}
			}
			if(currentCell.state==BinaryState.ALIVE)
			{
				if (living.contains(aliveNeighbors))
				{
					return BinaryState.ALIVE;	
				}
				else
					return BinaryState.DEAD;
			}
			else if(dying.contains(aliveNeighbors))
			{
				return BinaryState.ALIVE;
			}
			else
				return BinaryState.DEAD;
			}
			else
		{
			int redNeighbors=0;
			int yellowNeighbors=0;
			int blueNeighbors=0;
			int greenNeighbors=0;
			int sum;
			for (Cell cell:neighbors)
			{
				if (cell.state==QuadState.BLUE)
				{
					blueNeighbors++;
				}
				if (cell.state==QuadState.GREEN)
				{
					greenNeighbors++;
				}
				if (cell.state==QuadState.YELLOW)
				{
					yellowNeighbors++;
				}
				if (cell.state==QuadState.RED)
				{
					redNeighbors++;
				}	
			}
			sum=redNeighbors+yellowNeighbors+greenNeighbors+blueNeighbors;
			if(currentCell.state!=QuadState.DEAD)
			{
				if (living.contains(sum))
				{
					return currentCell.state;	
				}
				else
					return QuadState.DEAD;
			}
			else if(dying.contains(sum))
			{
				int tmp=Math.max(yellowNeighbors, greenNeighbors);
				int tmp2=Math.max(blueNeighbors, redNeighbors);
				tmp=Math.max(tmp, tmp2);
				if(tmp==1)
				{	
					return returnProperColor(redNeighbors, yellowNeighbors, greenNeighbors, 0);
				}
				else
				{
					return returnProperColor(redNeighbors, yellowNeighbors, greenNeighbors, tmp);
				}	
			}
			else
				return QuadState.DEAD;
		}
	}
	private QuadState returnProperColor(int redNeighbors, int yellowNeighbors,
			int greenNeighbors, int tmp) 
	{
		if(yellowNeighbors==tmp)
			return QuadState.YELLOW;
		else if(redNeighbors==tmp)
			return QuadState.RED;
		else if(greenNeighbors==tmp)
			return QuadState.GREEN;
		else
			return QuadState.BLUE;
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
                if (cells.get(new Coords2D(i,j))!=BinaryState.DEAD
                		&& cells.get(new Coords2D(i,j))!=QuadState.DEAD) 
                {
                	CellState state=cells.get(new Coords2D(i,j));
                	if (state==BinaryState.ALIVE)
                		g.setColor(Color.BLACK);
                	else if(state==QuadState.BLUE)
                		g.setColor(Color.BLUE);
                	else if(state==QuadState.YELLOW)
                		g.setColor(Color.YELLOW);
                	else if(state==QuadState.GREEN)
                		g.setColor(Color.GREEN);
                	else if (state==QuadState.RED)
                		g.setColor(Color.RED);
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
	public Integer getDyingInt()
	{
		return dyingInt;
	}
	public void setDyingInt(Integer dyingInt)
	{
		this.dyingInt = dyingInt;
	}
	public Integer getLivingInt() {
		return livingInt;
	}
	public void setLivingInt(Integer livingInt) 
	{
		this.livingInt = livingInt;
	}
	public boolean isQuadLife() 
	{
		return quadLife;
	}
	public void setQuadLife(boolean quadLife)
	{
		this.quadLife = quadLife;
	}
}