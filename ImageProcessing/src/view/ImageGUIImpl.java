package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ImageGUIImpl extends JFrame implements ActionListener, ItemListener{
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JPanel buttonPanel;

  private JPanel imagePanel;

  private JLabel comboboxDisplay;
  JLabel fileOpenDisplay;

  public ImageGUIImpl() throws IOException {
    setTitle("Process an Image!");
    setSize(300, 400);

    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new GridLayout(1, 2));
    this.mainScrollPane = new JScrollPane(this.mainPanel);
    add(this.mainScrollPane);



    this.imagePanel = new JPanel();
    this.imagePanel.setLayout(new BoxLayout(this.imagePanel, BoxLayout.PAGE_AXIS));

    JLabel picLabel = new JLabel("Image:");
    this.imagePanel.add(picLabel);
    BufferedImage myPicture = ImageIO.read(new File("res/exampleImages/Henock.jpg"));
    JLabel picture = new JLabel(new ImageIcon(myPicture));
    this.imagePanel.add(picture);
    JLabel histogramLabel = new JLabel("Histogram:");
    this.imagePanel.add(histogramLabel);
    this.mainPanel.add(this.imagePanel);

    this.buttonPanel = new JPanel();
    this.buttonPanel.setLayout(new GridLayout(16, 1));

//    JPanel fileopenPanel = new JPanel();
//    fileopenPanel.setLayout(new FlowLayout());
//    JButton fileOpenButton = new JButton("Open a file");
//    fileOpenButton.setActionCommand("load");
//    fileOpenButton.addActionListener(this);
//    fileopenPanel.add(fileOpenButton);
//    this.buttonPanel.add(fileopenPanel);

    JButton loadButton = new JButton("Load Image");
    loadButton.setPreferredSize(new Dimension(50, 30));
    loadButton.setActionCommand("load");
    loadButton.addActionListener(this);
    this.buttonPanel.add(loadButton);

    JButton saveButton = new JButton("Save Image");
    saveButton.setPreferredSize(new Dimension(50,30));
    saveButton.setActionCommand("save");
    this.buttonPanel.add(saveButton);

    JButton brightenButton = new JButton("Brighten");
    brightenButton.setPreferredSize(new Dimension(50, 30));
    brightenButton.setActionCommand("brighten");
    this.buttonPanel.add(brightenButton);

    JButton dimButton = new JButton("Dim");
    dimButton.setPreferredSize(new Dimension(50,30));
    dimButton.setActionCommand("dim");
    this.buttonPanel.add(dimButton);

//    // brighten and dim
//    JPanel typeButtons = new JPanel();
//    typeButtons.setLayout(new BoxLayout(typeButtons, BoxLayout.PAGE_AXIS));
//    JPanel brightenGroup = new JPanel();
//    brightenGroup.setLayout(new BoxLayout(brightenGroup, BoxLayout.LINE_AXIS));
//    JButton brightenButton = new JButton("Brighten by:");
//    brightenButton.setPreferredSize(new Dimension(100, 30));
//    JTextField brightenValue = new JTextField();
//    brightenGroup.add(brightenButton);
//    brightenGroup.add(brightenValue);
//    typeButtons.add(brightenGroup);
//
//    JPanel dimGroup = new JPanel();
//    dimGroup.setLayout(new BoxLayout(dimGroup, BoxLayout.LINE_AXIS));
//    JButton dimButton = new JButton("Dim by:");
//    dimButton.setPreferredSize(new Dimension(100,30));
//    JTextField dimValue = new JTextField();
//    dimValue.setPreferredSize(new Dimension(1,5));
//    dimGroup.add(dimButton);
//    dimGroup.add(dimValue);
//    typeButtons.add(dimGroup);
//    this.buttonPanel.add(typeButtons);

//    // grayscale options
//    JPanel grayscalePanel = new JPanel(new GridLayout(1,1));
    JButton grayscaleButton = new JButton("Grayscale");
    grayscaleButton.setActionCommand("grayscale");
    this.buttonPanel.add(grayscaleButton);
//
//    JPanel grayscaleOptions = new JPanel(new GridLayout(0,1));
//    ButtonGroup group = new ButtonGroup();
//    JRadioButton filter = new JRadioButton("Filter");
//    group.add(filter);
//    grayscaleOptions.add(filter);
//
//    JRadioButton value = new JRadioButton("Value");
//    group.add(value);
//    grayscaleOptions.add(value);
//
//    JRadioButton intensity = new JRadioButton("Intensity");
//    group.add(intensity);
//    grayscaleOptions.add(intensity);
//
//    JRadioButton luma = new JRadioButton("Luma");
//    group.add(luma);
//    grayscaleOptions.add(luma);
//
//    JRadioButton red = new JRadioButton("Red Component");
//    group.add(red);
//    grayscaleOptions.add(red);
//
//    JRadioButton green = new JRadioButton("Green Component");
//    group.add(green);
//    grayscaleOptions.add(green);
//
//    JRadioButton blue = new JRadioButton("Blue Component");
//    group.add(blue);
//    grayscaleOptions.add(blue);
//
//    grayscalePanel.add(grayscaleOptions);
//    this.buttonPanel.add(grayscalePanel);


    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    this.buttonPanel.add(blurButton);

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    this.buttonPanel.add(sharpenButton);

    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    this.buttonPanel.add(sepiaButton);

    this.mainPanel.add(this.buttonPanel);


  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()){
      case "load":
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PPM, JPG, PNG, BMP Images", "jpg", "ppm", "bmp", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ImageGUIImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay = new JLabel();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
        break;
    }

  }

  /**
   * Invoked when an item has been selected or deselected by the user.
   * The code written for this method performs the operations
   * that need to occur when an item is selected (or deselected).
   *
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {

  }
}
