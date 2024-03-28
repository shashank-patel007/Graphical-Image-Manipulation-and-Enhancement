import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import controller.commands.AbstractImageFormat;
import controller.commands.ImagePNG;
import model.Image;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * The ImagePNGTest class contains JUnit tests for
 * the functionality of the ImagePNG class. It includes
 * tests for loading PNG images, saving images in PNG format,
 * saving images in JPG format, and handling scenarios where
 * the file path is empty.
 */
public class ImagePNGTest {

  private final String testImagePath = "res\\nyc.png";
  File savedFile;

  /**
   * Cleans up by deleting the tempFile after each test execution.
   */
  @After
  public void delete() {
    if (savedFile != null && savedFile.exists()) {
      savedFile.delete();
    }
  }

  /**
   * test load method.
   */
  @Test
  public void testLoad() {
    try {
      AbstractImageFormat imagePNG = new ImagePNG(this.testImagePath);
      Image loadedImage = imagePNG.load();
      assertNotNull(loadedImage);
    } catch (IOException e) {
      fail("this test should have passed!");
    }
  }

  /**
   * test save method.
   */
  @Test
  public void testSave() {
    try {
      AbstractImageFormat imagePNG = new ImagePNG(this.testImagePath);
      Image loadedImage = imagePNG.load();

      String path = "test\\res\\testSave.png";
      imagePNG.save(path, loadedImage);

      File savedFile = new File(path);
      assertTrue(savedFile.exists());

    } catch (Exception e) {
      fail("this test should have passed!");
    }
  }

  /**
   * test save method.
   */
  @Test
  public void testSaveJPG() {
    try {
      AbstractImageFormat imagePNG = new ImagePNG(this.testImagePath);
      Image loadedImage = imagePNG.load();

      String path = "test\\res\\testSaveJPG.jpg";
      imagePNG.save(path, loadedImage);

      File savedFile = new File(path);
      assertTrue(savedFile.exists());

    } catch (Exception e) {
      fail("this test should have passed!");
    }
  }

  /**
   * test path empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullPath() {
    String path = "";
    new ImagePNG(path);
    fail("This test should have failed!");
  }
}