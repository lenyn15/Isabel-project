package academic.system.enrollment.entity;

import academic.system.enrollment.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "matricula" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue( strategy = IDENTITY )
    @Column( name = "id_matricula",
             updatable = false )
    private int id;

    @NotEmpty( message = "Por favor, seleccione una opcion" )
    @Column( name = "repitente",
             length = 2,
             nullable = false )
    private String repeating;

    @NotEmpty( message = "Por favor, seleccione el turno" )
    @Column( name = "turno",
             length = 15,
             nullable = false )
    private String shift;

    @Column( name = "numero_cuotas",
             nullable = false )
    private int fees_number;

    @Column( name = "detalle",
             length = 30 )
    private String detail;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "tutor_id")
    private int tutorId;

    @Column(name = "period_id")
    private int periodId;

    @Column(name = "level_id")
    private int levelId;
	
	@Column(name = "grade_id")
    private int gradeId;
	
	@Column(name = "section_id")
    private int sectionId;

    @Transient
    private Student student;

    @Transient
    private Tutor tutor;

    @Transient
    private AcademicPeriod period;

    @Transient
    private Level level;

    @Transient
    private Grade grade;

    @Transient
    private Section section;
}
