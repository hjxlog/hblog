import service from "@/utils/request";

const login = (user: User) => {
    console.log("login.ts")
    return service({
        url: "/login",
        method: "post",
        data: user
    })
}

export {login}