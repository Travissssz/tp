package seedu.healthbud.parser;

import seedu.healthbud.exception.InvalidDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateParser {

    private static final String OUTPUT_FORMAT = "dd MMM yyyy";

    // List of possible input date formats to try
    private static final List<String> INPUT_FORMATS = Arrays.asList(
            "dd/MM/yy",         // e.g., 12/11/25 -> 12 Nov 2025 (due to pivot date)
            "dd/MM/yyyy",       // e.g., 25/12/2023
            "dd-MM-yy",         // e.g., 12-11-23 -> 12 Nov 2023 (due to pivot date)
            "dd-MM-yyyy",       // e.g., 25-12-2023
            "dd MM yy",        // e.g., 25 12 23
            "dd MM yyyy",      // e.g., 25 12 2023
            "ddMMyy",       // e.g., 25 Nov 23
            "ddMMyyyy",     // e.g., 25112023
            "dd MMM yy",       // e.g., 25 Dec 23
            "dd MMM yyyy",     // e.g., 25 Dec 2023
            "dd MMMM yy",      // e.g., 25 December 23
            "dd MMMM yyyy",    // e.g., 25 December 2023
            "MMM dd, yyyy",     // e.g., Dec 25, 2023
            "MMMM dd, yyyy",    // e.g., December 25, 2023
            "MMM dd yyyy",
            "MMM dd, yy"        // e.g., Dec 25, 23


    );

    public static String formatDate(String inputDate) throws InvalidDateFormatException {
        assert inputDate != null : "Input date should not be null";

        if (inputDate == null || inputDate.trim().isEmpty()) {
            throw new InvalidDateFormatException();
        }

        for (String format : INPUT_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false); // Strict parsing

                // If using a two-digit year format, explicitly set the two-digit year start to 2000.
                if (format.contains("yy") && !format.contains("yyyy")) {
                    sdf.set2DigitYearStart(new SimpleDateFormat("yyyy").parse("2000"));
                }
                Date date = sdf.parse(inputDate);

                SimpleDateFormat outputSdf = new SimpleDateFormat(OUTPUT_FORMAT);
                return outputSdf.format(date);
            } catch (ParseException e) {
                // Try next format
            }
        }
        throw new InvalidDateFormatException();
    }
}
