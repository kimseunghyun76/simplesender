<template>
  <b-container fluid>
    <b-row>
      <b-col>
        <b-card title="Encoder/Decoder"
                sub-title="Input a String of text  and encode or decode it as you like">
        </b-card> 
      </b-col>
    </b-row>
    <b-row class="mt-3">
      <b-col>
        <b-form-textarea 
          v-model="requestText"
          placeholder="Enter Text" 
          :rows="8" 
          :max-rows="10" 
          :state="computedtranslate"
          contenteditable></b-form-textarea>
      </b-col>
    </b-row>
    <b-row class="mt-3">
      <b-col>
        <h3>Encoded</h3>
        <b-form-textarea 
          v-model.trim="encodedText"
          :rows="8" 
          :max-rows="10" 
          readonly></b-form-textarea>
      </b-col>
      <b-col>
        <h3>Decoded</h3>
        <b-form-textarea 
          v-model.trim="decodedText"
          :rows="8" 
          :max-rows="10" 
          readonly></b-form-textarea>
      </b-col>
    </b-row>
    <b-row class="mt-3">
      <b-col>
        <h3>Base64 Encoded (atob)</h3>
        <b-form-textarea 
          v-model.trim="encoded64Text"
          :rows="8" 
          :max-rows="10" 
          readonly></b-form-textarea>
      </b-col>
      <b-col>
        <h3>Base64 Decoded (btoa)</h3>
        <b-form-textarea 
          v-model.trim="decoded64Text"
          :rows="8" 
          :max-rows="10" 
          readonly></b-form-textarea>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      requestText: '',
      encodedText: '',
      decodedText: '',
      encoded64Text: '',
      decoded64Text: ''
    }
  },
  created: function () {
  },
  methods: {
    realtranslate () {
      this.decodedText = decodeURI(this.requestText)
      this.encodedText = encodeURI(this.requestText)
      try {
        this.decoded64Text = btoa(this.requestText)
        this.encoded64Text = atob(this.requestText)
      } catch (err) {
      }
    }
  },
  computed: {
    computedtranslate () {
      if (this.requestText.length > 0) {
        this.realtranslate()
      }
    }
  }
}
</script>
          
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
