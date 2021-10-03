package academic.system.enrollment.model;

import lombok.Data;

@Data
public class Grade {
    private int id;
    private String name;
    private String state;
    private Level level;
}
