<template>
    <div class="container">
        <Bar title="Matriculas" place="Operaciones" subplace="Matriculas" />

        <div class="form">
            <div class="title-form">
                <h3>Datos Academicos</h3>
                <a class="button" @click="chooseAction">Guardar</a>
            </div>
            <div class="body-form">
                <div class="lbl-period">
                    <label>Periodo</label>
                    <select v-model="periodSelected">
                        <option
                            v-for="period in periods"
                            :key="period.id"
                            :value="period"
                        >
                            {{ period.year }}
                        </option>
                    </select>
                </div>

                <div class="lbl-level">
                    <label>Nivel</label>
                    <select
                        v-model="levelSelected"
                        id="level"
                        @click="filterGrade(levelSelected.id)"
                    >
                        <option
                            v-for="level in levels"
                            :key="level.id"
                            :value="level"
                        >
                            {{ level.name }}
                        </option>
                    </select>
                </div>

                <div class="lbl-grade">
                    <label>Grado</label>
                    <select
                        v-model="gradeSelected"
                        id="grade"
                        @click="filterSection(gradeSelected.id)"
                    >
                        <option
                            v-for="grade in grades"
                            :key="grade.id"
                            :value="grade"
                        >
                            {{ grade.name }}
                        </option>
                    </select>
                </div>

                <div class="lbl-section">
                    <label>Seccion</label>
                    <select v-model="sectionSelected" id="section">
                        <option
                            v-for="section in sections"
                            :key="section.id"
                            :value="section"
                        >
                            {{ section.letter }}
                        </option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form">
            <div class="title-form">
                <h3>Datos Alumno</h3>
            </div>
            <div class="body-form">
                <div class="lbl-period">
                    <label>Nombre</label>
                    <input
                        type="text"
                        v-model="studentSelected.name"
                        disabled
                        placeholder="Nombre del estudiante"
                    />
                </div>

                <div class="lbl-level">
                    <input
                        type="text"
                        v-model="dniStudent"
                        placeholder="DNI del estudiante"
                    />
                </div>

                <div class="lbl-grade">
                    <a class="button" @click="foundStudent(dniStudent)">
                        Buscar
                    </a>
                </div>
            </div>
        </div>

        <div class="form">
            <div class="title-form">
                <h3>Datos Matricula</h3>
            </div>
            <div class="body-form">
                <div class="first-entry">
                    <div class="lbl-student-tutor">
                        <label class="student">Repitente</label>
                        <select v-model="bodyEnrollment.repeating">
                            <option disabled value="">Seleccione</option>
                            <option>Si</option>
                            <option>No</option>
                        </select>

                        <label class="tutor">Apoderado</label>
                        <input
                            type="text"
                            v-model="tutorSelected.name"
                            disabled
                            placeholder="Apoderado"
                        />
                    </div>

                    <div class="lbl-shift-fees">
                        <label>Turno</label>
                        <select v-model="bodyEnrollment.shift">
                            <option disabled value="">Seleccione</option>
                            <option>Mañana</option>
                            <option>Tarde</option>
                        </select>

                        <label class="fees">Numero de cuotas</label>
                        <input type="text" disabled value="8" />
                    </div>
                    <div class="lbl-detail">
                        <label>Detalle</label>
                        <input
                            type="text"
                            v-model="bodyEnrollment.detail"
                            placeholder="Detalle de matricula"
                        />
                    </div>
                </div>
                <div class="second-entry">
                    <div class="box-container">
                        <table>
                            <tr>
                                <td>
                                    <input
                                        type="text"
                                        v-model="dniTutor"
                                        placeholder="DNI del apoderado"
                                        class="search"
                                    />
                                </td>
                                <td>
                                    <a
                                        class="icon-search"
                                        @click="foundTutor(dniTutor)"
                                    >
                                        <i class="fas fa-search"></i>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-data">
            <div class="table-nav">
                <div>
                    <h3>Listado de Matriculas</h3>
                </div>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Estudiante</th>
                            <th>Grado</th>
                            <th>Seccion</th>
                            <th>Turno</th>
                            <th>N° Cuotas</th>
                            <th class="actions">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="enrollment in enrollments"
                            :key="enrollment.id"
                        >
                            <td class="id-d">{{ enrollment.id }}</td>
                            <td>{{ enrollment.student.name }}</td>
                            <td>{{ enrollment.grade.name }}</td>
                            <td>{{ enrollment.section.letter }}</td>
                            <td>{{ enrollment.shift }}</td>
                            <td>{{ enrollment.fees_number }}</td>
                            <td class="middle">
                                <a
                                    class="btn btn-edit"
                                    @click="setEnrollment(enrollment.id)"
                                >
                                    <i class="fas fa-edit"></i>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Bar from "../components/bar/Bar.vue";
