package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CallService;
import DO.EmailDO;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * calling /sendJsonMail with JSON data in POST will return a String 
 * which tells the result.
 * @author nbidari
 *
 */
@Controller
@RequestMapping("/sendJsonMail")
public class APIController {

	/**
	 *  Logging
	 */
	private static final org.apache.log4j.Logger logger = LogManager.getLogger(MailSend.class);

	/**
	 * Post request which will write output response of success/failure or wrong Input
	 * @param postPayload
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String sendJsonMail(@RequestBody String postPayload, HttpServletRequest request, HttpServletResponse response) {
		EmailDO data = parse(postPayload);  
		Map<String, Object> resultObj = new HashMap<String, Object>();            

		if(data == null){
			resultObj.put("result", "Wrong Input");
			resultObj.put("service", "Failed");
		}else{
			resultObj.put("result", CallService.sendMail(data));
			resultObj.put("service", "Sent Via "+CallService.getMailStrategy().getClass().getSimpleName());
		}


		String rt = JSONObject.toJSONString(resultObj);
		response.setContentType("application/json");
        response.setContentLength(rt.length());
        try {
			response.getWriter().write(rt);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * This function Parses the request as per EmailDO function.
	 * @param postPayload
	 * @return
	 */
	private EmailDO parse(String postPayload) {
		ObjectMapper mapper = new ObjectMapper();
		EmailDO emailDo = null;
		try {
			emailDo = mapper.readValue(postPayload, EmailDO.class);
		} catch (Exception e){
			logger.error(e.getLocalizedMessage());

		}
		return emailDo;
	}
}
