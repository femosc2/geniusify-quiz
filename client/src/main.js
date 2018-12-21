import Vue from 'vue'
import App from './App.vue'
import VueResource from "vue-resource"
//const key = require("./apiKeys.js");


Vue.use(VueResource)

Vue.config.productionTip = false

// BQDTlmwYgms4HV8Rn8UTi2dJR4KiyjNxjpAd-5BRvDIguGgoDkTJCJolzJC2cFIW6mCLbCUdd7MFeXGT_iE
Vue.http.options.headers = { "Authorization": "Bearer " + "BQBtvFYSUp0hIM9tVvqEU0SLadUX-W2xdVwAFZsU6nWioeRsR7cAz1cEDV0IHmEhDJDrHT6PXEzelKFSq4A" }

new Vue({
  render: h => h(App),
}).$mount('#app')
