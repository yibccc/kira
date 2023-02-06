function getStoreList(params){
    return $axios({
        url:'/stores/page',
        method:'get',
        params
    })
}

// 修改---启用禁用接口
function enableOrDisableStore (params) {
    return $axios({
      url: '/stores',
      method: 'put',
      data: { ...params }
    })
  }
  
  // 新增---添加门店
  function addStore (params) {
    return $axios({
      url: '/stores',
      method: 'post',
      data: { ...params }
    })
  }
  
  // 修改---添加门店
  function editStore (params) {
    return $axios({
      url: '/stores',
      method: 'put',
      data: { ...params }
    })
  }

  //删除---删除门店
  function deleteStore (id){
    return $axios({
        url:'/stores',
        method:'delete',
        params:{id}
    })
  }

    // 查询详情
function queryStoreById (id) {
  return $axios({
    url: `/stores/${id}`,
    method: 'get'
  })
}

