package controllers;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.CallService;
import DO.EmailDO;
import DO.EmailForm;

@Controller
public class MailSend {

	/**
	 *  Logging
	 */
	private static final org.apache.log4j.Logger logger = LogManager.getLogger(MailSend.class);

	@RequestMapping(value = "/Email", method = RequestMethod.GET)
	public ModelAndView startApp() {
		ModelMap model = new ModelMap();
		model.addAttribute("service", "Current Service is "+CallService.getMailStrategy().getClass().getSimpleName());
				return new ModelAndView("Email", model);
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView sendMail(@ModelAttribute("form") @Valid EmailForm form, BindingResult result ) {
		System.out.println("Narendra");
		System.out.println();

		System.out.println( form.toString());

		EmailDO data = new EmailDO(null, form.getTo(), form.getSubject(), form.getText());

		boolean resultString = CallService.sendMail(data);
		ModelMap model = new ModelMap();
		if(resultString == true){
			model.addAttribute("resultString", "Your Submission was Succesful");
			model.addAttribute("service", "Sent Via "+CallService.getMailStrategy().getClass().getSimpleName());
		}else{
			model.addAttribute("resultString", "Your Submission Failed!Sorry Try After Some time");
			logger.error(data.toString()+resultString); 
		}
		logger.info(data.toString()+resultString);
		return new ModelAndView("End",model); 		
	}
}
