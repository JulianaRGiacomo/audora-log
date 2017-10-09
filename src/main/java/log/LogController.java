package log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping(path="/api/log")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getLogs(@RequestParam (value = "produto", defaultValue = "") String produto,
                           @RequestParam (value = "cliente", defaultValue = "") String cliente,
                           @RequestParam (value = "categoria", defaultValue = "") String categoria,
                           @RequestParam (value = "dataInicial", defaultValue = "") String tempDataInicial,
                           @RequestParam (value = "dataFinal", defaultValue = "") String tempDataFinal) {
        Iterable<Log> logs;
        List<Map<String, Object>> json = new ArrayList<>();

//        boolean valido= false, datas = false;
//        LocalDateTime dataInicial, dataFinal;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        if(!produto.equals("")) {
//            map.put("produto", produto);
//            valido = true;
//        }
//        if(!cliente.equals("")) {
//            map.put("cliente", cliente);
//            valido = true;
//        }
//        if(!categoria.equals("")) {
//            map.put("categoria", categoria);
//            valido = true;
//        }
//        if(!tempDataInicial.equals("") && !tempDataFinal.equals("")) {
//            dataInicial = Log.formatarPrazo(tempDataInicial);
//            dataFinal = Log.formatarPrazo(tempDataFinal);
//            if(dataFinal == null && dataInicial == null)
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datas inválidas");
//            datas = true;
//        }
//
//        if(valido) {
//            if(datas){
//                logs = logRepository.
//            }
//        }
//        else {
            logs = logRepository.findAll();

            for(Log log : logs){
                json.add(log.getJson());
            }
            return ResponseEntity.ok(json);
//        }


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