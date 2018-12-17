import {Component, OnInit} from '@angular/core';
import {Task} from "../task.model";
import {TaskService} from "../task.service";

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService) {
    this.taskService.getTask().subscribe((tasks: any[])=>{
        this.tasks = tasks;
    }, (error) => console.log(error));
  }

  ngOnInit() {

  }

  onTaskChange(e, task){
    //this.taskService.save(task, event.target.checked).suscribe();
  }

  getDueDateLabel(task: Task){
    return task.completed ? 'label-success' : 'label-primary'
  }

}
