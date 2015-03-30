package controllers;

import org.apache.log4j.LogManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Error redirect
 * @author nbidari
 *
 */
@RestController
public class ErrorController {

	/**
	 *  Logging
	 */
	private static final org.apache.log4j.Logger logger = LogManager.getLogger(ErrorController.class);
	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public String error() {
		logger.error("ErrorPage");
		return "redirect:/End.jsp";
	}
    
}
