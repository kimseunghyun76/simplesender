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
          <b-form-select v-model="msgCode" :options="msgCodeOptions" size="sm">
          </b-form-select>
        </b-col>
      </b-row>
      <b-row class="mt-1">
        <b-col cols="2">
          <b-input-group size="sm" prepend="Date">
            <date-picker v-model="startDate" :config="datePickerConfig"></date-picker>
            <date-picker v-model="endDate" :config="datePickerConfig"></date-picker>
          </b-input-group>
        </b-col>
        <b-col cols="3">
          <b-input-group  size="sm" prepend="Paging">
            <b-form-select v-model="pagesize" :options="pagesizeOptions">
            </b-form-select>
            <b-btn variant="warn" @click="deleteEsList" size="sm"> Log Erase </b-btn>
          </b-input-group>
        </b-col>
        <b-col cols="7" class="text-right">
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
    <div class='text-center' style='height:200px'>{{eslogresult}}</div>
  </b-card>
</template>
<script>
import EsLogfields from './config/eslogfields.js'
import datePicker from 'vue-bootstrap-datetimepicker'
import backend from '../../config/backend.js'

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
      msgCode: '',
      msgCodeOptions: [
        { value: '', text: 'Log Type' }
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
      eslogtotal: 0,
      eslogresult: ''
    }
  },
  created: function () {
    this.$http.post(backend.restapi.mccmsgcode)
    .then((response) => {
      response.data.forEach(element => {
        this.msgCodeOptions.push(element)
      })
    })
    .catch(function (error) {
      console.log(error)
      alert('backend server가 활성화 되어 있지 않습니다.\n관리자에게 해당 사항에 대한 확인 요청 드립니다.')
    })
  },
  methods: {
    deleteEsList () {
      if (confirm('정말 모든 로그를 삭제하시겠습니까?')) {
        this.$http.post(backend.restapi.mcclogdelete)
        .then((response) => {
          this.esloglist = []
          this.eslogtotal = 0
        })
        .catch(function (err) {
          console.log(err)
        })
      }
    },
    getEsList () {
      this.$http.post(backend.restapi.mccloglist, {
        pagesize: this.pagesize,
        program: this.program,
        control: this.control,
        severity: this.severity,
        msgCode: this.msgCode,
        searchname: this.searchname,
        searchvalue: this.searchvalue,
        startDate: this.startDate,
        endDate: this.endDate
      })
      .then((response) => {
        this.esloglist = []
        this.eslogtotal = response.data.totalRecords
        if (this.eslogtotal === 0) {
          this.eslogresult = '검색한 결과가 없습니다.'
        } else {
          this.eslogresult = '전체 ' + this.eslogtotal + '중에 ' + this.pagesize + '열만 추출되었습니다.'
        }
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
              logMsg: queue.logMsg.replace(/\s</g, '\t<').replace(/\t</g, '\n\t<').replace(/></g, '>\n<')
            }
          )
        })
      })
      .catch(function (error) {
        console.log(error)
        alert('서버를 확인해 주시길 바랍니다.')
      })
    }
  }
}
</script>