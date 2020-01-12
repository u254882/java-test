package acceptance.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.example.application.PeterApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    PeterApplication peterApplication = new PeterApplication();
    String actualAnswer;

    private static String extractContent(String filePath) {
        Path path = Paths.get(filePath);
        StringBuilder fileContentBuilder = new StringBuilder();

        try {
            Files.readAllLines(path)
                    .forEach(fileContentBuilder::append);
        } catch (IOException e) {

            System.out.println("File read problem");
        }

        return fileContentBuilder.toString();
    }

    @Given("Peter application is running")
    public void startPeterApp() {
        PeterApplication.main(new String[]{});

    }

    @Given("^The basket in file '(.*)' is processed.$")
    public void callApp(String inputFilePath) {
        sendToSystemIn(extractContent(inputFilePath));
        actualAnswer = outContent.toString();
    }

    @Then("^The console output matches the contents of the file '(.*)'.$")
    public void checkBasket(String outputFilePath) {
        String expected = extractContent(outputFilePath);
        assertEquals(expected, actualAnswer);

    }

    private void sendToSystemIn(String userInput) {
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
    }
}
