import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ImageProcessingCommand;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingController class & its methods.
 */
public class ControllerTest {

  private ImageProcessingModel model1;
  private ImageProcessingView view1;
  private Readable in;
  private Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;
  private ImageProcessingController controller1;

  @Before
  public void init() {
    //this.controller1 = new ImageProcessingControllerImpl();
  }

  @Test
  public void testValidInit() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullModel() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullReadable() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullView() {

  }

  // testing runProgram

}
