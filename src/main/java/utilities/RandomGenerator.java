package utilities;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {
    public static String randomestring()
    {
        String generatedstring=RandomStringUtils.randomAlphabetic(8);
        return(generatedstring);
    }

    public static String randomeNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }
}
