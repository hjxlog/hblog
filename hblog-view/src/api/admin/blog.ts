import service from "@/utils/request";

export const getBlogData = (param: any) => {
    return service({
        url: "/admin/blog/list",
        method: "post",
        data: param
    })
}
export const getBlogDetail = (param: number) => {
    return service({
        url: "/admin/blog/get/" + param,
        method: "get"
    })
}

export const addBlog = (param: any) => {
    return service({
        url: "/admin/blog/add",
        method: "post",
        data: param
    })
}

export const updateBlog = (param: any) => {
    return service({
        url: "/admin/blog/update",
        method: "post",
        data: param
    })
}

export const deleteBlog = (param: number) => {
    return service({
        url: "/admin/blog/delete/" + param,
        method: "post"
    })
}
