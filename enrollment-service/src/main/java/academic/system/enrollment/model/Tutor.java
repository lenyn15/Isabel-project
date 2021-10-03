package academic.system.enrollment.model;

import lombok.Data;

@Data
public class Tutor {
    private int id;
    private String name;
    private String surname;
    private String dni;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private int age;
    private String marital_status;
    private String career;
    private String workspace;
    private String state;
}
