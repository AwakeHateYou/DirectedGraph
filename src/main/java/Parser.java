import java.io.File;
import java.util.ArrayList;

/**
 * Name of the project:  Sequence Builder.
 * Description:          Program reads the data from the input file
 *                       create directed graph and calculate
 *                       amount of vertex, lines and connections between vertex
 *                       and output to the output file or console
 *
 * Full name of the class: Parser.
 * Class description:    Program reads the input file
 *                       delete extra spaces, finde amount of vertex
 *                       and give source string
 * @author Andrey Martynov.
 * Group: IVT-42BO.
 */
public class Parser {
    /** Source string. */
    String source;

    /**
     * Getter for source string
     * @return - source string.
     */
    public String getSource(){return source;}

    /**
     * Array for source string.
     */
    String[] linesSource;

    /**
     * Getter for source array.
     * @return - source array.
     */
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
        int amount = Integer.valueOf(stringToStringArray(source)[0].trim().replace("\\s+", " "));
        if (amount > 0){
            return amount;
        }else{
            throw new NotARealNumberException();
        }
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
        checkInput();
    }

    /**
     * Check size of input matrix.
     * @throws Exception - InputMatrixException.
     */
    private void checkInput() throws Exception{
        for (int i = 1; i < vertexAmount; i++){
            String split[] = linesSource[i].split(" ");
            if (split.length < vertexAmount){
                throw new InputMatrixException();
            }
        }
    }
}
