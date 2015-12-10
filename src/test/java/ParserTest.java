import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by etere on 10.12.2015.
 */

public class ParserTest {
    /** Parser object. */
    Parser parser;

    /**
     * Setting up unit tests.
     */
    @Before
    public void setUp() {
        parser = new Parser(IOStreamer.fileToString("src\\\\test\\\\java\\\\testInput.txt"));
        parser.removeExtraSpaces();
    }
    /**
     * Test removing extra spaces in string.
     * String can contains no more when one
     * space between numbers.
     */
    @Test
    public void testRemovingExtraSpaces() {
        String source = parser.getSource();
        Pattern pattern = Pattern.compile("\\w[ ]{2,}\\w");
        Matcher matcher = pattern.matcher(source);
        Assert.assertFalse("More when one space founded!", matcher.find());
    }
    /**
     * Tests amount vertex in parser.
     * @throws Exception - exceptions from Parser class.
     */
    @Test
    public void testSequenceNumbers() throws Exception {
        parser.parseString();
        Assert.assertNotNull("Sequence numbers is null!", parser.getVertexAmount());
    }
}

