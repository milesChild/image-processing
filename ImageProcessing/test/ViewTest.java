import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageTextView;
import view.ImageTextViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ImageProcessingTextView class & its methods.
 */
public class ViewTest {

  ImageTextView view1;
  Appendable out;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.view1 = new ImageTextViewImpl(out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNullParameter() {
    Appendable testOut = null;
    ImageTextView test = new ImageTextViewImpl(testOut);
  }

  @Test
  public void testValidInitDefaultConstructor() {
    // TODO:
  }

  // test to ensure that the renderMessage() method properly mutates the appendable to be passed
  // to the user.
  @Test
  public void testRenderMessage() throws IOException {
    assertEquals("", this.out.toString());
    this.view1.renderMessage("test");
    assertEquals("test", this.out.toString());
  }

}
