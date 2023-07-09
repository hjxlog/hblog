import service from "@/utils/request";

export const getCategoryData = () => {
    return service({
        url: "/category/list",
        method: "get"
    })
}

export const getCategoryDetail = (id: number) => {
    return service({
        url: "/category/getDetail/" + id,
        method: "get"
    })
}
