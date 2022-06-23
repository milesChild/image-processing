package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import model.PixelImpl;
import model.ProcessableImage;
import model.ProcessableImageImpl;
import view.ImageGUIImpl;
import view.ImageProcessingView;

public class ImageProcessingControllerImplGUI implements ActionListener {
  private ImageProcessingModel model;
  private ImageGUIImpl view;
  private String currentKey;

  public ImageProcessingControllerImplGUI() throws IOException {
    this.model = new ImageProcessingModelImpl();
    this.view = new ImageGUIImpl();
    this.view.setListener(this);
    this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.view.setVisible(true);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()){
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showOpenDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String imageFilePath = f.getAbsolutePath();
          BufferedImage image = this.loadCommonImage(imageFilePath, "initialImage");
          this.currentKey = "initialImage";
          this.view.displayImage(this.convertToBuffered("initialImage"));
        }
        break;
      }
      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String saveFilePath = f.getAbsolutePath();
          this.saveImage(saveFilePath, this.currentKey);
        }
        break;
      }
      case "vertical": {
        this.model.flipVertically(this.currentKey, "verticalFlip");
        this.currentKey = "verticalFlip";
        this.view.displayImage(this.convertToBuffered("verticalFlip"));
        break;
      }
      case "horizontal": {
        this.model.flipHorizontally(this.currentKey, "horizontalFlip");
        this.currentKey = "horizontalFlip";
        this.view.displayImage(this.convertToBuffered("horizontalFlip"));
        break;
      }
      case "blur": {
        this.model.blur(this.currentKey, "blur");
        this.currentKey = "blur";
        this.view.displayImage(this.convertToBuffered("blur"));
        break;
      }
      case "sharpen": {
        this.model.sharpen(this.currentKey, "sharpen");
        this.currentKey = "sharpen";
        this.view.displayImage(this.convertToBuffered("sharpen"));
        break;
      }
      case "sepia": {
        this.model.sepia(this.currentKey, "sepia");
        this.currentKey = "sepia";
        this.view.displayImage(this.convertToBuffered("sepia"));
        break;
      }
      case "greyscale": {
        this.model.grayscale(this.currentKey, "greyscale");
        this.currentKey = "greyscale";
        this.view.displayImage(this.convertToBuffered("greyscale"));
        break;
      }
