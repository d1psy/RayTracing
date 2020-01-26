package diploma.geometry;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanvasTest {

    private Canvas canvas;
    private Color color;

    @Given("Canvas {int}, {int}")
    public void createCanvas(int width, int height) {
        canvas = new Canvas(width, height);
    }

    @Given("Color to paint {float} {float} {float}")
    public void createCanvas(float red, float green, float blue) {
        color = new Color(red, green, blue);
    }

    @When("Write to pixel {int} {int}")
    public void writeToPixel(int width, int height) {
        canvas.writeToPixel(width, height, color);
    }

    @Then("Canvas width is {int}")
    public void canvasWidth(int width) {
        assertEquals(width, canvas.getWidth());
    }

    @Then("Canvas height is {int}")
    public void canvasHeight(int height) {
        assertEquals(height, canvas.getHeight());
    }

    @Then("Every pixel of color is 0")
    public void compareColor() {
        Color expected = new Color();
        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                assertEquals(expected, canvas.getColors()[i][j]);
            }
        }
    }

    @Then("Pixel at {int} {int} is {float} {float} {float}")
    public void pixelEquls(int width, int height, float red, float green, float blue) {
        Color expected = new Color(red, green, blue);
        Color actual = canvas.getColors()[width][height];
        assertEquals(expected, actual);
    }
}
