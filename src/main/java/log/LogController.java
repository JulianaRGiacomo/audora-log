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
                           @RequestParam (value = "dataFinal", defaultValue = "") String tempDataFinal,
                           @RequestParam (value = "horaInicial", defaultValue = "") String tempHoraInicial,
                           @RequestParam (value = "horaFinal", defaultValue = "") String tempHoraFinal) {
        Iterable<Log> logs;
        List<Map<String, Object>> json = new ArrayList<>();

        boolean datas = false, all;
        LocalDateTime dataInicial = null, dataFinal = null;

        tempDataInicial = tempDataInicial.equals("") || tempHoraInicial.equals("") ?
                "" : tempDataInicial + tempHoraInicial;
        tempDataFinal = tempDataFinal.equals("") || tempHoraFinal.equals("")?
                "" : tempDataFinal + tempHoraFinal;

        if(!tempDataInicial.equals("") && !tempDataFinal.equals("")) {
            dataInicial = Log.formatarPrazo(tempDataInicial);
            dataFinal = Log.formatarPrazo(tempDataFinal);
            if (dataFinal == null && dataInicial == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato das datas inválidas");

            datas = true;
        }
        all = !produto.equals("") && !cliente.equals("") && !categoria.equals("");

        if(produto.equals("") && cliente.equals("") && categoria.equals("") && !datas) {
            logs = logRepository.findAll();
        } else if(datas && all) {
            logs = logRepository.findByDataHoraBetweenAndClienteAndCategoriaAndProduto(dataInicial, dataFinal,
                    cliente, categoria, produto);
        } else if(!datas) {
            if(all)
                logs = logRepository.findByClienteAndCategoriaAndProduto(cliente, categoria, produto);
            else {
                if(!produto.equals("") && !cliente.equals(""))
                    logs = logRepository.findByProdutoAndCliente(produto, cliente);
                else if(!produto.equals("") && !categoria.equals(""))
                    logs = logRepository.findByProdutoAndCategoria(produto, categoria);
                else if(!cliente.equals("") && !categoria.equals(""))
                    logs = logRepository.findByClienteAndCategoria(cliente, categoria);
                else if(!produto.equals(""))
                    logs = logRepository.findByProduto(produto);
                else if(!categoria.equals(""))
                    logs = logRepository.findByCategoria(categoria);
                else
                    logs = logRepository.findByCliente(cliente);
            }

        }  else {
            if(produto.equals("") && cliente.equals("") && categoria.equals(""))
                logs = logRepository.findByDataHoraBetween(dataInicial, dataFinal);
            else {
                if(!produto.equals("") && !cliente.equals(""))
                    logs = logRepository.findByDataHoraBetweenAndProdutoAndCliente(dataInicial, dataFinal,
                            produto, cliente);
                else if(!produto.equals("") && !categoria.equals(""))
                    logs = logRepository.findByDataHoraBetweenAndProdutoAndCategoria(dataInicial, dataFinal,
                            produto, categoria);
                else if(!cliente.equals("") && !categoria.equals(""))
                    logs = logRepository.findByDataHoraBetweenAndClienteAndCategoria(dataInicial, dataFinal,
                            cliente, categoria);
                else if(!produto.equals(""))
                    logs = logRepository.findByDataHoraBetweenAndProduto(dataInicial, dataFinal, produto);
                else if(!categoria.equals(""))
                    logs = logRepository.findByDataHoraBetweenAndCategoria(dataInicial, dataFinal, categoria);
                else
                    logs = logRepository.findByDataHoraBetweenAndCliente(dataInicial, dataFinal, cliente);
            }
        }

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