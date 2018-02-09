<template>
  <b-navbar toggleable="md" v-if="useremail!=null" >
    <b-collapse is-nav id="nav_collapse">
      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right>
          <!-- Using button-content slot -->
          <template slot="button-content">
            <em>{{useremail}}</em>
          </template>
          <b-dropdown-item @click="logout">Sign out</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>
<script>
import firebase from 'firebase'
export default {
  name: 'navigation',
  data () {
    return {
      useremail: firebase.auth().currentUser.email
    }
  },
  methods: {
    logout: function () {
      firebase.auth().signOut().then(() => {
        this.$router.replace('/auth/login')
      })
    }
  }
}
</script>
<style>

</style>
