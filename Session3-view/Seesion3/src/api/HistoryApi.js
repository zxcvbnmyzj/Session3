import request from '../utils/request'

export function getHistoryList () {
  return request({
    url: 'http://localhost:8888/yang/api/getHistoryList',
    method: 'get'
  })
}
