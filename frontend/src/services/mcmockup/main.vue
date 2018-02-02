<template>
  <div>
    <my-nav></my-nav>
    <b-container fluid class="mt-3">
      <b-row>
        <b-col>
          <http-sender></http-sender>
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col>
          <logtable></logtable>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from 'vue'
import mynav from '@/services/nav/nav'
import logtable from './logtable'
import httpSender from './httpSender'

Vue.component('my-nav', mynav)
Vue.component('http-sender', httpSender)
Vue.component('logtable', logtable)

export default {
  name: 'mcmockup'
}
</script>