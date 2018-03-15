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
      <template slot="tps" slot-scope="row">
        <trend
          :data="row.item.totallist"
          :gradient="['#6fa8dc', '#42b983', '#2c3e50']"
          :height="30"
          :width="200"
          auto-draw
          smooth>
        </trend>
        {{row.value}} msg/s
      </template>
    </b-table> 
    <div class="text-center" style="height:100px;color:grey" v-if="intervalStatus==='OFF'">
      <i class="fas fa-spinner fa-spin fa-3x"></i>
    </div>
  </b-card>
</template>
<script>
import Vue from 'vue'
import Trend from 'vuetrend'
import Queuefields from './config/queuefields.js'
import backend from '../../config/backend.js'

Vue.use(Trend)
export default {
  name: 'mqmonitor',
  data () {
    return {
      queuetotallist: [],
      queuefields: Queuefields,
      queuelist: [
      ],
      oldqueuelist: [ ],
      intervalStatus: 'OFF',
      timeoutID: '',
      tickermax: 2,
      ticker: 2,
      chartlength: 10,
      tps: 0
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
      this.$http.post(backend.restapi.queuelist)
      .then((response) => {
        // console.log(response)
        // TODO : 차후에 배열값을 비우고 채워넣는 것 말고, 치환하는 방법도 강구 필요
        this.intervalStatus = 'ON'
        this.queuelist.forEach(queue => {
          this.queuetotallist.push(queue.total)
        })
        this.queuelist = []
        response.data.forEach((queue, index) => {
          let oldtotallist = []
          let tpsperindex = 0
          if (this.oldqueuelist.length > 0) {
            tpsperindex = Math.abs((this.oldqueuelist[index].total - queue.messages) / this.tickermax, 2)
            if (this.oldqueuelist[index].totallist.length > this.chartlength - 1) {
              oldtotallist = this.oldqueuelist[index].totallist.slice(1, this.chartlength)
            } else {
              oldtotallist = this.oldqueuelist[index].totallist
            }
          }

          oldtotallist.push(queue.messages)
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
              processMemory: Math.round(queue.memory / 1024, 2) + 'kB',
              totallist: oldtotallist,
              tps: tpsperindex
            }
          )
          // console.log(oldtotallist)
        })
        this.oldqueuelist = this.queuelist
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