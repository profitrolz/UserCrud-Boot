package academy.kata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException (HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDate.now());
        mav.addObject("status", HttpStatus.NOT_FOUND);
        mav.addObject("error", "User not found");
        mav.setViewName("errorPage");
        return mav;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException (HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDate.now());
        mav.addObject("status", HttpStatus.BAD_REQUEST);
        mav.addObject("error", "Illegal argument exception");
        mav.setViewName("errorPage");
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException (HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDate.now());
        mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("error", exception.getMessage());
        mav.setViewName("errorPage");
        return mav;
    }

}
