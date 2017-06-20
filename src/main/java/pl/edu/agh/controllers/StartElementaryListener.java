package pl.edu.agh.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import pl.edu.agh.AutomatonView;
import pl.edu.agh.SimulationGrid;
import pl.edu.agh.automatons.ElementaryAutomaton;
import pl.edu.agh.coords.Coords1D;
import pl.edu.agh.factories.GeneralStateFactory;
import pl.edu.agh.hood.ElementaryNeighborhood;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.view.ElementarySetup;

public class StartElementaryListener implements ActionListener
{
	private AutomatonView theView;
	private Automaton automaton;
	private Simulation simulation;
	
	public StartElementaryListener(AutomatonView theView) 
	{
		super();
		this.theView = theView;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(simulation!=null)
			return;
		int size;
		int rule;
		try 
		{
			rule=setupMenuView().getRule();
			size = setupMenuView().getSizeField();
			automaton=startingStructure(size,rule);
			simulation=new Simulation(size*4);
			SwingUtilities.invokeLater(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	    			theView.setGameGrid(new SimulationGrid(automaton,
	    					new Dimension((size*4)+1,(size*6)+1)));

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
	private ElementarySetup setupMenuView() 
	{
		return (ElementarySetup) theView.getSetupMenu();
	} 
	public void stopSimulation()
	{
		if(this.simulation==null)
			return;
		this.simulation.stop();
		this.simulation=null;
	}
	private Automaton startingStructure(int size,int rule)
	{

		HashMap<CellCoordinates,CellState> map=new HashMap<CellCoordinates,CellState>();
		map.put(new Coords1D(size/2), BinaryState.ALIVE);
	    CellNeighborhood hood= new ElementaryNeighborhood(size);
		GeneralStateFactory factory=new GeneralStateFactory(map,BinaryState.DEAD);
		Automaton automaton=new ElementaryAutomaton(hood,factory,size,rule);
		return automaton;
	}
	class Simulation implements Runnable
	{
		private volatile boolean running;
		private int limit;
		private int generation=0;
		public Simulation(int limit)
		{
			this.limit=limit;
		}
		@Override
		public void run() 
		{
			this.running=true;
			while (running&&generation<limit) 
			{
            	try 
            	{
					repaint();
					automaton=automaton.nextState();
					((ElementaryAutomaton) automaton).setGeneration(generation);
					generation++;
					((SimulationGrid) theView.getGameGrid()).setAutomaton(automaton);
					Thread.sleep(20);
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