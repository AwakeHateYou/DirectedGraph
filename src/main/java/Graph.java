import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

/**
 * Name of the project:  Directed graph.
 * Description:
 *
 * Full name of the class: Direct Graph.
 * Class description:    Class for working with files.
 *                       It's can write graph and info about graph to the file,
 *                       read file to the string and print
 *                       graph and info about graph to console.
 *                       If a null object parameter is passed to any method,
 *                       then a NullPointerException will be thrown.
 *                       If an error occurs Input Output, then a IOException
 *                       will be thrown.
 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class Graph {

    /**
     * Adjacency matrix.
     */
    private String[][] matrix;
    final static double EPSILON =  1E-5;

    public ArrayList<String> getOutString() {
        return outString;
    }

    private ArrayList<String> outString = new ArrayList<String>();


    /**
     * Matrix with shortest distance.
     */
    private double[][] distance;

    /**
     * Matrix with paths.
     */
    private int[][] parents;
//    private int start;
//    private int finish;

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
        graphBuilder(source);
    }

    /**
     * Create directed graph
     * from Adjacency matrix
     * @param sourse - list of source string
     */
    private void graphBuilder(String[] sourse) {
        int j = 0;
        matrix = new String[size][size];
        distance = new double[size][size];
        parents = new int[size][size];
        for (String line : sourse) {
            String[] splitLine = line.split(" ");
            if (splitLine.length >= size) {
                if (j < size) {
                    for (int i = 0; i < size; i++) {
                        this.matrix[j][i] = splitLine[i];
                    }
                }
                j++;
            }
        }
    }

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
     * in directed graph.
     * @return - amount of lines
     */
    public void calculateAmountLines() throws Exception{
        int amount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].equals("-")){
                    amount++;
                }
            }
        }
        outString.add("Количество дуг = " + String.valueOf(size*size - amount) + "\n");
    }

    /**
     * Calculate amount lines connected to the every vertex.
     */
    public void amountLinesConnectedToVertex(){
        int amount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[j][i].equals("-")){
                    amount++;
                }
            }
            outString.add("Количество дуг, входящих в " + String.valueOf(i+1)+ " = " + String.valueOf(size - amount) + "\n");
            amount = 0;
        }
    }

    /**
     * Convert matrix from string to double
     * replace i=j elements to 0 and "-" value to Integer.MAX_VALUE/2
     * @throws NumberFormatException
     */
    private void prepareAdjacencyMatrix() throws Exception {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == j) {
                    distance[i][j] = 0;
                }else {
                    if (matrix[i][j].equals("-")) {
                        distance[i][j] = INFINITY;
                    } else {
                        distance[i][j] = Double.parseDouble(matrix[i][j]);
                    }
                }
                parents[i][j] = i;
            }
        }
    }
    public void calculateAllPathsToAllVertex(){

        algorithmFloyd();
        //printGraph(matrix);
        pathAhead();


    }

    /**
     * Generate Adjacency Matrix and calculate amount of lines,
     * amount lines connected to the vector and
     * all shortest parts to every vertex.
     * @throws NumberFormatException
     */
    public void generateInfoAboutDirectedGraph() throws Exception{
        //printGraph(matrix);
        prepareAdjacencyMatrix();
        calculateAmountLines();
        amountLinesConnectedToVertex();
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
