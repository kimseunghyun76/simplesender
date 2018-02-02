import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/services/auth/Login'
import Signup from '@/services/auth/Signup'
import firebase from 'firebase'
import Logviewer from '@/services/logviewer/main'
import Coder from '@/services/coder/main'
import McMockup from '@/services/mcmockup/main'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '*',
      redirect: '/auth/login'
    },
    {
      path: '/',
      redirect: '/auth/login'
    },
    {
      path: '/auth/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/auth/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/mcc/mockup',
      name: 'McMockup',
      component: McMockup,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/mcc/coder',
      name: 'Coder',
      component: Coder,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/mcc/logviewer',
      name: 'Logviewer',
      component: Logviewer,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  let currentUser = firebase.auth().currentUser
  let requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  if (requiresAuth && !currentUser) next('/auth/login')
  else if (!requiresAuth && currentUser) next('/mcc/logviewer')
  else next()
})

export default router
