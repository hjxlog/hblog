import service from "@/utils/request";

export const getDiaryData = (param: any) => {
    return service({
        url: "/admin/diary/list",
        method: "post",
        data: param
    })
}
export const getDiaryDetail = (param: number) => {
    return service({
        url: "/admin/diary/get/" + param,
        method: "get"
    })
}

export const addDiary = (param: any) => {
    return service({
        url: "/admin/diary/add",
        method: "post",
        data: param
    })
}

export const updateDiary = (param: any) => {
    return service({
        url: "/admin/diary/update",
        method: "post",
        data: param
    })
}

export const deleteDiary = (param: number) => {
    return service({
        url: "/admin/diary/delete/" + param,
        method: "post"
    })
}
