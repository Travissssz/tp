package seedu.healthbud.command.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import seedu.healthbud.exception.InvalidBMIException;
import seedu.healthbud.parser.BMIParser;

public class BMICommandTest {

    private String executeAndCaptureOutput(BMICommand command) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        command.execute();
        System.setOut(originalOut);
        return outContent.toString().trim();
    }



    @Test
    void parse_validInput_expectSuccess() throws InvalidBMIException {
        String input = "bmi /w 70 /h 1.75";
        BMICommand command = BMIParser.parse(input);
        String output = executeAndCaptureOutput(command);
        String expected = "Your BMI is: 22.86, you are of normal weight.";
        assertEquals(expected, output);
    }

    @Test
    void parse_missingParameters_expectInvalidBmiException() {
        String input = "bmi 70";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected InvalidBMIException when missing '/h' or height.");
    }

    @Test
    void parse_nonNumericValues_expectHealthBudException() {
        String input = "bmi /w seventy /h onepointsevenfive";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Invalid number format for weight or height.");
    }

    @Test
    void parse_zeroOrNegativeValues_expectHealthBudException() {
        // Zero weight
        String zeroWeightInput = "bmi /w 0 /h 1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(zeroWeightInput));

        // Negative height
        String negativeHeightInput = "bmi /w 70 /h -1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(negativeHeightInput),
                "Expected InvalidBMIException for negative height.");
    }

    @Test
    void parse_underweightInput_expectUnderweightMessage() throws InvalidBMIException {
        String input = "bmi /w 50 /h 1.75";
        BMICommand command = BMIParser.parse(input);
        String output = executeAndCaptureOutput(command);
        // 50 / (1.75^2) ~ 16.33
        String expected = "Your BMI is: 16.33, you are underweight.";
        assertEquals(expected, output);
    }

    @Test
    void parse_overweightInput_expectOverweightMessage() throws InvalidBMIException {
        String input = "bmi /w 80 /h 1.75";
        BMICommand command = BMIParser.parse(input);
        String output = executeAndCaptureOutput(command);
        // 80 / (1.75^2) ~ 26.12
        String expected = "Your BMI is: 26.12, you are overweight.";
        assertEquals(expected, output);
    }

    @Test
    void parse_obeseInput_expectObeseMessage() throws InvalidBMIException {
        String input = "bmi /w 100 /h 1.75";
        BMICommand command = BMIParser.parse(input);
        String output = executeAndCaptureOutput(command);
        // 100 / (1.75^2) ~ 32.65
        String expected = "Your BMI is: 32.65, you are obese.";
        assertEquals(expected, output);
    }


    @Test
    void parse_missingWeightParameter_expectInvalidBMIException() {
        String input = "bmi /h 1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception when missing weight parameter.");
    }

    @Test
    void parse_missingHeightParameter_expectInvalidBMIException() {
        String input = "bmi /w 70";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception when missing height parameter.");
    }

    @Test
    void parse_weightWithDecimal_expectInvalidBMIException() {
        // Weight must match "\\d+" so decimals are not allowed.
        String input = "bmi /w 70.5 /h 1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception for weight with decimals.");
    }

    @Test
    void parse_invalidHeightFormat_expectInvalidBMIException() {
        // Height should match "^\\d+(\\.\\d+)?$"
        String input = "bmi /w 70 /h 1.75abc";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception for height with non-digit characters.");
    }

    @Test
    void parse_boundaryHeightLower_expectSuccess() throws InvalidBMIException {
        // Height exactly at 0.2 is allowed.
        String input = "bmi /w 70 /h 0.2";
        BMICommand command = BMIParser.parse(input);
        // BMI = 70 / (0.2^2) = 70 / 0.04 = 1750.00, classified as obese.
        String output = executeAndCaptureOutput(command);
        String expected = "Your BMI is: 1750.00, you are obese.";
        assertEquals(expected, output);
    }

    @Test
    void parse_boundaryHeightUpper_expectSuccess() throws InvalidBMIException {
        // Height exactly at 3.0 is allowed.
        String input = "bmi /w 70 /h 3.0";
        BMICommand command = BMIParser.parse(input);
        // BMI = 70 / (3.0^2) = 70 / 9 = 7.78 (rounded) and classified as underweight.
        String output = executeAndCaptureOutput(command);
        String expected = "Your BMI is: 7.78, you are underweight.";
        assertEquals(expected, output);
    }

    @Test
    void parse_weightOne_expectSuccess() throws InvalidBMIException {
        // Minimal weight that is positive.
        String input = "bmi /w 1 /h 1.75";
        BMICommand command = BMIParser.parse(input);
        // BMI = 1 / (1.75^2) ~ 0.33, underweight.
        String output = executeAndCaptureOutput(command);
        String expected = "Your BMI is: 0.33, you are underweight.";
        assertEquals(expected, output);
    }

    @Test
    void parse_emptyWeightParameter_expectInvalidBMIException() {
        // Empty weight value should throw exception.
        String input = "bmi /w  /h 1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception for empty weight parameter.");
    }

    @Test
    void parse_upperCaseFlags_expectInvalidBMIException() {
        // If the parser does not normalize flags, uppercase flags will not match.
        String input = "bmi /W 70 /H 1.75";
        assertThrows(InvalidBMIException.class, () -> BMIParser.parse(input),
                "Expected exception for uppercase parameter flags.");
    }
}
