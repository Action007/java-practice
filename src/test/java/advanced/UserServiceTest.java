package advanced;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;
import com.practice.testing.samples.advanced.UserService;

public class UserServiceTest {

  @Test
  void testUserServiceData() {
    UserService userService = new UserService();

    assertAll("Checking user email", () -> {
      assertThat(userService.getActiveUserEmails(), hasSize(3));
    }, () -> {
      assertThat(userService.getActiveUserEmails(), hasItem("bob@test.org"));
    }, () -> {
      assertThat(userService.getActiveUserEmails(), everyItem(matchesPattern(".+@.+\\..+")));
    });

    assertAll("Checking user age", () -> {
      assertThat(userService.getUserAges(), hasKey("Alice"));
    }, () -> {
      assertThat(userService.getUserAges(), hasValue(25));
    }, () -> {
      assertThat(userService.getUserAges().values(), everyItem(greaterThan(20)));
    });
  }
}
