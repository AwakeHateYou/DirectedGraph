import java.io.File;
import java.util.ArrayList;

/**
 * Created by etere on 11.10.2015.
 */
public class Parser {
    /** Source string. */
    String source;
    public String getSource(){return source;}

    String[] linesSource;

    public String[] getLinesSource() {
        return linesSource;
    }
    /** Vertex amount. */
    int vertexAmount;

    public int getVertexAmount(){return vertexAmount;}
    /**
     * Constructor.
     * @param source - source string.
     */
    public Parser(String source) {
        this.source = source;
    }

    /**
     * Converts string to string array
     * separated by line separator.
     * @param source - source string.
     * @return - string array.
     */
    private String[] stringToStringArray(String source) {
        return source.split(System.lineSeparator());
    }

    /**
     * Remove exstra spaces
     * from source string
     */
    public void removeExtraSpaces(){
        String[] lines = stringToStringArray(source);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line.trim().replaceAll("\\s+", " "));
            stringBuilder.append(System.lineSeparator());
        }
        source = stringBuilder.toString();
        linesSource = stringToStringArray(source);
    }

    /**
     * Read amount of source sequences from
     * source string.
     * @return - amount of vertex.
     * @throws Exception - IOException,
     *                     NumberFormatException.
     */
    private int readVertexAmount() throws Exception {
        return Integer.valueOf(stringToStringArray(source)[0]);
    }

    /**
     * Starts parsing process.
     * @throws Exception IOException,
     *                   NumberFormatException,
     *                   ArrayIndexOutOfBoundsException,
     *                   NullPointerException,
     *                   DecreasingSequenceException.
     */
    public void parseString() throws Exception {
        vertexAmount = readVertexAmount();
        removeExtraSpaces();
       // System.out.println(vertexAmount);
       // for(String line : linesSource)
        //    System.out.println(line);

    }

}
