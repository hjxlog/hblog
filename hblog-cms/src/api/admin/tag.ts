import service from "@/utils/request";

/**
 * 获取标签数据
 * @param name 标签名称
 */
export const getTagList = (param: TagDto) => {
    return service({
        url: "/admin/tag/list",
        method: "post",
        data: param
    }).catch()
}

export const addTag = (param: TagDto) => {
    return service({
        url: "/admin/tag/add",
        method: "post",
        data: param
    })
}

export const updateTag = (param: TagDto) => {
    return service({
        url: "/admin/tag/update",
        method: "post",
        data: param
    })
}

export const deleteTag = (param: number) => {
    return service({
        url: "/admin/tag/delete/" + param,
        method: "post"
    })
}
