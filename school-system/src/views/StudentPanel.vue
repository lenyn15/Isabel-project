<template>
    <div class="container">
        <Bar title="Estudiantes" place="Mantenimiento" subplace="Estudiante" />

        <div class="form">
            <div class="title-form">
                <h3>Datos</h3>
                <a class="button" @click="chooseAction">Guardar</a>
            </div>
            <div class="body-form">
                <div class="first-entry">
                    <div class="lbl-name">
                        <label>Nombre</label>
                        <input
                            type="text"
                            v-model="bodyStudent.name"
                            placeholder="Nombre del estudiante"
                        />
                    </div>

                    <div class="lbl-surname">
                        <label>Apellidos</label>
                        <input
                            type="text"
                            v-model="bodyStudent.surname"
                            placeholder="Apellidos del estudiante"
                        />
                    </div>

                    <div class="lbl-dni-sex">
                        <label>DNI</label>
                        <input
                            type="text"
                            v-model="bodyStudent.dni"
                            placeholder="DNI"
                        />

                        <label>Sexo</label>
                        <select v-model="bodyStudent.gender">
                            <option disabled value="">
                                Seleccione un genero
                            </option>
                            <option>MASCULINO</option>
                            <option>FEMENINO</option>
                        </select>
                    </div>

                    <div class="lbl-email">
                        <label>Correo</label>
                        <input
                            type="email"
                            v-model="bodyStudent.email"
                            placeholder="Correo del estudiante"
                        />
                    </div>
                </div>
                <div class="second-entry">
                    <div class="lbl-dob">
                        <label>Fecha de Nacimiento</label>
                        <input type="date" v-model="bodyStudent.date_birth" />
                    </div>

                    <div class="lbl-address">
                        <label>Dirección</label>
                        <input
                            type="text"
                            v-model="bodyStudent.address"
                            placeholder="Direccion del estudiante"
                        />
                    </div>

                    <div class="lbl-phone-age">
                        <label>Telefono</label>
                        <input
                            type="text"
                            v-model="bodyStudent.phone"
                            placeholder="Telefono del estudiante"
                        />
                    </div>
                </div>
            </div>
        </div>

        <div class="table-data">
            <div class="table-nav">
                <div>
                    <h3>Listado de Estudiantes</h3>
                </div>
                <div class="search-filter">
                    <div class="wrapper">
                        <a class="filter-all" @click="getStudents">
                            <i class="fas fa-reply-all"></i>
                        </a>
                        <a
                            class="filter-active"
                            @click="filterState(activeState)"
                        >
                            <i class="fas fa-check"></i>
                        </a>
                        <a
                            class="filter-pendent"
                            @click="filterState(pendentState)"
                        >
                            <i class="fas fa-hourglass-half"></i>
                        </a>
                        <a
                            class="filter-inactive"
                            @click="filterState(inactiveState)"
                        >
                            <i class="far fa-times-circle"></i>
                        </a>
                    </div>
                    <div class="box-container">
                        <table>
                            <tr>
                                <td>
                                    <input
                                        type="text"
                                        v-model="nameSearch"
                                        placeholder="Nombre"
                                        class="search"
                                    />
                                </td>
                                <td>
                                    <a
                                        class="icon-search"
                                        @click="foundStudent(nameSearch)"
                                    >
                                        <i class="fas fa-search"></i>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>DNI</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Estado</th>
                            <th class="actions">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="student in students" :key="student.id">
                            <td class="id-d">{{ student.id }}</td>
                            <td>{{ student.name }}</td>
                            <td>{{ student.surname }}</td>
                            <td>{{ student.dni }}</td>
                            <td>{{ student.phone }}</td>
                            <td>{{ student.email }}</td>
                            <td>
                                <p
                                    class="status"
                                    v-bind:class="
                                        student.state === 'PENDIENTE'
                                            ? 'pendent'
                                            : student.state === 'MATRICULADO'
                                            ? 'active'
                                            : 'inactive'
                                    "
                                >
                                    {{ student.state }}
                                </p>
                            </td>
                            <td class="middle">
                                <a
                                    class="btn btn-edit"
                                    @click="setStudent(student.id)"
                                >
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a
                                    class="btn btn-delete"
                                    @click="deleteStudent(student.id)"
                                >
                                    <i class="fas fa-user-slash"></i>
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
            students: [],
            bodyStudent: {
                name: "",
                surname: "",
                dni: "",
                gender: "",
                email: "",
                date_birth: "",
                address: "",
                phone: "",
            },
            activeState: "MATRICULADO",
            pendentState: "PENDIENTE",
            inactiveState: "INACTIVO",
            nameSearch: "",
            idstudent: 0,
            isEdit: false,
        };
    },
    methods: {
        getStudent(id) {
            axios.get("http://localhost:8091/student/" + id).then((resp) => {
                this.bodyStudent = resp.data;
            });
        },
        getStudents() {
            axios.get("http://localhost:8091/student/list").then((resp) => {
                this.students = resp.data;
            });
        },
        foundStudent(name) {
            axios
                .get("http://localhost:8091/student/search/name/" + name)
                .then((resp) => {
                    this.students = resp.data;
                });
        },
        filterState(state) {
            axios
                .get("http://localhost:8091/student/filter/" + state)
                .then((resp) => {
                    this.students = resp.data;
                });
        },
        chooseAction() {
            if (this.isEdit) {
                this.isEdit = false;
                axios
                    .put(
                        "http://localhost:8091/student/update/" +
                            this.idstudent,
                        this.bodyStudent
                    )
                    .then(() => {
                        window.location.reload();
                    });
            } else {
                axios
                    .post("http://localhost:8091/student/new", this.bodyStudent)
                    .then(() => {
                        window.location.reload();
                    })
                    .catch((error) => console.log(error));
            }
        },
        setStudent(id) {
            this.getStudent(id);
            this.idstudent = id;
            this.isEdit = true;
        },
        deleteStudent(id) {
            axios
                .delete("http://localhost:8091/student/delete/" + id)
                .then(() => {
                    window.location.reload();
                });
        },
    },
    mounted() {
        this.getStudents();
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
        padding: 0.7rem 0.8rem;
        text-transform: uppercase;
        font-size: 0.8rem;
        font-weight: 900;
    }

    td {
        padding: 0.5rem 0.8rem;
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

        &.active {
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
    width: 343px;

    border: 1px solid rgba(0, 0, 0, 0.15);
    box-sizing: border-box;
    border-radius: 6px;
    font-family: "Quicksand", sans-serif;
}

select {
    width: 165px;
    margin-left: 15px;
    font-family: "Quicksand", sans-serif;
    border: 1px solid rgba(0, 0, 0, 0.15);
    box-sizing: border-box;
    border-radius: 6px;
    padding: 5px;
}

.lbl-name label {
    margin-left: 6px;
}

.lbl-dni-sex,
.lbl-email,
.lbl-surname,
.lbl-address {
    margin: 10px 0;
}

.lbl-email label {
    margin-left: 15px;
}

.lbl-email {
    margin-bottom: 1rem;
}

.lbl-dni-sex label {
    margin-left: 40px;
}

.lbl-dni-sex input {
    width: 88px;
}

.lbl-phone-age input {
    width: 185px;
}

.lbl-phone-age label {
    margin-left: 95px;
}

.lbl-phone-age .lbl-age {
    margin-left: 15px;
}

.lbl-phone-age .box-age {
    width: 90px;
}

.lbl-address label {
    margin-left: 88px;
}

.lbl-address input {
    width: 300px;
}

.lbl-dob input {
    width: 300px;
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
