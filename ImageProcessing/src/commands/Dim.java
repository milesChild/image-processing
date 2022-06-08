package commands;

import model.ImageProcessingModel;

public class Dim extends AbstractCommand {

  private final int value;

  public Dim(int value, String from, String to) {
    super(from, to);
    this.value = value;
  }

  /**
   * Calls upon the model to execute the dim function, giving it the origin image name and the
   * destination for the new image.
   * @param model the model which will be used to actually conduct the dim operation
   */
  @Override
  public void execute(ImageProcessingModel model) {

    model.dim(this.value, this.from, this.to);
  }

}
