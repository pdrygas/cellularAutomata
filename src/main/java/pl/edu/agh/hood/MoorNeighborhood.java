package pl.edu.agh.hood;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;

public class MoorNeighborhood implements CellNeighborhood
{
	private int r,width,height;
	private boolean folding;
	
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cell) 
	{
		Set<CellCoordinates> set = new HashSet<CellCoordinates>();
		if(r>=width/2 && r>=height/2)
			{
				for(int xiterator=0;xiterator<width;xiterator++)
				{
					for(int yiterator=0;yiterator<height;yiterator++)
					{
						set.add(new Coords2D(yiterator,xiterator));
					}
				}	
			}
			else
			{
				for(int xiterator=cell.getCoords().get(2)-r;xiterator<=cell.getCoords().get(2)+r;xiterator++)
				{
					for(int yiterator=cell.getCoords().get(1)-r;yiterator<=cell.getCoords().get(1)+r;yiterator++)
					{
						int tmpx=xiterator;
						int tmpy=yiterator;
						if (tmpx<0)
						{
							if(!folding)
								continue;
							tmpx=Math.abs(width-(Math.abs(tmpx)%width));
						}
						if(tmpx>=height)
						{
							if(!folding)
								continue;
							tmpx=Math.abs(0+(tmpx%width));
						}
						if (tmpy<0)
						{
							if(!folding)
								continue;
							tmpy=Math.abs(height-(Math.abs(tmpy)%height));
						}
						if(tmpy>=width)
						{
							if(!folding)
								continue;
							tmpy=Math.abs(0+(tmpy%height));
						}
						set.add(new Coords2D(tmpy,tmpx));
					}
				}	
			}
		if (set.contains(cell))
		{
			set.remove(cell);
		}
		return set;
	}
	public MoorNeighborhood(int r,int width,int height,boolean folding)
	{
		this.r=r;
		this.width=width;
		this.height=height;
		this.folding=folding;
	}
	
}