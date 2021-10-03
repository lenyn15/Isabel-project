<template>
    <div class="container">
        <Bar
            title="Niveles Academicos"
            place="Gestión Académica"
            subplace="Nivel"
        />

        <div class="form">
            <div class="title-form">
                <h3>Datos</h3>
                <a class="button" @click="chooseAction">Guardar</a>
            </div>
            <div class="body-form">
                <div class="lbl-name">
                    <label>Nombre</label>
                    <input
                        type="text"
                        v-model="bodyLevel.name"
                        placeholder="Nombre del Nivel Académico"
                    />
                </div>
            </div>
        </div>

        <div class="table-data">
            <div class="table-nav">
                <div>
                    <h3>Listado de Niveles Académicos</h3>
                </div>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Nombre</th>
                            <th class="actions">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="level in levels" :key="level.id">
                            <td class="id-d">{{ level.id }}</td>
                            <td>{{ level.name }}</td>
                            <td class="middle">
                                <a
                                    class="btn btn-edit"
                                    @click="setLevel(level.id)"
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
            levels: [],
            bodyLevel: {
                name: "",
            },
            activeState: "ACTIVO",
            inactiveState: "INACTIVO",
            nameSearch: "",
            idlevel: 0,
            isEdit: false,
        };
    },
    methods: {
        getLevel(id) {
            axios.get("http://localhost:8093/level/" + id).then((resp) => {
                this.bodyLevel = resp.data;
            });
        },
        getLevels() {
            axios.get("http://localhost:8093/level/list").then((resp) => {
                this.levels = resp.data;
                console.log(resp.data);
            });
        },
        chooseAction() {
            if (this.isEdit) {
                this.isEdit = false;
                axios
                    .put(
                        "http://localhost:8093/level/update/" + this.idlevel,
                        this.bodyLevel
                    )
                    .then(() => {
                        window.location.reload();
                    });
            } else {
                axios
                    .post("http://localhost:8093/level/new", this.bodyLevel)
                    .then(() => {
                        window.location.reload();
                    })
                    .catch((error) => console.log(error));
            }
        },
        setLevel(id) {
            this.getLevel(id);
            this.idlevel = id;
            this.isEdit = true;
        },
    },
    mounted() {
        this.getLevels();
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

input {
    margin-left: 15px;
    padding: 5px;
    width: 250px;

    border: 1px solid rgba(0, 0, 0, 0.15);
    box-sizing: border-box;
    border-radius: 6px;
    font-family: "Quicksand", sans-serif;
}

.lbl-name {
    margin: 0 0 20px 0;
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
