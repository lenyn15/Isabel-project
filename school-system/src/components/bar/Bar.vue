<template>
    <div class="bar">
        <h3>{{ title }}</h3>
        <div class="bar-place">
            <p class="first-p">{{ place }}</p>
            <p>/ {{ subplace }}</p>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        title: { type: String },
        place: { type: String },
        subplace: { type: String },
    },
};
</script>

<style scoped>
.bar {
    display: flex;
    justify-content: space-between;
    border: 1px solid #b7c6eb;
    background-color: white;
    padding: 0 15px;
    border-radius: 6px;
}

.bar-place {
    display: inherit;
    align-items: center;
    font-size: 13px;
    cursor: default;
}

.first-p {
    padding: 0 5px;
    color: #16a7da;
}
</style>
