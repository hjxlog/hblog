import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {ElMessage} from "element-plus";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        redirect: '/index',
        component: () => import("../layout/Layout.vue"),
        children: [
            {
                path: 'index',
                name: 'Index',
                component: () => import("../views/Index.vue"),
                meta: {
                    title: '首页',
                    icon: 'index'
                }
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import("../views/login/Login.vue")
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
                component: () => import("../views/content/category/CategoryList.vue"),
            },
            {
                path: "tag",
                name: "tag",
                meta: {
                    title: "标签管理"
                },
                component: () => import("../views/content/tag/TagList.vue"),
            },
            {
                path: "blog",
                name: "blog",
                meta: {
                    title: "博客管理"
                },
                component: () => import("../views/content/blog/BlogList.vue"),
            },
            {
                path: "blog/editor",
                name: "",
                meta: {
                    title: "写博客"
                },
                component: () => import("../views/content/blog/WriteBlog.vue"),
            },
            {
                path: "blog/editor/:id",
                name: 'editBlog',
                component: () => import("../views/content/blog/WriteBlog.vue"),
                meta: {
                    title: '写博客'
                }
            },
        ]
    },
    {
        path: "/system",
        name: "system",
        meta: {
            title: "系统管理"
        },
        component: () => import("../layout/Layout.vue"),
        children: [
            {
                path: "account",
                name: "account",
                meta: {
                    title: "账号管理"
                },
                component: () => import("../views/system/account/AccountManage.vue"),
            },
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
    if (to.path !== '/login') {
        const token = localStorage.getItem('token')
        if (!token) {
            ElMessage.info("请先登录。")
            return next("/login")
        }
    }
    next()
})

export default router
