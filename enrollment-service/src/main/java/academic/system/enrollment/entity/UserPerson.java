package academic.system.enrollment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint( name = "dni_unique",
                                   columnNames = "dni" ),
                @UniqueConstraint( name = "user_unique",
                                   columnNames = "nombre_usuario" )
        } )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPerson {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_usuario",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el nombre" )
    @Column( name = "nombre",
             length = 50,
             nullable = false )
    private String name;

    @NotEmpty( message = "Por favor, ingrese el apellido" )
    @Column( name = "apellido",
             length = 50,
             nullable = false )
    private String surname;

    @NotEmpty( message = "Por favor, ingrese el DNI de la persona" )
    @Size( min = 8,
           max = 8,
           message = "El tamaño del DNI es 8" )
    @Column( length = 8,
             nullable = false )
    private String dni;

    @NotEmpty( message = "Por favor, ingrese el cargo" )
    @Column( name = "cargo",
             length = 30,
             nullable = false )
    private String role;

    @NotEmpty( message = "Por favor, ingrese un nombre de usuario" )
    @Column( name = "nombre_usuario",
             length = 45,
             nullable = false )
    private String username;

    @NotEmpty( message = "Por favor, digite una contraseña" )
    @Size( min = 4,
           max = 10,
           message = "El tamaño de la contraseñas debe estar entre 4 y 10" )
    @Column( name = "clave",
             length = 10,
             nullable = false )
    private String password;

    @Column( name = "estado",
             length = 15,
             nullable = false )
    private String state;
}
