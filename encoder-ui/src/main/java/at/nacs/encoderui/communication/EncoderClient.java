package at.nacs.encoderui.communication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EncoderClient {

  private final RestTemplate restTemplate;

  @Value("${encoder.endpoint.url}")
  private String url;

  public String encode(String plaintext) {
    return restTemplate.postForObject(url, plaintext, String.class);
  }

}
