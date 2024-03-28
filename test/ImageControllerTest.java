import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ImageController;
import controller.ImageControllerInterface;
import model.MockModel;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class conducts testing to validate the controller's functionality,
 * ensuring it handles user requests, imageModel logic, and interactions with other
 * components effectively with the help of MockModel. MockModel is used to make sure
 * inputs and method invocation works correctly.
 */
public class ImageControllerTest {

  private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();

  private final PrintStream out = System.out;


  /**
   * Redirects System to capture console output during testing.
   */
  @Before
  public void setup() {
    System.setOut(new PrintStream(outResult));
  }

  /**
   * Restores the original System configuration after testing.
   */
  @After
  public void reset() {
    System.setOut(out);
  }

  /**
   * Test redComponentCommand.
   */
  @Test
  public void testControllerRedComponentMethodMock() {
    String inputData = "red-component testNYC testRed\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "red-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("redComponentCommand method is invoked!"));
  }

  /**
   * Test greenComponentCommand.
   */
  @Test
  public void testControllerGreenComponentMethodMock() {
    String inputData = "green-component testNYC testGreen\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "green-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("greenComponentCommand method is invoked!"));
  }

  /**
   * Test blueComponentCommand.
   */
  @Test
  public void testControllerBlueComponentMethodMock() {
    String inputData = "blue-component testNYC testBlue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "blue-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("blueComponentCommand method is invoked!"));
  }

  /**
   * Test valueComponentCommand.
   */
  @Test
  public void testControllerValueComponentMethodMock() {
    String inputData = "value-component testNYC testValue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "value-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("valueComponentCommand method is invoked!"));
  }

  /**
   * Test intensityComponentCommand.
   */
  @Test
  public void testControllerIntensityComponentMethodMock() {
    String inputData = "intensity-component testNYC testIntensity\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "intensity-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("intensityComponentCommand method is invoked!"));
  }

  /**
   * Test lumaComponentCommand.
   */
  @Test
  public void testControllerLumaComponentMethodMock() {
    String inputData = "luma-component testNYC testLuma\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "luma-component executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("lumaComponentCommand method is invoked!"));
  }


  /**
   * Test brightenComponentCommand.
   */
  @Test
  public void testControllerBrightenMethodMock() {
    String inputData = "brighten 30 testNYC testBrighten\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "brighten executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("brightenCommand method is invoked!"));
  }

  /**
   * Test brightenCommand Neg30.
   */
  @Test
  public void testControllerNegBrightenMethodMock() {
    String inputData = "brighten -30 testNYC testBrighten\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "brighten executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("brightenCommand method is invoked!"));
  }

  /**
   * Test blurCommand Neg30.
   */
  @Test
  public void testControllerBlurMethodMock() {
    String inputData = "blur testNYC testBlur\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "blur executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("blurCommand method is invoked!"));
  }


  /**
   * Test sepiaCommand.
   */
  @Test
  public void testControllerSepiaMethodMock() {
    String inputData = "sepia testNYC testSepia\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "sepia executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("sepiaCommand method is invoked!"));
  }


  /**
   * Test combineCommand.
   */
  @Test
  public void testControllerRGBCombineMethodMock() {
    String inputData = "rgb-combine testCombineNYC testRed testGreen testBlue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "rgb-combine executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("combineCommand method is invoked!"));
  }


  /**
   * Test sharpenCommand.
   */
  @Test
  public void testControllerSharpenMethodMock() {
    String inputData = "sharpen testSharpenNYC testBlue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "sharpen executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("sharpenCommand method is invoked!"));
  }

  /**
   * Test verticalFlipCommand.
   */
  @Test
  public void testControllerVerticalFlipMethodMock() {
    String inputData = "vertical-flip testSharpenNYC testBlue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "vertical-flip executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("verticalFlipCommand method is invoked!"));
  }

  /**
   * Test horizontalFlipCommand.
   */
  @Test
  public void testControllerHorizontalFlipMethodMock() {
    String inputData = "horizontal-flip testSharpenNYC testBlue\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "horizontal-flip executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("horizontalFlipCommand method is invoked!"));
  }


  /**
   * Test invalid command enter.
   */
  @Test
  public void testIncorrectCommand() {
    String inputData = "orange-component testNYC testOrange\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult = null;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
      expectedResult = "Invalid Input: orange-component" + System.lineSeparator() +
              "error executing orange-component";
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
  }

  /**
   * Test when view = null passed as parameter.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testViewNull() {
    String inputData = "red-component testNYC testRed\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = null;
    MockModel model = new MockModel();
    new ImageController(view, model);
    fail("this test should have failed!");
  }

  /**
   * Test when model = null passed as parameter.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testModelNull() {
    String inputData = "red-component testNYC testRed\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = null;
    new ImageController(view, model);
    fail("this test should have failed!");
  }


  /**
   * Test runCommand.
   */
  @Test
  public void testControllerRunCommand() {
    String inputData = "run res\\runCommand.txt\nq";

    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    ImageControllerInterface controller = new ImageController(view, model);

    controller.process();
    String expectedResult = "load executed successfully" + System.lineSeparator()
            + "sepia executed successfully" + System.lineSeparator()
            + "run executed successfully";

    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());

  }

  /**
   * Test runCommand.
   */
  @Test
  public void testControllerIncorrectFilePathRunCommand() {
    String inputData = "run test\\put.txt\nq";

    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    ImageControllerInterface controller = new ImageController(view, model);

    controller.process();
    String expectedResult = "File not Found!" + System.lineSeparator() +
            "error executing run";

    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());

  }


  /**
   * Test loadCommand.
   */
  @Test
  public void testControllerLoadMethodMock() {
    String inputData = "load test\\res\\nyc.png testNYC\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "load executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("AddImage method is invoked!"));
  }

  /**
   * Test compressCommand.
   */
  @Test
  public void testControllerCompressMethodMock() {
    String inputData = "compress 50 test\\res\\nyc.png testNYC\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "compress executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("compress method is invoked!"));
  }


  /**
   * test colorCorrectionMethod invoked.
   */
  @Test
  public void testColorCorrectionMethodInvoked() {
    String inputData = "color-correct testNYC testNYC-color-correct\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }

    expectedResult = "color-correct executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("color correction method is invoked!"));
  }

  /**
   * test levelAdjustments invoked.
   */
  @Test
  public void testLevelAdjustmentsMethodInvoked() {
    String inputData = "levels-adjust 20 100 255 testNYC testNYC-levels-adjust\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "levels-adjust executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("levels adjustment method is invoked!"));
  }

  /**
   * test compressImageMethod invoked.
   */
  @Test
  public void testCompressImageMethod() {
    String inputData = "compress 50 testNYC testNYC-levels-adjust\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "compress executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("compress method is invoked!"));
  }

  /**
   * test histogramMethod invoked.
   */
  @Test
  public void testHistogramMethod() {
    String inputData = "histogram testNYC testNYC-levels-adjust\nq";
    ByteArrayInputStream inContent = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(inContent);

    ImageViewInterface view = new ImageView();
    MockModel model = new MockModel();
    String expectedResult;
    ImageControllerInterface controller = new ImageController(view, model);
    try {
      controller.process();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
    expectedResult = "histogram executed successfully";
    assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    assertTrue(model.getLog().contains("histogram method is invoked!"));
  }
}