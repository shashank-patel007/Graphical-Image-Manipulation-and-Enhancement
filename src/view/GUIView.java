package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * The GUIView class represents the graphical user
 * interface for image processing.
 * It extends JFrame and implements the GUIInterface,
 * providing methods to interact with the user.
 */
public class GUIView extends JFrame implements GUIInterface {

  private JLabel imageDisplay;
  private JLabel histogramDisplay;
  private JButton loadButton;
  private JButton blurButton;
  private JButton saveButton;
  private JButton confirmButton;
  private JButton cancelButton;
  private JButton sepiaButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton lumaButton;
  private JButton sharpenButton;
  private JButton compressButton;
  private JButton colorCorrectedButton;
  private JButton adjustLevelsButton;

  /**
   * Constructs a GUIView object, initializing the user interface.
   */
  public GUIView() {
    initializeUI();
  }

  /**
   * Initializes the graphical user interface with image display, histogram, and buttons.
   */
  private void initializeUI() {
    setTitle("Image Processor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel displayPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    JPanel imagePanel = new JPanel(new BorderLayout());
    JLabel imageHeading = new JLabel("Image Display");
    imageHeading.setHorizontalAlignment(JLabel.CENTER);
    imageDisplay = new JLabel();
    imageDisplay.setHorizontalAlignment(JLabel.CENTER);
    imageDisplay.setPreferredSize(new Dimension(900, 600));
    JScrollPane imageScrollPane = new JScrollPane(imageDisplay);
    imagePanel.add(imageHeading, BorderLayout.NORTH);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.75;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    displayPanel.add(imagePanel, c);

    JPanel histogramPanel = new JPanel(new BorderLayout());
    JLabel histogramHeading = new JLabel("Histogram");
    histogramHeading.setHorizontalAlignment(JLabel.CENTER);
    histogramDisplay = new JLabel();
    histogramDisplay.setHorizontalAlignment(JLabel.CENTER);
    histogramDisplay.setPreferredSize(new Dimension(256, 256));
    JScrollPane histogramScrollPane = new JScrollPane(histogramDisplay);
    histogramPanel.add(histogramHeading, BorderLayout.NORTH);
    histogramPanel.add(histogramScrollPane, BorderLayout.CENTER);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.25;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    displayPanel.add(histogramPanel, c);

    displayPanel.setPreferredSize(new Dimension(1000, 500));
    add(displayPanel, BorderLayout.CENTER);

    makeButtons();

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Creates and configures buttons for various image processing operations.
   */
  private void makeButtons() {
    JPanel buttonPanel = new JPanel();
    int numberOfButtons = 16;
    int rows = 2;
    int cols = (int) Math.ceil((double) numberOfButtons / rows);
    buttonPanel.setLayout(new GridLayout(rows, cols));

    loadButton = new JButton("Load");
    buttonPanel.add(loadButton);

    redButton = new JButton("Red");
    buttonPanel.add(redButton);

    greenButton = new JButton("Green");
    buttonPanel.add(greenButton);

    blueButton = new JButton("Blue");
    buttonPanel.add(blueButton);

    blurButton = new JButton("Blur");
    buttonPanel.add(blurButton);

    sepiaButton = new JButton("Sepia");
    buttonPanel.add(sepiaButton);

    lumaButton = new JButton("Luma");
    buttonPanel.add(lumaButton);

    sharpenButton = new JButton("Sharpen");
    buttonPanel.add(sharpenButton);

    horizontalFlipButton = new JButton("Horizontal-Flip");
    buttonPanel.add(horizontalFlipButton);

    verticalFlipButton = new JButton("Vertical-Flip");
    buttonPanel.add(verticalFlipButton);

    compressButton = new JButton("Compress");
    buttonPanel.add(compressButton);

    colorCorrectedButton = new JButton("Color-Corrected");
    buttonPanel.add(colorCorrectedButton);

    adjustLevelsButton = new JButton("Adjust-Levels");
    buttonPanel.add(adjustLevelsButton);

    saveButton = new JButton("Save");
    buttonPanel.add(saveButton);

    confirmButton = new JButton("Confirm");
    confirmButton.setVisible(false);
    buttonPanel.add(confirmButton);

    cancelButton = new JButton("Cancel");
    cancelButton.setVisible(false);
    buttonPanel.add(cancelButton);


    add(buttonPanel, BorderLayout.SOUTH);


    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }


  /**
   * Adds the specified Features object as an ActionListener to various buttons.
   *
   * @param features The Features object to be added as ActionListener.
   */
  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.loadButton());
    blurButton.addActionListener(evt -> features.blurButton());
    sepiaButton.addActionListener(evt -> features.sepiaButton());
    redButton.addActionListener(evt -> features.redButton());
    greenButton.addActionListener(evt -> features.greenButton());
    blueButton.addActionListener(evt -> features.blueButton());
    lumaButton.addActionListener(evt -> features.lumaButton());
    sharpenButton.addActionListener(evt -> features.sharpenButton());
    compressButton.addActionListener(evt -> features.compressButton());
    colorCorrectedButton.addActionListener(evt -> features.colorCorrectedButton());
    adjustLevelsButton.addActionListener(evt -> features.adjustLevelsButton());
    horizontalFlipButton.addActionListener(evt -> features.horizontalFlipButton());
    verticalFlipButton.addActionListener(evt -> features.verticalFlipButton());
    saveButton.addActionListener(evt -> features.saveButton());
    cancelButton.addActionListener(evt -> features.cancelButton());
    confirmButton.addActionListener(evt -> features.confirmButton());
  }


