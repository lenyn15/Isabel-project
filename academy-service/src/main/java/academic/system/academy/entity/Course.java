package academic.system.academy.entity;

import academic.system.academy.model.Teacher;
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
@Table( name = "curso" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_curso",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el nombre del curso" )
    @Column( name = "nombre",
             length = 20,
             nullable = false )
    private String name;

    @ManyToOne( fetch = LAZY )
    @JoinColumn( name = "nivel_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private Level level;

    @Column( name = "estado",
             length = 20,
             nullable = false )
    private String state;

    @Column( name = "teacher_id" )
    private int teacherId;

    @Transient
    private Teacher teacher;
}
