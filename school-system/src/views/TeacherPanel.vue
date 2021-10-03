<template>
    <div class="container">
        <Bar title="Docentes" place="Mantenimiento" subplace="Docente" />

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
                            v-model="bodyTeacher.name"
                            placeholder="Nombre del docente"
                        />
                    </div>

                    <div class="lbl-surname">
                        <label>Apellidos</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.surname"
                            placeholder="Apellidos del docente"
                        />
                    </div>

                    <div class="lbl-dni-sex">
                        <label>DNI</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.dni"
                            placeholder="DNI"
                        />

                        <label>Sexo</label>
                        <select v-model="bodyTeacher.gender">
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
                            v-model="bodyTeacher.email"
                            placeholder="Correo del docente"
                        />
                    </div>
                </div>
                <div class="second-entry">
                    <div class="lbl-occupation">
                        <label>Especialidad</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.occupation"
                            placeholder="Especialidad del docente"
                        />
                    </div>

                    <div class="lbl-address">
                        <label>Dirección</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.address"
                            placeholder="Direccion del docente"
                        />
                    </div>

                    <div class="lbl-phone-age">
                        <label>Telefono</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.phone"
                            placeholder="Telefono del docente"
                        />

                        <label class="lbl-age">Edad</label>
                        <input
                            type="text"
                            v-model="bodyTeacher.age"
                            placeholder="Edad"
                            class="box-age"
                        />
                    </div>
                </div>
            </div>
        </div>

        <div class="table-data">
            <div class="table-nav">
                <div>
                    <h3>Listado de Docentes</h3>
                </div>
                <div class="search-filter">
                    <div class="wrapper">
                        <a class="filter-all" @click="getTeachers">
                            <i class="fas fa-reply-all"></i>
                        </a>
                        <a
                            class="filter-active"
                            @click="filterState(activeState)"
                        >
                            <i class="fas fa-check"></i>
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
                                        @click="foundTeacher(nameSearch)"
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
                        <tr v-for="teacher in teachers" :key="teacher.id">
                            <td class="id-d">{{ teacher.id }}</td>
                            <td>{{ teacher.name }}</td>
                            <td>{{ teacher.surname }}</td>
                            <td>{{ teacher.dni }}</td>
                            <td>{{ teacher.phone }}</td>
                            <td>{{ teacher.email }}</td>
                            <td>
                                <p
                                    class="status"
                                    v-bind:class="
                                        teacher.state === 'ACTIVO'
                                            ? 'active'
                                            : 'inactive'
                                    "
                                >
                                    {{ teacher.state }}
                                </p>
                            </td>
                            <td class="middle">
                                <a
                                    class="btn btn-edit"
                                    @click="setTeacher(teacher.id)"
                                >
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a
                                    class="btn btn-activate"
                                    @click="activateTeacher(teacher.id)"
                                >
                                    <i class="fas fa-user-check"></i>
                                </a>
                                <a
                                    class="btn btn-delete"
                                    @click="deleteTeacher(teacher.id)"
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
            teachers: [],
            bodyTeacher: {
                name: "",
                surname: "",
                dni: "",
                gender: "",
                email: "",
                occupation: "",
                address: "",
                phone: "",
                age: "",
            },
            activeState: "ACTIVO",
            inactiveState: "INACTIVO",
            nameSearch: "",
            idteacher: 0,
            isEdit: false,
        };
    },
    methods: {
        getTeacher(id) {
            axios.get("http://localhost:8091/teacher/" + id).then((resp) => {
                this.bodyTeacher = resp.data;
            });
        },
        getTeachers() {
            axios.get("http://localhost:8091/teacher/list").then((resp) => {
                this.teachers = resp.data;
            });
        },
        foundTeacher(name) {
            axios
                .get("http://localhost:8091/teacher/search/name/" + name)
                .then((resp) => {
                    this.teachers = resp.data;
                });
        },
        filterState(state) {
            axios
                .get("http://localhost:8091/teacher/filter/" + state)
                .then((resp) => {
                    this.teachers = resp.data;
                });
        },
        chooseAction() {
            if (this.isEdit) {
                this.isEdit = false;
                axios
                    .put(
                        "http://localhost:8091/teacher/update/" +
                            this.idteacher,
                        this.bodyTeacher
                    )
                    .then(() => {
                        window.location.reload();
                    });
            } else {
                axios
                    .post("http://localhost:8091/teacher/new", this.bodyTeacher)
                    .then(() => {
                        window.location.reload();
                    })
                    .catch((error) => console.log(error));
            }
        },
        setTeacher(id) {
            this.getTeacher(id);
            this.idteacher = id;
            this.isEdit = true;
        },
        deleteTeacher(id) {
            axios
                .delete("http://localhost:8091/teacher/delete/" + id)
                .then(() => {
                    window.location.reload();
                });
        },
        activateTeacher(id) {
            axios
                .put("http://localhost:8091/teacher/activate/" + id)
                .then(() => {
                    window.location.reload();
                });
        },
    },
    mounted() {
        this.getTeachers();
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
    margin-left: 31px;
}

.lbl-phone-age .lbl-age {
    margin-left: 15px;
}

.lbl-phone-age .box-age {
    width: 90px;
}

.lbl-address label {
    margin-left: 26px;
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
