package commands;

import model.ImageProcessingModel;

public class Brighten extends AbstractCommand {

  private int value;

  public Brighten(int value, String from, String to) {
    super(from, to);
    this.value = value;
  }

  /**
   * Calls upon the model to execute the brighten function, giving it the origin image name and the
   * destination for the new image.
   * @param model the model which will be used to actually conduct the brighten operation
   */
  @Override
  public void execute(ImageProcessingModel model) {

    model.brighten(this.value, this.from, this.to);
  }

}
