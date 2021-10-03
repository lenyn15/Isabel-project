package academic.system.person.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table( name = "estudiante" )
@PrimaryKeyJoinColumn( name = "id_estudiante" )
@Data
@EqualsAndHashCode( callSuper = true )
@NoArgsConstructor
public class Student extends Person {

    @Temporal( TemporalType.DATE )
    @Column( name = "fecha_nacimiento",
             nullable = false )
    private Date date_birth;

    @Column( name = "estado",
             length = 15,
             nullable = false )
    private String state;

    public Student( int id,
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
                    Date date_birth,
                    String state ) {
        super( id, name, surname, dni, gender, phone, email, address );
        this.date_birth = date_birth;
        this.state = state;
    }
}
