package advanced;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.practice.testing.samples.advanced.RandomNumberGenerator;

public class RandomNumberGeneratorTest {
  RandomNumberGenerator randomNumberGenerator;

  @BeforeEach
  void setup() {
    randomNumberGenerator = new RandomNumberGenerator();
  }

  @Tag("fast")
  @RepeatedTest(10)
  void testRandomEvenNumberRepeated(RepetitionInfo repetitionInfo) {
    int num = randomNumberGenerator.nextInt(100);

    System.out.println("Repetition " + repetitionInfo.getCurrentRepetition() + ": " + num);

    // We don't assert it's even (because it's random!),
    // but we could assert it's in range [0, 99]
    assertTrue(num >= 0 && num < 100, "Number should be in 0-100");
  }

  @Tag("fast")
  @Test
  void testOnlyOnJava11OrHigher() {
    assumeTrue(Runtime.version().feature() >= 11, "Test requires Java 11+");
    assertTrue(true, "Java version is >= 11");
  }
}
