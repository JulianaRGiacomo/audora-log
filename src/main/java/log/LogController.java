package log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @GetMapping(path="/log")
    public @ResponseBody
    Iterable<Log> getLogs() {
        return logRepository.findAll();
    }
}