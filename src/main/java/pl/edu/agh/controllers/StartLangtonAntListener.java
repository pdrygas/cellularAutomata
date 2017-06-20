package pl.edu.agh.controllers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;

import pl.edu.agh.AutomatonView;
import pl.edu.agh.SimulationGrid;
import pl.edu.agh.automatons.LangtonAnt;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.GeneralStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.hood.VonNeumanNeighborhood;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.states.AntState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.LangtonCell;
import pl.edu.agh.view.LangtonAntSetup;

public class StartLangtonAntListener implements ActionListener
{
	private AutomatonView theView;
	private Automaton automaton;
	private Simulation simulation;
	
	public StartLangtonAntListener(AutomatonView theView) 
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
		int ants;
		try 
		{
			boolean wrap=setupMenuView().getWrappingCheck();
			height = setupMenuView().getWidthField();
			width = setupMenuView().getHeightField();
			ants = setupMenuView().getAntsField();
			automaton=startingStructure(wrap,ants,height,width);
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
	private LangtonAntSetup setupMenuView() 
	{
		return ((LangtonAntSetup)theView.getSetupMenu());
	} 
	public void stopSimulation()
	{
		if(this.simulation==null)
			return;
		this.simulation.stop();
		this.simulation=null;
	}
	private Automaton startingStructure(boolean wrap,int ants,int width ,int height)
	{
    	AntState [] antStateArray=new AntState[]{AntState.EAST,AntState.NORTH,AntState.WEST,AntState.SOUTH};
		HashMap<CellCoordinates,CellState> map=new HashMap<CellCoordinates,CellState>();
	    for (int k = 0; k < ants; k++) 
	    {
	    	int i = ThreadLocalRandom.current().nextInt(width/3, 2*width/3);
	        int j = ThreadLocalRandom.current().nextInt(height/3, 2*height/3 + 1);
	        HashMap<Integer,AntState> tmpMap=new HashMap<Integer,AntState>();
	        tmpMap.put(new Integer(k+1), antStateArray[k%3]);
	        map.put(new Coords2D(i,j), new LangtonCell(BinaryState.DEAD,tmpMap));
	    }
	    CellNeighborhood hood= new VonNeumanNeighborhood(1,width,height,wrap);
		GeneralStateFactory factory=new GeneralStateFactory(map,new LangtonCell(BinaryState.DEAD));
		Automaton automaton=new LangtonAnt(hood,factory,width,height);
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