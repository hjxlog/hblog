import service from "@/utils/request";

export const addComment = (param: CommentDto) => {
    return service({
        url: "/comment/add",
        method: "post",
        data: param
    })
}
