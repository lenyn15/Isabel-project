package academic.system.academy.model;

import lombok.Data;

@Data
public class Teacher {

    private int id;
    private String name;
    private String surname;
    private String dni;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private int age;
    private String occupation;
    private String state;
}
