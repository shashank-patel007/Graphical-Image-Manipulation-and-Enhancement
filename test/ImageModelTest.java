import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import model.Image;
import model.ImageModel;
import model.ImageModelInterface;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The ImageModelTest class contains JUnit tests for
 * the ImageModel class, which represents the model in the
 * image processing application.
 */
public class ImageModelTest {

  private ImageModelInterface model;
  private Image image;

  /**
   * Sets up the initial state for testing by creating
   * a sample image and an instance of the ImageModel.
   */
  @Before
  public void setup() {

    Pixel[][] pixelArray = new Pixel[3][3];

    // Define specific constant values for each pixel
    pixelArray[0][0] = new Pixel(150, 100, 0);
    pixelArray[0][1] = new Pixel(0, 120, 180);
    pixelArray[0][2] = new Pixel(250, 0, 255);

    pixelArray[1][0] = new Pixel(0, 0, 0);
    pixelArray[1][1] = new Pixel(255, 255, 255);
    pixelArray[1][2] = new Pixel(10, 100, 200);

    pixelArray[2][0] = new Pixel(230, 130, 100);
    pixelArray[2][1] = new Pixel(125, 190, 0);
    pixelArray[2][2] = new Pixel(75, 20, 210);

    this.image = new Image(pixelArray);
    this.model = new ImageModel();
  }

