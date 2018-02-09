<template>
  <div>
    <http-sender></http-sender>
    <logtable class="mt-3"></logtable>
  </div>
</template>
<script>
import Vue from 'vue'
import logtable from './logtable'
import httpSender from './httpSender'

Vue.component('http-sender', httpSender)
Vue.component('logtable', logtable)

export default {
  name: 'mcmockup'
}
</script>