package pl.edu.agh;

import javax.swing.SwingUtilities;

/**
* <h1>Cellular automatons</h1>
* This application implements four cellular automatons:<br>
* <b>Connway's Game of Life</b> with "QuadLife" option<br>
* <b>Wire World</b><br>
* <b>Langton's Ant</b><br>
* <b>Elementary Cellular Automaton</b> with all 256 rules<br>
* @author  Paweł Drygaś
* @version 1.0
* @since   2016-01-05
*/

public class App implements Runnable 
{    
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new App());
    }
    @Override
    public void run() 
    {
        AutomatonView view =new AutomatonView();
        AutomatonController controller=new AutomatonController(view);
        view.setVisible(true);
    }
}