package com.skarvo.todosService.jpa;

import com.skarvo.todosService.models.TODO;
import org.springframework.data.repository.CrudRepository;

public interface TODOsRepository extends CrudRepository<TODO, Integer> {
}
