// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import axios from 'axios'
import firebase from 'firebase'
import firebaseconfig from './firebaseconfig.js'

Vue.use(BootstrapVue)
Vue.prototype.$http = axios
Vue.config.productionTip = false
let app

firebase.initializeApp(firebaseconfig)
firebase.auth().onAuthStateChanged(function (user) {
  if (!app) {
    /* eslint-disable no-new */
    new Vue({
      el: '#app',
      router,
      template: '<App/>',
      components: { App }
    })
  }
})
