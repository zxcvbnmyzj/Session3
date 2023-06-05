import request from '../utils/request'
export function login (data) {
  return request({
    url: 'http://localhost:8888/yang/api/login',
    method: 'post',
    data
  })
}
