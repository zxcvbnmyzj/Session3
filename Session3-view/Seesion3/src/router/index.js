import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import Main from '../views/Main.vue'
import Register from '../views/Register'
import Transfer from '../views/Transfer'
import History from '../views/History'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/main',
      name: 'Main',
      component: Main
    },
    {
      path: '/main/register',
      name: 'register',
      component: Register
    },
    {
      path: '/main/transfer',
      name: 'transfer',
      component: Transfer
    },
    {
      path: '/main/history',
      name: 'history',
      component: History
    }
  ]
})
