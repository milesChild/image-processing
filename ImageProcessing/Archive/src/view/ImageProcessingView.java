package view;

import java.io.IOException;

/**
 * Interface that will be implemented by the different view types (currently, we only support a
 * simple text viewing system for the client, but we anticipate more intricate view systems for
 * better UX). This interface holds all the methods that are common across all view
 * implementations.
 */
public interface ImageProcessingView {

  /**
   * Method that accepts a string and displays it on the command line to the client.
   * @param message the message to be displayed
   * @throws IOException if transmission to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
