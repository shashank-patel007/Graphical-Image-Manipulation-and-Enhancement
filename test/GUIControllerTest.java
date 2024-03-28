import org.junit.Before;
import org.junit.Test;


import controller.GUIController;
import model.ImageModelInterface;
import model.MockModel;
import view.GUIInterface;
import view.MockView;

import static org.junit.Assert.assertEquals;


/**
 * JUnit test class for the GUIController class.
 */
public class GUIControllerTest {

  private GUIController controller;

  /**
   * Set up the test environment by creating instances of {@link MockModel},
   * MockView, and GUIController.
   */
  @Before
  public void setUp() {
    ImageModelInterface model = new MockModel();
    GUIInterface mockView = new MockView();
    controller = new GUIController(mockView, model);
    mockView.addFeatures(controller);
    MockView.clearLog();
  }

  /**
   * testing LoadButton.
   */
  @Test
  public void testLoadButton() {
    controller.loadButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n" +
            "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * testing LoadButton with confirmationPrompt.
   */
  @Test
  public void testLoadConfirmationButton() {
    controller.loadButton();
    controller.loadButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "confirmLoadButton method is invoked!\n"
            + "loadImagePath method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing SaveButton.
   */
  @Test
  public void testSaveButton() {
    controller.loadButton();
    controller.saveButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "saveImagePath method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing BlurButton.
   */
  @Test
  public void testBlurButton() {
    controller.loadButton();
    controller.blurButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing BlurButton then LoadButton.
   */
  @Test
  public void testBlurThenLoadButton() {
    controller.loadButton();
    controller.blurButton();
    controller.loadButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n"
            + "confirmLoadButton method is invoked!\n"
            + "loadImagePath method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing sepiaButton.
   */
  @Test
  public void testSepiaButton() {
    controller.loadButton();
    controller.sepiaButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing lumaButton.
   */
  @Test
  public void testLumaButton() {
    controller.loadButton();
    controller.lumaButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing redButton.
   */
  @Test
  public void testRedButton() {
    controller.loadButton();
    controller.redButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing greenButton.
   */
  @Test
  public void testGreenButton() {
    controller.loadButton();
    controller.greenButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing blueButton.
   */
  @Test
  public void testBlueButton() {
    controller.loadButton();
    controller.greenButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing compressButton.
   */
  @Test
  public void testCompressButton() {
    controller.loadButton();
    controller.compressButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptCompressPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing adjustLevelsButton.
   */
  @Test
  public void testAdjustLevelsButton() {
    controller.loadButton();
    controller.adjustLevelsButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptForAdjustLevels method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing colorCorrectionButton.
   */
  @Test
  public void testColorCorrectedButton() {
    controller.loadButton();
    controller.colorCorrectedButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing sharpenButton.
   */
  @Test
  public void testSharpenButton() {
    controller.loadButton();
    controller.sharpenButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "promptPercentage method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing horizontalFlipButton.
   */
  @Test
  public void testHorizontalFlipButton() {
    controller.loadButton();
    controller.horizontalFlipButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

  /**
   * Testing verticalFlipButton.
   */
  @Test
  public void testVerticalFlipButton() {
    controller.loadButton();
    controller.verticalFlipButton();
    String res = MockView.getLog();

    String expectedResult = "loadImagePath method is invoked!\n"
            + "display method is invoked!\n"
            + "display method is invoked!\n";

    assertEquals(expectedResult, res);
  }

}