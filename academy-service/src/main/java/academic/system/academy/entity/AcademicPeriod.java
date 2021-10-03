package academic.system.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "periodo_academico",
        uniqueConstraints = {
                @UniqueConstraint( name = "year_unique",
                                   columnNames = "año" )
        } )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicPeriod {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_periodo",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, ingrese el año" )
    @Column( name = "año",
             length = 4,
             nullable = false )
    private String year;

    @Temporal( TemporalType.DATE )
    @Column( name = "fecha_inicio",
             nullable = false )
    private Date date_begin;

    @Temporal( TemporalType.DATE )
    @Column( name = "fecha_fin",
             nullable = false )
    private Date date_end;

    @Column( name = "estado",
             length = 20,
             nullable = false )
    private String state;
}
