// @ts-ignore
import axios from 'axios'
import {ElMessage} from "element-plus";

axios.defaults.headers['Content-Type'] = "application/json;charset=utf-8"
axios.defaults.timeout = 1000000

const service = axios.create({
    baseURL: "http://localhost:8899/"
})

// 请求拦截，在请求发送之前做一些处理，例如添加token等
service.interceptors.request.use(config => {
    return config
}, error => {
    return Promise.reject(error)
})

// 响应拦截，在接收到响应数据之前做一些处理
service.interceptors.response.use(response => {
    console.log("响应返回数据：" + JSON.stringify(response.data))
    const code = response.data.code
    if (code === '200') {
        return response.data;
    }
    let msg = response.data.msg || '发生错误。'
    ElMessage.error(msg)
    return Promise.reject(new Error(msg))
}, error => {
    return Promise.reject(error)
})

export default service
