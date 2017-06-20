package pl.edu.agh;

import java.awt.Dimension;
import java.awt.Graphics; 
import javax.swing.JPanel;
import pl.edu.agh.logic.Automaton;
 
public class SimulationGrid extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Automaton automaton=null;
 
    public Automaton getAutomaton() {
		return automaton;
	}
	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
	}
	public SimulationGrid(Automaton automaton,Dimension d) 
    {
        this.automaton=automaton;
        this.setPreferredSize(d);
    }    
    public SimulationGrid() 
    {
	}
	@Override
    protected void paintComponent(Graphics g) 
    {
        automaton.draw(g);
    }
}
    
