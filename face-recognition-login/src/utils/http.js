import axios from 'axios'
import ElementUI from 'element-ui'

const instance = axios.create({
    baseURL: 'http://127.0.0.1:8888',
    timeout: 8000,
})

instance.interceptors.response.use(response => {
    return response.data
}, reason => {
    ElementUI.Message({
        type: "warning",
        message: reason.message
    })
    console.log(reason)
    return Promise.reject(reason)
})

instance.interceptors.request.use(config => {
    return config
}, reason => {
    ElementUI.Message({
        type: "warning",
        message: "发送请求失败"
    })
    console.log(reason)
    return Promise.reject(reason)
})


export default instance