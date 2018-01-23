<template>
  <div>
  <b-card title="Mcc Http Sender"
          sub-title="This is a MC Mockup Page for sending a message from the Marine Cloud to the Marine Cloud Connector.">
    <b-container fluid>
      <b-row class="mt-3">
        <b-col>
          <b-input-group>
            <b-input-group-addon>
              <b-form-select v-model="selectedmethod" :options="options" style="width:200px">
              </b-form-select>
            </b-input-group-addon>
            <!-- Main form input -->
            <b-form-input
              id="targetUrl"
              type="text"
              v-model.trim="targeturl"
              :state="urlState"
              aria-describedby="targetUrlFeeback"
              placeholder="Enter URL"></b-form-input>
            <b-btn variant="info" @click="sendToMc"> SEND </b-btn>
          </b-input-group>
          <b-form-invalid-feedback  style="display:block">
            {{targetUrlFeeback}}
          </b-form-invalid-feedback>
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col>
          <!--REQUEST LAYER-->
          <b-card notitle>
            <div class="text-right">
              <b-button variant="success" @click="addRow" >Add Header</b-button>
            </div>
            <div v-for="(input, index) in inputs" :key="input.id"  class="mt-2">
              <b-input-group>
                <b-form-input type="text" id="headerKey" placeholder="New Key" size="sm" v-model="input.name"></b-form-input>
                <b-form-input type="text" id="headerValue" placeholder="Value" size="sm" v-model="input.value"></b-form-input>
                <b-btn size="sm" variant="secondary" @click="deleteRow(index)"> X </b-btn>
                <!-- Main form input -->
              </b-input-group>
            </div>
            <div class="text-right mt-3">
              <b-form-select v-model="selectedsvlist" :options="svlist" :state="getSVFile">
              </b-form-select>
              <b-form-textarea v-model="payload" placeholder="Enter something" :rows="6" :max-rows="10" contenteditable class='responseBodyForm'></b-form-textarea>
            </div>
          </b-card>
        </b-col>
        <b-col>
          <!--RESPONSE LAYER-->
          <b-card notitle>
            <h5>status :{{responseStatus}}</h5>
            <pre class="responseHeader">{{responseHeader}}</pre>
            <b-form-textarea v-model="responseData" placeholder="Response Body" :rows="11" :max-rows="12" readonly class='responseBodyForm'></b-form-textarea>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </b-card>
  </div>
</template>
<script>
export default {
  name: 'httpSender',
  components: {
  },
  data () {
    return {
      targetUrlFeeback: '',
      responseStatus: '',
      responseStatusText: '',
      responseHeader: 'no data',
      responseData: '',
      targeturl: 'http://143.248.55.83:8088',
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
      selectedsvlist: '',
      svlist: [
        { value: '', text: 'CHOOSE E-NAVI SV XXX SERVICE' }
      ]
    }
  },
  created: function () {
    this.$http.post('http://192.168.11.159:7090/svlist')
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
    addRow () {
      this.inputs.push({ name: this.inputs.index })
    },
    deleteRow (index) {
      this.inputs.splice(index, 1)
    },
    sendToMc () {
      this.$http.post('http://192.168.11.159:7090/path', {
        targetUrl: this.targeturl,
        method: this.selectedmethod,
        headers: this.inputs,
        body: this.payload
      })
      .then((response) => {
        this.responseStatus = response.data.responseCode
        this.responseHeader = response.data.headers
        this.responseData = response.data.payload
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
        this.$http.post('http://192.168.11.159:7090/svfile', {
          fileName: this.selectedsvlist
        })
        .then((response) => {
          this.payload = response.data
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
