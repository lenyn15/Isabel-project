package academic.system.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "nivel",
        uniqueConstraints = {
                @UniqueConstraint( name = "name_unique",
                                   columnNames = "nombre" )
        } )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Level {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_nivel",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el nombre del nivel" )
    @Column( name = "nombre",
             length = 45,
             nullable = false )
    private String name;
}
