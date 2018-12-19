package pe.gob.osce.rnp.seg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.model.jpa.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @GetMapping("/tasks")
    public List<Task> retornarTodasLasTareas(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", false, LocalDate.now()));
        tasks.add(new Task(2, "Task 2", false, LocalDate.now()));
        tasks.add(new Task(3, "Task 3", false, LocalDate.now()));
        return tasks;
    }
}
