package diploma.math;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FloatUtils {

    public static float format(float x, String pattern) {
        return Float.parseFloat(new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.US)).format(x));
    }
}
