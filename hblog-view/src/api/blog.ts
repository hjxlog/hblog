import service from "@/utils/request";

export const getBlogData = (param: BlogDto) => {
    return service({
        url: "/blog/list",
        method: "post",
        data: param
    })
}
