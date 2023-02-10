function getEmployeeList(params){
    return $axios({
        url:'/employees/page',
        method:'get',
        params
    })
}

// 修改---启用禁用接口
function enableOrDisableEmployee (params) {
    return $axios({
      url: '/employees',
      method: 'put',
      data: { ...params }
    })
  }
  
  // 新增---添加员工
  function addEmployee (params) {
    return $axios({
      url: '/employees',
      method: 'post',
      data: { ...params }
    })
  }
  
  // 修改---编辑员工
  function editEmployee (params) {
    return $axios({
      url: '/employees',
      method: 'put',
      data: { ...params }
    })
  }

  //删除---删除员工
  function deleteEmployee (id){
    return $axios({
        url:'/employees',
        method:'delete',
        params:{id}
    })
  }

  //获取职位列表
  function getJobList (params) {
    return $axios({
      url:'/jobs/list',
      method:'get',
      params
    })
  }

  //获取门店列表
  function getStoreList(params){
    return $axios({
      url:'/stores/list',
      method:'get',
      params
    })
  }

  // 查询详情
function queryEmployeeById (id) {
  return $axios({
    url: `/employees/${id}`,
    method: 'get'
  })
}

//获取偏好
function getFlavorById(id){
  return $axios({
    url:`/employees/flavors/${id}`,
    method:'get'
  })
}
