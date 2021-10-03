package academic.system.academy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "grado",
        uniqueConstraints = {
                @UniqueConstraint( name = "name_unique",
                                   columnNames = "nombre" )
        } )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_grado",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el nombre del grado" )
    @Column( name = "nombre",
             length = 45,
             nullable = false )
    private String name;

    @Column( name = "estado",
             length = 20,
             nullable = false )
    private String state;

    @ManyToOne( fetch = LAZY )
    @JoinColumn( name = "nivel_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private Level level;
}
