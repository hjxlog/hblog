import service from "@/utils/request";

export const updateUserInfo = (param: UserInfoDto) => {
    return service({
        url: "/admin/user/updateInfo",
        method: "post",
        data: param
    })
}

export const updatePassword = (param: UserInfoDto) => {
    return service({
        url: "/admin/user/updatePassword",
        method: "post",
        data: param
    })
}