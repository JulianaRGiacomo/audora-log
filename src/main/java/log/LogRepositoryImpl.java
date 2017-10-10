package log;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogRepositoryImpl implements LogRepositoryCostum {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Log> findByMultipleParameters(Map<String, String> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> query = cb.createQuery(Log.class);
        Root<Log> log = query.from(Log.class);

        List<Predicate> predicates = new ArrayList<>();

        for(Map.Entry<String,String> map : params.entrySet()) {
            Path<String> chave = log.get(map.getKey());
            Expression<String> valor = cb.literal(map.getValue());
            predicates.add(
                    cb.equal(chave,valor)
            );
        }

        query.select(log).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Log> findByMultipleParametersAndDates(Map<String, String> params, LocalDateTime init, LocalDateTime end) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> query = cb.createQuery(Log.class);
        Root<Log> log = query.from(Log.class);

        List<Predicate> predicates = new ArrayList<>();

        for(Map.Entry<String,String> map : params.entrySet()) {
            Path<String> chave = log.get(map.getKey());
            Expression<String> valor = cb.literal(map.getValue());
            predicates.add(
                    cb.equal(chave,valor)
            );
        }

        Path<LocalDateTime> dataHora = log.get("dataHora");
        Expression<LocalDateTime> initExp = cb.literal(init);
        Expression<LocalDateTime> endExp = cb.literal(end);

        predicates.add(cb.between(dataHora,initExp,endExp));

        query.select(log).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query).getResultList();
    }
}
