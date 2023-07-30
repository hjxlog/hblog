import service from "@/utils/request";

export const getLogData = (param: AppLogQueryDto) => {
    return service({
        url: "/admin/appLog/list",
        method: "post",
        data: param
    })
}
