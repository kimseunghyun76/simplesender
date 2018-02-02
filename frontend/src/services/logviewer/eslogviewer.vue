<template>
  <b-card title="Mcc Log" sub-title="MCC을 통해 진행되는 로그를 조회함">
    <b-container fluid>
      <b-row class="mt-3">
        <b-col cols="8">
          <b-input-group  size="sm" prepend="Group">
            <b-form-select v-model="program" :options="programOptions">
            </b-form-select>
            <b-form-select v-model="control" :options="controlOptions">
            </b-form-select>
            <b-form-select v-model="severity" :options="severityOptions">
            </b-form-select>
            <b-form-select v-model="searchname" :options="searchnameOptions">
            </b-form-select>
            <b-form-input
              id="targetUrl"
              type="text"
              v-model.trim="searchvalue"
              placeholder="Search for" style="width:200px"></b-form-input>
            <b-btn variant="info" @click="getEsList" size="sm"> Search </b-btn>
          </b-input-group>
        </b-col>
        <b-col cols="4" >
        </b-col>
      </b-row>
      <b-row class="mt-1">
        <b-col cols="2">
          <b-input-group size="sm" prepend="Date">
            <date-picker v-model="startDate" :config="datePickerConfig"></date-picker>
            <date-picker v-model="endDate" :config="datePickerConfig"></date-picker>
          </b-input-group>
        </b-col>
        <b-col cols="2">
          <b-input-group  size="sm" prepend="Paging">
            <b-form-select v-model="pagesize" :options="pagesizeOptions">
            </b-form-select>
          </b-input-group>
        </b-col>
        <b-col cols="8" class="text-right">
          <h5>Total : {{ eslogtotal }}</h5>
        </b-col>
      </b-row>
    </b-container>
    <b-table hover :items="esloglist" :fields="eslogfields"  class="mt-3">
<!-- we use @click.stop here to prevent emitting of a 'row-clicked' event  -->
      <template slot="logMsg" slot-scope="data">
        <b-button size="sm" @click.stop="data.toggleDetails" class="mr-2">
        {{ data.detailsShowing ? 'Hide' : 'Show'}} Details
        </b-button>
      </template>
<!-- In some circumstances you may need to use @click.native.stop instead -->
<!-- As `row.showDetails` is one-way, we call the toggleDetails function on @change -->
      <template slot="row-details" slot-scope="data">
        <b-card>
          <b-row>
            <b-col cols="2">timeStamp</b-col>
            <b-col>{{data.item.timeStamp}}</b-col>
          </b-row>
          <b-row>
            <b-col cols="2">log</b-col>
            <b-col>{{data.item.msgCode}}</b-col>
          </b-row>
          <b-row>
            <b-col cols="2">srcMrn</b-col>
            <b-col>{{data.item.srcMrn}}</b-col>
          </b-row>
          <b-row>
            <b-col cols="2">dstMrn</b-col>
            <b-col>{{data.item.dstMrn}}</b-col>
          </b-row>
          <b-row>
            <b-col class="mt-3">
              <b-form-textarea v-model="data.item.logMsg" placeholder="S-100 Payload" :rows="11" :max-rows="12" readonly class='responseBodyForm'></b-form-textarea>
            </b-col>
          </b-row>
          <b-button size="sm" @click="data.toggleDetails"  class="mt-3">Hide Details</b-button>
        </b-card>
      </template>
    </b-table> 
  </b-card>
</template>
<script>
import EsLogfields from './config/eslogfields.js'
import datePicker from 'vue-bootstrap-datetimepicker'

export default {
  name: 'eslogviewer',
  components: {
    datePicker
  },
  data () {
    return {
      pagesize: 50,
      pagesizeOptions: [
        { value: 50, text: 'limit 50' },
        { value: 100, text: 'limit 100' },
        { value: 500, text: 'limit 500' },
        { value: 1000, text: 'limit 1,000' }
      ],
      program: '',
      programOptions: [
        { value: '', text: 'All-Program' },
        { value: 0, text: 'DAEMON' },
        { value: 1, text: 'GATEWAY' }
      ],
      control: '',
      controlOptions: [
        { value: '', text: 'All-Control' },
        { value: 0, text: 'CONTROL' },
        { value: 1, text: 'NETWORK' },
        { value: 2, text: 'WRITE' },
        { value: 3, text: 'READ' },
        { value: 4, text: 'ACCESS' }
      ],
      severity: '',
      severityOptions: [
        { value: '', text: 'All-Severity' },
        { value: 0, text: 'FATAL' },
        { value: 1, text: 'ERROR' },
        { value: 2, text: 'WARN' },
        { value: 3, text: 'INFO' },
        { value: 4, text: 'DEBUG' }
      ],
      searchname: 'logMsg',
      searchnameOptions: [
        { value: 'logMsg', text: 'Message' },
        { value: 'uuid', text: 'UUID' },
        { value: 'dstMrn', text: 'DSTMRN' },
        { value: 'srcMrn', text: 'SRCMRN' }
      ],
      searchvalue: '',
      datePickerConfig: {
        format: 'YYYY-MM-DD',
        useCurrent: false
      },
      startDate: new Date().toISOString().split('T')[0],
      endDate: new Date().toISOString().split('T')[0],
      eslogfields: EsLogfields,
      esloglist: [
      ],
      eslogtotal: 0
    }
  },
  methods: {
    getEsList () {
      this.$http.post('http://192.168.11.159:7090/MccLogList', {
        pagesize: this.pagesize,
        program: this.program,
        control: this.control,
        severity: this.severity,
        searchname: this.searchname,
        searchvalue: this.searchvalue,
        startDate: this.startDate,
        endDate: this.endDate
      })
      .then((response) => {
        console.log(response)
        this.esloglist = []
        this.eslogtotal = response.data.totalRecords
        response.data.data.forEach(queue => {
          this.esloglist.push(
            {
              timeStamp: queue.timeStamp,
              componentCode: queue.componentCode,
              programCode: queue.programCode,
              severityCode: queue.severityCode,
              msgCode: queue.msgCode,
              uuid: queue.uuid,
              srcMrn: queue.srcMrn,
              dstMrn: queue.dstMrn,
              logMsg: decodeURI(queue.logMsg).replace(/\+/g, ' ')
            }
          )
        })
      })
      .catch(function (error) {
        console.log(error)
        alert('서버을 확인해 주시길 바랍니다.')
      })
    }
  }
}
</script>