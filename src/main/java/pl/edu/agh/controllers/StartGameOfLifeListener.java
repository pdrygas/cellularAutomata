package pl.edu.agh.controllers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;

import pl.edu.agh.AutomatonView;
import pl.edu.agh.SimulationGrid;
import pl.edu.agh.automatons.GameOfLife;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.GeneralStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.hood.VonNeumanNeighborhood;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.QuadState;
import pl.edu.agh.view.GameOfLifeSetup;

public class StartGameOfLifeListener implements ActionListener
{
	private AutomatonView theView;
	private Automaton automaton;
	private Simulation simulation;
	
	public StartGameOfLifeListener(AutomatonView theView) 
	{
		super();
		this.theView = theView;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(simulation!=null)
			return;
		if(!theView.gameGridIsNull())
		{
			simulation=new Simulation();
			Thread t=new Thread(simulation);
			t.start();
			return;
		}
		int width;
		int height;
		int r;
		Integer livingRule;
		Integer dyingRule;
		try 
		{
			boolean quadLife=setupMenuView().getQuadLifeCheck();
			boolean wrap=setupMenuView().getWrappingCheck();
			r=setupMenuView().getRField();
			height = setupMenuView().getWidthField();
			width = setupMenuView().getHeightField();
			livingRule = setupMenuView().getLivingRule();
			dyingRule = setupMenuView().getDyingRule();
			int flag=0;
			if(setupMenuView().getMooreCheck()==setupMenuView().getNeumanCheck())
			{
				throw new NumberFormatException();
			}
			else if(setupMenuView().getNeumanCheck())
			{
				flag=1;
			}
			else
				flag=0;
			automaton=startingStructure(wrap,r,flag,height,width,livingRule,dyingRule,quadLife);
			simulation=new Simulation();
			SwingUtilities.invokeLater(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	    			theView.setGameGrid(new SimulationGrid(automaton,
	    					new Dimension((height*4)+1,(width*4)+1)));

	            }
	        });
		}
		catch (NumberFormatException ex)
		{
			setupMenuView().displayErrorMessage("Wrong parameters");
		}
		Thread t=new Thread(simulation);
		t.start();
	}
	private GameOfLifeSetup setupMenuView() 
	{
		return (GameOfLifeSetup) theView.getSetupMenu();
	} 
	public void stopSimulation()
	{
		if(this.simulation==null)
			return;
		this.simulation.stop();
		this.simulation=null;
	}
	private Automaton startingStructure(boolean wrap,int r,int flag,int width ,int height ,
			Integer livingRule,Integer dyingRule,boolean quadLife)
	{
		Integer quadLifeRule=0;
		if(quadLife)
			quadLifeRule=3;
		else
			quadLifeRule=dyingRule;
		HashMap<CellCoordinates,CellState> map=new HashMap<CellCoordinates,CellState>();
		if(!quadLife)
	    {
			int count = width * height;
			for (int k = 0; k < count; k++) 
			{
				int i = ThreadLocalRandom.current().nextInt(0, width + 1);
				int j = ThreadLocalRandom.current().nextInt(0, height + 1);
				map.put(new Coords2D(i,j), BinaryState.ALIVE);
			}
	    }
		else
		{
			QuadState [] tmpMap=QuadState.values();
			int count = width * height;
			for (int k = 0; k < count; k++) 
			{
				int i = ThreadLocalRandom.current().nextInt(0, width + 1);
				int j = ThreadLocalRandom.current().nextInt(0, height + 1);
				map.put(new Coords2D(i,j), tmpMap[(i+j)%4]);
			}
		}	
	    CellNeighborhood hood;
	    if (flag==1)
	    {
	    	hood= new VonNeumanNeighborhood(r,width,height,wrap);
	    }
	    else
	    	hood= new MoorNeighborhood(r,width,height,wrap);
		GeneralStateFactory factory=new GeneralStateFactory(map,BinaryState.DEAD);
		Automaton automaton=new GameOfLife(hood,factory,width,height,
				livingRule,quadLifeRule,quadLife);
		return automaton;
	}
	class Simulation implements Runnable
	{
		private volatile boolean running;
		@Override
		public void run() 
		{
			this.running=true;
			while (running) 
			{
            	try 
            	{
					repaint();
					automaton=automaton.nextState();
					((SimulationGrid) theView.getGameGrid()).setAutomaton(automaton);
					Thread.sleep(100);
				}
            	catch (InterruptedException e) 
            	{
				}
            }
        }
		public synchronized void stop()
		{
			this.running=false;
		}
        private void repaint() 
        {
        	SwingUtilities.invokeLater(new Runnable()
        	{
        		@Override
        		public void run() 
        		{
        			theView.getGameGrid().repaint();
        		}
        	});
        }		
	}
}