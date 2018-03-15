// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'

import axios from 'axios'
import firebase from 'firebase'
import firebaseconfig from './config/firebaseconfig.js'

// BootstrapVue plugin
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// router setup
import routes from './routes/routes'

// plugin setup
Vue.use(VueRouter)
Vue.use(BootstrapVue)

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkAcvtiveClass: 'nav-item active'
})

router.beforeEach((to, from, next) => {
  let currentUser = firebase.auth().currentUser
  let requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  if (requiresAuth && !currentUser) next('/auth/login')
  else if (!requiresAuth && currentUser) next('/mcc/logviewer')
  else next()
})

let app
Vue.prototype.$http = axios
Vue.config.productionTip = false
firebase.initializeApp(firebaseconfig)
firebase.auth().onAuthStateChanged(function (user) {
  if (!app) {
    /* eslint-disable no-new */
    new Vue({
      el: '#app',
      render: h => h(App),
      router
    })
  }
})

