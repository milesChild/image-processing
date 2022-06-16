package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Dim;
import controller.commands.Grayscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ProcessableImageImpl;
import model.ImageProcessingModel;
import model.Pixel;
import view.ImageProcessingView;

/**
 * Primary implementation of the controller interface. This controller class is constructed with
 * an ImageProcessingModel (referenced when conducting operations), a Readable for user input, an
 * ImageProcessingView to transmit information to the user, and a map of known commands.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {

  private final ImageProcessingModel model;
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
  public ImageProcessingControllerImpl(ImageProcessingModel model, Readable in,
                                       ImageProcessingView view) throws IllegalArgumentException {
    if (model == null || in == null || view == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }
    this.model = model;
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
    this.knownCommands.put("blur", s -> new Blur(s[1], s[2]));
    this.knownCommands.put("sharpen", s -> new Sharpen(s[1], s[2]));
    this.knownCommands.put("grayscaleFilter", s -> new Grayscale(s[1], s[2]));
    this.knownCommands.put("sepia", s -> new Sepia(s[1], s[2]));
  }

  /**
   * Allows the user to run the program and pass in various commands for loading, saving, and
   * manipulating images. User can quit by typing "q" at any time.
   */
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
        }
        else {
          cmd = knownCommands.getOrDefault(in[0], null);
          if (cmd == null) {
            this.sendErrorMessage();
          }
          else {
            try {
              c = cmd.apply(in);
              c.execute(this.model);
            }
            catch (Exception e) {
              this.sendErrorMessage();
            }
          }
        }
      }
      catch (IndexOutOfBoundsException e){
        this.sendErrorMessage();
      }
    }
  }

  /**
   * Attempts to send a message to the view. Helps the runProgram method when notifying the user of
   * an error.
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
        return ImageProcessingModel.GrayscaleTypes.ValueGrayscale;
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

  /**
   * Loads an image into the application given the path of the file as well as a name to refer
   * the file to as. Given the file type, calls the relevant helper methods to load the image.
   * @param path path of the file
   * @param name name of what the image is to be called
   */
  private void loadImage(String path, String name) {
    if (path.endsWith(".ppm")) {
      this.loadPPM(path, name);
    }
    else if (path.endsWith(".jpg") || path.endsWith(".png")
            || path.endsWith(".bmp") || path.endsWith(".jpeg")){
      this.loadCommonImage(path, name);
    }
    else {
      throw new IllegalArgumentException("Path does not end in a valid file type!");
    }

  }

  /**
   * Loads a PPM image into the application given the path of the file and the name that will refer
   * to the file. Reads in the PPM image, creates a new
   * @param path
   * @param name
   */
  private void loadPPM(String path, String name) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + path + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3.");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelGrid[i][j] = new Pixel(r, g, b, maxValue);
      }
    }
    ProcessableImageImpl loadedProcessableImageImpl = new ProcessableImageImpl(pixelGrid, width, height, maxValue);
    this.model.addImage(name, loadedProcessableImageImpl);
  }

  private void loadCommonImage(String path, String name) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(path));
    }
    catch (IOException e){
      throw new IllegalArgumentException("File " + path + " not found!");
    }

    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    int maxValue = 255;

    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(bufferedImage.getRGB(j, i));
        pixelGrid[i][j] = new Pixel(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                maxValue);

      }
    }
    ProcessableImageImpl loadedProcessableImageImpl = new ProcessableImageImpl(pixelGrid, width, height, maxValue);
    this.model.addImage(name, loadedProcessableImageImpl);

  }

  /**
   * Saves this PPM image to a specified path on the device.
   *
   * @param path the device path the PPM image will be saved to
   */
  private void saveImage(String path, String imageName) throws IllegalArgumentException{
    if (path.endsWith(".ppm")) {
      this.saveAsPPM(path, imageName);
    }
    else if (path.endsWith(".jpg") || path.endsWith(".bmp") || path.endsWith(".png")) {
      this.saveAsCommonImage(path, imageName);
    }
    else {
      throw new IllegalArgumentException("Illegal file type in path!");
    }
  }

  private void saveAsPPM(String path, String imageName) {
    File newFile = new File(path);
    String ppmContents = model.getImage(imageName).createPPMContents();

    try {
      FileWriter writer = new FileWriter(newFile);
      newFile.createNewFile();
      writer.write(ppmContents);
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  private void saveAsCommonImage(String path, String imageName) {
    BufferedImage bufferedImage = model.getImage(imageName).createCommonImageContents();
    File newFile = new File(path);

    try {
      ImageIO.write(bufferedImage, path.split("\\.")[1], newFile);
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }

  }

}
