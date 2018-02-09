<template>
  <div id="main">
    <b-container fluid>
      <b-row>
        <b-col cols="2" v-bind:style="styleObject" >
          <left-navbar></left-navbar>
        </b-col>
        <b-col>
          <top-navbar></top-navbar>
          <mcc-content></mcc-content>
          <content-footer></content-footer>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
  import LeftNavbar from './LeftNavbar.vue'
  import TopNavbar from './TopNavbar.vue'
  import ContentFooter from './ContentFooter.vue'
  import MccContent from './Content.vue'
  export default {
    components: {
      LeftNavbar,
      TopNavbar,
      ContentFooter,
      MccContent
    },
    data () {
      return {
        styleObject: {
          backgroundColor: 'rgb(0, 80, 90)',
          height: document.documentElement.clientHeight + 'px'
        }
      }
    },
    created: function () {
      window.addEventListener('scroll', this.autoheight)
    },
    destroyed: function () {
      window.removeEventListener('scroll', this.autoheight)
    },
    methods: {
      autoheight: function () {
        this.styleObject.height = document.body.scrollHeight + 'px'
      }
    }
  }
</script>

<style>
#main {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
}
</style>