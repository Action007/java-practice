import org.junit.jupiter.api.Test;
import com.practice.testing.samples.Divider;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class DividerTest {
  Divider divider;

  @BeforeEach
  void setup() {
    divider = new Divider();
  }

  @Test
  void testDivideNormal() {
    double result = divider.divide(6, 2);
    assertEquals(3, result);
  }


  @Test
  void testDivideByZeroThrowsException() {
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> divider.divide(6, 0));
    assertTrue(ex.getMessage().contains("Division by zero"));
  }
}
