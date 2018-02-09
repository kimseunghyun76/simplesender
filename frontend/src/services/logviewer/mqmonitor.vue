<template>
  <b-card title="MQ Status" :sub-title="'Message Queues for Mcc ' + ticker">
    <b-table hover small :items="queuelist" :fields="queuefields">
      <template slot="status" slot-scope="data">
        <div style="color:green" v-if="data.value=='running'">
          <i class="fas fa-cog fa-spin"></i> {{data.value}}
        </div>
        <div style="color:grey" v-else>
          <i class="fas fa-cog"></i> {{data.value}}
        </div>
      </template>
    </b-table> 
    <div class="text-center" style="height:100px;color:grey" v-if="intervalStatus==='OFF'">
      <i class="fas fa-spinner fa-spin fa-3x"></i>
    </div>
  </b-card>
</template>
<script>
import Queuefields from './config/queuefields.js'
export default {
  name: 'mqmonitor',
  data () {
    return {
      queuefields: Queuefields,
      queuelist: [
      ],
      intervalStatus: 'OFF',
      timeoutID: '',
      tickermax: 3,
      ticker: 3
    }
  },
  created: function () {
    this.getMqList()
  },
  destroyed: function () {
    window.clearTimeout(this.timeoutID)
  },
  methods: {
    getMqList () {
      this.$http.post('http://192.168.11.159:7090/queuelist')
      .then((response) => {
        // console.log(response)
        // TODO : 차후에 배열값을 비우고 채워넣는 것 말고, 치환하는 방법도 강구 필요
        this.intervalStatus = 'ON'
        this.queuelist = []
        response.data.forEach(queue => {
          this.queuelist.push(
            {
              status: queue.state,
              name: queue.name,
              consumer: queue.consumers,
              total: queue.messages,
              ready: queue.messages_ready,
              unacked: queue.messages_unacknowledged,
              inMemory: queue.messages_ram,
              persistent: queue.messages_persistent,
              processMemory: Math.round(queue.memory / 1024, 2) + 'kB'
            }
          )
        })
        this.ticker = this.tickermax
        this.tick()
      })
      .catch(function (error) {
        console.log('RabbitMQ를 확인해 주시길 바랍니다.' + error)
        this.intervalStatus = 'OFF'
      })
    },
    tick () {
      this.ticker = this.ticker - 1
      if (this.ticker === -1) {
        this.getMqList()
      } else {
        this.timeoutID = setTimeout(this.tick, 1000)
      }
    }
  }
}
</script>