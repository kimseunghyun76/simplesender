<template>
<b-container fluid>
  <b-row class="mb-3">
    <b-col lg="6">
    </b-col>
    <b-col lg="6">
      <b-input-group class="mt-3">
        <b-form-input
          id="targetUrl"
          type="text"
          v-model="filterText"
          @keyup.enter="doFilter" 
          placeholder="dstMrn or srcMrn"></b-form-input>
      <b-button-group>
        <b-button variant="info" @click="doFilter">Search</b-button>
        <b-button @click="resetFilter">Reset</b-button>
      </b-button-group>
      </b-input-group>
    </b-col>
  </b-row>
</b-container>
</template>

<script>
export default {
  data () {
    return {
      filterText: ''
    }
  },
  methods: {
    doFilter () {
      this.$events.fire('filter-set', this.filterText)
    },
    resetFilter () {
      this.filterText = ''
      this.$events.fire('filter-reset')
    }
  }
}
</script>