import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The ImageViewTest class contains JUnit tests for the
 * functionality of the ImageView class. It includes tests for
 * the constructor, getting user input command, and displaying messages.
 */
public class ImageViewTest {
  private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();

  private final PrintStream out = System.out;

  @Before
  public void setup() {
    System.setOut(new PrintStream(outResult));
  }

  @After
  public void reset() {
    System.setOut(out);
  }

  /**
   * test Constructor.
   */
  @Test
  public void testConstructor() {
    try {
      new ImageView();
    } catch (Exception e) {
      fail("This test should have passed!");
    }
  }

  @Test
  public void testGetCommand() {
    String inputCommand = "load sample.png testImage";
    ByteArrayInputStream inResult = new ByteArrayInputStream(inputCommand.getBytes());
    System.setIn(inResult);

    ImageView view = new ImageView();
    assertEquals(inputCommand, view.getCommand());
  }

  @Test
  public void testDisplay() {
    String message = "display message!";

    ImageView view = new ImageView();
    view.display(message);

    assertEquals(message + System.lineSeparator(), outResult.toString());
  }
}