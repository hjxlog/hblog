import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {ElMessage} from "element-plus";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/login',
        name: 'login',
        component: () => import("../views/backend/login/Login.vue"),
    },
    // frontend
    {
        path: '/',
        name: 'home',
        component: () => import("@/views/frontend/Home.vue"),
    },
    {
        path: '/test',
        name: 'test',
        component: () => import("@/components/CatalogCard.vue"),
    },
    {
        path: '/category',
        name: 'category',
        component: () => import("@/views/frontend/category/CategoryList.vue")
    },
    {
        path: "/category/:id",
        name: 'categoryDetail',
        component: () => import("@/views/frontend/category/CategoryDetail.vue")
    },
    {
        path: "/blog/:id",
        name: 'blogDetail',
        component: () => import("@/views/frontend/blog/BlogDetail.vue")
    },
    {
        path: '/tag',
        name: 'tag',
        component: () => import("@/views/frontend/tag/TagList.vue")
    },
    {
        path: "/tag/:id",
        name: 'tagDetail',
        component: () => import("@/views/frontend/tag/TagDetail.vue")
    },
    {
        path: "/archive",
        name: 'archive',
        component: () => import("@/views/frontend/blog/Archive.vue")
    },
    //backend
    {
        path: '/backend',
        name: 'adminHome',
        redirect: '/backend/index',
        component: () => import("../layout/admin/Layout.vue"),
        children: [
            {
                path: 'index',
                name: 'Index',
                component: () => import("../views/backend/Index.vue"),
                meta: {
                    title: '首页',
                    icon: 'index'
                }
            }
        ]
    },
    {
        path: "/content",
        name: "content",
        meta: {
            title: "内容管理"
        },
        component: () => import("../layout/admin/Layout.vue"),
        children: [
            {
                path: "adminCategory",
                name: "adminCategory",
                meta: {
                    title: "分类管理"
                },
                component: () => import("../views/backend/content/category/CategoryList.vue"),
            },
            {
                path: "adminTag",
                name: "adminTag",
                meta: {
                    title: "标签管理"
                },
                component: () => import("../views/backend/content/tag/TagList.vue"),
            },
            {
                path: "adminBlog",
                name: "adminBlog",
                meta: {
                    title: "博客管理"
                },
                component: () => import("../views/backend/content/blog/BlogList.vue"),
            },
            {
                path: "adminBlog/editor",
                name: "",
                meta: {
                    title: "写博客"
                },
                component: () => import("../views/backend/content/blog/WriteBlog.vue"),
            },
            {
                path: "adminBlog/editor/:id",
                name: 'adminEditBlog',
                component: () => import("../views/backend/content/blog/WriteBlog.vue"),
                meta: {
                    title: '写博客'
                }
            },
        ]
    },
    {
        path: "/adminSystem",
        name: "adminSystem",
        meta: {
            title: "系统管理"
        },
        component: () => import("../layout/admin/Layout.vue"),
        children: [
            {
                path: "adminCount",
                name: "adminCount",
                meta: {
                    title: "账号管理"
                },
                component: () => import("../views/backend/system/account/AccountManage.vue"),
            },
        ]
    },
    {
        path: "/adminLog",
        name: "adminLog",
        meta: {
            title: "日志管理"
        },
        component: () => import("../layout/admin/Layout.vue"),
        children: [
            {
                path: "operate",
                name: "operate",
                meta: {
                    title: "操作日志"
                },
                component: () => import("../views/backend/log/AdminLog.vue"),
            },
            {
                path: "visit",
                name: "visit",
                meta: {
                    title: "访问日志"
                },
                component: () => import("../views/backend/log/ServeLog.vue"),
            },
            {
                path: "error",
                name: "error",
                meta: {
                    title: "异常日志"
                },
                component: () => import("../views/backend/log/ErrorLog.vue"),
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
