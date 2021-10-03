<template>
    <SideBar />
    <div :style="{ 'margin-left': sidebarWidth }">
        <div class="nav">
            <br />
            <div class="user-container">
                <div class="user-acces">
                    <p>{{ name }}</p>
                </div>
                <button>Cerrar Sesión</button>
            </div>
        </div>
        <router-view />
    </div>
</template>

<script>
import SideBar from "./components/sidebar/Sidebar";
import { sidebarWidth } from "./components/sidebar/state";

export default {
    components: { SideBar },
    data() {
        return {
            name: "Isabel Perez Cortez",
            role: "Administrador",
        };
    },
    setup() {
        return { sidebarWidth };
    },
};
</script>

<style scoped>
.nav {
    display: flex;
    align-content: flex-end;
    justify-content: space-between;
    background: linear-gradient(180deg, #c60808 0%, #c60808 100%);
    color: white;
    margin: 0;
    padding-right: 2vw;
}

.nav .user-container {
    display: flex;
    align-items: center;
}

.nav .user-container .user-acces p {
    margin: 15px 15px;
    font-weight: 600;
}

.nav .user-container button {
    margin: 10px 0;
    padding: 5px 10px;
}
</style>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Quicksand");

body {
    margin: 0;
    background-color: #f4f4f4;
}

#app {
    font-family: "Quicksand", sans-serif;
    color: #2c3e50;
}
</style>
