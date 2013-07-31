import junit.framework.*;
import org.w3c.tools.codec.Base64Decoder;
/*
 * NewEmptyJUnitTest.java
 * JUnit based test
 *
 * Created on July 2, 2013, 12:07 PM
 */

/**
 *
 * @author Elmo
 */
public class NewEmptyJUnitTest extends TestCase {
    
    public NewEmptyJUnitTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() throws Exception {
        Base64Decoder b = new Base64Decoder("b3NpcmlzMy9qc29uL2t5bC9LeWxTZXJ2aWNlLmdldExvZ28=");
        System.out.println(b.processString());
    }

}
