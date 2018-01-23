<template>
  <div class="custom-actions">
    <button class="ui basic button" @click="viewItem(rowData, rowIndex)"><i class="zoom icon"></i></button>
  </div>
</template>

<script>
  export default {
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
      viewItem (data, index) {
        this.$events.fire('detail-view', data.seq)
        // console.log('custom-actions: ' + action, data.name, index)
      }
    }
  }
</script>

<style>
  .custom-actions button.ui.button {
    padding: 8px 8px;
  }
  .custom-actions button.ui.button > i.icon {
    margin: auto !important;
  }
</style>