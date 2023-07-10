import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: () => import("@/views/blog/BlogList.vue")
    },
    {
        path: '/category',
        name: 'category',
        component: () => import("@/views/category/CategoryList.vue")
    },
    {
        path: "/category/:id",
        name: 'categoryDetail',
        component: () => import("@/views/category/CategoryDetail.vue")
    },
    {
        path: "/blog/:id",
        name: 'blogDetail',
        component: () => import("@/views/blog/BlogDetail.vue")
    },
    {
        path: '/tag',
        name: 'tag',
        component: () => import("@/views/tag/TagList.vue")
    },
    {
        path: "/tag/:id",
        name: 'tagDetail',
        component: () => import("@/views/tag/TagDetail.vue")
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
