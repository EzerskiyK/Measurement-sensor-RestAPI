import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String sensorName = "sensor1";
        
        registerSensor(sensorName);

        Random random = new Random();

        double maxTemperature = 100;
        for (int i = 0; i < 500; i++) {
            sendMeasurement(random.nextDouble() * maxTemperature, random.nextBoolean(), sensorName);
        }

    }

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJsonData(url, jsonData);

    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        
        makePostRequestWithJsonData(url,jsonData);
    }

    private static void makePostRequestWithJsonData(String url, Map<String, Object> jsonData) throws RuntimeException {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Успешно");
        }catch (HttpClientErrorException e){
            System.out.println("Ошибка");
            throw new RuntimeException(e.getMessage());

        }

    }
}
