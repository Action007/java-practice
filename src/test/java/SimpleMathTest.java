import org.junit.jupiter.api.Test;
import com.practice.testing.samples.SimpleMath;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class SimpleMathTest {
  SimpleMath simpleMath;

  @BeforeEach
  void setup() {
    simpleMath = new SimpleMath();
  }

  @Test
  void testAddPositiveNumbers() {
    int result = simpleMath.add(3, 5);

    assertEquals(8, result);
  }

  @Test
  void testSubtractPositiveNumbers() {
    int result = simpleMath.subtract(5, 3);

    assertEquals(2, result);
  }
}
