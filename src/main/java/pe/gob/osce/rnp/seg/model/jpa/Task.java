package pe.gob.osce.rnp.seg.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Task {

    public int id;
    public String name;
    public Boolean completed;
    @JsonFormat(pattern = "MM/dd/yyyy")
    public LocalDate dueDate;

    public Task(){}
}
