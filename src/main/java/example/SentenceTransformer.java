package example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

//import org.json.JSONObject;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

public class SentenceTransformer {
    @Context
    public Log log;

    private static final String API_ENDPOINT = "http://your-sentence-transformer-api-endpoint/similarity";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static class SimilarityOutput {
        public Double similarity;

        public SimilarityOutput(Double similarity) {
            this.similarity = similarity;
        }
    }

    /**
     * 这个过程接收两个句子并返回它们之间的相似度。
     *
     * @param sentence1 第一个句子
     * @param sentence2 第二个句子
     * @return 包含两个句子之间相似度的SimilarityOutput实例。
     */
    @Procedure(name = "example.sentenceTransformer")
    @Description("获取两个句子之间的相似度。")
    public Stream<SimilarityOutput> sentenceTransformer(@Name("sentence1") String sentence1, @Name("sentence2") String sentence2) {
        try {
            double similarity = calculateSimilarity(sentence1, sentence2);
            return Stream.of(new SimilarityOutput(similarity));
        } catch (IOException | InterruptedException e) {
            log.error("调用SentenceTransformer API时发生错误", e);
            return Stream.of(new SimilarityOutput(-1.0)); // 返回一个错误值
        }
    }

    private double calculateSimilarity(String sentence1, String sentence2) throws IOException, InterruptedException {
        // TODO(guozixu): remove debug code later
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("sentence1", sentence1);
//        requestBody.put("sentence2", sentence2);
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(API_ENDPOINT))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() == 200) {
//            JSONObject jsonResponse = new JSONObject(response.body());
//            return jsonResponse.getDouble("similarity");
//        } else {
//            log.error("API调用失败,状态码: " + response.statusCode());
//            throw new IOException("API调用失败");
//        }
        return 1.0;
    }
} 