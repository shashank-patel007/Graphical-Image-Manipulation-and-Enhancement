import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import controller.commands.AbstractImageFormat;
import controller.commands.ImageJPG;
import model.Image;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * The ImageJPGTest class contains JUnit tests for the
 * functionality of the ImageJPG class. It includes tests for
 * loading JPG images, saving images in JPG format, saving images in PNG format,
 * and handling scenarios where the file path is empty.
 */
public class ImageJPGTest {
  private final String testImagePath = "res\\nyc.jpg";
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
      AbstractImageFormat imageJPG = new ImageJPG(this.testImagePath);
      Image loadedImage = imageJPG.load();
      assertNotNull(loadedImage);
    } catch (IOException e) {
      fail("this test should have passed!");
    }
  }

  /**
   * test save method.
   */
  @Test
  public void testSaveJPG() {
    try {
      AbstractImageFormat imageJPG = new ImageJPG(this.testImagePath);
      Image loadedImage = imageJPG.load();

      String path = "test\\res\\testSaveJPG.jpg";
      imageJPG.save(path, loadedImage);

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
  public void testSavePNG() {
    try {
      AbstractImageFormat imageJPG = new ImageJPG(this.testImagePath);
      Image loadedImage = imageJPG.load();

      String path = "test\\res\\testSavePNG.png";
      imageJPG.save(path, loadedImage);

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
    new ImageJPG(path);
    fail("This test should have failed!");
  }
}