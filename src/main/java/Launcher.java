import java.util.ArrayList;

/**
 * Created by Awake on 20.09.2015.
 */
public class Launcher {
    private Graph graph;

    /** String with wrong input file error message. */
    public static final String WRONG_INPUT_FILE = "Wrong format of the input file.";

    public Launcher(String input){
        IOStreamer.infoAboutGraphToConsole(buildNewGraph(input));
    }
    public Launcher(String input, String output){
        IOStreamer.infoAboutGraphToFile(output, buildNewGraph(input));

    }
    /**
     * Prints help page.
     */
    public static void printHelp(){

    }

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
     * Parse input file and builds new sequences.
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

}
