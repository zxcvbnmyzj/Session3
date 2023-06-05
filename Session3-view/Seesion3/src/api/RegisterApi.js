import request from '../utils/request'

export function updateAsset (data) {
  return request({
    url: 'http://localhost:8888/yang/api/updateAsset',
    method: 'post',
    data
  })
}

export function RegisterAsset (data) {
  return request({
    url: 'http://localhost:8888/yang/api/RegisterAsset',
    method: 'post',
    data
  })
}
