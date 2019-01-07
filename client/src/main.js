import Vue from 'vue'
import App from './App.vue'
import VueResource from "vue-resource"
import Vue2Filters from "vue2-filters"

Vue.use(VueResource, Vue2Filters)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
