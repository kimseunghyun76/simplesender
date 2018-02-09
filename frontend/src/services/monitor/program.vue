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
                <div v-if="port===''">
                  <small class="text-muted"> SERVICE PID : {{pid}} </small><br/>
                  <small class="text-muted"> Session Name : {{sessionname}} </small><br/>
                  <small class="text-muted"> Session : {{session}} </small><br/>
                  <small class="text-muted"> Used Memory : {{usedmem}} </small><br/>
                  <small class="text-muted"> Memory Using Status : {{memusingstatus}} </small><br/>
                  <small class="text-muted"> User : {{user}} </small><br/>
                  <small class="text-muted"> CPU Time : {{cputime}} </small><br/>
                  <small class="text-muted"> Path : {{path}} </small>
                </div>
                <div v-else>
                  <small class="text-muted"> SERVICE PID : {{pid}} </small> | 
                  <small class="text-muted" > SERVICE PORT : {{port}} </small>
                </div>
              </div>
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
        status: 'OFF',
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
        this.$http.post('http://192.168.11.159:7090/status', {
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
          this.tick()
        })
        .catch(function (error) {
          console.log(error)
        })
      },
      serviceStart () {
        this.status = 'Hold'
        this.ticker = 20
        this.$http.post('http://192.168.11.159:7090/pstart', {
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
          this.status = 'Hold'
          this.$http.post('http://192.168.11.159:7090/pstop', {
            pid: this.pid
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