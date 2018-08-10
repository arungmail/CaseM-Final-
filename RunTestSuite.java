import org.apache.xpath.operations.Minus;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTestSuite {
	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(JunitTestSuite.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	   }
	 
}
@RunWith(Suite.class)
@Suite.SuiteClasses({
   CreateUser.class,
   Contact.class,
   Cases.class,
   Task.class,
   Cost.class,
   Honorary.class,
   Petition.class,
   LegalProcess.class,
   Comment.class,
   Location.class,
   Minute.class
   
})
  class JunitTestSuite {   
}  