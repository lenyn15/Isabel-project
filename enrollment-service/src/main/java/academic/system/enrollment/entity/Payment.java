package academic.system.enrollment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.TemporalType.*;

@Entity
@Table( name = "pago" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_pago",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, seleccione el lugar de pago" )
    @Column( name = "lugar_pago",
             length = 15,
             nullable = false )
    private String place_payment;

    @NotNull( message = "Por favor, seleccione la fecha de operacion" )
    @JsonFormat( shape = STRING,
                 pattern = "dd/MM/yyyy" )
    @Column( name = "fecha_operacion",
             nullable = false )
    private LocalDate date_operation;

    @NotNull( message = "Por favor, ingrese la hora de la operación" )
    @Temporal( TIME )
    @Column( name = "hora_operacion",
             nullable = false )
    private Date hour_operation;

    @Positive( message = "El monto debe ser mayor a 0" )
    @Column( name = "monto",
             nullable = false )
    private int amount;

    @Column( name = "descripcion",
             length = 15 )
    private String description;

    @ManyToOne( fetch = LAZY )
    @JoinColumn( name = "compromisos_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private CommitmentPayments commitmentPayments;

    @ManyToOne( fetch = LAZY )
    @JoinColumn( name = "matricula_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private Enrollment enrollment;
}
