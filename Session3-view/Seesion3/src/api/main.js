import request from '../utils/request'

export function AssetList () {
  return request({
    url: 'http://localhost:8888/yang/api/getAsset',
    method: 'get'
  })
}

export function DeportmentList () {
  return request({
    url: 'http://localhost:8888/yang/api/getDeportment',
    method: 'get'
  })
}

export function AssetGroupList () {
  return request({
    url: 'http://localhost:8888/yang/api/getAssetGroup',
    method: 'get'
  })
}

export function getAssetList (data) {
  return request({
    url: 'http://localhost:8888/yang/api/getAssetList',
    method: 'post',
    data
  })
}

export function getLocationList () {
  return request({
    url: 'http://localhost:8888/yang/api/getLocationList',
    method: 'get'
  })
}
