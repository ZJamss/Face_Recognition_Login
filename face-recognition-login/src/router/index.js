import VueRouter from "vue-router";
import Login from "@/components/Login";
import Register from "@/components/Register";
import Index from "@/components/Index";

export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: "/login"
        },
        {
            path: '/login',
            component: Login,
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/index',
            component: Index
        }
    ]
})