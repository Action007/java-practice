import org.junit.jupiter.api.Test;
import com.practice.testing.samples.GreetingService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class GreetingServiceTestJUnit5 {
  GreetingService greetingService;

  @BeforeEach
  void setup() {
    greetingService = new GreetingService();
  }

  @Test
  void testGreetValidName() {
    String result = greetingService.greet("Jhon");
    assertEquals("Hello, Jhon!", result);
  }

  @Test
  void testGreetInvalidName() {
    assertThrows(IllegalArgumentException.class, () -> {
      greetingService.greet(null);
    });
  }

  @Test
  void testGreetEmptyString() {
    assertThrows(IllegalArgumentException.class, () -> greetingService.greet(""));
  }

  @Test
  void testGreetBlankString() {
    assertThrows(IllegalArgumentException.class, () -> greetingService.greet("   "));
  }
}
