<template>
    <div class="container-card">
        <div class="menu">
            <div class="card-period">
                <Card
                    title="Periodo"
                    subtitle="Periodos Academicos"
                    filename="periodo.svg"
                    description="Registro de cada Periodo Academico"
                    to="/academic/period"
                />
            </div>
            <div class="card-level">
                <Card
                    title="Nivel"
                    subtitle="Modulo de Niveles"
                    filename="niveles.svg"
                    description="Registro de los Niveles Academicos"
                    to="/academic/level"
                />
            </div>
            <div class="card-grade">
                <Card
                    title="Grados"
                    subtitle="Grados Academicos"
                    filename="grados.svg"
                    description="Registros de los Grados Academicos"
                    to="/academic/grade"
                />
            </div>
        </div>
        <div class="menu">
            <div class="card-section">
                <Card
                    title="Secciones"
                    subtitle="Modulo de Secciones"
                    filename="section.svg"
                    description="Gestionar las secciones de cada Grado"
                    to="/academic/section"
                />
            </div>
            <div class="card-course">
                <Card
                    title="Cursos"
                    subtitle="Modulo de Cursos"
                    filename="course.svg"
                    description="Gestion de cada Curso por Nivel"
                    to="/academic/course"
                />
            </div>
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
    padding: 15px 0;
    background-color: white;
    border: 1px solid #b7c6eb;
    box-sizing: border-box;
    border-radius: 6px;
}

.menu {
    display: flex;
    justify-content: center;
}

.card-period,
.card-level,
.card-grade,
.card-section,
.card-course {
    margin: 0 1rem;
}
</style>
