import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import NotFound from '@/views/404'
import ab from '@/views/modules/ab/list'


//2.配置路由   注意：名字
const routes = [{
	path: '/ab',
    component: ab,
   },
  
  {
    path: '/',
    name: '地址簿',
    redirect: '/ab'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
