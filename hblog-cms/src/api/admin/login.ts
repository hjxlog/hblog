import service from "@/utils/request";

const login = (user: User) => {
    return service({
        url: "/login",
        method: "post",
        data: user
    })
}

const logout = () => {
    return service({
        url: "/logout",
        method: "post"
    })
}

export {login, logout}