import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * The ImageTest class contains JUnit tests for the Image
 * class, which represents an image composed of pixels.
 */
public class ImageTest {

  private Image image;
  private int width;
  private int height;

  /**
   * Sets up the initial state for testing by creating
   * a sample image.
   */
  @Before
  public void setup() {

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

    this.image = new Image(pixelArray);
    width = this.image.getPixels().length;
    height = this.image.getPixels()[0].length;
  }

  /**
   * Test creating an image with a specified array of pixels.
   */
  @Test
  public void testCreateImage() {
    Pixel[][] pixels = new Pixel[3][3];
    Pixel pixel = new Pixel(1, 1, 1);
    Image image;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        pixels[i][j] = pixel;
      }
    }

    image = new Image(pixels);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(pixel.getRed(), image.getPixels()[i][j].getRed());
        assertEquals(pixel.getGreen(), image.getPixels()[i][j].getGreen());
        assertEquals(pixel.getBlue(), image.getPixels()[i][j].getBlue());
      }
    }
  }

  /**
   * Test getting the pixel array of an image.
   */
  @Test
  public void testGetPixels() {
    Pixel[][] pixels = new Pixel[3][3];
    Pixel pixel = new Pixel(1, 1, 1);
    Image image;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        pixels[i][j] = pixel;
      }
    }

    image = new Image(pixels);

    assertEquals(pixels, image.getPixels());
  }


  /**
   * Test redComponent.
   */
  @Test
  public void testRedComponent() {
    Image testImage = this.image.redComponent();

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
    Image testImage = this.image.greenComponent();

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
   * Test blueComponent.
   */
  @Test
  public void testBlueComponent() {
    Image testImage = this.image.blueComponent();

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
   * Test valueComponent.
   */
  @Test
  public void testValueComponent() {
    Image testImage = this.image.valueComponent();

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
   * Test intensityComponent.
   */
  @Test
  public void testIntensityComponent() {
    Image testImage = this.image.intensityComponent();

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
   * Test lumaComponent.
   */
  @Test
  public void testLumaComponent() {
    Image testImage = this.image.lumaComponent();

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
   * Test brightenComponent +50.
   */
  @Test
  public void testBrightenComponentPos50() {
    int increment = 50;
    Image testImage = this.image.brighten(increment);

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
   * Test brightenComponent -50.
   */
  @Test
  public void testBrightenComponentNeg50() {
    int increment = -50;
    Image testImage = this.image.brighten(increment);

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
    int increment = 500;
    Image testImage = this.image.brighten(increment);

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
    int increment = -500;
    Image testImage = this.image.brighten(increment);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals(0, testImage.getPixels()[i][j].getRed());
        assertEquals(0, testImage.getPixels()[i][j].getGreen());
        assertEquals(0, testImage.getPixels()[i][j].getBlue());
      }
    }
  }

  /**
   * Test sepiaCommand.
   */
  @Test
  public void testSepiaCommand() {
    Image testImage = this.image.sepia();

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

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        assertEquals(expectedArray[x][y].getRed(), testImage.getPixels()[x][y].getRed());
        assertEquals(expectedArray[x][y].getGreen(), testImage.getPixels()[x][y].getGreen());
        assertEquals(expectedArray[x][y].getBlue(), testImage.getPixels()[x][y].getBlue());
      }
    }
  }

  /**
   * Test verticalFlipCommand.
   */
  @Test
  public void testVerticalFlipCommand() {
    Image testImage = this.image.verticalFlip();

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
   * Test horizontalFlipCommand.
   */
  @Test
  public void testHorizontalFlipCommand() {
    Image testImage = this.image.horizontalFlip();

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
   * Test for multiple commands. (redComponent + valueComponent).
   */
  @Test
  public void testRedComponentThenValueComponent() {
    Image redImage = this.image.redComponent();

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

    Image testImage = redImage.valueComponent();

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
    int increment = 35;
    Image blueImage = this.image.blueComponent();

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

    Image testImage = blueImage.brighten(increment);

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
    Image horizontalImage = this.image.horizontalFlip();

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

    Image testImage = horizontalImage.verticalFlip();

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
   * Test for multiple commands. (luma + sepia)
   */
  @Test
  public void testLumaThenSepia() {
    Image lumaImage = this.image.lumaComponent();

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

    Image testImage = lumaImage.sepia();

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
   * Test for multiple commands. (luma + sepia)
   */
  @Test
  public void testHistogram() {
    int[][] result = this.image.histogram();

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


    for (int i = 0; i < expectedArray.length; i++) {
      for (int j = 0; j < expectedArray[i].length; j++) {
        assertEquals(expectedArray[i][j], result[i][j]);
      }
    }
  }

  /**
   * Test compressCommand.
   */
  @Test
  public void testCompressCommand() {
    Image testImage = this.image.compress(35.8);

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
   * Test colorCorrection method.
   */
  @Test
  public void testColorCorrectionCommand() {
    Image testImage = this.image.correctImage();

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
  }

  /**
   * Test levelAdjustment method.
   */
  @Test
  public void testLevelAdjustmentCommand() {
    Image testImage = this.image.levelsAdjust(20, 100, 255);

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

}