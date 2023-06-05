import axios from 'axios'
import store from '@/store'

const service = axios.create({
  url: 'http://localhost:8888/yang'
})

// request
service.interceptors.request.use(
  config => {
    if (store.getters.getToken) {
      config.headers['token'] = window.sessionStorage.getItem('token')
    }
    return config
  },
  error => {
    console.log('error')
    return Promise.reject(error)
  }
)

// response
axios.interceptors.response.use(
  response => {
    let res = response.data
    console.log(res)
    if (res.code === 200) {
      return response
    } else {
      return Promise.reject(response.data.msg)
    }
  },
  error => {
    console.log(error)
    if (error.response.data) {
      error.message = error.response.data.msg
    }
    if (error.response.status === 401) {
      this.$router.push('/')
    }
    return Promise.reject(error)
  }
)

export default service
