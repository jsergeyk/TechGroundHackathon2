package com.hackathon.presentation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author SergeyK
 */
@ControllerAdvice
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(final Throwable throwable, HttpServletRequest request, Model model) {
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			model.addAttribute("error_status", status);
			String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
			model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
		}
        return "error";
    }

}
