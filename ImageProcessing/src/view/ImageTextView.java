package view;

import java.io.IOException;

/**
 * Interface for a primitive text-view feature that allows the client to view text-formatted results
 * of their commands to the program. Accepts commands from the controller in the form of strings to
 * be relayed to the user on the command line.
 */
public interface ImageTextView extends ImageProcessingView {

  // TODO: Make IOException more elaborate description
  /**
   * Method that accepts a string and displays it on the command line to the client.
   * @param message the message to be displayed
   * @throws IOException when an IOException occurs
   */
  void renderMessage(String message) throws IOException;

}
