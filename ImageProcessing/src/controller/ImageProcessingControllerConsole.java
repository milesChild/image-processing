package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Dim;
import controller.commands.Downscale;
import controller.commands.Grayscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Primary implementation of the controller interface. This controller class is constructed with
 * an ImageProcessingModel (referenced when conducting operations), a Readable for user input, an
 * ImageProcessingView to transmit information to the user, and a map of known commands.
 */
public class ImageProcessingControllerConsole extends AbstractImageProcessingController {
  private final Readable in;
  private final ImageProcessingView view;
  private final Map<String, Function<String[], ImageProcessingCommand>> knownCommands;

  /**
   * Default constructor for an image processing controller implementation which instantiates a
   * new controller using the provided model, readable, and view.
   *
   * @param model the model that the controller will transmit information to
   * @param in    the readable that the controller will use to accept user input
   * @param view  the view to which information will be transmitted according to the controller's
   *              discretion
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public ImageProcessingControllerConsole(ImageProcessingModel model, Readable in,
                                          ImageProcessingView view) throws IllegalArgumentException {
    super(model);
    if (in == null || view == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }
    this.in = in;
    this.view = view;
    this.knownCommands = new HashMap<>();
    initCommands();
  }

  /**
   * Initializes the known commands that can be consulted to operate on the model.
   */
  private void initCommands() {
    this.knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s[1], s[2]));
    this.knownCommands.put("vertical-flip", s -> new VerticalFlip(s[1], s[2]));
    this.knownCommands.put("brighten", s ->
            new Brighten(Integer.parseInt(s[1]), s[2], s[3]));
    this.knownCommands.put("dim", s -> new Dim(Integer.parseInt(s[1]), s[2], s[3]));
    this.knownCommands.put("grayscale", s -> new Grayscale(this.stringToGrayscaleEnum(s[1]),
            s[2], s[3]));
    this.knownCommands.put("blur", s -> new Blur(s[1], s[2], s[3]));
    this.knownCommands.put("sharpen", s -> new Sharpen(s[1], s[2]));
    this.knownCommands.put("sepia", s -> new Sepia(s[1], s[2]));
    this.knownCommands.put("downscale", s -> new Downscale(Integer.parseInt(s[1]), s[2], s[3]));
  }

  /**
   * Allows the user to run the program and pass in various commands for loading, saving, and
   * manipulating images. User can quit by typing "q" at any time.
   */
  @Override
  public void runProgram() {
    Scanner s = new Scanner(in);
    boolean quit = false;
    String userCommand;
    Function<String[], ImageProcessingCommand> cmd;

    while (s.hasNextLine()) {
      ImageProcessingCommand c;
      String[] in = s.nextLine().split(" ");

      for (String input : in) {
        if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
          quit = true;
          break;
        }
      }

      if (quit) {
        this.quitProgram();
        break;
      }

      try {
        userCommand = in[0];

        if (userCommand.equals("load")) {
          this.loadImage(in[1], in[2]);
        } else if (userCommand.equals("save")) {
          this.saveImage(in[1], in[2]);
        } else {
          cmd = knownCommands.getOrDefault(in[0], null);
          if (cmd == null) {
            this.sendErrorMessage();
          } else {
            try {
              c = cmd.apply(in);
              c.execute(this.model);
            } catch (Exception e) {
              this.sendErrorMessage();
            }
          }
        }
      } catch (IndexOutOfBoundsException e) {
        this.sendErrorMessage();
      }
    }
  }

  /**
   * Attempts to send a message to the view. Helps the runProgram method when notifying the user of
   * an error.
   *
   * @throws IllegalStateException if an error occurs when transmitting the message to the view
   */
  private void sendErrorMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Unknown or invalid command. Try Again."
              + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Converts a user input string to a grayscale type.
   *
   * @param userInput the string user input to be converted into a grayscale type.
   * @return a grayscale type enumeration that matches the user input
   */
  private ImageProcessingModel.GrayscaleTypes stringToGrayscaleEnum(String userInput) {
    switch (userInput) {
      case "value":
        return ImageProcessingModel.GrayscaleTypes.ValueGrayscale;
      case "intensity":
        return ImageProcessingModel.GrayscaleTypes.IntensityGrayscale;
      case "luma":
        return ImageProcessingModel.GrayscaleTypes.LumaGrayscale;
      case "red":
        return ImageProcessingModel.GrayscaleTypes.RedGrayscale;
      case "green":
        return ImageProcessingModel.GrayscaleTypes.GreenGrayscale;
      case "blue":
        return ImageProcessingModel.GrayscaleTypes.BlueGrayscale;
      default:
        return ImageProcessingModel.GrayscaleTypes.TransformationGrayscale;
    }
  }

  /**
   * Renders a message to the view, notifying the user that the program has ended.
   */
  private void quitProgram() {
    try {
      this.view.renderMessage("Program Ended.");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

}
