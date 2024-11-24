const base = {
    get() {
        return {
            url : "http://localhost:8082/address/",
            name: "address",
            // 退出到首页链接
            // indexUrl: 'http://localhost:8080/contact/front/index.html'
        };
            },
    getProjectName(){
        return {
            projectName: "地址簿"
        } 
    }
}
export default base
