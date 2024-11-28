package example;

import java.util.Random;
import java.util.stream.Stream;

import org.neo4j.graphdb.Node;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

public class MyComplexModel {
    @Context
    public Log log;

    /**
     * This procedure takes a Node and returns a random Double.
     *
     * @param node The node to process
     * @return A RandomDouble instance containing a random double value.
     */
    @Procedure(name = "example.myComplexModel")
    @Description("Get a random double for a given node.")
    public Stream<RandomDouble> myComplexModel(@Name("node") Node node) {
        Random random = new Random();
        Double randomDouble = random.nextDouble();
        return Stream.of(new RandomDouble(randomDouble));
    }

    public static class RandomDouble {
        public Double value;

        public RandomDouble(Double value) {
            this.value = value;
        }
    }
} 