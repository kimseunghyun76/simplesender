<template>
  <b-container fluid>
    <b-row>
      <b-col>
         <b-card title="Process monitor & controll for Mcc"
          sub-title="제어는 반드는 관리자와 협의후에 진행 해주세요.">
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mt-3">
      <b-col>
        <b-card-group deck class="mb-3">
          <mcc-program
           pmname="rabbitmq-server"
           ip="0.0.0.0"
           port="5672"></mcc-program>
          <mcc-program
           pmname="mccgateway"
           ip="0.0.0.0"
           port="7088"></mcc-program>
        </b-card-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-card-group deck class="mb-3">
          <mcc-program
           pmname="elasticsearch"
           ip="0.0.0.0"
           port="9200"></mcc-program>
          <mcc-program
           pmname="mysqld"
           ip="0.0.0.0"
           port="3306"></mcc-program>
        </b-card-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-card-group deck class="mb-3">
          <mcc-program
           pmname="logstash"
           ip="0.0.0.0"
           port="9600"></mcc-program>
          <mcc-program
           pmname="filebeat"
           ip="0.0.0.0"
           port=""></mcc-program>
        </b-card-group>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
  import MccProgram from './program.vue'
  export default {
    components: {
      MccProgram
    }
  }
</script>
<style>
</style>