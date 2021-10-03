package academic.system.enrollment.model;

import lombok.Data;

import java.util.Date;

@Data
public class AcademicPeriod {

    private int id;
    private String year;
    private Date date_begin;
    private Date date_end;
    private String state;
}
