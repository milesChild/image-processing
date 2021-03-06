import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageProcessingView;
import view.ImageProcessingViewConsole;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingTextView class & its methods.
 */
public class ViewTest {
  ImageProcessingView view1;
  Appendable out;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.view1 = new ImageProcessingViewConsole(out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNullParameter() {
    Appendable testOut = null;
    ImageProcessingView test = new ImageProcessingViewConsole(testOut);
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
