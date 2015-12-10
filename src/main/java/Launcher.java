import java.util.ArrayList;

/**
 * Name of the project:  Directed graph.
 * Description:          Program reads the data from the input file
 *                       create directed graph and calculate
 *                       amount of vertex, lines and connections between vertex
 *                       and output to the output file or console.
 *
 * Full name of the class: Application launcher.
 * Class description:    Main class. Control activity of all programm.
 *                       Catch NumberFormatException and
 *

 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class Launcher {
    private Graph graph;

    /** String with wrong input file error message. */
    public static final String WRONG_INPUT_FILE = "Wrong format of the input file.";
    /**
     * Constructor with one argument.
     * Called when the output is made in the console.
     * @param input - string with input filename.
     */
    public Launcher(String input){
        IOStreamer.infoAboutGraphToConsole(buildNewGraph(input));
    }
    /**
     * Constructor with two arguments.
     * Called when the output is made in the file.
     * @param input - string with input filename.
     * @param output - string with output filename.
     */
    public Launcher(String input, String output){
        IOStreamer.infoAboutGraphToFile(output, buildNewGraph(input));

    }
    /**
     * Prints help page.
     */
    public static void printHelp(){
        System.out.print(HELP_PAGE);
    }
    /**
     * Main method. Entry point of the class.
     * @param args - input arguments.
     */
    public static void main(String args[]){
        switch(args.length) {
            case 1:
                new Launcher(args[0]);
                break;
            case 2:
                new Launcher(args[0], args[1]);
                break;
            default:
                printHelp();
                return;
        }
    }

    /**
     * Parse input file and builds new graph and his main information.
     * @param input - string with input filename.
     * @return - directed graph.
     */
    private ArrayList<String> buildNewGraph(String input) {
        Parser parser = new Parser(IOStreamer.fileToString(input));
        try {
            parser.parseString();
            graph = new Graph(parser.getLinesSource(), parser.getVertexAmount());
            graph.generateInfoAboutDirectedGraph();
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                catchNumberFormatException((NumberFormatException) e);
            }
            if (e instanceof NullPointerException){
                catchNullPointerException();
            }
        }
        return graph.getOutString();
    }

    /**
     * Catches NumberFormatException.
     * @param e - NumberFormatException.
     */
    private void catchNumberFormatException(NumberFormatException e) {
        System.out.println(e.getMessage());
        System.out.println(WRONG_INPUT_FILE);
        Launcher.printHelp();
        System.exit(0);
    }

    /**
     * Catches NullPointerException.
     */
    private static void catchNullPointerException() {
        System.out.println(WRONG_INPUT_FILE);
        Launcher.printHelp();
        System.exit(0);
    }
    /** String with help page. */
    public static final String HELP_PAGE =
            "<============================ Help page ============================>\n" +
                    "Directed graph - a console application that builds\n" +
                    "from source data new directed graph and all information about it.\n\n" +
                    "Input parameters: [input.txt][output.txt] - input and output files.\n" +
                    "You can leave output blank.\n" +
                    "In this case the output will be on the console.\n\n" +
                    "\tInput file format:\n" +
                    "\t\t%d - amount of vertex in graph \n" +
                    "\t\t%f %f ... - weight of lines \n" +
                    "\tOutput file format:\n" +
                    "\t\tnumbers of lines\n" +
                    "\t\tamount lines from vertex to every vertex\n" +
                    "\t\tshortest path from every vertex to every vertex\n";
}
