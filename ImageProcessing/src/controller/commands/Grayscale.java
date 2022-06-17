package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the grayscale command, which converts the pixels in a specific image
 * to a specified grayscale type and saves it as a new image.
 */
public class Grayscale extends AbstractCommand {

  private final ImageProcessingModel.GrayscaleTypes grayscaleChoice;

  /**
   * The default constructor for a grayscale image manipulation command with a grayscale type.
   * This constructor will be called when the user specifies that they want to apply a certain type
   * of grayscale filter to an image.
   *
   * @param from            the name of the origin image
   * @param to              the name of the new image
   * @param grayscaleChoice the type of grayscale conversion to use
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Grayscale(ImageProcessingModel.GrayscaleTypes grayscaleChoice, String from, String to)
          throws IllegalArgumentException {
    super(from, to);
    this.grayscaleChoice = grayscaleChoice;
  }

  /**
   * The default constructor for a grayscale image manipulation command. This constructor will be
   * called when the user foes not specify any specific grasycaling type and will call on the model
   * to apply a filter to the desired image.
   *
   * @param from the name of the image to apply the grayscale filter to
   * @param to   the name that the new, filtered image will be stored under
   */
  public Grayscale(String from, String to) {
    super(from, to);
    this.grayscaleChoice = ImageProcessingModel.GrayscaleTypes.TransformationGrayscale;
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
    if (grayscaleChoice == ImageProcessingModel.GrayscaleTypes.TransformationGrayscale) {
      model.grayscale(from, to);
    } else {
      model.grayscale(grayscaleChoice, from, to);
    }

  }
}
