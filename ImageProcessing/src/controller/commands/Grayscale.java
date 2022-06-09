package controller.commands;

import model.ImageProcessingModel;

public class Grayscale extends AbstractCommand{

  private final ImageProcessingModel.GrayscaleTypes grayscaleChoice;

  /**
   * The abstract implementation for a command to be executed on an image.
   *
   * @param from            the name of the origin image
   * @param to              the name of the new image
   * @param grayscaleChoice
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Grayscale(String from, String to, ImageProcessingModel.GrayscaleTypes grayscaleChoice)
          throws IllegalArgumentException {
    super(from, to);
    this.grayscaleChoice = grayscaleChoice;
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
