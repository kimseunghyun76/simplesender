<template>
<b-container fluid>
  <b-row class="mb-3">
    <b-col cols="3">
      <b-input-group size="sm" prepend="Date">
        <date-picker v-model="filtervalue.startDate" :config="datePickerConfig"></date-picker>
        <date-picker v-model="filtervalue.endDate" :config="datePickerConfig"></date-picker>
        <b-button variant="default" @click="deleteLog" size="sm">Log Delete</b-button>
      </b-input-group>
    </b-col>
    <b-col cols="4">
      <b-input-group size="sm" prepend="search">
        <b-form-select v-model="filtervalue.searchName" :options="options" size="sm">
        </b-form-select>
        <b-form-input
          id="targetUrl"
          type="text"
          v-model="filtervalue.searchText"
          @keyup.enter="doFilter" 
          placeholder="Searching For"
          style="width:200px" ></b-form-input>
      <b-button-group size="sm">
        <b-button variant="info" @click="doFilter" size="sm">Search</b-button>
      </b-button-group>
      </b-input-group>
    </b-col>
  </b-row>
</b-container>
</template>

<script>
import datePicker from 'vue-bootstrap-datetimepicker'
import backend from '../../../config/backend.js'

export default {
  components: {
    datePicker
  },
  data () {
    return {
      datePickerConfig: {
        format: 'YYYY-MM-DD',
        useCurrent: false
      },
      filtervalue: {
        searchText: '',
        searchName: 'DstMRN',
        startDate: new Date().toISOString().split('T')[0],
        endDate: new Date().toISOString().split('T')[0]
      },
      options: [
        { value: 'DstMRN', text: 'DstMRN' },
        { value: 'SrcMRN', text: 'SrcMRN' }
      ]
    }
  },
  mounted () {
    this.$events.$on('table-reload', this.doFilter)
  },
  methods: {
    doFilter () {
      this.$events.fire('filter-set', this.filtervalue)
    },
    deleteLog () {
      if (confirm('모든 로그 데이터를 삭제하시겠습니까?')) {
        this.$http.post(backend.restapi.clientresponsedelete)
        .then((response) => {
          if (response.data) {
            this.$events.fire('table-reload', '')
          } else {
            console.log('관리자에게 문의 바랍니다.')
          }
        })
        .catch(function (error) {
          console.log(error)
        })
      }
    }
  }
}
</script>
<style>
</style>