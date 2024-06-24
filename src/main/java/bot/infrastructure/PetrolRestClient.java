package bot.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class PetrolRestClient {
    private final RestClient restClient;

    public ResponseEntity<?> getPetrolPrice(String url, Class<?> bodyType) {
        return restClient
                .get()
                .uri(URI.create(url))
                .retrieve()
                .toEntity(bodyType);
    }
}
