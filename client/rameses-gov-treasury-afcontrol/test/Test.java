import junit.framework.*;
/*
 * Test.java
 * JUnit based test
 *
 * Created on July 19, 2013, 1:37 PM
 */

/**
 *
 * @author Elmo
 */
public class Test extends TestCase {
    
    public Test(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() {
        String s = "selectedItem|entity.(startseries|qty)";
        System.out.println("selectedItem".matches(s));
        System.out.println("entity.qty".matches(s));
        System.out.println("entity.startseries".matches(s));
        System.out.println("selectedItem|entity".matches(s));
    }

}
