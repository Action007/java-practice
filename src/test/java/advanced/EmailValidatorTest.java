package advanced;

import org.junit.jupiter.api.*;
import com.practice.testing.samples.advanced.EmailValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.TimeUnit;

class EmailValidatorTest {
  EmailValidator emailValidator;

  @BeforeEach
  void setup() {
    emailValidator = new EmailValidator();
  }

  @DisplayName("✅ Should accept valid email like user@example.com")
  @Test
  void testValidEmail() {
    assertTrue(emailValidator.isValid("user@example.com"));
  }

  @DisplayName("❌ Should reject null email")
  @Test
  void testInvalidEmailNull() {
    assertFalse(emailValidator.isValid(null));
  }

  @Disabled("This test is expected to fail due to timeout — for learning")
  @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
  @Test
  void testProcessEmailTimeout() {
    emailValidator.processEmail("user@example.com");
  }
}
