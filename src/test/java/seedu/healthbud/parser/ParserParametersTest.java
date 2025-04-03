package seedu.healthbud.parser;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserParametersTest {

    @Test
    void parseParameters_cardioParams_expectCorrectMapping() {
        String input = "/s 1.5 /i 2.0 /t 30 /d 2024-04-01";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("1.5", params.get("s"));
        assertEquals("2.0", params.get("i"));
        assertEquals("30", params.get("t"));
        assertEquals("2024-04-01", params.get("d"));
        assertEquals(4, params.size());
    }

    @Test
    void parseParameters_goalParams_expectCorrectMapping() {
        String input = "/cal 2000 /w 8 /kg 70";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("2000", params.get("cal"));
        assertEquals("8", params.get("w"));
        assertEquals("70", params.get("kg"));
        assertEquals(3, params.size());
    }

    @Test
    void parseParameters_mealParams_expectCorrectMapping() {
        String input = "/cal 300 /d 2024-04-01 /t 12:00";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("300", params.get("cal"));
        assertEquals("2024-04-01", params.get("d"));
        assertEquals("12:00", params.get("t"));
        assertEquals(3, params.size());
    }

    @Test
    void parseParameters_pbParams_expectCorrectMapping() {
        String input = "/w 80 /d 2024-04-01";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("80", params.get("w"));
        assertEquals("2024-04-01", params.get("d"));
        assertEquals(2, params.size());
    }

    @Test
    void parseParameters_waterParams_expectCorrectMapping() {
        String input = "/ml 500 /d 2024-04-01 /t 10:00";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("500", params.get("ml"));
        assertEquals("2024-04-01", params.get("d"));
        assertEquals("10:00", params.get("t"));
        assertEquals(3, params.size());
    }

    @Test
    void parseParameters_workoutParams_expectCorrectMapping() {
        String input = "/r 4 /s 3 /d 2024-04-01 /w 60";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("4", params.get("r"));
        assertEquals("3", params.get("s"));
        assertEquals("2024-04-01", params.get("d"));
        assertEquals("60", params.get("w"));
        assertEquals(4, params.size());
    }

    @Test
    void parseParameters_multiWordValue_expectConcatenatedString() {
        String input = "/desc grilled chicken breast /cal 400";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("grilled chicken breast", params.get("desc")); // this triggers the space-append
        assertEquals("400", params.get("cal"));
        assertEquals(2, params.size());
    }

    @Test
    void parseParameters_outOfOrder_expectCorrectMapping() {
        String input = "/d 2024-04-01 /t 08:00 /cal 350";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("2024-04-01", params.get("d"));
        assertEquals("08:00", params.get("t"));
        assertEquals("350", params.get("cal"));
        assertEquals(3, params.size());
    }

    @Test
    void parseParameters_emptyInput_expectEmptyMap() {
        String input = "";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertTrue(params.isEmpty());
    }

    @Test
    void parseParameters_paramWithoutValue_expectEmptyString() {
        String input = "/cal";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("", params.get("cal"));
        assertEquals(1, params.size());
    }

    @Test
    void parseParameters_extraSpaces_expectTrimmedValues() {
        String input = "/ml     1000     /t     09:00  ";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertEquals("1000", params.get("ml"));
        assertEquals("09:00", params.get("t"));
        assertEquals(2, params.size());
    }

    @Test
    void parseParameters_onlySpaces_expectEmptyMap() {
        String input = "     ";
        Map<String, String> params = ParserParameters.parseParameters(input);
        assertTrue(params.isEmpty());
    }
}
