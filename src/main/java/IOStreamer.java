import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Name of the project:  Directed graph.
 * Description:          Program reads the data from the input file
 *                       create directed graph and calculate
 *                       amount of vertex, lines and connections between vertex
 *                       and output to the output file or console.
 *
 * Full name of the class: Input/Output File Streamer.
 * Class description:    Class for working with files.
 *                       It's can write graph and info about graph to the file,
 *                       read file from the string and print
 *                       graph and info about graph to console.
 *                       If a null object parameter is passed to any method,
 *                       then a NullPointerException will be thrown.
 *                       If an error occurs Input Output, then a IOException
 *                       will be thrown.
 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class IOStreamer {
    /**
     * Gets filename and returns contents
     * of the file.
     * @param filename - name of the input file.
     * @return - new string with file contents.
     */
    public static String fileToString(String filename) throws Exception {
        String string = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        if (line.isEmpty()){
            throw new InputMatrixException();
        }
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        string = sb.toString();
        br.close();
        return string;
    }

    /**
     * Prints all info about graph
     * to console.
     * @param graph - directed graph.
     */
    public static void infoAboutGraphToConsole(Graph graph) throws Exception{
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printAmountLines(printWriter, graph);
        printAmountLinesConnectedVector(printWriter, graph);
        printShortestPaths(printWriter, graph);
        printWriter.close();
    }

    /**
     * Prints sequences and their
     * extra data to the file.
     * @param filename - name of the output file.
     * @param graph - directed graph.
     * @throws IOException
     */
    public static void infoAboutGraphToFile(String filename, Graph graph) throws Exception {
            PrintWriter printWriter = new PrintWriter(filename);
            printAmountLines(printWriter, graph);
            printAmountLinesConnectedVector(printWriter, graph);
            printShortestPaths(printWriter, graph);
            printWriter.close();
    }

    /**
     * Print amount of lines to the stream.
     * @param printWriter stream
     * @param graph directed graph
     * @throws Exception IOException
     */
    private static void printAmountLines(PrintWriter printWriter, Graph graph) throws Exception{
        printWriter.print("Количество дуг = " + graph.getAmountLines() + "\n");
    }
    /**
     * Print amount of lines connected to vector to the stream.
     * @param printWriter stream
     * @param graph directed graph
     * @throws Exception IOException
     */
    private static void printAmountLinesConnectedVector(PrintWriter printWriter, Graph graph) throws Exception{
        for (int i = 0; i < graph.getAmountLinesConnectedVertex().length; i++){
            printWriter.print("Количество дуг, входящих в " + (i + 1) + " = " + graph.getAmountLinesConnectedVertex()[i] + "\n");
        }
    }
    /**
     * Print shortest paths.
     * @param printWriter stream
     * @param graph directed graph
     * @throws Exception IOException
     */
    private static void printShortestPaths(PrintWriter printWriter, Graph graph) throws Exception{
        for (ArrayList<Integer> intList : graph.getPaths()){
            if (intList.get(0) != -1) {
                printWriter.print("Путь из " + intList.get(0) + " в " +  intList.get(1) + ":");
                for (int i = 2; i < intList.size(); i++) {
                    printWriter.print(" " + intList.get(i));
                }
                printWriter.println();
            }else{
                printWriter.print("Пути из " + intList.get(1) + " в " + intList.get(2) + " нет\n");
            }
        }
    }
}

