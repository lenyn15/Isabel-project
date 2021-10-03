<template>
    <div class="container-card">
        <div class="card-teacher">
            <Card
                title="Docentes"
                subtitle="Modulo de Docentes"
                filename="Docentes.svg"
                description="Acceso al Modulo de Docente, para gestionar sus datos"
                to="/maintenance/teacher"
            />
        </div>
        <div class="card-student">
            <Card
                title="Estudiantes"
                subtitle="Modulo de Estudiantes"
                filename="Alumnos.svg"
                description="Acceso al Modulo de Estudiante, para gestionar sus datos"
                to="/maintenance/student"
            />
        </div>
        <div class="card-tutor">
            <Card
                title="Apoderados"
                subtitle="Modulo de Apoderados"
                filename="apoderados.svg"
                description="Acceso al Modulo de Apoderado, para gestionar sus datos"
                to="/maintenance/tutor"
            />
        </div>
    </div>
</template>

<script>
import Card from "../components/card/Card";

export default {
    components: { Card },
};
</script>

<style>
:root {
    --first-color: #c60808;
    --white-color: #e9eaec;
    --dark-color: #16a7da;
    --dark-color-lighten: #f6f9fb;

    --h1-font-size: 1.5rem;
    --h2-font-size: 1.25rem;
    --normal-font-size: 1rem;
    --small-font-size: 0.875rem;
}
</style>

<style scoped>
.container-card {
    font-size: var(--normal-font-size);
    margin: 15px 15px 10px 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    border: 1px solid #b7c6eb;
    box-sizing: border-box;
    border-radius: 6px;
    height: 85vh;
}

.card-teacher,
.card-student,
.card-tutor {
    margin: 0 1rem;
}
</style>
