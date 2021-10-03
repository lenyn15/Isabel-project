package academic.system.enrollment.model;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private int id;
    private String name;
    private String surname;
    private String dni;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Date date_birth;
    private String state;
}
