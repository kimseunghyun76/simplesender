<template>
  <div id="main">
    <b-container fluid>
      <b-row class="text-center" align-v="center">
          <b-col>
            <b-form @submit="onSubmit" style="padding:10px">
              <h1 class="h5 mb-3 font-weight-normal">Let's create a new account!</h1>
              <b-form-group id="IdPwGroup"
                            label=""
                            label-for="useremail"
                            description="">
                <b-form-input id="useremail"
                              type="email"
                              v-model="form.useremail"
                              required
                              placeholder="EMAIL" autofocus>
                </b-form-input>
                <b-form-input id="userpw"
                              type="password"
                              v-model="form.userpw"
                              required
                              placeholder="Password">
                </b-form-input>
              </b-form-group>
              <div class="text-center">
                <b-button type="submit" variant="primary">Sign In</b-button>
              </div>
              <div class="mt-3">or go back to <router-link to="/auth/login">Login</router-link></div>
            </b-form>
        </b-col>
        <b-col cols="8">
          <neonex-banner></neonex-banner>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import firebase from 'firebase'
import Vue from 'vue'
import banner from './banner'

Vue.component('neonex-banner', banner)
export default {
  name: 'signup',
  data () {
    return {
      form: {
        useremail: '',
        userpw: ''
      },
      show: true
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault()
      firebase.auth().createUserWithEmailAndPassword(this.form.useremail, this.form.userpw).then(
        (user) => {
          this.$router.replace('/mcc/mockup')
        },
        (err) => {
          alert('Oops, ' + err.message)
        }
      )
      // alert(JSON.stringify(this.form))
    },
    onReset (evt) {
      evt.preventDefault()
      /* Reset our form values */
      this.form.useremail = ''
      this.form.userpw = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    }
  }
}
</script>

<style>
#main {
  height: 100hz;
  font-size: 13px;
  color: black;
  border: 1px solid #000;
}
</style>
