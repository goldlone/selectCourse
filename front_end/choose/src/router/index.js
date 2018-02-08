import Vue from 'vue'
import Router from 'vue-router'
import self from '../components/self'
import update from  '../components/update'
import viewInfo from '../components/viewInfo/viewInfo'
import modifyInfo from '../components/modifyInfo/modifyInfo'
import chooseClass from '../components/chooseClass/chooseClass'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: self
    },
    {
      path:'/viewInfo',
      name:'viewInfo',
      component:viewInfo
    },
    {
      path:'/modifyInfo',
      name:'modifyInfo',
      component:modifyInfo
    },
    {
      path:'/chooseClass',
      name:'chooseClass',
      component:chooseClass
    }
  ]
})
