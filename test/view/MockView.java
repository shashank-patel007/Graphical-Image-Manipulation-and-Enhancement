package view;

import java.awt.image.BufferedImage;
import java.util.Optional;

import controller.Features;

/**
 * The MockView class implements the GUIInterface
 * and serves as a mock or testing view
 * for the image processing application. It logs
 * method invocations for testing and
 * debugging purposes and provides a log of the method calls.
 */
public class MockView implements GUIInterface {
  private static final StringBuilder log = new StringBuilder();

  /**
   * Logs the invocation of the "addFeatures" method.
   *
   * @param features The Features object to be added.
   */
  @Override
  public void addFeatures(Features features) {
    log.append("addFeatures is invoked!\n");
  }

  /**
   * Logs the invocation of the "setImage" method.
   *
   * @param image The BufferedImage object to be set.
   */
  @Override
  public void setImage(BufferedImage image) {
    log.append("setImage method is invoked!\n");
  }

  /**
   * Logs the invocation of the "setHistogram" method.
   *
   * @param histogram The BufferedImage object representing
   *                  the histogram to be set.
   */
  @Override
  public void setHistogram(BufferedImage histogram) {
    log.append("setHistogram method is invoked!\n");
  }

  /**
   * Logs the invocation of the "loadImagePath" method.
   *
   * @return A String representing the path to the loaded image.
   */
  @Override
  public String loadImagePath() {
    log.append("loadImagePath method is invoked!\n");
    return "res\\nyc.jpg";
  }

  /**
   * Logs the invocation of the "saveImagePath" method.
   *
   * @return A String representing the path to save the image.
   */
  @Override
  public String saveImagePath() {
    log.append("saveImagePath method is invoked!\n");
    return "temp";
  }

  /**
   * Logs the invocation of the "showOperationControls" method.
   *
   * @param show A boolean indicating whether to show
   *             or hide operation controls.
   */
  @Override
  public void showOperationControls(boolean show) {
    log.append("showOperationControls method is invoked!\n");
  }

  /**
   * Logs the invocation of the "confirmLoadButton" method.
   *
   * @return A boolean indicating the confirmation of the load button.
   */
  @Override
  public boolean confirmLoadButton() {
    log.append("confirmLoadButton method is invoked!\n");
    return true;
  }

  /**
   * Logs the invocation of the "promptPercentage" method.
   *
   * @return An Optional containing a Double representing
   *         the prompted percentage.
   */
  @Override
  public Optional<Double> promptPercentage() {
    log.append("promptPercentage method is invoked!\n");
    return Optional.of(20.5);
  }

  /**
   * Prompts the user for a percentage value and returns
   * it as an Optional Double for compression button.
   *
   * @return The percentage value as an Optional Double.
   */
  @Override
  public Optional<Double> promptCompressPercentage() {
    log.append("promptCompressPercentage method is invoked!\n");
    return Optional.of(30.4);
  }

  /**
   * Logs the invocation of the "promptForAdjustLevels" method.
   *
   * @return An Optional containing an int array representing
   *         the prompted levels.
   */
  @Override
  public Optional<int[]> promptForAdjustLevels() {
    log.append("promptForAdjustLevels method is invoked!\n");
    return Optional.of(new int[]{10, 20, 30});
  }

  /**
   * Logs the invocation of the "getCommand" method.
   *
   * @return A String representing the retrieved command.
   */
  @Override
  public String getCommand() {
    log.append("getCommand method is invoked!\n");
    return null;
  }

  /**
   * Logs the invocation of the "display" method.
   *
   * @param message A String representing the message to be displayed.
   */
  @Override
  public void display(String message) {
    log.append("display method is invoked!\n");
  }

  /**
   * Retrieves the log of method invocations.
   *
   * @return A String containing the log of method invocations.
   */
  public static String getLog() {
    return log.toString();
  }

  /**
   * Clears the log of method invocations.
   */
  public static void clearLog() {
    log.setLength(0);
  }
}
