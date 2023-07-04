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
            {
                path: "tag",
                name: "tag",
                meta: {
                    title: "标签管理"
                },
                component: () => import("../views/tag/TagList.vue"),
            },
            {
                path: "blog",
                name: "blog",
                meta: {
                    title: "博客管理"
                },
                component: () => import("../views/blog/BlogList.vue"),
            },
            {
                path: "blog/editor",
                name: "",
                meta: {
                    title: "写博客"
                },
                component: () => import("../views/blog/WriteBlog.vue"),
            },
            {
                path: "blog/editor/:id",
                name: 'editBlog',
                component: () => import("../views/blog/WriteBlog.vue"),
                meta: {
                    title: '写博客'
                }
            },
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
