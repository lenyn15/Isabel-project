package academic.system.enrollment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "pagos_compromisos" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitmentPayments {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_compromiso",
             updatable = false )
    private int id;

    @Column( name = "detalle",
             length = 15,
             nullable = false )
    private String detail;

    @JsonFormat( shape = STRING,
                 pattern = "dd/MM/yyyy" )
    @Column( name = "fecha_pago" )
    private LocalDate date_payment;

    @NotNull( message = "Por favor, ingrese la fecha de vencimiento" )
    @JsonFormat( shape = STRING,
                 pattern = "dd/MM/yyyy" )
    @Column( name = "fecha_vencimiento",
             nullable = false )
    private LocalDate due_date;

    @Positive( message = "El monto debe ser mayor a 0" )
    @Column( name = "monto",
             nullable = false )
    private int amount;

    @Column( name = "estado",
             length = 15,
             nullable = false )
    private String state;
}
