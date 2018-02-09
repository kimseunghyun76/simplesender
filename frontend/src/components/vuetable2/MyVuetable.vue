<script>
import Vuetable from 'vuetable-2/src/components/Vuetable'
// import VuetablePagination from 'vuetable-2/src/components/VuetablePagination'
import VuetablePaginationBootstrap from './VuetablePaginationBootstrap'
import VuetablePaginationInfo from 'vuetable-2/src/components/VuetablePaginationInfo'
import accounting from 'accounting'
import moment from 'moment'
import Vue from 'vue'
import CustomActions from './CustomAction'
import VueEvents from 'vue-events'
import CssConfig from './config/VuetableCssConfig.js'

Vue.use(VueEvents)
Vue.component('custom-actions', CustomActions)

export default {
  name: 'my-vuetable',
  components: {
    Vuetable,
    // VuetablePagination,
    VuetablePaginationInfo,
    VuetablePaginationBootstrap
  },
  props: {
    apiUrl: {
      type: String,
      required: true
    },
    fields: {
      type: Array,
      required: true
    },
    sortOrder: {
      type: Array,
      default () {
        return []
      }
    },
    appendParams: {
      type: Object,
      default () {
        return {}
      }
    },
    detailRowComponent: {
      type: String
    },
    trackBy: {
      type: String
    }
  },
  mounted () {
    this.$events.$on('filter-set', eventData => this.onFilterSet(eventData))
    this.$events.$on('detail-view', index => this.$refs.vuetable.toggleDetailRow(index))
  },
  data () {
    return {
      css: CssConfig
    }
  },
  render (h) {
    return h(
      'div',
      {
        class: { ui: true }
      },
      [
        h('filter-bar'),
        this.renderVuetable(h),
        this.renderPagination(h)
      ]
    )
  },
  methods: {
    // render related functions
    renderVuetable (h) {
      return h(
        'vuetable',
        {
          ref: 'vuetable',
          props: {
            apiUrl: this.apiUrl,
            fields: this.fields,
            paginationPath: '',
            perPage: 10,
            multiSort: false,
            sortOrder: this.sortOrder,
            appendParams: this.appendParams,
            trackBy: this.trackBy,
            detailRowComponent: this.detailRowComponent,
            renderIcon: this.renderBootstrapIcon,
            css: this.css.table
          },
          on: {
            'vuetable:cell-clicked': this.onCellClicked,
            'vuetable:pagination-data': this.onPaginationData
          },
          scopedSlots: this.$vnode.data.scopedSlots
        }
      )
    },
    renderPagination (h) {
      return h(
        'div',
        {
          class: {
            'vuetable-pagination': true,
            'ui': true,
            'basic': true,
            'segment': true,
            'grid': true
          }
        },
        [
          h('vuetable-pagination-info',
            {
              ref: 'paginationInfo',
              class: { 'pull-left': true },
              props: {
                infoTemplate: '{from} to {to} of {total} Requests',
                noDataTemplate: '요청한 데이터가 없습니다.'
              }
            }
          ),
          h('vuetable-pagination-bootstrap',
            {
              ref: 'pagination',
              class: { 'pull-right': true },
              props: {
                css: this.css.paginationInfo
              },
              on: {
                'vuetable-pagination:change-page': this.onChangePage
              }
            }
          )
        ]
      )
    },
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
      console.log(value.substring(0, 30))
      return (value.length > 30) ? '<pre>' + value.substring(0, 30) + '...</pre>' : value
    },
    onFilterSet (filterText) {
      this.appendParams.filter = {
        'filter': filterText
      }
      if (this.$refs.vuetable !== undefined) {
        Vue.nextTick(() => this.$refs.vuetable.refresh())
      }
    },
    onCellClicked (data, field, event) {
      console.log('cellClicked: ', field.name)
      this.$refs.vuetable.toggleDetailRow(data.seq)
    }
  }
}
</script>

<style>
div {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  font-size: 14px;
  color: black;
}
.table-bordered th {
  background-color:whitesmoke;
}
.page-item.active .page-link {
  background-color:#17a2b8;
  border-color:#17a2b8;
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  color: white;
}
</style>