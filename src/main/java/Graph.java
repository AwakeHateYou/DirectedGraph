import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

/**
 * Name of the project:  Directed graph.
 * Description:          Program reads the data from the input file
 *                       create directed graph and calculate
 *                       amount of vertex, lines and connections between vertex
 *                       and output to the output file or console.
 *
 * Full name of the class: Directed graph.
 * Class description:    Class for working with graph.
 *                       It's can generate service matrix, and write all information
 *                       in to ArrayList<String>.
 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class Graph {

    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * Adjacency matrix.
     */
    private String[][] matrix;
    /**
     * Small value/
     */
    final static double EPSILON =  1E-5;

    /**
     * Get out strings.
     * @return - out strings.
     */
    public ArrayList<String> getOutString() {
        return outString;
    }

    /**
     * Out strings.
     */
    private ArrayList<String> outString = new ArrayList<String>();

    /**
     * Getter for distance matrix.
     * @return - distance matrix.
     */
    public double[][] getDistance() {
        return distance;
    }

    /**
     * Getter for parents matrix.
     * @return - parents matrix.
     */
    public int[][] getParents() {
        return parents;
    }

    /**
     * Matrix with shortest distance.
     */
    private double[][] distance;

    /**
     * Matrix with paths.
     */
    private int[][] parents;
    /**
     *Size of adjacency matrix.
     */
    private int size;
    /**
     * Count with local infinity and max count of line.
     */
    private int INFINITY = Integer.MAX_VALUE / 2;

    /**
     * Constructor.
     * @param source - list of source string
     * @param size - size of adjacency matrix
     */
    public Graph(String[] source, int size) {
        this.size = size;
        buildFromSource(source);
    }

    /**
     * Create directed graph
     * from Adjacency matrix
     * @param sourse - list of source string.
     */
    private void buildFromSource(String[] sourse) {
        int j = 0;
        matrix = new String[size][size];
        distance = new double[size][size];
        parents = new int[size][size];
        for (int i = 1; i <= size; i++) {
            String[] splitLine = sourse[i].split(" ");
            if (j < size) {
                for (int k = 0; k < size; k++) {
                    this.matrix[j][k] = splitLine[k];
                }
            }
            j++;
        }
    }

    /**
     * Print graph to the console.
     * @param matrix - matrix with graph.
     */
    public void printGraph(String[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

    }

    /**
     * Calculate amount of lines
     * in directed graph and write to ArrayList.
     * @return - amount of lines.
     */
    public int calculateAmountLines() throws Exception{
        int amount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].equals("-")){
                    amount++;
                }
            }
        }
        outString.add("Количество дуг = " + String.valueOf(size*size - amount) + "\n");
        return (size*size - amount);
    }

    /**
     * Calculate amount lines connected to the every vertex
     * and write to the ArrayList.
     * @return - array with amounts.
     */
    public int[] calculateAmountLinesConnectedToVertex(){
        int amount = 0;
        int[] amountLines = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[j][i].equals("-")){
                    amount++;
                }
            }
            amountLines[i] = (size-amount);
            outString.add("Количество дуг, входящих в " + String.valueOf(i+1)+ " = " + String.valueOf(size - amount) + "\n");
            amount = 0;
        }
        return amountLines;
    }

    /**
     * Convert matrix from string to double
     * replace i = j elements to 0 and "-" value to Integer.MAX_VALUE/2
     * @throws NumberFormatException, NotARealNumberException
     */
    public void prepareAdjacencyMatrix() throws Exception {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j].equals("-")) {
                    distance[i][j] = INFINITY;
                }else {
                    distance[i][j] = Double.parseDouble(matrix[i][j]);
                    if (distance[i][j] <= 0){
                        throw new NotARealNumberException();
                    } else if (i == j){
                        distance[i][j] = 0;
                    }

                }
                parents[i][j] = i;
            }
        }
    }

    /**
     * Calculate shortest path from every vertex to every vertex
     * and write to the ArrayList.
     */
    public void calculateAllPathsToAllVertex(){
        algorithmFloyd();
        pathAhead();
    }

    /**
     * Generate Adjacency Matrix and calculate amount of lines,
     * amount lines connected to the vector and
     * all shortest parts to every vertex.
     * @throws NumberFormatException
     */
    public void generateInfoAboutDirectedGraph() throws Exception{
        printGraph(matrix);
        prepareAdjacencyMatrix();
        calculateAmountLines();
        calculateAmountLinesConnectedToVertex();
        calculateAllPathsToAllVertex();

    }

    /**
     * Write shortest path from every vertex
     * to every vertex in ArrayList.
     */
    private void pathAhead(){
        for (int start = 0; start < size; start++) {
            for (int finish = 0; finish < size; ++finish) {
                if (start != finish) {
                    if (distance[start][finish] < INFINITY) {
                        outString.add("Путь из " + String.valueOf(start + 1)+ " в " + String.valueOf(finish + 1) + " =");

                        path(finish, start);
                        outString.add("\n");

                    } else
                        outString.add("Пути из " + String.valueOf(start + 1) + " в " + String.valueOf(finish + 1) + " нет\n");
                }
            }
        }
    }

    /**
     * Create distence and parents matrix from source matrix.
     * Need fo Floyd algorithm.
     */
    private void algorithmFloyd(){
        for (int k = 0; k < size; ++k) {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if ((distance[i][j] - EPSILON) > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        parents[i][j] = parents[k][j];
                    }
                }
            }
        }
    }

    /**
     * Generate shortest path and write to ArrayList.
     * @param vertex - final position
     * @param start - start position
     */
    void path(int vertex, int start)
    {
        if (vertex == start)
        {
            outString.add(" " + String.valueOf(vertex + 1));
        }
        else
        {
            path(parents[start][vertex], start);
            outString.add(" " + String.valueOf(vertex + 1));
        }
    }
}
