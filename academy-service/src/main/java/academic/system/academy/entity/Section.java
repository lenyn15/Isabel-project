package academic.system.academy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "seccion" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_seccion",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el valor de la sección" )
    @Size( min = 1,
           max = 1,
           message = "El tamaño del valor de la seccion es 1" )
    @Column( name = "letra",
             length = 1,
             nullable = false )
    private String letter;

    @Positive( message = "La capacidad debe ser mayor a 0" )
    @Column( name = "capacidad",
             nullable = false )
    private int capacity;

    @ManyToOne( fetch = LAZY )
    @JoinColumn( name = "grado_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private Grade grade;
}
