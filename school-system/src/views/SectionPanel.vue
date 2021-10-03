<template>
    <div class="container">
        <Bar title="Secciones" place="Gestión Académica" subplace="Secciones" />

        <div class="form">
            <div class="title-form">
                <h3>Datos</h3>
                <a class="button" @click="chooseAction">Guardar</a>
            </div>
            <div class="body-form">
                <div>
                    <div class="lbl-name">
                        <label>Letra</label>
                        <input
                            type="text"
                            v-model="bodySection.letter"
                            placeholder="Letra de la Seccion"
                        />
                    </div>

                    <div class="lbl-capacity">
                        <label>Capacidad</label>
                        <input
                            type="number"
                            v-model="bodySection.capacity"
                            placeholder="Capacidad"
                        />
                    </div>

                    <div class="lbl-level">
                        <label>Grado</label>
                        <select v-model="gradeSelected" id="grade">
                            <option
                                v-for="grade in grades"
                                :key="grade.id"
                                :value="grade"
                            >
                                {{ grade.name }}
                            </option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-data">
            <div class="table-nav">
                <div>
                    <h3>Listado de Grados Académicos</h3>
                </div>
                <div class="search-filter">
                    <div class="wrapper">
                        <a class="filter-all" @click="getSections">
                            <i class="fas fa-reply-all"></i>
                        </a>
                    </div>
                    <div class="box-container">
                        <select
                            v-model="gradeFilter"
                            id="grade"
                            @click="filterSection(gradeFilter.id)"
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
                </div>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Grado</th>
                            <th>Seccion</th>
                            <th>Capacidad</th>
                            <th class="actions">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="section in sections" :key="section.id">
                            <td class="id-d">{{ section.id }}</td>
                            <td>{{ section.grade.name }}</td>
                            <td>{{ section.letter }}</td>
                            <td>{{ section.capacity }}</td>
                            <td class="middle">
                                <a
                                    class="btn btn-edit"
                                    @click="setSection(section.id)"
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
            sections: [],
            grades: [],
            gradeSelected: {},
            gradeFilter: {},
            bodySection: {
                letter: "",
                capacity: 0,
                grade: {
                    id: 0,
                },
            },
            idsection: 0,
            isEdit: false,
        };
    },
    methods: {
        getSection(id) {
            axios.get("http://localhost:8093/section/" + id).then((resp) => {
                this.bodySection = resp.data;
                this.gradeSelected = this.bodySection.grade;
            });
        },
        getSections() {
            axios.get("http://localhost:8093/section/list").then((resp) => {
                this.sections = resp.data;
            });
        },
        getGrades() {
            axios.get("http://localhost:8093/grade/list").then((resp) => {
                this.grades = resp.data;
            });
        },
        filterSection(idG) {
            axios
                .get("http://localhost:8093/section/list/grade/" + idG)
                .then((resp) => {
                    this.sections = resp.data;
                });
        },
        chooseAction() {
            this.bodySection.grade.id = this.gradeSelected.id;
            if (this.isEdit) {
                this.isEdit = false;
                axios
                    .put(
                        "http://localhost:8093/section/update/" +
                            this.gradeSelected.id +
                            "/" +
                            this.idsection,
                        this.bodySection
                    )
                    .then(() => {
                        window.location.reload();
                    });
            } else {
                axios
                    .post("http://localhost:8093/section/new", this.bodySection)
                    .then((resp) => {
                        console.log(resp.data);
                        window.location.reload();
                    })
                    .catch((error) => console.log(error));
            }
        },
        setSection(id) {
            this.getSection(id);
            this.idsection = id;
            this.isEdit = true;
        },
    },
    mounted() {
        this.getSections();
        this.getGrades();
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

.lbl-name label {
    margin-left: 6px;
}

.lbl-level {
    margin: 10px 0 20px 0;
}

.lbl-capacity {
    margin: 10px 0 0 0;
}

.lbl-capacity label {
    margin-left: -35px;
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
