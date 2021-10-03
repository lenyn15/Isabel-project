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
@Table( name = "apoderado" )
@PrimaryKeyJoinColumn( name = "id_apoderado" )
@Data
@EqualsAndHashCode( callSuper = true )
@NoArgsConstructor
public class Tutor extends Person {

    @Positive( message = "La edad debe ser mayor a 0" )
    @Column( name = "edad",
             nullable = false )
    private int age;

    @NotEmpty( message = "Por favor, seleccione un estado civil" )
    @Column( name = "estado_civil",
             length = 15,
             nullable = false )
    private String marital_status;

    @NotEmpty( message = "Por favor, ingrese la profesion del apoderado" )
    @Column( name = "profesion",
             length = 45,
             nullable = false )
    private String career;

    @NotEmpty( message = "Por favor, ingrese el centro de trabajo" )
    @Column( name = "centro_trabajo",
             length = 45,
             nullable = false )
    private String workspace;

    @Column( name = "estado",
             length = 15,
             nullable = false )
    private String state;

    public Tutor( int id,
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
                  String marital_status,
                  String career,
                  String workspace,
                  String state ) {
        super( id, name, surname, dni, gender, phone, email, address );
        this.marital_status = marital_status;
        this.career = career;
        this.workspace = workspace;
        this.state = state;
    }
}
