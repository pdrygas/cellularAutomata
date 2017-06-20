package pl.edu.agh.coords;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class Coords2DTest
{
	private static Coords2D a,b,c;
	public static class Equals
	{
		@Before public void init()
		{
			a= new Coords2D(5,6);
			b= new Coords2D(5,7);
			c= new Coords2D(5,6);
		}
		@Test public void reflexivePropertyTest()
		{
			assertTrue( a.equals(a) );
		}
	 
		@Test public void  symmetricPropertyTest()
		{
			assertTrue( a.equals(b) == b.equals(a) );
		} 
		@Test public void transitivePropertyTest()
		{
			if ( a.equals(b) && b.equals(c) ) 
			{
				assertTrue( a.equals(c) );
			}
		}
	    @Test public void consistencyPropertyTest()
	    {
	    	assertTrue( a.equals(b) == a.equals(b) );
	    }
	    @Test public void  nonNullPropertyTest()
	    {
	    	assertFalse( a.equals(null) );
	    }
	}
}