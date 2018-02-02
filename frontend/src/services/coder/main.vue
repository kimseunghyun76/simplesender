<template>
  <div>
    <my-nav></my-nav>
    <b-container fluid class="mt-3">
      <b-row>
        <b-col>
          <endecoder></endecoder>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from 'vue'
import mynav from '@/services/nav/nav'
import endecoder from './endecoder'
Vue.component('my-nav', mynav)
Vue.component('endecoder', endecoder)

export default {
  name: 'coder'
}
</script>