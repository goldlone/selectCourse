import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import feedBack from '../components/feedBack/feedBack'
import insertStudent from '../components/insertStudent/insertStudent'
import releaseClass from '../components/releaseClass/releaseClass'
import selectSeats from '../components/selectSeats/selectSeats'
import updateClass from '../components/updateClass/updateClass'
import updateStudent from '../components/updateStudent/updateStudent'
import viewStudent from '../components/viewStudent/viewStudent'
import changePassword from  '../components/changePassword/changePassword'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      // name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:'/feedBack',
      // name:'feedBack',
      component:feedBack
    },
    {
      path:'/insertStudent',
      // name:'insertStudent',
      component:insertStudent
    },
    {
      path:'/releaseClass',
      // name:'release-Class',
      component:releaseClass
    },
    {
      path:'/selectSeats',
      // name:'selectSeats',
      component:selectSeats
    },
    {
      path:'/updateClass',
      // name:'updateClass',
      component:updateClass
    },
    {
      path:'/updateStudent',
      // name:'updateStudent',
      component:updateStudent
    },
    {
      path:'/viewStudent',
      // name:'viewStudent',
      component:viewStudent
    },
    {
      path:'/changePassword',
      // name:'changePassword',
      component:changePassword
    }
  ]
})
