package pl.edu.agh;

import pl.edu.agh.controllers.ElementaryListener;
import pl.edu.agh.controllers.GameOfLifeListener;
import pl.edu.agh.controllers.LangtonAntListener;
import pl.edu.agh.controllers.WireWorldListener;

public class AutomatonController 
{
	private AutomatonView theView;
	public AutomatonController(AutomatonView theView)
	{
		this.theView = theView;
		this.theView.addGameOfLifeListener(new GameOfLifeListener(theView));
		this.theView.addWireWorldListener(new WireWorldListener(theView));
		this.theView.addElementaryListener(new ElementaryListener(theView));
		this.theView.addLangtonAntListener(new LangtonAntListener(theView));
	}
}