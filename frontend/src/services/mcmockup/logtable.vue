<template>
  <div>
    <b-card title="Get messages" sub-title="This target is http://106.248.228.114:7090/">
      <my-vuetable
        api-url="apiurl"
        :fields="fields"
        :sort-order="sortOrder"
        :append-params="moreParams"
        :track-by="trackBy"
        detail-row-component="my-detail-row"
      ></my-vuetable>
    </b-card>
  </div>
</template>
<script>
import Vue from 'vue'
import MyVuetable from '@/components/vuetable2/MyVuetable'
import FilterBar from './vuetableconfig/FilterBar'
import FieldDefs from './vuetableconfig/FieldDefs.js'
import DetailRow from './vuetableconfig/DetailRow'
import backend from '../../config/backend.js'

Vue.component('my-detail-row', DetailRow)
Vue.component('filter-bar', FilterBar)

export default {
  name: 'logtable',
  components: {
    MyVuetable
  },
  data () {
    return {
      // MyVueTable
      apiurl: backend.restapi.clientResponseList,
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
  }
}
</script>