import com.rameses.common.PropertyResolver;
import junit.framework.*;
/*
 * TestBean.java
 * JUnit based test
 *
 * Created on June 8, 2013, 9:55 PM
 */

/**
 *
 * @author Elmo
 */
public class TestBean extends TestCase {
    
    public TestBean(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public void setName(String name) {
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() {
        TestFact tf = new TestFact();
        tf.setFirstname("elmo");
        tf.setLastname("nazareno");
        System.out.println("name is " + PropertyResolver.getInstance().getProperty(tf,"firstname"));
    
    }

}