//      case "greyscaleRed": {
//        this.model.greyScale(this.currentKey, "red", GreyScaleEnum.RED);
//        Image editedImage = this.model.getImage("red");
//        this.currentKey = "red";
//        this.view.displayImage(editedImage);
//        break;
//      }
//      case "greyscaleGreen": {
//        this.model.greyScale(this.currentKey, "green", GreyScaleEnum.GREEN);
//        Image editedImage = this.model.getImage("green");
//        this.currentKey = "green";
//        this.view.displayImage(editedImage);
//        break;
//      }
//      case "greyscaleBlue": {
//        this.model.greyScale(this.currentKey, "blue", GreyScaleEnum.BLUE);
//        Image editedImage = this.model.getImage("blue");
//        this.currentKey = "blue";
//        this.view.displayImage(editedImage);
//        break;
//      }
//      case "greyscaleLuma": {
//        this.model.greyScale(this.currentKey, "luma", GreyScaleEnum.LUMA);
//        Image editedImage = this.model.getImage("luma");
//        this.currentKey = "luma";
//        this.view.displayImage(editedImage);
//        break;
//      }
//      case "greyscaleValue": {
//        this.model.greyScale(this.currentKey, "value", GreyScaleEnum.VALUE);
//        Image editedImage = this.model.getImage("value");
//        this.currentKey = "value";
//        this.view.displayImage(editedImage);
//        break;
//      }
//      case "greyscaleIntensity": {
//        this.model.greyScale(this.currentKey, "intensity", GreyScaleEnum.INTENSITY);
//        Image editedImage = this.model.getImage("intensity");
//        this.currentKey = "intensity";
//        this.view.displayImage(editedImage);
//        break;
//      }
//
//      case "darken": {
//        int increment = this.view.getIncrement();
//        this.model.darken(this.currentKey, increment, "darken");
//        Image editedImage = this.model.getImage("darken");
//        this.currentKey = "darken";
//        this.view.displayImage(editedImage);
//        break;
//      }

      case "brighten": {
        int increment = this.view.getInputValue("brighten");
        this.model.brighten(increment, this.currentKey, "brighten");
        this.currentKey = "brighten";
        this.view.displayImage(this.convertToBuffered("brighten"));
        break;
      }

    }

  }

  /**
   * Loads an image into the application given the path of the file as well as a name to refer
   * the file to as. Given the file type, calls the relevant helper methods to load the image.
   *
   * @param path path of the file
   * @param name name of what the image is to be called
   */
  private void loadImage(String path, String name) {
    if (path.endsWith(".ppm")) {
      this.loadPPM(path, name);
    } else if (path.endsWith(".jpg") || path.endsWith(".png")
            || path.endsWith(".bmp") || path.endsWith(".jpeg")) {
      this.loadCommonImage(path, name);
    } else {
      throw new IllegalArgumentException("Path does not end in a valid file type!");
    }

  }

  /**
   * Loads a PPM image into the application given the path of the file and the name that will refer
   * to the file.  Reads in the image, creates a new processable image, and adds the processable
   * image to the model.
   *
   * @param path the path of the ppm image to be loaded into the application
   * @param name the name the ppm image will be referred to as
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

    PixelImpl[][] pixelGrid = new PixelImpl[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelGrid[i][j] = new PixelImpl(r, g, b, maxValue);
      }
    }
    ProcessableImageImpl loadedProcessableImageImpl =
            new ProcessableImageImpl(pixelGrid, width, height, maxValue);
    this.model.addImage(name, loadedProcessableImageImpl);
  }

  /**
   * Loads a JPEG, JPG, PNG, or BMP image into the application given the path of the file and the
   * name that will refer to the file. Reads in the image, creates a new processable image, and adds
   * the processable image to the model.
   *
   * @param path the path of the ppm image to be loaded into the application
   * @param name the name the ppm image will be referred to as
   */
  private BufferedImage loadCommonImage(String path, String name) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(path));
    } catch (IOException e) {
      throw new IllegalArgumentException("File " + path + " not found!");
    }

    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    int maxValue = 255;

    PixelImpl[][] pixelGrid = new PixelImpl[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(bufferedImage.getRGB(j, i));
        pixelGrid[i][j] = new PixelImpl(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                maxValue);

      }
    }
    ProcessableImage loadedProcessableImage =
            new ProcessableImageImpl(pixelGrid, width, height, maxValue);
    this.model.addImage(name, loadedProcessableImage);

    return bufferedImage;

  }

  /**
   * Saves this processable image to a specified path on the device as the desired image file type.
   * Depending on the file type specified by the user, the actual saving work will be delegated to
   * one of two helpers: {@code saveAsPPM} or {@code saveAsCommonImage}.
   *
   * @param path the device path the processable image will be saved to
   */
  private void saveImage(String path, String imageName) throws IllegalArgumentException {
    if (path.endsWith(".ppm")) {
      this.saveAsPPM(path, imageName);
    } else if (path.endsWith(".jpg") || path.endsWith(".bmp") || path.endsWith(".png")) {
      this.saveAsCommonImage(path, imageName);
    } else {
      throw new IllegalArgumentException("Illegal file type in path!");
    }
  }

  /**
   * Saves this processable image to a specified path on the device as a PPM image file.
   *
   * @param path the device path the processable image will be saved to
   */
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

  /**
   * Saves this processable image to a specified path on the device as an image file of the type
   * specified by the user in the path.
   *
   * @param path the device path the processable image will be saved to
   */
  private void saveAsCommonImage(String path, String imageName) {
    String fileType = path.substring(path.length() - 3);
    BufferedImage bufferedImage = model.getImage(imageName).createCommonImageContents();
    File newFile = new File(path);

    try {
      ImageIO.write(bufferedImage, fileType, newFile);
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }

  }

  private BufferedImage convertToBuffered(String imageName){
    ProcessableImage image = this.model.getImage(imageName);
    return image.createCommonImageContents();
  }


}
