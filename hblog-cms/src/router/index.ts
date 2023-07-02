import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: () => import("../layout/Layout.vue"),
        children: []
    },
    {
        path: "/content",
        name: "content",
        meta: {
            title: "内容管理"
        },
        component: () => import("../layout/Layout.vue"),
        children: [
            {
                path: "category",
                name: "category",
                meta: {
                    title: "分类管理"
                },
                component: () => import("../views/category/CategoryList.vue"),
            },
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
