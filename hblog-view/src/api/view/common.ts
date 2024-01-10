import service from "@/utils/request";

export const getOverview = () => {
    return service({
        url: "/view/getOverview",
        method: "get"
    })
}
