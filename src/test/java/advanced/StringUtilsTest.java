package advanced;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import com.practice.testing.samples.advanced.StringUtils;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

  @ParameterizedTest
  @MethodSource("providePalindromes")
  void testPalindromesWithMethodSource(String input, boolean expected) {
    assertEquals(expected, StringUtils.isPalindrome(input));
  }

  @ParameterizedTest
  @CsvSource({"madam, true", "12321, true", "abc, false"})
  void testPalindromesWithCsvSource(String input, boolean expected) {
    assertEquals(expected, StringUtils.isPalindrome(input));
  }

  static Stream<Arguments> providePalindromes() {
    return Stream.of(Arguments.of("racecar", true),
        Arguments.of("A man a plan a canal Panama", true), Arguments.of("hello", false));
  }
}
