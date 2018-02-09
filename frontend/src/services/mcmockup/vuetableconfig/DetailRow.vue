<template>
    <div @click="onClick">
      <div class="inline field">
        <label>srcMrn: </label>
        <span>{{rowData.srcMrn}}</span>
      </div>
      <div class="inline field">
        <label>dstMrn: </label>
        <span>{{rowData.dstMrn}}</span>
      </div>
      <div class="inline field">
        <label>accessToken: </label>
        <span>{{rowData.accessToken}}</span>
      </div>
      <div class="inline field">
        <b-form-textarea v-model="payload" placeholder="S-100 Payload" :rows="11" :max-rows="12" readonly class='responseBodyForm'></b-form-textarea>
      </div>
    </div>
</template>

<script>
  export default {
    data () {
      return {
        payload: this.rowData.payload
      }
    },
    props: {
      rowData: {
        type: Object,
        required: true
      },
      rowIndex: {
        type: Number
      }
    },
    methods: {
      onClick (event) {
      }
    }
  }
</script>