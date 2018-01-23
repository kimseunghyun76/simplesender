import Vue from 'vue'
import Router from 'vue-router'
import McMockup from '@/components/McMockup'
import Coder from '@/components/Coder'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'McMockup',
      component: McMockup
    },
    {
      path: '/coder',
      name: 'Coder',
      component: Coder
    }
  ]
})
