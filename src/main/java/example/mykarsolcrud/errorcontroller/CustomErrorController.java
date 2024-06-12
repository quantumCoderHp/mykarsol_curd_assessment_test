package example.mykarsolcrud.errorcontroller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    @RequestMapping("/error/404")
    public String handle404Error() {
        return "404";
    }

    public String getErrorPath() {
        return "/error";
    }
}
