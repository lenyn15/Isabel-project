package academic.system.enrollment.model;

import lombok.Data;

@Data
public class Section {
    private int id;
    private String letter;
    private int capacity;
    private Grade grade;
}
