package io.swagger.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String index() {
		return "redirect:index.html";
	}


	@RequestMapping(value = "/swagger")
	public String swagger() {
		return "redirect:swagger-ui.html";
	}

	@RequestMapping(value = "/api")
	public String api() {
		return "redirect:swagger-ui.html";
	}
}
