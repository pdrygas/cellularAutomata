package pl.edu.agh;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.edu.agh.hood.*;
import pl.edu.agh.automatons.*;
import pl.edu.agh.coords.*;
import pl.edu.agh.factories.*;
import pl.edu.agh.logic.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ElementaryAutomatonTest.class,GameOfLifeTest.class,LangtonAntTest.class,WireWorldTest.class,
	Coords1DTest.class,Coords2DTest.class,MoorNeighborhoodTest.class,VonNeumanNeighborhoodTest.class,
	Automaton1DimTest.class,Automaton2DimTest.class,AutomatonTest.class,GeneralStateFactoryTest.class,
	UniformStateFactoryTest.class})
public class TestSuite 
{
}