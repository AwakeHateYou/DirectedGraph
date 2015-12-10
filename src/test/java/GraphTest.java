import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Name of the project:  Directed graph.
 * Full name of the class: Graph information Test.
 * Class description:    Unit tests for Graph class.
 * @author Eveny Terentyev.
 * Group: IVT-42BO.
 */
public class GraphTest {
    /**
     * Parser object.
     */
    private Parser parser;
    /**
     * Graph object.
     */
    private Graph graph;
    /**
     * Setting up unit tests.
     */
    @Before
    public void setUp() throws Exception{
        parser = new Parser(IOStreamer.fileToString("src\\\\test\\\\java\\\\testInput.txt"));
        parser.parseString();
        graph = new Graph(parser.getLinesSource(), parser.getVertexAmount());
        graph.prepareAdjacencyMatrix();
    }

    /**
     * Testing generatino String matrix.
     */
    @Test
    public void testBuildingFromSource(){
        Assert.assertFalse("Building is not correct", isBuildingsNotEqual());
    }
    public boolean isBuildingsNotEqual(){
        String[][] matr = {{"1", "1", "1", "1"}, {"4", "-", "5", "6"}, {"2", "3", "4", "7"}, {"2", "3", "4", "3"}};
        boolean check = true;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(matr[i][j].equals(graph.getMatrix()[i][j])){
                    check = false;
                }else{
                    check = true;
                }

            }
        }
        return check;
    }

    /**
     * Testing generation distance matrix.
     */
    @Test
    public void testDistance(){
        double dist[][] = {{0.0, 1.0, 1.1, 1.1}, {4.0, 0.0, 5.0, 5.0}, {2.0, 3.0, 0.0, 7.0}, {2.0, 3.0, 4.0, 0.0}};
        boolean check = true;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(dist[i][j] == graph.getDistance()[i][j]){
                    check = false;
                }else{
                    check = true;
                }

            }
        }
        Assert.assertFalse("Wrong distance", check);
    }
    @Test
    public void testParents(){
        int par[][] = {{0, 0, 0, 0},{1, 1, 1, 1},{2, 2, 2, 2},{3, 3, 3, 3}};
        boolean check = true;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(par[i][j] == graph.getParents()[i][j]){
                    check = false;
                }else{
                    check = true;
                }

            }
        }
        Assert.assertFalse("Wrong distance", check);
    }
    @Test
    public void testAmountLines() throws Exception{
        boolean check = true;
        if (graph.calculateAmountLines() == 15){
            check = false;
        }
        Assert.assertFalse("Wrong amount lines", check);
    }
    @Test
    public void testConnectedAmountLines(){
        int amounts[] = {4, 3, 4, 4};
        boolean check = true;
        for (int i = 0; i < 4; i++){
            if (amounts[i] == graph.calculateAmountLinesConnectedToVertex()[i]){
                check = false;
            }else{
                check = true;
            }

        }
        Assert.assertFalse("Wrong amount connected vertex", check);
    }
    @Test
    public void testFloyd(){
        graph.calculateAllPathsToAllVertex();
        double dist[][] = {{0.0, 1.0, 1.1, 1.1}, {4.0, 0.0, 5.0, 5.0}, {2.0, 3.0, 0.0, 3.0}, {2.0, 3.0, 3.0, 0.0}};
        boolean check = true;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(dist[i][j] == graph.getDistance()[i][j]){
                    check = false;
                }else{
                    check = true;
                }

            }
        }
        Assert.assertFalse("Error with Floyd", check);
    }
}
