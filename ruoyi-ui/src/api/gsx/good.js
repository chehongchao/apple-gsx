import request from '@/utils/request'

// 查询GSX商品列表
export function listGood(query) {
  return request({
    url: '/gsx/good/list',
    method: 'get',
    params: query
  })
}

// 查询GSX商品详细
export function getGood(goodId) {
  return request({
    url: '/gsx/good/' + goodId,
    method: 'get'
  })
}

// 新增GSX商品
export function addGood(data) {
  return request({
    url: '/gsx/good',
    method: 'post',
    data: data
  })
}

// 修改GSX商品
export function updateGood(data) {
  return request({
    url: '/gsx/good',
    method: 'put',
    data: data
  })
}

// 删除GSX商品
export function delGood(goodId) {
  return request({
    url: '/gsx/good/' + goodId,
    method: 'delete'
  })
}
