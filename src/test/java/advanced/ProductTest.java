package advanced;

import org.junit.jupiter.api.Test;
import com.practice.testing.samples.advanced.Product;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ProductTest {
  @Test
  void testProductDetails() {
    Product product = new Product("Laptop", 1200.0, "Electronics");

    assertAll("should be true", () -> {
      assertThat(product.getName(), is("Laptop"));
    }, () -> {
      assertThat(product.getPrice(), greaterThan(1000.0));
    }, () -> {
      assertThat(product.getCategory(), containsString("Electro"));
    });
  }
}
