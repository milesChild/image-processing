package view;

import java.io.IOException;

/**
 * Implementation of the image view class which offers a primitive method of displaying user
 * commands and their results as strings on the command line. This class will orders from the
 * controller in the form of strings to display to the user.
 */
public class ImageTextViewImpl implements ImageProcessingView {

  private final Appendable out;

  /**
   * Default constructor for the image text view implementation which accepts an appendable to which
   * the results of commands will be displayed.
   * @param out an appendable that is used to display information to the client
   * @throws IllegalArgumentException when the appendable is null
   */
  public ImageTextViewImpl(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    this.out = out;
  }

  /**
   * Method that accepts a string and displays it on the command line to the client.
   * @param message the message to be displayed
   * @throws IOException when an IOException occurs
   */
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

}
