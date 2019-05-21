package sjsu.edu.cmpe275.api.persistence.model.mapper;

import org.springframework.data.repository.CrudRepository;

import sjsu.edu.cmpe275.api.persistence.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
