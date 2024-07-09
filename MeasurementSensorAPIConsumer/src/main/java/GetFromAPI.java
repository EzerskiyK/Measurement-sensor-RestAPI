import dto.MeasurementResponseDTO;
import org.springframework.web.client.RestTemplate;


public class GetFromAPI {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements";
        MeasurementResponseDTO jsonResponse = restTemplate.getForObject(url, MeasurementResponseDTO.class);

        if (jsonResponse == null || jsonResponse.getMeasurements() == null) {
            System.out.println("Данных не найдено");
        }else {
            for (int i = 0; i < jsonResponse.getMeasurements().size(); i++) {
                System.out.println(jsonResponse.getMeasurements().get(i));
            }
        }

    }
}
