import service from "@/utils/request";

/**
 * 获取分类数据
 * @param name 分类名称
 */
export const getCategoryList = (param: CategoryDto) => {
    return service({
        url: "/admin/category/list",
        method: "post",
        data: param
    }).catch()
}

export const addCategory = (param: CategoryDto) => {
    return service({
        url: "/admin/category/add",
        method: "post",
        data: param
    })
}

export const updateCategory = (param: CategoryDto) => {
    return service({
        url: "/admin/category/update",
        method: "post",
        data: param
    })
}

export const deleteCategory = (param: number) => {
    return service({
        url: "/admin/category/delete/" + param,
        method: "post"
    })
}
