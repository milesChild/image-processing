package commands;

import model.ImageProcessingModel;

public class Dim extends AbstractCommand {

  private final int value; // the amount the pixels will be dimmed by

  /**
   * The default constructor for a Dim command, which dims the pixels in a specific image and
   * saves it as a new image.
   * @param value the amount you dim the pixels by in the image
   * @param from the name of the origin image
   * @param to the name for the new image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Dim(int value, String from, String to) {
    super(from, to);
    this.value = value;
  }

  /**
   * Calls upon the model to execute the dim function, giving it the origin image name and the
   * name for the new image.
   * @param model the model which will be used to actually conduct the dim operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.dim(this.value, this.from, this.to);
  }

}
