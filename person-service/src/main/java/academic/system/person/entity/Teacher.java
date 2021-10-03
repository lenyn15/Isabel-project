package academic.system.person.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table( name = "docente" )
@PrimaryKeyJoinColumn( name = "id_docente" )
@Data
@EqualsAndHashCode( callSuper = true )
@NoArgsConstructor
public class Teacher extends Person {

    @Positive( message = "La edad debe ser mayor a 0" )
    @Column( name = "edad",
             nullable = false )
    private int age;

    @NotEmpty( message = "Por favor, digite la especialidad" )
    @Column( name = "especialidad",
             length = 45,
             nullable = false )
    private String occupation;

    @Column( name = "estado",
             length = 15,
             nullable = false )
    private String state;

    public Teacher( int id,
                    @NotEmpty( message = "Por favor, ingrese el nombre de la persona" ) String name,
                    @NotEmpty( message = "Por favor, ingrese el apellido de la persona" ) String surname,
                    @NotEmpty( message = "Por favor, ingrese el DNI de la persona" ) @Size( min = 8,
                                                                                            max = 8,
                                                                                            message = "El tamaño del DNI es 8" ) String dni,
                    @NotEmpty( message = "Por favor, seleccione un género" ) String gender,
                    @NotEmpty( message = "Por favor, ingrese el número de teléfono de la persona" ) @Size( min = 9,
                                                                                                           max = 9,
                                                                                                           message = "El tamaño del telefono es 9" ) String phone,
                    @NotEmpty( message = "Por favor, ingrese en el correo de la persona" ) String email,
                    @NotEmpty( message = "Por favor, ingrese la direccion de la persona" ) String address,
                    int age,
                    String occupation,
                    String state ) {
        super( id, name, surname, dni, gender, phone, email, address );
        this.age = age;
        this.occupation = occupation;
        this.state = state;
    }
}
