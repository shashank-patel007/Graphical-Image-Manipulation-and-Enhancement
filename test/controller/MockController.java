package controller;

import model.ImageModelInterface;
import view.GUIView;

/**
 * MockController class implements the Features interface and provides mock
 * implementations for various button actions.
 */
public class MockController implements Features {

  GUIView view;
  ImageModelInterface model;

  /**
   * Constructs a MockController with the specified model and view.
   *
   * @param model The ImageModelInterface object.
   * @param view  The GUIView object.
   * @throws IllegalArgumentException if either the model or view is null.
   */
  public MockController(ImageModelInterface model, GUIView view)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View Object is missing!");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model Object is missing!");
    }
    this.view = view;
    this.model = model;
  }

  private static final StringBuilder log = new StringBuilder();

  /**
   * Simulates the action when the "Load" button is pressed.
   * Appends "LoadButton Pressed!" to the log.
   */
  @Override
  public void loadButton() {
    log.append("LoadButton Pressed!");
  }

  /**
   * Simulates the action when the "Blur" button is pressed.
   * Appends "BlurButton Pressed!" to the log.
   */
  @Override
  public void blurButton() {
    log.append("BlurButton Pressed!");
  }

  /**
   * Simulates the action when the "Sepia" button is pressed.
   * Appends "SepiaButton Pressed!" to the log.
   */
  @Override
  public void sepiaButton() {
    log.append("SepiaButton Pressed!");
  }

  /**
   * Simulates the action when the "Luma" button is pressed.
   * Appends "LumaButton Pressed!" to the log.
   */
  @Override
  public void lumaButton() {
    log.append("LumaButton Pressed!");
  }

  /**
   * Simulates the action when the "Red" button is pressed.
   * Appends "RedButton Pressed!" to the log.
   */
  @Override
  public void redButton() {
    log.append("RedButton Pressed!");
  }

  /**
   * Simulates the action when the "Green" button is pressed.
   * Appends "GreenButton Pressed!" to the log.
   */
  @Override
  public void greenButton() {
    log.append("GreenButton Pressed!");
  }

  /**
   * Simulates the action when the "Blue" button is pressed.
   * Appends "BlueButton Pressed!" to the log.
   */
  @Override
  public void blueButton() {
    log.append("BlueButton Pressed!");
  }

  /**
   * Simulates the action when the "Compress" button is pressed.
   * Appends "CompressButton Pressed!" to the log.
   */
  @Override
  public void compressButton() {
    log.append("CompressButton Pressed!");
  }

  /**
   * Simulates the action when the "Adjust Levels" button is pressed.
   * Appends "AdjustLevelsButton Pressed!" to the log.
   */
  @Override
  public void adjustLevelsButton() {
    log.append("AdjustLevelsButton Pressed!");
  }

  /**
   * Simulates the action when the "Color Corrected" button is pressed.
   * Appends "ColorCorrectedButton Pressed!" to the log.
   */
  @Override
  public void colorCorrectedButton() {
    log.append("ColorCorrectedButton Pressed!");
  }

  /**
   * Simulates the action when the "Sharpen" button is pressed.
   * Appends "SharpenButton Pressed!" to the log.
   */
  @Override
  public void sharpenButton() {
    log.append("SharpenButton Pressed!");
  }

  /**
   * Simulates the action when the "Horizontal Flip" button is pressed.
   * Appends "HorizontalFlipButton Pressed!" to the log.
   */
  @Override
  public void horizontalFlipButton() {
    log.append("HorizontalFlipButton Pressed!");
  }

  /**
   * Simulates the action when the "Vertical Flip" button is pressed.
   * Appends "VerticalFlipButton Pressed!" to the log.
   */
  @Override
  public void verticalFlipButton() {
    log.append("VerticalFlipButton Pressed!");
  }

  /**
   * Simulates the action when the "Save" button is pressed.
   * Appends "SaveButton Pressed!" to the log.
   */
  @Override
  public void saveButton() {
    log.append("SaveButton Pressed!");
  }

  /**
   * Simulates the action when the "Cancel" button is pressed.
   * Appends "CancelButton Pressed!" to the log.
   */
  @Override
  public void cancelButton() {
    log.append("CancelButton Pressed!");
  }

  /**
   * Simulates the action when the "Confirm" button is pressed.
   * Appends "ConfirmButton Pressed!" to the log.
   */
  @Override
  public void confirmButton() {
    log.append("ConfirmButton Pressed!");
  }

  /**
   * Clears the log by resetting the StringBuilder.
   */
  public static void clearLog() {
    log.setLength(0);
  }

  /**
   * Gets the log as a string.
   *
   * @return The log string.
   */
  public String getLog() {
    return log.toString();
  }
}