  /**
   * Constructor works correctly.
   */
  @Test
  public void testConstructor() {
    try {
      new ImageModel();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
  }

  /**
   * Adding an Image.
   */
  @Test
  public void testAddImage() {
    String imageName = "testImage";
    this.model.addImage(imageName, this.image);

    Image testImage = this.model.getImage(imageName);
    int width = testImage.getPixels().length;
    int height = testImage.getPixels()[0].length;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals(this.image.getPixels()[i][j], testImage.getPixels()[i][j]);
      }
    }
  }


  /**
   * Test redComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageRedComponent() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.redComponentCommand(imageNotExist, destinationImageName);
    fail("This test should have failed!");
  }

  /**
   * Test redComponent.
   */
  @Test
  public void testRedComponent() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);

    this.model.redComponentCommand(imageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * Test greenComponent.
   */
  @Test
  public void testGreenComponent() {
    String imageName = "testImage";
    String destinationImageName = "greenImage";
    this.model.addImage(imageName, this.image);

    this.model.greenComponentCommand(imageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test greenComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageGreenComponent() {
    String imageName = "testImage";
    String destinationImageName = "greenImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.greenComponentCommand(imageNotExist, destinationImageName);
    fail("This test should have failed!");
  }


  /**
   * Test blueComponent.
   */
  @Test
  public void testBlueComponent() {
    String imageName = "testImage";
    String destinationImageName = "blueImage";
    this.model.addImage(imageName, this.image);

    this.model.blueComponentCommand(imageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test blueComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageBlueComponent() {
    String imageName = "testImage";
    String destinationImageName = "blueImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.blueComponentCommand(imageNotExist, destinationImageName);
    fail("This test should have failed!");
  }

  /**
   * Test valueComponent.
   */
  @Test
  public void testValueComponent() {
    String imageName = "testImage";
    String destinationImageName = "valueImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.valueComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test valueComponent with splitPercentage.
   */
  @Test
  public void testValueComponentWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "valueImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.of(50.0);

    this.model.valueComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test valueComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageValueComponent() {
    String imageName = "testImage";
    String destinationImageName = "valueImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    Optional<Double> splitPercentage = Optional.empty();
    this.model.valueComponentCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test intensityComponent.
   */
  @Test
  public void testIntensityComponent() {
    String imageName = "testImage";
    String destinationImageName = "intensityImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.intensityComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test intensityComponent with splitPercentage.
   */
  @Test
  public void testIntensityComponentWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "intensityImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.of(40.5);

    this.model.intensityComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * Test intensityComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageIntensityComponent() {
    String imageName = "testImage";
    String destinationImageName = "intensityImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    String imageNotExist = "testImage2";
    this.model.intensityComponentCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test lumaComponent.
   */
  @Test
  public void testLumaComponent() {
    String imageName = "testImage";
    String destinationImageName = "lumaImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.lumaComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * Test lumaComponent with splitPercentage.
   */
  @Test
  public void testLumaComponentWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "lumaImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.of(20.5);

    this.model.lumaComponentCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test lumaComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageLumaComponent() {
    String imageName = "testImage";
    String destinationImageName = "lumaImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    String imageNotExist = "testImage2";
    this.model.lumaComponentCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test brightenComponent +50.
   */
  @Test
  public void testBrightenComponentPos50() {
    String imageName = "testImage";
    String destinationImageName = "brightenImage";
    int increment = 50;
    this.model.addImage(imageName, this.image);

    this.model.brightenCommand(imageName, destinationImageName, increment);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test lumaComponent invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageBrightenComponent() {
    String imageName = "testImage";
    String destinationImageName = "brightenImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.brightenCommand(imageNotExist, destinationImageName, 50);
    fail("This test should have failed!");
  }


  /**
   * Test brightenComponent -50.
   */
  @Test
  public void testBrightenComponentNeg50() {
    String imageName = "testImage";
    String destinationImageName = "brightenImage";
    int increment = -50;
    this.model.addImage(imageName, this.image);

    this.model.brightenCommand(imageName, destinationImageName, increment);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test brightenComponent 500.
   */
  @Test
  public void testBrightenComponentPos500() {
    String imageName = "testImage";
    String destinationImageName = "brightenImage";
    int increment = 500;
    this.model.addImage(imageName, this.image);

    this.model.brightenCommand(imageName, destinationImageName, increment);

    Image testImage = this.model.getImage(destinationImageName);
    int width = testImage.getPixels().length;
    int height = testImage.getPixels()[0].length;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {

        assertEquals(255, testImage.getPixels()[i][j].getRed());
        assertEquals(255, testImage.getPixels()[i][j].getGreen());
        assertEquals(255, testImage.getPixels()[i][j].getBlue());
      }
    }
  }


  /**
   * Test brightenComponent -500.
   */
  @Test
  public void testBrightenComponentNeg500() {
    String imageName = "testImage";
    String destinationImageName = "brightenImage";
    int increment = -500;
    this.model.addImage(imageName, this.image);

    this.model.brightenCommand(imageName, destinationImageName, increment);

    Image testImage = this.model.getImage(destinationImageName);
    int width = testImage.getPixels().length;
    int height = testImage.getPixels()[0].length;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals(0, testImage.getPixels()[i][j].getRed());
        assertEquals(0, testImage.getPixels()[i][j].getGreen());
        assertEquals(0, testImage.getPixels()[i][j].getBlue());
      }
    }
  }


  /**
   * Test combinedCommand.
   */
  @Test
  public void testCombineCommand() {
    String imageName = "testImage";
    String redImageName = "redImage";
    String greenImageName = "greenImage";
    String blueImageName = "blueImage";
    String destinationImageName = "combineImage";

    this.model.addImage(imageName, this.image);
    this.model.redComponentCommand(imageName, redImageName);
    this.model.greenComponentCommand(imageName, greenImageName);
    this.model.blueComponentCommand(imageName, blueImageName);

    this.model.combineCommand(redImageName, greenImageName,
            blueImageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test combinedCommand. Image does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageDoesNotExistCombineCommand() {
    String imageName = "testImage";
    String redImageName = "redImage";
    String greenImageName = "greenImage";
    String blueImageName = "blueImage";
    String destinationImageName = "combineImage";

    this.model.addImage(imageName, this.image);
    this.model.redComponentCommand(imageName, redImageName);
    this.model.greenComponentCommand(imageName, greenImageName);
    this.model.blueComponentCommand(imageName, blueImageName);

    redImageName = "redImage2";
    blueImageName = "blueImage2";
    this.model.combineCommand(redImageName, greenImageName,
            blueImageName, destinationImageName);
    fail("This test should have failed!");
  }


  /**
   * Test combinedCommand. Image does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDifferentImageDimensionsCombineCommand() {
    Pixel[][] pixelArray = new Pixel[1][3];

    pixelArray[0][0] = new Pixel(150, 100, 0);
    pixelArray[0][1] = new Pixel(0, 120, 180);
    pixelArray[0][2] = new Pixel(250, 0, 255);

    Image newImage = new Image(pixelArray);
    String newImageName = "newImage";

    String imageName = "testImage";
    String redImageName = "redImage";
    String greenImageName = "greenImage";
    String blueImageName = "blueImage";
    String destinationImageName = "combineImage";

    this.model.addImage(imageName, this.image);
    this.model.addImage(newImageName, newImage);

    this.model.redComponentCommand(imageName, redImageName);
    this.model.greenComponentCommand(newImageName, greenImageName);
    this.model.blueComponentCommand(imageName, blueImageName);

    this.model.combineCommand(redImageName, greenImageName,
            blueImageName, destinationImageName);
    fail("This test should have failed!");
  }


  /**
   * Test blurCommand.
   */
  @Test
  public void testBlurCommand() {
    String imageName = "testImage";
    String destinationImageName = "blurImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.blurCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        assertEquals(expectedArray[x][y].getRed(), testImage.getPixels()[x][y].getRed());
        assertEquals(expectedArray[x][y].getGreen(), testImage.getPixels()[x][y].getGreen());
        assertEquals(expectedArray[x][y].getBlue(), testImage.getPixels()[x][y].getBlue());
      }
    }
  }


  /**
   * Test blurCommand with splitPercentage.
   */
  @Test
  public void testBlurCommandWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "blurImage";
    Optional<Double> splitPercentage = Optional.of(70.3);

    this.model.addImage(imageName, this.image);
    this.model.blurCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test blur invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageBlurComponent() {
    String imageName = "testImage";
    String destinationImageName = "blurImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    String imageNotExist = "testImage2";
    this.model.blurCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * Test blur invalid image with splitPercentage.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageBlurComponentWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "blurImage";
    Optional<Double> splitPercentage = Optional.of(40.0);

    this.model.addImage(imageName, this.image);
    String imageNotExist = "testImage2";
    this.model.blurCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test sharpenCommand.
   */
  @Test
  public void testSharpenCommand() {
    String imageName = "testImage";
    String destinationImageName = "sharpenImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.sharpenCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * Test sharpenCommand with splitPercentage.
   */
  @Test
  public void testSharpenCommandWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "sharpenImage";
    Optional<Double> splitPercentage = Optional.of(90.2);

    this.model.addImage(imageName, this.image);
    this.model.sharpenCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test sharpen invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageSharpenComponent() {
    String imageName = "testImage";
    String destinationImageName = "sharpenImage";
    Optional<Double> splitPercentage = Optional.empty();
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.sharpenCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test sepiaCommand.
   */
  @Test
  public void testSepiaCommand() {
    String imageName = "testImage";
    String destinationImageName = "sepiaImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.sepiaCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * Test sepiaCommand with splitPercentage.
   */
  @Test
  public void testSepiaCommandWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "sepiaImage";
    Optional<Double> splitPercentage = Optional.of(50.0);

    this.model.addImage(imageName, this.image);
    this.model.sepiaCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test sepia invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageSepiaComponent() {
    String imageName = "testImage";
    String destinationImageName = "sepiaImage";
    Optional<Double> splitPercentage = Optional.empty();
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.sepiaCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }


  /**
   * Test verticalFlipCommand.
   */
  @Test
  public void testVerticalFlipCommand() {
    String imageName = "testImage";
    String destinationImageName = "verticalFlipImage";

    this.model.addImage(imageName, this.image);
    this.model.verticalFlipCommand(imageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test VerticalFlip invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageVerticalFlipComponent() {
    String imageName = "testImage";
    String destinationImageName = "verticalFlipImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.verticalFlipCommand(imageNotExist, destinationImageName);
    fail("This test should have failed!");
  }


  /**
   * Test horizontalFlipCommand.
   */
  @Test
  public void testHorizontalFlipCommand() {
    String imageName = "testImage";
    String destinationImageName = "horizontalFlipImage";

    this.model.addImage(imageName, this.image);
    this.model.horizontalFlipCommand(imageName, destinationImageName);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test horizontalFlip invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoImageHorizontalFlipComponent() {
    String imageName = "testImage";
    String destinationImageName = "horizontalFlipImage";
    this.model.addImage(imageName, this.image);

    String imageNotExist = "testImage2";
    this.model.horizontalFlipCommand(imageNotExist, destinationImageName);
    fail("This test should have failed!");
  }

  /**
   * Test RGB-Split.
   */
  @Test
  public void testRGBSplit() {
    String imageName = "testImage";
    String redImageName = "redImage";
    String greenImageName = "greenImage";
    String blueImageName = "blueImage";

    this.model.addImage(imageName, this.image);
    this.model.rgbSplitCommand(redImageName, greenImageName, blueImageName, imageName);

    Image testRedImage = this.model.getImage(redImageName);
    Image testGreenImage = this.model.getImage(greenImageName);
    Image testBlueImage = this.model.getImage(blueImageName);

    int widthRed = testRedImage.getPixels().length;
    int heightRed = testRedImage.getPixels()[0].length;
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

    for (int i = 0; i < widthRed; i++) {
      for (int j = 0; j < heightRed; j++) {


        assertEquals(expectedArray[i][j].getRed(), testRedImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), testGreenImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), testBlueImage.getPixels()[i][j].getBlue());
      }
    }
  }

  /**
   * Test for multiple commands. (redComponent + valueComponent).
   */
  @Test
  public void testRedComponentThenValueComponent() {
    String originalImageName = "testImage";
    String redImageName = "redComponentImage";
    String finalImageName = "valueOfRedComponentImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(originalImageName, this.image);
    this.model.redComponentCommand(originalImageName, redImageName);
    Image redImage = this.model.getImage(redImageName);
    int width = redImage.getPixels().length;
    int height = redImage.getPixels()[0].length;

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
        assertEquals(expectedArray[i][j].getRed(), redImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), redImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), redImage.getPixels()[i][j].getBlue());
      }
    }

    this.model.valueComponentCommand(redImageName, finalImageName, splitPercentage);

    Image testImage = this.model.getImage(finalImageName);
    width = testImage.getPixels().length;
    height = testImage.getPixels()[0].length;

    expectedArray = new Pixel[3][3];

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
  }


  /**
   * Test for multiple commands. (blueComponent + brightenComponent).
   */
  @Test
  public void testBlueComponentThenBrighten() {
    String imageName = "testImage";
    String blueImageName = "blueComponentImage";
    String brightenedImageName = "brightenedBlueImage";
    int increment = 35;

    this.model.addImage(imageName, this.image);
    this.model.blueComponentCommand(imageName, blueImageName);
    Image blueImage = this.model.getImage(blueImageName);
    int width = blueImage.getPixels().length;
    int height = blueImage.getPixels()[0].length;

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
        assertEquals(expectedArray[i][j].getRed(), blueImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), blueImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), blueImage.getPixels()[i][j].getBlue());
      }
    }

    this.model.brightenCommand(blueImageName, brightenedImageName, increment);

    Image testImage = this.model.getImage(brightenedImageName);
    width = testImage.getPixels().length;
    height = testImage.getPixels()[0].length;

    expectedArray = new Pixel[3][3];

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
  }


  /**
   * Test for multiple commands. (horizontalFlip + verticalFlip).
   */
  @Test
  public void testHorizontalThenVerticalFlip() {
    String imageName = "testImage";
    String horizontalFlipImageName = "horizontalFlipImage";
    String finalFlippedImageName = "finalFlippedImage";

    this.model.addImage(imageName, this.image);
    this.model.horizontalFlipCommand(imageName, horizontalFlipImageName);
    Image horizontalImage = this.model.getImage(horizontalFlipImageName);

    int width = horizontalImage.getPixels().length;
    int height = horizontalImage.getPixels()[0].length;

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
        assertEquals(expectedArray[i][j].getRed(), horizontalImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), horizontalImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), horizontalImage.getPixels()[i][j].getBlue());
      }
    }

    this.model.verticalFlipCommand(horizontalFlipImageName, finalFlippedImageName);
    Image testImage = this.model.getImage(finalFlippedImageName);
    width = testImage.getPixels().length;
    height = testImage.getPixels()[0].length;

    expectedArray = new Pixel[3][3];
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
  }


  /**
   * Test for multiple commands. (rgb-split + combine)
   */
  @Test
  public void testRGBSplitAndCombine() {
    String imageName = "testImage";
    String redImageName = "redImage";
    String greenImageName = "greenImage";
    String blueImageName = "blueImage";
    String combinedImageName = "combinedImage";


    this.model.addImage(imageName, this.image);
    this.model.rgbSplitCommand(redImageName, greenImageName, blueImageName, imageName);
    this.model.combineCommand(redImageName, greenImageName, blueImageName, combinedImageName);


    Image combinedImage = this.model.getImage(combinedImageName);
    int width = combinedImage.getPixels().length;
    int height = combinedImage.getPixels()[0].length;

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
        assertEquals(expectedArray[i][j].getRed(), combinedImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), combinedImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), combinedImage.getPixels()[i][j].getBlue());
      }
    }
  }


  /**
   * Test for multiple commands. (luma + sepia)
   */
  @Test
  public void testLumaThenSepia() {
    String imageName = "testImage";
    String lumaImageName = "lumaImage";
    String sepiaImageName = "sepiaFromLumaImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.lumaComponentCommand(imageName, lumaImageName, splitPercentage);
    Image lumaImage = this.model.getImage(lumaImageName);
    int width = lumaImage.getPixels().length;
    int height = lumaImage.getPixels()[0].length;

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

        assertEquals(expectedArray[i][j].getRed(), lumaImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), lumaImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), lumaImage.getPixels()[i][j].getBlue());
      }
    }

    this.model.sepiaCommand(lumaImageName, sepiaImageName, splitPercentage);

    Image testImage = this.model.getImage(sepiaImageName);
    width = testImage.getPixels().length;
    height = testImage.getPixels()[0].length;

    expectedArray = new Pixel[3][3];
    expectedArray[0][0] = new Pixel(140, 125, 97);
    expectedArray[0][1] = new Pixel(133, 119, 92);
    expectedArray[0][2] = new Pixel(95, 85, 66);

    expectedArray[1][0] = new Pixel(0, 0, 0);
    expectedArray[1][1] = new Pixel(255, 255, 237);
    expectedArray[1][2] = new Pixel(118, 105, 82);

    expectedArray[2][0] = new Pixel(201, 179, 139);
    expectedArray[2][1] = new Pixel(220, 196, 152);
    expectedArray[2][2] = new Pixel(60, 54, 42);


    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        assertEquals(expectedArray[x][y].getRed(), testImage.getPixels()[x][y].getRed());
        assertEquals(expectedArray[x][y].getGreen(), testImage.getPixels()[x][y].getGreen());
        assertEquals(expectedArray[x][y].getBlue(), testImage.getPixels()[x][y].getBlue());
      }
    }
  }

  /**
   * Test for multiple commands with splitPercentage. (luma + sepia)
   */
  @Test
  public void testLumaThenSepiaWithSplitPercentage() {
    String imageName = "testImage";
    String lumaImageName = "lumaImage";
    String sepiaImageName = "sepiaFromLumaImage";
    Optional<Double> splitPercentage = Optional.of(70.0);

    this.model.addImage(imageName, this.image);
    this.model.lumaComponentCommand(imageName, lumaImageName, splitPercentage);
    Image lumaImage = this.model.getImage(lumaImageName);
    int width = lumaImage.getPixels().length;
    int height = lumaImage.getPixels()[0].length;

    Pixel[][] expectedArray = new Pixel[3][3];
    expectedArray[0][0] = new Pixel(104, 104, 104);
    expectedArray[0][1] = new Pixel(99, 99, 99);
    expectedArray[0][2] = new Pixel(71, 71, 71);

    expectedArray[1][0] = new Pixel(0, 0, 0);
    expectedArray[1][1] = new Pixel(254, 254, 254);
    expectedArray[1][2] = new Pixel(88, 88, 88);

    expectedArray[2][0] = new Pixel(230, 130, 100);
    expectedArray[2][1] = new Pixel(125, 190, 0);
    expectedArray[2][2] = new Pixel(75, 20, 210);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals(expectedArray[i][j].getRed(), lumaImage.getPixels()[i][j].getRed());
        assertEquals(expectedArray[i][j].getGreen(), lumaImage.getPixels()[i][j].getGreen());
        assertEquals(expectedArray[i][j].getBlue(), lumaImage.getPixels()[i][j].getBlue());
      }
    }

    splitPercentage = Optional.of(40.0);
    this.model.sepiaCommand(lumaImageName, sepiaImageName, splitPercentage);

    Image testImage = this.model.getImage(sepiaImageName);
    width = testImage.getPixels().length;
    height = testImage.getPixels()[0].length;

    expectedArray = new Pixel[3][3];
    expectedArray[0][0] = new Pixel(140, 125, 97);
    expectedArray[0][1] = new Pixel(133, 119, 92);
    expectedArray[0][2] = new Pixel(95, 85, 66);

    expectedArray[1][0] = new Pixel(0, 0, 0);
    expectedArray[1][1] = new Pixel(254, 254, 254);
    expectedArray[1][2] = new Pixel(88, 88, 88);

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
  }

  /**
   * test ColorCorrection method.
   */
  @Test
  public void testColorCorrection() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.colorCorrectionCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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


    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        assertEquals(expectedArray[x][y].getRed(), testImage.getPixels()[x][y].getRed());
        assertEquals(expectedArray[x][y].getGreen(), testImage.getPixels()[x][y].getGreen());
        assertEquals(expectedArray[x][y].getBlue(), testImage.getPixels()[x][y].getBlue());
      }
    }
  }

  /**
   * test ColorCorrection method invalid percentage.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testColorCorrectionInvalidPercentage() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.of(-30.2);

    this.model.addImage(imageName, this.image);
    this.model.colorCorrectionCommand(imageName, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test ColorCorrection method invalid percentage.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testColorCorrectionInvalidPercentage2() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.of(130.2);

    this.model.addImage(imageName, this.image);
    this.model.colorCorrectionCommand(imageName, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test ColorCorrection method with splitPercentage.
   */
  @Test
  public void testColorCorrectionWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.of(60.5);

    this.model.addImage(imageName, this.image);
    this.model.colorCorrectionCommand(imageName, destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * Test horizontalFlip invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorCorrectionNoImageExist() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectionImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.of(10.0);

    String imageNotExist = "testImage2";
    this.model.colorCorrectionCommand(imageNotExist, destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test levelAdjustments method.
   */
  @Test
  public void testLevelAdjustments() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.empty();

    this.model.addImage(imageName, this.image);
    this.model.levelsAdjustmentCommand(20, 100, 255, imageName,
            destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }


  /**
   * test levelAdjustments method with splitPercentage.
   */
  @Test
  public void testLevelAdjustmentsWithSplitPercentage() {
    String imageName = "testImage";
    String destinationImageName = "colorCorrectedImage";
    Optional<Double> splitPercentage = Optional.of(100.0);

    this.model.addImage(imageName, this.image);
    this.model.levelsAdjustmentCommand(20, 100, 255, imageName,
            destinationImageName, splitPercentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * test compressImage method.
   */
  @Test
  public void testCompress() {
    String imageName = "testImage";
    String destinationImageName = "compressedImage";
    double percentage = 50;

    this.model.addImage(imageName, this.image);
    this.model.compressImage(imageName, destinationImageName, percentage);

    Image testImage = this.model.getImage(destinationImageName);
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
  }

  /**
   * test compressImage method.
   */
  @Test
  public void testCompress2() {
    String imageName = "testImage";
    String destinationImageName = "compressedImage";
    double percentage = 35.8;

    this.model.addImage(imageName, this.image);
    this.model.compressImage(imageName, destinationImageName, percentage);

    Image testImage = this.model.getImage(destinationImageName);
    int width = testImage.getPixels().length;
    int height = testImage.getPixels()[0].length;

    Pixel[][] expectedArray = new Pixel[3][3];
    expectedArray[0][0] = new Pixel(150, 108, 0);
    expectedArray[0][1] = new Pixel(0, 128, 217);
    expectedArray[0][2] = new Pixel(250, 0, 227);

    expectedArray[1][0] = new Pixel(0, 0, 0);
    expectedArray[1][1] = new Pixel(255, 246, 217);
    expectedArray[1][2] = new Pixel(9, 50, 227);

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
  }

  /**
   * test histogram method.
   */
  @Test
  public void testHistogram() {
    String imageName = "testImage";
    String destinationImageName = "histogramImage";

    this.model.addImage(imageName, this.image);
    try {
      int[][] result = this.model.histogramCommand(imageName, destinationImageName);

      int width = result.length;
      int height = result[0].length;

      int[][] expectedArray = new int[3][256];
      for (int[] row : expectedArray) {
        Arrays.fill(row, 0);
      }

      expectedArray[0][0] = 2;
      expectedArray[0][10] = 1;
      expectedArray[0][75] = 1;
      expectedArray[0][125] = 1;
      expectedArray[0][150] = 1;
      expectedArray[0][230] = 1;
      expectedArray[0][250] = 1;
      expectedArray[0][255] = 1;

      expectedArray[1][0] = 2;
      expectedArray[1][20] = 1;
      expectedArray[1][100] = 2;
      expectedArray[1][120] = 1;
      expectedArray[1][130] = 1;
      expectedArray[1][190] = 1;
      expectedArray[1][255] = 1;

      expectedArray[2][0] = 3;
      expectedArray[2][100] = 1;
      expectedArray[2][180] = 1;
      expectedArray[2][200] = 1;
      expectedArray[2][210] = 1;
      expectedArray[2][255] = 2;


      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          assertEquals(expectedArray[i][j], result[i][j]);
        }
      }
    } catch (Exception e) {
      fail("This test should have passed!");
    }
  }

  /**
   * test compressImage invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompressNoImageExist() {
    String imageName = "testImage";
    String destinationImageName = "compressImage";
    double percentage = 50.7;
    this.model.addImage(imageName, this.image);


    String imageNotExist = "testImage2";
    this.model.compressImage(imageNotExist, destinationImageName, percentage);
    fail("This test should have failed!");
  }

  /**
   * test compressImage invalid percentage.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompressImageInvalidPercentage() {
    String imageName = "testImage";
    String destinationImageName = "compressImage";
    double percentage = 101.7;
    this.model.addImage(imageName, this.image);

    this.model.compressImage(imageName, destinationImageName, percentage);
    fail("This test should have failed!");
  }

  /**
   * test compressImage invalid percentage.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompressImageInvalidPercentageNeg() {
    String imageName = "testImage";
    String destinationImageName = "compressImage";
    double percentage = -11.7;
    this.model.addImage(imageName, this.image);

    this.model.compressImage(imageName, destinationImageName, percentage);
    fail("This test should have failed!");
  }

  /**
   * test LevelAdjustments invalid image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLevelAdjustmentsNoImageExist() {
    String imageName = "testImage";
    String destinationImageName = "horizontalFlipImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.of(10.0);

    String imageNotExist = "testImage2";
    this.model.levelsAdjustmentCommand(20, 100, 255, imageNotExist,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test invalid b, m, w value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeValueOfBMW() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(-20, -100, -255, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test invalid value of b.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValueOfB() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(101, 100, 255, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test invalid value of m.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValueOfM() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(20, 19, 255, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test invalid value of w.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValueOfW() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(20, 100, 19, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test value of b,m and w out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValueOfBMW() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(255, 100, 20, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test value of B out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsB() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(256, 100, 255, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test value of M out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsM() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(20, 256, 255, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }

  /**
   * test value of W out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsW() {
    String imageName = "testImage";
    String destinationImageName = "redImage";
    this.model.addImage(imageName, this.image);
    Optional<Double> splitPercentage = Optional.empty();

    this.model.levelsAdjustmentCommand(20, 100, 256, imageName,
            destinationImageName, splitPercentage);
    fail("This test should have failed!");
  }
}