export default {
    components: { Bar },
    data() {
        return {
            periods: [],
            periodSelected: {},
            levels: [],
            levelSelected: {},
            grades: [],
            gradeSelected: {},
            sections: [],
            sectionSelected: {},
            studentSelected: {},
            tutorSelected: {},
            enrollments: [],
            bodyEnrollment: {
                repeating: "",
                shift: "",
                detail: "",
                studentId: 0,
                tutorId: 0,
                periodId: 0,
                levelId: 0,
                gradeId: 0,
                sectionId: 0,
            },
            dniStudent: "",
            dniTutor: "",
            idenrollment: 0,
            isEdit: false,
        };
    },
    methods: {
        getEnrollment(id) {
            axios.get("http://localhost:8092/enrollment/" + id).then((resp) => {
                this.bodyEnrollment = resp.data;
                this.periodSelected = this.bodyEnrollment.period;
                this.levelSelected = this.bodyEnrollment.level;
                this.studentSelected = this.bodyEnrollment.student;
                this.tutorSelected = this.bodyEnrollment.tutor;
                this.gradeSelected = this.bodyEnrollment.grade;
                this.sectionSelected = this.bodyEnrollment.section;
            });
        },
        getEnrollments() {
            axios.get("http://localhost:8092/enrollment/list").then((resp) => {
                console.log(resp.data);
                this.enrollments = resp.data;
            });
        },
        getPeriods() {
            axios.get("http://localhost:8093/period/list").then((resp) => {
                this.periods = resp.data;
            });
        },
        getLevels() {
            axios.get("http://localhost:8093/level/list").then((resp) => {
                this.levels = resp.data;
            });
        },
        getGrades() {
            axios.get("http://localhost:8093/grade/list").then((resp) => {
                this.grades = resp.data;
            });
        },
        getGrade(id) {
            axios.get("http://localhost:8093/grade/" + id).then((resp) => {
                this.gradeSelected = resp.data;
            });
        },
        filterGrade(idL) {
            axios
                .get("http://localhost:8093/grade/list/level/" + idL)
                .then((resp) => {
                    this.grades = resp.data;
                });
        },
        getSections() {
            axios.get("http://localhost:8093/section/list").then((resp) => {
                this.sections = resp.data;
            });
        },
        getSection(id) {
            axios.get("http://localhost:8093/section/" + id).then((resp) => {
                this.sectionSelected = resp.data;
            });
        },
        filterSection(idG) {
            axios
                .get("http://localhost:8093/section/list/grade/" + idG)
                .then((resp) => {
                    this.sections = resp.data;
                });
        },
        foundStudent(dni) {
            axios
                .get("http://localhost:8091/student/search/dni/" + dni)
                .then((resp) => {
                    this.studentSelected = resp.data;
                });
        },
        foundTutor(dni) {
            axios
                .get("http://localhost:8091/tutor/search/dni/" + dni)
                .then((resp) => {
                    this.tutorSelected = resp.data;
                });
        },
        chooseAction() {
            this.bodyEnrollment.studentId = this.studentSelected.id;
            this.bodyEnrollment.tutorId = this.tutorSelected.id;
            this.bodyEnrollment.periodId = this.periodSelected.id;
            this.bodyEnrollment.levelId = this.levelSelected.id;
            this.bodyEnrollment.gradeId = this.gradeSelected.id;
            this.bodyEnrollment.sectionId = this.sectionSelected.id;
            console.log(this.bodyEnrollment);
            if (this.isEdit) {
                this.isEdit = false;
                axios
                    .put(
                        "http://localhost:8092/enrollment/update/" +
                            this.idenrollment,
                        this.bodyEnrollment
                    )
                    .then(() => {
                        window.location.reload();
                    });
            } else {
                axios
                    .post(
                        "http://localhost:8092/enrollment/new",
                        this.bodyEnrollment
                    )
                    .then(() => {
                        window.location.reload();
                    })
                    .catch((error) => console.log(error));
            }
        },
        setEnrollment(id) {
            this.getEnrollment(id);
            this.idenrollment = id;
            this.isEdit = true;
        },
    },
    mounted() {
        this.getPeriods();
        this.getLevels();
        this.getGrades();
        this.getSections();
        this.getEnrollments();
    },
};
</script>

<style lang="scss" scoped>
.search-filter {
    display: flex;
    justify-content: space-between;
    margin-right: 5%;
}

.box-container {
    margin: auto;
    position: relative;
    border: 2px solid #16a7da;
    border-radius: 50px;
    padding-right: 10px;

    table {
        width: 100%;
        height: 100%;
        vertical-align: middle;

        .icon-search {
            color: #16a7da;
            cursor: pointer;
        }
    }

    input {
        width: 200px;
        outline: none;
    }

    select {
        border: none;
    }
}

.search {
    border: none;
    width: 100%;
    height: 100%;
}

