import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

import controller.ImageController;
import controller.ImageControllerInterface;
import model.Image;
import model.ImageModel;
import model.ImageModelInterface;
import model.Pixel;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * This class conducts end-to-end testing to validate the controller's functionality,
 * ensuring it handles user requests, imageModel logic, and interactions with other
 * components effectively.
 */
public class ImageControllerCompleteTest {

  private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private ByteArrayInputStream inResult;
  private final String imagePath = "test\\res\\controllerTest.png";
  private final String savePath = "test\\res\\saveTestImage.png";
  private final String lineSeparator = System.lineSeparator();

  /**
   * Sets up the testing environment.
   *
   * @throws IOException if there's an issue creating the image.
   */
  @Before
  public void setup() throws IOException {
    outResult.reset();
    System.setOut(new PrintStream(outResult));
    createImage();
  }

  /**
   * Resets the testing environment after completion.
   */
  @After
  public void reset() {
    System.setOut(originalOut);
    System.setIn(inResult);
    new File(imagePath).delete();
    new File(savePath).delete();
  }

  /**
   * creates 3x3 image for testing.
   *
   * @throws IOException if file not found.
   */
  private void createImage() throws IOException {
    BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
    Pixel[][] pixelArray = new Pixel[3][3];

    pixelArray[0][0] = new Pixel(150, 100, 0);
    pixelArray[0][1] = new Pixel(0, 120, 180);
    pixelArray[0][2] = new Pixel(250, 0, 255);

    pixelArray[1][0] = new Pixel(0, 0, 0);
    pixelArray[1][1] = new Pixel(255, 255, 255);
    pixelArray[1][2] = new Pixel(10, 100, 200);

    pixelArray[2][0] = new Pixel(230, 130, 100);
    pixelArray[2][1] = new Pixel(125, 190, 0);
    pixelArray[2][2] = new Pixel(75, 20, 210);

    for (int x = 0; x < pixelArray.length; x++) {
      for (int y = 0; y < pixelArray[x].length; y++) {
        int rgb = (pixelArray[x][y].getRed() << 16) |
                (pixelArray[x][y].getGreen() << 8) | pixelArray[x][y].getBlue();
        image.setRGB(x, y, rgb);
      }
    }

    File outputFile = new File("test\\res\\controllerTest.png");
    ImageIO.write(image, "png", outputFile);
  }

  /**
   * Simulates user input by setting the standard input stream to a provided input string.
   *
   * @param input The input string to be simulated as user input.
   */
  private void simulateUserInput(String input) {
    inResult = new ByteArrayInputStream(input.getBytes());
    System.setIn(inResult);
  }

  /**
   * test loadCommand and saveCommand works correctly.
   */
  @Test
  public void testLoadSaveCommand() {
    String destinationImage = "testImage";
    String inputData = "load " + this.imagePath + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;


      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 100, 0);
      expectedArray[0][1] = new Pixel(0, 120, 180);
      expectedArray[0][2] = new Pixel(250, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test loadCommand and saveCommand works correctly.
   */
  @Test
  public void testEmptyPathSaveCommand() {
    String path = null;
    String destinationImage = "testImage";
    String inputData = "load " + this.imagePath + " " + destinationImage + "\n"
            + "save " + path + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      String expectedResult = "load executed successfully" + lineSeparator +
              "error executing save";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test redComponent.
   */
  @Test
  public void testRedComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testRedImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "red-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;


      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 0, 0);
      expectedArray[0][1] = new Pixel(0, 0, 0);
      expectedArray[0][2] = new Pixel(250, 0, 0);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 0, 0);
      expectedArray[1][2] = new Pixel(10, 0, 0);

      expectedArray[2][0] = new Pixel(230, 0, 0);
      expectedArray[2][1] = new Pixel(125, 0, 0);
      expectedArray[2][2] = new Pixel(75, 0, 0);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "red-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test greenComponent.
   */
  @Test
  public void testGreenComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testGreenImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "green-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(0, 100, 0);
      expectedArray[0][1] = new Pixel(0, 120, 0);
      expectedArray[0][2] = new Pixel(0, 0, 0);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(0, 255, 0);
      expectedArray[1][2] = new Pixel(0, 100, 0);

