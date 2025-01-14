import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class Servicio {
    private String monedaOrigen;
    private String monedaDestino;
    private Float valorOrigen;

    public Respuesta consultaApi(String monedaOrigen, String monedaDestino, Float valorOrigen) {
        URI direccion = URI
                .create("https://v6.exchangerate-api.com/v6/885d0e156963f3dd77e417be/pair/"
                        + monedaOrigen + "/"
                        + monedaDestino + "/"
                        + valorOrigen);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Respuesta.class);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un error en la conexión");
        }
    }
}