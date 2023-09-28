package com.skarvo.todosService.controllers;

import com.skarvo.todosService.helpers.CompletedTODOsHelper;
import com.skarvo.todosService.jpa.TODOsRepository;
import com.skarvo.todosService.models.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/todo")
public class TODOsController {

    @Autowired
    private CompletedTODOsHelper completedTODOsHelper;

    @Autowired
    private TODOsRepository repo;


    @GetMapping("/")
    @ResponseBody
    public Iterable<TODO> listItems() {
        final Iterable<TODO> todos = repo.findAll();

        todos.forEach(todo -> {
            todo.setCompleted(completedTODOsHelper.getStatus(todo.getId()));
        });
        return todos;
    }

    @PostMapping("")
    public Object createItem(@ModelAttribute final TODO todo) {
        repo.save(todo);
        completedTODOsHelper.saveStatus(todo.getId(), todo.isCompleted());
        return "redirect:/";
    }


    @GetMapping(value = "/mark")
    public Object mark(@RequestParam("id") final Integer id,
                               @RequestParam("completed") final Boolean completed) {
        completedTODOsHelper.saveStatus(id, completed);
        return "redirect:/";
    }

}
