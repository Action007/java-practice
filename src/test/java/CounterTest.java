

import org.junit.jupiter.api.Test;
import com.practice.testing.samples.Counter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class CounterTest {
  Counter counter;

  @BeforeEach
  void setup() {
    counter = new Counter();
  }


  @Test
  void testIncrement() {
    counter.increment();
    assertEquals(1, counter.getCount());
  }

  @Test
  void testDecrement() {
    counter.decrement();
    assertEquals(-1, counter.getCount());
  }

  @Test
  void testReset() {
    counter.increment();
    counter.increment();
    counter.reset();
    assertEquals(0, counter.getCount());
  }
}
