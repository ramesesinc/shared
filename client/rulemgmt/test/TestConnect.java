import com.rameses.service.ScriptServiceContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import junit.framework.*;
/*
 * TestConnect.java
 * JUnit based test
 *
 * Created on June 28, 2013, 9:52 AM
 */

/**
 *
 * @author Elmo
 */
public class TestConnect extends TestCase {
    
    public TestConnect(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() throws Exception {
        Map map = new HashMap();
        map.put("app.context","etracs25");
        map.put("app.host","192.168.254.7");
        map.put("app.cluster", "osiris3");
        ScriptServiceContext sp = new ScriptServiceContext(map);
        /*
        ServiceProxy sp1 = (ServiceProxy)sp.create( "DateService" );
        Date dt = new Date();
        System.out.println("->"+sp1.invoke( "getYear", new Object[]{dt} ));
         */
        MyDummy d = sp.create( "DateService", MyDummy.class );
        System.out.println("my dummy date ->" + d.getYear( new Date() ));
    }

    private interface MyDummy {
        int getYear( Object dt );
    }
    
}
