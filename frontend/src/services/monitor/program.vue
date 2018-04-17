<template>
  <b-card notitle>
    <div class="card-text">
      <div v-if="status==='ON'">
        <b-container>
          <b-row>
            <b-col> <h3> {{pmname}} </h3></b-col>
            <b-col class="text-right mt-3" v-if="status==='ON'">
              <b-button @click="serviceStop" variant="info" size="sm">Service Stop</b-button>
              <!-- b-progress :value="ticker" :max="tickermax" variant="info" class="mt-3" animated></b-progress -->
            </b-col>
          </b-row>
          <b-row>
            <b-col style="font-size:1.0em; color:rgb(0, 80, 90)"> <i class="fas fa-cog fa-spin"></i> {{status}} </b-col>
          </b-row>
            <b-row>
              <b-col>
                <div v-if="status==='ON'">
                  <small class="text-muted"> SERVICE IP : {{ip}} </small> | 
                  <small class="text-muted" > SERVICE PORT : {{port}} </small> <br />
                  <small class="text-muted"> {{statusLog}} </small> 
                </div>
              </b-col>
              <b-col>
              </b-col>
            </b-row>
        </b-container>
      </div>
      <div v-else>
        <b-container>
          <b-row>
            <b-col> <h3> {{pmname}} </h3></b-col>
            <b-col class="text-right mt-3" v-if="status==='OFF'">
              <b-button @click="serviceStart" variant="danger" size="sm">Service Start</b-button>
              <b-button @click="healthCheck" variant="default" size="sm">health check</b-button>
              <!-- b-progress :value="ticker" :max="tickermax" variant="info" class="mt-3" animated></b-progress -->
            </b-col>
          </b-row>
          <b-row>
            <b-col style="font-size:1.0em; color:red"> <i class="fas fa-cog fa-spin"></i> {{status}} </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <div slot="footer">
      <small class="text-muted">Last updated {{updateDateTime}}</small>
    </div>
  </b-card>
</template>
<script>
  import backend from '../../config/backend.js'
  export default {
    name: 'mcc-program',
    props: {
      pmname: {
        type: String,
        required: true
      },
      ip: {
        type: String,
        required: true
      },
      port: {
        type: String,
        required: true
      }
    },
    data () {
      return {
        status: 'HOLD',
        pid: '-',
        updateDateTime: '0',
        timeoutID: '',
        tickermax: 5,
        ticker: 5,
        pmname: this.pmname
      }
    },
    created: function () {
      this.healthCheck()
    },
    destroyed: function () {
      window.clearTimeout(this.timeoutID)
    },
    methods: {
      healthCheck () {
        this.$http.post(backend.process.status, {
          name: this.pmname,
          ip: this.ip,
          port: this.port
        })
        .then((response) => {
          this.status = response.data.status
          this.pid = response.data.pid
          this.updateDateTime = response.data.updateDateTime
          this.sessionname = response.data.sessionname
          this.session = response.data.session
          this.usedmem = response.data.usedmem
          this.memusingstatus = response.data.memusingstatus
          this.user = response.data.user
          this.cputime = response.data.cputime
          this.path = response.data.path
          this.ticker = this.tickermax
          this.statusLog = response.data.statusLog
          this.tick()
        })
        .catch(function (error) {
          console.log(error)
        })
      },
      serviceStart () {
        this.status = 'HOLD'
        this.ticker = 20
        this.$http.post(backend.process.pstart, {
          name: this.pmname
        })
        .then((response) => {
          console.log(response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
      },
      serviceStop () {
        if (confirm('서비스를 중지하게 되면, MCC 서비스 전체가 영향을 받습니다. \n그래도 중지하시겠습니까?')) {
          this.status = 'HOLD'
          this.$http.post(backend.process.pstop, {
            name: this.pmname
          })
          .then((response) => {
            console.log(response.data)
          })
          .catch(function (error) {
            console.log(error)
          })
        }
      },
      tick () {
        this.ticker = this.ticker - 1
        if (this.ticker === -1) {
          this.healthCheck()
        } else {
          this.timeoutID = setTimeout(this.tick, 1000)
        }
      }
    }
  }
</script>
<style>
</style>