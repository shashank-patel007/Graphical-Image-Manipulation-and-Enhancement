package view;

import java.awt.image.BufferedImage;
import java.util.Optional;

import controller.Features;

/**
 * The GUIInterface represents the graphical user
 * interface for image manipulation. It extends the
 * ImageViewInterface and provides methods for interaction
 * and input from the user.
 */
public interface GUIInterface extends ImageViewInterface {

  /**
   * Adds the specified Features implementation
   * to the graphical user interface.
   *
   * @param features The Features implementation to add.
   */
  void addFeatures(Features features);

  /**
   * Sets the displayed image on the graphical user interface.
   *
   * @param image The BufferedImage to be displayed.
   */
  void setImage(BufferedImage image);

  /**
   * Sets the displayed histogram image on
   * the graphical user interface.
   *
   * @param histogram The BufferedImage representing the histogram.
   */
  void setHistogram(BufferedImage histogram);

  /**
   * Prompts the user for the path to load an
   * image from and returns the path as a string.
   *
   * @return The path to load the image from.
   */
  String loadImagePath();

  /**
   * Prompts the user for the path to save the
   * current image and returns the path as a string.
   *
   * @return The path to save the image to.
   */
  String saveImagePath();

  /**
   * Shows or hides the operation controls on the
   * graphical user interface.
   *
   * @param show True to show the operation controls, false to hide them.
   */
  void showOperationControls(boolean show);

  /**
   * Confirms the user's intention to load a new image.
   * Returns true if confirmed, false otherwise.
   *
   * @return True if the user confirms, false otherwise.
   */
  boolean confirmLoadButton();

  /**
   * Prompts the user for a percentage value and returns
   * it as an Optional Double.
   *
   * @return The percentage value as an Optional Double.
   */
  Optional<Double> promptPercentage();

  /**
   * Prompts the user for a percentage value and returns
   * it as an Optional Double for compression button.
   *
   * @return The percentage value as an Optional Double.
   */
  Optional<Double> promptCompressPercentage();

  /**
   * Prompts the user for adjustment levels and returns
   * them as an Optional array of integers.
   *
   * @return The adjustment levels as an Optional array of integers.
   */
  Optional<int[]> promptForAdjustLevels();
}