  /**
   * Sets the displayed image in the user interface.
   *
   * @param image The BufferedImage to be displayed.
   */
  @Override
  public void setImage(BufferedImage image) {
    imageDisplay.setIcon(new ImageIcon(image));
  }

  /**
   * Sets the displayed histogram in the user interface.
   *
   * @param histogram The BufferedImage representing the histogram to be displayed.
   */
  @Override
  public void setHistogram(BufferedImage histogram) {
    histogramDisplay.setIcon(new ImageIcon(histogram));
  }

  /**
   * Opens a file dialog for the user to select an image file to load.
   *
   * @return The absolute path of the selected image file, or null if canceled.
   */
  @Override
  public String loadImagePath() {
    JFileChooser fileChooser = new JFileChooser();

    String currentDirectory = System.getProperty("user.dir");
    fileChooser.setCurrentDirectory(new File(currentDirectory));

    fileChooser.setDialogTitle("Open Image File");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
            "jpg", "jpeg", "png", "ppm"));

    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    return null;
  }

  /**
   * Opens a file dialog for the user to specify a location to save an image.
   *
   * @return The absolute path of the file to save the image, or null if canceled.
   */
  @Override
  public String saveImagePath() {
    JFileChooser fileChooser = new JFileChooser();
    String currentDirectory = System.getProperty("user.dir");
    fileChooser.setCurrentDirectory(new File(currentDirectory));
    fileChooser.setDialogTitle("Save Image");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
            "jpg", "jpeg", "png", "ppm"));

    fileChooser.setSelectedFile(new File("Untitled.jpg"));

    if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    File file = fileChooser.getSelectedFile();
    if (!file.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png|ppm)$")) {
      file = new File(file.getAbsolutePath() + ".jpg");
    }
    return file.getAbsolutePath();
  }

  /**
   * Shows or hides the operation controls based on the provided boolean value.
   *
   * @param show True to show the controls, false to hide them.
   */
  @Override
  public void showOperationControls(boolean show) {
    confirmButton.setVisible(show);
    cancelButton.setVisible(show);
    loadButton.setEnabled(!show);
    blurButton.setEnabled(!show);
    saveButton.setEnabled(!show);
    sepiaButton.setEnabled(!show);
    redButton.setEnabled(!show);
    greenButton.setEnabled(!show);
    blueButton.setEnabled(!show);
    horizontalFlipButton.setEnabled(!show);
    verticalFlipButton.setEnabled(!show);
    lumaButton.setEnabled(!show);
    sharpenButton.setEnabled(!show);
    compressButton.setEnabled(!show);
    colorCorrectedButton.setEnabled(!show);
    adjustLevelsButton.setEnabled(!show);
  }

  /**
   * Asks the user to confirm loading a new image and returns the user's choice.
   *
   * @return True if the user confirms, false otherwise.
   */
  @Override
  public boolean confirmLoadButton() {
    int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure? The current image is not saved!",
            "Confirm Load",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    return choice == JOptionPane.YES_OPTION;
  }


  /**
   * Prompts the user for a percentage value and returns it as an Optional.
   *
   * @return An Optional containing the entered percentage
   *         if valid, empty otherwise.
   */
  @Override
  public Optional<Double> promptPercentage() {
    String result = JOptionPane.showInputDialog(this,
            "Specify the percentage of the image on which you"
                    + " would like to apply the effect.\n Enter Percentage: ");
    try {
      Optional<Double> percentage = Optional.ofNullable(result).map(Double::parseDouble);

      if (percentage.isPresent() && (percentage.get() < 0 || percentage.get() > 100)) {
        display("Percentage must be between 0 and 100.");
        return Optional.empty();
      }

      return percentage;
    } catch (NumberFormatException e) {
      display("Invalid percentage format.");
      return Optional.empty();
    }
  }

  /**
   * Prompts the user for a percentage value and returns it as an Optional.
   *
   * @return An Optional containing the entered percentage
   *         if valid, empty otherwise.
   */
  @Override
  public Optional<Double> promptCompressPercentage() {
    String result = JOptionPane.showInputDialog(this,
            "Specify the percentage of compression that you"
                    + " would like to apply on the image.\n Enter Percentage: ");
    try {
      Optional<Double> percentage = Optional.ofNullable(result).map(Double::parseDouble);

      if (percentage.isPresent() && (percentage.get() < 0 || percentage.get() > 100)) {
        display("Percentage must be between 0 and 100.");
        return Optional.empty();
      }

      return percentage;
    } catch (NumberFormatException e) {
      display("Invalid percentage format.");
      return Optional.empty();
    }
  }

  /**
   * Prompts the user for adjustment levels and returns them as an Optional array.
   *
   * @return An Optional containing the entered adjustment
   *         levels if valid, empty otherwise.
   */
  @Override
  public Optional<int[]> promptForAdjustLevels() {
    int b;
    int m;
    int w;

    try {
      String resultB = JOptionPane.showInputDialog(this, "Enter value for b:");
      b = Integer.parseInt(resultB);
      if (b < 0 || b > 255) {
        display("Value of b must be in the range of 0 to 255!");
        return Optional.empty();
      }

      String resultM = JOptionPane.showInputDialog(this, "Enter value for m:");
      m = Integer.parseInt(resultM);
      if (m < 0 || m > 255) {
        display("Value of m must be in the range of 0 to 255!");
        return Optional.empty();
      }

      String resultW = JOptionPane.showInputDialog(this, "Enter value for w:");
      w = Integer.parseInt(resultW);
      if (w < 0 || w > 255) {
        display("Value of w must be in the range of 0 to 255!");
        return Optional.empty();
      }

      if (b >= m || m >= w) {
        display("Values must be in the range 0 to 255 and satisfy b < m < w!");
        return Optional.empty();
      }

      return Optional.of(new int[]{b, m, w});
    } catch (NumberFormatException e) {
      display("Invalid input. Please enter integer values.");
    }
    return Optional.empty();
  }

  /**
   * Retrieves the user command.
   *
   * @return The user command as a String.
   */
  @Override
  public String getCommand() {
    return null;
  }

  /**
   * Displays an error message dialog with the provided message.
   *
   * @param message The error message to be displayed.
   */
  @Override
  public void display(String message) {
    JOptionPane.showMessageDialog(null, message, "Error",
            JOptionPane.ERROR_MESSAGE);
  }
}
