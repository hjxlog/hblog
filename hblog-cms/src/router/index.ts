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
        component: () => import("@/components/Test.vue"),
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
        path: "/dashboard",
        name: "dashboard",
        meta: {
            title: "后台管理"
        },
        redirect: '/dashboard/index',
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
            },
            {
                path: "content",
                name: "content",
                redirect: '/dashboard/content/blog',
                children: [
                    {
                        path: "blog",
                        name: "adminBlog",
                        meta: {
                            title: "博客管理"
                        },
                        component: () => import("../views/backend/content/blog/BlogList.vue"),
                    },
                    {
                        path: "category",
                        name: "adminCategory",
                        meta: {
                            title: "分类管理"
                        },
                        component: () => import("../views/backend/content/category/CategoryList.vue"),
                    },
                    {
                        path: "tag",
                        name: "adminTag",
                        meta: {
                            title: "标签管理"
                        },
                        component: () => import("../views/backend/content/tag/TagList.vue"),
                    },
                    {
                        path: "blog/editor",
                        name: "",
                        meta: {
                            title: "写博客"
                        },
                        component: () => import("../views/backend/content/blog/WriteBlog.vue"),
                    },
                    {
                        path: "blog/editor/:id",
                        name: 'adminEditBlog',
                        component: () => import("../views/backend/content/blog/WriteBlog.vue"),
                        meta: {
                            title: '写博客'
                        }
                    },
                ]
            },
            {
                path: "log",
                name: 'log',
                redirect: '/dashboard/log/operate',
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
            {
                path: "system",
                name: "system",
                redirect: '/dashboard/system/account',
                children: [
                    {
                        path: "account",
                        name: "adminCount",
                        meta: {
                            title: "账号管理"
                        },
                        component: () => import("../views/backend/system/account/AccountManage.vue"),
                    },
                ]
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
    if (to.path !== '/login' && to.path.startsWith('/dashboard')) {
        const token = localStorage.getItem('token')
        if (!token) {
            ElMessage.info("请先登录。")
            return next("/login")
        }
    }
    next()
})

export default router
