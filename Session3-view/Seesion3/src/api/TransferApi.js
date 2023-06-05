import request from '../utils/request'
export function TransferAsset (data) {
  return request({
    url: 'http://localhost:8888/yang/api/transferAsset',
    method: 'post',
    data
  })
}
