import Vue from 'vue'
import Router from 'vue-router'

import McMockup from '@/components/contents/mcmockup/main'
import Coder from '@/components/contents/coder/main'
import Login from '@/components/auth/Login'
import Signup from '@/components/auth/Signup'
import firebase from 'firebase'

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
    }
  ]
})

router.beforeEach((to, from, next) => {
  let currentUser = firebase.auth().currentUser
  let requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  if (requiresAuth && !currentUser) next('/auth/login')
  else if (!requiresAuth && currentUser) next('/mcc/mockup')
  else next()
})

export default router
