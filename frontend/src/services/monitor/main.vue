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
           pmname="rabbitmq"
           ip="0.0.0.0"
           port="5672"></mcc-program>
          <mcc-program
           pmname="mccgateway"
           ip="0.0.0.0"
           port="7088"></mcc-program>
          <mcc-program
           pmname="mysql"
           ip="0.0.0.0"
           port="3306"></mcc-program>
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
           pmname="logstash"
           ip="0.0.0.0"
           port="9600"></mcc-program>
          <mcc-program
           pmname="kibana"
           ip="127.0.0.1"
           port="5601"></mcc-program>
        </b-card-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-card-group deck class="mb-3">
          <mcc-program
           pmname="mccdaemon.exe"
           ip=""
           port=""></mcc-program>
          <mcc-program
           pmname="filebeat.exe"
           ip=""
           port=""></mcc-program>
        </b-card-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
         <b-card title="서비스 기동 순서"
          sub-title="관리자에게 문의 필요" class="mt-3">
            <div class="mt-3">
              <h6>1) OS(Windows Server 2012) 내 아래 서비스들 정상 동작 여부</h6>
               Services 에서  <b> MccLogviewer</b> ,<b> MccGateway</b> ,<b>  Elaticsearch </b>, <b> RabbitMQ</b>  상태 체크 <br/><br/>
              
              <h6>2) Mcc 로그 수집 관련  서비스 기동</h6>
              - <b>Logstash</b> , <b>Filebeat</b> <br/>
              ** Logstash 기동 시간이 타 프로세스보다 상대적으로 길다.(짧게는 30초, 길게는 1분) <br/><br/>

              <h6>3) Mcc Gateway 기동</h6>
              - 수동 기동: java -jar MccGateway-1.0-SNAPSHOT.jar --spring.profiles.active=local <br/><br/>

              <h6>4) Daemon  기동</h6>
              -  <b>MCCDaemon.exe </b>
              </div>
          </b-card>
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