import Vue from 'vue'
import App from './App.vue'
import VueResource from "vue-resource"
//const key = require("./apiKeys.js");


Vue.use(VueResource)

Vue.config.productionTip = false

// BQDTlmwYgms4HV8Rn8UTi2dJR4KiyjNxjpAd-5BRvDIguGgoDkTJCJolzJC2cFIW6mCLbCUdd7MFeXGT_iE
Vue.http.options.headers = { "Authorization": "Bearer " + "BQCF67E6iLtXRjwidhPcXz0A5bcOSIpbu-cSJ5Xxp72BDPpxLi4r4tiG9XzKPrcFI4D5UmG9GsX_Qcu4uxo" }

new Vue({
  render: h => h(App),
}).$mount('#app')
