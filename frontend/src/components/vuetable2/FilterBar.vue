<template>
<b-container fluid>
  <b-row class="mb-3">
    <b-col cols="2">
      <b-input-group size="sm" prepend="Date">
        <date-picker v-model="filtervalue.startDate" :config="datePickerConfig"></date-picker>
        <date-picker v-model="filtervalue.endDate" :config="datePickerConfig"></date-picker>
      </b-input-group>
    </b-col>
    <b-col cols="6">
      <b-input-group size="sm" prepend="seaerch">
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
        <b-button variant="info" @click="doFilter">Search</b-button>
        <b-button @click="resetFilter">Reset</b-button>
      </b-button-group>
      </b-input-group>
    </b-col>
  </b-row>
</b-container>
</template>

<script>
import datePicker from 'vue-bootstrap-datetimepicker'

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
  methods: {
    doFilter () {
      this.$events.fire('filter-set', this.filtervalue)
    },
    resetFilter () {
      this.filterText = ''
      this.$events.fire('filter-reset')
    }
  }
}
</script>
<style>
</style>