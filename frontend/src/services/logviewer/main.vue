<template>
  <div>
    <my-nav></my-nav>
    <b-container fluid class="mt-3">
      <b-row>
        <b-col>
          <mqmonitor></mqmonitor>
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col>
          <eslogviewer></eslogviewer>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from 'vue'
import mynav from '@/services/nav/nav'
import mqmonitor from './mqmonitor'
import eslogviewer from './eslogviewer'
Vue.component('my-nav', mynav)
Vue.component('mqmonitor', mqmonitor)
Vue.component('eslogviewer', eslogviewer)

export default {
  name: 'logviewer'
}
</script>
<style>
td {
  font-size:14px;
}
</style>