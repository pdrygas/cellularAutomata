package pl.edu.agh.hood;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;

public class VonNeumanNeighborhood implements CellNeighborhood
{
	private int width;
	private int height;
	private int r;
	private boolean folding;
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cell) 
	{
		Set<CellCoordinates> set = new HashSet<CellCoordinates>();
		boolean ascending=true;
		int tmpy=cell.getCoords().get(1)-r;
		for(int offset=0;;)
		{
			for(int xiterator=cell.getCoords().get(2)-offset;xiterator<=cell.getCoords().get(2)+offset;xiterator++)
			{
				int tmpx=xiterator;

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
			if(!ascending && offset==0)
				break;
			if (ascending)
			{
				offset++;
			}
			else
			{
				offset--;
			}
			if (offset>r)
			{
				offset-=2;
				ascending=false;
			}
			tmpy++;
		}
		if (set.contains(cell))
		{
			set.remove(cell);
		}
		return set;
	}
	public VonNeumanNeighborhood(int r,int width,int height,boolean folding)
	{
		this.r=r;
		this.width=width;
		this.height=height;
		this.folding=folding;
	}

}
