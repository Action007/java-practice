package advanced;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({RandomNumberGeneratorTest.class, StringUtilsTest.class})
public class AllTestsSuite {
  // Empty — just a suite
}
