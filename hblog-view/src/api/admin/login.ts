import service from "@/utils/request";

const login = (user: User) => {
    return service({
        url: "/admin/auth/login",
        method: "post",
        data: user
    })
}

const logout = () => {
    return service({
        url: "/admin/auth/logout",
        method: "post"
    })
}

export {login, logout}
