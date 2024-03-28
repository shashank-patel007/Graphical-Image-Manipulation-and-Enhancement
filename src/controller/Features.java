package controller;

/**
 * The Features interface provides a contract for the different
 * functionalities that can be triggered through
 * user interface elements, such as buttons, in the
 * image processing application.
 */
public interface Features {

  /**
   * Initiates the process of loading an image.
   */
  void loadButton();

  /**
   * Initiates the process of applying a blur
   * effect to the loaded image.
   */
  void blurButton();

  /**
   * Initiates the process of applying a sepia
   * tone effect to the loaded image.
   */
  void sepiaButton();

  /**
   * Initiates the process of applying a luma
   * component effect to the loaded image.
   */
  void lumaButton();

  /**
   * Initiates the process of applying a red
   * component effect to the loaded image.
   */
  void redButton();

  /**
   * Initiates the process of applying a green
   * component effect to the loaded image.
   */
  void greenButton();

  /**
   * Initiates the process of applying a blue
   * component effect to the loaded image.
   */
  void blueButton();

  /**
   * Initiates the process of compressing
   * the loaded image.
   */
  void compressButton();

  /**
   * Initiates the process of adjusting levels
   * in the loaded image.
   */
  void adjustLevelsButton();

  /**
   * Initiates the process of applying color correction
   * to the loaded image.
   */
  void colorCorrectedButton();

  /**
   * Initiates the process of applying sharpening
   * to the loaded image.
   */
  void sharpenButton();

  /**
   * Initiates the process of horizontally flipping
   * the loaded image.
   */
  void horizontalFlipButton();

  /**
   * Initiates the process of vertically flipping
   * the loaded image.
   */
  void verticalFlipButton();

  /**
   * Initiates the process of saving the current
   * state of the image.
   */
  void saveButton();

  /**
   * Initiates the process of canceling the
   * current operation.
   */
  void cancelButton();

  /**
   * Initiates the process of confirming and
   * finalizing the current operation.
   */
  void confirmButton();

}
