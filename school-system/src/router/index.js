import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        name: "Dashboard",
        component: () => import("../views/Dashboard"),
    },
    {
        path: "/maintenance",
        name: "Maintenance",
        component: () => import("../views/Maintenance"),
    },
    {
        path: "/maintenance/teacher",
        name: "Teacher",
        component: () => import("../views/TeacherPanel"),
    },
    {
        path: "/maintenance/student",
        name: "Student",
        component: () => import("../views/StudentPanel"),
    },
    {
        path: "/maintenance/tutor",
        name: "Tutor",
        component: () => import("../views/TutorPanel"),
    },
    {
        path: "/academic",
        name: "Academic",
        component: () => import("../views/Academic"),
    },
    {
        path: "/academic/period",
        name: "Period",
        component: () => import("../views/PeriodPanel"),
    },
    {
        path: "/academic/level",
        name: "Level",
        component: () => import("../views/LevelPanel"),
    },
    {
        path: "/academic/grade",
        name: "Grade",
        component: () => import("../views/GradePanel"),
    },
    {
        path: "/academic/section",
        name: "Section",
        component: () => import("../views/SectionPanel"),
    },
    {
        path: "/academic/course",
        name: "Course",
        component: () => import("../views/CoursePanel"),
    },
    {
        path: "/operation",
        name: "Operation",
        component: () => import("../views/Operation"),
    },
    {
        path: "/operation/enrollment",
        name: "Enrollment",
        component: () => import("../views/EnrollmentPanel"),
    },
    {
        path: "/operation/payment",
        name: "Payment",
        component: () => import("../views/PaymentPanel"),
    },
    {
        path: "/report",
        name: "Report",
        component: () => import("../views/ReportPanel"),
    },
    {
        path: "/user",
        name: "User",
        component: () => import("../views/UserPanel"),
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
