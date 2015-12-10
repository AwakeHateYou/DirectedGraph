/**
 * Name of the project:  Directed graph.
 * Description:          Program reads the data from the input file
 *                       create directed graph and calculate
 *                       amount of vertex, lines and connections between vertex
 *                       and output to the output file or console.
 * Full name of the class: Input matrix exception.
 * Class description:    Exception while size of input matrix is not correct.
 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class InputMatrixException extends Exception{
    /** String with error message. */
    private static final String ERROR_MESSAGE = "Wrong size of input file";

    /**
     * Default constructor.
     * Sets exception message.
     */
    public InputMatrixException() {
        super(ERROR_MESSAGE);
    }
}
