package com.example.controllercrud.Controller;
import com.example.controllercrud.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasktracker")
public class TaskTrackerController {
    ArrayList<TaskTracker>  taskTrackers = new ArrayList<>();
@GetMapping("/get")
    public ArrayList<TaskTracker> getTaskTrackers() {
        return taskTrackers;
    }
    @PostMapping("/add")
    public String addTaskTracker(@RequestBody TaskTracker taskTracker) {
    taskTrackers.add(taskTracker);
    return "taskTracker added successfully" ;
    }

    @PutMapping("/updateStatus/{index}/{status}")
    public String updateTaskStatus(@PathVariable int index, @PathVariable String status) {
        if (index >= 0 && index < taskTrackers.size()) {
            taskTrackers.get(index).setStatus(status);
            return "taskTracker status updated to: " + status;
        } else {
            return "taskTracker not found";
        }
    }


    @DeleteMapping("/delete/{index}")
public String deleteTaskTracker(@PathVariable int index) {
    taskTrackers.remove(index);
    return "taskTracker deleted successfully" ;

}
    @GetMapping("/search")
    // @RequestParam annotation is used to bind the title parameter to the query parameter in the URL.
    //The required = false attribute for title parameter is optional. If it's not provided, the method will still execute.
    public ArrayList<TaskTracker> searchByTitle(@RequestParam(required = false) String title) {
        if (title == null || title.isEmpty()) {
            return taskTrackers;
        } else {
            return (ArrayList<TaskTracker>) taskTrackers.stream()
                    .filter(t -> t.getTitle().equalsIgnoreCase(title))
                    .collect(Collectors.toList());
        }
    }

}
