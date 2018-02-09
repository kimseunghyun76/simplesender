<template>
  <div>
    <mqmonitor></mqmonitor>
    <eslogviewer class="mt-3"></eslogviewer>
  </div>
</template>
<script>
import Vue from 'vue'
import mqmonitor from './mqmonitor'
import eslogviewer from './eslogviewer'

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