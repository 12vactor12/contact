import Vue from 'vue'
import App from '@/App.vue'
// element ui 完全引入
import ElementUI from 'element-ui'
import '@/assets/css/element-variables.scss'
import '@/assets/css/style.scss'
// 加载路由
// import router from '@/router/router-static.js';
import router from '@/router/router-static.js';
// 引入echart
import echarts from 'echarts'
// 引入echart主题
// import  '@/assets/js/echarts-theme-macarons.js'
import 'echarts/theme/macarons.js'
// ajax
import http from '@/utils/http.js'
// 基础配置
import base from '@/utils/base'
// 工具类
import { isAuth } from '@/utils/utils'
// storage 封装
import storage from "@/utils/storage";
// api 接口
import api from '@/utils/api'
// 数据校验工具类
import * as validate from '@/utils/validate.js'
// 后台地图
import VueAMap from 'vue-amap'
import '@/icons'
//excel导出
import JsonExcel from 'vue-json-excel'
//打印
import printJS from 'print-js'
//MD5
import md5 from 'js-md5';
Vue.prototype.$validate = validate
Vue.prototype.$http = http // ajax请求方法
Vue.prototype.$echarts = echarts
Vue.prototype.$base = base.get()
Vue.prototype.$project = base.getProjectName()
Vue.prototype.$storage = storage
Vue.prototype.$api = api
// 判断权限方法
Vue.prototype.isAuth = isAuth
// Vue.prototype.$base = base
Vue.use(ElementUI, { size: 'medium', zIndex: 3000 });
Vue.config.productionTip = false
//excel导出
Vue.component('downloadExcel', JsonExcel)
//MD5
Vue.prototype.$md5 = md5;
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
