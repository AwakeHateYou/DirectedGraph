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
    /** String with wrong input file error message. */
    public static final String WRONG_INPUT_FILE = "Wrong format of the input file.";
    /**
     * Gets filename and returns contents
     * of the file.
     * @param filename - name of the input file.
     * @return - new string with file contents.
     */
    public static String fileToString(String filename) {
        String string = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            if (line.isEmpty()){
                throw new IOException();
            }
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            string = sb.toString();
            br.close();
        } catch (IOException e) {
            catchIOException(e);
        } catch (NullPointerException e) {
            catchNullPointerException();
        }
        return string;
    }

    /**
     * Prints all info about graph
     * to console.
     * @param outStrings - string with all information about graph.
     */
    public static void infoAboutGraphToConsole(ArrayList<String> outStrings) {
        for(String line: outStrings){
            System.out.print(line);
        }
    }

    /**
     * Prints sequences and their
     * extra data to the file.
     * @param filename - name of the output file.
     * @param outStrings - string with all information about graph.
     */
    public static void infoAboutGraphToFile(String filename, ArrayList<String> outStrings) {
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            for (String line : outStrings) {
                printWriter.print(line);
            }
            printWriter.close();
        } catch (IOException e) {
            catchIOException(e);
        }
    }

    /**
     * Catches IOException.
     * @param e - IOException.
     */
    private static void catchIOException(IOException e) {
        System.out.println(e.getMessage());
        System.out.println(WRONG_INPUT_FILE);
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


}