      expectedArray[2][0] = new Pixel(0, 130, 0);
      expectedArray[2][1] = new Pixel(0, 190, 0);
      expectedArray[2][2] = new Pixel(0, 20, 0);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "green-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test blueComponent.
   */
  @Test
  public void testBlueComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testBlueImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "blue-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(0, 0, 0);
      expectedArray[0][1] = new Pixel(0, 0, 180);
      expectedArray[0][2] = new Pixel(0, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(0, 0, 255);
      expectedArray[1][2] = new Pixel(0, 0, 200);

      expectedArray[2][0] = new Pixel(0, 0, 100);
      expectedArray[2][1] = new Pixel(0, 0, 0);
      expectedArray[2][2] = new Pixel(0, 0, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "blue-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test valueComponent.
   */
  @Test
  public void testValueComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testValueImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "value-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 150, 150);
      expectedArray[0][1] = new Pixel(180, 180, 180);
      expectedArray[0][2] = new Pixel(255, 255, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(200, 200, 200);

      expectedArray[2][0] = new Pixel(230, 230, 230);
      expectedArray[2][1] = new Pixel(190, 190, 190);
      expectedArray[2][2] = new Pixel(210, 210, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "value-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test valueComponent with split percentage.
   */
  @Test
  public void testValueComponentCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testValueImage";
    double percentage = 50.0;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "value-component" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 150, 150);
      expectedArray[0][1] = new Pixel(180, 180, 180);
      expectedArray[0][2] = new Pixel(255, 255, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "value-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test intensityComponent.
   */
  @Test
  public void testIntensityComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testValueImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "intensity-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(83, 83, 83);
      expectedArray[0][1] = new Pixel(100, 100, 100);
      expectedArray[0][2] = new Pixel(168, 168, 168);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(103, 103, 103);

      expectedArray[2][0] = new Pixel(153, 153, 153);
      expectedArray[2][1] = new Pixel(105, 105, 105);
      expectedArray[2][2] = new Pixel(101, 101, 101);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "intensity-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test intensityComponent with split percentage.
   */
  @Test
  public void testIntensityComponentCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testIntensityImage";
    double percentage = 40.5;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "intensity-component" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(83, 83, 83);
      expectedArray[0][1] = new Pixel(100, 100, 100);
      expectedArray[0][2] = new Pixel(168, 168, 168);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "intensity-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test lumaComponent.
   */
  @Test
  public void testLumaComponentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testLumaImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "luma-component" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(104, 104, 104);
      expectedArray[0][1] = new Pixel(99, 99, 99);
      expectedArray[0][2] = new Pixel(71, 71, 71);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(254, 254, 254);
      expectedArray[1][2] = new Pixel(88, 88, 88);

      expectedArray[2][0] = new Pixel(149, 149, 149);
      expectedArray[2][1] = new Pixel(163, 163, 163);
      expectedArray[2][2] = new Pixel(45, 45, 45);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "luma-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test lumaComponent with split percentage.
   */
  @Test
  public void testLumaComponentCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testLumaImage";
    double percentage = 20.5;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "luma-component" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 100, 0);
      expectedArray[0][1] = new Pixel(0, 120, 180);
      expectedArray[0][2] = new Pixel(250, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "luma-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test brightenCommand.
   */
  @Test
  public void testBrighten50Command() {
    String interMediateImage = "testImage";
    String destinationImage = "testLumaImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "brighten 50" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(200, 150, 50);
      expectedArray[0][1] = new Pixel(50, 170, 230);
      expectedArray[0][2] = new Pixel(255, 50, 255);

      expectedArray[1][0] = new Pixel(50, 50, 50);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(60, 150, 250);

      expectedArray[2][0] = new Pixel(255, 180, 150);
      expectedArray[2][1] = new Pixel(175, 240, 50);
      expectedArray[2][2] = new Pixel(125, 70, 260);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "brighten executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test brightenCommand.
   */
  @Test
  public void testBrightenNeg50Command() {
    String interMediateImage = "testImage";
    String destinationImage = "testLumaImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "brighten -50" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(100, 50, 0);
      expectedArray[0][1] = new Pixel(0, 70, 130);
      expectedArray[0][2] = new Pixel(200, 0, 205);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(205, 205, 205);
      expectedArray[1][2] = new Pixel(0, 50, 150);

      expectedArray[2][0] = new Pixel(180, 80, 50);
      expectedArray[2][1] = new Pixel(75, 140, 0);
      expectedArray[2][2] = new Pixel(25, 0, 160);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "brighten executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test combineCommand.
   */
  @Test
  public void testCombineCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testLumaImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "red-component" + " " + interMediateImage + " " + "redImage" + "\n"
            + "green-component" + " " + interMediateImage + " " + "greenImage" + "\n"
            + "blue-component" + " " + interMediateImage + " " + "blueImage" + "\n"
            + "rgb-combine" + " " + destinationImage + " " + "redImage greenImage blueImage\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 100, 0);
      expectedArray[0][1] = new Pixel(0, 120, 180);
      expectedArray[0][2] = new Pixel(250, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "red-component executed successfully" + lineSeparator +
              "green-component executed successfully" + lineSeparator +
              "blue-component executed successfully" + lineSeparator +
              "rgb-combine executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test blurCommand.
   */
  @Test
  public void testBlurCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testBlurImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "blur" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(53, 56, 38);
      expectedArray[0][1] = new Pixel(83, 81, 121);
      expectedArray[0][2] = new Pixel(80, 43, 127);

      expectedArray[1][0] = new Pixel(87, 80, 56);
      expectedArray[1][1] = new Pixel(125, 131, 147);
      expectedArray[1][2] = new Pixel(83, 79, 151);

      expectedArray[2][0] = new Pixel(89, 72, 41);
      expectedArray[2][1] = new Pixel(102, 104, 83);
      expectedArray[2][2] = new Pixel(52, 57, 93);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "blur executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test blur with split percentage.
   */
  @Test
  public void testBlurCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testBlurImage";
    double percentage = 70.3;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "blur" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(53, 56, 38);
      expectedArray[0][1] = new Pixel(83, 81, 121);
      expectedArray[0][2] = new Pixel(80, 43, 127);

      expectedArray[1][0] = new Pixel(87, 80, 56);
      expectedArray[1][1] = new Pixel(125, 131, 147);
      expectedArray[1][2] = new Pixel(83, 79, 151);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "blur executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test sharpenCommand.
   */
  @Test
  public void testSharpenCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testSharpenImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "sharpen" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(165, 163, 0);
      expectedArray[0][1] = new Pixel(174, 186, 255);
      expectedArray[0][2] = new Pixel(219, 9, 255);

      expectedArray[1][0] = new Pixel(15, 64, 13);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(64, 139, 255);

      expectedArray[2][0] = new Pixel(255, 199, 58);
      expectedArray[2][1] = new Pixel(218, 255, 137);
      expectedArray[2][2] = new Pixel(94, 113, 255);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "sharpen executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test sharpen with split percentage.
   */
  @Test
  public void testSharpenCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testsharpenImage";
    double percentage = 90.2;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "sharpen" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(165, 163, 0);
      expectedArray[0][1] = new Pixel(174, 186, 255);
      expectedArray[0][2] = new Pixel(219, 9, 255);

      expectedArray[1][0] = new Pixel(15, 64, 13);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(64, 139, 255);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "sharpen executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test sepiaCommand.
   */
  @Test
  public void testSepiaCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testSepiaImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "sepia" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(135, 120, 94);
      expectedArray[0][1] = new Pixel(126, 112, 87);
      expectedArray[0][2] = new Pixel(146, 130, 101);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 238);
      expectedArray[1][2] = new Pixel(118, 105, 82);

      expectedArray[2][0] = new Pixel(209, 186, 145);
      expectedArray[2][1] = new Pixel(195, 173, 135);
      expectedArray[2][2] = new Pixel(84, 75, 58);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "sepia executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test sepia with split percentage.
   */
  @Test
  public void testSepiaCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testSepiaImage";
    double percentage = 50.0;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "sepia" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(135, 120, 94);
      expectedArray[0][1] = new Pixel(126, 112, 87);
      expectedArray[0][2] = new Pixel(146, 130, 101);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "sepia executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test verticalFlipCommand.
   */
  @Test
  public void testVerticalFlipCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testVerticalFlipImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "vertical-flip" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(250, 0, 255);
      expectedArray[0][1] = new Pixel(0, 120, 180);
      expectedArray[0][2] = new Pixel(150, 100, 0);

      expectedArray[1][0] = new Pixel(10, 100, 200);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(0, 0, 0);

      expectedArray[2][0] = new Pixel(75, 20, 210);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(230, 130, 100);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "vertical-flip executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test horizontalFlipCommand.
   */
  @Test
  public void testHorizontalFlipCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testHorizontalFlipImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "horizontal-flip" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(230, 130, 100);
      expectedArray[0][1] = new Pixel(125, 190, 0);
      expectedArray[0][2] = new Pixel(75, 20, 210);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(150, 100, 0);
      expectedArray[2][1] = new Pixel(0, 120, 180);
      expectedArray[2][2] = new Pixel(250, 0, 255);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "horizontal-flip executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test rgbSplitCommand.
   */
  @Test
  public void testRGBSpiltCommand() {
    String redImagePath = "test\\res\\redImage.png";
    String greenImagePath = "test\\res\\greenImage.png";
    String blueImagePath = "test\\res\\BlueImage.png";
    String interMediateImage = "testImage";

    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "rgb-split" + " " + interMediateImage + " " + "redImage greenImage blueImage" + "\n"
            + "save " + redImagePath + " " + "redImage" + "\n"
            + "save " + greenImagePath + " " + "greenImage" + "\n"
            + "save " + blueImagePath + " " + "blueImage" + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testRedImage = model.getImage("redImage");
      Image testGreenImage = model.getImage("greenImage");
      Image testBlueImage = model.getImage("blueImage");

      int width = testRedImage.getPixels().length;
      int height = testRedImage.getPixels()[0].length;


      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 100, 0);
      expectedArray[0][1] = new Pixel(0, 120, 180);
      expectedArray[0][2] = new Pixel(250, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testRedImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testGreenImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testBlueImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "rgb-split executed successfully" + lineSeparator +
              "save executed successfully" + lineSeparator +
              "save executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * Test for multiple commands. (redComponent + valueComponent).
   */
  @Test
  public void testRedComponentThenValueComponent() {
    String originalImageName = "testImage";
    String redImageName = "redComponentImage";
    String destinationImage = "testNewImage";

    String inputData = "load " + this.imagePath + " " + originalImageName + "\n"
            + "red-component" + " " + originalImageName + " " + redImageName + "\n"
            + "value-component" + " " + redImageName + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];

      expectedArray[0][0] = new Pixel(150, 150, 150);
      expectedArray[0][1] = new Pixel(0, 0, 0);
      expectedArray[0][2] = new Pixel(250, 250, 250);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 10, 10);

      expectedArray[2][0] = new Pixel(230, 230, 230);
      expectedArray[2][1] = new Pixel(125, 125, 125);
      expectedArray[2][2] = new Pixel(75, 75, 75);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "red-component executed successfully" + lineSeparator +
              "value-component executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * Test for multiple commands. (redComponent + valueComponent).
   */
  @Test
  public void testBlueComponentThenBrighten() {
    String originalImageName = "testImage";
    String blueImageName = "BlueComponentImage";
    String destinationImage = "testNewImage";

    String inputData = "load " + this.imagePath + " " + originalImageName + "\n"
            + "blue-component" + " " + originalImageName + " " + blueImageName + "\n"
            + "brighten 35" + " " + blueImageName + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];

      expectedArray[0][0] = new Pixel(35, 35, 35);
      expectedArray[0][1] = new Pixel(35, 35, 215);
      expectedArray[0][2] = new Pixel(35, 35, 255);

      expectedArray[1][0] = new Pixel(35, 35, 35);
      expectedArray[1][1] = new Pixel(35, 35, 255);
      expectedArray[1][2] = new Pixel(35, 35, 235);

      expectedArray[2][0] = new Pixel(35, 35, 135);
      expectedArray[2][1] = new Pixel(35, 35, 35);
      expectedArray[2][2] = new Pixel(35, 35, 245);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "blue-component executed successfully" + lineSeparator +
              "brighten executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * Test for multiple commands. (horizontalFlip + verticalFlip).
   */
  @Test
  public void testHorizontalThenVerticalFlip() {
    String originalImageName = "testImage";
    String flipImageName = "FlipComponentImage";
    String destinationImage = "testNewImage";

    String inputData = "load " + this.imagePath + " " + originalImageName + "\n"
            + "horizontal-flip" + " " + originalImageName + " " + flipImageName + "\n"
            + "vertical-flip" + " " + flipImageName + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(75, 20, 210);
      expectedArray[0][1] = new Pixel(125, 190, 0);
      expectedArray[0][2] = new Pixel(230, 130, 100);

      expectedArray[1][0] = new Pixel(10, 100, 200);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(0, 0, 0);

      expectedArray[2][0] = new Pixel(250, 0, 255);
      expectedArray[2][1] = new Pixel(0, 120, 180);
      expectedArray[2][2] = new Pixel(150, 100, 0);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "horizontal-flip executed successfully" + lineSeparator +
              "vertical-flip executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * Test for multiple commands. (luma + sepia)
   */
  @Test
  public void testLumaThenSepia() {
    String originalImageName = "testImage";
    String lumaImageName = "LumaComponentImage";
    String destinationImage = "testNewImage";

    String inputData = "load " + this.imagePath + " " + originalImageName + "\n"
            + "luma-component" + " " + originalImageName + " " + lumaImageName + "\n"
            + "sepia" + " " + lumaImageName + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(140, 125, 97);
      expectedArray[0][1] = new Pixel(133, 119, 92);
      expectedArray[0][2] = new Pixel(95, 85, 66);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 237);
      expectedArray[1][2] = new Pixel(118, 105, 82);

      expectedArray[2][0] = new Pixel(201, 179, 139);
      expectedArray[2][1] = new Pixel(220, 196, 152);
      expectedArray[2][2] = new Pixel(60, 54, 42);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "luma-component executed successfully" + lineSeparator +
              "sepia executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }


  /**
   * test compressCommand.
   */
  @Test
  public void testCompressCommandPercentage60() {
    String interMediateImage = "testImage";
    String destinationImage = "testCompressImage";
    int percentage = 60;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "compress" + " " + percentage + " "
            + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(159, 135, 0);
      expectedArray[0][1] = new Pixel(0, 101, 217);
      expectedArray[0][2] = new Pixel(210, 0, 196);

      expectedArray[1][0] = new Pixel(43, 18, 0);
      expectedArray[1][1] = new Pixel(245, 219, 217);
      expectedArray[1][2] = new Pixel(3, 0, 196);

      expectedArray[2][0] = new Pixel(142, 80, 63);
      expectedArray[2][1] = new Pixel(142, 164, 13);
      expectedArray[2][2] = new Pixel(95, 42, 121);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }
      String expectedResult = "load executed successfully" + lineSeparator +
              "compress executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test Color Correction Command.
   */
  @Test
  public void testColorCorrectionCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testColorCorrectImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "color-correct" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];

      expectedArray[0][0] = new Pixel(166, 91, 0);
      expectedArray[0][1] = new Pixel(16, 111, 171);
      expectedArray[0][2] = new Pixel(255, 0, 246);

      expectedArray[1][0] = new Pixel(16, 0, 0);
      expectedArray[1][1] = new Pixel(255, 246, 246);
      expectedArray[1][2] = new Pixel(26, 91, 191);

      expectedArray[2][0] = new Pixel(246, 121, 91);
      expectedArray[2][1] = new Pixel(141, 181, 0);
      expectedArray[2][2] = new Pixel(91, 11, 201);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }
      String expectedResult = "load executed successfully" + lineSeparator +
              "color-correct executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test Color Correction Command with split percentage.
   */
  @Test
  public void testColorCorrectionCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testColorCorrectionImage";
    double percentage = 60.5;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "color-correct" + " " + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(166, 91, 0);
      expectedArray[0][1] = new Pixel(16, 111, 171);
      expectedArray[0][2] = new Pixel(255, 0, 246);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(10, 100, 200);

      expectedArray[2][0] = new Pixel(230, 130, 100);
      expectedArray[2][1] = new Pixel(125, 190, 0);
      expectedArray[2][2] = new Pixel(75, 20, 210);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "color-correct executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test compressCommand.
   */
  @Test
  public void testCompressCommandPercentage30() {
    String interMediateImage = "testImage";
    String destinationImage = "testCompressImage";
    int percentage = 30;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "compress" + " " + percentage + " "
            + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(150, 91, 0);
      expectedArray[0][1] = new Pixel(0, 111, 201);
      expectedArray[0][2] = new Pixel(250, 0, 243);

      expectedArray[1][0] = new Pixel(0, 8, 16);
      expectedArray[1][1] = new Pixel(255, 255, 233);
      expectedArray[1][2] = new Pixel(9, 66, 211);

      expectedArray[2][0] = new Pixel(230, 145, 113);
      expectedArray[2][1] = new Pixel(125, 174, 13);
      expectedArray[2][2] = new Pixel(37, 5, 196);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }
      String expectedResult = "load executed successfully" + lineSeparator +
              "compress executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test level adjustment command.
   */
  @Test
  public void testLevelsAdjustmentCommand() {
    String interMediateImage = "testImage";
    String destinationImage = "testLevelsAdjustImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "levels-adjust" + " 20 100 255" + " "
            + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];

      expectedArray[0][0] = new Pixel(186, 136, 0);
      expectedArray[0][1] = new Pixel(0, 153, 213);
      expectedArray[0][2] = new Pixel(253, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(0, 136, 228);

      expectedArray[2][0] = new Pixel(245, 165, 136);
      expectedArray[2][1] = new Pixel(159, 221, 0);
      expectedArray[2][2] = new Pixel(93, 0, 234);


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "levels-adjust executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test level adjustment command with split percentage.
   */
  @Test
  public void testLevelAdjustmentCommandWithSplitPercentage() {
    String interMediateImage = "testImage";
    String destinationImage = "testLevelAdjustmentImage";
    double percentage = 100.0;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "levels-adjust" + " 20 100 255 "
            + interMediateImage + " " + destinationImage + " split "
            + percentage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);
      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];
      expectedArray[0][0] = new Pixel(186, 136, 0);
      expectedArray[0][1] = new Pixel(0, 153, 213);
      expectedArray[0][2] = new Pixel(253, 0, 255);

      expectedArray[1][0] = new Pixel(0, 0, 0);
      expectedArray[1][1] = new Pixel(255, 255, 255);
      expectedArray[1][2] = new Pixel(0, 136, 228);

      expectedArray[2][0] = new Pixel(245, 165, 136);
      expectedArray[2][1] = new Pixel(159, 221, 0);
      expectedArray[2][2] = new Pixel(93, 0, 234);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "levels-adjust executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test compress method.
   */
  @Test
  public void testCompressMethod() {
    String interMediateImage = "testImage";
    String destinationImage = "testCompressedImage";
    int percentage = 50;
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "compress" + " " + percentage + " "
            + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      int width = testImage.getPixels().length;
      int height = testImage.getPixels()[0].length;

      Pixel[][] expectedArray = new Pixel[3][3];

      expectedArray[0][0] = new Pixel(159, 135, 0);
      expectedArray[0][1] = new Pixel(0, 101, 217);
      expectedArray[0][2] = new Pixel(210, 0, 227);

      expectedArray[1][0] = new Pixel(43, 18, 0);
      expectedArray[1][1] = new Pixel(245, 219, 217);
      expectedArray[1][2] = new Pixel(3, 0, 227);

      expectedArray[2][0] = new Pixel(177, 118, 63);
      expectedArray[2][1] = new Pixel(177, 201, 13);
      expectedArray[2][2] = new Pixel(60, 5, 196);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j].getRed(), testImage.getPixels()[i][j].getRed());
          assertEquals(expectedArray[i][j].getGreen(), testImage.getPixels()[i][j].getGreen());
          assertEquals(expectedArray[i][j].getBlue(), testImage.getPixels()[i][j].getBlue());
        }
      }

      String expectedResult = "load executed successfully" + lineSeparator +
              "compress executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }

  /**
   * test histogram method.
   */
  @Test
  public void testHistogramMethod() {
    String interMediateImage = "testImage";
    String destinationImage = "testHistogramImage";
    String inputData = "load " + this.imagePath + " " + interMediateImage + "\n"
            + "histogram" + " " + interMediateImage + " " + destinationImage + "\n"
            + "save " + this.savePath + " " + destinationImage + "\nq";
    simulateUserInput(inputData);

    ImageViewInterface view = new ImageView();
    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller = new ImageController(view, model);

    try {
      controller.process();
      Image testImage = model.getImage(destinationImage);

      assertNotNull(testImage);

      String expectedResult = "load executed successfully" + lineSeparator +
              "histogram executed successfully" + lineSeparator +
              "save executed successfully";
      assertEquals(expectedResult + System.lineSeparator(), outResult.toString());
    } catch (Exception e) {
      fail("This test should have failed!");
    }
  }
}
