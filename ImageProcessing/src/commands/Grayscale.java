package commands;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

public class Grayscale extends AbstractCommand{

  ImageProcessingModelImpl.GrayscaleTypes grayscaleChoice;

  /**
   * The abstract implementation for a command to be executed on an image.
   *
   * @param from the name of the origin image
   * @param to   the name of the new image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Grayscale(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to execute the specified function (decided based on the actual
   * implementation of the abstract command), giving it the origin image
   * name and the destination for the new image.
   *
   * @param model the model which will be used to actually conduct the specified operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.grayscale(grayscaleChoice, from, to);
  }
}
