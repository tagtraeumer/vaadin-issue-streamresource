package com.lieven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Old controller for the JSP UI.
 * 
 * @author lieve
 *
 */
@Controller
public class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/v/main/{category}", method = RequestMethod.GET)
	public String showMainPage(ModelMap map, @PathVariable String category) {
		map.put("category", category);
		return "v-main";
	}

}
