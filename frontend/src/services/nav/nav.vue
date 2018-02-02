<template>
  <b-navbar toggleable="md" type="dark" variant="dark" v-if="useremail!=null">
    <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
    <b-navbar-brand href="#">E-navigation(neonexsoft)</b-navbar-brand>
    <b-collapse is-nav id="nav_collapse">
      <b-navbar-nav>
        <b-nav-item href="/#/mcc/mockup">Http Sender</b-nav-item>
        <b-nav-item href="/#/mcc/coder">Encode/Decode</b-nav-item>
        <b-nav-item href="/#/mcc/logviewer">logviewer</b-nav-item>
        <b-nav-item href="#" disabled>Disabled</b-nav-item>
      </b-navbar-nav>
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
