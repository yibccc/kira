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
//获取员工列表
function getEmployeeList(params){
    return $axios({
        url:'/employees/list',
        method:'get',
        params
    })
}

function getSelectedInfo(params){
    return $axios({
        url:'/paiban/list',
        method:'get',
        params
    })
}
