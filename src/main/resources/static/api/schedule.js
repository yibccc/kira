function getStoreList(params){
    return $axios({
        url:'/stores/lift',
        method:'get',
        params
    })
}