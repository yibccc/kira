function getRuleList(params){
    return $axios({
        url:'/rules/page',
        method:'get',
        params
    })
}
  
  // 新增---添加规则
  function addRule (params) {
    return $axios({
      url: '/rules',
      method: 'post',
      data: { ...params }
    })
  }
  
  // 修改---添加规则
  function editRule (params) {
    return $axios({
      url: '/rules',
      method: 'put',
      data: { ...params }
    })
  }

  //删除---删除规则
  function deleteRule (id){
    return $axios({
        url:'/rules',
        method:'delete',
        params:{id}
    })
  }

  // function getJobList(params){
  //   return $axios({
  //       url:'/jobs/list',
  //       method:'get',
  //       params
  //   })
  // }
//获取门店列表
function getStoreList(params){
    return $axios({
        url:'/stores/list',
        method:'get',
        params
    })
}
    // 查询详情
function queryRuleById (id) {
    return $axios({
        url: `/rules/${id}`,
        method: 'get'
    })
}
