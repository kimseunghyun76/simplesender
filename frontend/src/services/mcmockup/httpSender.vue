<template>
  <div>
  <b-card title="HTTP SENDER"
          sub-title="This is a http sender with some header and body(payload) to target url.">
    <b-container fluid>
      <b-row class="mt-1">
        <b-col>
          <b-input-group>
            <b-input-group-addon>
              <b-form-select v-model="selectedmethod" :options="options" style="width:200px">
              </b-form-select>
            </b-input-group-addon>
            <b-form-input
              id="targetUrl"
              type="text"
              v-model.trim="targeturl"
              :state="urlState"
              aria-describedby="targetUrlFeeback"
              placeholder="Enter URL"></b-form-input>
              <b-btn variant="info" @click="sendToMc"> SEND </b-btn>
            <b-form-invalid-feedback  style="display:block">
              {{targetUrlFeeback}}
            </b-form-invalid-feedback>
          </b-input-group>
        </b-col>
      </b-row>
      <b-row class="mt-1">
        <b-col>
          <!--REQUEST LAYER-->
          <div v-for="(input, index) in inputs" :key="input.id"  class="mt-1">
            <b-input-group>
              <b-form-input type="text" id="headerKey" placeholder="New Key" size="sm" v-model="input.name"></b-form-input>
              <b-form-input type="text" id="headerValue" placeholder="Value" size="sm" v-model="input.value" style="width:60%"></b-form-input>
              <b-btn size="sm" variant="secondary" @click="deleteRow(index)"> X </b-btn>
              <!-- Main form input -->
            </b-input-group>
          </div>
          <div class="text-right mt-1">
            <b-button variant="info" @click="addRow" size="sm">Add Header</b-button>
          </div>
          <div class="text-right mt-1">
            <b-form-select v-model="selectedsvlist" :options="svlist" :state="getSVFile" size="sm">
            </b-form-select>
            <b-form-textarea v-model="payload" placeholder="Enter something" :rows="4" :max-rows="10" contenteditable class='responseBodyForm'></b-form-textarea>
          </div>
        </b-col>
        <b-col>
          <b-card notitle>
            <b-input-group class="mt-2" size="sm" prepend="AUTO">
              <b-form-select v-model="intervalSec" :options="intervalOptions">
              </b-form-select>
              <b-button variant="outline-success" @click="intervalSendStart" size="sm" v-if="responseStatus==='STOP'">Start</b-button>
              <b-button variant="outline-success" @click="intervalSendStop" size="sm" v-else>Stop</b-button>
                  <b-button variant="outline-success" @click="intervalReset" size="sm" v-if="responseStatus==='STOP'">RESET</b-button>
            </b-input-group>
          </b-card>
          <!--RESPONSE LAYER-->
          <b-card notitle class="mt-1">
            <h5>status : {{responseStatus}}</h5>
            <div>
              <span>
                Total <b-badge variant="light">{{sendTotalcount}}</b-badge>
              </span>
              <span>
                Success <b-badge variant="light">{{sendSuccesscount}}</b-badge>
              </span>
              <span>
                Fail <b-badge variant="light">{{sendFailcount}}</b-badge>
              </span>
              <span>
              </span>
            </div>
            <pre class="responseHeader">{{responseHeader}}</pre>
            <b-form-textarea v-model="responseData" placeholder="Response Body" :rows="3" :max-rows="12" readonly class='responseBodyForm'></b-form-textarea>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </b-card>
  </div>
