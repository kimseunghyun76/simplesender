<template>
  <div>
    <div style="padding:10px;color:grey;color:white">
      <h5>
        Maritime Cloud Connector<br/>
      </h5>
    </div>
    <b-list-group>
      <b-list-group-item href="#/mcc/logviewer">
        <div style="font-size:0.8em; color:grey">
          <h6 style="color:black"><i class="fas fa-desktop"></i> &nbsp;<a href="#/mcc/logviewer"  target="_blank">LogViewer</a></h6>
          - MQ Status & Mcc Log Search
        </div>
      </b-list-group-item>
      <b-list-group-item href="#/mcc/monitor">
        <div style="font-size:0.8em; color:grey">
          <h6 style="color:black"><i class="fas fa-power-off"></i> &nbsp;<a href="#/mcc/monitor" target="_blank">Monitor</a></h6>
          - Status of RabbitMQ, Elasticsearch etc..
        </div>
      </b-list-group-item>
      <b-list-group-item href="#/mcc/mockup">
        <div style="font-size:0.8em; color:grey">
          <h6 style="color:black"><i class="fas fa-upload"></i> &nbsp;<a href="#/mcc/mockup" target="_blank">HttpSender</a></h6>
          - mockup that can send s-100 to target
        </div>
      </b-list-group-item>
      <b-list-group-item href="#/mcc/coder">
        <div style="font-size:0.8em; color:grey">
          <h6 style="color:black"><i class="fas fa-file-code"></i> &nbsp;<a href="#/mcc/coder" target="_blank">Encoder/Decoder</a></h6>
          - normal, base64 encoder/decoder
        </div>
      </b-list-group-item>
    </b-list-group>


    <b-list-group class="mt-3">
      <b-list-group-item>
        <div style="font-size:0.8em; color:grey">
          <h6 style="color:black"> Server Status <small class="text-muted">{{ticker}}</small></h6> 
        </div>
      </b-list-group-item>
      <b-list-group-item>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Memory : {{metrics['mem']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Memory Free : {{metrics['mem.free']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Processors : {{metrics['processors']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> instance.uptime : {{metrics['instance.uptime']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> uptime : {{metrics['uptime']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> systemload.average : {{metrics['systemload.average']}}
        </div>
      </b-list-group-item>
      <b-list-group-item>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> threads : {{metrics['threads']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> threads peak : {{metrics['threads.peak']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> threads daemon : {{metrics['threads.daemon']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> threads totalStarted : {{metrics['threads.totalStarted']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> classes : {{metrics['classes']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> classes.loaded : {{metrics['classes.loaded']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> classes.unloaded : {{metrics['classes.unloaded']}}
        </div>
      </b-list-group-item>
      <b-list-group-item>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Heap Memory : {{metrics['heap']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Heap init : {{metrics['heap.init']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Heap used : {{metrics['heap.used']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Heap committed : {{metrics['heap.committed']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Non Heap Memory : {{metrics['nonheap.committed']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Non Heap committed : {{metrics['nonheap.committed']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Non Heap Memory : {{metrics['nonheap.committed']}}
        </div>
        <div style="font-size:0.9em; color:grey">
          <i class="fas fa-arrow-circle-right"></i> Non Heap committed : {{metrics['nonheap.committed']}}
        </div>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>
<script>
  export default {
    name: 'selfmonitor',
    data () {
      return {
        timeoutID: '',
        metrics: '',
        tickermax: 3,
        ticker: 3
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
        this.$http.get('http://192.168.11.159:7090/metrics')
        .then((response) => {
          this.metrics = response.data
          this.ticker = this.tickermax
          this.tick()
        })
        .catch(function (error) {
          console.log('서버를 확인해 주시길 바랍니다.' + error)
        })
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