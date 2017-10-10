package log;

import org.springframework.data.repository.CrudRepository;

import log.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long>, LogRepositoryCostum{

    List<Log> findByDataHoraBetween(LocalDateTime data1, LocalDateTime data2);
}