<template>
  <div id="app">
    <b-container fluid>
      <b-row>
        <b-col>
          <http-sender></http-sender>
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col>
          <b-card title="FROM MC REQUEST">
            <my-vuetable
              api-url="http://192.168.11.159:7090/clientResponseList"
              :fields="fields"
              :sort-order="sortOrder"
              :append-params="moreParams"
              :track-by="trackBy"
              detail-row-component="my-detail-row"
            ></my-vuetable>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
    <!--div>selectedmethod: <strong>{{ selectedmethod }}</strong></div-->
  </div>
</template>

<script>
import Vue from 'vue'
import MyVuetable from '@/components/vuetable2/MyVuetable'
import FieldDefs from './vuetable2/FieldDefs.js'
import DetailRow from './vuetable2/DetailRow'
import httpSender from './httpSender'

Vue.component('http-sender', httpSender)
Vue.component('my-detail-row', DetailRow)

export default {
  name: 'router',
  components: {
    MyVuetable
  },
  data () {
    return {
      // MyVueTable
      fields: FieldDefs,
      sortOrder: [
        {
          field: 'regDate',
          sortField: 'regDate',
          direction: 'desc'
        }
      ],
      moreParams: {
      },
      trackBy: 'seq'
    }
  },
  methods: {
  },
  computed: {
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
