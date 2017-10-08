package log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/api/log")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getLogs() {
        Iterable<Log> logs;
        List<Map<String, Object>> json = new ArrayList<>();
        logs = logRepository.findAll();

        for(Log log : logs){
            json.add(log.getJson());
        }
        return ResponseEntity.ok(json);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createLogs(@RequestBody Log log) {

        log.setDataHora(LocalDateTime.now());

        String validate =  log.check();
        if(validate != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validate);

        logRepository.save(log);


        return ResponseEntity.ok(log.getJson());
    }
}