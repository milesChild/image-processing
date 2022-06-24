package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the blurring command, which applies a filter to blur the pixels in a
 * specific image and saves it as a new blurred image.
 */
public class Blur extends AbstractCommand {

  /**
   * The default constructor for a blurring filter command, which calls upon the model to take a
   * current image, apply a blurring filter, and save it as a new image.
   * @param from the name of the origin image
   * @param to the new name for the blurred image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Blur(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  public Blur(String from, String mask, String to) {
    super(from, mask, to);

  }

  /**
   * Calls upon the model to blur the image, giving it the origin image name and the
   * name of the new image.
   * @param model the model which will be used to actually apply the blurring filter
   */
  public void execute(ImageProcessingModel model) {
    if (this.mask == null) {
      model.blur(this.from, this.to);
    } else {
      model.selectiveBlur(this.from, this.mask, this.to);
    }
  }

}
