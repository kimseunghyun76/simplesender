<template>
  <div id="main">
    <b-container fluid>
      <b-row class="text-center" align-v="center">
          <b-col>
            <b-form @submit="onSubmit" style="padding:10px">
              <h1 class="h2 mb-3 font-weight-normal">Welcome!</h1>
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
                <b-button type="submit" variant="primary">Login</b-button>
              </div>
              <div class="mt-3">you don't have an account? You can <router-link to="/auth/signup">create one</router-link></div>
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
  name: 'login',
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
      // alert(JSON.stringify(this.form))
      firebase.auth().signInWithEmailAndPassword(this.form.useremail, this.form.userpw).then(
        (user) => {
          this.$router.replace('/mcc/mockup')
        },
        (err) => {
          alert('Oops, ' + err.message)
        }
      )
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