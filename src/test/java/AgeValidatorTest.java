

import org.junit.jupiter.api.Test;
import com.practice.testing.samples.AgeValidator;
import com.practice.testing.samples.Counter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class AgeValidatorTest {
  AgeValidator ageValidator;

  @BeforeEach
  void setup() {
    ageValidator = new AgeValidator();
  }

  @Test
  void testValidAge() {
    int testValue = 25;
    boolean result = ageValidator.isValidAge(testValue);
    assertTrue(result);
  }

  @Test
  void testInvalidAgeTooLow() {
    int testValue = -1;
    boolean result = ageValidator.isValidAge(testValue);
    assertFalse(result);
  }

  @Test
  void testInvalidAgeTooHigh() {
    int testValue = 121;
    boolean result = ageValidator.isValidAge(testValue);
    assertFalse(result);
  }
}
