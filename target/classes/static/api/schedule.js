function getStoreList(params){
    return $axios({
        url:'/stores/page',
        method:'get',
        params
    })
}