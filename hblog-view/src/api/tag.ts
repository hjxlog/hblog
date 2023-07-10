import service from "@/utils/request";

export const getTagData = () => {
    return service({
        url: "/tag/list",
        method: "get"
    })
}

export const getTagDetail = (id: number) => {
    return service({
        url: "/tag/getDetail/" + id,
        method: "get"
    })
}
