package academic.system.person.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table( name = "persona",
        uniqueConstraints = {
                @UniqueConstraint( name = "dni_unique",
                                   columnNames = "dni" ),
                @UniqueConstraint( name = "email_unique",
                                   columnNames = "correo" )
        } )
@Inheritance( strategy = JOINED )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el nombre de la persona" )
    @Column( name = "nombre",
             length = 45,
             nullable = false )
    private String name;

    @NotEmpty( message = "Por favor, ingrese el apellido de la persona" )
    @Column( name = "apellido",
             length = 45,
             nullable = false )
    private String surname;

    @NotEmpty( message = "Por favor, ingrese el DNI de la persona" )
    @Size( min = 8,
           max = 8,
           message = "El tamaño del DNI es 8" )
    @Column( length = 8,
             nullable = false )
    private String dni;

    @NotEmpty( message = "Por favor, seleccione un género" )
    @Column( name = "sexo",
             length = 9,
             nullable = false )
    private String gender;

    @NotEmpty( message = "Por favor, ingrese el número de teléfono de la persona" )
    @Size( min = 9,
           max = 9,
           message = "El tamaño del telefono es 9" )
    @Column( name = "telefono",
             length = 9,
             nullable = false )
    private String phone;

    @NotEmpty( message = "Por favor, ingrese en el correo de la persona" )
    @Column( name = "correo",
             length = 45,
             nullable = false )
    private String email;

    @NotEmpty( message = "Por favor, ingrese la direccion de la persona" )
    @Column( name = "direccion",
             length = 45,
             nullable = false )
    private String address;
}
