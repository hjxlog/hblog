import service from "@/utils/request";

export const getBlogData = (param: BlogDto) => {
    return service({
        url: "/blog/list",
        method: "post",
        data: param
    })
}

export const getBlogDetail = (id: number) => {
    return service({
        url: "/blog/get/" + id,
        method: "get"
    })
}

export const getBlogListByCategoryId = (categoryId: number) => {
    return service({
        url: "/blog/getByCategoryId/" + categoryId,
        method: "get"
    })
}

export const getBlogListByTagId = (tagId: number) => {
    return service({
        url: "/blog/getByTagId/" + tagId,
        method: "get"
    })
}

export const getRecommendBlogList = () => {
    return service({
        url: "/blog/getRecommendBlogList",
        method: "get"
    })
}

export const getArchiveList = (param: PageDto) => {
    return service({
        url: "/blog/getArchiveList",
        method: "post",
        data: param
    })
}
