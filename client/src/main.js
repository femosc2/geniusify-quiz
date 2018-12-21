import Vue from 'vue'
import App from './App.vue'
import VueResource from "vue-resource"
//const key = require("./apiKeys.js");


Vue.use(VueResource)

Vue.config.productionTip = false

// BQDTlmwYgms4HV8Rn8UTi2dJR4KiyjNxjpAd-5BRvDIguGgoDkTJCJolzJC2cFIW6mCLbCUdd7MFeXGT_iE
Vue.http.options.headers = { "Authorization": "Bearer " + "BQC28VUVtE6-4ZBwYuelUwDOJ0k3FSuH22W2gOlFi_hoV0G1FW-sfVAGmQ6ftrwB9dxp4hfxKeydFA6FApc" }

new Vue({
  render: h => h(App),
}).$mount('#app')
