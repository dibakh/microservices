package at.nacs.encoderui.view;

import at.nacs.encoderui.communication.EncoderClient;
import at.nacs.encoderui.view.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

  private final EncoderClient encoderClient;

  @ModelAttribute("message")
  Message message() {
    return new Message();
  }

  @GetMapping
  String page() {
    return "home";
  }

  @PostMapping
  String post(@Valid Message message, BindingResult result, RedirectAttributesModelMap redirect) {
    if (result.hasErrors()) {
      return page();
    }
    String plaintext = message.getPlaintext();
    String ciphertext = encoderClient.encode(plaintext);
    redirect.addFlashAttribute("plaintext", plaintext);
    redirect.addFlashAttribute("ciphertext", ciphertext);
    return "redirect:/";
  }

}