</template>
<script>
// intervalID 은 내부 Data로 활용불가
import backend from '../../config/backend.js'
var intervalID
export default {
  name: 'httpSender',
  components: {
  },
  data () {
    return {
      targetUrlFeeback: '',
      responseStatus: 'STOP',
      responseHeader: '',
      responseData: '',
      targeturl: backend.mockup.defaulttarget,
      payload: '',
      inputs: [
        { name: 'dstMRN', value: 'urn:mrn:smart:service:instance:mof:S40T' },
        { name: 'srcMRN', value: 'urn:mrn:mcl:vessel:dma:poul-lowenorn' }
      ],
      selectedmethod: 'GET',
      options: [
        { value: 'GET', text: 'GET' },
        { value: 'POST', text: 'POST' },
        { value: 'PUT', text: 'PUT', disabled: true },
        { value: 'PATCH', text: 'PATCH', disabled: true },
        { value: 'DELETE', text: 'DELETE', disabled: true },
        { value: 'OPTIONS', text: 'OPTIONS', disabled: true }
      ],
      intervalSec: '5000',
      intervalOptions: [
        { value: '5000', text: '5 sec' },
        { value: '3000', text: '3 sec' },
        { value: '1000', text: '1 sec' },
        { value: '500', text: '0.5 sec' },
        { value: '100', text: '0.1 sec' },
        { value: '50', text: '0.05 sec' },
        { value: '10', text: '0.01 sec' }
      ],
      selectedsvlist: '',
      svlist: [
        { value: '', text: 'CHOOSE E-NAVI SV XXX SERVICE' }
      ],
      sendTotalcount: 0,
      sendSuccesscount: 0,
      sendFailcount: 0
    }
  },
  created: function () {
    this.$http.post(backend.restapi.svlist)
    .then((response) => {
      response.data.forEach(element => {
        this.svlist.push(element)
      })
    })
    .catch(function (error) {
      console.log(error)
      alert('backend server가 활성화 되어 있지 않습니다.\n관리자에게 해당 사항에 대한 확인 요청 드립니다.')
    })
  },
  methods: {
    intervalSendStart () {
      this.responseStatus = 'START'
      this.responseHeader = 'this below textarea has fail results.'
      intervalID = window.setInterval(this.intervalSendToMc, this.intervalSec)
    },
    intervalSendStop () {
      this.responseStatus = 'STOP'
      this.responseHeader = ''
      window.clearInterval(intervalID)
    },
    intervalReset () {
      this.responseStatus = 'STOP'
      this.responseHeader = ''
      this.responseData = ''
      this.sendTotalcount = 0
      this.sendSuccesscount = 0
      this.sendFailcount = 0
    },
    addRow () {
      this.inputs.push({ name: this.inputs.index })
    },
    deleteRow (index) {
      this.inputs.splice(index, 1)
    },
    sendToMc () {
      this.$http.post(backend.restapi.svpath, {
        targetUrl: this.targeturl,
        method: this.selectedmethod,
        headers: this.inputs,
        body: this.payload
      })
      .then((response) => {
        this.responseStatus = 'STOP'
        this.responseHeader = response.data.headers
        this.responseData = response.data.payload
        this.$events.fire('table-reload', '')
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    intervalSendToMc () {
      this.sendTotalcount++
      this.$http.post(backend.restapi.svpath, {
        targetUrl: this.targeturl,
        method: this.selectedmethod,
        headers: this.inputs,
        body: this.payload
      })
      .then((response) => {
        if (response.data.responseCode === 200) {
          this.sendSuccesscount++
        } else {
          this.sendFailcount++
          this.responseData = this.responseData + response.data.responseCode + ':' + response.data.payload + '\n'
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    }
  },
  computed: {
    urlState () {
      if (this.targeturl.length < 2) {
        this.targetUrlFeeback = 'Enter at least 3 letters'
      } else {
        this.targetUrlFeeback = ''
        return true
      }
      return false
    },
    getSVFile () {
      if (this.selectedsvlist !== '') {
        this.$http.post(backend.restapi.svfile, {
          fileName: this.selectedsvlist
        })
        .then((response) => {
          console.log(response)
          if (typeof response.data !== 'string') {
            this.payload = JSON.stringify(response.data, undefined, 4)
          } else {
            this.payload = response.data
          }
        })
        .catch(function (error) {
          console.log(error)
        })
        return true
      }
      return false
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.responseBodyForm {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  font-size: 12px;
}
.responseHeader {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  font-size: 10px;
}
</style>
