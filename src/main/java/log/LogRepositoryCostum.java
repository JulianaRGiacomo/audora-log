package log;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface LogRepositoryCostum {

    List<Log> findByMultipleParameters(Map<String,String> params);

    List<Log> findByMultipleParametersAndDates(Map<String,String> params, LocalDateTime init, LocalDateTime end);
}
