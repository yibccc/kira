//注册--新增用户
function addUser(params){
    return $axios({
        url:'/users/register',
        method: 'post',
        data:{...params}
    })
}
//修改用户密码
function editUser(params){
    return $axios({
        url:'/users',
        method:'put',
        data:{...params}
    })
}
//注销--删除用户
function deleteUser(id){
    return $axios({
        url:'/users',
        method:'delete',
        params:{id}
    })
}