.table {
    border-collapse: collapse;
    box-shadow: 0 5px 10px #e1e5ee;
    text-align: left;

    thead {
        box-shadow: 0 5px 10px #e1e5ee;
        background-color: #c60808;
        color: white;
    }

    th {
        padding: 1rem 2rem;
        text-transform: uppercase;
        font-size: 0.8rem;
        font-weight: 900;
    }

    td {
        padding: 0.8rem 2rem;
        font-size: 0.8rem;
    }

    .id-d {
        text-decoration: none;
        color: #2962ff;
    }

    .actions {
        text-align: center;
    }

    .status {
        border-radius: 0.2rem;
        background-color: red;
        padding: 0.2rem 1rem;
        text-align: center;

        &.enrolled {
            background-color: #c8e6c9;
            color: #388e3c;
        }

        &.pendent {
            background-color: #fff0c2;
            color: #a68b00;
        }

        &.inactive {
            background-color: #ffcdd2;
            color: #c62828;
        }
    }

    tr:nth-child(even) {
        background-color: #f4f6fb;
    }
}

.wrapper {
    display: flex;
    align-items: center;
    margin-right: 5rem;
    box-sizing: border-box;

    a {
        margin: 0 20px;
        cursor: pointer;
    }

    .filter-all {
        border: 2px solid #2e93b7;
        padding: 10px 15px;
        border-radius: 50%;
    }

    .filter-all:hover {
        background-color: #2e93b7;
        color: white;
        border: none;
    }

    .filter-active {
        border: 2px solid #1aa538;
        padding: 10px 15px;
        border-radius: 50%;
    }

    .filter-active:hover {
        background-color: #1aa538;
        color: white;
        border: none;
    }

    .filter-pendent {
        border: 2px solid #a68b00;
        padding: 10px 15px;
        border-radius: 50%;
    }

    .filter-pendent:hover {
        background-color: #a68b00;
        color: white;
        border: none;
    }

    .filter-inactive {
        border: 2px solid #c60808;
        padding: 10px 15px;
        border-radius: 50%;
    }

    .filter-inactive:hover {
        background-color: #c60808;
        color: white;
        border: none;
    }
}
</style>

<style scoped>
.container {
    margin: 15px 15px 10px 35px;
    box-sizing: border-box;
}

.form,
.table-data {
    margin: 10px 0;
    background-color: white;
    border-radius: 6px;
    border: 1px solid #b7c6eb;
}

.table-data {
    padding: 0 1rem 1rem 1rem;
    box-sizing: border-box;
}

.table-container {
    display: grid;
    justify-content: center;
    align-items: center;
}

.body-form {
    display: flex;
    justify-content: center;
}

.body-form select {
    width: 150px;
}

.body-form .first-entry select {
    width: 100px;
}

.title-form {
    display: flex;
    justify-content: space-between;
}

.title-form .button {
    text-decoration: none;
    display: block;
    width: max-content;
    padding: 0.5rem 0.8rem;
    background-color: #2e93b7;
    border-radius: 0.5rem;
    font-weight: 600;
    transition: 0.2s;
    margin: 15px 0;
    border-radius: 5px;
    color: white;
    cursor: pointer;
}

.title-form .button:hover {
    box-shadow: 0.995225px 0px 9.95225px rgba(0, 0, 0, 0.15);
}

.form .title-form,
.body-form .first-entry {
    padding: 0 15px;
}

label {
    text-align: left;
}

input {
    margin-left: 15px;
    padding: 5px;
    width: 200px;

    border: 1px solid rgba(0, 0, 0, 0.15);
    box-sizing: border-box;
    border-radius: 6px;
    font-family: "Quicksand", sans-serif;
}

select {
    width: 200px;
    margin-left: 15px;
    font-family: "Quicksand", sans-serif;
    border: 1px solid rgba(0, 0, 0, 0.15);
    box-sizing: border-box;
    border-radius: 6px;
    padding: 5px;
}

.lbl-period,
.lbl-grade {
    margin: 0 20px;
}

.lbl-grade .button {
    text-decoration: none;
    display: block;
    padding: 0.25rem 0.55rem;
    background-color: #378a49;
    border-radius: 0.5rem;
    font-weight: 600;
    transition: 0.2s;
    border-radius: 5px;
    color: white;
    cursor: pointer;
}

.lbl-period,
.lbl-level,
.lbl-grade,
.lbl-section {
    margin-bottom: 15px;
}

.lbl-shift-fees .fees {
    margin-left: 15px;
}

.lbl-shift-fees {
    margin: 10px 0 10px 0;
}

.lbl-detail {
    margin: 0 0 15px 0;
}

.lbl-detail label {
    margin-left: -10px;
}

.lbl-detail input {
    width: 468px;
}

.lbl-student-tutor .student {
    margin-left: -30px;
}
.lbl-student-tutor .tutor {
    margin-left: 70px;
}

.middle {
    box-sizing: border-box;
    text-align: center;
    width: 100%;
}

.btn {
    box-sizing: border-box;
    display: inline-block;
    background-color: #e0d9d9;
    width: 40px;
    height: 40px;
    margin: 10px;
    border-radius: 30%;
    box-shadow: 0 5px 15px -5px #00000070;
    color: #3498db;
    padding-top: 5%;
    padding-left: 2%;
}

.btn-edit:hover {
    background-color: #1aa538;
    color: white;
}

.btn-delete:hover {
    background-color: #c60808;
    color: white;
}

.btn-activate:hover {
    background-color: #3498db;
    color: white;
}

.table-nav {
    display: flex;
    justify-content: space-between;
}
</style>
