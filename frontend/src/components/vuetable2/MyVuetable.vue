<template>
    <div class="ui">
        <filter-bar></filter-bar>
        <vuetable 
        ref="vuetable"
        api-url="http://192.168.11.159:7090/clientResponseList"
        :fields="fields"
        pagination-path=""
        :per-page="10"
        track-by="seq"
        :render-icon='renderBootstrapIcon'
        :append-params="moreParams"
        :sort-order="sortOrder"
        detail-row-component="my-detail-row"
        @vuetable:pagination-data="onPaginationData"
        ></vuetable>
        <div class="vuetable-pagination ui basic segment grid">
            <vuetable-pagination-info 
            ref="paginationInfo"
            info-template="MC로 부터 {from} to {to} of {total} Requests"
            no-data-template="요청한 데이터가 없습니다."
            ></vuetable-pagination-info>
            <vuetable-pagination 
            ref="pagination"
            @vuetable-pagination:change-page="onChangePage"></vuetable-pagination>
        </div>
    </div>
</template>

<script>
import Vuetable from 'vuetable-2/src/components/Vuetable'
import VuetablePagination from 'vuetable-2/src/components/VuetablePagination'
import VuetablePaginationInfo from 'vuetable-2/src/components/VuetablePaginationInfo'
import accounting from 'accounting'
import moment from 'moment'
import Vue from 'vue'
import CustomActions from './CustomAction'
import DetailRow from './DetailRow'
import FilterBar from './FilterBar'
import VueEvents from 'vue-events'
import FieldDefs from './FieldDefs.js'

Vue.use(VueEvents)
Vue.component('custom-actions', CustomActions)
Vue.component('my-detail-row', DetailRow)
Vue.component('filter-bar', FilterBar)

export default {
  components: {
    Vuetable,
    VuetablePagination,
    VuetablePaginationInfo
  },
  mounted () {
    this.$events.$on('filter-set', eventData => this.onFilterSet(eventData))
    this.$events.$on('filter-reset', e => this.onFilterReset())
    this.$events.$on('detail-view', index => this.$refs.vuetable.toggleDetailRow(index))
  },
  data () {
    return {
      css: {
        tableClass: 'ui blue selectable celled stackable attached table',
        loadingClass: 'loading',
        ascendingIcon: 'blue chevron up icon',
        descendingIcon: 'blue chevron down icon',
        detailRowClass: 'vuetable-detail-row',
        sortHandleIcon: 'grey sidebar icon'
      },
      sortOrder: [
        {
          field: 'regDate',
          sortField: 'regDate',
          direction: 'desc'
        }
      ],
      moreParams: {

      },
      fields: FieldDefs
    }
  },
  methods: {
    chgupper (value) {
      return value.toUpperCase()
    },
    chgulower (value) {
      return value.toLowerCase()
    },
    methodLabel (value) {
      return value === 'GET'
      ? 'GET'
      : 'POST'
    },
    formatNumber (value) {
      return accounting.formatNumber(value, 2)
    },
    formatDate (value, fmt = 'D MMM YYYY') {
      return (value == null) ? '' : moment(value, 'YYYY-MM-DD HH:mm:ss').format(fmt)
    },
    onPaginationData (paginationData) {
      this.$refs.pagination.setPaginationData(paginationData)
      this.$refs.paginationInfo.setPaginationData(paginationData)
    },
    onChangePage (page) {
      this.$refs.vuetable.changePage(page)
    },
    renderBootstrapIcon (classes, options) {
      return `<span class="${classes.join(' ')}" ${options}></span>`
    },
    omitStr (value) {
      return (value.length > 30) ? value.substring(0, 30) + '...' : value
    },
    onCellClicked (data, field, event) {
      this.$refs.vuetable.toggleDetailRow(data.seq)
    },
    onFilterSet (filterText) {
      this.moreParams = {
        'filter': filterText
      }
      Vue.nextTick(() => this.$refs.vuetable.refresh())
    },
    onFilterReset () {
      this.moreParams = {}
      Vue.nextTick(() => this.$refs.vuetable.refresh())
    }
  }
}
</script>

<style>
.ui.table tr td{
  font-size: 14px
}
</style>