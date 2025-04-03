package seedu.healthbud.parser;

import org.junit.jupiter.api.Test;
import seedu.healthbud.exception.InvalidDateFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateParserTest {

    @Test
    void formatDate_validFormats_expectCorrectOutput() throws InvalidDateFormatException {
        assertEquals("25 Dec 2023", DateParser.formatDate("25-12-2023"));
        assertEquals("25 Dec 2023", DateParser.formatDate("Dec 25 2023"));
        assertEquals("25 Dec 2023", DateParser.formatDate("25 12 2023"));
        assertEquals("25 Dec 2023", DateParser.formatDate("25/12/2023"));

    }

    @Test
    void formatDate_invalidFormat_expectException() {
        assertThrows(InvalidDateFormatException.class, () ->
                DateParser.formatDate("25th of December 2023"));
    }

    @Test
    void formatDate_emptyInput_expectException() {
        assertThrows(InvalidDateFormatException.class, () ->
                DateParser.formatDate(""));
    }

    @Test
    void formatDate_nullInput_expectException() {
        assertThrows(AssertionError.class, () ->
                DateParser.formatDate(null));
    }
}
