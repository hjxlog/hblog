import service from "@/utils/request";

export const getArchiveList = (param: PageDto) => {
    return service({
        url: "/diary/getArchiveList",
        method: "post",
        data: param
    })
}
