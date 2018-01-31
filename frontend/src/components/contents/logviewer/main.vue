<template>
  <div id="app">
    <b-container fluid class="mt-3">
      <b-row>
        <b-col>
          <b-table hover small :items="queuelist" :fields="queuefields" caption-top>
            <template slot="table-caption">
              <h4>Message Queues for Mcc</h4>
            </template>
          </b-table> 
        </b-col>
      </b-row>
      <b-row class="mt-3">
        <b-col>
          여기에는 Elasticsearch에서 긁어온 로그를 보여줄꺼야.
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  data () {
    return {
      queuefields: [
        {
          key: 'status',
          label: 'Status',
          sortable: true
        },
        {
          key: 'name',
          label: 'Queue Name',
          sortable: true
        },
        {
          key: 'consumer',
          label: 'consumer',
          sortable: true
        },
        {
          key: 'total',
          label: 'total'
        },
        {
          key: 'ready',
          label: 'ready'
        },
        {
          key: 'unacked',
          label: 'unacked'
        },
        {
          key: 'inMemory',
          label: 'in Memory'
        },
        {
          key: 'persistent',
          label: 'persistent'
        },
        {
          key: 'processMemory',
          label: 'process Memory'
        }
      ],
      queuelist: [
      ]
    }
  },
  created: function () {
    window.setInterval(() => {
      this.$http.post('http://192.168.11.159:7090/queuelist')
      .then((response) => {
        // console.log(response)
        // TODO : 차후에 배열값을 비우고 채워넣는 것 말고, 치환하는 방법도 강구 필요
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
      })
      .catch(function (error) {
        console.log(error)
      })
    }, 2000)
  },
  methods: {
  },
  computed: {
  }
}
</script>
          
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
