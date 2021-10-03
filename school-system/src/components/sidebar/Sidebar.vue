<script>
import { collapsed, toggleSidebar, sidebarWidth } from "./state";
import SidebarLink from "./SidebarLink";

export default {
    components: { SidebarLink },
    props: {},
    data() {
        return {
            title: "Institución Educativa Experimental",
        };
    },
    setup() {
        return { collapsed, toggleSidebar, sidebarWidth };
    },
};
</script>

<template>
    <div class="sidebar" :style="{ width: sidebarWidth }">
        <h2>
            <span v-if="collapsed">
                <br />
                <br />
                <br />
            </span>
            <span v-else>{{ title }}</span>
        </h2>

        <SidebarLink to="/" icon="fas fa-chart-line">Dashboard</SidebarLink>
        <SidebarLink to="/academic" icon="fas fa-graduation-cap">
            Gestion Académica
        </SidebarLink>
        <SidebarLink to="/maintenance" icon="fas fa-tools">
            Mantenimientos
        </SidebarLink>
        <SidebarLink to="/operation" icon="fas fa-chalkboard">
            Operaciones
        </SidebarLink>
        <SidebarLink to="/report" icon="fas fa-poll-h">Reportes</SidebarLink>
        <SidebarLink to="/user" icon="fas fa-users">Usuarios</SidebarLink>

        <span
            class="collapse-icon"
            :class="{ 'rotate-180': collapsed }"
            @click="toggleSidebar"
        >
            <i class="fas fa-angle-double-left"></i>
        </span>
    </div>
</template>

<style>
:root {
    --sidebar-color: #c60808;
    --sidebar-bg-color: #fff;
    --sidebar-item-hover: #16a7da;
    --sidebar-item-active: #f6f9fb;
}
</style>

<style scoped>
.sidebar {
    color: #3c5b66;
    background-color: var(--sidebar-bg-color);
    box-shadow: 0.995225px 0px 9.95225px rgba(0, 0, 0, 0.15);
    text-align: center;

    float: left;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    bottom: 0;
    padding: 0.5em;

    transition: 0.3s ease;

    display: flex;
    flex-direction: column;
}

.collapse-icon {
    position: absolute;
    bottom: 0;
    padding: 0.75em;

    color: #eb5757b3;

    transition: 0.2s linear;
}

.rotate-180 {
    transform: rotate(180deg);
    transition: 0.2s linear;
}
</style>
