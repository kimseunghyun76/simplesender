<template>
  <b-container fluid>
    <b-row>
      <b-col>
         <b-card title="Sitemap"
          sub-title="계정정보 문의는 관리자에게">
            <b-table :items="items" :fields="fields"  hover small>
            </b-table>
          </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
  import backend from '../../config/backend.js'
  export default {
    data () {
      return {
        fields: [ 'name', 'link', 'details' ],
        items: backend.linkitem
      }
    }
  }
</script>
<style>
</style>